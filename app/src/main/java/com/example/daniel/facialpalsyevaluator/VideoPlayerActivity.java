package com.example.daniel.facialpalsyevaluator;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import daniel.example.com.facialpalsyevaluator.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    String TAG = "VideoPlayer";
     StorageReference mStorageRef;
   // private FirebaseAuth mAuth;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        AssetManager mngr = getAssets();
        try {
        //    InputStream is = mngr.open("assets\\video_alfie.mp4");
            configureVideoView("C:\\\\Users\\Daniel\\AndroidStudioProjects\\FacialPalsyEvaluator\\app\\src\\main\\assets\\video_alfie.mp4");
           // configureVideoView("video_alfie.mp4");

        }
        catch (Exception e){}
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//        mAuth = FirebaseAuth.getInstance();
//
//        mAuth.signInAnonymously()
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInAnonymously:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            accessFirebase();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInAnonymously:failure", task.getException());
//                            Toast.makeText(VideoPlayerActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                });



    }

    private void configureVideoView(String path) {

        videoView =
                findViewById(R.id.videoView1);

        videoView.setVideoPath(path);

        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new
                                                MediaPlayer.OnPreparedListener()  {
                                                    @Override
                                                    public void onPrepared(MediaPlayer mp) {
                                                        mp.setLooping(true);
                                                        Log.i(TAG, "Duration = " +
                                                                videoView.getDuration());
                                                    }
                                                });
        videoView.start();
    }

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



    }

