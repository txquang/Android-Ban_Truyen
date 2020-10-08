package com.cit.test.sanpham;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.test.R;

import java.util.List;

class ViewHolderSanPham extends RecyclerView.ViewHolder{
    public List<SanPham_Info> users;
    protected ImageView viewAnhSP;
    protected TextView viewTenSP;
    protected TextView viewTheLoai;
    protected TextView viewDanhGia;
    protected TextView viewGiaSP;
    protected TextView viewSoLuong;
    protected LinearLayout viewLinear;

    //
    public ViewHolderSanPham(@NonNull View itemView) {
        super(itemView);
        viewAnhSP = itemView.findViewById(R.id.IT_IM_Anh);
        viewTenSP = (TextView) itemView.findViewById(R.id.IT_TV_Ten);
        viewTheLoai = (TextView) itemView.findViewById(R.id.IT_TV_TheLoai);
        viewDanhGia = itemView.findViewById(R.id.IT_TV_DanhGia);
        viewGiaSP = (TextView) itemView.findViewById(R.id.IT_TV_Gia);
        viewSoLuong = (TextView) itemView.findViewById(R.id.IT_TV_SL);
        //
        viewLinear = itemView.findViewById(R.id.IT_Linear);
        //

    }
}
