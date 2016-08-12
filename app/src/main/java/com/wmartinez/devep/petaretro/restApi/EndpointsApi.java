package com.wmartinez.devep.petaretro.restApi;

import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;
import com.wmartinez.devep.petaretro.restApi.model.FollowersResponse;
import com.wmartinez.devep.petaretro.restApi.model.LikeNotificationResponse;
import com.wmartinez.devep.petaretro.restApi.model.LikesResponse;
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
    Call<RegistroResponse> registrarUsuario(@Field("id_dispositivo") String deviceId,
                                            @Field("id_user_instagram") String userId);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_USER_SET_LIKE)
    Call<LikeNotificationResponse> regsitrarLikes(@Field("id_foto_instagram") String id_foto_instagram,
                                                  @Field("id_usuario_instagram") String id_usuario_instagram,
                                                  @Field("id_dispositivo") String id_dispositivo,
                                                  @Field("likes") String likes);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_SET_LIKE)
    Call<LikesResponse> setLikes(@Path("media-id") String id_foto_instagram,
                                 @Field("access_token") String access_token);

    @FormUrlEncoded
    @POST(ConstantesRestApi.URL_RELATIONSHIP)
    Call<UserResponse> setFollowUnfollow(@Path("user-id") String id_usuario,
                                         @Field("action") String relationship);

    @GET(ConstantesRestApi.KEY_LIKE_USER)
    Call<RegistroResponse> likeUser(@Path("id") String id, @Path("user") String user);

    @GET(ConstantesRestApi.KEY_GET_LIKE_PET)
    Call<LikeNotificationResponse> sendLikeNotification(
            @Path("id_foto_instagram") String id_foto_instagram,
            @Path("id_usuario_instagram") String id_usuario_instagram,
            @Path("id_dispositivo") String id_dispositivo
    );

    @GET(ConstantesRestApi.KEY_NOTIFICATION_USER)
    Call<LikeNotificationResponse> sendNotificationUser(
            @Path("id") String id,
            @Path("id_usuario_instagram") String id_usuario_instagram
    );

}
