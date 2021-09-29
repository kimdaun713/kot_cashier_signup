package com.example.kot_cashier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.moveSignUpButton
import kotlinx.android.synthetic.main.activity_registerdata.*

class RegisterMemberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerdata)

        completeSignupButton.setOnClickListener({
            val intent = Intent(this, ShowFinishSignUpActivity::class.java)
            startActivity(intent)
        })
    }

    // 뒤로가기 버튼 막기(인증 끝났으니깐)
    override fun onBackPressed() {
    //super.onBackPressed();
    }
}