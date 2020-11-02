package com.donggi.mbtitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler bpc; //뒤로 가기 버튼 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //전체 화면 만들기
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        bpc = new BackPressCloseHandler(this); //객체 생성

        TextView textView1 = (TextView) findViewById(R.id.text1) ;
        textView1.setText("아친 - MBTI로 아이돌 친구 찾기") ;

        //TextView text2 = (TextView) findViewById(R.id.text2);
        //text2.setText("아이돌편");

        Button button = (Button) findViewById(R.id.start1);  //시작하기 버튼
        button.setText("시작하기");

        // 다음 페이지로 이동
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mymbti.class);
                startActivity(intent);
            }
        });

    }
    //1번 누르면 토스트 팝업, 두번 누르면 종료
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        bpc.onBackPressed();
    }
}