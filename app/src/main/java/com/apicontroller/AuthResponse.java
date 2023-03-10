package com.apicontroller;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("refresh_token")
	private String refreshToken;


}
