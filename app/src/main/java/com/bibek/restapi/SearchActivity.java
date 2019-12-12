package com.bibek.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bibek.restapi.api.EmployeeAPI;
import com.bibek.restapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private TextView tvResult;
    private EditText etSearch;
    private Button btnFind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tvResult = findViewById(R.id.tvResult);
        etSearch = findViewById(R.id.etSearch);
        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();
            }
        });


        }
        private void Search(){
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dummy.restapiexample.com/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();

                 //Interface instance

            EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
            Call<Employee> listCall = employeeAPI.getEmployeeById(Integer.parseInt(etSearch.getText().toString()));

            //Asynchronus call

            listCall.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    if (! response.isSuccessful()){
                        Toast.makeText(SearchActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT);
                         return;
                    }

                    String serchResult = "";

                    serchResult += "ID :" + response.body().getId() + "\n";
                    serchResult += "employee_name :" + response.body().getEmployee_name() + "\n";
                    serchResult += "employee_salary :" + response.body().getEmployee_salary() + "\n";
                    serchResult += "employee_age :" + response.body().getEmployee_age() + "\n";
                    serchResult += "profile_image :" + response.body().getProfile_image();

                    tvResult.setText(serchResult);

                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                  tvResult.setText("Error" + t.getMessage());
                }
            });

        }



    }


