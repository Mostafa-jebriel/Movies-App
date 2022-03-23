package com.example.moviestreaming.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviestreaming.model.Movies;
import com.example.moviestreaming.R;
import com.example.moviestreaming.databinding.FragmentHomeBinding;
import com.example.moviestreaming.model.Image;
import com.example.moviestreaming.model.MainAd;
import com.example.moviestreaming.model.Move_Adapter;
import com.example.moviestreaming.model.MovieItem;
import com.example.moviestreaming.model.ViewPager_Adapter;

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
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {
    FragmentHomeBinding fh;
    ViewPager_Adapter pagerAdapter;
    List<Image> homeItems;
    List<Movies> strem,movies;
    List<MovieItem> itemList;
    MainAd mainAd;
    Move_Adapter moveAdapter;
    private Activity mActivity;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json,njson;
    String [] name_type={"Popular Movies","New Movies","Popular TV Show","New TV Show"};
    int t=0;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fh=FragmentHomeBinding.inflate(getLayoutInflater());

        itemList=new ArrayList<>();
        return fh.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View_Pager();
        Sterm();
        new Get_Movie().execute("popular_movies","2");
        new Get_Movie().execute("popular_movies","3");
        new Get_Movie().execute("popular_movies","6");
        new Get_Movie().execute("popular_movies","5");
    }

    class Get_Movie extends AsyncTask<String,String,String> {
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
                    movies=new ArrayList<>();
                    movies.removeAll(movies);
                    for (int c = 0; c < jsonArray.length(); c++) {
                        JSONObject jo = jsonArray.getJSONObject(c);
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
                    String n=name_type[t];
                    itemList.add(new MovieItem(n,movies));
                    t++;
                    mainAd=new MainAd(getActivity(),itemList);
                    fh.allMovies.setHasFixedSize(true);
                    fh.allMovies.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                    fh.allMovies.setAdapter(mainAd);
                    if (t==name_type.length){
                        t=0;
                    }

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
            return null;
        }
    }

    private void Sterm(){
        strem=new ArrayList<>();
        strem.add(new Movies("n",R.drawable.n));
        strem.add(new Movies("cw",R.drawable.cw));
        strem.add(new Movies("h",R.drawable.hh));
        strem.add(new Movies("a",R.drawable.a));
        strem.add(new Movies("dc",R.drawable.dc));
        strem.add(new Movies("hbo",R.drawable.hbo));
        strem.add(new Movies("m",R.drawable.marvel));
        strem.add(new Movies("p",R.drawable.p));
        moveAdapter=new Move_Adapter(getActivity(),strem,2,false);
        fh.listSterm.setHasFixedSize(true);
        fh.listSterm.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        fh.listSterm.setAdapter(moveAdapter);

    }

    private void View_Pager(){
        homeItems=new ArrayList<>();
        homeItems.add(new Image("https://maxcdn.icons8.com/app/uploads/2019/05/poster-design-for-movies.jpg"));
        homeItems.add(new Image("https://maxcdn.icons8.com/app/uploads/2019/05/movie-poster-design-tips.png"));
        homeItems.add(new Image("https://see.news/wp-content/uploads/2022/01/share-1140x570.jpg"));
        homeItems.add(new Image("https://gate.ahram.org.eg/Media/News/2021/10/12/19_2021-637696358755763816-576.jpg"));
        homeItems.add(new Image("https://images.thedirect.com/media/article_full/dc-flash-black-adam.jpg?imgeng=cmpr_75/"));
        homeItems.add(new Image("https://photos.almashhadalaraby.com/61c2f43878bc8.jpeg?tag=550x0"));
        pagerAdapter=new ViewPager_Adapter(getActivity(),homeItems);
        fh.viewPager.setAdapter(pagerAdapter);
        fh.indicator.setupWithViewPager(fh.viewPager);
        Timer t=new Timer();
        t.scheduleAtFixedRate(new AutoSlider(),4000,6000);
        fh.indicator.setupWithViewPager(fh.viewPager,true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity=getActivity();
    }

    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            mActivity.runOnUiThread(new TimerTask() {
                @Override
                public void run() {
                    if (fh.viewPager.getCurrentItem()<homeItems.size()-1){
                        fh.viewPager.setCurrentItem(fh.viewPager.getCurrentItem()+1);
                    }
                    else {
                        fh.viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}