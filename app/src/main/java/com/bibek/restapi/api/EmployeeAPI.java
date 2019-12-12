package com.bibek.restapi.api;

import com.bibek.restapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {
    //Retriving all employee
    @GET("employees")
   Call<List<Employee>> getAllEmployees();

    @GET("employee/{empId}")
    Call<Employee>getEmployeeById(@Path("empId") int empId);
}
