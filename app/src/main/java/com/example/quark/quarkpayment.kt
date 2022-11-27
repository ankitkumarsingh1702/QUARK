package com.example.quark

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class quarkpayment : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quarkpayment)

        val textPrivacy : TextView = findViewById(R.id.adminsaveDataquark)

        textPrivacy.movementMethod = LinkMovementMethod.getInstance()

    }
}