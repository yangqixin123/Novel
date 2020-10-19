package com.sxau.novel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.sxau.novel.LoginUtils.MyDataBaseHelper;
import com.sxau.novel.LoginUtils.User;
import com.sxau.novel.LoginUtils.UserInfoManager;
import com.sxau.novel.LoginUtils.ViewUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioGroup rg_login; // 声明一个单选组对象
    private RadioButton rb_password; // 声明一个单选按钮对象
    private RadioButton rb_verifycode; // 声明一个单选按钮对象
    private EditText et_phone; // 声明一个编辑框对象
    private EditText et_password; // 声明一个编辑框对象
    private TextView tv_password; // 声明一个文本视图对象
    private TextView tv_userRegister;// 声明一个文本视图对象
    private TextView tv_service_agreement;// 声明一个文本视图对象
    private Button btn_forget; // 声明一个按钮控件对象
    private Button btn_login;  //声明一个按钮控件对象
    private CheckBox ck_remember; // 声明一个复选框对象
    private ImageView iv_third_method1;//声明一个图片控件
    private ImageView iv_third_method2;//声明一个图片控件
    private ImageView iv_third_method3;//声明一个图片控件

    private boolean bRemember = false; // 是否记住密码
    private int mRequestCode = 0; // 跳转页面时的请求代码
    private String mVerifyCode; // 验证码
    private String mPassword ; // 默认密码
    String nickname = "";
    private MyDataBaseHelper mHelper; // 声明一个用户数据库帮助器对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actity_loginmain);
        rg_login=findViewById(R.id.rg_login);
        rb_password=findViewById(R.id.rb_password);
        rb_verifycode=findViewById(R.id.rb_verifycode);
        et_phone=findViewById(R.id.et_phone);
        et_password=findViewById(R.id.et_password);
        tv_password=findViewById(R.id.tv_password);
        tv_service_agreement=findViewById(R.id.tv_service_agreement);
        tv_userRegister=findViewById(R.id.tv_userRegister);
        btn_forget=findViewById(R.id.btn_forget);
        btn_login=findViewById(R.id.btn_login);
        iv_third_method1=findViewById(R.id.iv_third_method1);
        iv_third_method2=findViewById(R.id.iv_third_method2);
        iv_third_method3=findViewById(R.id.iv_third_method3);

        ck_remember=findViewById(R.id.ck_remember);
        // 给rg_login设置单选监听器
        rg_login.setOnCheckedChangeListener(new RadioListener());
        // 给ck_remember设置勾选监听器
        ck_remember.setOnCheckedChangeListener(new CheckListener());
        // 给et_phone添加文本变更监听器
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone));
        // 给et_password添加文本变更监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password));
        btn_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        iv_third_method1.setOnClickListener(this); // 第三方登录方式1
        iv_third_method2.setOnClickListener(this); // 第三方登录方式2
        iv_third_method3.setOnClickListener(this); // 第三方登录方式3
        tv_service_agreement.setOnClickListener(this);//同意服务协议
        tv_userRegister.setOnClickListener(this); //用户注册
        Intent intent=getIntent();
        nickname=intent.getStringExtra("nickname");
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

    // 校验密码不大于6位
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() < 6;
    }
    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        String password=et_password.getText().toString();
        UserInfoManager userInfoManager=new UserInfoManager(LoginMainActivity.this);
        boolean flag=userInfoManager.login(phone, password);
        if (v.getId() == R.id.btn_forget) { // 点击了“忘记密码”按钮
            if (phone.length() < 11||!(isTelphoneValid(phone))) { // 手机号码不足11位
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rb_password.isChecked()) { // 选择了密码方式校验，此时要跳到找回密码页面
                Intent intent = new Intent(this, LoginForgetActivity.class);
                // 携带手机号码跳转到找回密码页面
                intent.putExtra("phone", phone);
                startActivityForResult(intent, mRequestCode);
            } else if (rb_verifycode.isChecked()) { // 选择了验证码方式校验，此时要生成六位随机数字验证码
                // 生成六位随机数字的验证码
                mVerifyCode = String.format("%06d", (int) (Math.random() * 1000000 % 1000000));
                // 弹出提醒对话框，提示用户六位验证码数字
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.notice);
                builder.setTitle("Novel验证码");
                builder.setMessage("手机号" + phone + "，本次验证码是" + mVerifyCode + "。该验证码5分钟内有效。若非您本人操作，请忽略本短信。");
                builder.setPositiveButton("好的", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        } else if (v.getId() == R.id.btn_login) { // 点击了“登录”按钮

            if (phone.length() < 11||!(isTelphoneValid(phone))) { // 手机号码不足11位或者格式不对
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rb_password.isChecked()) { // 密码方式校验
                if (/*!et_password.getText().toString().equals(mPassword)||*/ isPasswordValid(password)) {
                    Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                } else if (flag){ // 密码校验通过
                    loginSuccess(); // 提示用户登录成功
                }else {
                    Toast.makeText(this, "登录失败，账号或者密码错误", Toast.LENGTH_SHORT).show();
                }
            } else if (rb_verifycode.isChecked()) { // 验证码方式校验
                if (!et_password.getText().toString().equals(mVerifyCode)) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                } else { // 验证码校验通过
                    loginSuccess(); // 提示用户登录成功
                }
            }
        }else if (v.getId()==R.id.iv_third_method1){
            Toast.makeText(this, "微信登录，开发中！", Toast.LENGTH_SHORT).show();
        }else if (v.getId()==R.id.iv_third_method2){
            Toast.makeText(this, "QQ登录，开发中！", Toast.LENGTH_SHORT).show();
        }else if (v.getId()==R.id.iv_third_method3){
            Toast.makeText(this, "GitHub登录，开发中！", Toast.LENGTH_SHORT).show();
        }else if (v.getId()==R.id.tv_service_agreement){
            Toast.makeText(this, "协议正在起草中，敬请期待！", Toast.LENGTH_SHORT).show();
        }else if (v.getId()==R.id.tv_userRegister){
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra("phoneNumber",phone);
            startActivity(intent);
        }
    }

    // 定义登录方式的单选监听器
    private class RadioListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_password) { // 选择了密码登录
                tv_password.setText("密    码：");
                et_password.setHint("请输入6位数字密码");
                btn_forget.setText("忘记密码");
                ck_remember.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.rb_verifycode) { // 选择了验证码登录
                tv_password.setText("验证码：");
                et_password.setHint("请输入验证码");
                btn_forget.setText("获取验证码");
                ck_remember.setVisibility(View.INVISIBLE);
            }
        }
    }
    // 定义是否记住密码的勾选监听器
    private class CheckListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.ck_remember) {
                bRemember = isChecked;
            }
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
                ViewUtil.hideOneInputMethod(LoginMainActivity.this, mView);
            }

        }
    }

    // 从后一个页面携带参数返回当前页面时触发
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == mRequestCode && data != null) {
            // 用户密码已改为新密码，故更新密码变量
            mPassword = data.getStringExtra("new_password");
        }
    }

    // 从修改密码页面返回登录页面，要清空密码的输入框
    @Override
    protected void onRestart() {
        et_password.setText("");
        super.onRestart();
    }

    // 校验通过，登录成功
    private void loginSuccess() {
        String phone = et_phone.getText().toString();
        String desc = String.format("您的手机号码是%s。恭喜你通过登录验证，点击“确定”按钮返回上个页面",
                et_phone.getText().toString());
        Intent intent=new Intent(LoginMainActivity.this,UserActivity.class);
//        Bundle bundle=new Bundle();
//        bundle.putString("phoneNumber",phone);
//        bundle.putString("nickname",nickname);
        intent.putExtra("phoneNumber",phone);
        startActivity(intent);
        // 弹出提醒对话框，提示用户登录成功
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("我再看看", null);
        AlertDialog alert = builder.create();
        alert.show();

        // 如果勾选了“记住密码”，则把手机号码和密码保存为数据库的用户表记录
//        if (bRemember) {
//            // 创建一个用户信息实体类
//            User info = new User();
//            info.username = et_phone.getText().toString();
//            info.password = et_password.getText().toString();
//            // 往用户数据库添加登录成功的用户信息（包含手机号码、密码、登录时间）
//            //mHelper.insert(info);
//        }
    }
}
