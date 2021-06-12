package com.awarec.soccerleaugefixture.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.awarec.soccerleaugefixture.R;
import com.awarec.soccerleaugefixture.viewmodel.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String[] team_names;

    private ViewPager viewPager;
    private Button btn_drawFixtures;
    private TextView tv_allTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewpager);
        btn_drawFixtures = findViewById(R.id.btn_draw_fixtures);
        tv_allTeams = findViewById(R.id.tv_all_teams);

        team_names = fillList(6);

        String allNames = new String();
        for (int i = 0; i < team_names.length; i++) {

            allNames += "\n\n" + team_names[i];
        }

        tv_allTeams.setText(allNames);


        btn_drawFixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setVisibility(View.VISIBLE);
                System.out.println("tık tık");
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, team_names);
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