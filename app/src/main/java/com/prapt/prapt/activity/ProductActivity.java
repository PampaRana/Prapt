package com.prapt.prapt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.R;
import com.prapt.prapt.adapter.BrandAdapter;
import com.prapt.prapt.adapter.ProductDetailsListAdapter;
import com.prapt.prapt.adapter.ProductListAdapter;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.model.product.ProductData;
import com.prapt.prapt.model.product.ProductDataDetails;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.InternetCheck;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ProductDataDetails> brandWisePdtList=new ArrayList<>();
    TextView tv_no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wishlist);
        recyclerView=findViewById(R.id.recyclerView);
        tv_no_data=findViewById(R.id.tv_no_data);
        if (InternetCheck.isConnected(this)) {
            showHud();
            getDetailsProduct(SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                    getIntent().getStringExtra("brand_id"));

        } else {

            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    private void getDetailsProduct(String user_id, String brand_id) {

        Call<ProductData> call= ApiNewClient.getInstance(this).getBrandWisePdtList(
                user_id, brand_id
        );
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {

                hide();
                if (response.isSuccessful()){
                    brandWisePdtList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){

                        if (response.body().getProductDetailsDataList().size()>0) {
                            tv_no_data.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < response.body().getProductDetailsDataList().size(); i++) {
                                brandWisePdtList.add(response.body().getProductDetailsDataList().get(i));


                            }
                            ProductListAdapter adapter = new ProductListAdapter(ProductActivity.this, brandWisePdtList);
                            recyclerView.setAdapter(adapter);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                            recyclerView.setLayoutManager(layoutManager);
                        }else {
                            tv_no_data.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    }else {
                        tv_no_data.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {

                hide();
                tv_no_data.setVisibility(View.VISIBLE);
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
}