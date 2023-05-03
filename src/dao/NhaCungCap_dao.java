/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.NhaCungCapInterface;
import java.util.ArrayList;
import entity.connguoi.NhaCungCap;
import java.sql.*;
import entity.share.ConnectDB;
import entity.share.DiaChi;

/**
 *
 * @author thanh
 */
public class NhaCungCap_dao implements NhaCungCapInterface {

    @Override
    public ArrayList<NhaCungCap> getAllNhaCungCap() {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from NhaCungCap");

            while (rs.next()) {
                String ma = rs.getString("maNhaCungCap");
                String ten = rs.getString("tenNhaCungCap");
                String soDienThoai = rs.getString("soDienThoai");
                String maSoThue = rs.getString("maSoThue");
                String email = rs.getString("email");
                String maDiaChi = rs.getString("maDiaChi");

                NhaCungCap ncc = new NhaCungCap(ma, ten, new DiaChi(maDiaChi), soDienThoai, maSoThue, email);

                result.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<NhaCungCap> getNhaCungCapTheoMa(String maNCC) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select * from NhaCungCap where maNhaCungCap = ?");
            st.setString(1, maNCC);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String ma = rs.getString("maNhaCungCap");
                String ten = rs.getString("tenNhaCungCap");
                String soDienThoai = rs.getString("soDienThoai");
                String maSoThue = rs.getString("maSoThue");
                String email = rs.getString("email");
                String maDiaChi = rs.getString("maDiaChi");

                NhaCungCap ncc = new NhaCungCap(ma, ten, new DiaChi(maDiaChi), soDienThoai, maSoThue, email);

                result.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean xoaNhaCungCap(String maNCC) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from NhaCungCap where maNhaCungCap = ?");
            st.setString(1, maNCC);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean themNhaCungCap(NhaCungCap ncc) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE NhaCungCap SET tenNhaCungCap = ?, soDienThoai = ?, maSoThue = ?, email = ?, maDiaChi = ? WHERE maNhaCungCap = ?;");
            int i = 1;
            st.setString(i++, ncc.getTenNCC());
            st.setString(i++, ncc.getSoDT());
            st.setString(i++, ncc.getMaSoThue());
            st.setString(i++, ncc.getEMail());
            st.setString(i++, ncc.getDiaChi().getMaDiaChi());
            st.setString(i++, ncc.getMaNCC());
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean capNhatNhaCungCap(String maNCC, NhaCungCap ncc) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("INSERT INTO NhaCungCap(maNhaCungCap, tenNhaCungCap, soDienThoai, maSoThue, email, maDiaChi) VALUES (?, ?, ?, ?, ?, ?);");
            int i = 1;
            st.setString(i++, ncc.getMaNCC());
            st.setString(i++, ncc.getTenNCC());
            st.setString(i++, ncc.getSoDT());
            st.setString(i++, ncc.getMaSoThue());
            st.setString(i++, ncc.getEMail());
            st.setString(i++, ncc.getDiaChi().getMaDiaChi());

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

}
