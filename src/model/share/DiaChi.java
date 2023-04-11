/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.share;

/**
 *
 * @author HP
 */
public class DiaChi {
    private String so;
    private String duong;
    private String quan;
    private String thanhPho;
    private String quocGia;

    public DiaChi(String so, String duong, String quan, String thanhPho, String quocGia) {
        this.so = so;
        this.duong = duong;
        this.quan = quan;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getDuong() {
        return duong;
    }

    public void setDuong(String duong) {
        this.duong = duong;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    @Override
    public String toString() {
        return "DiaChi{" + "so=" + so + ", duong=" + duong + ", quan=" + quan + ", thanhPho=" + thanhPho + ", quocGia=" + quocGia + '}';
    }
    
    
}
