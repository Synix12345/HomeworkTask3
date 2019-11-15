package com.example.homeworkTask3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.homeworkTask3.R;
import com.example.homeworkTask3.fragments.CatSearchFragment;
import com.example.homeworkTask3.fragments.FavouritesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Fragment fragment = new CatSearchFragment();
        final Fragment fragment2 = new FavouritesFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_favourites) {
                    swapFragment(fragment2);
                    return true;
                }
                else if (menuItem.getItemId() == R.id.nav_search){
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

        searchView = findViewById(R.id.searchbar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sendMessage(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }
        });
    }


    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
    private void sendMessage(CharSequence query){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }


}
