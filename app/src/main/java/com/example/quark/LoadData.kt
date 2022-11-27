package com.example.quark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quark.databinding.ActivityLoadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoadData : AppCompatActivity() {
    private lateinit var  binding: ActivityLoadDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveData.setOnClickListener {
            validateData()
            val name = binding.UserName.text.toString()
            val age = binding.UserAge.text.toString()
            val walletaddress = binding.Userwalletadress.text.toString()
            val postaladdress = binding.Userpostadress.text.toString()
            val gender = binding.UserGender.text.toString()
            val pincode = binding.Userpincode.text.toString()


            database= FirebaseDatabase.getInstance().getReference("Users")
            val user = UserNew(name, age, gender, walletaddress, postaladdress, pincode)
            database.child(walletaddress).setValue(user).addOnSuccessListener {
                binding.UserName.text?.clear()
                binding.UserAge.text?.clear()
                binding.Userwalletadress.text?.clear()
                binding.Userpostadress.text?.clear()
                binding.Userpostadress.text?.clear()
                binding.UserGender.text?.clear()
                binding.Userpincode.text?.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateData() {
        if (binding.UserName.text.toString().isEmpty()
            || binding.UserAge.text.toString().isEmpty()
            || binding.UserGender.text.toString().isEmpty()
            || binding.Userwalletadress.text.toString().isEmpty()
            || binding.Userpostadress.text.toString().isEmpty()
            || binding.Userpincode.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
        }else if (!binding.termsCondition.isChecked){
            Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show()
        }
    }
}