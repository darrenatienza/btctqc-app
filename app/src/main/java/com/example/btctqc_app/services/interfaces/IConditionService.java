package com.example.btctqc_app.services.interfaces;

import com.example.btctqc_app.BuildConfig;
import com.example.btctqc_app.services.models.ConditionModel;
import com.example.btctqc_app.services.models.JsonArrayHolder;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.RequiresCookie;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = BuildConfig.IP+ BuildConfig.API_PORT + BuildConfig.BASE_URL,converters = { MappingJackson2HttpMessageConverter.class })
public interface IConditionService {

    @Get("/records/conditions")
    //@RequiresCookie(Constants.SESSION_NAME)
    JsonArrayHolder<ConditionModel> getAll();


}
