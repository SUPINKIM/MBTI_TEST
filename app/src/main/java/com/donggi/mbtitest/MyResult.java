package com.donggi.mbtitest;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class MyResult extends Activity {

    private BackPressCloseHandler backKeyClickHandler;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<MyAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    private RecyclerView rRecyclerview; // 짝꿍 mbti 결과 해설을 위한 리사이클러

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //전체 화면 만들기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_myresult);
        
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        backKeyClickHandler = new BackPressCloseHandler(this);  //뒤로 가기 버튼 핸들러 : 1번 토스트 팝업 , 2번 앱 종료

        Intent intent = getIntent();
        String rembti = intent.getExtras().getString("friend");
        //myDataset = intent.getExtras().getStringArrayList("allData");


        rRecyclerview = (RecyclerView) findViewById(R.id.mbti_explain); //mbti 설명 영역
        rRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<com.donggi.mbtitest.ExpandableListAdapter.Item> data = new ArrayList<>();

        HashMap<String,String> mbti_expl = new HashMap<>(); //mbti 설명 저장하기 위한 hashmap 생성
        String [] mbti_arr = {"ISFP","ISFJ","ENFP","ESFP","ESFJ","ENFJ","INTP","INTJ","ISTJ","ISTP","INFJ","ESTJ","ESTP","ENTP","ENTJ","INFP"}; //16개짜리 mbti 배열 생성
        String [] explain_arr = {"당신과 찰떡궁합인 ISFP 유형은 신중하고 정이 많으며 겸손한 성격을 가진 성인군자 유형입니다. 말보단 행동으로 따뜻한 마음을 표현하기 때문에 멤버들을 뒤에서 조용히 잘 챙겨주는 모습이 자주 목격되네요!"
        ,"당신과 찰떡궁합인 ISFJ 유형은 성실하고 성격이 원만하여 대인관계에 능통한 서포터 유형입니다. 눈치가 빠르고 센스가 있어 멤버들을 잘 챙겨주곤 합니다. 매사에 열심이지만 어딘가 뚝딱거리는 모습이 귀여움 포인트입니다!"
        ,"당신과 찰떡궁합인 ENFP 유형은 밝고 낙천적이며 활발한 댕댕이같은 유형으로, 아이돌들의 mbti유형 중 가장 많은 유형이기도 합니다. 팀에 I(내향적)유형이 많다면 더 돋보이는 그들의 활달함은 팀 내의 분위기메이커, 예능 담당 멤버로서 모두를 웃음짓게 하네요. 관계성 맛집인 것은 덤이구요. :)"
        ,"당신과 찰떡궁합인 ESFP 유형은 친절하고 수용적이며 열정이 가득한 자유로운 천상 연예인의 유형입니다. 말을 재치있게 하고 장난끼도 많아서 어딜가나 인기쟁이겠네요. 멤버들과 티키타카가 하는 모습이 웃긴 짤로 돌아다니기도 합니다. :)"
        ,"당신과 찰떡궁합인 ESFJ 유형은 항상 친절하고 쾌활하며 사람 만나는 것을 즐기는 천상 인싸의 유형입니다. 웃음이 많고 리액션이 좋아서 주위 사람을 행복하게 하는 능력이 있습니다. 그룹에서 예능 담당을 맡고 있겠네요!"
        ,"당신과 찰떡궁합인 ENFJ 유형은 강직하고 도덕적인 성품을 지니고 있으며 이상을 꿈꾸는 언변능숙 유형입니다. 솔직하고 눈치가 빠르기 때문에 그룹 내에서 조언자의 역할을 하고 있을 것 같네요. 칭찬을 즐기고 주목받는 것을 좋아하기 때문에 멤버들의 칭찬을 들으면 즐거워하는 모습을 볼 수 있겠습니다."
        ,"당신과 찰떡궁합인 INTP 유형은 조용하고 과묵하며 통찰력이 있고 지적인 아이디어뱅크 유형입니다. 본인의 관심사에 있어서는 놀라운 집중력을 발휘하고 원하는 바를 이루기 위해 많은 노력을 합니다. 그리고 멤버들에게 그것을 설득하는 과정에서 무조건 밀어붙이는 것이 아니라 체계적인 이유를 들어 원하는 바를 이뤄냅니다."
        ,"당신과 찰떡궁합인 INTJ 유형은 내향적이고 상상력이 풍부하며 결단력이 있는 전력가 유형입니다. 호기심이 많아 지식을 끊임없이 탐구하는 스타일이며 자신이 좋아하는 주제에 얘기를 할 때 눈빛이 반짝입니다."
        ,"당신과 찰떡궁합인 ISTJ 유형은 성실하고 실용적이며 팩트에 기반한 결정을 내리는 완벽주의자 유형입니다. 조용하고 의지력이 강하며 현실적이기 때문에 조곤조곤 팩트만 말하는 스타일 입니다. 멤버들에게 누구보다 현실적이고 도움이 되는 조언을 해주는 역할을 하고 있갰네요."
        ,"당신과 찰떡궁합인 ISTP 유형은 자기 분야에서 뛰어난 재주로 일을 처리하는 본업 천재 유형입니다. 현실적이면서도 효율을 추구하기 때문에 경험한 것들을 바탕으로 창의적인 결과물들을 만들어냅니다. 처음엔 낯을 가리지만 친해지면 활달하게 말을 잘하는 모습이 보여 멤버들에게만 보여주는 특유의 밝은 모습을 팬들만 알고있갰네요."
        ,"당신과 찰떡궁합인 INFJ 유형은 게으름피우지 않고 성실하며 남을 돕는 것을 즐기는 선의의 옹호자 유형입니다. 사랑이 많아 멤버들의 감정을 따스하게 보살피고 있겠습니다. 매사에 진지하고 생각이 많지만 본인이 옳다고 생각하는 일에는 추진력이 강해 매력적으로 느껴집니다."
        ,"당신과 찰떡궁합인 ESTJ 유형은 조직화 된 것을 좋아하며 외향적이기 때문에 리더에 최적화된 유형입니다. 강직하고 확고한 성격이며 경험을 중시하기 때문에 멤버들이 노력하는 모습을 크게 칭찬하고 독려해주는 모습을 볼 수 있습니다. 끈기가 강하고 솔선수범하기 때문에 리더 포지션이 아니더라도 그룹 내에서 기둥같이 든든한 역할을 맡고 있겠네요."
        ,"당신과 찰떡궁합인 ESTP 유형은 사람들사이에서 항상 주목받고 중심이 되는 핵인싸 유형입니다. 에너지가 넘치고 사교적이기 때문에 예능멤버로 활약할 확률이 높습니다. 새로운 것을 도전하는 데에 두려움이 없어 그룹 내에 프레시함을 불어넣는 인물이기도 합니다."
        ,"당신과 찰떡궁합인 ENTP 유형은 독창적이고 상상력이 풍부한 팔방미인 유형입니다. 자신의 생각을 거리낌없이 얘기하고 추진력이 있지만 타인을 이해하는 능력 또한 뛰어나기 때문에 누구에게나 그 특이함 때문에 인기가 많은 유형입니다. 호감의 표시로 짓궂은 장난을 자주 치곤 하기 때문에 내향적인 멤버들을 애정표현으로 곧잘 놀리곤 하네요."
        ,"당신과 찰떡궁합인 ENTJ 유형은 열정적이고 단호하며 통솔력이 있는 유형입니다. 대화의 주도권을 가지며 모임을 만들기도 합니다. 완벽을 추구하며 일에 대한 열정이 충만하기 때문에 아이돌이라는 자신의 직업에 누구보다도 진심인 모습을 볼 수 있습니다."
        ,"당신과 찰떡궁합인 INFP 유형은 마음이 따뜻하고 조용하며 낭만적인 유형입니다. 감정에 예민하고 이상적이기 때문에 섬세한 감정캐치능력으로 작사 작곡을 곧잘하기도 합니다. 성격이 세심하고 디테일해서 멤버들에게도 소소한 감동을 선사하기도 하네요."};


        for (int i=0; i < 16; i++){
            mbti_expl.put(mbti_arr[i],explain_arr[i]);
        }
        //hashmap에 각 키,값끼리 넣기

        /*
        //설명이 닫혀진 채로 보임
        com.example.mbtitest.ExpandableListAdapter.Item places = new com.example.mbtitest.ExpandableListAdapter.Item(com.example.mbtitest.ExpandableListAdapter.HEADER, "나와 잘 맞는 MBTI는?  "+rembti);
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListAdapter.Item(com.example.mbtitest.ExpandableListAdapter.CHILD, mbti_expl.get(rembti)));

        data.add(places);

        rRecyclerview.setAdapter(new com.example.mbtitest.ExpandableListAdapter(data));

        */

        //---------------------------------------------------------------
        //설명이 열린 채로 보임
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER,"나와 잘 맞는 MBTI는?  "+rembti));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD,mbti_expl.get(rembti)));


        rRecyclerview.setAdapter(new com.donggi.mbtitest.ExpandableListAdapter(data));

        //---------------------------------------------------------------

        TextView tx3 = (TextView) findViewById(R.id.txtxss); //결과 멘트
        tx3.setText("당신과 지금 당장 찐친 가능한 아이돌은 바로 ... ");



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        //myDataset = (ArrayList<MyData>) intent.getSerializableExtra("allData");

        ArrayList<MyData> list = (ArrayList<MyData>) intent.getSerializableExtra("allData");

        if(list.size()>8){ //8개 넘으면 개수 8개만
            Random random = new Random();
            int a[] = new int[8];

            for(int i=0; i<8; i++){
                a[i]=random.nextInt(list.size()); //랜덤으로 숫자 하나 생성
                for(int j=0;j<i;j++){
                    if(a[j]==a[i]){
                        i--;
                    }
                }
            }

            for(int i=0; i<8; i++){
                System.out.println(a[i]);
                myDataset.add(list.get(a[i]));
            }


        }
        else{  //8개보다 적으면 다 출력
            myDataset=list;
        }


        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


        SnapHelper snapHelper = new GravitySnapHelper(Gravity.CENTER);
        snapHelper.attachToRecyclerView(mRecyclerView);


        // 1)에서 만든 name, group 변수에 저장된 값 불러옴

        //CircleIndicator2 indicator = (CircleIndicator2) findViewById(R.id.indicator);
        CircleIndicator2 indicator = (CircleIndicator2) findViewById(R.id.indicator);
        indicator.attachToRecyclerView(mRecyclerView, snapHelper);
        // optional
        mAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());


        ImageButton btn2 = (ImageButton) findViewById(R.id.goback);
        //Button btn = (Button) findViewById(R.id.btn);  //돌아가기 버튼

        //btn.setText("   <   다시하기   ");
        //btn.setPaintFlags(btn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);  //자신의 mbti를 선택하는 화면으로 다시 돌아가기
                startActivity(intent2);
            }
        });


    }


    //aos back 버튼을 1번 눌렀을 시, 토스트 팝업 노출 2번 눌렀을 시, 앱 종료

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backKeyClickHandler.onBackPressed();
    }

}