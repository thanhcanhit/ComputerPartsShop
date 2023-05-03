/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.connguoi;

import java.time.LocalDate;
import java.util.Objects;
import entity.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class NhanVien extends ConNguoi {
    private String maNV;
    private String chucDanh;
    private boolean trangThai;
    private TaiKhoan taiKhoan;
    
    public NhanVien(String maNV, String chucDanh, String hoTen, String soDT, String email, LocalDate namSinh, DiaChi diaChi, boolean gioiTinh,boolean trangThai) throws Exception {
        super(hoTen, soDT, email, namSinh, diaChi, gioiTinh);
        this.maNV = maNV;
        this.chucDanh = chucDanh;
        this.trangThai = trangThai;

    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
   

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }


    public NhanVien(String maNV) {
        this.maNV=maNV;
    }
    
    

    public NhanVien(String maNV, String chucDanh,String hoTen, String soDT, String email, LocalDate namSinh,DiaChi diaChi,boolean gioiTinh) throws Exception{
        super(hoTen,soDT,email,namSinh,diaChi,gioiTinh);
        setChucNang(chucDanh);
        setMaNV(maNV);
        this.trangThai=true;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.maNV);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }
    

 
}
