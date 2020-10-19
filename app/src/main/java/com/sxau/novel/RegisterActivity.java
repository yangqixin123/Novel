package com.sxau.novel;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sxau.novel.LoginUtils.User;
import com.sxau.novel.LoginUtils.UserInfoManager;
import com.sxau.novel.LoginUtils.ViewUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_get_otp;
    private Button bt_submit_register;
    private EditText et_telphone;
    private EditText et_otpCode;
    private EditText et_username;
    private EditText et_gender;
    private EditText et_age;
    private EditText et_password1;
    private EditText et_password2;

    private String mVerifyCode; // 验证码
    String account = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bt_get_otp = findViewById(R.id.bt_get_otp);
        bt_submit_register = findViewById(R.id.bt_submit_register);
        et_telphone = findViewById(R.id.et_telphone);
        et_otpCode = findViewById(R.id.et_otpCode);
        et_username = findViewById(R.id.et_username);
        et_gender = findViewById(R.id.et_gender);
        et_age = findViewById(R.id.et_age);
        et_password1 = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);

        bt_get_otp.setOnClickListener(this);
        bt_submit_register.setOnClickListener(this);
        // 给et_telphone添加文本变更监听器
        et_telphone.addTextChangedListener(new RegisterActivity.HideTextWatcher(et_telphone));
        et_password1.addTextChangedListener(new RegisterActivity.HideTextWatcher(et_password1));
        et_password2.addTextChangedListener(new RegisterActivity.HideTextWatcher(et_password2));
        /*
            设置当输入框焦点失去时提示错误信息
            第一个参数指明输入框对象
            第二个参数指明输入数据类型
            第三个参数指明输入不合法时提示信息
         */
        setOnFocusChangeErrMsg(et_telphone, "phone", "手机号格式不正确");
        setOnFocusChangeErrMsg(et_password1, "password", "密码必须为6位数字");
        setOnFocusChangeErrMsg(et_gender, "gender", "性别只能填1或2");
        // 接收用户在登录界面输入的数据，如果输入过了就不用再输入了
        // 注意接收上一个页面Intent的信息，需要getIntent，而非重新new一个Intent
        Intent intent = getIntent();
        account = intent.getStringExtra("phoneNumber");
        // 把对应的account设置到telphone输入框
        et_telphone.setText(account);
    }

    private void setOnFocusChangeErrMsg(final EditText editText, final String inputType, final String errMsg) {
        editText.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        String inputStr = editText.getText().toString();
                        if (!hasFocus) {
                            if (inputType == "phone") {
                                if (isTelphoneValid(inputStr)) {
                                    editText.setError(null);
                                } else {
                                    editText.setError(errMsg);
                                }
                            }
                            if (inputType == "password") {
                                if (isPasswordValid(inputStr)) {
                                    editText.setError(null);
                                } else {
                                    editText.setError(errMsg);
                                }
                            }
                            if (inputType == "gender") {
                                if (isGenderValid(inputStr)) {
                                    editText.setError(null);
                                } else {
                                    editText.setError(errMsg);
                                }
                            }
                        }
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        String telphone = et_telphone.getText().toString().trim();
        String otpCode = et_otpCode.getText().toString().trim();
        String username = et_username.getText().toString().trim();
        String gender = et_gender.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String password1 = et_password1.getText().toString().trim();
        String password2 = et_password2.getText().toString().trim();

        UserInfoManager userInfoManager=new UserInfoManager(RegisterActivity.this);
        User user=new User();
        user.setUsername(telphone);
        user.setPassword(password1);
        user.setAge(age);
        user.setGender(gender);
        userInfoManager.register(user);

        if (v.getId()==R.id.bt_get_otp){
            if (TextUtils.isEmpty(telphone)) {
                Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            } else if (telphone.length() < 11||!(isTelphoneValid(telphone))) { // 手机号码不足11位
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }else {
                // 生成六位随机数字的验证码
                mVerifyCode = String.format("%06d", (int) (Math.random() * 1000000 % 1000000));
                // 弹出提醒对话框，提示用户六位验证码数字
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.notice);
                builder.setTitle("Novel验证码");
                builder.setMessage("手机号" + telphone + "，本次验证码是" + mVerifyCode + "。该验证码5分钟内有效。若非您本人操作，请忽略本短信。");
                builder.setPositiveButton("好的", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        }else if (v.getId()==R.id.bt_submit_register){
            if (!et_otpCode.getText().toString().equals(mVerifyCode)) {
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            } else { // 验证码校验通过

                registerSuccess(telphone, otpCode, username, gender, age, password1, password2); // 提示用户注册成功

            }
        }
    }



    // 校验账号不能为空且必须是中国大陆手机号（宽松模式匹配）
    private boolean isTelphoneValid(String account) {
        if (account == null) {
            return false;
        }
        // 首位为1, 第二位为3-9, 剩下九位为 0-9, 共11位数字
        String pattern = "^[1]([3-9])[0-9]{9}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(account);
        return m.matches();
    }

    // 校验密码不多于6位
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length()>5;
    }

    // 性别只能填1或2
    private boolean isGenderValid(String gender) {
        return gender.equals("1") || gender.equals("2");
    }

    private void registerSuccess(final String telphone, final String otpCode,
                                 final String username, final String gender,
                                 final String age, final String password1, final String password2){

        if (TextUtils.isEmpty(telphone) || TextUtils.isEmpty(otpCode) || TextUtils.isEmpty(username)
                || TextUtils.isEmpty(gender) || TextUtils.isEmpty(age)
                || TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2)) {
            Toast.makeText(RegisterActivity.this, "存在输入为空，注册失败", Toast.LENGTH_SHORT).show();
        } else if (password1.equals(password2)) {
            Intent intent=new Intent(this,LoginMainActivity.class);
            intent.putExtra("nickname",username);
            startActivity(intent);
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        }
    }

    // 定义编辑框的文本变化监听器
    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        private CharSequence mStr;

        HideTextWatcher(EditText v) {
            super();
            mView = v;
            mMaxLength = ViewUtil.getMaxLength(v);
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mStr = s;
        }

        @Override
        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            if (mStr == null || mStr.length() == 0)
                return;
            // 手机号码输入达到11位，或者密码/验证码输入达到6位，都关闭输入法软键盘
            if ((mStr.length() == 11 && mMaxLength == 11) ||
                    (mStr.length() == 6 && mMaxLength == 6)) {
                ViewUtil.hideOneInputMethod(RegisterActivity.this, mView);
            }

        }
    }
}