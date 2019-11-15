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

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.BreedViewHolder> {
    private List<Cats> catsToAdapt;

    public void setData(List<Cats> catsToAdapt){
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cats, parent, false);
        BreedViewHolder breedViewHolder = new BreedViewHolder(view);
        return breedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        final Cats breedAtPosition = catsToAdapt.get(position);
        holder.name.setText(breedAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("BreedID", breedAtPosition.getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }
    public static class BreedViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView name;

        public BreedViewHolder(View v){
            super(v);
            view =v;
            name = v.findViewById(R.id.textView);
        }
    }

}
