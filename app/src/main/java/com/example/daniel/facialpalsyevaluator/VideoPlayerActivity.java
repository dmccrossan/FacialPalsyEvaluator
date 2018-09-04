package com.example.daniel.facialpalsyevaluator;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import daniel.example.com.facialpalsyevaluator.R;

public class VideoPlayerActivity extends AppCompatActivity {

////    private VideoView videoView;
//    private MediaController mediaController;
//    String TAG = "VideoPlayer";
//    StorageReference mStorageRef;
//
//    public VideoView vid;

    // private FirebaseAuth mAuth;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    private VideoView videoView;
    private MediaController mediaController;
    String TAG = "VideoPlayer";
    String prevPage;

    List<Patient> pList;
    int pTag;
    int aptTag;
    int vidTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent i = getIntent();

        pList = (List<Patient>) i.getSerializableExtra("pList");
        pTag = (int)i.getSerializableExtra("pTag");
        aptTag = (int)i.getSerializableExtra("aptTag");
        vidTag = (int)i.getSerializableExtra("vidTag");
        prevPage = (String) i.getSerializableExtra("prevPage");

        configureVideoView();
    }

    private void configureVideoView() {

        videoView =
                findViewById(R.id.videoView1);

//       File storageDir = new File(
//                Environment.getExternalStoragePublicDirectory(
//                        Environment.DIRECTORY_DCIM
//                ),"/Camera");

       String temp = pList.get(pTag).appointments.get(aptTag).videos.get(vidTag);
       // videoView.setVideoPath("/Internal storage/DCIM/Camera/20180903_125256.mp4");

        videoView.setVideoPath(temp);
        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new
                                                MediaPlayer.OnPreparedListener() {
                                                    @Override
                                                    public void onPrepared(MediaPlayer mp) {
                                                        mp.setLooping(true);
                                                        Log.i(TAG, "Duration = " +
                                                                videoView.getDuration());
                                                    }
                                                });
        videoView.start();
    }


    public void done (View view) {
        Intent i = new Intent(VideoPlayerActivity.this, AppointmentActivity.class);

        i.putExtra("pList", (Serializable) pList);
        i.putExtra("pTag", pTag);
        i.putExtra("aptTag", aptTag);
        i.putExtra("prevPage", prevPage);
        //   i.putExtra("rowNum", );
        startActivity(i);
    }
}


//VideoView vid;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_player);
//
//        vid = (VideoView)findViewById(R.id.videoView1);
//
//    }
//
//    public void playVideo(View v) {
//        MediaController m = new MediaController(this);
//        vid.setMediaController(m);
//
//        String path = "E://DCIM//Camera//20180903_125256.mp4";
//
//        Uri u = Uri.parse(path);
//
//        vid.setVideoURI(u);
//
//        vid.start();
//
//    }
//}

   /*  public void accessFirebase(){

       /*    String url = "https://firebasestorage.googleapis.com/v0/b/facialpalsyevaluator.appspot.com/o/1234567896%2F1%2FVideos%2Freceived_207352770.mp4";

            Uri uri = Uri.parse(url);
            mStorageRef.getFile(uri);

            try {

                // File localFile = File.createTempFile("video", "mp4");

                mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        configureVideoView("http://www.ebookfrenzy.com/android_book/movie.mp4");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
            }
            catch (Exception e) {}




        File localFile = null;
        try {
            localFile = File.createTempFile("file", "mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }


        File storagePath = new File(Environment.getExternalStorageDirectory(), "https:///firebasestorage.googleapis.com//v0//b//facialpalsyevaluator.appspot.com//1234567896//1//Videos//received_207352770.mp4");
        //facialpalsyevaluator.appspot.com/o/
        if(!storagePath.exists()) {
            storagePath.mkdirs();
        }

        final File myFile = new File(storagePath,"received_207352770.mp4");

        StorageReference riversRef = mStorageRef.child("received_207352770.mp4");


        riversRef.getFile(myFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        Log.v("Download","Success");
                        configureVideoView("http://www.ebookfrenzy.com/android_book/movie.mp4");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                Log.v("Download","unSuccess");
                // ...
            }
        });


        }
*/




