/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class TestQuanLyDonHang extends JFrame{

    public TestQuanLyDonHang() {
        setSize(1000, 700);
        this.add(new Panel_QuanLyDonHang());
    }
    
    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        new TestQuanLyDonHang().setVisible(true);
    }
}
