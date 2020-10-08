package com.cit.test.sanpham;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cit.test.activity.ChiTietSPActivity;
import com.cit.test.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<ViewHolderSanPham>{
    private Context mcontext;
    private List<SanPham_Info> mData = new ArrayList<>();
    private List<SanPham_Info> mDataFilter = new ArrayList<>();
    //
    Dialog myDialog;//hien hop thoai
    public SanPhamAdapter(Context mcontext, List<SanPham_Info> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
        this.mDataFilter = mData;
    }
    //

    @NonNull
    @Override
    public ViewHolderSanPham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        ViewHolderSanPham viewHolder = new ViewHolderSanPham(item);
        return new ViewHolderSanPham(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSanPham holder, final int position) {

        SanPham_Info imageModel=mDataFilter.get(position);
        ViewHolderSanPham imageItemHolder=(ViewHolderSanPham) holder;
        imageItemHolder.viewTenSP.setText(imageModel.getTenSP());//ten
        imageItemHolder.viewTheLoai.setText(imageModel.getTheLoaiSP());//TheLoai
        imageItemHolder.viewDanhGia.setText(String.valueOf(imageModel.getDanhGiaSP()));//DanhGia
        imageItemHolder.viewSoLuong.setText("Còn lại: "+String.valueOf(imageModel.getSoLuongSP()));//Soluong
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        imageItemHolder.viewGiaSP.setText(decimalFormat.format(imageModel.getGiaSP())+" ₫");//Gia

        //Anh
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.drawable.anh_tai);
        requestOptions.error(R.drawable.anh_loi);

        Glide.with(mcontext)
                .load(imageModel.getAnhSP())
                .apply(requestOptions)
                .into(imageItemHolder.viewAnhSP);

        //Xem chi tiet
        holder.viewLinear.setAnimation(AnimationUtils.loadAnimation(mcontext,R.anim.fade_scale_animation));
        holder.viewAnhSP.setAnimation(AnimationUtils.loadAnimation(mcontext,R.anim.fade_transition_animation));
        holder.viewLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ChiTietSPActivity.class);

                intent.putExtra("MaSP",mDataFilter.get(position).getMaSP());
                intent.putExtra("TenSach",mDataFilter.get(position).getTenSP());
                intent.putExtra("DanhGia",mDataFilter.get(position).getDanhGiaSP());
                intent.putExtra("Gia",mDataFilter.get(position).getGiaSP());
                intent.putExtra("AnhSP",mDataFilter.get(position).getAnhSP());
                mcontext.startActivity(intent);
            }
        });
    }


    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {

                    mDataFilter = mData ;

                }
                else {
                    List<SanPham_Info> lstFiltered = new ArrayList<>();
                    for (SanPham_Info row : mData) {

                        if (row.getTenSP().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }

                    }

                    mDataFilter = lstFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFilter;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                mDataFilter = (List<SanPham_Info>) results.values;
                notifyDataSetChanged();

            }
        };




    }


    @Override
    public int getItemCount() {
        return mDataFilter.size();
    }
}
