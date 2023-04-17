/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.connguoi;

import java.time.LocalDate;
import model.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class KhachHang extends ConNguoi {
    private String maKH;
    private String maSoThue;
    private int diemThanhVien;

    public int getDiemThanhVien() {
        return diemThanhVien;
    }

    public void setDiemThanhVien(int diemThanhVien) {
        if(diemThanhVien>0)
            this.diemThanhVien = diemThanhVien;
        else
            this.diemThanhVien = 0;
    }

    public KhachHang(String maKH, String maSoThue,String hoTen, String soDT, String email, LocalDate namSinh,DiaChi diaChi,boolean gioiTinh,int diemThanhVien) throws Exception {
        super(hoTen,soDT,email,namSinh,diaChi,gioiTinh);
        setMaKH(maKH);
        setMaSoThue(maSoThue);
        setDiemThanhVien(diemThanhVien);
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) throws Exception {
        if(maKH.trim().length()>0)
            this.maKH = maKH;
        else
            throw new Exception("Mã khách hàng không được rỗng !");
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue)  {
        if(maSoThue!=null)
            this.maSoThue = maSoThue;
        else
            this.maSoThue = "Null";
    }

    public KhachHang(String maKH) {
        this.maKH=maKH;
    }
    
}
