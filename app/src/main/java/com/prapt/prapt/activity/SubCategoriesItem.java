package com.prapt.prapt.activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.R;
import com.prapt.prapt.adapter.SubCategoriesItemAdapter;
import com.prapt.prapt.pogo.SubcatagoriesViewSetGet;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesItem extends AppCompatActivity {
    RecyclerView recyclerView;
    List<SubcatagoriesViewSetGet> subcatagoriesViewSetGetList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_catagories_item);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        subcatagoriesViewSetGetList = new ArrayList<>();
        subcatagoriesViewSetGetList.add(
                new SubcatagoriesViewSetGet(
                        "13.6%",
                        "(9.0)",
                        "margin for 601 unit",
                        "Kissan Ketchup Fresh",
                        "Tomato Chotu 90g     ",
                        "10",
                        "8.8",
                        "14.0%",
                        R.drawable.sunlight
                ));

        subcatagoriesViewSetGetList.add(
                new SubcatagoriesViewSetGet(
                        "13.6%",
                        "(9.0)",
                        "margin for 601 unit",
                        "Kissan Ketchup Fresh",
                        "Tomato Chotu 90g     ",
                        "10",
                        "8.8",
                        "14.0%",
                        R.drawable.sunlight
                ));

        subcatagoriesViewSetGetList.add(
                new SubcatagoriesViewSetGet(
                        "13.6%",
                        "(9.0)",
                        "margin for 601 unit",
                        "Kissan Ketchup Fresh",
                        "Tomato Chotu 90g     ",
                        "10",
                        "8.8",
                        "14.0%",
                        R.drawable.sunlight
                ));

        subcatagoriesViewSetGetList.add(
                new SubcatagoriesViewSetGet(
                        "13.6%",
                        "(9.0)",
                        "margin for 601 unit",
                        "Kissan Ketchup Fresh",
                        "Tomato Chotu 90g     ",
                        "10",
                        "8.8",
                        "14.0%",
                        R.drawable.sunlight
                ));

        subcatagoriesViewSetGetList.add(
                new SubcatagoriesViewSetGet(
                        "13.6%",
                        "(9.0)",
                        "margin for 601 unit",
                        "Kissan Ketchup Fresh",
                        "Tomato Chotu 90g     ",
                        "10",
                        "8.8",
                        "14.0%",
                        R.drawable.sunlight
                ));
        subcatagoriesViewSetGetList.add(
                new SubcatagoriesViewSetGet(
                        "13.6%",
                        "(9.0)",
                        "margin for 601 unit",
                        "Kissan Ketchup Fresh",
                        "Tomato Chotu 90g     ",
                        "10",
                        "8.8",
                        "14.0%",
                        R.drawable.sunlight
                ));

        SubCategoriesItemAdapter adapter = new SubCategoriesItemAdapter(getApplication(), subcatagoriesViewSetGetList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(),
                LinearLayoutManager.VERTICAL, true));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }
}
