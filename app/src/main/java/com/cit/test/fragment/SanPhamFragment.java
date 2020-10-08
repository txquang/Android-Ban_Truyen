package com.cit.test.fragment;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.test.R;
import com.cit.test.ketnoi.NetworkResponseListener;
import com.cit.test.ketnoi.TaskSanPham;
import com.cit.test.sanpham.SanPhamAdapter;
import com.cit.test.sanpham.SanPham_Info;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SanPhamFragment extends Fragment implements NetworkResponseListener {

    //recyview
    private RecyclerView RC_SP;
    private ProgressBar progressBar;
    String URL;
    SanPhamAdapter imageItemAdapter;

    EditText searchInput ;
    CharSequence search="";

    public SanPhamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_san_pham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RC_SP = view.findViewById(R.id.RC_SP);
        progressBar=view.findViewById(R.id.progress);
        searchInput = view.findViewById(R.id.edt_search);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                imageItemAdapter.getFilter().filter(s);
                search = s;


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        URL = "http://"+getString(R.string.DiaChi)+"/SHOP/public/api/SanPham";
        TaskSanPham loadDataTask=new TaskSanPham(SanPhamFragment.this, URL);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            loadDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            loadDataTask.execute();

    }

    @Override
    public void SuccessData(String data) {

        progressBar.setVisibility(View.GONE);
        /*jsonArray.getJSONObject(i).getString("author"),
                jsonArray.getJSONObject(i).getString("download_url")*/
        try {
            List<SanPham_Info> imageModels=new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++){
                imageModels.add(
                        new SanPham_Info(
                                 jsonArray.getJSONObject(i).getString("masp"),//Masp
                                "http://"+getString(R.string.DiaChi)+"/SHOP/public/Anh/"+
                                    jsonArray.getJSONObject(i).getString("tenanh"),//anh
                                 jsonArray.getJSONObject(i).getString("tensp"),//ten
                                 jsonArray.getJSONObject(i).getString("tentl"),//theloai
                                 jsonArray.getJSONObject(i).getInt("soluong"),//gia
                                 jsonArray.getJSONObject(i).getInt("gia"),//soluong
                                 jsonArray.getJSONObject(i).getInt("sao")//sao
                        )
                );
            }

            imageItemAdapter=new SanPhamAdapter(getContext(),imageModels);
            RC_SP.setLayoutManager(new LinearLayoutManager(getActivity()));
            RC_SP.setAdapter(imageItemAdapter);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public void FailedData() {

        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Failed to Load Data on Fragment 2", Toast.LENGTH_SHORT).show();

    }
}
