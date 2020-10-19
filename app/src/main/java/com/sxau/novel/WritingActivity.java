package com.sxau.novel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WritingActivity extends AppCompatActivity implements View.OnClickListener {
//    String account = "";
//    String mynickname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

//        Intent intent = getIntent();
//        Bundle bundle=intent.getExtras();
//        account = bundle.getString("phoneNumber");
//        mynickname=bundle.getString("nickname");


        Button bookStore=findViewById(R.id.bookstore_btn);
        bookStore.setOnClickListener(this);

        Button bookshelf=findViewById(R.id.bookshelf_btn);
        bookshelf.setOnClickListener(this);

        Button user=findViewById(R.id.user_btn);
        user.setOnClickListener(this);

        //得到toolbar对象
        Toolbar toolbar = (Toolbar) findViewById(R.id.tools_bar);
        //  添加menu
        toolbar.inflateMenu(R.menu.writing_menu);
        //  为menu设置监听事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {

                    case R.id.item1:
                        msg = "原创有福利！";
                        break;
                    case R.id.item2:
                        msg = "开发中，敬请期待！";
                        break;
                    case R.id.item3:
                        msg = "开发中，敬请期待！";
                        break;


                }
                if(!msg.equals("")) {
                    Toast.makeText(WritingActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //点击新写一本按钮
        Button button = (Button) findViewById(R.id.add_min);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WritingActivity.this, newWritting.class));

            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bookstore_btn:
                Intent intent=new Intent();
                intent.setClass(WritingActivity.this,BookstoreActivity.class);
                startActivity(intent);
                break;

            case R.id.bookshelf_btn:
                Intent intent1=new Intent();
                intent1.setClass(WritingActivity.this,MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.user_btn:
            {
                Intent intent2=new Intent();
                intent2.setClass(WritingActivity.this,UserActivity.class);
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