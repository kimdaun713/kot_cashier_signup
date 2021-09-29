package com.example.kot_cashier

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class CertifyActivity : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certify)

        //하드웨어가 생체인식 기능을 지원하는지 체크
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                Log.e("MY_APP_TAG", "필요한 인텐트 작업 호출 - 생략")
            }
        }

        val authStatusTv = findViewById<TextView>(R.id.authStatusTv)
        val FingerButton = findViewById<Button>(R.id.FingerButton)

        //init biometric
        executor = ContextCompat.getMainExecutor(this)

        //인증 과정의 각 상황별로 다음단계 처리 해줌
        biometricPrompt = BiometricPrompt(this@CertifyActivity, executor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                //인증 에러 출력
                authStatusTv.text = "인증 에러: $errString"
                Toast.makeText(this@CertifyActivity, "인증 에러: $errString", Toast.LENGTH_LONG).show()

            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                //인증 성공
                authStatusTv.text = "인증 성공..."
                Toast.makeText(this@CertifyActivity, "인증 성공...", Toast.LENGTH_LONG).show()

                //인증 성공하였으니 다른 액티비티를 열어 보기로 하자.
                //val intent = Intent(this@MainActivity, NewActivity::class.java)
                //startActivity(intent)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                //인증 실패!
                authStatusTv.text = "인증 실패!!!"
                Toast.makeText(this@CertifyActivity, "인증 실패!!!", Toast.LENGTH_LONG).show()
            }
        })
        //인증 다이어로그 설정
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("서비스 사용을 위한 2차 지문 인증")
            .setSubtitle("지문을 이용 로그인 합니다.")
            .setNegativeButtonText("계정 패스워드를 이용")
            .build()

        //Click event
        FingerButton.setOnClickListener {
            //인증 대화상자 보이기
            biometricPrompt.authenticate(promptInfo)
        }

    }







}