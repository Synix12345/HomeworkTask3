package com.example.homeworkTask3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkTask3.activities.CatDetailActivity;
import com.example.homeworkTask3.adapters.FavouritesAdapter;
import com.example.homeworkTask3.R;
import com.example.homeworkTask3.model.CatPhotos;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<CatPhotos> favouritesList = CatDetailActivity.dList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = view.findViewById(R.id.favourites_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        FavouritesAdapter favouritesAdapter = new FavouritesAdapter();
        favouritesAdapter.setData(favouritesList);
        recyclerView.setAdapter(favouritesAdapter);
        return view;
    }

}
