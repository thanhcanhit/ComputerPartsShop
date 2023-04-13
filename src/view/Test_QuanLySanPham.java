/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.JFrame;

/**
 *
 * @author nxnam
 */
public class Test_QuanLySanPham extends JFrame{

    public Test_QuanLySanPham() {
        setSize(1000, 700);
        this.add(new Panel_QuanLySanPham());
    }
    
    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        new Test_QuanLySanPham().setVisible(true);
    }
    
}
