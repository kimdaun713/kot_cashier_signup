package com.example.kot_cashier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_completesignup.*
import kotlinx.android.synthetic.main.activity_registerdata.*

class ShowFinishSignUpActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completesignup)

        moveMainButton.setOnClickListener({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        })
    }

    // 뒤로가기 버튼 막기(회원 가입 끝났으니깐)
    override fun onBackPressed() {
        //super.onBackPressed();
    }
}