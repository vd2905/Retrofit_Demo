package com.example.retrofit_demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Models.Instance_class;
import Models.Register_Class;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView submit;
    EditText name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Instance_class.Callapi().registerUser(name.getText().toString(),email.getText().toString(),password.getText().toString()).enqueue(new Callback<Register_Class>() {
                    @Override
                    public void onResponse(Call<Register_Class> call, Response<Register_Class> response) {
                        if(response.body().getConnection()==1)
                        {
                            if (response.body().getResult() == 1) {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(RegisterActivity.this, "New Account Created SuccessFully", Toast.LENGTH_LONG).show();
                            }
                            if (response.body().getResult() == 0) {
                                Toast.makeText(RegisterActivity.this, "Sorry,Account Not Created,Try Again..", Toast.LENGTH_LONG).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Register_Class> call, Throwable t)
                    {
                        Log.d("TTT", "onFailure: off = "+t.getLocalizedMessage());
                    }
                });
            }
        });

    }
}