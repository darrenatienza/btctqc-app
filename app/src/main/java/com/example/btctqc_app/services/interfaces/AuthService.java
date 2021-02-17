package com.example.btctqc_app.services.interfaces;

import com.example.btctqc_app.BuildConfig;
import com.example.btctqc_app.services.models.LoginModel;
import com.example.btctqc_app.services.models.UserModel;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
@Rest(rootUrl = BuildConfig.BASE_URL,converters = { MappingJackson2HttpMessageConverter.class })
public interface AuthService {
    @Post("/login")
    //@SetsCookie({Constants.SESSION_NAME}) // retrieves cookie from http request
    UserModel login(@Body LoginModel model);

}
