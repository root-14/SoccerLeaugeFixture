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
    static ArrayList<String[]> weeksList;

    private int week, totalMatchCount, weeklyMatchCount;

    //constructor
    public ViewPagerAdapter(Context context, String[] team_names) {
        this.context = context;
        this.team_names = team_names;

        totalMatchCount = team_names.length * (team_names.length - 1);
        String[] teams = team_names;//fillList(5);
        String[] lastWeekFixture = makeFixture(teams);
        week = totalMatchCount / (teams.length / 2);
        weeklyMatchCount = totalMatchCount / week;


        weeksList = new ArrayList<String[]>();

        //getting weekly fixture
        for (int i = 0; i < week; i++) {

            String[] _temp = matchTeams(lastWeekFixture);

            lastWeekFixture = makeFixture(lastWeekFixture);
            weeksList.add(_temp);
        }

        String[] currentWeek = getWeek(0);
        for (int j = 0; j < currentWeek.length; j++) {
            System.out.println(currentWeek[j].toString());
        }
    }

    //demoe purposes
    public static String[] fillList(int count) {

        String array[] = new String[count];

        for (int i = 0; i < count; i++) {

            array[i] = "team" + (i + 1);

        }

        System.out.println("length: " + array.length);
        return array;
    }

    //getting specific week
    private static String[] getWeek(int weekIndex) {
        return weeksList.get(weekIndex);
    }

    //determine to how many elements in viewpager
    @Override
    public int getCount() {
        return week;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //inflating layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_pager, container, false);

        TextView tv_week, tv_matches;

        tv_week = view.findViewById(R.id.tv_week);
        tv_matches = view.findViewById(R.id.tv_matches);


        tv_week.setText("Week: " + (position + 1));

        String s = "";

        //getting weekly information based on viewpager position
        for (int i = 0; i < weeklyMatchCount; i++) {

            s += "\n" + getWeek(position)[i];
        }
        tv_matches.setText(s);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    //getting fixtured array and matched itself to ready to publish
    public static String[] matchTeams(String[] teamStrings) {

        int a = 0, b = teamStrings.length - 1, currentArrayIndex = 0;
        int maxLoop = teamStrings.length / 2;

        String[] _teamStrings = new String[teamStrings.length / 2];

        //matching it from the start to end
        for (int i = 0; i < maxLoop; i++) {
            _teamStrings[currentArrayIndex] = teamStrings[a] + " - " + teamStrings[b];
            currentArrayIndex++;
            a++;
            b--;

        }
        //its returning matched results
        return _teamStrings;

    }

    //its returning fixtured
    public static String[] makeFixture(String[] teamNameStrings) {
        // the algorithm start to calculate from 2 week

        int currentArrayIndex = 0, currentLine = 0, currentColumn = 1, i = 0, totalMatchCount = 0;

        String[] _teamNameStrings = teamNameStrings;

        // max column 2 because matches between 2 team
        // _shuffeledStrings lastly updated list
        // _teamNameStrings updated after odd statement as well
        // _teamMatrixStrings used to facilitate fixture chart


        // detecting odd number team and update current list like 1,2,3,4,5 -> 1,2,3,4,5,bye
        if (_teamNameStrings.length % 2 != 0) {
            // odd number of team
            String[] _oddArrayStrings = new String[teamNameStrings.length + 1];// create one big array from odd list
            // to circling
            for (int j = 0; j < _teamNameStrings.length; j++) {
                _oddArrayStrings[j] = _teamNameStrings[j];
                // transfer to a temporary list to avoid confusion
            }

            _oddArrayStrings[_oddArrayStrings.length - 1] = "bye";
            // odd array updated last number is null so we update it to last item with "bye"

            String[] _tempCircleArray = new String[_oddArrayStrings.length + 1];

            for (int j = 0; j < _tempCircleArray.length - 1; j++) {

                _tempCircleArray[j + 1] = _oddArrayStrings[j];

            }
            // update first item with oddArray's last item
            _oddArrayStrings[0] = _tempCircleArray[_tempCircleArray.length - 1];

            // then update like bye,1,2,3,4,5
            for (int j = 0; j < _tempCircleArray.length; j++) {
                try {
                    _oddArrayStrings[j + 1] = _tempCircleArray[j + 1];
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                }

            }

            _teamNameStrings = _oddArrayStrings;// publish to public use
        }

        // Algorithm starts

        String[][] _teamMatrixStrings = new String[_teamNameStrings.length / 2][2];
        String[] _shuffeledStrings = new String[_teamNameStrings.length];

        for (int a = 0; a < _teamNameStrings.length / 2; a++) {

            if (currentColumn == 1 && currentLine == 1) {
                // filling, list and matrix for special case
                _teamMatrixStrings[currentLine][currentColumn] = _teamNameStrings[_teamNameStrings.length - 1];
                _shuffeledStrings[1] = _teamNameStrings[_teamNameStrings.length - 1];
                currentLine++;
                i++;

            } else {
                // filling, list and matrix
                _teamMatrixStrings[currentLine][currentColumn] = _teamNameStrings[currentArrayIndex];
                _shuffeledStrings[i] = _teamNameStrings[currentArrayIndex];
                currentLine++;
                currentArrayIndex++;
                i++;
            }

        }

        currentColumn--; // switching to the first column
        currentLine--;
        for (int b = currentLine; b >= 0; b--) {
            // filling, list and matrix to backwards
            _teamMatrixStrings[currentLine][currentColumn] = _teamNameStrings[currentArrayIndex];
            _shuffeledStrings[i] = _teamNameStrings[currentArrayIndex];
            i++;
            currentLine--;
            currentArrayIndex++;
        }

        // for displaying
        for (int j = 0; j < _shuffeledStrings.length; j++) {
            // System.out.println("shuffeledString " + j + ": " + _shuffeledStrings[j]);
        }

        totalMatchCount = _shuffeledStrings.length * (_shuffeledStrings.length - 1);

        System.out.println("total match count: " + totalMatchCount);
        return _shuffeledStrings;
    }
}
