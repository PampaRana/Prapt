package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.apiCall.ApiClient;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.model.SuccessData;
import com.prapt.prapt.model.cart.CartDataDetails;
import com.prapt.prapt.pogo.MycartsetGet;
import com.prapt.prapt.R;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    private Context mCtx;
    private ArrayList<CartDataDetails> mycartsetGetList;
    int listview;
    //int minteger = 0;
    addToCartListener addToCartListener;
    TextView cartPrice;
    TextView discountText;
    public MyCartAdapter(Context mCtx, ArrayList<CartDataDetails> mycartsetGetList,
                         TextView cartPrice,
                         TextView discountText,
                         addToCartListener addToCartListener) {
        this.mCtx = mCtx;
        this.mycartsetGetList = mycartsetGetList;
        this.cartPrice = cartPrice;
        this.discountText = discountText;

        this.addToCartListener = addToCartListener;
    }

    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_list, parent, false);
        return new MyCartAdapter.ViewHolder(view, addToCartListener);
    }

    @Override
    public void onBindViewHolder(final MyCartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartDataDetails mycartsetGet = mycartsetGetList.get(position);
        listview = position;
        holder.cartName.setText(String.valueOf(mycartsetGet.getProduct_name()));
        //holder.cartGram.setText(String.valueOf(mycartsetGet.getCartGram()));
        holder.cartPrice.setText("₹" + mycartsetGet.getActual_price());
        holder.discountText.setText("₹" + mycartsetGet.getAfter_discount_price());

        double totalActualPrice=0.0;
        double totalDiscountPrice=0.0;
        for (int i=0;i<mycartsetGetList.size();i++) {
            totalActualPrice += Double.parseDouble(mycartsetGetList.get(i).getActual_price())
            * Double.parseDouble(mycartsetGetList.get(i).getQuantity());

            totalDiscountPrice+=Double.parseDouble(mycartsetGetList.get(i).getAfter_discount_price())
            *Double.parseDouble(mycartsetGetList.get(i).getQuantity());

        }
        DecimalFormat decfor = new DecimalFormat("0.00");
      ;
        cartPrice.setText("₹"+String.valueOf( decfor.format(totalActualPrice)));
        discountText.setText("₹"+String.valueOf(  decfor.format(totalDiscountPrice)));

        holder.qty.setText(mycartsetGet.getQuantity());
        //minteger=Integer.parseInt(mycartsetGet.getQuantity());
        holder.decreaseInteger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (Integer.parseInt(mycartsetGet.getQuantity()) > 0) {
                        int quantity = Integer.parseInt(mycartsetGet.getQuantity()); // get the quantity for this row item
                        mycartsetGet.setQuantity(String.valueOf(quantity - 1));

                        if (Integer.parseInt(mycartsetGet.getQuantity())==0){
                            //Toast.makeText(mCtx, "need to remove", Toast.LENGTH_SHORT).show();
                            showHud();
                            getDeleteCart(
                                    position,
                                    SharedPreferencesClass.retrieveData(mCtx, Config.USER_ID),
                                    SharedPreferencesClass.retrieveData(mCtx, Config.LOGIN_TOKEN),
                                    mycartsetGet.getProduct_id()
                            );

                        }else {
                            holder.qty.setText(String.valueOf(mycartsetGet.getQuantity()));
                            addToCartListener.addToCartItem(mycartsetGet.getProduct_id(), mycartsetGet.getQuantity());

                        }
                    }



//                    minteger = minteger - 1;
//                    display(minteger,holder.qty,position);
                //int currentNos = Integer.parseInt(holder.qty.getText().toString()) ;
                //holder.qty.setText(String.valueOf(--currentNos));
            }
        });
        holder.increaseInteger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity = Integer.parseInt(mycartsetGet.getQuantity()); // get the quantity for this row item
                mycartsetGet.setQuantity(String.valueOf(quantity + 1));
                holder.qty.setText(String.valueOf(mycartsetGet.getQuantity()));
                addToCartListener.addToCartItem(mycartsetGet.getProduct_id(), mycartsetGet.getQuantity());

                //minteger = minteger + 1;
               // holder.qty.setText(String.valueOf(minteger));
//                    mCallback.onClick(quantity.getText().toString());
                //addToCartListener.addToCartItem(mycartsetGet.getProduct_id(), String.valueOf(minteger));

//                    minteger = minteger + 1;
//                    display(minteger,holder.qty,position);
                //int currentNos = Integer.parseInt(holder.qty.getText().toString()) ;
                //holder.qty.setText(String.valueOf(++currentNos));
//                    mCallback.onClick(quantity.getText().toString());
            }


        });
           /* Glide.with(mCtx)
                    .load(mycartsetGet.getDfile1())
                    .into(holder.wish_Image);*/
        Glide.with(mCtx)
                .load(mycartsetGet.getDfile1())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.content_loading_pb.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.wish_Image)
        ;
        holder.deleteId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHud();
                getDeleteCart(
                        position,
                        SharedPreferencesClass.retrieveData(mCtx, Config.USER_ID),
                        SharedPreferencesClass.retrieveData(mCtx, Config.LOGIN_TOKEN),
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

    private void getDeleteCart(int position, String user_id,
                               String token,
                               String product_id) {
        Call<SuccessData> call = ApiNewClient.getInstance(mCtx).getDeleteCart(
                user_id, product_id
        );
        call.enqueue(new Callback<SuccessData>() {
            @Override
            public void onResponse(Call<SuccessData> call, Response<SuccessData> response) {
                hide();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("true")) {
                        mycartsetGetList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mycartsetGetList.size());
                        showToastMessage(response.body().getMessage());
                    } else {
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
        private TextView cartName, cartGram, cartPrice, discountText, decreaseInteger, increaseInteger, qty;
        private ImageView wish_Image, deleteId;
        ContentLoadingProgressBar content_loading_pb;
        addToCartListener addToCartListener;

        public ViewHolder(View view, addToCartListener addToCartListener) {
            super(view);
            this.addToCartListener = addToCartListener;
            cartName = (TextView) view.findViewById(R.id.cartName);
            cartGram = (TextView) view.findViewById(R.id.cartGram);
            cartPrice = (TextView) view.findViewById(R.id.cartPrice);
            discountText = (TextView) view.findViewById(R.id.discountText);
            wish_Image = (ImageView) view.findViewById(R.id.wish_Image);
            deleteId = view.findViewById(R.id.deleteId);
            content_loading_pb = view.findViewById(R.id.content_loading_pb);

            decreaseInteger = (TextView) view.findViewById(R.id.decreaseInteger);
            increaseInteger = (TextView) view.findViewById(R.id.increaseInteger);
            qty = (TextView) view.findViewById(R.id.qty);
        }
    }

    /*private void display(int number, TextView qty, int position) {
        System.out.println("ListView" + position);

        if (position == 0) {
            qty.setText("qty" + number);
            minteger = 0;
        } else if (position == 1) {
            qty.setText("qty" + number);
            minteger = 0;
        } else if (position == 2) {
            qty.setText("qty" + number);
            minteger = 0;

        }
    }*/

    public interface addToCartListener {
        void addToCartItem(String product_id, String quantity);
    }
}




