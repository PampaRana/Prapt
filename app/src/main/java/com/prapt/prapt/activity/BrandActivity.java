package com.prapt.prapt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.adapter.BrandAdapter;
import com.prapt.prapt.apiCall.ApiClient;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.model.brand.BrandData;
import com.prapt.prapt.model.brand.BrandDataDetails;
import com.prapt.prapt.model.subCategory.SubCategoryData;
import com.prapt.prapt.model.subCategory.SubCategoryDataDetails;
import com.prapt.prapt.pogo.BrandSetGet;
import com.prapt.prapt.R;
import com.prapt.prapt.pogo.SubcatagoriessetGet;
import com.prapt.prapt.adapter.SubcategoriesAdapter;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.InternetCheck;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandActivity extends AppCompatActivity implements BrandAdapter.detailsListener{
    RecyclerView recyclerView, recyclerView1;
    TextView cardId;
    ArrayList<BrandDataDetails> brandList=new ArrayList<>();
    ArrayList<SubCategoryDataDetails> subCatList=new ArrayList<>();
    TextView tv_no_data1,tv_no_data2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_activity);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView1 = findViewById(R.id.recyclerView1);
        cardId=findViewById(R.id.cardId);
        tv_no_data1=findViewById(R.id.tv_no_data1);
        tv_no_data2=findViewById(R.id.tv_no_data2);

        cardId.setText(getIntent().getStringExtra("brand_name"));
        if (InternetCheck.isConnected(this)) {
            showHud();
            getBrandList(SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                    getIntent().getStringExtra("cat_id"));
            getSubCategoryList(SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                    getIntent().getStringExtra("cat_id"));

        } else {

            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
        /*brandSetGetList.add(
                new BrandSetGet(
                        "0",
                        R.drawable.surfexcels
                ));
        brandSetGetList.add(
                new BrandSetGet(
                        "1",
                        R.drawable.rin
                ));

        brandSetGetList.add(
                new BrandSetGet(
                        "2",
                        R.drawable.comfort
                ));
        brandSetGetList.add(
                new BrandSetGet(
                        "2",
                        R.drawable.wheel
                ));
        brandSetGetList.add(
                new BrandSetGet(
                        "3",
                        R.drawable.bsunlight
                ));*/

        /*BrandAdapter adapter = new BrandAdapter(getApplication(), brandSetGetList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(),
                LinearLayoutManager.HORIZONTAL, true));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

         subcatagoriessetGets = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
       subcatagoriessetGets.add(
                new SubcatagoriessetGet(
                        "1",
                        "Bars",
                        R.drawable.bars
                ));
        subcatagoriessetGets.add(
                new SubcatagoriessetGet(
                        "2",
                        "Bleach",
                        R.drawable.bleach
                ));
        subcatagoriessetGets.add(
                new SubcatagoriessetGet(
                        "3",
                        "Fabric Conditioner",
                        R.drawable.fabricconditioner
                ));
        subcatagoriessetGets.add(
                new SubcatagoriessetGet(
                        "4",
                        "Liquid",
                        R.drawable.liquid
                ));
        subcatagoriessetGets.add(
                new SubcatagoriessetGet(
                        "5",
                        "Matic Liquid",
                        R.drawable.maticliquid
                ));
        subcatagoriessetGets.add(
                new SubcatagoriessetGet(
                        "6",
                        "Matic Powder",
                        R.drawable.maticmaticpowder
                ));
        subcatagoriessetGets.add(
                new SubcatagoriessetGet(
                        "7",
                        "Powder",
                        R.drawable.powder
                ));*/

    }

    private void getSubCategoryList(String id, String cat_id) {
       // showHud();
        Call<SubCategoryData> call= ApiNewClient.getInstance(this).getSubCategoryList(
               id,cat_id
        );
        call.enqueue(new Callback<SubCategoryData>() {
            @Override
            public void onResponse(Call<SubCategoryData> call, Response<SubCategoryData> response) {
               hide();
                if (response.isSuccessful()){
                    subCatList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        if (response.body().getSubCategoryDetailsDataList().size()>0) {
                            tv_no_data2.setVisibility(View.GONE);
                            recyclerView1.setVisibility(View.VISIBLE);
                            for (int i = 0; i < response.body().getSubCategoryDetailsDataList().size(); i++) {
                                subCatList.add(response.body().getSubCategoryDetailsDataList().get(i));

                            }
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                            recyclerView1.setLayoutManager(layoutManager);
                            SubcategoriesAdapter adapter1 = new SubcategoriesAdapter(BrandActivity.this, subCatList);
                            recyclerView1.setAdapter(adapter1);
                        }else {
                            tv_no_data2.setVisibility(View.VISIBLE);
                            recyclerView1.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SubCategoryData> call, Throwable t) {

                hide();
                tv_no_data2.setVisibility(View.VISIBLE);
                recyclerView1.setVisibility(View.GONE);
            }
        });
    }

    private void getBrandList(String id, String cat_id) {
        //showHud();

        Call<BrandData> call= ApiNewClient.getInstance(this).getBrandList(
                id,cat_id
        );
        call.enqueue(new Callback<BrandData>() {
            @Override
            public void onResponse(Call<BrandData> call, Response<BrandData> response) {
                if (response.isSuccessful()){
                    hide();
                    brandList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        if (response.body().getBrandDetailsDataList().size()>0) {
                            tv_no_data1.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < response.body().getBrandDetailsDataList().size(); i++) {

                                brandList.add(response.body().getBrandDetailsDataList().get(i));
                            }
                            BrandAdapter adapter = new BrandAdapter(BrandActivity.this, brandList, BrandActivity.this::goToDetailsBrand);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(),
                                    LinearLayoutManager.HORIZONTAL, false));
                        }else {
                            tv_no_data1.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                        //PagerSnapHelper snapHelper = new PagerSnapHelper();
                       // snapHelper.attachToRecyclerView(recyclerView);
                    }
                }
            }

            @Override
            public void onFailure(Call<BrandData> call, Throwable t) {

                hide();
                tv_no_data1.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }

    KProgressHUD hud;

    void showHud() {
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    void hide() {
        hud.dismiss();
    }

    @Override
    public void goToDetailsBrand(String brand_id) {
        Intent intent=new Intent(new Intent(BrandActivity.this, ProductActivity.class));
        intent.putExtra("brand_id",brand_id);
        startActivity(intent);


    }
}
