package com.cit.test.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cit.test.R;
import com.cit.test.ketnoi.NetworkResponseListener;
import com.cit.test.lichsudonhang.DonHangAdapter;
import com.cit.test.lichsudonhang.DonHang_Info;
import com.cit.test.sanpham.SanPhamAdapter;
import com.cit.test.sanpham.SanPham_Info;

import java.util.ArrayList;
import java.util.List;

public class LichSuDonHangActivity extends AppCompatActivity implements NetworkResponseListener {
    private RecyclerView recyclerView;
    private List<DonHang_Info> lstSP;

    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);


        recyclerView = findViewById(R.id.DH_RC);
        lstSP = new ArrayList<>();
        add();
        DonHangAdapter sanPhamAdapter = new DonHangAdapter(this,lstSP);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sanPhamAdapter);
    }

    private void add() {
        lstSP.add(new DonHang_Info("21324","31231242","12 thg 12 2020",
                "12 thg 12 2020",120000,1));
        lstSP.add(new DonHang_Info("21324","31231242","12 thg 12 2020",
                "",120000,0));
    }

    @Override
    public void SuccessData(String data) {

    }

    @Override
    public void FailedData() {

    }
}
