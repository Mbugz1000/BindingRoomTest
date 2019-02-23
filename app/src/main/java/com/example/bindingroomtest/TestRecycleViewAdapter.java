package com.example.bindingroomtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.bindingroomtest.databinding.TestRecyclerItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TestRecycleViewAdapter extends RecyclerView.Adapter<TestRecycleViewAdapter.TestViewHolder> implements BindableAdapter<List<TestEntity>> {

    private final LayoutInflater layoutInflater;
    private List<TestEntity> entityList;
    
    private static final String TAG = "TestRecycleViewAdapter";

    public TestRecycleViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<TestEntity> setEntityList){
//        if (entityList != null) {
        Log.i(TAG, "setList: Set list called!!");
        entityList = setEntityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TestRecyclerItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.test_recycler_item, parent, false);
        return new TestViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecycleViewAdapter.TestViewHolder holder, int position) {
        holder.itemBinding.setItemEntity(entityList.get(position));
//            holder.itemBinding.entityItem.setText(entityList.get(position).getAnything());

        Log.i(TAG, "onBindViewHolder: Binding to View holder done!!" + position);
    }

    @Override
    public int getItemCount() {
        if (entityList != null)
            return entityList.size();
        else return 0;
    }

    class TestViewHolder extends RecyclerView.ViewHolder{
        private final TestRecyclerItemBinding itemBinding;

        public TestViewHolder(@NonNull TestRecyclerItemBinding itemBinding) {
            super(itemBinding.getRoot());
            itemBinding.executePendingBindings();
            this.itemBinding = itemBinding;
        }
    }

}
