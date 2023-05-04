/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import bus.DiaChi_bus;
import bus.NhanVien_bus;
import bus.TaiKhoan_bus;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import entity.connguoi.NhanVien;
import entity.connguoi.TaiKhoan;
import entity.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class Panel_QuanLyNhanVien extends javax.swing.JPanel {

    private Frame_InputDiaChi frame_diaChi;
    private NhanVien_bus NV_bus = new NhanVien_bus();
    private DiaChi_bus DC_bus = new DiaChi_bus();
    private ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
    DefaultTableModel model_dsNhanVien;

    /**
     * Creates new form Panel_QuanLyNhanVien
     */
    public Panel_QuanLyNhanVien() {
        String col[] = {"Mã nhân viên", "Họ tên", "Email", "Số điện thoại", "Địa chỉ", "Chức danh", "Năm sinh", "Giới tính", "Trạng thái"};
        model_dsNhanVien = new DefaultTableModel(col, 0);
        listNV = NV_bus.getAllNhanVien();

        initComponents();
        alterTable();

        tbl_dsNhanVien.getSelectionModel().addListSelectionListener((e) -> {
            int row = tbl_dsNhanVien.getSelectedRow();
            if (row != -1) {
                txt_maNV.setText(model_dsNhanVien.getValueAt(row, 0).toString());
                txt_hoTenNV.setText(model_dsNhanVien.getValueAt(row, 1).toString());
                txt_mailNV.setText(model_dsNhanVien.getValueAt(row, 2).toString());
                txt_soDT.setText(model_dsNhanVien.getValueAt(row, 3).toString());

                cmb_chucVu.setSelectedItem(model_dsNhanVien.getValueAt(row, 5).toString());

                txt_diaChi.setText(model_dsNhanVien.getValueAt(row, 4).toString());
                if (txt_diaChi.getText().trim().length() > 0) {
                    String ma = (model_dsNhanVien.getValueAt(row, 0).toString());
                    String maDC = NV_bus.getMaDiaChi(ma);
                    DiaChi dc = DC_bus.getDiaChiTheoMa(maDC);
                    frame_diaChi = new Frame_InputDiaChi(this, dc);
                } else {
                    frame_diaChi = new Frame_InputDiaChi(this);
                }
                LocalDate ngaySinh = (LocalDate) model_dsNhanVien.getValueAt(row, 6);
                //convert LocalDate to Date
                txt_date.setDate(Date.from(ngaySinh.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

                cmb_gioiTinh.setSelectedItem(model_dsNhanVien.getValueAt(row, 7).toString());

            }

        });

        renderAll();

    }

    public void alterTable() {
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);

        tbl_dsNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_dsNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_dsNhanVien.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbl_dsNhanVien.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbl_dsNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_dsNhanVien.getColumnModel().getColumn(4).setPreferredWidth(200);
        tbl_dsNhanVien.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbl_dsNhanVien.getColumnModel().getColumn(6).setPreferredWidth(100);
        tbl_dsNhanVien.getColumnModel().getColumn(7).setPreferredWidth(100);

        tbl_dsNhanVien.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_dsNhanVien.getColumnModel().getColumn(6).setCellRenderer(rightAlign);

        tbl_dsNhanVien.setDefaultEditor(Object.class, null);
    }

    public void renderAll() {
        ArrayList<NhanVien> list = NV_bus.getAllNhanVien();
        renderListToTable(list);
    }

    public void renderListToTable(ArrayList<NhanVien> list) {
        model_dsNhanVien.setRowCount(0);
        for (NhanVien nv : list) {
            String gioiTinh;
            if (nv.isGioiTinh()) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            String tt;
            if(nv.isTrangThai()){
                tt="Đang làm";
            }else{
                tt="Đã nghỉ";
            }
            model_dsNhanVien.addRow(new Object[]{nv.getMaNV(), nv.getHoTen(), nv.getEmail(), nv.getSoDT(), nv.getDiaChi().toString(), nv.getChucDanh(), nv.getNamSinh(), gioiTinh, tt});
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_timKiem = new javax.swing.JPanel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(32767, 0));
        txt_timKiem = new javax.swing.JTextField();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(32767, 0));
        btn_timKiem = new javax.swing.JButton();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 32767));
        btn_reset = new javax.swing.JButton();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        pnl_Control = new javax.swing.JPanel();
        pnl_controlgroup = new javax.swing.JPanel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btn_themNV = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btn_xoaNV = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btn_suaNV = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btn_capNhatMKNV = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        pnl_ttNhanVien = new javax.swing.JPanel();
        pnl_avata = new javax.swing.JPanel();
        lbl_img = new javax.swing.JLabel();
        pnl_formNV = new javax.swing.JPanel();
        pnl_maNV = new javax.swing.JPanel();
        lbl_maNV = new javax.swing.JLabel();
        txt_maNV = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_hoTenNV = new javax.swing.JPanel();
        lbl_hoTen = new javax.swing.JLabel();
        txt_hoTenNV = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_email = new javax.swing.JPanel();
        lbl_mailNV = new javax.swing.JLabel();
        txt_mailNV = new javax.swing.JTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_soDT = new javax.swing.JPanel();
        lbl_soDT = new javax.swing.JLabel();
        txt_soDT = new javax.swing.JTextField();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_diaChi = new javax.swing.JPanel();
        lbl_diaChi = new javax.swing.JLabel();
        txt_diaChi = new javax.swing.JTextField();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_chucVu = new javax.swing.JPanel();
        lbl_chucVu = new javax.swing.JLabel();
        cmb_chucVu = new javax.swing.JComboBox<>();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_namSinh = new javax.swing.JPanel();
        lbl_namSinh = new javax.swing.JLabel();
        lbl_gioiTinh = new javax.swing.JLabel();
        cmb_gioiTinh = new javax.swing.JComboBox<>();
        txt_date = new com.toedter.calendar.JDateChooser();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_submit = new javax.swing.JPanel();
        btn_xoaTrang = new javax.swing.JButton();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnl_dsNhanVien = new javax.swing.JPanel();
        scr_1 = new javax.swing.JScrollPane();
        tbl_dsNhanVien = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnl_timKiem.setBackground(new java.awt.Color(255, 255, 255));
        pnl_timKiem.setPreferredSize(new java.awt.Dimension(1000, 40));
        pnl_timKiem.setLayout(new javax.swing.BoxLayout(pnl_timKiem, javax.swing.BoxLayout.X_AXIS));
        pnl_timKiem.add(filler12);

        txt_timKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_timKiem.setForeground(new java.awt.Color(153, 153, 153));
        txt_timKiem.setText("Mã nhân viên");
        txt_timKiem.setToolTipText("Nhập mã nhân viên");
        txt_timKiem.setPreferredSize(new java.awt.Dimension(300, 23));
        txt_timKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusLost(evt);
            }
        });
        txt_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemActionPerformed(evt);
            }
        });
        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyPressed(evt);
            }
        });
        pnl_timKiem.add(txt_timKiem);
        pnl_timKiem.add(filler11);

        btn_timKiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_timKiem.setText("Tìm kiếm");
        btn_timKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_timKiem.setMaximumSize(new java.awt.Dimension(151, 340));
        btn_timKiem.setPreferredSize(new java.awt.Dimension(160, 40));
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });
        pnl_timKiem.add(btn_timKiem);
        pnl_timKiem.add(filler18);

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartReset.png"))); // NOI18N
        btn_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reset.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_reset.setPreferredSize(new java.awt.Dimension(40, 40));
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        pnl_timKiem.add(btn_reset);
        pnl_timKiem.add(filler17);

        add(pnl_timKiem, java.awt.BorderLayout.NORTH);

        pnl_Control.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Control.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl_Control.setPreferredSize(new java.awt.Dimension(745, 70));
        pnl_Control.setLayout(new javax.swing.BoxLayout(pnl_Control, javax.swing.BoxLayout.LINE_AXIS));

        pnl_controlgroup.setBackground(new java.awt.Color(255, 255, 255));
        pnl_controlgroup.setPreferredSize(new java.awt.Dimension(996, 100));
        pnl_controlgroup.setLayout(new javax.swing.BoxLayout(pnl_controlgroup, javax.swing.BoxLayout.LINE_AXIS));
        pnl_controlgroup.add(filler8);

        btn_themNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btn_themNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlynhanvien/add.png"))); // NOI18N
        btn_themNV.setText("Thêm Nhân Viên");
        btn_themNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_themNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_themNV.setIconTextGap(10);
        btn_themNV.setMaximumSize(new java.awt.Dimension(125, 50));
        btn_themNV.setPreferredSize(new java.awt.Dimension(200, 50));
        btn_themNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themNVActionPerformed(evt);
            }
        });
        pnl_controlgroup.add(btn_themNV);
        pnl_controlgroup.add(filler4);

        btn_xoaNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btn_xoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlynhanvien/remove.png"))); // NOI18N
        btn_xoaNV.setText("Xoá trạng thái");
        btn_xoaNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_xoaNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_xoaNV.setIconTextGap(10);
        btn_xoaNV.setMaximumSize(new java.awt.Dimension(115, 50));
        btn_xoaNV.setPreferredSize(new java.awt.Dimension(200, 50));
        btn_xoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaNVActionPerformed(evt);
            }
        });
        pnl_controlgroup.add(btn_xoaNV);
        pnl_controlgroup.add(filler5);

        btn_suaNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btn_suaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlynhanvien/edit.png"))); // NOI18N
        btn_suaNV.setText("Sửa Nhân Viên");
        btn_suaNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_suaNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_suaNV.setIconTextGap(10);
        btn_suaNV.setMaximumSize(new java.awt.Dimension(117, 50));
        btn_suaNV.setPreferredSize(new java.awt.Dimension(200, 50));
        btn_suaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaNVActionPerformed(evt);
            }
        });
        pnl_controlgroup.add(btn_suaNV);
        pnl_controlgroup.add(filler6);

        btn_capNhatMKNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btn_capNhatMKNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlynhanvien/password.png"))); // NOI18N
        btn_capNhatMKNV.setText("Cập Nhật Mật Khẩu");
        btn_capNhatMKNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_capNhatMKNV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_capNhatMKNV.setIconTextGap(10);
        btn_capNhatMKNV.setMaximumSize(new java.awt.Dimension(145, 50));
        btn_capNhatMKNV.setPreferredSize(new java.awt.Dimension(200, 50));
        btn_capNhatMKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capNhatMKNVActionPerformed(evt);
            }
        });
        pnl_controlgroup.add(btn_capNhatMKNV);
        pnl_controlgroup.add(filler7);

        pnl_Control.add(pnl_controlgroup);

        add(pnl_Control, java.awt.BorderLayout.SOUTH);

        pnl_ttNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ttNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_ttNhanVien.setPreferredSize(new java.awt.Dimension(400, 300));
        pnl_ttNhanVien.setLayout(new java.awt.BorderLayout());

        pnl_avata.setBackground(new java.awt.Color(255, 255, 255));
        pnl_avata.setMaximumSize(new java.awt.Dimension(32767, 100));
        pnl_avata.setPreferredSize(new java.awt.Dimension(0, 300));
        pnl_avata.setLayout(new java.awt.BorderLayout());

        lbl_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlynhanvien/user-4.png"))); // NOI18N
        lbl_img.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_img.setIconTextGap(10);
        lbl_img.setPreferredSize(new java.awt.Dimension(200, 200));
        pnl_avata.add(lbl_img, java.awt.BorderLayout.CENTER);

        pnl_ttNhanVien.add(pnl_avata, java.awt.BorderLayout.CENTER);

        pnl_formNV.setBackground(new java.awt.Color(255, 255, 255));
        pnl_formNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl_formNV.setMaximumSize(new java.awt.Dimension(390, 100));
        pnl_formNV.setPreferredSize(new java.awt.Dimension(390, 330));
        pnl_formNV.setLayout(new javax.swing.BoxLayout(pnl_formNV, javax.swing.BoxLayout.Y_AXIS));

        pnl_maNV.setBackground(new java.awt.Color(255, 255, 255));
        pnl_maNV.setPreferredSize(new java.awt.Dimension(180, 70));

        lbl_maNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_maNV.setForeground(new java.awt.Color(102, 102, 102));
        lbl_maNV.setText("Mã nhân viên:");
        lbl_maNV.setPreferredSize(new java.awt.Dimension(85, 17));

        txt_maNV.setEditable(false);
        txt_maNV.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_maNV.setMaximumSize(new java.awt.Dimension(280, 30));
        txt_maNV.setMinimumSize(new java.awt.Dimension(65, 30));
        txt_maNV.setPreferredSize(new java.awt.Dimension(280, 30));

        javax.swing.GroupLayout pnl_maNVLayout = new javax.swing.GroupLayout(pnl_maNV);
        pnl_maNV.setLayout(pnl_maNVLayout);
        pnl_maNVLayout.setHorizontalGroup(
            pnl_maNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_maNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_maNVLayout.setVerticalGroup(
            pnl_maNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_maNVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_maNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        pnl_formNV.add(pnl_maNV);
        pnl_formNV.add(filler1);

        pnl_hoTenNV.setBackground(new java.awt.Color(255, 255, 255));
        pnl_hoTenNV.setPreferredSize(new java.awt.Dimension(180, 70));

        lbl_hoTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_hoTen.setForeground(new java.awt.Color(102, 102, 102));
        lbl_hoTen.setText("Họ tên:");
        lbl_hoTen.setPreferredSize(new java.awt.Dimension(85, 17));

        txt_hoTenNV.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_hoTenNV.setPreferredSize(new java.awt.Dimension(65, 30));

        javax.swing.GroupLayout pnl_hoTenNVLayout = new javax.swing.GroupLayout(pnl_hoTenNV);
        pnl_hoTenNV.setLayout(pnl_hoTenNVLayout);
        pnl_hoTenNVLayout.setHorizontalGroup(
            pnl_hoTenNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_hoTenNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(txt_hoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_hoTenNVLayout.setVerticalGroup(
            pnl_hoTenNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_hoTenNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_hoTenNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_hoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_formNV.add(pnl_hoTenNV);
        pnl_formNV.add(filler2);

        pnl_email.setBackground(new java.awt.Color(255, 255, 255));
        pnl_email.setPreferredSize(new java.awt.Dimension(180, 70));

        lbl_mailNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_mailNV.setForeground(new java.awt.Color(102, 102, 102));
        lbl_mailNV.setText("Email:");
        lbl_mailNV.setPreferredSize(new java.awt.Dimension(85, 17));

        txt_mailNV.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_mailNV.setPreferredSize(new java.awt.Dimension(65, 30));

        javax.swing.GroupLayout pnl_emailLayout = new javax.swing.GroupLayout(pnl_email);
        pnl_email.setLayout(pnl_emailLayout);
        pnl_emailLayout.setHorizontalGroup(
            pnl_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_emailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_mailNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(txt_mailNV, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_emailLayout.setVerticalGroup(
            pnl_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_emailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_mailNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mailNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        pnl_formNV.add(pnl_email);
        pnl_formNV.add(filler3);

        pnl_soDT.setBackground(new java.awt.Color(255, 255, 255));
        pnl_soDT.setPreferredSize(new java.awt.Dimension(180, 70));

        lbl_soDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_soDT.setForeground(new java.awt.Color(102, 102, 102));
        lbl_soDT.setText("Số điện thoại: ");
        lbl_soDT.setPreferredSize(new java.awt.Dimension(85, 17));

        txt_soDT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_soDT.setPreferredSize(new java.awt.Dimension(280, 30));

        javax.swing.GroupLayout pnl_soDTLayout = new javax.swing.GroupLayout(pnl_soDT);
        pnl_soDT.setLayout(pnl_soDTLayout);
        pnl_soDTLayout.setHorizontalGroup(
            pnl_soDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_soDTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_soDT, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_soDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_soDTLayout.setVerticalGroup(
            pnl_soDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_soDTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_soDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_soDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_soDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_formNV.add(pnl_soDT);
        pnl_formNV.add(filler9);

        pnl_diaChi.setBackground(new java.awt.Color(255, 255, 255));
        pnl_diaChi.setMinimumSize(new java.awt.Dimension(65, 40));
        pnl_diaChi.setPreferredSize(new java.awt.Dimension(180, 70));

        lbl_diaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_diaChi.setForeground(new java.awt.Color(102, 102, 102));
        lbl_diaChi.setText("Địa chỉ:");
        lbl_diaChi.setPreferredSize(new java.awt.Dimension(85, 17));

        txt_diaChi.setEditable(false);
        txt_diaChi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_diaChi.setMinimumSize(new java.awt.Dimension(65, 30));
        txt_diaChi.setPreferredSize(new java.awt.Dimension(280, 30));
        txt_diaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_diaChiMouseClicked(evt);
            }
        });
        txt_diaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diaChiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_diaChiLayout = new javax.swing.GroupLayout(pnl_diaChi);
        pnl_diaChi.setLayout(pnl_diaChiLayout);
        pnl_diaChiLayout.setHorizontalGroup(
            pnl_diaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_diaChiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_diaChiLayout.setVerticalGroup(
            pnl_diaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_diaChiLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(pnl_diaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        pnl_formNV.add(pnl_diaChi);
        pnl_formNV.add(filler16);

        pnl_chucVu.setBackground(new java.awt.Color(255, 255, 255));
        pnl_chucVu.setPreferredSize(new java.awt.Dimension(180, 70));

        lbl_chucVu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_chucVu.setForeground(new java.awt.Color(102, 102, 102));
        lbl_chucVu.setText("Chức vụ:");
        lbl_chucVu.setPreferredSize(new java.awt.Dimension(85, 17));

        cmb_chucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên kinh doanh" }));
        cmb_chucVu.setPreferredSize(new java.awt.Dimension(161, 30));

        javax.swing.GroupLayout pnl_chucVuLayout = new javax.swing.GroupLayout(pnl_chucVu);
        pnl_chucVu.setLayout(pnl_chucVuLayout);
        pnl_chucVuLayout.setHorizontalGroup(
            pnl_chucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_chucVuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_chucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_chucVu, 0, 281, Short.MAX_VALUE))
        );
        pnl_chucVuLayout.setVerticalGroup(
            pnl_chucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_chucVuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_chucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_chucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_chucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnl_formNV.add(pnl_chucVu);
        pnl_formNV.add(filler10);

        pnl_namSinh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_namSinh.setPreferredSize(new java.awt.Dimension(180, 70));

        lbl_namSinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_namSinh.setForeground(new java.awt.Color(102, 102, 102));
        lbl_namSinh.setText("Năm sinh: ");
        lbl_namSinh.setPreferredSize(new java.awt.Dimension(85, 17));

        lbl_gioiTinh.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lbl_gioiTinh.setForeground(new java.awt.Color(102, 102, 102));
        lbl_gioiTinh.setText("Giới tính:");

        cmb_gioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cmb_gioiTinh.setPreferredSize(new java.awt.Dimension(72, 30));

        txt_date.setPreferredSize(new java.awt.Dimension(64, 30));
        txt_date.setLocale(new Locale("vi","VN"));

        javax.swing.GroupLayout pnl_namSinhLayout = new javax.swing.GroupLayout(pnl_namSinh);
        pnl_namSinh.setLayout(pnl_namSinhLayout);
        pnl_namSinhLayout.setHorizontalGroup(
            pnl_namSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_namSinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_namSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_date, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_gioiTinh)
                .addGap(8, 8, 8)
                .addComponent(cmb_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_namSinhLayout.setVerticalGroup(
            pnl_namSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_namSinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_namSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_namSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_namSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_gioiTinh)
                        .addComponent(cmb_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnl_formNV.add(pnl_namSinh);
        pnl_formNV.add(filler15);

        pnl_submit.setBackground(new java.awt.Color(255, 255, 255));
        pnl_submit.setMinimumSize(new java.awt.Dimension(78, 30));
        pnl_submit.setPreferredSize(new java.awt.Dimension(180, 70));
        pnl_submit.setLayout(new javax.swing.BoxLayout(pnl_submit, javax.swing.BoxLayout.LINE_AXIS));

        btn_xoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_xoaTrang.setForeground(new java.awt.Color(102, 102, 102));
        btn_xoaTrang.setText("Xóa trắng");
        btn_xoaTrang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_xoaTrang.setMaximumSize(new java.awt.Dimension(32767, 32767));
        btn_xoaTrang.setMinimumSize(new java.awt.Dimension(150, 40));
        btn_xoaTrang.setPreferredSize(new java.awt.Dimension(150, 70));
        btn_xoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaTrangActionPerformed(evt);
            }
        });
        pnl_submit.add(btn_xoaTrang);

        pnl_formNV.add(pnl_submit);
        pnl_formNV.add(filler14);

        pnl_ttNhanVien.add(pnl_formNV, java.awt.BorderLayout.SOUTH);

        add(pnl_ttNhanVien, java.awt.BorderLayout.EAST);

        pnl_dsNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pnl_dsNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_dsNhanVien.setPreferredSize(new java.awt.Dimension(450, 370));
        pnl_dsNhanVien.setLayout(new java.awt.BorderLayout());

        scr_1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scr_1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbl_dsNhanVien.setModel(model_dsNhanVien);
        tbl_dsNhanVien.setMaximumSize(new java.awt.Dimension(100, 0));
        tbl_dsNhanVien.setRowHeight(30);
        tbl_dsNhanVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_dsNhanVien.setShowGrid(true);
        tbl_dsNhanVien.setShowVerticalLines(false);
        scr_1.setViewportView(tbl_dsNhanVien);

        pnl_dsNhanVien.add(scr_1, java.awt.BorderLayout.CENTER);

        add(pnl_dsNhanVien, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        timKiem();
    }//GEN-LAST:event_btn_timKiemActionPerformed

    private void txt_timKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            timKiem();
        }
    }//GEN-LAST:event_txt_timKiemKeyPressed

    private void txt_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemActionPerformed
    public void showMessageFocus(String msg, JTextField txt) {
        JOptionPane.showMessageDialog(this, msg);
        txt.selectAll();
        txt.requestFocus();
    }

    private void btn_themNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themNVActionPerformed
        // TODO add your handling code here:
        String maNV = NV_bus.sinhMa();
        String hoTen = txt_hoTenNV.getText();
        String email = txt_mailNV.getText();
        String soDT = txt_soDT.getText();
        String chucVu = (String) cmb_chucVu.getSelectedItem();
        String gt = (String) cmb_gioiTinh.getSelectedItem();

        if (!Pattern.matches("^\\p{L}+\\s+\\p{L}+.*$", hoTen)) {
            showMessageFocus("Họ tên không hợp lệ", txt_hoTenNV);
            return;
        }
        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
            showMessageFocus("Email không hợp lệ", txt_mailNV);
            return;
        }
        if (!Pattern.matches("0\\d{9}", soDT)) {
            showMessageFocus("Số điện thoại không hợp lệ", txt_soDT);
            return;
        }
        if (txt_diaChi.getText().trim().length() <= 0) {
            showMessageFocus("Địa chỉ không được rỗng", txt_diaChi);
            return;
        }

        DiaChi dc = frame_diaChi.getDiaChi();
        boolean gioiTinh;
        if (gt.equals("Nữ")) {
            gioiTinh = false;
        } else {
            gioiTinh = true;
        }
        Date ns = txt_date.getDate();
        LocalDate ngaySinh = ns.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if((LocalDate.now().getYear()-ngaySinh.getYear())<18){
            JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi");
            return;
        }
        try {
            NhanVien nv = new NhanVien(maNV, chucVu, hoTen, soDT, email, ngaySinh, dc, gioiTinh);
            int count = 0;
            for (NhanVien n : listNV) {
                if (nv.getSoDT().equals(n.getSoDT())) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại !");
                    count++;
                }
            }
            if (count == 0 && NV_bus.themNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
                txt_maNV.setText("");
                txt_hoTenNV.setText("");
                txt_mailNV.setText("");
                txt_soDT.setText("");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        renderAll();


    }//GEN-LAST:event_btn_themNVActionPerformed

    private void btn_xoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaNVActionPerformed
        // TODO add your handling code here:
        int row = tbl_dsNhanVien.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(this, "Bạn cần chọn nhân viên để xoá !");
             return;
        }
             
        String maNV = model_dsNhanVien.getValueAt(row, 0).toString();
        String trangThai = model_dsNhanVien.getValueAt(row, 8).toString();
        boolean tt;
        if (trangThai.equals("Đang làm")) {
            
            tt = false;
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật trạng thái làm việc nhân viên?", "Chú ý!!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (NV_bus.capNhatTrangThaiNhanVien(maNV, tt)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật trạng thái thành công !");
                    renderAll();
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Nhân viên đã nghĩ làm !");
        }


    }//GEN-LAST:event_btn_xoaNVActionPerformed

    private void txt_diaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_diaChiActionPerformed

    private void txt_diaChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_diaChiMouseClicked

        if (txt_diaChi.getText().trim().length() > 0) {
            int row = tbl_dsNhanVien.getSelectedRow();
            if(row==-1)
                    return;
            String maNV = (model_dsNhanVien.getValueAt(row, 0).toString());
            String maDC = NV_bus.getMaDiaChi(maNV);
            DiaChi dc = DC_bus.getDiaChiTheoMa(maDC);
            frame_diaChi = new Frame_InputDiaChi(this, dc);

        } else {
            frame_diaChi = new Frame_InputDiaChi(this);
        }

        frame_diaChi.setVisible(true);
        frame_diaChi.getDiaChi();
    }//GEN-LAST:event_txt_diaChiMouseClicked

    private void btn_suaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaNVActionPerformed
        int row = tbl_dsNhanVien.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(this, "Bạn cần chọn nhân viên để sửa !");
             return;
        }
        String maNV = txt_maNV.getText();
        String hoTen = txt_hoTenNV.getText();
        String email = txt_mailNV.getText();
        String soDT = txt_soDT.getText();
        String chucVu =  cmb_chucVu.getSelectedItem().toString();
        String gt =  cmb_gioiTinh.getSelectedItem().toString();

        if (!Pattern.matches("^\\p{L}+\\s+\\p{L}+.*$", hoTen)) {
            showMessageFocus("Họ tên không hợp lệ", txt_hoTenNV);
            return;
        }
        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
            showMessageFocus("Email không hợp lệ", txt_mailNV);
            return;
        }
        if (!Pattern.matches("0\\d{9}", soDT)) {
            showMessageFocus("Số điện thoại không hợp lệ", txt_soDT);
            return;
        }
        if (txt_diaChi.getText().trim().length() <= 0) {
            showMessageFocus("Địa chỉ không được rỗng", txt_diaChi);
            return;
        }

        DiaChi dc = frame_diaChi.getDiaChi();
        dc.setMaDiaChi(NV_bus.getMaDiaChi(maNV));
        boolean gioiTinh;
        if (gt.equals("Nữ")) {
            gioiTinh = false;
        } else {
            gioiTinh = true;
        }
        Date ns = txt_date.getDate();
        LocalDate ngaySinh = ns.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if((LocalDate.now().getYear()-ngaySinh.getYear())<18){
            JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi");
            return;
        }
        try {
            NhanVien nv = new NhanVien(maNV, chucVu, hoTen, soDT, email, ngaySinh, dc, gioiTinh);
            if (NV_bus.capNhatNhanVien(maNV, nv)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại !");
            }
            renderAll();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_suaNVActionPerformed

    private void btn_xoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaTrangActionPerformed
        // TODO add your handling code here:
        txt_maNV.setText("");
        txt_hoTenNV.setText("");
        txt_mailNV.setText("");
        txt_soDT.setText("");
        txt_diaChi.setText("");
        cmb_gioiTinh.setSelectedIndex(0);


    }//GEN-LAST:event_btn_xoaTrangActionPerformed

    private void btn_capNhatMKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capNhatMKNVActionPerformed
        // TODO add your handling code here:
        String soTK = JOptionPane.showInputDialog(this, "Nhập số tài khoản cần đổi mật khẩu !");
        if(soTK==null)
        {
            return;
        }
        if(soTK.trim().length()<=0){
            JOptionPane.showMessageDialog(this, "Số tài khoản không được rỗng!");
            return;
        }
        String mk = JOptionPane.showInputDialog(this, "Nhập mật khấu mới !");
        if(mk==null){
            return;
        }
        if(mk.trim().length()<=0)
        {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được rỗng !");
        }
        TaiKhoan_bus TK_bus = new TaiKhoan_bus();
        TaiKhoan taiKhoan;
        try {
            taiKhoan = new TaiKhoan(soTK, mk);
            if (TK_bus.capNhatTaiKhoan(soTK, taiKhoan)) {
                JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công !");
            } else {
                JOptionPane.showConfirmDialog(this, "Số tài khoản không tồn tại !");
            }

        } catch (Exception ex) {
            Logger.getLogger(Panel_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btn_capNhatMKNVActionPerformed

    private void txt_timKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusGained
        if (txt_timKiem.getText().trim().equals("Mã nhân viên")) {
            txt_timKiem.setText("");
            txt_timKiem.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txt_timKiemFocusGained

    private void txt_timKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusLost
        if (txt_timKiem.getText().trim().equals("")) {
            txt_timKiem.setText("Mã nhân viên");
            txt_timKiem.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_timKiemFocusLost

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        renderAll();
        txt_timKiem.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetActionPerformed
    public void timKiem() {
        String ma = txt_timKiem.getText().trim();
        if (ma.length() > 0) {
            ArrayList<NhanVien> list = new ArrayList<NhanVien>();
            list = NV_bus.getNhanVienTheoMa(ma);
            renderListToTable(list);
        } else {
            renderAll();
        }

    }

    public void updateDiaChi(DiaChi dc) {
        txt_diaChi.setText(dc.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_capNhatMKNV;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_suaNV;
    private javax.swing.JButton btn_themNV;
    private javax.swing.JButton btn_timKiem;
    private javax.swing.JButton btn_xoaNV;
    private javax.swing.JButton btn_xoaTrang;
    private javax.swing.JComboBox<String> cmb_chucVu;
    private javax.swing.JComboBox<String> cmb_gioiTinh;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel lbl_chucVu;
    private javax.swing.JLabel lbl_diaChi;
    private javax.swing.JLabel lbl_gioiTinh;
    private javax.swing.JLabel lbl_hoTen;
    private javax.swing.JLabel lbl_img;
    private javax.swing.JLabel lbl_maNV;
    private javax.swing.JLabel lbl_mailNV;
    private javax.swing.JLabel lbl_namSinh;
    private javax.swing.JLabel lbl_soDT;
    private javax.swing.JPanel pnl_Control;
    private javax.swing.JPanel pnl_avata;
    private javax.swing.JPanel pnl_chucVu;
    private javax.swing.JPanel pnl_controlgroup;
    private javax.swing.JPanel pnl_diaChi;
    private javax.swing.JPanel pnl_dsNhanVien;
    private javax.swing.JPanel pnl_email;
    private javax.swing.JPanel pnl_formNV;
    private javax.swing.JPanel pnl_hoTenNV;
    private javax.swing.JPanel pnl_maNV;
    private javax.swing.JPanel pnl_namSinh;
    private javax.swing.JPanel pnl_soDT;
    private javax.swing.JPanel pnl_submit;
    private javax.swing.JPanel pnl_timKiem;
    private javax.swing.JPanel pnl_ttNhanVien;
    private javax.swing.JScrollPane scr_1;
    private javax.swing.JTable tbl_dsNhanVien;
    private com.toedter.calendar.JDateChooser txt_date;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_hoTenNV;
    private javax.swing.JTextField txt_maNV;
    private javax.swing.JTextField txt_mailNV;
    private javax.swing.JTextField txt_soDT;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
