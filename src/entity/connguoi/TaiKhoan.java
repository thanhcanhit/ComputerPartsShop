/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.connguoi;

import java.util.ArrayList;

/**
 *
 * @author macbookk
 */
public class TaiKhoan {

    private String soTK;
    private String passWord;

    public String getSoTK() {
        return soTK;
    }

    public void setSoTK(String soTK) throws Exception {
        if (soTK.trim().length() > 0) {
            this.soTK = soTK;
        } else {
            throw new Exception("Số tài khoản không được rỗng !");
        }
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) throws Exception {
        if (passWord.trim().length() > 0) {
            this.passWord = passWord;
        } else {
        }
    }

    public TaiKhoan(String soTK, String passWord) throws Exception {
        setPassWord(passWord);
        setSoTK(soTK);
    }

    public TaiKhoan() {
    }

}
