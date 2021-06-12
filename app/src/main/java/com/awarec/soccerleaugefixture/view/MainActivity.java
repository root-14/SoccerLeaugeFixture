package com.awarec.soccerleaugefixture.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.awarec.soccerleaugefixture.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> team_names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



}