package com.example.moviestreaming.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviestreaming.model.Movies;
import com.example.moviestreaming.databinding.FragmentSearchBinding;
import com.example.moviestreaming.model.Move_Adapter;

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

public class SearchFragment extends Fragment{
    FragmentSearchBinding sb;
    List<Movies> movies;
    Move_Adapter moveAdapter;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json,njson;


    public SearchFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sb=FragmentSearchBinding.inflate(getLayoutInflater());
        movies=new ArrayList<>();
        return sb.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sb.textSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new Search().execute("search","10",query.toString());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //new Search().execute("search","10",newText);
                return false;
            }
        });


    }
    class Search extends AsyncTask<String,String,String> {
        final String  Url = "http://192.168.1.13/movies_app/get_movie.php";


        @Override
        protected void onPreExecute() {
            super.onPreExecute(); }

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
                    moveAdapter = new Move_Adapter(getActivity(), movies, 1);
                    sb.searchList.setHasFixedSize(true);
                    sb.searchList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    sb.searchList.setAdapter(moveAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(String... type) {
            String method = type[0];
            if (method.equals("search")){
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
                            "&"+URLEncoder.encode("name","utf-8")+"="+URLEncoder.encode(sh2,"utf-8");
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