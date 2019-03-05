package mg.studio.weatherappdesign;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadUpdate().execute();
    }

    public void btnClick(View view) {
        new DownloadUpdate().execute();
    }


    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        String buffertemp;
        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "http://t.weather.sojson.com/api/weather/city/101040100";
            HttpURLConnection urlConnection = null;

            BufferedReader reader;

            try{
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setConnectTimeout(5000);//设置连接超时:500ms
                urlConnection.setReadTimeout(5000);//设置读取超时:500ms

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    // Nothing to do.
                    Log.d("TAG", "input is null");
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    Log.d("TAG", line);
                    buffer.append(line + "\n");
                    if(buffer.toString().equals(buffertemp)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "It has updated already", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).start();
                    }
                    else
                    {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "update sucessfully", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).start();
                    };
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    Log.d("TAG", "buffer id null");
                    return null;

                }
                //The temperature
                buffertemp = buffer.toString();

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String buffer) {
            //Update the temperature displayed
            //buffer.toString();
            if(buffer == null)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Something goes wrong", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();
                return;
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//Date指定格式：yyyy-MM-dd HH:mm:ss:SSS
            Date datee = new Date();//创建一个date对象保存当前时间
            String time = simpleDateFormat.format(datee.getTime());//format()方法将Date转换成指定格式的Strin

            String date, today, temp;
            String tf[] = new String[5];
            String ltf[] = new String[5];
            String df[] = new String[5];
            String pic[] = new String[5];

            JsonParser parser = new JsonParser();  //创建JSON解析器
            JsonObject object = (JsonObject) parser.parse(buffer);  //创建JsonObject对象

            //temp = object.get("data").getAsString();               //用temp接住这个object
            //JsonObject objectdata=(JsonObject) parser.parse(temp);  //创建JsonObject对象
            JsonObject objectdata = object.get("data").getAsJsonObject();

            JsonArray array = objectdata.get("forecast").getAsJsonArray();    //得到为json的数组
            for (int i = 0; i <= 4; i++) {
                //System.out.println("---------------");
                JsonObject subObject = array.get(i).getAsJsonObject();
                tf[i] = subObject.get("high").getAsString();
                ltf[i] = subObject.get("low").getAsString();
                df[i] = subObject.get("week").getAsString();
                pic[i] = subObject.get("type").getAsString();
                //System.out.println(pic[i]);
            }

            ((TextView) findViewById(R.id.tf0)).setText(tf[0]);
            ((TextView) findViewById(R.id.tv_time)).setText(time);

            ((TextView) findViewById(R.id.tf1)).setText(tf[1]);
            ((TextView) findViewById(R.id.tf2)).setText(tf[2]);
            ((TextView) findViewById(R.id.tf3)).setText(tf[3]);
            ((TextView) findViewById(R.id.tf4)).setText(tf[4]);

            ((TextView) findViewById(R.id.ltf0)).setText(ltf[0]);
            ((TextView) findViewById(R.id.ltf1)).setText(ltf[1]);
            ((TextView) findViewById(R.id.ltf2)).setText(ltf[2]);
            ((TextView) findViewById(R.id.ltf3)).setText(ltf[3]);
            ((TextView) findViewById(R.id.ltf4)).setText(ltf[4]);

            ((TextView) findViewById(R.id.df1)).setText(df[1]);
            ((TextView) findViewById(R.id.df2)).setText(df[2]);
            ((TextView) findViewById(R.id.df3)).setText(df[3]);
            ((TextView) findViewById(R.id.df4)).setText(df[4]);

            ((TextView) findViewById(R.id.tp1)).setText(pic[1]);
            ((TextView) findViewById(R.id.tp2)).setText(pic[2]);
            ((TextView) findViewById(R.id.tp3)).setText(pic[3]);
            ((TextView) findViewById(R.id.tp4)).setText(pic[4]);

            int i = 0;
            while (i <= 4) {
                switch (pic[i]) {
                    case "多云":{}
                    case "阴": {
                        if(i==0)
                            findViewById(R.id.pic0).setBackgroundResource(R.drawable.partly_sunny_small);
                        if(i==1)
                            findViewById(R.id.pic1).setBackgroundResource(R.drawable.partly_sunny_small);
                        if(i==2)
                            findViewById(R.id.pic2).setBackgroundResource(R.drawable.partly_sunny_small);
                        if(i==3)
                            findViewById(R.id.pic3).setBackgroundResource(R.drawable.partly_sunny_small);
                        if(i==4)
                            findViewById(R.id.pic4).setBackgroundResource(R.drawable.partly_sunny_small);
                    }
                    break;
                    case "晴": {
                        if(i==0)
                            findViewById(R.id.pic0).setBackgroundResource(R.drawable.sunny_small);
                        if(i==1)
                            findViewById(R.id.pic1).setBackgroundResource(R.drawable.sunny_small);
                        if(i==2)
                            findViewById(R.id.pic2).setBackgroundResource(R.drawable.sunny_small);
                        if(i==3)
                            findViewById(R.id.pic3).setBackgroundResource(R.drawable.sunny_small);
                        if(i==4)
                            findViewById(R.id.pic4).setBackgroundResource(R.drawable.sunny_small);
                    }
                    break;
                    case "小雨": {
                        if(i==0)
                            findViewById(R.id.pic0).setBackgroundResource(R.drawable.rainy_small);
                        if(i==1)
                            findViewById(R.id.pic1).setBackgroundResource(R.drawable.rainy_small);
                        if(i==2)
                            findViewById(R.id.pic2).setBackgroundResource(R.drawable.rainy_small);
                        if(i==3)
                            findViewById(R.id.pic3).setBackgroundResource(R.drawable.rainy_small);
                        if(i==4)
                            findViewById(R.id.pic4).setBackgroundResource(R.drawable.rainy_small);
                    }
                    break;
                }
                i++;
            }
        }
    }
}
