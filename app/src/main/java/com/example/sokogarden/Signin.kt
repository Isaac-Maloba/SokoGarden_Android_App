package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//    Find the two edit texts, a button and the text view by use of their IDs
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signInButton = findViewById<Button>(R.id.signinBtn)
        val signupTextView = findViewById<TextView>(R.id.signuptxt)

//      On the text view, set an onclick listener such that when clicked, one is redirected to the sign up activity
        signupTextView.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

//        On click with the button Sign In, we need to interact with our API endpoint as we pass the email and password
        signInButton.setOnClickListener {
//            Specify the API endpoint
            val api = "https://maloba.alwaysdata.net/android/api/signin"

//            Create a RequestParams that will enable you to hold the data in form of a bundle/package
            val data = RequestParams()

//            Append/add/attach the email and the password
            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

//            Import the API helper
            val helper = ApiHelper(applicationContext)

//            By use of the function post_login inside of the helper class, post your data
            helper.post_login(api, data)
        }
    }
}