package com.prapt.prapt.apiCall;


import com.prapt.prapt.model.LoginData;
import com.prapt.prapt.model.SuccessData;
import com.prapt.prapt.model.allOffer.AllOfferData;
import com.prapt.prapt.model.banner.BannerData;
import com.prapt.prapt.model.brand.BrandData;
import com.prapt.prapt.model.cart.CartData;
import com.prapt.prapt.model.cart.SuccessIsInCartData;
import com.prapt.prapt.model.category.CategoryData;
import com.prapt.prapt.model.product.ProductData;
import com.prapt.prapt.model.subCategory.SubCategoryData;
import com.prapt.prapt.model.topOffer.TopOfferData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiRequest {

    /*===========================Login====================================*/
    @FormUrlEncoded
    @POST("login")
    Call<LoginData> getLogin(@Field("email_phone") String email_phone,
                             @Field("password") String password);


    /*===========================Registration====================================*/
    @FormUrlEncoded
    @POST("registration")
    Call<SuccessData> getRegistration(@Field("usertype") String usertype,
                                      @Field("username") String username,
                                      @Field("phone") String phone,
                                      @Field("password") String password,
                                      @Field("email") String email,
                                      @Field("address") String address,
                                      @Field("pincode") String pincode,
                                      @Field("dob") String dob);

    /*===========================Category====================================*/
    @FormUrlEncoded
    @POST("get_categories")
    Call<CategoryData> getCategoryList(@Field("id") String id);

    /*===========================Sub Category====================================*/
    @FormUrlEncoded
    @POST("get_subcategories")
    Call<SubCategoryData> getSubCategoryList(@Field("id") String id,
                                             @Field("cat_id") String cat_id);

    /*===========================Product====================================*/
    //search-->product_name="cake"
    //category wise ---> cat_id="12"
    //sub category wise ---> cat_id="0", sub_cat_id="12"
    //cat & sub category wise ---> cat_id="12", sub_cat_id="12"
    @FormUrlEncoded
    @POST("get_products")
    Call<ProductData> getProductList(@Field("id") String id,
                                     @Field("cat_id") String cat_id,
                                     @Field("subcat_id") String subcat_id,
                                     @Field("product_name") String product_name);


    /*===========================Brand Product====================================*/
    @FormUrlEncoded
    @POST("get_brands")
    Call<BrandData> getBrandList(@Field("id") String id,
                                 @Field("cat_id") String cat_id);

    /*===========================Brand Wise Product====================================*/
    @FormUrlEncoded
    @POST("get_brandwise_product")
    Call<ProductData> getBrandWisePdtList(@Field("id") String id,
                                          @Field("brand") String brand);


    /*===========================Cart List====================================*/
    @FormUrlEncoded
    @POST("add_to_cart_list")
    Call<CartData> getCartList(@Field("id") String id);

    /*===========================Add To Cart====================================*/
    @FormUrlEncoded
    @POST("add_to_cart")
    Call<SuccessData> getAddToCart(@Field("id") String id,
                                   @Field("product_id") String product_id,
                                   @Field("quantity") String quantity);


    /*===========================Delete Cart====================================*/
    @FormUrlEncoded
    @POST("delete_add_to_cart")
    Call<SuccessData> getDeleteCart(@Field("id") String id,
                                    @Field("product_id") String product_id);

    /*===========================Already Product is in Cart or not====================================*/
    @FormUrlEncoded
    @POST("is_in_add_to_cart")
    Call<SuccessIsInCartData> getProductIsInCart(@Field("id") String id,
                                                 @Field("product_id") String product_id);

    /*===========================Banner Image====================================*/
    @FormUrlEncoded
    @POST("banner")
    Call<BannerData> getBannerList(@Field("id") String id);

    /*===========================Top Offers====================================*/
    @FormUrlEncoded
    @POST("top_offers")
    Call<TopOfferData> getTopOfferList(@Field("id") String id);

    /*===========================All Offer====================================*/
    @FormUrlEncoded
    @POST("all_offers")
    Call<AllOfferData> getAllOfferList(@Field("id") String id);




    /*===========================Pin Code Check====================================*/
    @FormUrlEncoded
    @POST("check_pincode")
    Call<SuccessData> getCheckPinCode(@Field("id") String id,
                                      @Field("pincode") String pincode);
}
