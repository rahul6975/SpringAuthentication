package com.rahul.authenticateusingspring.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rahul.authenticateusingspring.R;
import com.rahul.authenticateusingspring.remote.ApiService;
import com.rahul.authenticateusingspring.remote.Network;
import com.rahul.authenticateusingspring.responseClasses.ResponseClass;
import com.rahul.authenticateusingspring.responseClasses.ResponseRegisterClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvRegister;
    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeListeners();
        onClickListeners();
    }

    private void onClickListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUsername() && validatePassword()) {
                    ResponseRegisterClass responseRegisterClass = new ResponseRegisterClass(etUsername.getText().toString(), etPassword.getText().toString());

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.getUser(responseRegisterClass).enqueue(new Callback<ResponseClass>() {
                        @Override
                        public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                            if (response.body() != null) {
                                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, SuccessfulLogin.class);
                                intent.putExtra("username", response.body().getUsername());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseClass> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }

    private boolean validateUsername() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError("username cannot be empty");
            etUsername.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("password cannot be empty");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void initializeListeners() {
        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        tvRegister = findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnLogin);
    }
}