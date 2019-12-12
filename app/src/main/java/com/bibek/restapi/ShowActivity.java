package com.bibek.restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bibek.restapi.api.EmployeeAPI;
import com.bibek.restapi.model.Employee;
import com.bibek.restapi.model.EmployeeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowActivity extends AppCompatActivity {
    TextView tvOutPut;

  //private  static  String base_url = "http://dummy.restapiexample.com/api/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tvOutPut = findViewById(R.id.tvOutPut);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dummy.restapiexample.com/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();
         //Interface Instance

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        //Asynchronus call
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (! response.isSuccessful()){
                    Toast.makeText(ShowActivity.this,"Error" + response.code(), Toast.LENGTH_SHORT);
                    return;
                }
                List<Employee> lstEmployee = response.body();

                for (Employee employee: lstEmployee){

                    String emp ="";
                    emp += "ID :" + employee.getId() + "\n";
                    emp += "employee_name :" + employee.getEmployee_name() + "\n";
                    emp += "employee_salary :" + employee.getEmployee_salary() + "\n";
                    emp += "employee_age :" + employee.getEmployee_age() + "\n";
                    emp += "profile_image :" + employee.getProfile_image() + "\n";
                    emp += "------------------------------------------ "+"\n";

                   tvOutPut.append(emp);



                }




            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                    tvOutPut.setText("Error" + t.getMessage());
            }
        });
    }
}
