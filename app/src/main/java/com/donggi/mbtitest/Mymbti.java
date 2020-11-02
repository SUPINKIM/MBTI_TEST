package com.donggi.mbtitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Mymbti extends AppCompatActivity {

    String myMbti;
    private BackPressCloseHandler backKeyClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //전체 화면 만들기
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_mymbti);
        backKeyClickHandler = new BackPressCloseHandler(this);  //뒤로 가기 버튼 핸들러 : 1번 토스트 팝업 , 2번 앱 종료

        //final String myMbti;
        TextView tx = (TextView)findViewById(R.id.text2);
        tx.setText("나의 MBTI를 선택해주세요.");

        Spinner sp = (Spinner) findViewById(R.id.spinner1);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myMbti = parent.getItemAtPosition(position).toString(); //스피너 값 저장
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //myMbti = sp.getSelectedItem().toString();  //스피너 값 저장

        Button nx = (Button) findViewById(R.id.next);
        nx.setText("다음     >");
        nx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), LoadingPage.class);
                intent2.putExtra("sendData",myMbti);
                startActivity(intent2);
            }
        });


        TextView tx2 = (TextView)findViewById(R.id.text3);
        tx2.setText("아직 나의 MBTI 타입을 모른다면? \n 버튼을 눌러 바로 확인해보세요.");

        Button btn = (Button) findViewById(R.id.button2);
        btn.setText("나의 MBTI 알아보기");

        //성격 검사 웹뷰 생성
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyWebView.class);
                startActivity(intent);
            }
        });

        /*
        // mbti 검사 웹 페이지로 이동
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.16personalities.com/ko/무료-성격-유형-검사"));
                startActivity(intent);
            }
        });

         */

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //backKeyClickHandler.onBackPressed();
    }

}