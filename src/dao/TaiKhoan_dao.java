/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.TaiKhoanInterface;
import java.util.ArrayList;
import entity.connguoi.TaiKhoan;
import java.sql.*;
import entity.share.ConnectDB;

/**
 *
 * @author macbookk
 */
public class TaiKhoan_dao implements TaiKhoanInterface {

    @Override
    public ArrayList<TaiKhoan> getTaiKhoanTheoMaNV(String soTK) {
        ArrayList<TaiKhoan> result = new ArrayList<TaiKhoan>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from TaiKhoan where maNhanVien = ?");
            st.setString(1, soTK);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String sotk = rs.getString("soTaiKhoan");
                String mk = rs.getString("matKhau");
                TaiKhoan tk = new TaiKhoan(sotk, mk);
                result.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean themTaiKhoan(TaiKhoan taiKhoan) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into TaiKhoan "
                    + " values(?,?)");
            st.setString(1, taiKhoan.getSoTK());
            st.setString(2, taiKhoan.getPassWord());

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean xoaTaiKhoan(String soTK) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from TaiKhoan where maNhanVien = ?");
            st.setString(1, soTK);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean capNhatTaiKhoan(String soTK, TaiKhoan taiKhoan) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("update TaiKhoan "
                    + "set matKhau= ?"
                    + " where maNhanVien = ?");
            st.setString(1, taiKhoan.getPassWord());
            st.setString(2, soTK);

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean kiemTraTaiKhoan(String tk, String mk) {
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select * from TaiKhoan where maNhanVien = ? and matKhau = ?");
            st.setString(1, tk);
            st.setString(2, mk);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
