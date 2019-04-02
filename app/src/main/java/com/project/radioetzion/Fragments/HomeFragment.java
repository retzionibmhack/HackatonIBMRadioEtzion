package com.project.radioetzion.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.project.radioetzion.Utils.JSONHandler;
import com.project.radioetzion.Model.JSONData;

import com.project.radioetzion.R;
import com.project.radioetzion.Adapters.StreamAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    ArrayList<JSONData> streamItems = new ArrayList<>();
     RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Calendar calendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        setPointer();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        final Bundle bundle = new Bundle();

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



        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
        Log.e(TAG, "setPointer: " + "hiwwww" );
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



        Call<List<JSONData>> call = jsonHandler.getJson();
        call.enqueue(new Callback<List<JSONData>>() {
            @Override
            public void onResponse(Call<List<JSONData>> call, Response<List<JSONData>> response) {


                for (int i = 0; i < response.body().size(); i++){
                    calendar.setTimeInMillis(Long.parseLong(response.body().get(i).getCreationDate()));
                    String currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/"+ calendar.get(Calendar.MONTH) +"/"+ calendar.get(Calendar.YEAR);

                    streamItems.add(new JSONData(response.body().get(i).getStreamName(),
                            response.body().get(i).getVodName().replace("_"," ").replace(".mp4", " "),
                            response.body().get(i).getStreamId(),
                            response.body().get(i).getFilePath(),
                            response.body().get(i).getVodId(),
                            response.body().get(i).getType(),
                            currentDate,
                            response.body().get(i).getDuration(),
                            response.body().get(i).getFileSize()));
                    Log.e(TAG, "response.body().get(i).getVodName(): " +  response.body().get(i).getVodName());

                }
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<JSONData>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
