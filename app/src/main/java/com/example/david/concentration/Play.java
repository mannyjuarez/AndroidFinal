package com.example.david.concentration;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by David on 12/2/2017.
 */

public class Play extends android.support.v4.app.Fragment{
    public static Play newInstance() { return new Play();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.play_frag, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((Button)getActivity().findViewById(R.id.b20)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(20))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b18)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(18))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b16)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(16))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b14)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(14))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b12)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(12))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b10)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(10))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(8))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(6))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.b4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Game.newInstance(4))
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((Button)getActivity().findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, Home.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
