package com.awarec.soccerleaugefixture.model;


import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("id")
    int team_id;

    @SerializedName("name")
    String team_name;

    @SerializedName("img_url")
    String image_url;

    public int getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getImage_url() {
        return image_url;
    }


}
