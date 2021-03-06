package com.project.radioetzion.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.project.radioetzion.Model.JSONData;
import com.project.radioetzion.R;

public abstract class ProfileAdapter extends FirebaseRecyclerAdapter<JSONData, ProfileAdapter.ProfileViewHolder> {

    public ProfileAdapter(FirebaseRecyclerOptions<JSONData> options) {
        super(options);
    }

    public ProfileAdapter(Object options) {
        super((FirebaseRecyclerOptions<JSONData>) options);
    }

    protected abstract void onBindViewHolder(@NonNull ProfileViewHolder holder, int position, @NonNull JSONData model);


    public static class ProfileViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView txtStreamName;
        public TextView txtCreateDate;
        public TextView txtDuretion;
        public CardView cvListItem;
        public ImageView ivLikeUnfilled;
        public ImageView ivLikeFilled;
        public TextView tvLikeCounter;

        public ProfileViewHolder(View itemView){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgStream);
            txtStreamName = itemView.findViewById(R.id.txtStreamName);
            txtCreateDate = itemView.findViewById(R.id.txtCreateDate);
            txtDuretion = itemView.findViewById(R.id.txtDuretion);
            cvListItem = itemView.findViewById(R.id.cvListItem);
            ivLikeUnfilled = itemView.findViewById(R.id.ivLikeUnfilled);
            ivLikeFilled = itemView.findViewById(R.id.ivLikeFilled);
            tvLikeCounter = itemView.findViewById(R.id.tvLikeCounter);

        }


    }
}
