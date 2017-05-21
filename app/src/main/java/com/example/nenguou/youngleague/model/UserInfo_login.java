package com.example.nenguou.youngleague.model;

/**
 * Created by Nenguou on 2017/5/14.
 */

public class UserInfo_login {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int user_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserInfo_login{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", scope='" + scope + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
/*  "access_token": "34d5c793-7e13-45ec-9bbf-c3fb101df8dd",
          "token_type": "bearer",
          "refresh_token": "94bbeb0c-d2ca-4c3b-bf6c-56e64c44ac4a",
          "expires_in": 16273,
          "scope": "read write",
          "user_id": "9884605"*/
}
