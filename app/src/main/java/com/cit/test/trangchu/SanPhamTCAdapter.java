package com.cit.test.trangchu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cit.test.activity.ChiTietSPActivity;
import com.cit.test.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SanPhamTCAdapter extends RecyclerView.Adapter<ViewHolderSanPhamTC>{
    private Context mcontext;
    private List<SanPhamTC_Info> mData = new ArrayList<>();
    //
    public SanPhamTCAdapter(Context mcontext, List<SanPhamTC_Info> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }
    //
    @NonNull
    @Override
    public ViewHolderSanPhamTC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchu,parent,false);
        ViewHolderSanPhamTC viewHolder = new ViewHolderSanPhamTC(item);
        return new ViewHolderSanPhamTC(item);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSanPhamTC holder, final int position) {

        SanPhamTC_Info imageModel=mData.get(position);
        ViewHolderSanPhamTC imageItemHolder=(ViewHolderSanPhamTC) holder;
        imageItemHolder.viewTenSP.setText(imageModel.getTenSanPham());//ten
        imageItemHolder.viewSaoSP.setText(String.valueOf(mData.get(position).getSao()));//sao
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        imageItemHolder.viewGiaSP.setText(decimalFormat.format(mData.get(position).getGiaSanPham())+" ₫");//gia

        //Anh
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.drawable.anh_tai);
        requestOptions.error(R.drawable.anh_loi);

        Glide.with(mcontext)
                .load(imageModel.getAnhSP())
                .apply(requestOptions)
                .into(imageItemHolder.viewAnhSP);

        //Xem chi tiet
        holder.viewLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ChiTietSPActivity.class);

                intent.putExtra("MaSP",mData.get(position).getMaSanPham());
                intent.putExtra("TenSach",mData.get(position).getTenSanPham());
                intent.putExtra("DanhGia",mData.get(position).getSao());
                intent.putExtra("Gia",mData.get(position).getGiaSanPham());
                intent.putExtra("AnhSP",mData.get(position).getAnhSP());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
