package com.cit.test.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.cit.test.fragment.GioHangFragment;
import com.cit.test.R;
import com.cit.test.fragment.SanPhamFragment;
import com.cit.test.fragment.TaiKhoanFragment;
import com.cit.test.fragment.TrangChuFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Home extends AppCompatActivity {
    private static String TAG = Home.class.getSimpleName();
    ChipNavigationBar chipNavigationBar;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        chipNavigationBar = findViewById(R.id.botton_nav);

        if (savedInstanceState == null)
        {
            chipNavigationBar.setItemSelected(R.id.home,true);
            fragmentManager = getSupportFragmentManager();
            TrangChuFragment homeFragment = new TrangChuFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,homeFragment)
                    .commit();
        }
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i)
                {
                    case R.id.home:
                        fragment = new TrangChuFragment();
                        break;
                    case R.id.home1:
                        fragment = new SanPhamFragment();
                        break;
                    case R.id.home2:
                        fragment = new GioHangFragment();
                        break;
                    case R.id.home3:
                        fragment = new TaiKhoanFragment();
                        break;
                }
                if (fragment != null)
                {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .commit();
                }
                else
                {
                    Log.e(Home.TAG,"loi ttao fragmen");
                }
            }
        });
    }


}
