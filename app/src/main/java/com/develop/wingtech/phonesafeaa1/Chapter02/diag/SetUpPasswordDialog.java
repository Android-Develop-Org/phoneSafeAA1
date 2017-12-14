package com.develop.wingtech.phonesafeaa1.Chapter02.diag;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.wingtech.phonesafeaa1.R;

/**
 * Created by liyalin on 2017/12/14.
 */

public class SetUpPasswordDialog extends Dialog implements View.OnClickListener {

    /**标题栏*/
    private TextView mTitleTV;
    /**首次输入密码文本框*/
    public EditText mFirstPWDET;
    /**确认密码文本框*/
    public EditText mAffirmET;
    /**回调接口*/
    private MyCallBack myCallBack;

    Context mContext;

    public SetUpPasswordDialog(Context context) {
        super(context);
        mContext = context;
    }

    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack = myCallBack;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_password_dailog);
        intView();
    }

    /**初始化控件*/
    public void intView(){
        mTitleTV = (TextView) findViewById(R.id.tv_setuppwd_title);
        mFirstPWDET = (EditText) findViewById(R.id.et_firstpwd);
        mAffirmET = (EditText) findViewById(R.id.et_affirm_password);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_cancle).setOnClickListener(this);
    }


    /**
     * 设置对话框标题栏
     * @param title
     */
    public void setTitle1(String title) {
        if(!TextUtils.isEmpty(title)) {
            Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show();
            mTitleTV.setText(title);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok :
                myCallBack.ok();
                break;
            case R.id.btn_cancle :
                myCallBack.cancle();
                break;
        }
    }
    public interface MyCallBack {
        void ok();
        void cancle();
    }

}
