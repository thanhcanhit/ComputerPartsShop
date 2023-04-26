/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.DiaChi_bus;
import controller.HoaDon_bus;
import controller.KhachHang_bus;
import controller.NhanVien_bus;
import controller.SanPham_bus;
import model.connguoi.KhachHang;
import model.connguoi.NhanVien;
import model.hoadon.ChiTietHoaDon;
import model.hoadon.HoaDon;
import model.sanpham.SanPham;
import model.share.ConnectDB;
import model.share.DiaChi;
import model.share.Utility;

/**
 *
 * @author thanh
 */
public class Frame_HoaDon extends javax.swing.JFrame {

    /**
     * Creates new form Frame_HoaDon
     */
    private HoaDon hd;

    public Frame_HoaDon(HoaDon hd) {
        initComponents();
        this.hd = hd;
        render();
    }

    public void render() {
        String line = "\n----------------------------------------------------------------------------------\n";
        String result = "ComputerPartShop";
        result += line;
        result += "Mã hóa đơn: " + hd.getMaHoaDon();
        result += "\nNgày lập: " + hd.getNgayLap();
        NhanVien nv = new NhanVien_bus().getNhanVienTheoMa(hd.getNhanVien().getMaNV()).get(0);
        result += "\nNhân viên: " + nv.getHoTen();
        KhachHang kh = new KhachHang_bus().getKhachHangTheoMa(hd.getKhachHang().getMaKH()).get(0);
        result += "\nKhách hàng: " + kh.getHoTen();
        result += "\nSố điện thoại: " + kh.getSoDT();
        DiaChi dc = new DiaChi_bus().getDiaChiTheoMa(kh.getDiaChi().getMaDiaChi());
        result += "\nĐịa chỉ: " + dc.toString();
        result += line;
        result += String.format("%20s %20s %50s", "Đơn giá", "Số lượng", "Thành tiền");
        result += line;

        int i = 1;
        for (ChiTietHoaDon ct : hd.getDsChiTiethoaDon()) {
            SanPham sp = new SanPham_bus().getSanPhamTheoMa(ct.getSanPham().getMaSP()).get(0);
            result += i + ". " + sp.getTenSP();
            result += String.format("\n%20s %20s %50s\n", Utility.getVND(sp.getGiaBan()), ct.getSoLuong(), Utility.getVND(sp.getGiaBan() * ct.getSoLuong()));
            i++;
        }
        result += line;
        result += String.format("%100s", "Tổng tiền: " + Utility.getVND(hd.getTongTien()));
        result += String.format("\n%90s", "Phương thức thanh toán: " + hd.getPhuongThucThanhToan());
        txa_hoaDon.setText(result);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_hoaDon = new javax.swing.JTextArea();
        pnl_footer = new javax.swing.JPanel();
        btn_xacNhan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hóa đơn");
        setBackground(new java.awt.Color(255, 255, 255));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setPreferredSize(new java.awt.Dimension(500, 800));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        pnl_main.setBackground(new java.awt.Color(255, 255, 255));
        pnl_main.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnl_main.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        txa_hoaDon.setEditable(false);
        txa_hoaDon.setBackground(new java.awt.Color(255, 255, 255));
        txa_hoaDon.setColumns(10);
        txa_hoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txa_hoaDon.setRows(5);
        txa_hoaDon.setTabSize(4);
        txa_hoaDon.setAutoscrolls(false);
        txa_hoaDon.setBorder(null);
        txa_hoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txa_hoaDon.setFocusable(false);
        jScrollPane1.setViewportView(txa_hoaDon);

        pnl_main.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_main, java.awt.BorderLayout.CENTER);

        pnl_footer.setPreferredSize(new java.awt.Dimension(500, 50));
        pnl_footer.setLayout(new java.awt.BorderLayout());

        btn_xacNhan.setBackground(new java.awt.Color(65, 165, 238));
        btn_xacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btn_xacNhan.setText("Xác nhận");
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });
        pnl_footer.add(btn_xacNhan, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_footer, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_xacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xacNhan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl_footer;
    private javax.swing.JPanel pnl_main;
    private javax.swing.JTextArea txa_hoaDon;
    // End of variables declaration//GEN-END:variables
}
