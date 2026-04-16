package com.example.sokogarden

import android.R.attr.name
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the views by use of their IDs
        val txtName = findViewById<TextView>(R.id.txtProductName)
        val txtCost = findViewById<TextView>(R.id.txtProductCost)
        val txtDescription = findViewById<TextView>(R.id.txtProductDescription)
        val imgProduct = findViewById<ImageView>(R.id.imgProduct)

//        Retrieve the data from the previous activity(MainActivity)
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val description = intent.getStringExtra("product_description")
        val product_photo = intent.getStringExtra("product_photo")

//        Update the textview with the data first from the previous activity (MainActivity)
        txtName.text = name
        txtCost.text = "Ksh $cost"
        txtDescription.text = description

//        Specify the Image url
        val imageUrl = "https://kbenkamotho.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

//        Find the editText and pay now button using their IDs
        val phone = findViewById<EditText>(R.id.phone)
        val btnPay = findViewById<Button>(R.id.pay)

//        Set onclick listener
        btnPay.setOnClickListener {
//            Specify the API endpoint for mpesa
            val api = "https://kbenkamotho.alwaysdata.net/api/mpesa_payment"

            val data = RequestParams()

//            Insert data into the request from
            data.put("amount", cost)
            data.put("phone", phone.text.toString().trim())

//            Import the helper
            val helper = ApiHelper(applicationContext)

//            Access the post function inside of the helper class
            helper.post(api, data)

//            Clear your phone number from the EditText
            phone.text.clear()
        }

    }
}