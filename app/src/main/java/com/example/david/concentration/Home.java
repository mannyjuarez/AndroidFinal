package com.example.david.concentration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.media.*;

/**
 * Created by David on 12/2/2017.
 */

public class Home extends Fragment {


    public static Home newInstance() { return new Home();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_frag, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((Button)getActivity().findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Play.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, HighScores.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Credits.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.mp.isPlaying())
                {
                    MainActivity.mp.pause();
                }
                else
                {
                    MainActivity.mp.start();
                }
            }
        });
    }
}
