package com.develop.wingtech.phonesafeaa1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.develop.wingtech.phonesafeaa1.Chapter01.adapter.HomeAdapter;
import com.develop.wingtech.phonesafeaa1.Chapter02.diag.SetUpPasswordDialog;

/**
 *
 */
public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView home_gv;

    /** 存储手机防盗密码的sp */
    private SharedPreferences msharedPreferences;

    /**保存最后点击时间*/
    private long mExitTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        msharedPreferences = getSharedPreferences("config", MODE_APPEND);

        home_gv = (GridView)findViewById(R.id.home_gv);
        HomeAdapter  homeAdapter = new HomeAdapter(this);
        home_gv.setAdapter(homeAdapter);
        home_gv.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:  // 手机防盗
                if(isSetUpPassword()){
                    //如果已经设置密码，弹出输入密码对话框

                } else {
                    //如果没有设置密码，弹出设置密码对话框
                    showSetUpPswdDialog();
                }
                break;
            case 1:  // 通讯卫士
                break;
            case 2:  // 软件管家
                break;
            case 3:  // 手机杀毒
                break;
            case 4:  // 缓存清理
                break;
            case 5:  // 进程管理
                break;
            case 6:  // 流量统计
                break;
            case 7:  // 高级工具
                break;
            case 8:  // 设置中心11
                break;

        }
    }

    /**
     * 弹出设置密码对话框
     */
    private void showSetUpPswdDialog() {
        final SetUpPasswordDialog setUpPasswordDialog = new SetUpPasswordDialog(HomeActivity.this);
        setUpPasswordDialog.setCallBack(new SetUpPasswordDialog.MyCallBack() {
            @Override
            public void ok() {
                String fisrtPswd = setUpPasswordDialog.mFirstPWDET.getText().toString().trim();
                String affPswd = setUpPasswordDialog.mAffirmET.getText().toString().trim();
                if(!TextUtils.isEmpty(fisrtPswd) && !TextUtils.isEmpty(affPswd)){
                    if(fisrtPswd.equals(affPswd)){
                        savePswd(fisrtPswd);
                        //弹出输入密码对话框

                    } else{
                        Toast.makeText(HomeActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(HomeActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void cancle() {
                setUpPasswordDialog.dismiss();
            }
        });
        setUpPasswordDialog.setCancelable(true);
        setUpPasswordDialog.show();
    }

    /**
     * 保存密码
     * @param fisrtPswd
     */
    private void savePswd(String fisrtPswd) {

    }
    /**
     * 判断是否设置密码
     * @return
     */
    private boolean isSetUpPassword() {
        String password = msharedPreferences.getString("PhoneAntiTheftPWD", null);
        if(TextUtils.isEmpty(password)){
            return false;
        }
        return true;
    }

    /**
     * 开启新的activity不关闭自己
     * @param cls 新的activity的字节码
     */
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(HomeActivity.this, cls);
        startActivity(intent);
    }
    /**
     * 按两次换返回键退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis() - mExitTime)>2000){
                Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
