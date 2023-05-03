/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.connguoi;

import entity.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class NhaCungCap {

    private String maNCC;
    private String tenNCC;
    private DiaChi diaChi;
    private String soDT;
    private String maSoThue;
    private String eMail;

    public NhaCungCap(String maNCC) {
        this.maNCC = maNCC;
    }

    public NhaCungCap(String maNCC, String tenNCC, DiaChi diaChi, String soDT, String maSoThue, String eMail) throws Exception {
        setMaNCC(maNCC);
        setTenNCC(tenNCC);
        setSoDT(soDT);
        setMaSoThue(maSoThue);
        setEMail(eMail);
        setDiaChi(diaChi);
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
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

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) throws Exception {
        if (maSoThue.trim().length() > 0) {
            this.maSoThue = maSoThue;
        } else {
            throw new Exception("Mã số thuế không được rỗng !");
        }

    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) throws Exception {
        if (eMail.trim().length() > 0) {
            this.eMail = eMail;
        } else {
            throw new Exception("Email không được rỗng !");
        }
    }

    public NhaCungCap() {
    }

}
