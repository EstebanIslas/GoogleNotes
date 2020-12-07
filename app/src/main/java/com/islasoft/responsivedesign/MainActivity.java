package com.islasoft.responsivedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btn_login.isPressed()){
            toast("Welcome!!");
            Intent notes = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(notes);
        }
    }

    private void toast(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}