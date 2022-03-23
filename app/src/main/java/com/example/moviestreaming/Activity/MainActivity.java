package com.example.moviestreaming.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.moviestreaming.Fragment.HomeFragment;
import com.example.moviestreaming.Fragment.ListFragment;
import com.example.moviestreaming.Fragment.MoviesFragment;
import com.example.moviestreaming.Fragment.SearchFragment;
import com.example.moviestreaming.Fragment.TVFragment;
import com.example.moviestreaming.R;
import com.example.moviestreaming.databinding.ActivityMainBinding;
import com.example.moviestreaming.model.Image;
import com.example.moviestreaming.model.MainAd;
import com.example.moviestreaming.model.MovieItem;
import com.example.moviestreaming.model.ViewPager_Adapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
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

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mb=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mb.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.contaner_f,new HomeFragment()).commit();
        mb.bottomn.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment2=null;
                switch (item.getItemId()){

                    case R.id.home:
                        fragment2=new HomeFragment();
                        break;
                    case R.id.movies:
                        fragment2=new MoviesFragment();
                        break;
                    case R.id.tv:
                        fragment2=new TVFragment();
                        break;
                    case R.id.search:
                        fragment2=new SearchFragment();
                        break;
                    case R.id.mylist:
                        fragment2=new ListFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.contaner_f,fragment2).commit();
                return true;
            }
        });


    }
}