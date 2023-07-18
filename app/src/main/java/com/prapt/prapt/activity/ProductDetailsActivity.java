package com.prapt.prapt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.pogo.ProductDetailsList;
import com.prapt.prapt.adapter.ProductDetailsListAdapter;
import com.prapt.prapt.R;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerViews;
    List<ProductDetailsList> productDetailsListList;
    ImageView arrow,arrow1,Img;
    NumberPicker number_picker;
    TextView addToCart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_activity);
        recyclerViews = (RecyclerView)findViewById(R.id.recyclerViews);
        arrow = (ImageView)findViewById(R.id.arrow);
        arrow1 = (ImageView)findViewById(R.id.arrow1);
        Img = (ImageView)findViewById(R.id.Img);
        number_picker = (NumberPicker)findViewById(R.id.number_picker);
        addToCart = (TextView) findViewById(R.id.addToCart);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_picker.setVisibility(View.VISIBLE);
                addToCart.setVisibility(View.GONE);

            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrow.setVisibility(View.GONE);
                arrow1.setVisibility(View.VISIBLE);
                recyclerViews.setVisibility(View.VISIBLE);
            }
        });
        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrow.setVisibility(View.VISIBLE);
                arrow1.setVisibility(View.GONE);

                recyclerViews.setVisibility(View.GONE);
            }
        });
        productDetailsListList = new ArrayList<>();
        productDetailsListList.add(
                new ProductDetailsList(
                        R.drawable.persent1,
                        "Get additional 1.5% off per unit"
                ));

        ProductDetailsListAdapter adapter = new ProductDetailsListAdapter(getApplication(), productDetailsListList);
        recyclerViews.setAdapter(adapter);
        recyclerViews.setLayoutManager(new LinearLayoutManager(getApplication(),
                LinearLayoutManager.VERTICAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViews);
    }
}
