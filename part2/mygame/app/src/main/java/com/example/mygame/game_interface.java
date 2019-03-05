package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class game_interface extends AppCompatActivity {

    int i,score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_interface);

        i=1;
        score = 0;

       /* ImageButton btn_chose1=(ImageButton)findViewById(R.id.btn_chose1);
        ImageButton btn_chose2=(ImageButton)findViewById(R.id.btn_chose2);
        ImageButton btn_chose3=(ImageButton)findViewById(R.id.btn_chose3);
        ImageButton btn_chose4=(ImageButton)findViewById(R.id.btn_chose4);*/
    }

    public void changtarpic()  //改变目标图片
    {
        ImageView img_tar=(ImageView)findViewById(R.id.img_target);

        Random rand = new Random();
        i = rand.nextInt(4)+1;  //随机生成 1-4
        if(i==1) img_tar.setImageDrawable(getResources().getDrawable(R.drawable.bear));
        if(i==2) img_tar.setImageDrawable(getResources().getDrawable(R.drawable.bird));
        if(i==3) img_tar.setImageDrawable(getResources().getDrawable(R.drawable.cat));
        if(i==4) img_tar.setImageDrawable(getResources().getDrawable(R.drawable.elephant));
        if(i==5) img_tar.setImageDrawable(getResources().getDrawable(R.drawable.fish));
        System.out.println(i);
    }

    public void onClickChose1(View view){
        if(i==1)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "Congraduation you are right", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();

            score++;
            ((TextView) findViewById(R.id.text_score)).setText(String.valueOf(score));
            changtarpic();
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "No you are wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();
        }
    }

    public void onClickChose2(View view){
        if(i==2)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "Congraduation you are right", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();

            score++;
            ((TextView) findViewById(R.id.text_score)).setText(String.valueOf(score));
            changtarpic();
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "No you are wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();
        }
    }

    public void onClickChose3(View view){
        if(i==3)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "Congraduation you are right", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();

            score++;
            ((TextView) findViewById(R.id.text_score)).setText(String.valueOf(score));
            changtarpic();
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "No you are wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();
        }
    }

    public void onClickChose4(View view){
        if(i==4)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "Congraduation you are right", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();

            score++;
            ((TextView) findViewById(R.id.text_score)).setText(String.valueOf(score));
            changtarpic();
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(game_interface.this, "No you are wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();
        }
    }


}
