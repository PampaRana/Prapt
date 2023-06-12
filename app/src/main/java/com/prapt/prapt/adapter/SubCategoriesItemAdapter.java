package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prapt.prapt.R;
import com.prapt.prapt.pogo.SubCategoriesItemExpandableListView;
import com.prapt.prapt.pogo.SubViewSecondSetGet;
import com.prapt.prapt.activity.ProductDetailsActivity;
import com.prapt.prapt.pogo.SubcatagoriesViewSetGet;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesItemAdapter extends RecyclerView.Adapter<SubCategoriesItemAdapter.ViewHolder> {
        private Context mCtx;
        private List<SubcatagoriesViewSetGet> subcatagoriesViewSetGetList;
        int listview;
        List<SubViewSecondSetGet> subViewSecondSetGetList;
        List<SubCategoriesItemExpandableListView> subCategoriesItemExpandableListViews;
        public SubCategoriesItemAdapter(Context mCtx, List<SubcatagoriesViewSetGet> subcatagoriesViewSetGetList) {
            this.mCtx = mCtx;
            this.subcatagoriesViewSetGetList = subcatagoriesViewSetGetList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_catagories_item_view, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            SubcatagoriesViewSetGet cat = subcatagoriesViewSetGetList.get(position);
            listview = position;
            holder.perTextPre.setText(cat.getPretext());
            holder.perTextPreCross.setText(cat.getPretextCross());
            holder.perTextPreCrossText.setText(cat.getPretextCrossText());
            holder.itemName.setText(cat.getItemName());
            holder.itemName1.setText(cat.getItemName1());
            holder.mrpId1.setText(cat.getMrp());
            holder.rateId1.setText(cat.getRate());
            holder.maginId1.setText(cat.getMergin());
            Glide.with(mCtx)
                    .load(cat.getImage())
                    .into(holder.ImageId);
            holder.ImageId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(mCtx, ProductDetailsActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mCtx.startActivity(myIntent);
                }
            });

            subViewSecondSetGetList = new ArrayList<>();

            subViewSecondSetGetList.add(
                    new SubViewSecondSetGet(
                            "0",
                            "10.Rs/-"
                    ));
            subViewSecondSetGetList.add(
                    new SubViewSecondSetGet(
                            "1",
                            "1Kg"
                    ));
            subViewSecondSetGetList.add(
                    new SubViewSecondSetGet(
                            "2",
                            "3Kg"
                    ));
            subViewSecondSetGetList.add(
                    new SubViewSecondSetGet(
                            "3",
                            "1.5Kg"
                    ));
            subViewSecondSetGetList.add(
                    new SubViewSecondSetGet(
                            "3",
                            "500g"
                    ));
            subViewSecondSetGetList.add(
                    new SubViewSecondSetGet(
                            "4",
                            "5Kg"
                    ));
            subViewSecondSetGetList.add(
                    new SubViewSecondSetGet(
                            "5",
                            "5Kg"
                    ));
            holder.recyclerViews.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mCtx,4);
            holder.recyclerViews.setLayoutManager(layoutManager);
            SubcatagoriSecondAdapter adapter = new SubcatagoriSecondAdapter(mCtx, subViewSecondSetGetList);
            holder.recyclerViews.setAdapter(adapter);
            subCategoriesItemExpandableListViews = new ArrayList<>();
            subCategoriesItemExpandableListViews.add(
                    new SubCategoriesItemExpandableListView(
                            "Bulk offers starting at 208.9/unit",
                            "Get additional 1.5% off per unit",
                            R.drawable.tag,
                            R.drawable.persent1
                    ));
            subCategoriesItemExpandableListViews.add(
                    new SubCategoriesItemExpandableListView(
                            "Bulk offers starting at 208.9/unit",
                            "Get additional 1.5% off per unit",
                            R.drawable.tag,
                            R.drawable.persent1
                    ));
//            subCategoriesItemExpandableListViews.add(
//                    new SubCategoriesItemExpandableListView(
//                            "Bulk offers starting at 208.9/unit",
//                            "Get additional 1.5% off per unit",
//                            R.drawable.tag,
//                            R.drawable.persent1
//                    ));
//            subCategoriesItemExpandableListViews.add(
//                    new SubCategoriesItemExpandableListView(
//                            "Bulk offers starting at 208.9/unit",
//                            "Get additional 1.5% off per unit",
//                            R.drawable.tag,
//                            R.drawable.persent1
//                    ));
//            subCategoriesItemExpandableListViews.add(
//                    new SubCategoriesItemExpandableListView(
//                            "Bulk offers starting at 208.9/unit",
//                            "Get additional 1.5% off per unit",
//                            R.drawable.tag,
//                            R.drawable.persent1
//                    ));
//            subCategoriesItemExpandableListViews.add(
//                    new SubCategoriesItemExpandableListView(
//                            "Bulk offers starting at 208.9/unit",
//                            "Get additional 1.5% off per unit",
//                            R.drawable.tag,
//                            R.drawable.persent1
//                    ));


            SubExpendaleAdapter adapter1 = new SubExpendaleAdapter(mCtx, subCategoriesItemExpandableListViews);
            holder.recyclerViews1.setAdapter(adapter1);
            holder.recyclerViews1.setLayoutManager(new LinearLayoutManager(mCtx,
                    LinearLayoutManager.VERTICAL, true));
            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(holder.recyclerViews1);
        }
        @Override
        public int getItemCount() {
            return subcatagoriesViewSetGetList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView perTextPre,perTextPreCross,perTextPreCrossText,itemName,itemName1,mrpId1,rateId1,maginId1;
            ImageView ImageId;
            RecyclerView recyclerViews,recyclerViews1;
            public ViewHolder(View view) {
                super(view);
                perTextPre = (TextView) view.findViewById(R.id.perTextPre);
                perTextPreCross = (TextView) view.findViewById(R.id.perTextPreCross);
                perTextPreCrossText = (TextView) view.findViewById(R.id.perTextPreCrossText);
                itemName = (TextView) view.findViewById(R.id.itemName);
                itemName1 = (TextView) view.findViewById(R.id.itemName1);
                mrpId1 = (TextView) view.findViewById(R.id.mrpId1);
                rateId1 = (TextView) view.findViewById(R.id.rateId1);
                maginId1 = (TextView) view.findViewById(R.id.maginId1);
                ImageId = (ImageView) view.findViewById(R.id.ImageId);
                recyclerViews = (RecyclerView) view.findViewById(R.id.recyclerViews);
                recyclerViews1 = (RecyclerView) view.findViewById(R.id.recyclerViews1);

            }
        }
    }
