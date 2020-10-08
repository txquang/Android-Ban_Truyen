package com.cit.test.trangchu;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.test.R;

import java.util.List;

class ViewHolderSanPhamTC extends RecyclerView.ViewHolder{
    public List<SanPhamTC_Info> users;
    protected ImageView viewAnhSP;
    protected TextView viewTenSP;
    protected TextView viewGiaSP;
    protected TextView viewSaoSP;
    protected LinearLayout viewLinear;
    //
    protected LinearLayout item_sanpham;
    public ViewHolderSanPhamTC(@NonNull View itemView) {
        super(itemView);
        viewAnhSP = itemView.findViewById(R.id.IC_SPTC_Anh);
        viewTenSP = (TextView) itemView.findViewById(R.id.IC_SPTC_TenSach);
        viewSaoSP = (TextView) itemView.findViewById(R.id.IC_SPTC_Sao);
        viewGiaSP = (TextView) itemView.findViewById(R.id.IC_SPTC_Gia);
        //
        viewLinear = itemView.findViewById(R.id.IC_SPTC_Linear);
        //
        //item_sanpham = itemView.findViewById(R.id.sanpham_item);
    }
}
