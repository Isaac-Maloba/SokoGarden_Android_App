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

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find all the views by use of their IDs
        var username = findViewById<EditText>(R.id.username)
        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)
        var phone = findViewById<EditText>(R.id.phone)
        val signupButton = findViewById<Button>(R.id.signupBtn)
        val signinText = findViewById<TextView>(R.id.signintxt)

//        Below, when a person clicks on the textview, they are redirected to the signin page
        signinText.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

//        On click of the signup button, we want to register a person
        signupButton.setOnClickListener {
//            Specify the API endpoint
            val api = "https://maloba.alwaysdata.net/android/api/signup"

//             Create a RequestParams, where we are going to hold all the data
            val data = RequestParams()

//            Add the user, email, password and phone on the data
            data.put("username", username.text.toString().trim())
            data.put("email", email.text.toString().trim())
            data.put("password", password.text.toString().trim())
            data.put("phone", phone.text.toString().trim())

//            Import the API helper
            val helper = ApiHelper(applicationContext)

//            Inside of the helper class, access the function post
            helper.post(api, data)

//            Clear the fields
            username.text.clear()
            email.text.clear()
            password.text.clear()
            phone.text.clear()

        }
    }
}