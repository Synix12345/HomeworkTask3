package com.example.homeworkTask3.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkTask3.activities.CatDetailActivity;
import com.example.homeworkTask3.R;
import com.example.homeworkTask3.model.Cats;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchViewHolder> {
    public List<Cats> breedList;

    public void setData(List<Cats> breedList){
        this.breedList = breedList;
    }
    @NonNull
    @Override
    public SearchAdapter.searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cats, parent, false);
        searchViewHolder searchViewHolder = new searchViewHolder(view);
        return searchViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.searchViewHolder holder, final int position) {
        final Cats breedPos = breedList.get(position);

        holder.textView.setText(breedPos.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("BreedID", breedList.get(position).getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return breedList.size();
    }
    public static class searchViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView textView;

        public searchViewHolder(@NonNull View v) {
            super(v);
            view = v;
            textView = v.findViewById(R.id.textView);
        }
    }
}
