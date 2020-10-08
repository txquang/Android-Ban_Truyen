package com.cit.test.giohang;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.test.R;

import java.util.List;

public class ViewHolderGioHang extends RecyclerView.ViewHolder{
    public List<GioHang_Info> users;


    protected ImageView viewAnhSP;
    protected TextView viewTenSP;
    protected TextView viewGiaSP;
    protected TextView viewSoLuong;
    //
    protected ImageButton IBCong;
    protected ImageButton IBTru;
    protected ImageButton IBXoa;
    protected ImageButton IBThem;
    protected LinearLayout cart_item;

    public ViewHolderGioHang(@NonNull View itemView) {
        super(itemView);
        viewAnhSP = itemView.findViewById(R.id.imageBook);
        viewTenSP = (TextView) itemView.findViewById(R.id.txtTen);
        viewGiaSP = (TextView) itemView.findViewById(R.id.txtGia);
        viewSoLuong = (TextView) itemView.findViewById(R.id.counterValue);
        IBCong = itemView.findViewById(R.id.imagephus);
        IBTru = itemView.findViewById(R.id.imageminus);
        IBXoa = itemView.findViewById(R.id.imagecancel);
        IBThem = itemView.findViewById(R.id.imagecancel);

//        IBTru.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                viewSoLuong.setText("1");
//                return false;
//            }
//        });
//        IBTru.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int SoLuong = Integer.parseInt(viewSoLuong.getText().toString().trim());
//                SoLuong = SoLuong - 1;
//                if(SoLuong <2)
//                    SoLuong = 1;
//                viewSoLuong.setText(String.valueOf(SoLuong));
//            }
//        });
//        IBCong.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                int SoLuong = Integer.parseInt(viewSoLuong.getText().toString().trim());
//                SoLuong = SoLuong + 10;
//                viewSoLuong.setText(String.valueOf(SoLuong));
//                return false;
//            }
//        });
//        IBCong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int SoLuong = Integer.parseInt(viewSoLuong.getText().toString().trim());
//                SoLuong = SoLuong + 1;
//                viewSoLuong.setText(String.valueOf(SoLuong));
//            }
//        });
    }
}
