package com.cit.test.trangchu;

public class SanPhamTC_Info {
    private String MaSanPham,TenSanPham, AnhSP;
    private int Sao,GiaSanPham;

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getAnhSP() {
        return AnhSP;
    }

    public void setAnhSP(String anhSP) {
        AnhSP = anhSP;
    }

    public int getSao() {
        return Sao;
    }

    public void setSao(int sao) {
        Sao = sao;
    }

    public int getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public SanPhamTC_Info(String maSanPham, String tenSanPham, String anhSP, int sao, int giaSanPham) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        AnhSP = anhSP;
        Sao = sao;
        GiaSanPham = giaSanPham;
    }
}
