package com.cit.test.lichsudonhang;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.test.R;

import java.util.List;

public class ViewHolderDonHang extends RecyclerView.ViewHolder{
    public List<DonHang_Info> users;
    protected TextView viewDonHang;
    protected TextView viewNgayDat;
    protected TextView viewNgayThanhtoan;
    protected TextView viewTrangThai;
    protected TextView viewTongTien;
    //
    protected LinearLayout cart_item;
    public ViewHolderDonHang(@NonNull View itemView) {
        super(itemView);
        viewDonHang = (TextView) itemView.findViewById(R.id.DH_TV_DonHang);
        viewNgayDat = (TextView) itemView.findViewById(R.id.DH_TV_NgayDat);
        viewNgayThanhtoan = (TextView) itemView.findViewById(R.id.DH_TV_NgayThanhToan);
        viewTrangThai = (TextView) itemView.findViewById(R.id.DH_TV_TrangThai);
        viewTongTien = (TextView) itemView.findViewById(R.id.DH_TV_TongTien);
        //

    }
}
