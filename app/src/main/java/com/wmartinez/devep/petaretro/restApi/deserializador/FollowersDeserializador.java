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
import com.wmartinez.devep.petaretro.restApi.model.FollowersResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by WilsonMartinez on 6/24/2016.
 */
public class FollowersDeserializador implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowersResponse followersResponse = gson.fromJson(json, FollowersResponse.class);
        JsonArray followersResponseData     = json.getAsJsonObject().getAsJsonArray(JsonKeys.DATA_RESPONSE_ARRAY);

        followersResponse.setFollowers(deserializadorFollowersDeJson(followersResponseData));
        return followersResponse;
    }

    private ArrayList<Mascota> deserializadorFollowersDeJson(JsonArray followersResponseData) {
        ArrayList<Mascota> followers    = new ArrayList<>();
        for (int i = 0; i < followersResponseData.size(); i++) {
            JsonObject  followersResponseDataObject = followersResponseData.get(i).getAsJsonObject();

            JsonObject userInfo         = followersResponseDataObject.getAsJsonObject();
            String userNameJson         = userInfo.get(JsonKeys.USER_NAME).getAsString();
            String userIdJson           = userInfo.get(JsonKeys.USER_ID).getAsString();
            String fullNameJson         = userInfo.get(JsonKeys.USER_FULLNAME).getAsString();
            String profilePictureUrl    = userInfo.get(JsonKeys.PROFILE_PICTURE).getAsString();

            Mascota followerActual  = new Mascota();
            followerActual.setId(userIdJson);
            followerActual.setNombreCompleto(fullNameJson);
            followerActual.setUserName(userNameJson);
            followerActual.setUrlFoto(profilePictureUrl);

            followers.add(followerActual);
        }
        return followers;
    }
}
