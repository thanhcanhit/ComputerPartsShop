/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.connguoi;

/**
 *
 * @author macbookk
 */
public class KhachHang extends ConNguoi {
    private String maKH;
    private String maSoThue;

    public KhachHang(String maKH, String maSoThue) throws Exception {
        setMaKH(maKH);
        setMaSoThue(maSoThue);
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

    public void setMaSoThue(String maSoThue) throws Exception  {
        if(maSoThue.trim().length()>0)
            this.maSoThue = maSoThue;
        else
            throw new Exception("Mã số thuế không được rỗng !");
    }

    public KhachHang() {
    }
    
}
