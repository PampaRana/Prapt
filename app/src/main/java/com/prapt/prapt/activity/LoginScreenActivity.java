package com.prapt.prapt.activity;

import static com.prapt.prapt.utils.Config.BASEURL;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

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
import com.prapt.prapt.model.LoginData;
import com.prapt.prapt.pogo.LoginModel;
import com.prapt.prapt.pogo.LoginModelMatch;
import com.prapt.prapt.R;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.InternetCheck;
import com.prapt.prapt.utils.SharedPrefManagerLogin;
import com.prapt.prapt.utils.SharedPrefManagerLoginMach;
import com.prapt.prapt.apiCall.VolleySingleton;
import com.prapt.prapt.utils.SharedPreferencesClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreenActivity extends AppCompatActivity {
    AppCompatButton btn_login;
    TextView signUp;
    TextInputEditText emailId, PasswordID;
    String token, token1;
    String emailPattern,mobilePattern;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_screen);
        btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        signUp = (TextView) findViewById(R.id.signUp);
        emailId = (TextInputEditText) findViewById(R.id.emailId);
        PasswordID = (TextInputEditText) findViewById(R.id.PasswordID);
        emailPattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        mobilePattern = "[0-9]{10}";

//        emailId.setHint("Enter Phone Number or Email ID");
//        PasswordID.setHint("Password");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
        token = SharedPrefManagerLogin.getInstance(getApplicationContext()).getUser().getToken();
        token1 = SharedPrefManagerLoginMach.getInstance(getApplicationContext()).getUser().getTokenMach();
        if (token != null && token1 != null) {
            System.out.println("NoMach" + "2");
            if (token.equals(token1)) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", "0");
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "successfull", Toast.LENGTH_SHORT).show();
                System.out.println("NoMach" + "3");
            } else {
                System.out.println("NoMach" + "4");
            }
        } else {
            System.out.println("NoMach" + "5");
        }
    }

    public void login() {
        String email = emailId.getText().toString().trim();
        String password = PasswordID.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailId.setError("Enter Email");
            emailId.requestFocus();
            return;
        } /*else if (!email.matches(emailPattern)) {
            emailId.setError("Enter Correct Email");
            emailId.requestFocus();
            return;
        } */else if (TextUtils.isEmpty(password)) {
            PasswordID.setError("Enter Password");
            PasswordID.requestFocus();
            return;
        }else {
            if (InternetCheck.isConnected(this)) {
                showHud();
                getLoginInfo(email, password);
            }else {
                showToastMessage("No internet connection");
            }
        }


        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, BASEURL+"login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("registration"+response);
                        try {
                            //converting the string to json array object
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("success");
                            String message = obj.getString("Message");
                            String token = obj.getString("token");
                            String userId = obj.getString("user_id");
                            String name = obj.getString("name");
                            String code = obj.getString("code");
                            if (status.equals("true")){
                                LoginModel loginModel = new LoginModel(token,userId,name,code);
                                LoginModelMatch loginModelMatch = new LoginModelMatch(token);
                                SharedPrefManagerLogin.getInstance(getApplicationContext()).TokenID(loginModel);
                                SharedPrefManagerLoginMach.getInstance(getApplicationContext()).TokenID(loginModelMatch);
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("id", "0");
                                startActivity(intent);
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
                params.put("email_phone", email);
                params.put("password", password);
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
    private void getLoginInfo(String email, String password) {
        Call<LoginData> call= ApiClient.getInstance().getLogin(email,password);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                hide();
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (response.body().getSuccess().equals("true")){
                        SharedPreferencesClass.insertData(LoginScreenActivity.this, Config.LOGIN_TOKEN,response.body().getToken());
                        SharedPreferencesClass.insertData(LoginScreenActivity.this, Config.LOGIN_STATUS,"1");
                        SharedPreferencesClass.insertData(LoginScreenActivity.this, Config.USER_ID,response.body().getUser_id());
                        SharedPreferencesClass.insertData(LoginScreenActivity.this, Config.USER_NAME,response.body().getName());
                        SharedPreferencesClass.insertData(LoginScreenActivity.this, Config.USER_CODE,response.body().getCode());
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        showToastMessage(response.body().getMessage());
                    } else {
                        showToastMessage(response.body().getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {

            }
        });

    }
    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
