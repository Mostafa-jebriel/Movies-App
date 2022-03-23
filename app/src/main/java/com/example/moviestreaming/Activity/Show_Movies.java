package com.example.moviestreaming.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviestreaming.R;
import com.example.moviestreaming.databinding.ActivityShowMoviesBinding;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Show_Movies extends AppCompatActivity {
    ActivityShowMoviesBinding sm;
    String a,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm=ActivityShowMoviesBinding.inflate(getLayoutInflater());
        setContentView(sm.getRoot());
        Intent i=getIntent();
        if (i!=null){
            n=i.getStringExtra("title");
            sm.title.setText(i.getStringExtra("title"));
            Glide.with(Show_Movies.this).load(i.getStringExtra("Poster")).into(sm.poster);

            sm.plot.setText(i.getStringExtra("Plot"));
            sm.imdbRe.setText(i.getStringExtra("ImdbRating"));
            String g=i.getStringExtra("Genre");
            String r=i.getStringExtra("Runtime");
            String y=i.getStringExtra("Year");
            String t=i.getStringExtra("Type");
            a=i.getStringExtra("Mylist");
            sm.allDe.setText(t+"   |    "+r+"   |    "+y+"\n"+g);
            if (a.equals("0")){
                sm.add.setImageResource(R.drawable.add);
            }
            else {
                sm.add.setImageResource(R.drawable.check);
            }

            sm.addToList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (a.equals("0")){
                        sm.add.setImageResource(R.drawable.check);
                        a="1";
                        new Add_List().execute("add_list","8",n);
                        Toast.makeText(Show_Movies.this, "Add to Watch List", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sm.add.setImageResource(R.drawable.add);
                        a="0";
                        new Add_List().execute("add_list","9",n);
                        Toast.makeText(Show_Movies.this, "Remove from Watch List", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    class Add_List extends AsyncTask<String,String,String> {
        final String  Url = "http://192.168.1.7/movies_app/get_movie.php";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... type) {
            String method = type[0];
            if (method.equals("add_list")){
                String sh1=type[1];
                String sh2=type[2];
                try {
                    URL url=new URL(Url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream osw=httpURLConnection.getOutputStream();
                    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(osw,"UTF-8"));
                    String data= URLEncoder.encode("sql_number","UTF-8")+"="+URLEncoder.encode(sh1,"UTF-8")+
                            "&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(sh2,"UTF-8");
                    writer.write(data);
                    writer.flush();
                    writer.close();
                    osw.close();
                    InputStream ins = httpURLConnection.getInputStream();
                    ins.close();
                    return "Registration Success...";
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else  if (method.equals("remove_list")){
                String sh1=type[1];
                String sh2=type[2];
                try {
                    URL url=new URL(Url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream osw=httpURLConnection.getOutputStream();
                    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(osw,"UTF-8"));
                    String data= URLEncoder.encode("sql_number","UTF-8")+"="+URLEncoder.encode(sh1,"UTF-8")+
                            "&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(sh2,"UTF-8");
                    writer.write(data);
                    writer.flush();
                    writer.close();
                    osw.close();
                    InputStream ins = httpURLConnection.getInputStream();
                    ins.close();
                    return "Registration Success...";
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }
}