package com.wmartinez.devep.petaretro.restApi;

/**
 * Created by WilsonMartinez on 6/23/2016.
 */
public final class ConstantesRestApi {
    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3435346040.e050623.cd4afd9336f44c4ca540a3c96a4ba0c5";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_SCOPE = "&scope=public_content";
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
    //https://api.instagram.com/v1/media/{media-id}/likes
    public static final String KEY_SET_LIKE = "media/{media-id}/likes";

    //https://api.instagram.com/v1/users/search?access_token=ACCESS-TOKE&scope=public_content
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    public static final String KEY_GET_SEARCH = "users/search";
    public static final String URL_SEARCH_USERS = KEY_ACCESS_TOKEN + ACCESS_TOKEN + KEY_SCOPE;

    /**
     * Constantes para Heroku
     */
    /*https://aqueous-waters-70466.herokuapp.com/*/
    public static final String ROOT_URL_HEROKU = "https://aqueous-waters-70466.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "android/token-device/";
    public static final String KEY_POST_USER_REGISTER = "android/registrar-usuario/";
    public static final String KEY_POST_USER_SET_LIKE = "android/pet-like/";
    public static final String KEY_LIKE_USER = "android/like-user/{id}/{user}/";
    public static final String KEY_GET_LIKE_PET = "pet-like/{id_foto_instagram}/{id_usuario_instagram}/{id_dispositivo}";

}
