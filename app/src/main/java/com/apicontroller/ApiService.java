package com.apicontroller;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


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

