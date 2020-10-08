package com.cit.test.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cit.test.R;
import com.cit.test.activity.ChiTietSPActivity;
import com.cit.test.dangnhap.LoginActivity;
import com.cit.test.giohang.GioHangAdapter;
import com.cit.test.giohang.GioHang_Info;
import com.cit.test.ketnoi.NetworkResponseListener;
import com.cit.test.ketnoi.TaskChiTietSanPham;
import com.cit.test.ketnoi.TaskGioHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangFragment extends Fragment implements NetworkResponseListener {

    private RecyclerView RC_GH;
    private ProgressBar progressBar;

    TextView viewTieuDe, viewTongTien;
    Button viewMuaHang;

    String URL, MaDH, MaNV;
    int TongTien = 0;

    public GioHangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RC_GH = view.findViewById(R.id.GH_RC);

        //Now Lets Show a Progressbar
        progressBar=view.findViewById(R.id.progress);
        viewTieuDe = view.findViewById(R.id.GH_TieuDe);
        viewTongTien = view.findViewById(R.id.GH_gia);
        viewMuaHang = view.findViewById(R.id.GH_BT_MuaHang);

        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences",MODE_PRIVATE);
        MaDH = sharedPref.getString("madh", "");
        //
        viewMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( MaDH.length() < 7 ) {
                    Toast.makeText(getContext(), "Vui lòng đăng   nhập", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MuaHang();
                }

            }
        });
        if( MaDH.length() < 7 ) {
            Toast.makeText(getContext(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            viewTieuDe.setText("Giỏ Hàng (Đăng Nhập)");
            return;
        }
        else
        {
            URL = "http://"+getString(R.string.DiaChi)+"/SHOP/public/api/GioHang/"+MaDH;
            TaskGioHang loadDataTask=new TaskGioHang(GioHangFragment.this, URL);
            loadDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }


    }

    @Override
    public void SuccessData(String data) {
        progressBar.setVisibility(View.GONE);
        try {
            List<GioHang_Info> imageModels=new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++)
            {
                imageModels.add(
                       new GioHang_Info(
                               jsonArray.getJSONObject(i).getString("masp"),//Masp
                               jsonArray.getJSONObject(i).getString("tensp"),//ten
                               "http://"+getString(R.string.DiaChi)+"/SHOP/public/Anh/"+
                                    jsonArray.getJSONObject(i).getString("tenanh"),//anh
                               jsonArray.getJSONObject(i).getInt("id"),//id
                               jsonArray.getJSONObject(i).getInt("soluong"),//soluong
                               jsonArray.getJSONObject(i).getInt("dongia")//gia
                       )
                );

                this.TongTien += jsonArray.getJSONObject(i).getInt("thanhtien");
            }

            GioHangAdapter imageItemAdapter=new GioHangAdapter(getContext(),imageModels);
            RC_GH.setLayoutManager(new LinearLayoutManager(getActivity()));
            RC_GH.setAdapter(imageItemAdapter);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String tt = decimalFormat.format(Double.valueOf(TongTien))+" ₫";
        if(TongTien > 0 )
            viewTongTien.append(tt);
        else
            viewTongTien.setText("0");
    }
    @Override
    public void FailedData() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Failed to Load Data on Fragment 2", Toast.LENGTH_SHORT).show();
    }

    public void MuaHang()
    {
        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences",MODE_PRIVATE);
        MaDH = sharedPref.getString("madh", "");
        MaNV = sharedPref.getString("manv", "");

        URL = "http://" + getString(R.string.DiaChi) + "/SHOP/public/api/LichSu";
        //Toast.makeText(LoginActivity.this, TaiKhoan+"   "+Matkhau, Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getContext(),  "Mua Hàng Thành Công", Toast.LENGTH_SHORT).show();

                    JSONObject jsonObject = new JSONObject(response);
                    SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("madh", jsonObject.getString("madh").trim() );
                    editor.commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("madh", MaDH);
                params.put("manv", MaNV);
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
}