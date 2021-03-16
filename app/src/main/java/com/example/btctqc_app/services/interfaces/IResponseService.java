package com.example.btctqc_app.services.interfaces;

import com.example.btctqc_app.BuildConfig;
import com.example.btctqc_app.services.models.ConditionModel;
import com.example.btctqc_app.services.models.JsonArrayHolder;
import com.example.btctqc_app.services.models.ResponseModel;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresCookie;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = BuildConfig.IP + BuildConfig.API_PORT+ BuildConfig.BASE_URL,converters = { MappingJackson2HttpMessageConverter.class })
public interface IResponseService {


    @Get("/records/view_responses?filter=survey_id,eq,{surveyID}")
        //@RequiresCookie(Constants.SESSION_NAME)
    JsonArrayHolder<ResponseModel> getAll(@Path int surveyID);

    @Post("/records/responses?exclude=response_id,create_time_stamp")
    //@RequiresCookie(Constants.SESSION_NAME)
    Integer addNew(@Body ResponseModel model);
}
