package com.sxau.novel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        Bundle bundle=this.getIntent().getExtras();
        String name=bundle.getString("book");

        TextView textView=findViewById(R.id.textview1);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(name);

    }
}
