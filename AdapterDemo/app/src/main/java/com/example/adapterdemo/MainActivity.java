package com.example.adapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button arrayAdapterDemoBt;
    private Button simpleAdapterDemoBt;
    private Button baseAdapterDemoBt;
    private Button gridViewDemoBt;
    private Button autoTextDemoBt;
    private Button spinnerDemoBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapterDemoBt = (Button) findViewById(R.id.arrayAdapterDemoBt);
        simpleAdapterDemoBt = (Button) findViewById(R.id.simpleAdapterDemoBt);
        baseAdapterDemoBt = (Button) findViewById(R.id.baseAdapterDemoBt);
        gridViewDemoBt = (Button) findViewById(R.id.gridViewDemoBt);
        autoTextDemoBt = (Button) findViewById(R.id.autoTextDemoBt);
        spinnerDemoBt = (Button) findViewById(R.id.spinnerDemoBt);

        arrayAdapterDemoBt.setOnClickListener(this);
        simpleAdapterDemoBt.setOnClickListener(this);
        baseAdapterDemoBt.setOnClickListener(this);
        gridViewDemoBt.setOnClickListener(this);
        autoTextDemoBt.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.arrayAdapterDemoBt:
                intent = new Intent(MainActivity.this, ArrayAdapterDemo.class);
                startActivity(intent);
                break;
            case R.id.simpleAdapterDemoBt:
                intent = new Intent(MainActivity.this, SimpleAdapterDemo.class);
                startActivity(intent);
                break;
            case R.id.baseAdapterDemoBt:
                intent = new Intent(MainActivity.this, BaseAdapterDemo.class);
                startActivity(intent);
                break;
            case R.id.gridViewDemoBt:
                intent = new Intent(MainActivity.this, GridViewSimpleAdapter.class);
                startActivity(intent);
                break;
            case R.id.autoTextDemoBt:
                intent = new Intent(MainActivity.this, AutoCompleteTextViewArrayAdapter.class);
                startActivity(intent);
                break;
        }
    }

}