package com.project.radioetzion.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.radioetzion.Fragments.CommentsFragment;
import com.project.radioetzion.Fragments.HomeFragment;
import com.project.radioetzion.R;
import com.project.radioetzion.Fragments.NowPlayingFragment;
import com.project.radioetzion.Fragments.PlaylistFragment;

public class BottomActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment =null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = new NowPlayingFragment();
                    break;
                case R.id.navigation_playlist:
                    selectedFragment = new PlaylistFragment();
                        break;
                case R.id.navigation_comments:
                    selectedFragment = new CommentsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.framagment_container, selectedFragment).commit();

            return true;
        }
    };
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);





        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.framagment_container, new HomeFragment()).commit();

    }



}
