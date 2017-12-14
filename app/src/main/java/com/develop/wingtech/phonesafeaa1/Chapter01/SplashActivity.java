package com.develop.wingtech.phonesafeaa1.Chapter01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.develop.wingtech.phonesafeaa1.HomeActivity;
import com.develop.wingtech.phonesafeaa1.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*
        * 判断版本号是否要更新
        * 1.如果有新版本，更新版本
        * 2.否则，进入Home界面
        * */
        if(isHasNewVersion()) {
            //更新版本没有实现
        } else{
            //进入主界面
            Intent intent = new Intent();
            intent.setClass(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //isHasNewVersion()
    private boolean isHasNewVersion(){
        return false;
    }
}
