package com.example.kot_cashier

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.moveSignUpButton
import kotlinx.android.synthetic.main.activity_registerdata.*

private var backPressedTime : Long = 0
data class userInfo(

    var userName: String? = "",
    var userPwd: String?="",
    var userUID: String?="",
    var userEmail: String?=""
)

class RegisterMemberActivity : AppCompatActivity() {
    var fbAuth : FirebaseAuth? =null
    var fbFirestore : FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth  //사용자

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerdata)
        fbAuth = FirebaseAuth.getInstance() //파이어베이스 인스턴스 생성
        fbFirestore = FirebaseFirestore.getInstance()
        var uInfo = userInfo()
        //이메일칸에 받은 데이터 미리 기록
        if(intent.hasExtra("email")){
            uInfo.userEmail=intent.getStringExtra("email")
            editTextEmail.text=uInfo.userEmail
        }


        completeSignupButton.setOnClickListener({


            //유저 데이터 구조에 정보 저장
            uInfo.userName=editTextName.text.toString()
            uInfo.userUID=editTextPersonNum1.text.toString() + editTextPersonNum2.text.toString()
            uInfo.userPwd=editTextPassword.text.toString()


            if(!uInfo.userName.isNullOrEmpty() && !editTextPersonNum1.text.toString().isNullOrEmpty() && !editTextPersonNum2.text.toString().isNullOrEmpty() && !uInfo.userPwd.isNullOrEmpty() && !uInfo.userEmail.isNullOrEmpty()){


                auth = Firebase.auth
                //이메일 비밀번호 활용하여 사용자 정보 저장
                auth.createUserWithEmailAndPassword(uInfo.userEmail.toString(), uInfo.userPwd.toString())
                    .addOnCompleteListener(this){task->
                      if(!task.isSuccessful){
                          Toast.makeText(this@RegisterMemberActivity, "중복된 이메일입니다.",Toast.LENGTH_SHORT).show()

                          Log.d(TAG, "createUserWithEmail:fail")

                      }else{
                          Log.d(TAG, "createUserWithEmail:success")
                          fbFirestore?.collection("users")?.document(uInfo.userEmail.toString())?.set(uInfo)
                          val intent = Intent(this, ShowFinishSignUpActivity::class.java)
                          startActivity(intent)
                      }
                    }
                //create할때 같이 저장해도되겠는데?해서 아래 지움
               //?.collection("users")?.document(auth.uid.toString())?.set(uInfo)



            }
            else{
                Toast.makeText(this@RegisterMemberActivity,"회원 정보를 제대로 입력하세요!!",Toast.LENGTH_SHORT).show()
            }

        })
    }

    // 뒤로가기 버튼 막기(인증 끝났으니깐)
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