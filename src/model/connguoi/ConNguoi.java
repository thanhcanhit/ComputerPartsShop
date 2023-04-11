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
public class ConNguoi {

    private String hoTen;
    private String soDT;
    private String email;
    private String namSinh;
    private DiaChi diaChi;

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) throws Exception{
 
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
        if(soDT.trim().length()>0)
            this.soDT = soDT;
        else
            throw new Exception("Số điện thoại không được rỗng !");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if(email.trim().length()>0)
            this.email = email;
        else
            throw new Exception("Email không được rỗng !");
    }


    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) throws Exception {
        if(namSinh.trim().length()>0)
            this.namSinh = namSinh;
        else
            throw new Exception("Năm sinh không được rỗng !");
    }

    public ConNguoi(String hoTen, String soDT, String email, String namSinh,DiaChi diaChi) throws Exception{
        setHoTen(hoTen);
        setEmail(email);
        setSoDT(soDT);
        setNamSinh(namSinh);
        setDiaChi(diaChi);
    }
    public ConNguoi() {
    }
    
    
    
}
