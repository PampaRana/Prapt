package com.prapt.prapt.fragment;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.adapter.TopOfferAdapter;
import com.prapt.prapt.apiCall.ApiNewClient;
import com.prapt.prapt.interfacelistener.BackButtonHandlerInterface;
import com.prapt.prapt.interfacelistener.OnBackClickListener;
import com.prapt.prapt.R;
import com.prapt.prapt.adapter.AllofferAdapter;
import com.prapt.prapt.model.allOffer.AllOfferData;
import com.prapt.prapt.model.allOffer.AllOfferDataDetails;
import com.prapt.prapt.model.topOffer.TopOfferData;
import com.prapt.prapt.model.topOffer.TopOfferDataDetails;
import com.prapt.prapt.pogo.AllOfferList;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.InternetCheck;
import com.prapt.prapt.utils.SharedPreferencesClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlloffersTab extends Fragment implements OnBackClickListener {
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView recyclerView;
    //List<AllOfferList> allOfferListList;
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
            Log.e("TABB","all");
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_offers_tab, container, false);
        System.out.println("ViewTab"+"AllOffer");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        if (InternetCheck.isConnected(getActivity())) {

            getAllOfferList();
        } else {
            showToastMessage("No internet connection");
        }


        /*allOfferListList = new ArrayList<>();
        allOfferListList.add(
                new AllOfferList(
                        R.drawable.vim,
                        R.drawable.vim,
                        R.drawable.vim,
                        "Vim Dishwash Gel Lemon 500ml",
                        "Offer Expires in 2 days",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit"
                ));

        allOfferListList.add(
                new AllOfferList(
                        R.drawable.vim,
                        R.drawable.vim,
                        R.drawable.vim,
                        "Vim Dishwash Gel Lemon 500ml",
                        "Offer Expires in 2 days",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit"
                ));

        allOfferListList.add(
                new AllOfferList(
                        R.drawable.vim,
                        R.drawable.vim,
                        R.drawable.vim,
                        "Vim Dishwash Gel Lemon 500ml",
                        "Offer Expires in 2 days",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit"
                ));

        allOfferListList.add(
                new AllOfferList(
                        R.drawable.vim,
                        R.drawable.vim,
                        R.drawable.vim,
                        "Vim Dishwash Gel Lemon 500ml",
                        "Offer Expires in 2 days",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit"
                ));


        allOfferListList.add(
                new AllOfferList(
                        R.drawable.vim,
                        R.drawable.vim,
                        R.drawable.vim,
                        "Vim Dishwash Gel Lemon 500ml",
                        "Offer Expires in 2 days",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit"
                ));
        allOfferListList.add(
                new AllOfferList(
                        R.drawable.vim,
                        R.drawable.vim,
                        R.drawable.vim,
                        "Vim Dishwash Gel Lemon 500ml",
                        "Offer Expires in 2 days",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit"
                ));

        allOfferListList.add(
                new AllOfferList(
                        R.drawable.vim,
                        R.drawable.vim,
                        R.drawable.vim,
                        "Vim Dishwash Gel Lemon 500ml",
                        "Offer Expires in 2 days",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit",
                        "Get additional 1% off per unit on order between2 unit and 5 unit"
                ));*/
//        AllofferAdapter adapter = new AllofferAdapter(getContext(), allOfferListList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL, true));
//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    private void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
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
    private void getAllOfferList() {
        showHud();
        ArrayList<AllOfferDataDetails> allOfferList = new ArrayList<>();
        Call<AllOfferData> call= ApiNewClient.getInstance(getContext()).getAllOfferList(
                SharedPreferencesClass.retrieveData(getContext(), Config.USER_ID)
        );
        call.enqueue(new Callback<AllOfferData>() {
            @Override
            public void onResponse(Call<AllOfferData> call, Response<AllOfferData> response) {
                hide();
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){

                        allOfferList.clear();
                        for (int i=0; i<response.body().getAllOfferDataDetails().size();i++){
                            allOfferList.add(response.body().getAllOfferDataDetails().get(i));
                        }
                        AllofferAdapter adapter = new AllofferAdapter(getContext(), allOfferList);
                        recyclerView.setAdapter(adapter);

                    } else {
                        //showToastMessage(response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<AllOfferData> call, Throwable t) {

            }
        });
    }
}