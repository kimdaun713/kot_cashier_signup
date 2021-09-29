package com.example.kot_cashier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registerdata.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        NextSignButton.setOnClickListener({
            val intent = Intent(this, RegisterMemberActivity::class.java)
            startActivity(intent)
        })
    }
}