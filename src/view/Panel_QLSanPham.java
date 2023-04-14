/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.sanpham.SanPham;
import model.sanpham.ThuongHieu;

/**
 *
 * @author nxnam
 */
public class Panel_QLSanPham extends javax.swing.JPanel {
    
//    private DefaultTableModel tbl_ModelProduct;
//    private String[] str_TenCot = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Thương hiệu", "Cấu hình", "Giá cả", "Số lượng"};

    /**
     * Creates new form Panel_QLSanPham
     */
    public Panel_QLSanPham() {
        initComponents();
        initTableModel();
       
    }
    
    private void initTableModel() {
//        tbl_ModelProduct = new DefaultT
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_TimKiem = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_TimLoai = new javax.swing.JTextField();
        lbl_TimKiem = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pnl_CauHinh = new javax.swing.JPanel();
        lbl_CauHinh = new javax.swing.JLabel();
        txt_CauHinh = new javax.swing.JTextField();
        pnl_Loai = new javax.swing.JPanel();
        lbl_Loai = new javax.swing.JLabel();
        txt_Loai = new javax.swing.JTextField();
        pnl_ThuongHieu = new javax.swing.JPanel();
        lbl_ThuongHieu = new javax.swing.JLabel();
        cmb_ThươngHieu = new javax.swing.JComboBox<>();
        pnl_DanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnl_Tensp = new javax.swing.JPanel();
        lbl_Tensp = new javax.swing.JLabel();
        txt_Tensp = new javax.swing.JTextField();
        pnl_Loaisp = new javax.swing.JPanel();
        cmb_Loaisp = new javax.swing.JComboBox<>();
        lbl_Loaisp = new javax.swing.JLabel();
        pnl_Giasp = new javax.swing.JPanel();
        txt_Gia = new javax.swing.JTextField();
        lbl_Giasp = new javax.swing.JLabel();
        pnl_Soluongsp = new javax.swing.JPanel();
        lbl_Soluongsp = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        pnl_CauHinhsp = new javax.swing.JPanel();
        lbl_Cauhinhsp = new javax.swing.JLabel();
        txt_Cauhinhsp = new javax.swing.JTextField();
        pnl_ThuongHieusp = new javax.swing.JPanel();
        cmb_Thuonghieusp = new javax.swing.JComboBox<>();
        lbl_Thuonghieusp = new javax.swing.JLabel();
        pnl_DieuKhien = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 529));
        setLayout(new java.awt.BorderLayout());

        pnl_TimKiem.setName(""); // NOI18N

        lbl_TimKiem.setText("Tên sản phẩm");

        jButton1.setText("Tìm kiếm");

        lbl_CauHinh.setText("Cấu hình");

        javax.swing.GroupLayout pnl_CauHinhLayout = new javax.swing.GroupLayout(pnl_CauHinh);
        pnl_CauHinh.setLayout(pnl_CauHinhLayout);
        pnl_CauHinhLayout.setHorizontalGroup(
            pnl_CauHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_CauHinhLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbl_CauHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txt_CauHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_CauHinhLayout.setVerticalGroup(
            pnl_CauHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_CauHinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_CauHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_CauHinh)
                    .addComponent(txt_CauHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbl_Loai.setText("Loại sản phẩm");

        txt_Loai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_LoaiLayout = new javax.swing.GroupLayout(pnl_Loai);
        pnl_Loai.setLayout(pnl_LoaiLayout);
        pnl_LoaiLayout.setHorizontalGroup(
            pnl_LoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_LoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Loai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Loai, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_LoaiLayout.setVerticalGroup(
            pnl_LoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_LoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_LoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Loai)
                    .addComponent(txt_Loai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbl_ThuongHieu.setText("Thương hiệu");

        cmb_ThươngHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnl_ThuongHieuLayout = new javax.swing.GroupLayout(pnl_ThuongHieu);
        pnl_ThuongHieu.setLayout(pnl_ThuongHieuLayout);
        pnl_ThuongHieuLayout.setHorizontalGroup(
            pnl_ThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThuongHieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ThuongHieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_ThươngHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_ThuongHieuLayout.setVerticalGroup(
            pnl_ThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThuongHieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ThuongHieu)
                    .addComponent(cmb_ThươngHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_CauHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(pnl_Loai, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(pnl_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnl_Loai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnl_CauHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_TimKiem)
                .addGap(32, 32, 32)
                .addComponent(txt_TimLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_TimKiem)
                    .addComponent(txt_TimLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnl_TimKiemLayout = new javax.swing.GroupLayout(pnl_TimKiem);
        pnl_TimKiem.setLayout(pnl_TimKiemLayout);
        pnl_TimKiemLayout.setHorizontalGroup(
            pnl_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TimKiemLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_TimKiemLayout.setVerticalGroup(
            pnl_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_TimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnl_TimKiem, java.awt.BorderLayout.PAGE_START);

        pnl_DanhSach.setBackground(new java.awt.Color(255, 255, 255));
        pnl_DanhSach.setLayout(new javax.swing.BoxLayout(pnl_DanhSach, javax.swing.BoxLayout.LINE_AXIS));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Thương hiệu", "Cấu hình", "Giá cả", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        pnl_DanhSach.add(jScrollPane1);

        add(pnl_DanhSach, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 0));

        jLabel4.setText("Ảnh");

        lbl_Tensp.setText("Tên sản phẩm:");

        txt_Tensp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenspActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_TenspLayout = new javax.swing.GroupLayout(pnl_Tensp);
        pnl_Tensp.setLayout(pnl_TenspLayout);
        pnl_TenspLayout.setHorizontalGroup(
            pnl_TenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TenspLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Tensp)
                .addGap(18, 18, 18)
                .addComponent(txt_Tensp, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_TenspLayout.setVerticalGroup(
            pnl_TenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TenspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_TenspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Tensp)
                    .addComponent(txt_Tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cmb_Loaisp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_Loaisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_LoaispActionPerformed(evt);
            }
        });

        lbl_Loaisp.setText("Loại sản phẩm:");

        javax.swing.GroupLayout pnl_LoaispLayout = new javax.swing.GroupLayout(pnl_Loaisp);
        pnl_Loaisp.setLayout(pnl_LoaispLayout);
        pnl_LoaispLayout.setHorizontalGroup(
            pnl_LoaispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_LoaispLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Loaisp)
                .addGap(18, 18, 18)
                .addComponent(cmb_Loaisp, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_LoaispLayout.setVerticalGroup(
            pnl_LoaispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_LoaispLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_LoaispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Loaisp)
                    .addComponent(cmb_Loaisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbl_Giasp.setText("Giá cả:");

        javax.swing.GroupLayout pnl_GiaspLayout = new javax.swing.GroupLayout(pnl_Giasp);
        pnl_Giasp.setLayout(pnl_GiaspLayout);
        pnl_GiaspLayout.setHorizontalGroup(
            pnl_GiaspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_GiaspLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Giasp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_GiaspLayout.setVerticalGroup(
            pnl_GiaspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_GiaspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_GiaspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Giasp)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbl_Soluongsp.setText("Số lượng:");

        txtSoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoluongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_SoluongspLayout = new javax.swing.GroupLayout(pnl_Soluongsp);
        pnl_Soluongsp.setLayout(pnl_SoluongspLayout);
        pnl_SoluongspLayout.setHorizontalGroup(
            pnl_SoluongspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SoluongspLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Soluongsp)
                .addGap(49, 49, 49)
                .addComponent(txtSoluong)
                .addContainerGap())
        );
        pnl_SoluongspLayout.setVerticalGroup(
            pnl_SoluongspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SoluongspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_SoluongspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Soluongsp)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbl_Cauhinhsp.setText("Cấu hình:");

        javax.swing.GroupLayout pnl_CauHinhspLayout = new javax.swing.GroupLayout(pnl_CauHinhsp);
        pnl_CauHinhsp.setLayout(pnl_CauHinhspLayout);
        pnl_CauHinhspLayout.setHorizontalGroup(
            pnl_CauHinhspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_CauHinhspLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Cauhinhsp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_Cauhinhsp, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_CauHinhspLayout.setVerticalGroup(
            pnl_CauHinhspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_CauHinhspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_CauHinhspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Cauhinhsp)
                    .addComponent(txt_Cauhinhsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cmb_Thuonghieusp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_Thuonghieusp.setText("Thương hiệu:");

        javax.swing.GroupLayout pnl_ThuongHieuspLayout = new javax.swing.GroupLayout(pnl_ThuongHieusp);
        pnl_ThuongHieusp.setLayout(pnl_ThuongHieuspLayout);
        pnl_ThuongHieuspLayout.setHorizontalGroup(
            pnl_ThuongHieuspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThuongHieuspLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Thuonghieusp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmb_Thuonghieusp, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_ThuongHieuspLayout.setVerticalGroup(
            pnl_ThuongHieuspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThuongHieuspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ThuongHieuspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Thuonghieusp)
                    .addComponent(cmb_Thuonghieusp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btn_Them.setText("Thêm");

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_DieuKhienLayout = new javax.swing.GroupLayout(pnl_DieuKhien);
        pnl_DieuKhien.setLayout(pnl_DieuKhienLayout);
        pnl_DieuKhienLayout.setHorizontalGroup(
            pnl_DieuKhienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DieuKhienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Them)
                .addGap(18, 18, 18)
                .addComponent(btn_Xoa)
                .addGap(18, 18, 18)
                .addComponent(btn_Sua)
                .addContainerGap())
        );
        pnl_DieuKhienLayout.setVerticalGroup(
            pnl_DieuKhienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DieuKhienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_DieuKhienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btn_Xoa)
                    .addComponent(btn_Sua))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnl_DieuKhien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pnl_CauHinhsp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnl_Soluongsp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnl_Loaisp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnl_Giasp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnl_Tensp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnl_ThuongHieusp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_Tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(pnl_Loaisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnl_Giasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnl_Soluongsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnl_CauHinhsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_ThuongHieusp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnl_DieuKhien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoluongActionPerformed

    private void cmb_LoaispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_LoaispActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_LoaispActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void txt_LoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LoaiActionPerformed

    private void txt_TenspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenspActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cmb_Loaisp;
    private javax.swing.JComboBox<String> cmb_Thuonghieusp;
    private javax.swing.JComboBox<String> cmb_ThươngHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_CauHinh;
    private javax.swing.JLabel lbl_Cauhinhsp;
    private javax.swing.JLabel lbl_Giasp;
    private javax.swing.JLabel lbl_Loai;
    private javax.swing.JLabel lbl_Loaisp;
    private javax.swing.JLabel lbl_Soluongsp;
    private javax.swing.JLabel lbl_Tensp;
    private javax.swing.JLabel lbl_ThuongHieu;
    private javax.swing.JLabel lbl_Thuonghieusp;
    private javax.swing.JLabel lbl_TimKiem;
    private javax.swing.JPanel pnl_CauHinh;
    private javax.swing.JPanel pnl_CauHinhsp;
    private javax.swing.JPanel pnl_DanhSach;
    private javax.swing.JPanel pnl_DieuKhien;
    private javax.swing.JPanel pnl_Giasp;
    private javax.swing.JPanel pnl_Loai;
    private javax.swing.JPanel pnl_Loaisp;
    private javax.swing.JPanel pnl_Soluongsp;
    private javax.swing.JPanel pnl_Tensp;
    private javax.swing.JPanel pnl_ThuongHieu;
    private javax.swing.JPanel pnl_ThuongHieusp;
    private javax.swing.JPanel pnl_TimKiem;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txt_CauHinh;
    private javax.swing.JTextField txt_Cauhinhsp;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_Loai;
    private javax.swing.JTextField txt_Tensp;
    private javax.swing.JTextField txt_TimLoai;
    // End of variables declaration//GEN-END:variables

   
}