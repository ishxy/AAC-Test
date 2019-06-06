package com.example.youcanrunsuccessfully;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youcanrunsuccessfully.databinding.ItemLayoutBinding;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private List<TestBean> mList;

    public TestAdapter(List<TestBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_layout, viewGroup, false);
        return new TestViewHolder(itemLayoutBinding.getRoot());
    }
    @Override
    public void onBindViewHolder(@NonNull TestViewHolder testViewHolder, int i) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.getBinding(testViewHolder.itemView);
        itemLayoutBinding.setBean(mList.get(i));
        itemLayoutBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class TestViewHolder extends RecyclerView.ViewHolder {
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
