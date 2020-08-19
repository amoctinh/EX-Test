package com.example.logincheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DangXuat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
    }

    public void clickback(View view) {
        Intent it=new Intent(DangXuat.this,MainActivity.class);
        startActivity(it);
    }

    public void clicklogout(View view) {
        Intent it=new Intent(DangXuat.this,MainActivity.class);
        startActivity(it);
    }
}
