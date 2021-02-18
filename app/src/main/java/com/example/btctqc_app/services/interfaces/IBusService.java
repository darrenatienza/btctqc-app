package com.example.btctqc_app.services.interfaces;

import com.example.btctqc_app.BuildConfig;
import com.example.btctqc_app.services.models.BusInfoModel;
import com.example.btctqc_app.services.models.ConditionModel;
import com.example.btctqc_app.services.models.JsonArrayHolder;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = BuildConfig.BASE_URL,converters = { MappingJackson2HttpMessageConverter.class })
public interface IBusService {
    @Get("/records/bus_infos?filter=code,eq,{code}")
        //@RequiresCookie(Constants.SESSION_NAME)
    JsonArrayHolder<BusInfoModel> getByCode(@Path String code);
}
