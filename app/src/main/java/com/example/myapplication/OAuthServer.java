package com.example.myapplication;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;

public class OAuthServer {
    private static final String siteURL = "https://www.dbs.com/sandbox/api/sg/v1";

    public static OAuthServerIntface oAuthServerIntface = null;

    public static OAuthServerIntface getoAuthServerIntface(){

        if(oAuthServerIntface == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(siteURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            oAuthServerIntface = retrofit.create(OAuthServerIntface.class);

        }
        return oAuthServerIntface;
    }

    public interface OAuthServerIntface {

        // @Headers("Accept: application/json")

        /**
         * The call to request a token
         */

        @POST("oauth/tokens")
        @FormUrlEncoded
        Call<OAuthToken> getAccessToken(
                @Field("Authorization") String Authorization,
                @Field("code") String code,
                @Field("redirect_uri") String redirect_uri
                //@Field("grant_type") String grant_type

        );

    }


}
