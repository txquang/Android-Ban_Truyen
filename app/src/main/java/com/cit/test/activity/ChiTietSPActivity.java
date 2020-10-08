package com.cit.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.cit.test.dangnhap.LoginActivity;
import com.cit.test.ketnoi.NetworkResponseListener;
import com.cit.test.ketnoi.TaskChiTietSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ChiTietSPActivity extends AppCompatActivity implements NetworkResponseListener {

    TextView viewTenSP, viewGiaSP, viewDanhGia, viewTheLoai, viewKichThuoc, viewSoTrang,
                viewNhaXuatBan, viewTacGia, viewTrongLuong, viewMoTa;
    ImageView viewAnhSP;
    Button viewMuaHang;

    String MaSP, TenSP, AnhSP, URL, MaDH;
    Integer DanhGia, Gia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsp);

        AnhXa();

        Intent intent = getIntent();
        MaSP = intent.getExtras().getString("MaSP");
        TenSP = intent.getExtras().getString("TenSach");
        DanhGia = intent.getExtras().getInt("DanhGia");
        Gia = intent.getExtras().getInt("Gia");
        AnhSP = intent.getExtras().getString("AnhSP");

        viewTenSP.setText(AnhSP);
        viewTenSP.setText(TenSP);
        viewDanhGia.setText(String.valueOf(DanhGia));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewGiaSP.setText(decimalFormat.format(Gia) + " ₫");
        //load anh
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.anh_tai).error(R.drawable.anh_loi);
        Glide.with(this).load(AnhSP).apply(requestOptions).into(viewAnhSP);

        URL = "http://"+getString(R.string.DiaChi)+"/SHOP/public/api/SanPham/"+MaSP;
        TaskChiTietSanPham loadDataTask=new TaskChiTietSanPham(ChiTietSPActivity.this, URL);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            loadDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            loadDataTask.execute();

        viewMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuaHang();
            }
        });
    }
    public void MuaHang()
    {
        SharedPreferences sharedPref = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        MaDH = sharedPref.getString("madh", "");
        if( MaDH.length() < 7 ) {
            startActivity(new Intent(ChiTietSPActivity.this, LoginActivity.class));
            return;
        }
        URL = "http://" + getString(R.string.DiaChi) + "/SHOP/public/api/GioHang";
        //Toast.makeText(LoginActivity.this, TaiKhoan+"   "+Matkhau, Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(ChiTietSPActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(ChiTietSPActivity.this,  "Đã Thêm Sản Phẩm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChiTietSPActivity.this, "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("madh", MaDH);
                params.put("masp", MaSP);
                params.put("soluong", "1");
                params.put("dongia", String.valueOf(Gia));
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

    private void AnhXa() {
        viewAnhSP = findViewById(R.id.CT_IG_Anh);
        viewTenSP = findViewById(R.id.CT_TV_Ten);
        viewDanhGia = findViewById(R.id.CT_TV_DanhGia);
        viewGiaSP = findViewById(R.id.CT_TV_Gia);
        viewTheLoai = findViewById(R.id.CT_TV_TheLoai);
        viewKichThuoc = findViewById(R.id.CT_TV_KichThuoc);
        viewSoTrang = findViewById(R.id.CT_TV_SoTrang);
        viewNhaXuatBan = findViewById(R.id.CT_TV_NXB);
        viewTacGia = findViewById(R.id.CT_TV_TacGia);
        viewTrongLuong = findViewById(R.id.CT_TV_TrongLuong);
        viewMoTa = findViewById(R.id.CT_TV_Mota);
        viewMuaHang = findViewById(R.id.CT_BT_MuaHang);
    }

    @Override
    public void SuccessData(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++)
            {
                viewTheLoai.setText( jsonArray.getJSONObject(i).getString("tentl"));
                viewKichThuoc.setText( jsonArray.getJSONObject(i).getString("kichthuoc"));
                viewSoTrang.setText( String.valueOf( jsonArray.getJSONObject(i).getInt("sotrang") ) );
                viewNhaXuatBan.setText(jsonArray.getJSONObject(i).getString("nhaxb"));
                viewTacGia.setText( jsonArray.getJSONObject(i).getString("tacgia"));
                viewTrongLuong.setText( String.valueOf( jsonArray.getJSONObject(i).getInt("trongluong") ));
                viewMoTa.setText( Html.fromHtml( jsonArray.getJSONObject(i).getString("tomtat") ) );
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    @SuppressLint("WrongConstant")
    @Override
    public void FailedData() {
        Toast.makeText(this, "Vui Lòng Kiểm Tra IP Máy Tính: "+getString(R.string.DiaChi), 20000).show();
    }
}
