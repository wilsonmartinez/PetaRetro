package com.wmartinez.devep.petaretro.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wmartinez.devep.petaretro.PerfilActivity;
import com.wmartinez.devep.petaretro.restApi.EndpointsApi;
import com.wmartinez.devep.petaretro.restApi.adapter.RestApiAdapter;
import com.wmartinez.devep.petaretro.restApi.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WilsonMartinez on 8/1/2016.
 */
public class RespondLike extends BroadcastReceiver {

    private static final String PETMASTER2016_ID = "3595518142";
    private static final String USER_RELATIONSHIP = "petmaster2016";
    private static String relationship = "follow";

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY1 = "LIKE_FOLLOWER";
        String ACCION_KEY2 = "MY_PROFILE";
        String ACCION_KEY3 = "UN_FOLLOW_USER";
        String ACCION_KEY4 = "USER_PROFILE";

        String accion = intent.getAction();

        if (ACCION_KEY1.equals(accion)) {

        }
        if (ACCION_KEY2.equals(accion)) {
            String userAccount = "petamaster";
            SharedPreferences userAccountPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE);
            SharedPreferences.Editor userAccountEdit = userAccountPreferences.edit();
            userAccountEdit.putString("EditAccount", userAccount);
            userAccountEdit.commit();
            Intent intentProfile = new Intent(context.getApplicationContext(), PerfilActivity.class);
            intentProfile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentProfile);
        }
        if (ACCION_KEY3.equals(accion)) {
            if (relationship.contentEquals("follow")) {
                relationship = "unfollow";
            } else {
                relationship = "follow";
            }
            setRelationship(relationship);
            Toast.makeText(context, "Diste " + String.valueOf(relationship) + " a " + String.valueOf(USER_RELATIONSHIP),
                    Toast.LENGTH_SHORT).show();
        }
        if (ACCION_KEY4.equals(accion)) {
            String userAccount = "petmaster2016";
            SharedPreferences userAccountPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE);
            SharedPreferences.Editor userAccountEdit = userAccountPreferences.edit();
            userAccountEdit.putString("EditAccount", userAccount);
            userAccountEdit.commit();
            Intent intentProfile = new Intent(context.getApplicationContext(), PerfilActivity.class);
            intentProfile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentProfile);
        }
    }

    private void setRelationship(final String relationship) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonRelationship = restApiAdapter.construyeGsonDeserializadorFollowers();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonRelationship);
        Call<UserResponse> userResponseCall = endpointsApi.setFollowUnfollow(PETMASTER2016_ID, relationship);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
