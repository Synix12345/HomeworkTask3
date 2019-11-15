package com.example.homeworkTask3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkTask3.R;
import com.example.homeworkTask3.model.CatPhotos;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavAdapterViewHolder> {
    private static ArrayList<CatPhotos> breedsList = new ArrayList<>();

    public void setData(ArrayList<CatPhotos> breedsList){
        this.breedsList = breedsList;
    }

    @NonNull
    @Override
    public FavAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cats, parent, false);
        FavAdapterViewHolder favAdapterViewHolder = new FavAdapterViewHolder(view);
        return favAdapterViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapterViewHolder holder, int position) {
        final String cat = breedsList.get(position).getBreeds().get(0).getId();
        if (breedsList.isEmpty()) {
            holder.text.setText("You currently have no favourites :(");
        }
        else {
            holder.text.setText(breedsList.get(position).getBreeds().get(0).getName());
        }
    }

    @Override
    public int getItemCount() {
        return breedsList.size();

    }
    public class FavAdapterViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView text;

        public FavAdapterViewHolder(@NonNull View v){
            super(v);
            view = v;
            text = view.findViewById(R.id.textView);
        }
    }
}
