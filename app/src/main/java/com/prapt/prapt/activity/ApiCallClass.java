package com.prapt.prapt.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prapt.prapt.R;
import com.prapt.prapt.apiCall.ApiClient;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.model.SuccessData;
import com.prapt.prapt.model.brand.BrandData;
import com.prapt.prapt.model.brand.BrandDataDetails;
import com.prapt.prapt.model.cart.CartData;
import com.prapt.prapt.model.cart.CartDataDetails;
import com.prapt.prapt.model.product.ProductData;
import com.prapt.prapt.model.product.ProductDataDetails;
import com.prapt.prapt.model.subCategory.SubCategoryData;
import com.prapt.prapt.model.subCategory.SubCategoryDataDetails;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallClass extends AppCompatActivity {
    ArrayList<ProductDataDetails> brandWisePdtList=new ArrayList<>();
    ArrayList<CartDataDetails> cartList=new ArrayList<>();

    ArrayList<ProductDataDetails> pdtList=new ArrayList<>();
    ArrayList<BrandDataDetails> brandList=new ArrayList<>();
    ArrayList<SubCategoryDataDetails> subCatList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_activity);
        getCheckPinCode();
       // getSubCategory();
        //getBrand();
        //getBrandWisePdt();
        getProduct();
        //addToCartItem();
        //deleteCartItem();
        //getCartPdtLIst();
    }

    private void getCheckPinCode() {
        Call<SuccessData> call=ApiClient.getInstance().getCheckPinCode(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                ""
        );
        call.enqueue(new Callback<SuccessData>() {
            @Override
            public void onResponse(Call<SuccessData> call, Response<SuccessData> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("true")){

                    }else {

                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessData> call, Throwable t) {

            }
        });
    }
    private void addToCartItem() {
        Call<SuccessData> call=ApiNewClient.getInstance(this).getAddToCart(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                "",""
        );
        call.enqueue(new Callback<SuccessData>() {
            @Override
            public void onResponse(Call<SuccessData> call, Response<SuccessData> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("true")){

                    }else {

                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessData> call, Throwable t) {

            }
        });

    }

    private void getBrandWisePdt() {
        Call<ProductData> call= ApiNewClient.getInstance(this).getBrandWisePdtList(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID), ""
        );
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {

                if (response.isSuccessful()){
                    brandWisePdtList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        for (int i=0;i<response.body().getProductDetailsDataList().size(); i++){
                            brandWisePdtList.add(response.body().getProductDetailsDataList().get(i));

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {

            }
        });
    }

    private void getProduct() {
        Call<ProductData> call= ApiClient.getInstance().getProductList(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                "","",""
        );
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {

                if (response.isSuccessful()){
                    pdtList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        for (int i=0;i<response.body().getProductDetailsDataList().size(); i++){
                            pdtList.add(response.body().getProductDetailsDataList().get(i));

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {

            }
        });
    }

    /*private void getCartPdtLIst() {
        Call<CartData> call= ApiClient.getInstance().getCartList(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                SharedPreferencesClass.retrieveData(this,Config.LOGIN_TOKEN)

        );
        call.enqueue(new Callback<CartData>() {
            @Override
            public void onResponse(Call<CartData> call, Response<CartData> response) {

                if (response.isSuccessful()){
                    cartList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        for (int i=0;i<response.body().getCartDetailsDataList().size(); i++){
                            cartList.add(response.body().getCartDetailsDataList().get(i));

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<CartData> call, Throwable t) {

            }
        });
    }

    private void deleteCartItem() {
        Call<SuccessData> call=ApiClient.getInstance().getDeleteCart(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                SharedPreferencesClass.retrieveData(this,Config.LOGIN_TOKEN),
                ""
        );
        call.enqueue(new Callback<SuccessData>() {
            @Override
            public void onResponse(Call<SuccessData> call, Response<SuccessData> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("true")){

                    }else {

                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessData> call, Throwable t) {

            }
        });
    }*/



    /*private void getBrand() {
        Call<BrandData> call= ApiClient.getInstance().getBrandList(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID)
        );
        call.enqueue(new Callback<BrandData>() {
            @Override
            public void onResponse(Call<BrandData> call, Response<BrandData> response) {
                if (response.isSuccessful()){
                    brandList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        for (int i=0;i<response.body().getBrandDetailsDataList().size(); i++){

                            brandList.add(response.body().getBrandDetailsDataList().get(i));
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<BrandData> call, Throwable t) {

            }
        });
    }

    private void getSubCategory() {
        Call<SubCategoryData> call= ApiNewClient.getInstance(this).getSubCategoryList(
                SharedPreferencesClass.retrieveData(this, Config.USER_ID),
                ""
        );
        call.enqueue(new Callback<SubCategoryData>() {
            @Override
            public void onResponse(Call<SubCategoryData> call, Response<SubCategoryData> response) {
                if (response.isSuccessful()){
                    subCatList.clear();
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        for (int i=0;i<response.body().getSubCategoryDetailsDataList().size(); i++){
                            subCatList.add(response.body().getSubCategoryDetailsDataList().get(i));

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<SubCategoryData> call, Throwable t) {

            }
        });
    }*/
}
