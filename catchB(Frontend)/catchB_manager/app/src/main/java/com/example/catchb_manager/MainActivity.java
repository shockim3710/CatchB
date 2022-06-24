package com.example.catchb_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AddImageClick(View view) {
        Intent intent = new Intent(this, AddImage.class);
        startActivity(intent);
    }

    public void ProcessImageClick(View view) {
        Intent intent = new Intent(this, ProcessImage.class);
        startActivity(intent);
    }

    public void AddGifticonClick(View view) {
        Intent intent = new Intent(this, AddGifticon.class);
        startActivity(intent);
    }


}