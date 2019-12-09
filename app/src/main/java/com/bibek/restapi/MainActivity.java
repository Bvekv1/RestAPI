package com.bibek.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRegister, btnUpdateDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.btnRegister);
        btnUpdateDelete = findViewById(R.id.btnUpdateDelete);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenRegister();
            }
        });
        btnUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenUpdateDelete();
            }
        });
    }
    private  void OpenRegister(){
        Intent intent = new Intent(MainActivity.this,RegisterEmployee.class);
        startActivity(intent);
    }

    private  void OpenUpdateDelete() {
        Intent intent = new Intent(MainActivity.this, UpdateOrDeleteEmployee.class);
        startActivity(intent);
    }
}
