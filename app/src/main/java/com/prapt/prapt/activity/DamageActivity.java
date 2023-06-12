package com.prapt.prapt.activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.apiCall.ApiClient;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.model.LoginData;
import com.prapt.prapt.model.category.CategoryData;
import com.prapt.prapt.model.category.CategoryDataDetails;
import com.prapt.prapt.pogo.DamageSetGetview;
import com.prapt.prapt.adapter.DamageViewAdapter;
import com.prapt.prapt.R;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DamageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DamageSetGetview> damageSetGetviewList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.damage_activity);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        damageSetGetviewList = new ArrayList<>();
        damageSetGetviewList.add(
                new DamageSetGetview(
                        "1",
                        "Sunlight Eb Powder 500g",
                        ": Damage",
                        ": 5 Unit",
                        ": 0 Unit",
                        R.drawable.sunlight,
                        "In Progress"
                ));
        damageSetGetviewList.add(
                new DamageSetGetview(
                        "2",
                        "Sunlight Eb Powder 500g",
                        ": Damage",
                        ": 5 Unit",
                        ": 0 Unit",
                        R.drawable.sunlight,
                        "In Progress"
                ));
        DamageViewAdapter adapter = new DamageViewAdapter(getApplication(), damageSetGetviewList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(),
                LinearLayoutManager.VERTICAL, true));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private void getLoginInfo(String user_id, String token) {
        ArrayList<CategoryDataDetails> catList = new ArrayList<>();

        Call<CategoryData> call= ApiNewClient.getInstance(this).getCategoryList(user_id);
        call.enqueue(new Callback<CategoryData>() {
            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {

                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){

                        catList.clear();
                        for (int i=0; i<response.body().getCategoryDetailsDataList().size();i++){
                            catList.add(response.body().getCategoryDetailsDataList().get(i));
                        }
                        showToastMessage(response.body().getMessage());
                    } else {
                        showToastMessage(response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {

            }
        });

    }
    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
