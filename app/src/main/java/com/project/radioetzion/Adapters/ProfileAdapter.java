package com.project.radioetzion.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.project.radioetzion.Model.JSONData;
import com.project.radioetzion.Model.StreamItems;
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
        public TextView mTextView2;
        public TextView mTextView3;

        public ProfileViewHolder(View itemView){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgStream);
            txtStreamName = itemView.findViewById(R.id.txtStreamName);
            mTextView2 = itemView.findViewById(R.id.txtCreate);
            mTextView3 = itemView.findViewById(R.id.txtDuretion);
        }


    }
}
