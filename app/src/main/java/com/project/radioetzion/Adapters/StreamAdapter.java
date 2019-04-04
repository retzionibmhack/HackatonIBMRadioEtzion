package com.project.radioetzion.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.radioetzion.Model.JSONData;
import com.project.radioetzion.R;

import java.util.ArrayList;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamViewHolder> {

    private ArrayList<JSONData> mData;
    private OnItemClickListener listener;


    public StreamAdapter(ArrayList<JSONData> streamList, OnItemClickListener listener) {
        mData = streamList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public StreamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        StreamViewHolder streamViewHolder = new StreamViewHolder(v);
        return streamViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StreamViewHolder streamViewHolder, int i) {
        JSONData currentItem = mData.get(i);
        streamViewHolder.mImageView.setImageResource(R.drawable.musicicon);
        streamViewHolder.mTextView1.setText(currentItem.getVodName());
        streamViewHolder.mTextView2.setText(""+currentItem.getCreationDate());
        streamViewHolder.mTextView3.setText(""+Double.toString(currentItem.getDuration()));

        streamViewHolder.bind(mData.get(i), listener);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(JSONData item);
    }


    public static class StreamViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;


        public StreamViewHolder(View itemView){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgStream);
            mTextView1 = itemView.findViewById(R.id.txtStreamName);
            mTextView2 = itemView.findViewById(R.id.txtCreate);
            mTextView3 = itemView.findViewById(R.id.txtDuretion);

        }
        public void bind(final JSONData item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
