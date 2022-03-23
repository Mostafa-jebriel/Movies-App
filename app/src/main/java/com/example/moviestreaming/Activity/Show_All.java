package com.example.moviestreaming.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.moviestreaming.databinding.ActivityShowAllBinding;
import com.example.moviestreaming.model.Move_Adapter;
import com.example.moviestreaming.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Show_All extends AppCompatActivity {
    ActivityShowAllBinding sb;
    String n;
    List<Movies> movies;
    Move_Adapter moveAdapter;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json,njson;
    String [] name_type={"Popular Movies","New Movies","Popular TV Show","New TV Show"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sb=ActivityShowAllBinding.inflate(getLayoutInflater());
        setContentView(sb.getRoot());
        movies=new ArrayList<>();
        Intent i=getIntent();
        if(i!=null){
            n=i.getStringExtra("title_a");
            sb.textA.setText(n);
            if (n.equals("Popular Movies")){
                new Show().execute("popular_movies","2");
            }
            else if (n.equals("New Movies")){
                new Show().execute("popular_movies","3");
            }
            else if (n.equals("Popular TV Show")){
                new Show().execute("popular_movies","6");
            }
            else if (n.equals("New TV Show")){
                new Show().execute("popular_movies","5");
            }
            else if (n.equals("n") ||n.equals("m")||n.equals("dc")||
                    n.equals("h")||n.equals("a")||n.equals("p")||
                    n.equals("hbo")||n.equals("cw")){
                new Show().execute("company","11",n);
            }
        }
        if (n.equals("n")){
            sb.textA.setText("Netflix");
        }
        else if(n.equals("m")){
            sb.textA.setText("Marvel");
        }
        else if(n.equals("dc")){
            sb.textA.setText("DC");
        }
        else if(n.equals("h")){
            sb.textA.setText("hulu");
        }
        else if(n.equals("a")){
            sb.textA.setText("Apple TV");
        }
        else if(n.equals("cw")){
            sb.textA.setText("CW");
        }
        else if(n.equals("hbo")){
            sb.textA.setText("HBO");
        }
        else if(n.equals("p")){
            sb.textA.setText("Prime Video");
        }



    }
    class Show extends AsyncTask<String,String,String>{
        final String  Url = "http://192.168.1.13/movies_app/get_movie.php";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            njson = s;

            if (s != null) {
                try {
                    jsonObject = new JSONObject(s);
                    jsonArray = jsonObject.getJSONArray("movie");
                    movies.removeAll(movies);
                    for (int c = 0; c < jsonArray.length(); c++) {
                        JSONObject jo = jsonArray.getJSONObject(c);
                        //movies.add(new Movies(jo.getString("title"),jo.getString("poster")));
                        Movies m=new Movies();
                        m.setTitle( jo.getString("title"));
                        m.setPoster(jo.getString("poster"));
                        m.setType(jo.getString("m_t"));
                        m.setImdbRating(jo.getString("imdbRating"));
                        m.setRuntime(jo.getString("runtime"));
                        m.setYear(jo.getString("year"));
                        m.setPlot( jo.getString("plot"));
                        m.setGenre(jo.getString("genre"));
                        m.setType_app(jo.getString("type_app"));
                        m.setCompany(jo.getString("company"));
                        m.setVideo(jo.getString("video"));
                        m.setMylist(jo.getString("mylist"));
                        movies.add(m);
                    }
                    moveAdapter = new Move_Adapter(Show_All.this, movies, 1);
                    sb.allList.setHasFixedSize(true);
                    sb.allList.setLayoutManager(new GridLayoutManager(Show_All.this, 2));
                    sb.allList.setAdapter(moveAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(String... type) {
            String method = type[0];
            if (method.equals("popular_movies")){
                String sh1=type[1];
                try {
                    URL l_url=new URL(Url);
                    HttpURLConnection htt=(HttpURLConnection)l_url.openConnection();
                    htt.setRequestMethod("POST");
                    htt.setDoOutput(true);
                    htt.setDoInput(true);
                    OutputStream osw=htt.getOutputStream();
                    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(osw,"utf-8"));
                    String data= URLEncoder.encode("sql_number","utf-8")+"="+URLEncoder.encode(sh1,"utf-8");
                    writer.write(data);
                    writer.flush();
                    writer.close();
                    osw.close();
                    InputStream ins = htt.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(ins,"iso-8859-1"));
                    StringBuilder stringBuilder=new StringBuilder();
                    while ((json=reader.readLine())!=null){
                        stringBuilder.append(json+"\n");
                    }
                    reader.close();
                    reader.close();
                    ins.close();
                    htt.disconnect();
                    return stringBuilder.toString().trim();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (method.equals("company")){
                String sh1=type[1];
                String sh2=type[2];
                try {
                    URL l_url=new URL(Url);
                    HttpURLConnection htt=(HttpURLConnection)l_url.openConnection();
                    htt.setRequestMethod("POST");
                    htt.setDoOutput(true);
                    htt.setDoInput(true);
                    OutputStream osw=htt.getOutputStream();
                    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(osw,"utf-8"));
                    String data= URLEncoder.encode("sql_number","utf-8")+"="+URLEncoder.encode(sh1,"utf-8")+
                            "&"+URLEncoder.encode("company","utf-8")+"="+URLEncoder.encode(sh2,"utf-8");
                    writer.write(data);
                    writer.flush();
                    writer.close();
                    osw.close();
                    InputStream ins = htt.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(ins,"iso-8859-1"));
                    StringBuilder stringBuilder=new StringBuilder();
                    while ((json=reader.readLine())!=null){
                        stringBuilder.append(json+"\n");
                    }
                    reader.close();
                    reader.close();
                    ins.close();
                    htt.disconnect();
                    return stringBuilder.toString().trim();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }
}