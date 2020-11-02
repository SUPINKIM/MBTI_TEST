package com.donggi.mbtitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class LoadingPage extends AppCompatActivity {

    String receiveStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //전체 화면 만들기
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loading_page);



        Intent intent = getIntent();
        receiveStr = intent.getExtras().getString("sendData");// 전달한 값을 받을 때(mbti 값)


        TextView tx = (TextView) findViewById(R.id.loadtx);
        tx.setText("결과를 분석 중입니다....");


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intn = new Intent(getApplicationContext(), ResultMbti.class);
                intn.putExtra("sendData",receiveStr);
                startActivity(intn);

                //여기에 딜레이 후 시작할 작업들을 입력
            }
        }, 1500);// 0.5초 정도 딜레이를 준 후 시작

    }

    //뒤로 가기 버튼 막기

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}