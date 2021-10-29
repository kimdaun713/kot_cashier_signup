package com.example.kot_cashier

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {
    var fbAuth : FirebaseAuth? =null
    var fbFirestore : FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fbAuth = FirebaseAuth.getInstance()
        auth= Firebase.auth
        //로그인 버튼 클릭시
        LoginButton.setOnClickListener({
            var email=LoginIDText.text.toString()
            auth!!.signInWithEmailAndPassword(email, LoginPasswordText.text.toString())
                .addOnCompleteListener(this){
                    if(it.isSuccessful){ //성공시 메인 화면(서비스) 진입
                        val intent = Intent(this, RealMainActivity::class.java)
                        intent.putExtra("usermail",email) //메인화면에 유저 메일 정보
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"로그인 실패. 로그인 정보를 재확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }


        })

    }
}