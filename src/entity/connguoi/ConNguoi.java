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
public abstract class ConNguoi {

    private String hoTen;
    private String soDT;
    private String email;
    private LocalDate namSinh;
    private DiaChi diaChi;
    private boolean gioiTinh;

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) throws Exception {

        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) throws Exception {
        this.hoTen = hoTen;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) throws Exception {
        if (soDT.trim().length() > 0) {
            this.soDT = soDT;
        } else {
            throw new Exception("Số điện thoại không được rỗng !");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email.trim().length() > 0) {
            this.email = email;
        } else {
            throw new Exception("Email không được rỗng !");
        }
    }

    public LocalDate getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(LocalDate namSinh) throws Exception {
        this.namSinh = namSinh;
    }

    public ConNguoi(String hoTen, String soDT, String email, LocalDate namSinh, DiaChi diaChi, boolean gioiTinh) throws Exception {
        setHoTen(hoTen);
        setEmail(email);
        setSoDT(soDT);
        setNamSinh(namSinh);
        setDiaChi(diaChi);
        this.gioiTinh = gioiTinh;
    }

    public ConNguoi() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.soDT);
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
        final ConNguoi other = (ConNguoi) obj;
        return Objects.equals(this.soDT, other.soDT);
    }

}
