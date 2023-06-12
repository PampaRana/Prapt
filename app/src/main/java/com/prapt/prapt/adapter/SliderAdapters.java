package com.prapt.prapt.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.prapt.prapt.R;
import com.prapt.prapt.pogo.SliderItemss;

import java.util.List;

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder> {
    private List<SliderItemss> SliderViewHolder;
    private ViewPager2 viewPager2;
    public SliderAdapters(List<SliderItemss> SliderViewHolder, ViewPager2 viewPager2) {
        this.SliderViewHolder = SliderViewHolder;
        this.viewPager2 = viewPager2;
    }
    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_containers, parent, false
                ) );
    }
    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(SliderViewHolder.get(position));
        if (position == SliderViewHolder.size()- 2){
//            viewPager2.post(runnable);
        }
    }
    @Override
    public int getItemCount() {
        return SliderViewHolder.size();
    }
    class SliderViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;
        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }
        void setImage(SliderItemss sliderItems){
//use glide or picasso in case you get image from internet
            imageView.setImageResource(sliderItems.getImage());
        }
    }
}
