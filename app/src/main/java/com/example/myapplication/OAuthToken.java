package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OAuthToken {
    @SerializedName("access_token")
    @Expose
    private String access_token;
    @SerializedName("expires_in")
    @Expose
    private Long expiresIn;
    @SerializedName("token_type")
    @Expose
    private String token_type;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("party_id")
    @Expose
    private String partyId;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;

    }
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) { this.partyId = partyId;}

    }
