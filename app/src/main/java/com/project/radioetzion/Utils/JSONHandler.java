package com.project.radioetzion.Utils;

import com.project.radioetzion.Model.JSONData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONHandler {

    @GET("broadcast/getVodList/0/100")
    Call<List<JSONData>> getJson();
}