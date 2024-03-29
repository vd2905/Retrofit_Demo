package com.example.retrofit_demo;

import static com.example.retrofit_demo.Splash_Activity.editor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit_demo.databinding.ActivityLoginBinding;

import Models.Instance_class;
import Models.Login_Class;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instance_class.Callapi().loginUser(binding.email.getText().toString(),binding.password.getText().toString()).enqueue(new Callback<Login_Class>() {
                    @Override
                    public void onResponse(Call<Login_Class> call, Response<Login_Class> response) {
                        if(response.body().getConnection()==1)
                        {
                            if (response.body().getResult() == 1) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                editor.putInt("login",1);
                                editor.putInt("uid", Integer.parseInt(response.body().getUserdata().getId()));
                                editor.putString("name",response.body().getUserdata().getName());
                                editor.putString("email",response.body().getUserdata().getEmail());
                                editor.commit();
                                startActivity(intent);
                                finish();
                                Toast.makeText(LoginActivity.this, "Log In SuccessFully", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "User Not Found!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login_Class> call, Throwable t)
                    {
                        Log.d("TTT", "onFailure: off = "+t.getLocalizedMessage());
                    }
                });

            }
        });
        binding.createAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}