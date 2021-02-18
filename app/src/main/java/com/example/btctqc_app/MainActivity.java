package com.example.btctqc_app;

import android.content.Intent;
import android.os.Bundle;

import com.example.btctqc_app.misc.MyPrefs_;
import com.example.btctqc_app.services.interfaces.ISurveyService;
import com.example.btctqc_app.services.models.SurveyModel;
import com.example.btctqc_app.ui.login.LoginActivity_;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.RestClientException;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    static final int LOGIN_REQUEST_CODE = 101;
    static final int SCANNER_REQUEST_CODE = 102;
    static final int CONDITION_UPDATE_REQUEST_CODE = 103;
    @ViewById
    FloatingActionButton fab;
    @ViewById
    Toolbar toolbar;
    @Pref
    MyPrefs_ myPrefs;
    @RestService
    ISurveyService surveyService;
    @AfterViews
    void afterViews(){

    setSupportActionBar(toolbar);
        LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
    }

    @Click
    void fab(){
        Scanner_.intent(this).startForResult(SCANNER_REQUEST_CODE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @OnActivityResult(LOGIN_REQUEST_CODE)
    void onResult(int resultCode) {
        if (resultCode == RESULT_CANCELED){
            finish();
        }else{
            Toast.makeText(this,"Successfully login as: "
                    + myPrefs.currentUserName().get(),Toast.LENGTH_LONG).show();
        }
    }
    @OnActivityResult(SCANNER_REQUEST_CODE)
    void onScannerResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            int busInfoID = data.getIntExtra("busInfoID",0);
            int currentUserID = myPrefs.currentUserID().get();
            addNewSurvey(busInfoID,currentUserID);
        }
    }
    @OnActivityResult(CONDITION_UPDATE_REQUEST_CODE)
    void onConditionUpdateResult(int resultCode) {
        if (resultCode == RESULT_OK){
           Toast.makeText(this,"Thank you for accomplishing this survey.",Toast.LENGTH_LONG).show();
        }
    }
    @Background
    void addNewSurvey(int busInfoID, int currentUserID) {
        try {
            SurveyModel surveyModel = new SurveyModel();
            surveyModel.setUser_id(currentUserID);
            surveyModel.setBus_info_id(busInfoID);
            int surveyID = surveyService.addNew(surveyModel);
            addNewSurveySuccess(surveyID);
        }catch (RestClientException ex){
            showError(ex.getMessage());
        }
    }
    @UiThread
    void addNewSurveySuccess(int surveyID) {
        Log.d("success","adding new survey");
        Toast.makeText(this,"Lunching condition ",Toast.LENGTH_LONG).show();
        ConditionUpdateActivity_.intent(this).surveyID(surveyID).startForResult(CONDITION_UPDATE_REQUEST_CODE);

    }
    @UiThread
    void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}