/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.share;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author thanh
 */
public class Utility {

    public static String sinhMaTang(String lastMa, String tienTo, int soHauTo) {
        int num = Integer.parseInt(lastMa.substring(tienTo.length()));
        num++;

        // Mac dinh la 3
        String nextNum = "";
        switch (soHauTo) {
            case 2 ->
                nextNum = String.format("%02d", num);
            case 4 ->
                nextNum = String.format("%04d", num);
            case 5 ->
                nextNum = String.format("%05d", num);
            default -> {
                nextNum = String.format("%03d", num);
            }
        }
        return tienTo + nextNum;
    }

    public static String getVND(double money) {
        NumberFormat vnd = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
        return vnd.format(money);
    }
}
