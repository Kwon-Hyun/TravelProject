package com.example.travelproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelproject.databinding.ActivitySignupBinding
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth : FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인증 초기화
        mAuth  = Firebase.auth

        binding.btnSignup.setOnClickListener {
            val email = binding.txtEmail.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()

            signUp(email, password)
        }
    }
    // 회원가입~!
    private fun signUp(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 성공하셨습니다~", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "회원가입 실패하셨습니다....", Toast.LENGTH_SHORT).show()
                    Log.d("SignUp", "Error: ${task.exception}")
                }
            }
    }
}
