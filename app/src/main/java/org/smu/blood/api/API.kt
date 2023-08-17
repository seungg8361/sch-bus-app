package org.smu.blood.api

import android.telecom.Call
import com.example.login.User
import retrofit2.http.Body
import retrofit2.http.*

public interface API {
    //login
    @POST("android")
    fun getLoginResponse(@Body user: User): Call<String>
}