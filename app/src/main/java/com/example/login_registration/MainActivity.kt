package com.example.login_registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var EdtEmail: EditText
    private lateinit var EdtPassword: EditText
    lateinit var Edtconfirm: EditText
    private lateinit var Btnlogn: Button
    lateinit var Txtsign: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EdtEmail = findViewById(R.id.Email)
        EdtPassword = findViewById(R.id.Password)
        Edtconfirm = findViewById(R.id.passwordconfirm)
        Btnlogn = findViewById(R.id.logn)
        Txtsign = findViewById(R.id.redirect)
        auth = Firebase.auth

        Btnlogn.setOnClickListener {
            SignupUser()
        }
        Txtsign.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }

    private fun SignupUser() {
        val Email = EdtEmail.text.toString()
        val password = EdtPassword.text.toString()
        val confirmpass = Edtconfirm.text.toString()
        if (Email.isEmpty() or password.isEmpty() or confirmpass.isEmpty()) {

            Toast.makeText(this, "Please input the information.", Toast.LENGTH_LONG).show()
            return
        } else if (password != confirmpass) {
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_LONG).show()
        }
        auth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Sign up successful", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Failed to sign in", Toast.LENGTH_LONG).show()
            }

        }


    }

}