package cs245.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.*;
import android.widget.Button;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    static MediaPlayer mp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(mp == null)
        {
            initializeMP();
        }

        final Button button = (Button)findViewById(R.id.Music);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mp.isPlaying())
                {
                    mp.pause();
                }
                else
                    mp.start();
            }
        });
    }

    public void initializeMP()
    {
        mp = MediaPlayer.create(MainActivity.this,R.raw.krtd);
        mp.start();
        mp.setLooping(true);

    }

}
