package com.example.quark.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quark.*
import com.example.quark.databinding.FragmentMessageBinding
import com.example.quark.databinding.FragmentProfileBinding


class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding


    override fun onCreateView(



        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(layoutInflater)


        binding.adminsaveData.setOnClickListener {
            startActivity(Intent(requireContext(), ReadData::class.java))
        }

        binding.usersaveData.setOnClickListener {
            startActivity(Intent(requireContext(),LoadData::class.java))
        }

        binding.userverifyData.setOnClickListener {
            startActivity(Intent(requireContext(),verifydocs::class.java))
        }

        return binding.root
    }

}