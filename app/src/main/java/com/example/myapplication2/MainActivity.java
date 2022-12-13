package com.example.myapplication2;

import static com.example.myapplication2.R.*;
import static com.example.myapplication2.R.raw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    static MediaPlayer mMediaPlayer;
    int currentIndex = 0;
    TextView songName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(id.startButton);
        Button next = findViewById(id.nextButton);
        Button prev = findViewById(id.prevButton);
        songName=findViewById(id.songName);
        ArrayList <Integer> list= new ArrayList<>();

        list.add(0, raw.song);
        list.add(1, raw.song2);
        list.add(2, raw.song3);

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), list.get(currentIndex));

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.pause();
                    start.setText(string.start);
                }
                else
                {
                    mMediaPlayer.start();
                    start.setText(string.pause);
                }
                songNames();
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (currentIndex < list.size() - 1)
                {
                    currentIndex++;
                }
                else
                {
                    currentIndex = 0;
                }
                if (mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.stop();
                }
                mMediaPlayer = MediaPlayer.create(getApplicationContext(),
                        list.get(currentIndex));
                mMediaPlayer.start();
                songNames();

            }
        });

        prev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (currentIndex > 0)
                {
                    currentIndex--;
                }
                else
                {
                    currentIndex = list.size() - 1;
                }
                if (mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.stop();
                }
                mMediaPlayer = MediaPlayer.create(getApplicationContext(),
                        list.get(currentIndex));
                mMediaPlayer.start();
                songNames();
            }
        });
    }

    private void songNames()
    {
        if (currentIndex == 0)
        {
            songName.setText(string.songName);
        }
        if (currentIndex == 1)
        {
            songName.setText(string.songName2);
        }
        if (currentIndex == 2)
        {
            songName.setText(string.songName3);
        }
    }

}

