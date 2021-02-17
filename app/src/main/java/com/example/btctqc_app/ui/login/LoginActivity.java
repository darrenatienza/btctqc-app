package com.example.btctqc_app.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btctqc_app.R;
import com.example.btctqc_app.services.interfaces.AuthService;
import com.example.btctqc_app.services.models.LoginModel;
import com.example.btctqc_app.ui.login.LoginViewModel;
import com.example.btctqc_app.ui.login.LoginViewModelFactory;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.RestClientException;

@EActivity
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    @ViewById(R.id.username)
    EditText usernameEditText;
    @ViewById(R.id.password)
    EditText passwordEditText;
    @ViewById(R.id.login)
    Button loginButton;
    @ViewById(R.id.loading)
    ProgressBar loadingProgressBar;

    @RestService
    AuthService authService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                   //login request here
                }
                return false;
            }
        });


    }
    @Click(R.id.login)
    void onClickLogin(){
        loadingProgressBar.setVisibility(View.VISIBLE);
        onLogin();
    }
    @Background
    void onLogin(){
        try {
            LoginModel loginModel = new LoginModel();
            loginModel.setPassword(passwordEditText.getText().toString());
            loginModel.setUsername(usernameEditText.getText().toString());
            onLoginSuccess();
        }catch (RestClientException ex){
            showLoginFailed(R.string.login_failed);
        }
    }
    @UiThread
    void onLoginSuccess(){
        loadingProgressBar.setVisibility(View.GONE);
        Log.d("login", "success");
        setResult(Activity.RESULT_OK);
        //Complete and destroy login activity once successful
        finish();
    }


    @UiThread
    void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}