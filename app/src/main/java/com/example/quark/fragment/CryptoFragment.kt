package com.example.quark.fragment

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.quark.R
import com.example.quark.ReadData
import com.example.quark.databinding.FragmentCryptoBinding
import com.example.quark.databinding.FragmentMessageBinding
import com.example.quark.quarkpayment


class CryptoFragment : Fragment() {


    private lateinit var binding: FragmentCryptoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = FragmentCryptoBinding.inflate(layoutInflater)

        binding.adminsaveDataquark.setOnClickListener {
            startActivity(Intent(requireContext(), quarkpayment::class.java))
        }


        return binding.root
    }


}