package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.view.View.OnClickListener;
import android.content.Intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view层的控件和业务层的控件，靠id关联和映射  给btn1赋值，即设置布局文件中的Button按钮id进行关联
                ImageButton btn_start=(ImageButton)findViewById(R.id.btn_start);

                //给btn1绑定监听事件
            btn_start.setOnClickListener(new OnClickListener() {

                  @Override
                public void onClick(View v) {

                               // 给bnt1添加点击响应事件
                               Intent intent =new Intent(MainActivity.this,game_interface.class);
                                 //启动
                                 startActivity(intent);

                  }
         });

    }
}
