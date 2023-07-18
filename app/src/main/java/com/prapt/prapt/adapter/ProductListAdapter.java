package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.R;
import com.prapt.prapt.activity.BrandActivity;
import com.prapt.prapt.activity.ProductActivity;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.model.SuccessData;
import com.prapt.prapt.model.brand.BrandDataDetails;
import com.prapt.prapt.model.product.ProductDataDetails;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private ProductActivity mCtx;
    private ArrayList<ProductDataDetails> productList;
    int listview;
    //detailsListener detailsListener;

    public ProductListAdapter(ProductActivity mCtx, ArrayList<ProductDataDetails> productList
                              /*detailsListener detailsListener*/) {
        this.mCtx = mCtx;
        this.productList = productList;
        //this.detailsListener = detailsListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_adapter, parent, false);
        return new ViewHolder(view/*,detailsListener*/);

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductDataDetails itemsView = productList.get(position);
        listview = position;
        holder.wishName.setText(itemsView.getProduct_name());
        //holder.wishGram.setText(itemsView.getProduct_name());
        holder.wishPrice.setText("₹"+itemsView.getActual_price());
        holder.discountText.setText("₹"+itemsView.getAfter_discount_price());
        Glide.with(mCtx)
                .load(itemsView.getDfile1())
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
        holder.add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCartItem(itemsView.getProduct_id(), "1");
            }
        });

      /*  holder.Ids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsListener.goToDetailsBrand(brandSetGets.get(position).getBrand_id());
            }
        });*/


       // holder.profile_image.setImageDrawable(mCtx.getResources().getDrawable(cat.getBrand_logo()));
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView wishName,wishGram,wishPrice,discountText;
        private ImageView wish_Image,deleteId;
        ContentLoadingProgressBar content_loading_pb;
        CardView add_to_cart_btn;
        //detailsListener detailsListener;
        public ViewHolder(View view/*,detailsListener detailsListener*/) {
            super(view);
            //this.detailsListener=detailsListener;

            content_loading_pb=view.findViewById(R.id.content_loading_pb);
            wishName = (TextView) view.findViewById(R.id.wishName);
            wishGram = (TextView) view.findViewById(R.id.wishGram);
            wishPrice = (TextView) view.findViewById(R.id.wishPrice);
            discountText = (TextView) view.findViewById(R.id.discountText);
            wish_Image = (ImageView) view.findViewById(R.id.wish_Image);
            deleteId = (ImageView) view.findViewById(R.id.deleteId);
            add_to_cart_btn=view.findViewById(R.id.add_to_cart_btn);
        }
    }
    public interface detailsListener{
        void goToDetailsBrand(String brand_id);
    }

    private void addToCartItem(String pdt_id, String quantity) {

        showHud();
        Call<SuccessData> call= ApiNewClient.getInstance(mCtx).getAddToCart(
                SharedPreferencesClass.retrieveData(mCtx, Config.USER_ID),
                pdt_id,"1"
        );
        call.enqueue(new Callback<SuccessData>() {
            @Override
            public void onResponse(Call<SuccessData> call, Response<SuccessData> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess().equals("true")){

                        hide();
                        Toast.makeText(mCtx, "Successfully added", Toast.LENGTH_SHORT).show();

                    }else {

                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessData> call, Throwable t) {

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
}