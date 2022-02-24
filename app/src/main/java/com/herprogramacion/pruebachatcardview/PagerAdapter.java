package com.herprogramacion.pruebachatcardview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    private int numeroDePestaña;

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
        switch (position) {
            case 0:
                return Chat.newInstance("josele","");
            case 1:
                return new Video();
            case 2:
                return new Video();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return numeroDePestaña;
    }
}
