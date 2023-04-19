/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.share;

/**
 *
 * @author thanh
 */
public class Utility {

    public static String sinhMaTang(String lastMa, String tienTo, int soHauTo) {
        int num = Integer.parseInt(lastMa.substring(tienTo.length()));
        num++;

        // Mac dinh la 3
        String nextNum = String.format("%03d", num);
        if (soHauTo == 4) {
            nextNum = String.format("%04d", num);
        } else if (soHauTo == 5) {
            nextNum = String.format("%05d", num);
        } else if (soHauTo == 2) {
            nextNum = String.format("%02d", num);
        }
        return tienTo + nextNum;
    }
}
