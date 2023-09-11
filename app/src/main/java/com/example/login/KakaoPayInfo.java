package com.example.login;

import com.google.gson.annotations.SerializedName;

public class KakaoPayInfo {
    @SerializedName("tid")
    private String tid;

    @SerializedName("next_redirect_app_url")
    private String nextRedirectAppUrl;

    @SerializedName("next_redirect_mobile_url")
    private String nextRedirectMobileUrl;

    @SerializedName("next_redirect_pc_url")
    private String nextRedirectPcUrl;

    @SerializedName("android_app_scheme")
    private String androidAppScheme;

    @SerializedName("ios_app_scheme")
    private String iosAppScheme;

    @SerializedName("created_at")
    private String createdAt;

    // Getter 메서드들
    public String getTid() {
        return tid;
    }

    public String getNextRedirectAppUrl() {
        return nextRedirectAppUrl;
    }

    public String getNextRedirectMobileUrl() {
        return nextRedirectMobileUrl;
    }

    public String getNextRedirectPcUrl() {
        return nextRedirectPcUrl;
    }

    public String getAndroidAppScheme() {
        return androidAppScheme;
    }

    public String getIosAppScheme() {
        return iosAppScheme;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}