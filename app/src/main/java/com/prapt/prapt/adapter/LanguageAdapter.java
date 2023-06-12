package com.prapt.prapt.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.prapt.prapt.pogo.Languagesetget;
import com.prapt.prapt.R;

import java.util.List;
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {
    private Context mCtx;
    private List<Languagesetget> languagesetgetList;
    int listview;
    private RadioButton lastCheckedRB = null;
    public LanguageAdapter(Context mCtx, List<Languagesetget> languagesetgetList) {
        this.mCtx = mCtx;
        this.languagesetgetList = languagesetgetList;
    }
    @Override
    public LanguageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_list_view, parent, false);
        return new LanguageAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final LanguageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Languagesetget languagesetget = languagesetgetList.get(position);
        listview = position;
        holder.lang.setText(String.valueOf(languagesetget.getLanguageName()));
        holder.lang1.setText(String.valueOf(languagesetget.getLanguageNameEng()));
        Glide.with(mCtx)
                .load(languagesetget.getLanguageImage())
                .into(holder.imageId);
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked_rb = (RadioButton) group.findViewById(checkedId);
                if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(false);
                }
                //store the clicked radiobutton
                lastCheckedRB = checked_rb;
            }
        });
    }
    @Override
    public int getItemCount() {
        return languagesetgetList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lang,lang1;
        private ImageView imageId;
        private RadioGroup radioGroup;
        RadioButton radioButton1;
        public ViewHolder(View view) {
            super(view);
            lang = (TextView) view.findViewById(R.id.lang);
            lang1 = (TextView) view.findViewById(R.id.lang1);
            imageId = (ImageView) view.findViewById(R.id.imageId);
            radioButton1 = (RadioButton) view.findViewById(R.id.radioButton1);
            radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        }
    }
}