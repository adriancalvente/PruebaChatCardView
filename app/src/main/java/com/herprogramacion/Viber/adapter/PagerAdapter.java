package com.herprogramacion.Viber.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.herprogramacion.Viber.fragments.Audio;
import com.herprogramacion.Viber.fragments.Chat;

public class PagerAdapter extends FragmentStateAdapter {

    private final int numeroDePestaña;

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int numeroDePestaña) {
        super(fragmentManager, lifecycle);
        this.numeroDePestaña = numeroDePestaña;
    }

    public PagerAdapter(@NonNull Fragment fragment, int numeroDePestaña) {
        super(fragment);
        this.numeroDePestaña = numeroDePestaña;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try {

            switch (position) {
                case 0:
                    return new Chat();
                case 1:
                    return new Audio();
                default:
                    return null;
            }
        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return numeroDePestaña;
    }
}
