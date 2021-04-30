package com.example.btctqc_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btctqc_app.R;
import com.example.btctqc_app.adapters.ConditionListAdapter;
import com.example.btctqc_app.adapters.ItemClickSupport;
import com.example.btctqc_app.adapters.ResponseListAdapter;
import com.example.btctqc_app.adapters.SimpleDividerItemDecoration;
import com.example.btctqc_app.itemviews.ResponseItemView;
import com.example.btctqc_app.services.interfaces.IConditionService;
import com.example.btctqc_app.services.interfaces.IResponseService;
import com.example.btctqc_app.services.interfaces.ISurveyService;
import com.example.btctqc_app.services.models.ConditionModel;
import com.example.btctqc_app.services.models.ResponseModel;
import com.example.btctqc_app.services.models.SurveyModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_symptom_update)
public class ConditionUpdateActivity extends AppCompatActivity {

    @RestService
    IConditionService conditionService;
    @RestService
    IResponseService responseService;
    @RestService
    ISurveyService surveyService;
    @ViewById
    Spinner spinner;
    @ViewById(R.id.bus_code)
    TextView busCode;
    @ViewById(R.id.bus_name)
    TextView busName;
    @ViewById(R.id.full_name)
    TextView fullName;
    @ViewById(R.id.contact)
    TextView contact;
    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.start_route)
    EditText startRoute;
    @ViewById(R.id.destination_route)
    EditText destinationRoute;
    @ViewById
    RecyclerView recyclerView;
    @Bean
    ResponseListAdapter adapter;
    @Extra
    int surveyID;

    private List<ConditionModel> conditionModels;
    private int selectedConditionID;
    private List<ResponseModel> responseModels;

    @AfterViews
    void afterViews(){
        initializeRecyclerView();
        loadConditions();
        loadSurveyDetail();

    }
    @Background(serial = "init")
    void loadSurveyDetail() {
        try{

            SurveyModel surveyModel = surveyService.getAll(surveyID).getSingleRecord();
            loadSurveyDetailSuccess(surveyModel);
        }catch (RestClientException e){
            showError(e.getMessage());
        }
    }
    @UiThread()
    void loadSurveyDetailSuccess(SurveyModel surveyModel) {
        Log.d("Survey Name",surveyModel.getFirst_name());
        busCode.setText(surveyModel.getBus_code());
        busName.setText(surveyModel.getBus_name());
        fullName.setText(surveyModel.getFirst_name() + " " + surveyModel.getMiddle_name() + " " +surveyModel.getLast_name());
        contact.setText(surveyModel.getContact_number());
        date.setText(surveyModel.getCreate_time_stamp());
    }

    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
            // do it
            TextView t = v.findViewById(R.id.id);
            int id = Integer.parseInt(t.getText().toString());
            //Todo: Add dialog to delete item
        });
    }

    @Background(serial = "init")
    void loadConditions(){
        try{
            conditionModels = conditionService.getAll().getRecords();
            loadConditionsSuccess(conditionModels);
        }catch (RestClientException e){
            showError(e.getMessage());
        }
    }
    @Background
    void loadResponses(){
        try{

            responseModels = responseService.getAll(surveyID).getRecords();
            loadResponsesSuccess(responseModels);
        }catch (RestClientException e){
            showError(e.getMessage());
        }
    }
    @UiThread
    void loadResponsesSuccess(List<ResponseModel> responseModels) {
        adapter.setList(responseModels);
        adapter.notifyDataSetChanged();
    }

    @UiThread
   void showError(String message) {
        Log.e("Error",message);
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    @UiThread
    void loadConditionsSuccess(List<ConditionModel> conditionModels) {
        ArrayList<String> names =  new ArrayList<>();
        for (ConditionModel c: conditionModels
             ) {
            names.add(c.getName());
        }
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<ConditionModel> adapter = new ArrayAdapter(this,
                R.layout.item_spinner,names);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @ItemSelect(R.id.spinner)
    void itemSelect(boolean selected, int position){
        Log.d("Selected Spinner value", String.valueOf(position));
        selectedConditionID = conditionModels.get(position).getCondition_id();

    }
    @Click(R.id.add)
    void onAdd(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setSurvey_id(surveyID);
        responseModel.setCondition_id(selectedConditionID);
        addNewResponse(responseModel);
    }
    @Background
    void addNewResponse(ResponseModel responseModel) {
        try {
            responseService.addNew(responseModel);
            loadResponses();
        }catch(RestClientException ex){
            showError(ex.getMessage());
        }
    }
    @Background
    void updateSurveyRoute(SurveyModel surveyModel) {
        try {
            surveyService.edit(surveyID,surveyModel);
           closeSurveyForm();
        }catch(RestClientException ex){
            showError(ex.getMessage());
        }
    }
    @UiThread
    void closeSurveyForm() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Click(R.id.finish)
    void onFinish(){
        SurveyModel surveyModel = new SurveyModel();
        surveyModel.setDestination_route(destinationRoute.getText().toString());
        surveyModel.setStart_route(startRoute.getText().toString());
        updateSurveyRoute(surveyModel);
    }
    //todo: add delete on condition


}