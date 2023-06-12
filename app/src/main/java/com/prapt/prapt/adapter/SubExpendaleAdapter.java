package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.R;
import com.prapt.prapt.pogo.SubCategoriesItemExpandableListView;

import java.util.List;

public class SubExpendaleAdapter extends RecyclerView.Adapter<SubExpendaleAdapter.ViewHolder> {
        private Context mCtx;
        private List<SubCategoriesItemExpandableListView> subCategoriesItemExpandableListViews;;
        int listview;
        private static int currentPosition = 0;
        public SubExpendaleAdapter(Context mCtx, List<SubCategoriesItemExpandableListView> subCategoriesItemExpandableListViews) {
            this.mCtx = mCtx;
            this.subCategoriesItemExpandableListViews = subCategoriesItemExpandableListViews;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subexpandable_llstview, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            SubCategoriesItemExpandableListView cat = subCategoriesItemExpandableListViews.get(position);
            listview = position;
            holder.textViewName.setText(cat.getTitleName());
            holder.imageTag.setImageDrawable(mCtx.getResources().getDrawable(cat.getTitleImage()));
            holder.subText.setText(cat.getSubName());
            holder.subimageTag.setImageDrawable(mCtx.getResources().getDrawable(cat.getSubImage()));

            holder.linearLayout.setVisibility(View.GONE);
            holder.arrow.setVisibility(View.VISIBLE);
            holder.arrow1.setVisibility(View.GONE);

            //if the position is equals to the item position which is to be expanded
            if (currentPosition == position) {
                //creating an animation
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);

                //toggling visibility
                holder.linearLayout.setVisibility(View.GONE);

                //adding sliding effect
                holder.linearLayout.startAnimation(slideDown);
            }

//            holder.textViewName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    //getting the position of the item to expand it
//                    currentPosition = position;
//
//                    //reloding the list
//                    notifyDataSetChanged();
//                }
//            });
             holder.arrow.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     holder.linearLayout.setVisibility(View.VISIBLE);
                     holder.arrow.setVisibility(View.GONE);
                     holder.arrow1.setVisibility(View.VISIBLE);
//                     currentPosition = position;
//
//                     //reloding the list
//                     notifyDataSetChanged();


                 }
             });
            holder.arrow1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.linearLayout.setVisibility(View.GONE);
                    holder.arrow.setVisibility(View.VISIBLE);
                    holder.arrow1.setVisibility(View.GONE);
//                    currentPosition = position;
//
//                    //reloding the list
//                    notifyDataSetChanged();

                }
            });
        }
        @Override
        public int getItemCount() {
            return subCategoriesItemExpandableListViews.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageTag,arrow,arrow1,subimageTag;
            TextView textViewName,subText,subapply;
            LinearLayout linearLayout;

            public ViewHolder(View view) {
                super(view);
                textViewName = (TextView) view.findViewById(R.id.textViewName);
                subText = (TextView) view.findViewById(R.id.subText);
                subapply = (TextView) view.findViewById(R.id.subapply);
                imageTag = (ImageView) view.findViewById(R.id.imageTag);
                arrow = (ImageView) view.findViewById(R.id.arrow);
                arrow1 = (ImageView) view.findViewById(R.id.arrow1);
                subimageTag = (ImageView) view.findViewById(R.id.subimageTag);
                linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
            }
        }
    }