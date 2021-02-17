package com.example.btctqc_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btctqc_app.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_symptom_update)
public class ConditionUpdateActivity extends AppCompatActivity {

    @ViewById
    Spinner spinner;

    @AfterViews
    void afterViews(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
    @ItemSelect(R.id.spinner)
    void itemSelect(boolean selected, String position){
        Toast.makeText(this,String.valueOf(position),Toast.LENGTH_SHORT).show();
    }
    //Todo:add loading of health conditions


}