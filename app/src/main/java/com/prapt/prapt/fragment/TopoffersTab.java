package com.prapt.prapt.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.R;
import com.prapt.prapt.adapter.SliderAdapter;
import com.prapt.prapt.adapter.SliderAdapters;
import com.prapt.prapt.adapter.TopOfferAdapter;
import com.prapt.prapt.adapter.TopOfferGVAdapter;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.interfacelistener.BackButtonHandlerInterface;
import com.prapt.prapt.interfacelistener.OnBackClickListener;
import com.prapt.prapt.model.banner.BannerData;
import com.prapt.prapt.model.banner.BannerDataDetails;
import com.prapt.prapt.model.category.CategoryData;
import com.prapt.prapt.model.category.CategoryDataDetails;
import com.prapt.prapt.model.topOffer.TopOfferData;
import com.prapt.prapt.model.topOffer.TopOfferDataDetails;
import com.prapt.prapt.pogo.SliderItemss;
import com.prapt.prapt.pogo.TopOfferModel;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.InternetCheck;
import com.prapt.prapt.utils.SharedPreferencesClass;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopoffersTab extends Fragment implements OnBackClickListener {
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    SliderView sliderView;
    RecyclerView idGVcourses;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        backButtonHandler = (BackButtonHandlerInterface) activity;
//        backButtonHandler.addBackClickListener(this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.e("TABB","top");
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.top_offers_tab, container, false);
        sliderView = rootView.findViewById(R.id.slider);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        idGVcourses = rootView.findViewById(R.id.idGVcourses);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        idGVcourses.setLayoutManager(layoutManager);
        if (InternetCheck.isConnected(getActivity())) {

            getBannerList();
            getTopOfferList();
        } else {
            showToastMessage("No internet connection");
        }
        return rootView;
    }

    private void getTopOfferList() {
       // showHud();
        ArrayList<TopOfferDataDetails> topOfferList = new ArrayList<>();
        Call<TopOfferData> call= ApiNewClient.getInstance(getContext()).getTopOfferList(
                SharedPreferencesClass.retrieveData(getContext(), Config.USER_ID)
        );
        call.enqueue(new Callback<TopOfferData>() {
            @Override
            public void onResponse(Call<TopOfferData> call, Response<TopOfferData> response) {
                hide();
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){

                        topOfferList.clear();
                        for (int i=0; i<response.body().getTopOfferDataDetails().size();i++){
                            topOfferList.add(response.body().getTopOfferDataDetails().get(i));
                        }
                        TopOfferAdapter adapter = new TopOfferAdapter(getContext(), topOfferList);
                        idGVcourses.setAdapter(adapter);

                    } else {
                        //showToastMessage(response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<TopOfferData> call, Throwable t) {

            }
        });
    }

    KProgressHUD hud;

    void showHud() {
        hud = KProgressHUD.create(getContext())
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
    private void getBannerList() {
        showHud();
        ArrayList<BannerDataDetails> bannerList = new ArrayList<>();

        Call<BannerData> call= ApiNewClient.getInstance(getContext()).getBannerList( SharedPreferencesClass.retrieveData(getContext(), Config.USER_ID));
        call.enqueue(new Callback<BannerData>() {
            @Override
            public void onResponse(Call<BannerData> call, Response<BannerData> response) {

                hide();
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){

                        bannerList.clear();
                        for (int i=0; i<response.body().getBannerDataDetails().size();i++){
                            bannerList.add(response.body().getBannerDataDetails().get(i));
                        }

                        SliderAdapter adapter = new SliderAdapter(getContext(), bannerList);

                        // below method is used to set auto cycle direction in left to
                        // right direction you can change according to requirement.
                        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

                        // below method is used to
                        // setadapter to sliderview.
                        sliderView.setSliderAdapter(adapter);

                        // below method is use to set
                        // scroll time in seconds.
                        sliderView.setScrollTimeInSec(3);

                        // to set it scrollable automatically
                        // we use below method.
                        sliderView.setAutoCycle(true);

                        // to start autocycle below method is used.
                        sliderView.startAutoCycle();
                        //showToastMessage(response.body().getMessage());
                    } else {
                        //showToastMessage(response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<BannerData> call, Throwable t) {

            }
        });

    }
    private void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
       // sliderHandler.postDelayed(sliderRunnable, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }
    @Override
    public void onDetach() {
        super.onDetach();
        backButtonHandler.removeBackClickListener(this);
        backButtonHandler = null;
    }
    @Override
    public boolean onBackClick() {
        //Toast.makeText(getContext(), "Do not exit", Toast.LENGTH_SHORT).show();
        return true;
    }

}