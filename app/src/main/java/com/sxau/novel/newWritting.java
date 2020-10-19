package com.sxau.novel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newWritting extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newwritting);
        //得到toolbar对象
        Toolbar toolbar = (Toolbar) findViewById(R.id.tools_bar);
        //  设置导航图标
        toolbar.setNavigationIcon(R.drawable.back2);
        //  为NavigationIcon设置监听事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(newWritting.this, "你在点击导航图标", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(newWritting.this,WritingActivity.class);
                startActivity(intent);
            }
        });

        //点击发布按钮弹出对话框
        Button button=findViewById(R.id.publish);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog alert=new AlertDialog.Builder(newWritting.this).create();
                alert.setTitle("发布中…");
                alert.setMessage("内容正在审核中，请等待！");
                //添加取消按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                //添加"确定"按钮
                alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
                alert.show();
            }
        });

        EditText editText=findViewById(R.id.ET3);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30000)});
    }
}
