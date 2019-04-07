package com.project.radioetzion.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.radioetzion.Activities.SplashActivity;
import com.project.radioetzion.Adapters.ProfileAdapter;
import com.project.radioetzion.Model.Likes;
import com.project.radioetzion.Utils.JSONHandler;
import com.project.radioetzion.Model.JSONData;

import com.project.radioetzion.R;

import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@SuppressWarnings("ALL")
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    ArrayList<JSONData> streamItems = new ArrayList<>();
     RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Calendar calendar = Calendar.getInstance();
    public DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private Object options;
    ProfileAdapter adapter;



    private FirebaseDatabase database;
    public ProgressDialog mDialog;
    private DatabaseReference likeRef;
    private String formattedDate2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.rvListData);

        mDialog = ProgressDialog.show(getContext(), "",
                "בטעינה אנא המתן...", true);

//        databaseReference = FirebaseDatabase.getInstance().getReference("dataSource");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("dataSource");
        mAuth = FirebaseAuth.getInstance();
        likeRef = database.getReference("likes").child(mAuth.getUid());


        options = new FirebaseRecyclerOptions.Builder<JSONData>()
                .setQuery(databaseReference, JSONData.class).build();

        adapter = new ProfileAdapter(options){

            @NonNull
            @Override
            public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.recycler_item, viewGroup, false);

                return new ProfileViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ProfileViewHolder holder, int position, @NonNull final JSONData model) {
                if (model.getFilePath() != null){
                    mDialog.dismiss();
                    //test
                    holder.txtStreamName.setText(model.getVodName().replace("_"," ").replace(".mp4", " "));

                    final double profileCreationDate = model.getCreationDate();
                    Date date = new java.util.Date((long) (model.getCreationDate()));
                    SimpleDateFormat sdg = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                    SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");

                    // give a timezone reference for formatting (see comment at the bottom)
                    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
                    final String formattedDate = sdf.format(date);

                    sdg.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
                    formattedDate2 = sdg.format(date);

                    holder.txtCreateDate.setText(formattedDate);

                    holder.txtDuretion.setText(model.getDuration());

                    holder.cvListItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("key", model.getFilePath());
                            nowPlayingFragment.setArguments(bundle);
                            Log.e("shimi", "onItemClick: " + "hi1" );

                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.framagment_container, nowPlayingFragment)
                                    .commit();


                        }
                    });
//
//                    likeRef.child(formattedDate2).addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            dataSnapshot.getValue()
//                            Likes post = dataSnapshot.getValue(Likes.class);
//                            String likes = post.g;
//                            Log.d("likes", likes);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });

                    holder.ivLikeUnfilled.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Likes l = new Likes("true");
                            holder.ivLikeUnfilled.setVisibility(View.INVISIBLE);
                            holder.ivLikeFilled.setVisibility(View.VISIBLE);
                            likeRef.push().child(formattedDate2).setValue(l);

                        }
                    });
                    holder.ivLikeFilled.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Likes l = new Likes("false");
                            holder.ivLikeFilled.setVisibility(View.INVISIBLE);
                            holder.ivLikeUnfilled.setVisibility(View.VISIBLE);
                            likeRef.push().child(formattedDate2).setValue(l);

                        }
                    });


                }else{
                    mDialog.dismiss();
                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("סיום").
                            setMessage("אין חיבור רשת, אנא וודא שהנך מחובר לרשת לאחר מכן לחץ על התחבר  מחדש")
                            .setCancelable(true)
                            .setPositiveButton("התחבר מחדש", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(getContext(), SplashActivity.class);
                                        startActivity(i);

                                }
                            })
                            .show();
                }

            }
        };


        setPointer();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);


  /*
        // on item click listener pass the url of mp4 to streamFragment.
        mAdapter = new StreamAdapter(streamItems, new StreamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(JSONData item) {
                ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                        "Loading. Please wait...", true);

                StreamFragment streamFragment = new StreamFragment();
                bundle.putString("key", item.getFilePath());
                streamFragment.setArguments(bundle);
                Log.e(TAG, "onItemClick: " + "hi1" );
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framagment_container, streamFragment)
                        .commit();

            }
        });
*/

   /*
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
        Log.e(TAG, "setPointer: " + "hiwwww" );
*/

        //send JSONData to Firebase:



     return view;

    }


    private void setPointer() {
        Log.e(TAG, "setPointer: " + "hi" );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://be.repoai.com:5080/WebRTCAppEE/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONHandler jsonHandler = retrofit.create(JSONHandler.class);

        final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // network working
        } else {
            Toast.makeText(getContext(), "Network is down", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onResponse: "  +"Network is down");

        }

//
//
//        Call<List<JSONData>> call = jsonHandler.getJson();
//        call.enqueue(new Callback<List<JSONData>>() {
//            @Override
//            public void onResponse(Call<List<JSONData>> call, Response<List<JSONData>> response) {
//
//
//                for (int i = 0; i < response.body().size(); i++){
//                    calendar.setTimeInMillis((long) response.body().get(i).getCreationDate());
//                    String currentDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) + "/"+ calendar.get(Calendar.MONTH) +"/"+ calendar.get(Calendar.YEAR));
//
//
//                    streamItems.add(new JSONData(
//                            Double.parseDouble(response.body().get(i).getStreamName()),
//                            Double.parseDouble(response.body().get(i).getVodName().replace("_"," ").replace(".mp4", " ")),
//                            response.body().get(i).getStreamId(),
//                            Double.parseDouble(response.body().get(i).getFilePath()),
//                            String.valueOf(response.body().get(i).getVodId()),
//                            response.body().get(i).getType(),
//                            currentDate,
//                            response.body().get(i).getDuration(),
//                            String.valueOf(response.body().get(i).getFileSize())));
//                    Log.e(TAG, "response.body().get(i).getVodName(): " +  response.body().get(i).getVodName());
//
//                }
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<JSONData>> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//            }
//        });
    }


}

