package com.awarec.soccerleaugefixture.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.awarec.soccerleaugefixture.R;
import com.awarec.soccerleaugefixture.interfaces.JsonApi;
import com.awarec.soccerleaugefixture.model.Model;
import com.awarec.soccerleaugefixture.viewmodel.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private Button btn_drawFixtures;
    private TextView tv_allTeams;
    private ProgressBar progressBar;

    public String BASE_URL = "https://60c1fe47069afc0017f494a3.mockapi.io/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewpager);
        btn_drawFixtures = findViewById(R.id.btn_draw_fixtures);
        tv_allTeams = findViewById(R.id.tv_all_teams);
        progressBar = findViewById(R.id.progressBar);

        callApi();

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                System.out.println("gece modu evet");
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                System.out.println("gece modu hayır");

                break;
        }

    }

    //getting team names from REST API
    public void callApi() {
        //getting data
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<List<Model>> call = jsonApi.getTeams();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "an error happened.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //response successful
                progressBar.setVisibility(View.INVISIBLE);

                List<Model> modelList = response.body();

                String[] _teams = new String[modelList.size()];
                for (int i = 0; i < modelList.size(); i++) {
                    _teams[i] = modelList.get(i).getTeam_name();
                }

                //list to all team names
                String allNames = new String();
                for (int i = 0; i < _teams.length; i++) {

                    allNames += "\n\n" + _teams[i];
                }

                //displaying all teams when app launched
                tv_allTeams.setText(allNames);

                //fixture button listener
                btn_drawFixtures.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setVisibility(View.VISIBLE);
                        System.out.println("tık tık");
                    }
                });
                //viewpager
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), _teams);
                viewPager.setAdapter(viewPagerAdapter);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "an error happened.", Toast.LENGTH_SHORT).show();
            }
        });

        return;

    }

    //for demo purposes
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