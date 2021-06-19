package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this,Game.class));
        /*
        Random random = new Random();
        int rnd= random.nextInt();
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource((Integer)imageArrayList.get(rnd));
        */


    }
}