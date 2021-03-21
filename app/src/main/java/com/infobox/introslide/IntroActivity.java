package com.infobox.introslide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {

    intropageradapter adapter;
    private ViewPager viewPager;
    TabLayout tabLayout;
    Button buttonnext, buttonstarted;
    int position;
    Animation buttonAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();
        
        
        if (loaddata()){

            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
            
        }


        setContentView(R.layout.activity_intro);
        viewPager = findViewById(R.id.viewpagerid);
        tabLayout = findViewById(R.id.tablayoutid);
        buttonnext = findViewById(R.id.nextbtnid);
        buttonstarted = findViewById(R.id.startedbuttonid);
        buttonAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        String food = getResources().getString(R.string.food);
        final ArrayList<ScreenItem> list = new ArrayList<>();
        list.add(new ScreenItem("Gathered Contact", food, R.drawable.contact));
        list.add(new ScreenItem("Blood Donor", food, R.drawable.blood_donation));
        list.add(new ScreenItem("Find Location", food, R.drawable.map));
        adapter = new intropageradapter(this, list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = viewPager.getCurrentItem();
                if (position < list.size()) {
                    position++;
                    viewPager.setCurrentItem(position);

                }

                if (position == list.size() - 1) {
                    loadLastScreen();
                }

            }


        });

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        buttonstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                saveData();
                finish();
            }
        });

    }

    private boolean loaddata() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean value=pref.getBoolean("isIntroOpended",false);
        return  value;

    }

    private void saveData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpended", true);
        editor.commit();


    }

    private void loadLastScreen() {
        buttonnext.setVisibility(View.INVISIBLE);
        buttonstarted.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        buttonstarted.setAnimation(buttonAnim);


    }
}
