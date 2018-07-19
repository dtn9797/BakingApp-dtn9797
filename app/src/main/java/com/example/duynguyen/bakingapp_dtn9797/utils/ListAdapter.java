package com.example.duynguyen.bakingapp_dtn9797.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duynguyen.bakingapp_dtn9797.R;

import java.util.ArrayList;

/**
 * Created by duynguyen on 7/19/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    Context mContext;
    ArrayList<String> mData;

    public ListAdapter(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu_vh,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.setData(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView shortDescriptionTv;

        public ListViewHolder(View itemView) {
            super(itemView);
            shortDescriptionTv = itemView.findViewById(R.id.short_description_tv);
        }

        public void setData (String data){
            shortDescriptionTv.setText(data);
        }
    }
}
