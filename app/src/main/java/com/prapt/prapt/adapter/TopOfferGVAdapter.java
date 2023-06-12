package com.prapt.prapt.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prapt.prapt.R;
import com.prapt.prapt.model.topOffer.TopOfferDataDetails;
import com.prapt.prapt.pogo.TopOfferModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopOfferGVAdapter extends ArrayAdapter<TopOfferDataDetails> {
    public TopOfferGVAdapter(@NonNull Context context, ArrayList<TopOfferDataDetails> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        TopOfferDataDetails courseModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.idTVCourse);
        ImageView courseIV = listitemView.findViewById(R.id.idIVcourse);
        ContentLoadingProgressBar content_loading_pb=listitemView.findViewById(R.id.content_loading_pb);

        courseTV.setVisibility(View.GONE);
        courseTV.setText(courseModel.getP_title());
       /* Glide.with(getContext())
                .load(courseModel.getDfile1())
                .fitCenter()
                .into(courseIV);*/
        //courseIV.setImageResource(courseModel.getDfile1());
        Log.e("FileTop", "getView: "+courseModel.getDfile1() );
        Glide.with(getContext())
                .load(courseModel.getDfile1())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        content_loading_pb.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(courseIV)
        ;
        return listitemView;
    }
}
