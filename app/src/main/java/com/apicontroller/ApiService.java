package com.apicontroller;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
        @FormUrlEncoded
        @POST("oauth2/default/token")
        Call<AuthResponse> authenticateUser(@FieldMap Map<String, Object> map);

}

