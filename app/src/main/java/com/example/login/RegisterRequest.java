package com.example.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://localhost:8080/signup";
    private Map<String, String> parameters;
    public RegisterRequest(String userId,String userPw, String userName, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("userPw", userPw);
        parameters.put("userName", userName);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
