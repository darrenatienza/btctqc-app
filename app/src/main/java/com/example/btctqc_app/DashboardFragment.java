package com.example.btctqc_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.btctqc_app.misc.MyPrefs_;
import com.example.btctqc_app.services.interfaces.ISurveyService;
import com.example.btctqc_app.services.models.SurveyModel;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.RestClientException;

import static android.app.Activity.RESULT_OK;

@EFragment
public class DashboardFragment extends Fragment {

    static final int SCANNER_REQUEST_CODE = 102;

    @Pref
    MyPrefs_ myPrefs;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHistoryClick(view);
        initScanQRCodeClick(view);
        initAccountSettingClick(view);
    }

    private void initAccountSettingClick(View view) {
        view.findViewById(R.id.account_setting).setOnClickListener(view1 -> {
           /** NavHostFragment.findNavController(DashboardFragment.this)
                    .navigate(R.id.action_DashboardFragment_to_HistoryFragment); */
            Uri uri = Uri.parse(BuildConfig.IP+BuildConfig.WEB_PORT); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

    private void initScanQRCodeClick(View view) {
        view.findViewById(R.id.scan_qr_code).setOnClickListener(view1 -> {
            Scanner_.intent(getContext()).startForResult(SCANNER_REQUEST_CODE);
        });
    }

    private void initHistoryClick(View view) {
        view.findViewById(R.id.history).setOnClickListener(view1 -> {
            /**NavHostFragment.findNavController(DashboardFragment.this)
                    .navigate(R.id.action_DashboardFragment_to_HistoryFragment);*/
            Uri uri = Uri.parse(BuildConfig.IP+BuildConfig.WEB_PORT); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

    }







}