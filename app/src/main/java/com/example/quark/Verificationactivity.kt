package com.example.quark

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.quark.databinding.ActivityMainBinding
import com.example.quark.databinding.ActivityReadDataBinding
import com.example.quark.databinding.ActivityVerificationactivityBinding
import com.example.quark.databinding.FragmentProfileBinding

class Verificationactivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        }

    }






