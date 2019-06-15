package com.shxy.paging;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shxy.paging.databinding.ItemLayoutBinding;

public class MyPagedAdapter extends PagedListAdapter<DataHolder, MyPagedAdapter.MyViewHolder> {

    static DiffUtil.ItemCallback<DataHolder> itemCallback = new DiffUtil.ItemCallback<DataHolder>() {
        @Override
        public boolean areItemsTheSame(@NonNull DataHolder dataHolder, @NonNull DataHolder t1) {
            return dataHolder == t1;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataHolder dataHolder, @NonNull DataHolder t1) {
            return dataHolder.equals(t1);
        }
    };

    protected MyPagedAdapter() {
        super(itemCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(
                viewGroup.getContext()), R.layout.item_layout, viewGroup, false);
        itemLayoutBinding.setData(getItem(i));
        return new MyViewHolder(itemLayoutBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.getBinding(myViewHolder.itemView);
        if (itemLayoutBinding != null) {
            itemLayoutBinding.setData(getItem(i));
            itemLayoutBinding.executePendingBindings();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
