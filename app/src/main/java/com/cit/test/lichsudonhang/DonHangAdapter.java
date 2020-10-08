package com.cit.test.lichsudonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.test.R;

import java.text.DecimalFormat;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<ViewHolderDonHang> {
    private Context mcontext;
    private List<DonHang_Info> mData;

    public DonHangAdapter(Context mcontext, List<DonHang_Info> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public ViewHolderDonHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (mcontext);
        View view = inflater.inflate (R.layout.item_orderhistory, parent, false);
        return new ViewHolderDonHang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDonHang holder, int position) {

        holder.viewDonHang.append(mData.get(position).getDonHang());
        holder.viewNgayDat.append(mData.get(position).getNgayDat());
        if( mData.get(position).getNgayThanhToan().length() <1 )
            holder.viewNgayThanhtoan.setText("");
        else
            holder.viewNgayThanhtoan.append(mData.get(position).getNgayThanhToan());
        if(mData.get(position).getTrangThai() == 1)
            holder.viewTrangThai.setText("Da giao hang >");
        else
            holder.viewTrangThai.setText("Dang xu ly >");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.viewTongTien.append(decimalFormat.format(mData.get(position).getTongTien())+" ₫");

        //su kien number button
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
