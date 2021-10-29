package com.example.kot_cashier

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_realmain.*
import kotlinx.android.synthetic.main.activity_registerdata.*

class RealMainActivity : AppCompatActivity() {
    private var backPressedTime : Long = 0
    private var userEmail: String? = ""
    //데이타 베이스 접근위한 참조
    private lateinit var db: FirebaseFirestore
   // private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = getIntent()
        db=Firebase.firestore
        //login activity에서 로그인에 성공한 이메일 값 가져오기
        if(intent.hasExtra("usermail")){
            userEmail=intent.getStringExtra("usermail")
            Log.d("massage",userEmail.toString())
        }else{
            Log.d("message ","fail")
        }
        //databaseRef= Firebase.database.reference

        //데이터 받아오기(유저이메일 도큐먼트 기준, 유저 네임의 내용을 출력)
        val docRef = db.collection("users").document(userEmail.toString())
            docRef.get().addOnSuccessListener { document->
                if(document!=null) {
                    userEmail = document.getString("userName")
                    //여기서부터 계속 document.getString해서 textview에 setText하면 될 듯
                    Log.d("massage",userEmail.toString())
                    mainNameText.setText(userEmail)
                }
            }


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