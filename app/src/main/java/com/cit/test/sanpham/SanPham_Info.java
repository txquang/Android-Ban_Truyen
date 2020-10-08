package com.cit.test.sanpham;

public class SanPham_Info {
    private String MaSP, AnhSP, TenSP, TheLoaiSP;
    private int SoLuongSP, GiaSP, DanhGiaSP;

    public SanPham_Info(String maSP, String anhSP, String tenSP, String theLoaiSP, int soLuongSP, int giaSP, int danhGiaSP) {
        MaSP = maSP;
        AnhSP = anhSP;
        TenSP = tenSP;
        TheLoaiSP = theLoaiSP;
        SoLuongSP = soLuongSP;
        GiaSP = giaSP;
        DanhGiaSP = danhGiaSP;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getAnhSP() {
        return AnhSP;
    }

    public void setAnhSP(String anhSP) {
        AnhSP = anhSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getTheLoaiSP() {
        return TheLoaiSP;
    }

    public void setTheLoaiSP(String theLoaiSP) {
        TheLoaiSP = theLoaiSP;
    }

    public int getSoLuongSP() {
        return SoLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        SoLuongSP = soLuongSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getDanhGiaSP() {
        return DanhGiaSP;
    }

    public void setDanhGiaSP(int danhGiaSP) {
        DanhGiaSP = danhGiaSP;
    }
}
