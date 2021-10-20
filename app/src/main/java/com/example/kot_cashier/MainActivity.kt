package com.example.kot_cashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var backPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveSignUpButton.setOnClickListener({
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        })

        moveLoginButton.setOnClickListener({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onBackPressed() {
        //super.onBackPressed();
        if(System.currentTimeMillis() - backPressedTime >=2000 ) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.finishAffinity(this) //액티비티 종료
            System.exit(0);
        }
    }
}