package com.cit.test.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.cit.test.R;
import com.cit.test.activity.LichSuDonHangActivity;
import com.cit.test.dangnhap.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaiKhoanFragment extends Fragment {
    //
    View v;
    private ImageView IVAnh;
    private CardView LichSuDH, CVTaiKhoan, CVDangXuat;
    TextView viewTenND;
    public TaiKhoanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //
        v = inflater.inflate(R.layout.fragment_account, container, false);
        AnhXa();
        IVAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(),LoginActivity.class);//chu y
                startActivity(mIntent);
            }
        });
        LichSuDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LichSuDonHangActivity.class));
            }
        });
        CVTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(),LoginActivity.class);//chu y
                startActivity(mIntent);
            }
        });
        CVDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", getContext().MODE_PRIVATE);
                sharedPref.edit().clear().commit();//Xoa du lieu
                Toast.makeText(getContext(), "Đăng Xuất Thành Công", Toast.LENGTH_SHORT).show();
            }
        });



        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", getContext().MODE_PRIVATE);
        String tennv = sharedPref.getString("tennv", "");
        if(tennv.length() > 2)
            viewTenND.setText(tennv);
        else
            viewTenND.setText("Đăng Nhập/Đăng Ký");
        Toast.makeText(getContext(), "Kiểm Tra"+tennv+"..", Toast.LENGTH_SHORT).show();

        return v;
    }

    private void AnhXa() {
        IVAnh = v.findViewById(R.id.Acc_Avatar);
        LichSuDH = v.findViewById(R.id.AC_CV_DonHang);
        CVTaiKhoan = v.findViewById(R.id.AC_CV_TaiKhoan);
        CVDangXuat = v.findViewById(R.id.AC_CV_DangXuat);
        viewTenND = v.findViewById(R.id.Acc_TenND);
    }
}
