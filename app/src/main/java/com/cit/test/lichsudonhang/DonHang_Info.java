package com.cit.test.lichsudonhang;

public class DonHang_Info {
    private String MaDonHang,DonHang,NgayDat,NgayThanhToan;
    private int TongTien,TrangThai;

    public DonHang_Info(String maDonHang, String donHang, String ngayDat, String ngayThanhToan, int tongTien, int trangThai) {
        MaDonHang = maDonHang;
        DonHang = donHang;
        NgayDat = ngayDat;
        NgayThanhToan = ngayThanhToan;
        TongTien = tongTien;
        TrangThai = trangThai;
    }

    public String getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        MaDonHang = maDonHang;
    }

    public String getDonHang() {
        return DonHang;
    }

    public void setDonHang(String donHang) {
        DonHang = donHang;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        NgayThanhToan = ngayThanhToan;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }
}
