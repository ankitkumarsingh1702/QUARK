package com.example.quark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quark.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {
    private lateinit var binding: ActivityReadDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readData.setOnClickListener {
            val Userwalletadress:String= binding.Userkey.text.toString()
            if (Userwalletadress.isNotEmpty()){
                readData(Userwalletadress)
            }else{
                Toast.makeText( this,"Please enter the Key", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(userwalletadress: String) {
        database= FirebaseDatabase.getInstance().getReference("Users")
        database.child(userwalletadress).get().addOnSuccessListener {
            if (it.exists()){
                val firstName = it.child("name").value
                val Address = it.child("postaladdress").value
                val gender = it.child("gender").value
                val walletaddress = it.child("walletaddress").value
                Toast.makeText( this,"Dont Share this with anyone", Toast.LENGTH_SHORT).show()

                binding.Userkey.text?.clear()
                binding.cardUsernameOn.text = firstName.toString()
                binding.cardUseradressOn.text = Address.toString()
                binding.cardUsergenderOn.text = gender.toString()
                binding.cardwalletaddresson.text = walletaddress.toString()

                binding.cardlayout.visibility = View.VISIBLE

            }else{
                Toast.makeText( this,"User Doesnot exsist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText( this,"You are not a authorized admin", Toast.LENGTH_SHORT).show()
        }

    }
}