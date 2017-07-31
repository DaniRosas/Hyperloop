package com.hyperloop.uploadapp.upload.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyperloop.login.R;
import com.hyperloop.uploadapp.upload.Category;
import com.hyperloop.uploadapp.upload.listeners.OnItemClickCategoryListener;

import java.util.List;


/**
 * Created by DaniRosas on 31/7/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.myViewHolder>{

    private Context mContext;
    private List<Category> categoriesList;
    private OnItemClickCategoryListener listener;
    private SparseBooleanArray selectedItems;
    private int position;


    public CategoriesAdapter(Context mContext, List<Category> categoriesList, OnItemClickCategoryListener listener) {
        this.mContext = mContext;
        this.categoriesList = categoriesList;
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
        position = 0;
        selectedItems.put(position, true);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        Category category = categoriesList.get(position);

        holder.tv.setText(category.getCategory());
        holder.setOnItemClickListener(category, listener, position);

        holder.itemView.setSelected(selectedItems.get(position, false));

        holder.setOnItemClickListener(category, listener, position);

        if(category.isSelected()){
            holder.itemView.setSelected(true);


        }else{
            holder.itemView.setSelected(false);
        }

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        //View the name of the category
        private TextView tv;

        public myViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.textViewCategory);
        }

        public void setOnItemClickListener(final Category category, final OnItemClickCategoryListener onItemClickCategoryListener, final int pos) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCategoryListener.onClickCategory(category);
                    if (selectedItems.get(pos, true)) {

                        selectedItems.delete(position);
                        categoriesList.get(position).setSelected(false);
                        categoriesList.set(position, categoriesList.get(position));

                        selectedItems.put(pos, true);
                        category.setSelected(true);
                        categoriesList.set(pos, category);

                        position = pos;
                    }
                    notifyDataSetChanged();

                }
            });
        }
    }


}
