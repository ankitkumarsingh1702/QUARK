package com.example.quark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quark.databinding.ActivityLoadDataBinding
import com.example.quark.databinding.ActivityVerifyLoadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class VerifyLoadData : AppCompatActivity() {
    private lateinit var  binding: ActivityVerifyLoadDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyLoadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveData.setOnClickListener {
            validateData()
            val name = binding.UserName.text.toString()
            val walletaddress = binding.Userwalletadress.text.toString()
            val postaladdress = binding.Userpostadress.text.toString()
            val docslink = binding.Userpincode.text.toString()


            database= FirebaseDatabase.getInstance().getReference("verifyusers")
            val user = userverify(name, walletaddress, postaladdress, docslink)
            database.child(name).setValue(user).addOnSuccessListener {
                binding.UserName.text?.clear()
                binding.Userwalletadress.text?.clear()
                binding.Userpostadress.text?.clear()
                binding.Userpincode.text?.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateData() {
        if (binding.UserName.text.toString().isEmpty()

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