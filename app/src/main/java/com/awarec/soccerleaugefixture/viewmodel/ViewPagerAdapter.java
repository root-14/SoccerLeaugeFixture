package com.awarec.soccerleaugefixture.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.awarec.soccerleaugefixture.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private String[] team_names;


    public ViewPagerAdapter(Context context,  String[] team_names) {
        this.context = context;
        this.team_names = team_names;
    }


    @Override
    public int getCount() {
        return team_names.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_view_pager, container, false);

        TextView tv_week, tv_matches;

        tv_week = view.findViewById(R.id.tv_week);
        tv_matches = view.findViewById(R.id.tv_matches);

        tv_matches.setText(team_names[position]);
        tv_week.setText("Week: " + position);

        //container.addView(view, position);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {

        container.removeView((View) object);
        //super.destroyItem(container, position, object);
    }
}
