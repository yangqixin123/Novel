package com.sxau.novel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sxau.novel.LoginUtils.MyDataBaseHelper;
import com.sxau.novel.LoginUtils.User;
import com.sxau.novel.LoginUtils.UserInfoManager;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    //private TextView nickname;
    private TextView user_val;
    String account = "";
    //String mynickname="";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //nickname=findViewById(R.id.user_name);
        user_val=findViewById(R.id.user_val);

        Intent intent = getIntent();
        //Bundle bundle=intent.getExtras();
        account = intent.getStringExtra("phoneNumber");
        //mynickname=bundle.getString("nickname");
        // 把对应的account设置到telphone文本框
        user_val.setText(account);
        //nickname.setText(mynickname);

//        UserInfoManager userInfoManager=new UserInfoManager(UserActivity.this);
//        User user=new User();
//        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(UserActivity.this);
//        Object object=myDataBaseHelper.queryByPhone(account);
//        userInfoManager.register(user);
//        mynickname=object.toString();
//        nickname.setText(mynickname);

        Button button=(Button)findViewById(R.id.user_exitbtn);
        //得到toolbar对象
        Toolbar toolbar = (Toolbar) findViewById(R.id.tools_bar);
        //  设置导航图标
        toolbar.setNavigationIcon(R.drawable.back2);
        //  为NavigationIcon设置监听事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserActivity.this, "你在点击导航图标", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(UserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        TextView textView=findViewById(R.id.about);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String msg = "";
                msg="想知道更多关于Noveld的，就给我打电话吧！";
                if(!msg.equals("")) {
                    Toast.makeText(UserActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                //调到拨号界面
                Uri uri = Uri.parse("tel:17535286143");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }

        });

        TextView textView1=findViewById(R.id.update);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String msg = "";
                msg="已经是最新版本了！";
                if(!msg.equals("")) {
                    Toast.makeText(UserActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textView2=findViewById(R.id.collect);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String msg = "";
                msg="空空如也！";
                if(!msg.equals("")) {
                    Toast.makeText(UserActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textView3=findViewById(R.id.history);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String msg = "";
                msg="脑海里面什么都没有！";
                if(!msg.equals("")) {
                    Toast.makeText(UserActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textView4=findViewById(R.id.download);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String msg = "";
                msg="暂时没有下载任务哦！";
                if(!msg.equals("")) {
                    Toast.makeText(UserActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //点击退出登录按钮弹出对话框
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog alert=new AlertDialog.Builder(UserActivity.this).create();
                alert.setIcon(R.drawable.pitiful);
                alert.setTitle("退出？");
                alert.setMessage("真的要狠心退出吗？");
                //添加取消按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE,"我再想想",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                //添加"确定"按钮
                alert.setButton(DialogInterface.BUTTON_POSITIVE,"狠心退出", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent=new Intent(UserActivity.this,LoginMainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();
            }
        });

        Button bookStore=findViewById(R.id.bookstore_btn);
        bookStore.setOnClickListener(this);

        Button writing=findViewById(R.id.writing_btn);
        writing.setOnClickListener(this);

        Button bookshelf=findViewById(R.id.bookshelf_btn);
        bookshelf.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bookstore_btn:
                Intent intent=new Intent();
                intent.setClass(UserActivity.this,BookstoreActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("phoneNumber",account);
//                bundle.putString("nickname",mynickname);
//                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.writing_btn:
                Intent intent1=new Intent();
                intent1.setClass(UserActivity.this,WritingActivity.class);
//                Bundle bundle1=new Bundle();
//                bundle1.putString("phoneNumber",account);
//                bundle1.putString("nickname",mynickname);
//                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;

            case R.id.bookshelf_btn:
            {
                Intent intent2=new Intent();
                intent2.setClass(UserActivity.this,MainActivity.class);
//                Bundle bundle2=new Bundle();
//                bundle2.putString("phoneNumber",account);
//                bundle2.putString("nickname",mynickname);
//                intent2.putExtras(bundle2);
                startActivity(intent2);
            }

            break;
        }

    }
}

