package com.cit.test.dangnhap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cit.test.R;
import com.cit.test.activity.Home;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button BT_Login;
    TextView TV_QuenMK,TV_DangKy;
    EditText ET_TaiKhoan,ET_MatKhau;
    Integer TongTien;
    String TaiKhoan, MatKhau, URL, MaNV, TenNV, DiaChi, MaDH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();//goi ham
        BT_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra ki tu khong
                TaiKhoan = ET_TaiKhoan.getText().toString().trim();
                MatKhau = ET_MatKhau.getText().toString().trim();
                if(TextUtils.isEmpty(TaiKhoan)) {
                    ET_TaiKhoan.setError("Tài Khoản Không Được Để Trống");
                    return;
                }
                if(TextUtils.isEmpty(MatKhau)) {
                    ET_MatKhau.setError("Mật Khẩu Không Được Để Trống");
                    return;
                }
                //end
               sendLoginInfo(TaiKhoan, MatKhau);

            }
        });
        TV_QuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Tính Năng Chưa Phát Trển", Toast.LENGTH_SHORT).show();
            }
        });
        TV_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RegisterActivity.class));
            }
        });
    }
    public void AnhXa()
    {
        BT_Login  = findViewById(R.id.LG_BT_Dangnhap);
        TV_QuenMK = findViewById(R.id.LG_TV_QuenMK);
        ET_TaiKhoan = findViewById(R.id.LG_ET_Email);
        ET_MatKhau = findViewById(R.id.LG_ET_Pass);
        TV_DangKy = findViewById(R.id.LG_TV_DangKy);

    }
    public  void sendLoginInfo(final String TaiKhoan, final String Matkhau) {

        URL = "http://" + getString(R.string.DiaChi) + "/SHOP/public/api/DangNhap/id";
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    KiemTra(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", TaiKhoan);
                params.put("password", Matkhau);
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
    public void KiemTra(JSONObject Json) throws JSONException {

        if (Json.getString("result").trim().equals("success"))
        {
            SharedPreferences sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("taikhoan", Json.getString("taikhoan").trim() );
            editor.putString("matkhau", Json.getString("matkhau").trim() );
            editor.putString("manv", Json.getString("manv").trim() );
            editor.putString("tennv", Json.getString("tennv").trim() );
            editor.putString("diachi", Json.getString("diachi").trim() );
            editor.putString("madh", Json.getString("madh").trim() );
            editor.putInt("tongtien", Json.getInt("tongtien"));
            editor.commit();
            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, Home.class));
        }
        else
            Toast.makeText(LoginActivity.this, "Sai Tài Khoản Hoặc Mật Khẩu !", Toast.LENGTH_SHORT).show();
    }
}
