package com.wmartinez.devep.petaretro.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wmartinez.devep.petaretro.pojo.Mascota;
import com.wmartinez.devep.petaretro.restApi.JsonKeys;
import com.wmartinez.devep.petaretro.restApi.model.FollowerMediaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/27/2016.
 */
public class FollowersMediaDeserializador implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowerMediaResponse followerMediaResponse = gson.fromJson(json, FollowerMediaResponse.class);
        JsonArray followersResponseData     = json.getAsJsonObject().getAsJsonArray(JsonKeys.DATA_RESPONSE_ARRAY);

        followerMediaResponse.setFollowersMedia(deserializadorFollowersMediaDeJson(followersResponseData));
        return followerMediaResponse;
    }

    private ArrayList<Mascota> deserializadorFollowersMediaDeJson(JsonArray followersResponseData) {
        ArrayList<Mascota> followers    = new ArrayList<>();
        for (int i = 0; i < followersResponseData.size(); i++) {
            JsonObject followersResponseDataObject = followersResponseData.get(i).getAsJsonObject();

            JsonObject userJson     = followersResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson            = followersResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likeJson = followersResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes           = likeJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota followerActual  = new Mascota();
            followerActual.setId(id);
            followerActual.setNombreCompleto(nombreCompleto);
            followerActual.setUrlFoto(urlFoto);
            followerActual.setLikes(likes);

            followers.add(followerActual);
        }
        return followers;
    }
}
