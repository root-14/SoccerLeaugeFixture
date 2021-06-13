package com.awarec.soccerleaugefixture.interfaces;

import com.awarec.soccerleaugefixture.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("teams")
    Call<List<Model>> getTeams();

}
