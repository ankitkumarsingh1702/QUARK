package com.example.quark.auth

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.quark.MainActivity
import com.example.quark.R
import com.example.quark.databinding.ActivityRegisterBinding
import com.example.quark.model.UserModel
import com.example.quark.utils.Config
import com.example.quark.utils.Config.hideDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private var imageUri : Uri? = null

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it
        binding.UserImage.setImageURI(imageUri)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.UserImage.setOnClickListener {
            selectImage.launch("image/*")
        }

        binding.saveData.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        if (binding.UserName.text.toString().isEmpty()
            || binding.UserEmail.text.toString().isEmpty()
            || binding.UserAddress.text.toString().isEmpty()
            || imageUri == null                ) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
        }else if (!binding.termsCondition.isChecked){
            Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show()
        }else{
            uploadImage()
        }
    }

    private fun uploadImage() {
        Config.showDialog(this)

        val storageRef = FirebaseStorage.getInstance().getReference("profile")
            .child(FirebaseAuth.getInstance().currentUser!!.uid).child("profile.jpg")
            .child("profile.jpg")


        storageRef.putFile(imageUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    storeData(it)
                }.addOnFailureListener {
                    hideDialog()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                hideDialog()
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun storeData(imageUrl : Uri?) {

        val data = UserModel(
            name = binding.UserName.text.toString(),
            image = imageUrl.toString(),
            email = binding.UserEmail.text.toString(),
            address = binding.UserAddress.text.toString(),
            number = FirebaseAuth.getInstance().currentUser!!.phoneNumber,
        )

        FirebaseDatabase.getInstance().getReference("users")
            .child(FirebaseAuth.getInstance().currentUser!!.phoneNumber!!)
            .setValue(data).addOnCompleteListener {
                hideDialog()
                if (it.isSuccessful){

                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                    Toast.makeText(this, "User register sucessfull", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,it.exception!!.message, Toast.LENGTH_SHORT).show()

                }
            }

    }
}