package com.prapt.prapt.activity;

import static com.prapt.prapt.utils.Config.BASEURL;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.prapt.prapt.apiCall.ApiClient;
import com.prapt.prapt.model.SuccessData;
import com.prapt.prapt.otherFrag.ExampleBottomSheetDialog;
import com.prapt.prapt.R;
import com.prapt.prapt.apiCall.VolleySingleton;
import com.prapt.prapt.utils.InternetCheck;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements ExampleBottomSheetDialog.BottomSheetListener {
    AppCompatButton btn_signup;
    TextInputEditText userId, phoneNumberId, emailNumberId,
            passwordId, addressNameId, pinNumberId, dateBarthId;
    CheckBox checkBox;
    String mobilePattern, emailPattern, dobPattern;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_activity);
        btn_signup = (AppCompatButton) findViewById(R.id.btn_signup);
        userId = (TextInputEditText) findViewById(R.id.userId);
        phoneNumberId = (TextInputEditText) findViewById(R.id.phoneNumberId);
        emailNumberId = (TextInputEditText) findViewById(R.id.emailNumberId);
        passwordId = (TextInputEditText) findViewById(R.id.passwordId);
        addressNameId = (TextInputEditText) findViewById(R.id.addressNameId);
        pinNumberId = (TextInputEditText) findViewById(R.id.pinNumberId);
        dateBarthId = (TextInputEditText) findViewById(R.id.dateBarthId);
        checkBox = findViewById(R.id.checkBox);
        mobilePattern = "[0-9]{10}";
        emailPattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        dobPattern = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registration();
            }
        });
        dateBarthId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if (text.length() == 2 || text.length() == 5) {
                    text.append('-');
                }
            }
        });
    }

    @Override
    public void onButtonClicked(String text) {

    }

    public void registration() {
        String userName = userId.getText().toString().trim();
        String MobileNo = phoneNumberId.getText().toString().trim();
        String email = emailNumberId.getText().toString().trim();
        String password = passwordId.getText().toString().trim();
        String address = addressNameId.getText().toString().trim();
        String pinCode = pinNumberId.getText().toString().trim();
        String dob = dateBarthId.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            userId.setError("Enter User Name");
            userId.requestFocus();
            return;
        } else if (TextUtils.isEmpty(MobileNo)) {
            phoneNumberId.setError("Enter Phone Number");
            phoneNumberId.requestFocus();
            return;
        } else if (!MobileNo.matches(mobilePattern)) {
            phoneNumberId.setError("Enter Correct Phone Number");
            phoneNumberId.requestFocus();
            return;
        } else if (TextUtils.isEmpty(email)) {
            emailNumberId.setError("Enter Email");
            emailNumberId.requestFocus();
            return;
        } else if (!email.matches(emailPattern)) {
            emailNumberId.setError("Enter Correct Email");
            emailNumberId.requestFocus();
            return;
        } else if (TextUtils.isEmpty(password)) {
            passwordId.setError("Enter Password");
            passwordId.requestFocus();
            return;
        } else if (password.length() < 4) {
            passwordId.setError("Enter at least 4 characters Password");
            passwordId.requestFocus();
            return;
        } else if (TextUtils.isEmpty(address)) {
            addressNameId.setError("Enter Address");
            addressNameId.requestFocus();
            return;
        } else if (TextUtils.isEmpty(pinCode)) {
            pinNumberId.setError("Enter Pin Code");
            pinNumberId.requestFocus();
            return;
        } else if (TextUtils.isEmpty(dob)) {
            dateBarthId.setError("Enter DOB");
            dateBarthId.requestFocus();
            return;
        } else if (TextUtils.isEmpty(dob)) {
            dateBarthId.setError("Enter DOB");
            dateBarthId.requestFocus();
            return;
        } else if (!dob.matches(dobPattern)) {
            dateBarthId.setError("Enter correct DOB");
            dateBarthId.requestFocus();
            return;
        } else if (!checkBox.isChecked()) {
            Toast.makeText(this, "Please check the terms and conditions", Toast.LENGTH_SHORT).show();
        } else {
            if (InternetCheck.isConnected(this)) {
                showHud();
                getRegister(userName, MobileNo, email, password, address, pinCode, dob);
            }else {
                showToastMessage("No internet connection");
            }

        }
        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, BASEURL+"registration",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("registration"+response);
                        try {
                            //converting the string to json array object
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("success");
                            String message = obj.getString("Message");
                            if (status.equals("true")){
                                ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
                                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("usertype", "1");
                params.put("username", userName);
                params.put("phone", MobileNo);
                params.put("password", password);
                params.put("email", email);
                params.put("address",address);
                params.put("pincode", pinCode);
                params.put("dob", dob);
                System.out.println("registration"+params.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization", "Bearer " + access_token);
                return headers;
            }
        };
        //   VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);*/
    }

    KProgressHUD hud;

    void showHud() {
        hud = KProgressHUD.create(this)
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

    private void getRegister(String userName, String mobileNo,
                             String email, String password, String address,
                             String pinCode, String dob) {

        Call<SuccessData> call = ApiClient.getInstance().getRegistration(
                "1", userName, mobileNo, password, email, address, pinCode, dob

        );
        call.enqueue(new Callback<SuccessData>() {
            @Override
            public void onResponse(@NonNull Call<SuccessData> call, @NonNull Response<SuccessData> response) {
                hide();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")) {
                        startActivity(new Intent(getApplicationContext(), LoginScreenActivity.class));
                        finish();
                        showToastMessage(response.body().getMessage());
                    } else {
                        showToastMessage(response.body().getMessage());

                    }


                }
            }

            @Override
            public void onFailure(Call<SuccessData> call, Throwable t) {
                hide();
                Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
