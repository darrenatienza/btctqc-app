package com.example.btctqc_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.btctqc_app.services.interfaces.IBusService;
import com.example.btctqc_app.services.models.BusInfoModel;
import com.google.zxing.Result;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.RestClientException;

@EActivity(R.layout.activity_code_scanner)
public class Scanner extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    @ViewById(R.id.scanner_view)
    CodeScannerView scannerView;
    @ViewById(R.id.code)
    TextView code;
    @ViewById(R.id.please_wait)
    TextView pleaseWait;
    @RestService
    IBusService busService;
    @AfterViews
    void afterViews(){
        codeScanner();
    }

    private void codeScanner() {
        mCodeScanner = new CodeScanner(this,scannerView);
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> onScanSuccess(result.getText())));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());
    }

    private void onScanSuccess(String resultCode) {
        //show visible result
        code.setText(resultCode);
        pleaseWait.setVisibility(View.VISIBLE);
        validateResultCode(resultCode);
    }
    @Background
    void validateResultCode(String code) {
        try {
            BusInfoModel busInfoModel = busService.getByCode(code).getSingleRecord();
            if(busInfoModel == null){
                onValidateResultCodeFailed();
            }else{
                onValidateResultCodeSuccess(busInfoModel.getBus_info_id());
            }
        }catch (RestClientException ex){
            showError(ex.getMessage());
        }
    }
    @UiThread
    void showError(String message) {
        Log.e("Error",message);
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @UiThread
     void onValidateResultCodeFailed() {
        pleaseWait.setText("Your scanned code not found on our database, please try again!");
    }

    @UiThread
    void onValidateResultCodeSuccess(int busInfoID) {
        pleaseWait.setText("Successful!");
        Intent intent = new Intent();
        intent.putExtra("busInfoID",busInfoID);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}