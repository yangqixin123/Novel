package com.sxau.novel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    String account = "";
//    String mynickname="";
    private Toolbar toolbar;
    private RelativeLayout relativeLayout;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        Bundle bundle=intent.getExtras();
//        account = bundle.getString("phoneNumber");
//        mynickname=bundle.getString("nickname");

        //得到toolbar对象
        Toolbar toolbar = (Toolbar) findViewById(R.id.tools_bar);
        //  设置导航图标
        toolbar.setNavigationIcon(R.drawable.search3);
        //  为NavigationIcon设置监听事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "你在点击导航图标", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,SearchDemo.class);
                startActivity(intent);
            }
        });


        //  添加menu
        toolbar.inflateMenu(R.menu.bookshelf_menu);
        //  为menu设置监听事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {

                    case R.id.item1:
                        msg = "更新连载书";
                        break;
                    case R.id.item2:
                        msg = "下载管理";
                        break;
                    case R.id.item3:
                        msg = "书籍管理";
                        break;


                }
                if(!msg.equals("")) {
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        Button bookStore=findViewById(R.id.bookstore_btn);
        bookStore.setOnClickListener(this);

        Button writing=findViewById(R.id.writing_btn);
        writing.setOnClickListener(this);

        Button user=findViewById(R.id.user_btn);
        user.setOnClickListener(this);

        Button book1=findViewById(R.id.book1);
        book1.setOnClickListener(this);

        Button book2=findViewById(R.id.book2);
        book2.setOnClickListener(this);

        Button book3=findViewById(R.id.book3);
        book3.setOnClickListener(this);

        Button book4=findViewById(R.id.book4);
        book4.setOnClickListener(this);

        Button book5=findViewById(R.id.book5);
        book5.setOnClickListener(this);

        Button book6=findViewById(R.id.book6);
        book6.setOnClickListener(this);

        Button add=findViewById(R.id.add);
        add.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bookstore_btn:
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,BookstoreActivity.class);
                startActivity(intent);
                break;

            case R.id.writing_btn:
                Intent intent1=new Intent();
                intent1.setClass(MainActivity.this,WritingActivity.class);
                startActivity(intent1);
                break;

            case R.id.user_btn:
                Intent intent2=new Intent();
                intent2.setClass(MainActivity.this,UserActivity.class);
//                Bundle mybundle=new Bundle();
//                mybundle.putString("phoneNumber",account);
//                mybundle.putString("nickname",mynickname);
//                intent2.putExtras(mybundle);
                startActivity(intent2);
                break;

            case R.id.book1:
                InputStream inputStream=getResources().openRawResource(R.raw.book1);
                String string = TxtReader.getString(inputStream);

                Intent intent3=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("book",string);
                intent3.putExtras(bundle);
                intent3.setClass(MainActivity.this,DisplayActivity.class);
                startActivity(intent3);
                break;

            case R.id.book2:
                InputStream inputStream1=getResources().openRawResource(R.raw.book2);
                String string1 = TxtReader.getString(inputStream1);

                Intent intent4=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("book",string1);
                intent4.putExtras(bundle1);
                intent4.setClass(MainActivity.this,DisplayActivity.class);
                startActivity(intent4);
                break;

            case R.id.book3:
                InputStream inputStream2=getResources().openRawResource(R.raw.book3);
                String string2 = TxtReader.getString(inputStream2);

                Intent intent5=new Intent();
                Bundle bundle2=new Bundle();
                bundle2.putString("book",string2);
                intent5.putExtras(bundle2);
                intent5.setClass(MainActivity.this,DisplayActivity.class);
                startActivity(intent5);
                break;

            case R.id.book4:
                InputStream inputStream3=getResources().openRawResource(R.raw.book4);
                String string3 = TxtReader.getString(inputStream3);

                Intent intent6=new Intent();
                Bundle bundle3=new Bundle();
                bundle3.putString("book",string3);
                intent6.putExtras(bundle3);
                intent6.setClass(MainActivity.this,DisplayActivity.class);
                startActivity(intent6);
                break;

            case R.id.book5:
                InputStream inputStream4=getResources().openRawResource(R.raw.book5);
                String string4 = TxtReader.getString(inputStream4);

                Intent intent7=new Intent();
                Bundle bundle4=new Bundle();
                bundle4.putString("book",string4);
                intent7.putExtras(bundle4);
                intent7.setClass(MainActivity.this,DisplayActivity.class);
                startActivity(intent7);
                break;

            case R.id.book6:
                InputStream inputStream5=getResources().openRawResource(R.raw.book6);
                String string5 = TxtReader.getString(inputStream5);

                Intent intent8=new Intent();
                Bundle bundle5=new Bundle();
                bundle5.putString("book",string5);
                intent8.putExtras(bundle5);
                intent8.setClass(MainActivity.this,DisplayActivity.class);
                startActivity(intent8);
                break;

            case R.id.add:
                Toast.makeText(this,"开发中...敬请期待",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
