package com.example.quark

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color.BLACK
import android.graphics.Color.BLUE
import android.hardware.camera2.params.RggbChannelVector.BLUE
import android.hardware.camera2.params.RggbChannelVector.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView
import com.google.type.Color

class verifydocs : AppCompatActivity() {

    private lateinit var button_forward: TextView
    private lateinit var button_forward_1: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifydocs)

        val textPrivacy :  TextView = findViewById(R.id.adminsaveData)

        textPrivacy.movementMethod = LinkMovementMethod.getInstance()

        button_forward = findViewById(R.id.usersaveDatanew)

        button_forward.setOnClickListener {
            val intent = Intent(this, VerifyReadData ::class.java)

            startActivity(intent)
        }

        button_forward_1 = findViewById(R.id.UsersaveData)

        button_forward_1.setOnClickListener {
            val intent = Intent(this, VerifyLoadData ::class.java)

            startActivity(intent)
        }



    }
}