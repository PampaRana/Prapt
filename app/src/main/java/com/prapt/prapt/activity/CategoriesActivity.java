package com.prapt.prapt.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.pogo.ItemView;
import com.prapt.prapt.adapter.CategoryAdapter;
import com.prapt.prapt.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoryAdapter itemViewAdapter;
    List<ItemView> itemViews;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        itemViews = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemViewAdapter);
    }


}
