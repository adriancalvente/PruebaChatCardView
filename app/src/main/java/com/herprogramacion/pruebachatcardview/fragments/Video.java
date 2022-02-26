package com.herprogramacion.pruebachatcardview.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.herprogramacion.pruebachatcardview.R;
import com.herprogramacion.pruebachatcardview.threads.MediaThread;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Video#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Video extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private VideoView videoView;

    public Video() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Video.
     */
    // TODO: Rename and change types and number of parameters
    public static Video newInstance(String param1, String param2) {
        Video fragment = new Video();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {

            View inflate = inflater.inflate(R.layout.fragment_video, container, false);
            videoView = inflate.findViewById(R.id.videoView);
            MediaThread mediaThread = new MediaThread(getContext(), R.raw.video, videoView);
            mediaThread.start();
            return inflate;
        } catch (Exception e) {
            Log.e("unexpected", "Unexpected error");
            return null;
        }
    }
}