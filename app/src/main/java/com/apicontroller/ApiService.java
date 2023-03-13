package com.apicontroller;

import okhttp3.ResponseBody;
import retrofit.http.Body;
import retrofit2.Call;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface ApiService {
        @FormUrlEncoded
        @POST("oauth2/default/token")
        Call<AuthResponse> authenticateUser(
                @Field("grant_type") String grantType,
                @Field("client_id") String clientId,
                @Field("scope") String scope,
                @Field("user_role") String userRole,
                @Field("username") String username,
                @Field("password") String password
        );

}

