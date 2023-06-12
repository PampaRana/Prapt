package com.prapt.prapt.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.apiCall.ApiClient;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.model.SuccessData;
import com.prapt.prapt.model.cart.CartDataDetails;
import com.prapt.prapt.pogo.MycartsetGet;
import com.prapt.prapt.R;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
        private Context mCtx;
        private ArrayList<CartDataDetails> mycartsetGetList;
        int listview;
        int minteger = 0;
        public MyCartAdapter(Context mCtx, ArrayList<CartDataDetails> mycartsetGetList) {
            this.mCtx = mCtx;
            this.mycartsetGetList = mycartsetGetList;
        }
        @Override
        public MyCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_list, parent, false);
            return new MyCartAdapter.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final MyCartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            CartDataDetails mycartsetGet = mycartsetGetList.get(position);
            listview = position;
            holder.cartName.setText(String.valueOf(mycartsetGet.getProduct_name()));
            //holder.cartGram.setText(String.valueOf(mycartsetGet.getCartGram()));
            holder.cartPrice.setText(String.valueOf(mycartsetGet.getActual_price()));
            holder.discountText.setText(String.valueOf(mycartsetGet.getAfter_discount_price()));
            holder.qty.setText(String.valueOf(mycartsetGet.getQuantity()));
            holder.decreaseInteger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    minteger = minteger - 1;
//                    display(minteger,holder.qty,position);
                    int currentNos = Integer.parseInt(holder.qty.getText().toString()) ;
                    holder.qty.setText(String.valueOf(--currentNos));
                }
            });
            holder.increaseInteger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    minteger = minteger + 1;
//                    display(minteger,holder.qty,position);
                    int currentNos = Integer.parseInt(holder.qty.getText().toString()) ;
                    holder.qty.setText(String.valueOf(++currentNos));
//                    mCallback.onClick(quantity.getText().toString());
                }


            });
            Glide.with(mCtx)
                    .load(mycartsetGet.getDfile1())
                    .into(holder.wish_Image);

            holder.deleteId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showHud();
                    getDeleteCart(
                            position,
                            SharedPreferencesClass.retrieveData(mCtx, Config.USER_ID),
                            SharedPreferencesClass.retrieveData(mCtx,Config.LOGIN_TOKEN),
                            mycartsetGet.getProduct_id()
                    );
                }
            });

        }
    KProgressHUD hud;

    void showHud() {
        hud = KProgressHUD.create(mCtx)
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
    private void getDeleteCart(int position,String user_id,
                               String token,
                               String product_id) {
        Call<SuccessData> call= ApiNewClient.getInstance(mCtx).getDeleteCart(
                user_id,product_id
        );
        call.enqueue(new Callback<SuccessData>() {
            @Override
            public void onResponse(Call<SuccessData> call, Response<SuccessData> response) {
               hide();
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("true")){
                        mycartsetGetList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mycartsetGetList.size());
                        showToastMessage(response.body().getMessage());
                    }else {
                        showToastMessage(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessData> call, Throwable t) {
                hide();
            }
        });

    }
    private void showToastMessage(String message) {
        Toast.makeText(mCtx, message, Toast.LENGTH_LONG).show();
    }
    @Override
        public int getItemCount() {
            return mycartsetGetList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cartName,cartGram,cartPrice,discountText,decreaseInteger,increaseInteger,qty;
            private ImageView wish_Image,deleteId;
            public ViewHolder(View view) {
                super(view);
                cartName = (TextView) view.findViewById(R.id.cartName);
                cartGram = (TextView) view.findViewById(R.id.cartGram);
                cartPrice = (TextView) view.findViewById(R.id.cartPrice);
                discountText = (TextView) view.findViewById(R.id.discountText);
                wish_Image = (ImageView) view.findViewById(R.id.wish_Image);
                deleteId=view.findViewById(R.id.deleteId);

                decreaseInteger = (TextView) view.findViewById(R.id.decreaseInteger);
                increaseInteger = (TextView) view.findViewById(R.id.increaseInteger);
                qty = (TextView) view.findViewById(R.id.qty);
            }
        }
       private void display(int number,TextView qty,int position) {
            System.out.println("ListView"+position);

            if (position==0){
                qty.setText("qty" + number);
                minteger = 0;
            }
           else if (position==1){
                qty.setText("qty" + number);
                minteger = 0;
           }
            else if (position==2){
                qty.setText("qty" + number);
                minteger = 0;

           }
    }
    }



