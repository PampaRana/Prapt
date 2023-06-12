package com.prapt.prapt.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.pogo.Categories;
import com.prapt.prapt.R;
import com.prapt.prapt.activity.MainActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {
    private Context mCtx;
    private List<Categories> categories;
    int listview;

    public AdapterCategories(Context mCtx, List<Categories> categories) {
        this.mCtx = mCtx;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_button_with_image_and_text, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Categories cat = categories.get(position);
        listview = position;
        holder.led_tv.setText(cat.getItemName());
        System.out.println("getName"+cat.getItemName());
        holder.profile_image.setImageDrawable(mCtx.getResources().getDrawable(cat.getItemImage()));
        holder.Ids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, MainActivity.class);
                intent.putExtra("id", "1");
                mCtx.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return categories.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView Img_Id;
        TextView led_tv;
        LinearLayout Ids;
        CircleImageView profile_image;
        public ViewHolder(View view) {
            super(view);
            led_tv = (TextView) view.findViewById(R.id.led_tv);
//            Img_Id = (ImageView) view.findViewById(R.id.Img_Id);
            profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
            Ids = (LinearLayout) view.findViewById(R.id.Ids);
        }
    }
}