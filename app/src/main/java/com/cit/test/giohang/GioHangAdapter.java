package com.cit.test.giohang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cit.test.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GioHangAdapter extends RecyclerView.Adapter<ViewHolderGioHang> {
    private Context mcontext;
    private List<GioHang_Info> mData;
    public  int Gia =0;
    public  String URL;
//    public String ip = "172.16.0.232";
    public String ip = "192.168.43.181";

    public GioHangAdapter(Context mcontext, List<GioHang_Info> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_giohang, parent, false);
        return new ViewHolderGioHang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGioHang holder, final int position) {

        GioHang_Info imageModel=mData.get(position);
        ViewHolderGioHang imageItemHolder=(ViewHolderGioHang) holder;
        imageItemHolder.viewTenSP.setText(imageModel.getTenSanPham());//ten
        imageItemHolder.viewSoLuong.append(String.valueOf(imageModel.getSoLuongSP()));//Soluong
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        imageItemHolder.viewGiaSP.setText(decimalFormat.format(imageModel.getGiaSanPham())+" ₫");//Gia

        //Anh
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.drawable.anh_tai);
        requestOptions.error(R.drawable.anh_loi);

        Glide.with(mcontext)
                .load(imageModel.getAnhSP())
                .apply(requestOptions)
                .into(imageItemHolder.viewAnhSP);


        final int currentPosition = position;
        final GioHang_Info infoData = mData.get(position);
        holder.IBCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ChinhSua(infoData);
            }
            public void ChinhSua(final GioHang_Info infoData)
            {
                URL = "http://"+ip+"/SHOP/public//api/GioHang/"+infoData.getId();
                RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
                StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("result").trim().equals("success"))
                            {
                                Toast toast = Toast.makeText(mcontext, "Đã Sửa Thành Công: "+infoData.getTenSanPham(), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 400);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mcontext, "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("soluong", String.valueOf(infoData.getSoLuongSP()+1));
                        return params;
                    }

                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Conten-Tupe", "application/x-www-form-urlencoded");
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        holder.IBTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChinhSua(infoData);
            }
            public void ChinhSua(final GioHang_Info infoData)
            {
                URL = "http://"+ip+"/SHOP/public//api/GioHang/"+infoData.getId();
                RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
                StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("result").trim().equals("success"))
                            {
                                Toast toast = Toast.makeText(mcontext, "Đã Sửa Thành Công: "+infoData.getTenSanPham(), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 400);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mcontext, "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        if ( infoData.getSoLuongSP() >1 )
                            params.put("soluong", String.valueOf(infoData.getSoLuongSP()-1));
                        else
                            params.put("soluong", String.valueOf(infoData.getSoLuongSP()));
                        return params;
                    }

                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Conten-Tupe", "application/x-www-form-urlencoded");
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        holder.IBXoa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removeItem(infoData);
            }

            private void removeItem(final GioHang_Info infoData) {
                int currentPosition = mData.indexOf(infoData);
                //Xoa San Pham
                URL = "http://"+ip+"/SHOP/public//api/GioHang/"+infoData.getId();
                RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("result").trim().equals("success"))
                            {
                                Toast toast = Toast.makeText(mcontext, "Đã Xóa Thành Công: "+infoData.getTenSanPham(), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 400);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mcontext, "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        return params;
                    }

                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Conten-Tupe", "application/x-www-form-urlencoded");
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
                mData.remove(currentPosition);
                notifyItemRemoved(currentPosition);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
