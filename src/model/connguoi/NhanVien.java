/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.connguoi;

import model.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class NhanVien extends ConNguoi {
    private String maNV;
    private String chucDanh;
    

    public NhanVien() {
    }

    public NhanVien(String maNV, String chucDanh,String hoTen, String soDT, String email, String namSinh,DiaChi diaChi,boolean gioiTinh) throws Exception{
        super(hoTen,soDT,email,namSinh,diaChi,gioiTinh);
        setChucNang(chucDanh);
        setMaNV(maNV);
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) throws  Exception{
        if(maNV.trim().length()>0)
            this.maNV = maNV;
        else
            throw new Exception("Mã nhân viên không được rỗng !");
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucNang(String chucDanh) throws Exception {
        if(chucDanh.trim().length()>0)
            this.chucDanh=chucDanh;
        else
            throw new Exception("Chức danh không được rỗng !");
            
    }

 
}
