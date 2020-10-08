package com.cit.test.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.cit.test.activity.Home;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button BT_Login;
    TextView TV_DangNhap;
    EditText ET_TaiKhoan,ET_MatKhau,ET_MatKhauAG;

    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        BT_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra ki tu khong
                String strUserName = ET_TaiKhoan.getText().toString().trim();
                String strUserName1 = ET_MatKhau.getText().toString().trim();
                String strUserName3 = ET_MatKhauAG.getText().toString().trim();
                if(TextUtils.isEmpty(strUserName)) {
                    ET_TaiKhoan.setError("Tài Khoản Không Được Để Trống");
                    return;
                }
                if(TextUtils.isEmpty(strUserName1)) {
                    ET_MatKhau.setError("Mật Khẩu Không Được Để Trống");
                    return;
                }
                if( !strUserName1.equals(strUserName3) ) {
                    ET_MatKhauAG.setError("Mật Khẩu Không Khớp");
                    return;
                }
                //end
                sendDangKyInfo(strUserName, strUserName1);
            }
        });
        TV_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LoginActivity.class));
                finish();
                onBackPressed();
            }
        });

    }

    private void AnhXa()
    {
        BT_Login  = findViewById(R.id.RS_BT_Dangnhap);
        ET_TaiKhoan = findViewById(R.id.RS_ET_Email);
        ET_MatKhau = findViewById(R.id.RS_ET_pass);
        ET_MatKhauAG = findViewById(R.id.RS_ET_passag);
        TV_DangNhap = findViewById(R.id.RS_TV_DangNhap);
    }

    public void sendDangKyInfo(final String TK, final String MK)
    {
        URL = "http://" + getString(R.string.DiaChi) + "/SHOP/public/api/DangNhap";
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

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
                ET_TaiKhoan.setError("Tài Khoản Đã Tồn Tại");
                //Toast.makeText(RegisterActivity.this, "Kiểm Tra Kết Nối Mạng", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", TK);
                params.put("password", MK);
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
    public void  KiemTra(JSONObject jsonObject) throws JSONException
    {
        if (jsonObject.getString("result").trim().equals("success"))
        {

            Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        else
            Toast.makeText(RegisterActivity.this, "Vui lòng kiểm tra lại !", Toast.LENGTH_SHORT).show();
    }

}
