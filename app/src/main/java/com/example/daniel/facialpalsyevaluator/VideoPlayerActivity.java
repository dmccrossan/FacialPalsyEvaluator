package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.Serializable;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

/*
Loads video player page
 */
public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    String TAG = "VideoPlayer";
    String prevPage;
    List<Patient> pList;
    int pTag;
    int aptTag;
    int vidTag;

    // loads and sets variables for the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int) i.getSerializableExtra("pTag");
        aptTag = (int) i.getSerializableExtra("aptTag");
        vidTag = (int) i.getSerializableExtra("vidTag");
        prevPage = (String) i.getSerializableExtra("prevPage");

        configureVideoView();
    }

    //sets up video player and plays chosen video
    private void configureVideoView() {

        videoView = findViewById(R.id.videoView1);

        String temp = pList.get(pTag).appointments.get(aptTag).videos.get(vidTag);

        videoView.setVideoPath(temp);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                Log.i(TAG, "Duration = " + videoView.getDuration());
            }
        });
        videoView.start();
    }

    // Returns to previous page
    public void done(View view) {

        Intent i = new Intent(VideoPlayerActivity.this, AppointmentActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("prevPage", prevPage);
        startActivity(i);
    }
}




