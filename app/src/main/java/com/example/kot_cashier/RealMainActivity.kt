package com.example.kot_cashier

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_realmain.*

class RealMainActivity : AppCompatActivity() {
    private var backPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realmain)

        certifyButton.setOnClickListener({
            val intent = Intent(this, CertifyActivity::class.java)
            startActivity(intent)
        })

        moveMypageButton.setOnClickListener({
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        })
    }

    // 로그인 풀리지 않게 뒤로가기 막기
    override fun onBackPressed() {
        //super.onBackPressed();
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - backPressedTime >=2000 ) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.finishAffinity(this) //액티비티 종료
            System.exit(0);
        }
    }
}