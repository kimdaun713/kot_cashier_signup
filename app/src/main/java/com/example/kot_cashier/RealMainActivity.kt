package com.example.kot_cashier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class RealMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realmain)


    }

    // 로그인 풀리지 않게 뒤로가기 막기
    override fun onBackPressed() {
        //super.onBackPressed();
    }
}