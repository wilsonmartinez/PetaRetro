package com.wmartinez.devep.petaretro.restApi;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public class ConstantesRestApi {
    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3435346040.e050623.cd4afd9336f44c4ca540a3c96a4ba0c5";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN

    //https://api.instagram.com/v1/users/self/followed-by?access_token=ACCESS-TOKEN
    public static final String KEY_GET_FOLLOWED_BY_USER = "users/self/followed-by";
    public static final String URL_GET_FOLLOWED_BY_USER = KEY_GET_FOLLOWED_BY_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKE
    public static final String KEY_USER_ID = "{user-id}";
    public static final String KEY_GET_FOLLOWED_MEDIA_BY_USER = "/media/recent/";
    public static final String KEY_GET_FOLLOWER_BY_USER = "users/";
    public static final String URL_GET_FOLLOWER_MEDIA_BY_USER = KEY_GET_FOLLOWER_BY_USER + KEY_USER_ID + KEY_GET_FOLLOWED_MEDIA_BY_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
