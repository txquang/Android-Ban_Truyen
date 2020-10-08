package com.cit.test.giohang;

public class GioHang_Info {
    private String MaSanPham, TenSanPham, AnhSP;
    private int id, SoLuongSP, GiaSanPham;

    public GioHang_Info(String maSanPham, String tenSanPham, String anhSP, int id, int soLuongSP, int giaSanPham) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        AnhSP = anhSP;
        this.id = id;
        SoLuongSP = soLuongSP;
        GiaSanPham = giaSanPham;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoLuongSP() {
        return SoLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        SoLuongSP = soLuongSP;
    }

    public int getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        GiaSanPham = giaSanPham;
    }
}
