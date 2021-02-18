package com.example.btctqc_app.services.interfaces;

import com.example.btctqc_app.BuildConfig;
import com.example.btctqc_app.services.models.JsonArrayHolder;
import com.example.btctqc_app.services.models.ResponseModel;
import com.example.btctqc_app.services.models.SurveyModel;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresCookie;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = BuildConfig.BASE_URL,converters = { MappingJackson2HttpMessageConverter.class })
public interface ISurveyService {

    @Get("/records/view_surveys?filter=survey_id,eq,{surveyID}")
        //@RequiresCookie(Constants.SESSION_NAME)
    JsonArrayHolder<SurveyModel> getAll(@Path int surveyID);

    @Post("/records/surveys?exclude=survey_id,create_time_stamp")
        //@RequiresCookie(Constants.SESSION_NAME)
    Integer addNew(@Body SurveyModel model);
}
