package com.donggi.mbtitest;

import android.app.Activity;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class BackPressCloseHandler {
    private long backKeyClickTime = 0;
    private Activity activity;

    public BackPressCloseHandler(Activity activity) {
        this.activity = activity;
    }

    public void onBackPressed() {
            if (System.currentTimeMillis() > backKeyClickTime + 2000) {
                backKeyClickTime = System.currentTimeMillis();
                showToast();
            }
            else if (System.currentTimeMillis() <= backKeyClickTime + 2000) {
                activity.moveTaskToBack(true);
                activity.finish();
                activity.finishAffinity();
                //ActivityCompat.finishAffinity(this);
                android.os.Process.killProcess(android.os.Process.myPid());

                //activity.finish();
            }
    }

    public void showToast() { Toast.makeText(activity, "뒤로 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show(); }

}
