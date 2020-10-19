package com.sxau.novel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookstoreActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> data;
    private TextView tv_marquee; // 声明一个文本视图对象
    private boolean isPaused = false; // 跑马灯文字是否暂停滚动
//    String account = "";
//    String mynickname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);

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
                Toast.makeText(BookstoreActivity.this, "你在点击导航图标", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(BookstoreActivity.this,SearchDemo.class);
                startActivity(intent);
            }
        });

        // 从布局文件中获取名叫tv_marquee的文本视图
        tv_marquee = findViewById(R.id.tv_marquee);
        // 给tv_marquee设置点击监听器
        tv_marquee.setOnClickListener(this);

        Button bookshelf=findViewById(R.id.bookshelf_btn);
        bookshelf.setOnClickListener(this);

        Button writing=findViewById(R.id.writing_btn);
        writing.setOnClickListener(this);

        Button user=findViewById(R.id.user_btn);
        user.setOnClickListener(this);



        data = new ArrayList<Map<String, Object>>();
        simpleAdapter = new SimpleAdapter(this, getData(), R.layout.item_bookstore, new String[]{"title", "image"}, new int[]{R.id.title, R.id.image});
        lv = (ListView) findViewById(R.id.listView);
        //设置监听器
        lv.setAdapter(simpleAdapter);
        lv.setOnItemClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bookshelf_btn:
                Intent intent=new Intent();
                intent.setClass(BookstoreActivity.this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.writing_btn:
                Intent intent1=new Intent();
                intent1.setClass(BookstoreActivity.this,WritingActivity.class);
                startActivity(intent1);
                break;

            case R.id.user_btn:
            {
                Intent intent2=new Intent();
                intent2.setClass(BookstoreActivity.this,UserActivity.class);
//                Bundle bundle2=new Bundle();
//                bundle2.putString("phoneNumber",account);
//                bundle2.putString("nickname",mynickname);
//                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            }

            case R.id.tv_marquee:
            {
                isPaused = !isPaused;
                if (isPaused) {
                    tv_marquee.setFocusable(false); // 不允许获得焦点
                    tv_marquee.setFocusableInTouchMode(false); // 不允许在触摸时获得焦点
                } else {
                    tv_marquee.setFocusable(true); // 允许获得焦点
                    tv_marquee.setFocusableInTouchMode(true); // 允许在触摸时获得焦点
                    tv_marquee.requestFocus(); // 强制获得焦点，让跑马灯滚起来
                }

            }
            break;
        }
    }

    private List<Map<String, Object>> getData() {
        int[] imageId=new int[]{
                R.drawable.book1,R.drawable.book2,R.drawable.book3,R.drawable.book4,R.drawable.book5,R.drawable.book6,
               R.drawable.book7,R.drawable.book8,R.drawable.book9,R.drawable.book10
        };
        final String[]title=new String[]{"我修的可能是假仙","抗日之烽火系统","交锋","永恒国度","最强升级系统","创始道纪","大戏骨","明日未临","美人鱼","备胎大联盟"};
        for (int i=0;i<imageId.length;i++) {
            Map<String, Object>map = new HashMap<String, Object>();
            map.put("image",imageId[i]);
            map.put("title",title[i]);
            data.add(map);
        }

        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //通过view获取其内部的组件，进而进行操作
        String text = (String) ((TextView)view.findViewById(R.id.title)).getText();
        //大多数情况下，position和id相同，并且都从0开始
        String showText = "您正在阅读：" + text;
        Toast.makeText(this, showText, Toast.LENGTH_LONG).show();
        switch(position){
            case 0:
                InputStream inputStream=getResources().openRawResource(R.raw.book1);
                String string = TxtReader.getString(inputStream);

                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("book",string);
                intent.putExtras(bundle);
                intent.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent);
                break;
            case 1:
                InputStream inputStream1=getResources().openRawResource(R.raw.book2);
                String string1 = TxtReader.getString(inputStream1);

                Intent intent1=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("book",string1);
                intent1.putExtras(bundle1);
                intent1.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent1);
                break;
            case 2:
                InputStream inputStream2=getResources().openRawResource(R.raw.book3);
                String string2 = TxtReader.getString(inputStream2);

                Intent intent2=new Intent();
                Bundle bundle2=new Bundle();
                bundle2.putString("book",string2);
                intent2.putExtras(bundle2);
                intent2.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent2);
                break;
            case 3:
                InputStream inputStream3=getResources().openRawResource(R.raw.book4);
                String string3 = TxtReader.getString(inputStream3);

                Intent intent3=new Intent();
                Bundle bundle3=new Bundle();
                bundle3.putString("book",string3);
                intent3.putExtras(bundle3);
                intent3.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent3);
                break;
            case 4:
                InputStream inputStream4=getResources().openRawResource(R.raw.book5);
                String string4 = TxtReader.getString(inputStream4);

                Intent intent4=new Intent();
                Bundle bundle4=new Bundle();
                bundle4.putString("book",string4);
                intent4.putExtras(bundle4);
                intent4.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent4);
                break;
            case 5:
                InputStream inputStream5=getResources().openRawResource(R.raw.book6);
                String string5 = TxtReader.getString(inputStream5);

                Intent intent5=new Intent();
                Bundle bundle5=new Bundle();
                bundle5.putString("book",string5);
                intent5.putExtras(bundle5);
                intent5.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent5);
                break;
            case 6:
                InputStream inputStream6=getResources().openRawResource(R.raw.book7);
                String string6 = TxtReader.getString(inputStream6);

                Intent intent6=new Intent();
                Bundle bundle6=new Bundle();
                bundle6.putString("book",string6);
                intent6.putExtras(bundle6);
                intent6.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent6);
                break;
            case 7:
                InputStream inputStream7=getResources().openRawResource(R.raw.book8);
                String string7 = TxtReader.getString(inputStream7);

                Intent intent7=new Intent();
                Bundle bundle7=new Bundle();
                bundle7.putString("book",string7);
                intent7.putExtras(bundle7);
                intent7.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent7);
                break;
            case 8:
                InputStream inputStream8=getResources().openRawResource(R.raw.book9);
                String string8 = TxtReader.getString(inputStream8);

                Intent intent8=new Intent();
                Bundle bundle8=new Bundle();
                bundle8.putString("book",string8);
                intent8.putExtras(bundle8);
                intent8.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent8);
                break;
            case 9:
                InputStream inputStream9=getResources().openRawResource(R.raw.book10);
                String string9 = TxtReader.getString(inputStream9);

                Intent intent9=new Intent();
                Bundle bundle9=new Bundle();
                bundle9.putString("book",string9);
                intent9.putExtras(bundle9);
                intent9.setClass(BookstoreActivity.this,DisplayActivity.class);
                startActivity(intent9);
                break;

        }
    }
}