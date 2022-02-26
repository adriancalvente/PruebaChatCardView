package com.herprogramacion.Viber.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.herprogramacion.Viber.R;
import com.herprogramacion.Viber.adapter.PagerAdapter;
import com.herprogramacion.Viber.fragments.Chat;
import com.herprogramacion.Viber.fragments.Video;

public class MainActivity extends AppCompatActivity {
    public static String strUsuario;
    private TabLayout tabLayout;
    private TabItem chat, video, audio;
    private ViewPager2 viewPager;
    private FragmentManager fragmentManager;
    private Chat fragmentChat;
    private Video fragmentVideo;
    private PagerAdapter pagerAdapter;


    @SuppressLint("HardwareIds")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            declararObjetos();
            Intent intent = getIntent();
            strUsuario = intent.getStringExtra("usuario");
        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
        }
    }

    private void declararObjetos() {
        try {

            chat = findViewById(R.id.tabChat);
            video = findViewById(R.id.tabVideo);
            audio = findViewById(R.id.tabAudio);
            tabLayout = findViewById(R.id.tabLayout);
            viewPager = findViewById(R.id.viewPager);
            pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle(), tabLayout.getTabCount());
            viewPager.setAdapter(pagerAdapter);
            new TabLayoutMediator(
                    tabLayout,
                    viewPager,
                    (tab, position) -> {
                        switch (position) {
                            case 0:
                                //TODO cambiar a la r.string para internalizacion
                                tab.setText("Chat");
                                break;
                            case 1:
                                tab.setText("Video");
                                break;
                            case 2:
                                tab.setText("Audio");
                                break;
                        }
                    }
            ).attach();

        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
        }
    }

}
