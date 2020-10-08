package com.cit.test.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cit.test.R;
import com.cit.test.activity.TheLoaiActivity;
import com.cit.test.ketnoi.NetworkResponseListener;
import com.cit.test.ketnoi.TaskTrangChu;
import com.cit.test.trangchu.SanPhamTCAdapter;
import com.cit.test.trangchu.SanPhamTC_Info;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class TrangChuFragment extends Fragment implements NetworkResponseListener {

    //recyview
    private RecyclerView RC_TC;
    private CardView CV_KiNang, CV_TamLy, CV_TieuThuyet, CV_TruyenNgan;
    private ProgressBar progressBar;
    //end rc
    String URL;
    public TrangChuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);
        //Now Lets Show a Progressbar
        URL = "http://"+getString(R.string.DiaChi)+"/SHOP/public/api/SanPham";
        TaskTrangChu loadDataTask=new TaskTrangChu(TrangChuFragment.this, URL);
        loadDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        //
        CV_KiNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getContext(), TheLoaiActivity.class );
                intent.putExtra("MaTL", "TL19052020104033");
                intent.putExtra("TenTL","Kỹ Năng Sống");
                intent.putExtra("AnhTL",1);
                getContext().startActivity(intent);
            }
        });
        CV_TamLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getContext(), TheLoaiActivity.class );
                intent.putExtra("MaTL", "TL19052020104533");
                intent.putExtra("TenTL","Tâm Lý");
                intent.putExtra("AnhTL",2);
                getContext().startActivity(intent);
            }
        });
        CV_TieuThuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getContext(), TheLoaiActivity.class );
                intent.putExtra("MaTL", "TL19052020102033");
                intent.putExtra("TenTL","Tiểu Thuyết");
                intent.putExtra("AnhTL",3);
                getContext().startActivity(intent);
            }
        });
        CV_TruyenNgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getContext(), TheLoaiActivity.class );
                intent.putExtra("MaTL", "TL19052020103533");
                intent.putExtra("TenTL","Truyện Ngắn");
                intent.putExtra("AnhTL",4);
                getContext().startActivity(intent);
            }
        });

    }

    @Override
    public void SuccessData(String data) {

        progressBar.setVisibility(View.GONE);
        //
        try {
            List<SanPhamTC_Info> imageModels =new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<8;i++){
                imageModels.add(
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

            SanPhamTCAdapter imageItemAdapter=new SanPhamTCAdapter(getContext(),imageModels);
            RC_TC.setLayoutManager(new GridLayoutManager(getContext(),2));
            RC_TC.setAdapter(imageItemAdapter);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    @SuppressLint("WrongConstant")
    @Override
    public void FailedData() {

        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Failed to Load Data on Fragment :"+getString(R.string.DiaChi), 10000).show();

    }

    public void AnhXa(View view)
    {
        progressBar=view.findViewById(R.id.progress);
        RC_TC = view.findViewById(R.id.RC_TC);
        CV_KiNang = view.findViewById(R.id.DM_CV_KiNang);
        CV_TamLy = view.findViewById(R.id.DM_CV_TamLy);
        CV_TieuThuyet = view.findViewById(R.id.DM_CV_TieuThuyet);
        CV_TruyenNgan = view.findViewById(R.id.DM_CV_TruyenNgan);
    }
}

