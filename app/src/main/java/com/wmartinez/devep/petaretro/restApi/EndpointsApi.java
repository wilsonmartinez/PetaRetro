package com.wmartinez.devep.petaretro.restApi;

import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;
import com.wmartinez.devep.petaretro.restApi.model.FollowersResponse;
import com.wmartinez.devep.petaretro.restApi.model.MascotaResponse;
import com.wmartinez.devep.petaretro.restApi.model.RegistroResponse;
import com.wmartinez.devep.petaretro.restApi.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UserResponse> registrarTokenID(@Field("token") String token);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_USER_REGISTER)
    Call<RegistroResponse> registrarUsuario(@Field("id_dispositivo") String deviceId, @Field("id_user_instagram") String userId);

    @GET(ConstantesRestApi.KEY_LIKE_USER)
    Call<RegistroResponse> likeUser(@Path("id") String id, @Path("user") String user);

}
