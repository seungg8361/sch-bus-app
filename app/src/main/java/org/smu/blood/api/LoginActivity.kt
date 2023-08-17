package org.smu.blood.api

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import retrofit2.Call
import com.example.login.User
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {

    var id: String = ""
    var pw: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.loginButton)
        val userId = findViewById<EditText>(R.id.idText)
        val userPw = findViewById<EditText>(R.id.passwordText)

        button.setOnClickListener{
            id = userId.text.toString()
            pw = userPw.text.toString()
            val user = User()
            user.id = userId.text.toString()
            user.password = userPw.text.toString()

            Log.d("BUTTON CLICKED", "id: "+ user.id + ", pw: "+ user.password)
            Login(user)
        }
    }
    fun Login(user: User){
        val call = RetrofitBuilder.api.getLoginResponse(user)
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ){
                if (response.isSuccessful()){
                    Log.d("RESPONSE: ", response.body().toString())
                }else{
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable){
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}