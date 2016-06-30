package com.wmartinez.devep.petaretro.restApi;

import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;
import com.wmartinez.devep.petaretro.restApi.model.FollowersResponse;
import com.wmartinez.devep.petaretro.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public interface EndpointsApi {
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_FOLLOWED_BY_USER)
    Call<FollowersResponse> getFollowedBy();

    @GET(ConstantesRestApi.KEY_GET_SEARCH + ConstantesRestApi.URL_SEARCH_USERS)
    Call<FollowersResponse> getUsersSearch(@Query("q") String q);

    @GET(ConstantesRestApi.URL_GET_FOLLOWER_MEDIA_BY_USER)
    Call<FollowerMediaResponse> getFollowerMediaBy(@Path("user-id") String userId);
}
