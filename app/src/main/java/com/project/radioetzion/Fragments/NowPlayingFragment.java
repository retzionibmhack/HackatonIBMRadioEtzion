package com.project.radioetzion.Fragments;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.radioetzion.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.project.radioetzion.Fragments.HomeFragment.mNowPlayingDialog;


public class NowPlayingFragment extends Fragment {
    private static final String TAG = "StreamFragment";
    private Button btnStart, btnForward, btnBack;
    private MediaPlayer mPlayer;

    private double startTime = 0;
    private double finalTime = 0;
    private String streamURL;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView txtCurrentTime,txtTime;

    public static int oneTimeOnly = 0;
    private String filePath;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_player, container, false);
        btnStart = view.findViewById(R.id.btnStart);
        btnForward = view.findViewById(R.id.btnForward);
        btnBack = view.findViewById(R.id.btnBack);

        txtCurrentTime = view.findViewById(R.id.txtCurrentTime);
        txtTime = view.findViewById(R.id.txtTime);

        mNowPlayingDialog.dismiss();

        seekbar = view.findViewById(R.id.seekBar);
        Bundle bundle =  this.getArguments();

        streamURL = (String) bundle.get("key");
        setPointer();
        return view;

    }



    private void setPointer() {
//        mediaPlayer();
        mediaPlayer2();
    }

    private void mediaPlayer2() {
        mPlayer = new MediaPlayer();
//        Bundle bundle = this.getArguments();
//        String url = null;
//        if (bundle != null) {
            Log.e(TAG, "mediaPlayer2: "+" hi2" );
            String url = "http://be.repoai.com:5080/WebRTCAppEE/" + streamURL;
//            Log.e(TAG, "mediaPlayer: " + bundle.get("key"));
//        }

        try {
            mPlayer.setDataSource(url);

        } catch (IllegalArgumentException e) {
            Toast.makeText(getContext(), getString(R.string.wrongURI), Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getContext(),  getString(R.string.wrongURI), Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getContext(),  getString(R.string.wrongURI), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            Log.e(TAG, "mediaPlayer2: " + e.getLocalizedMessage());
        }
//        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mPlayer.prepare();
        } catch (IllegalStateException e) {
            Toast.makeText(getContext(),  getString(R.string.wrongURI), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getContext(),  getString(R.string.wrongURI), Toast.LENGTH_LONG).show();
        }
        finalTime = mPlayer.getDuration();
        startTime = mPlayer.getCurrentPosition();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finalTime = mPlayer.getDuration();
//                startTime = mPlayer.getCurrentPosition();
                if(mPlayer.isPlaying()){
                    mPlayer.pause();
                    btnStart.setBackgroundResource(R.drawable.play);

                }else{
                    Log.e(TAG, "onClick: " + finalTime +" "+ startTime );
                    if (oneTimeOnly == 0) {
                        seekbar.setMax((int) finalTime);
                        oneTimeOnly = 1;
                    }

                    txtTime.setText(String.format("%d:%d",
                            TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                            finalTime)))
                    );

                    txtCurrentTime.setText(String.format("%d:%d",
                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                            startTime)))
                    );

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        seekbar.setProgress((int)startTime, true);
                    }else{
                        seekbar.setProgress((int)startTime);
                    }
                    myHandler.postDelayed(UpdateSongTime,100);

                    mPlayer.start();
                    btnStart.setBackgroundResource(R.drawable.pause);
                }

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.seekTo(seekBar.getProgress());
                mPlayer.start();
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mPlayer.seekTo((int) startTime);
                    Toast.makeText(getContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mPlayer.seekTo((int) startTime);
                    Toast.makeText(getContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mPlayer.getCurrentPosition();
            txtCurrentTime.setText(String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

}
