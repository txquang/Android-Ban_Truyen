package com.cit.test.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cit.test.R;
import com.cit.test.ketnoi.NetworkResponseListener;
import com.cit.test.ketnoi.TaskTheLoai;
import com.cit.test.trangchu.SanPhamTCAdapter;
import com.cit.test.trangchu.SanPhamTC_Info;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiActivity extends AppCompatActivity implements NetworkResponseListener {
    TextView viewTheLoai, viewSolLuong;
    LinearLayout viewAnhSP;
    //recyview
    private RecyclerView RC_TL;
    private ProgressBar progressBar;
    String TenTL, URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);

        AnhXa();
        Intent intent = getIntent();
        String MaTL = intent.getExtras().getString("MaTL");
        TenTL= intent.getExtras().getString("TenTL");
        Integer AnhTL = intent.getExtras().getInt("AnhTL");

        switch (AnhTL) {
            case 1:
                viewAnhSP.setBackgroundResource(R.drawable.im_kynangsong);
                break;
            case 2:
                viewAnhSP.setBackgroundResource(R.drawable.im_tamly);
                break;
            case 3:
                viewAnhSP.setBackgroundResource(R.drawable.im_tieuthuyet);
                break;
            default:
                viewAnhSP.setBackgroundResource(R.drawable.im_truyenngan);
        }
        URL = "http://"+getString(R.string.DiaChi)+"/SHOP/public/api/SanPham/"+MaTL;
        TaskTheLoai loadDataTask=new TaskTheLoai(TheLoaiActivity.this, URL);
        loadDataTask.execute();


    }

    private void AnhXa() {
        progressBar= findViewById(R.id.progress);
        RC_TL = findViewById(R.id.TL_RC_TheLoai);
        viewAnhSP = findViewById(R.id.TL_Liner);
        viewTheLoai = findViewById(R.id.TL_TV_TheLoai);
        viewSolLuong = findViewById(R.id.TL_TV_SoLuong);
    }

    @Override
    public void SuccessData(String data) {
        progressBar.setVisibility(View.GONE);
        //
        try {
            List<SanPhamTC_Info> SanPhamTL =new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++){
                SanPhamTL.add(
                        new SanPhamTC_Info(

                                jsonArray.getJSONObject(i).getString("masp"),
                                jsonArray.getJSONObject(i).getString("tensp"),
                                "http://"+getString(R.string.DiaChi)+"/SHOP/public/Anh/"
                                        +jsonArray.getJSONObject(i).getString("tenanh"),
                                jsonArray.getJSONObject(i).getInt("sao"),
                                jsonArray.getJSONObject(i).getInt("gia")
                        )
                );
            }
            viewSolLuong.setText("Tổng Cộng Có: "+jsonArray.length()+" Cuốn Sách");
            viewTheLoai.setText(TenTL);
            SanPhamTCAdapter imageItemAdapter=new SanPhamTCAdapter(this,SanPhamTL);
            RC_TL.setLayoutManager(new GridLayoutManager(this,2));
            RC_TL.setAdapter(imageItemAdapter);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    @SuppressLint("WrongConstant")
    @Override
    public void FailedData() {
        //progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Vui Lòng Kiểm Tra IP Máy Tính: "+getString(R.string.DiaChi), 20000).show();
    }
}
