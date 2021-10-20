package com.example.kot_cashier

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_completesignup.*
import kotlinx.android.synthetic.main.activity_registerdata.*

class ShowFinishSignUpActivity: AppCompatActivity() {
    private var backPressedTime : Long = 0

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
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - backPressedTime >=2000 ) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.finishAffinity(this) //액티비티 종료
            System.exit(0);
        }
    }
}