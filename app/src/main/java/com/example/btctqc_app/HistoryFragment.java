package com.example.btctqc_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.btctqc_app.misc.MyPrefs_;
import com.example.btctqc_app.services.interfaces.ISurveyService;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@EFragment
public class HistoryFragment extends Fragment {

    @RestService
    ISurveyService surveyService;
    @Pref
    MyPrefs_ myPrefs;

    EditText date;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       initToDashboardClick(view);
       initDateInput(view);
    }

    private void initDateInput(View view) {
        date = view.findViewById(R.id.date);
        date.setText(currentDate());
    }

    private String currentDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return df.format(c);
    }

    private void initToDashboardClick(View view) {
        view.findViewById(R.id.back)
                .setOnClickListener(view1 -> NavHostFragment.findNavController(HistoryFragment.this)
                .navigate(R.id.action_HistoryFragment_to_DashboardFragment));
    }
}