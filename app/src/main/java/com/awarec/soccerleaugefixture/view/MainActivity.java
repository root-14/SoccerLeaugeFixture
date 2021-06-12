package com.awarec.soccerleaugefixture.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.awarec.soccerleaugefixture.R;
import com.awarec.soccerleaugefixture.viewmodel.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String[] team_names;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, fillList(6));
        viewPager.setAdapter(viewPagerAdapter);
    }


    public static String[] fillList(int count) {

        String array[] = new String[count];

        for (int i = 0; i < count; i++) {

            array[i] = "team" + (i + 1);

            // System.out.println(array[i]);

        }

        System.out.println("length: " + array.length);
        return array;
    }


}