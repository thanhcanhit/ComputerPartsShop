/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import bus.KhoHang_bus;
import bus.SanPham_bus;
import bus.ThuongHieu_bus;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import entity.sanpham.SanPham;
import entity.sanpham.ThuongHieu;

/**
 *
 * @author nxnam
 */
public class Panel_QuanLySanPham extends javax.swing.JPanel {

    private DefaultTableModel tbl_ModelProduct;
    private String[] str_TenCot = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Thương hiệu", "Số lượng tồn", "Giá nhập", "Giá bán", "Giảm giá", "VAT", "Bảo hành", "Loại hàng", "Cấu hình"};
    private SanPham_bus sanPham_bus = new SanPham_bus();
    private ThuongHieu_bus thuongHieu_bus = new ThuongHieu_bus();
    private KhoHang_bus khoHang_bus = new KhoHang_bus();
    NumberFormat vnd = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));

    private int page = 1;

    /**
     * Creates new form Panel_QuanLySanPham
     */
    public Panel_QuanLySanPham() {
        tbl_ModelProduct = new DefaultTableModel(str_TenCot, 0);

        initComponents();
        renderPage();
        alterTable();

        tbl_products.getSelectionModel().addListSelectionListener((e) -> {
            int row = tbl_products.getSelectedRow();
            if (row != -1) {
                String maSanPham = tbl_ModelProduct.getValueAt(row, 0).toString();
                SanPham sanPham = sanPham_bus.getSanPhamTheoMa(maSanPham).get(0);
                txt_maSanPham.setText(maSanPham);
                txt_tenSP.setText(sanPham.getTenSP());
                ThuongHieu TH = thuongHieu_bus.getThuongHieuTheoMa(sanPham.getThuongHieu().getMaTH()).get(0);
                txt_thuongHieu.setText(TH.toString().replace("[", "").replace("]", ""));
                txt_soLuongTon.setText(Integer.toString(khoHang_bus.getSoLuongTon("KHO01", maSanPham)));
                txt_giaNhap.setText(Double.toString(sanPham.getGiaNhap()));
                txt_giaBan.setText(Double.toString(sanPham.getGiaBan()));
                txt_giamGia.setText(Double.toString(sanPham.getGiamGia()));
                txt_vat.setText(Double.toString(sanPham.getVAT()));
                txt_baoHanh.setText(Integer.toString(sanPham.getSoThangBaoHanh()));
                cmb_loaiSP.setSelectedItem(sanPham.getTenLoai());

                txa_cauHinh.setText(sanPham.getCauHinh().replace("; ", ";\n"));

            }
        });

    }

    private void alterTable() {

        tbl_products.setDefaultEditor(Object.class, null);

        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);

        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);

        tbl_products.getColumnModel().getColumn(0).setCellRenderer(leftAlign);
        tbl_products.getColumnModel().getColumn(1).setCellRenderer(leftAlign);
        tbl_products.getColumnModel().getColumn(2).setCellRenderer(leftAlign);
        tbl_products.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_products.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        tbl_products.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        tbl_products.getColumnModel().getColumn(6).setCellRenderer(rightAlign);
        tbl_products.getColumnModel().getColumn(7).setCellRenderer(rightAlign);
        tbl_products.getColumnModel().getColumn(8).setCellRenderer(leftAlign);
        tbl_products.getColumnModel().getColumn(9).setCellRenderer(leftAlign);
        tbl_products.getColumnModel().getColumn(10).setCellRenderer(leftAlign);

        tbl_products.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_products.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbl_products.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(6).setPreferredWidth(65);
        tbl_products.getColumnModel().getColumn(7).setPreferredWidth(65);
        tbl_products.getColumnModel().getColumn(8).setPreferredWidth(70);
        tbl_products.getColumnModel().getColumn(9).setPreferredWidth(65);
        tbl_products.getColumnModel().getColumn(10).setPreferredWidth(500);
    }

    public void renderAll() {
        ArrayList<SanPham> list = sanPham_bus.getAllSanPham();
        renderProductTable(list);
    }

    public void renderPage() {
        if (lbl_soTrang != null) {
            lbl_soTrang.setText(page + "/" + sanPham_bus.getSoTrangMax());
        }
        renderProductTable(sanPham_bus.getSanPhamTrang(page));
    }

    private void renderProductTable(ArrayList<SanPham> list) {
        tbl_ModelProduct.setRowCount(0);

        for (SanPham sp : list) {
            ArrayList<ThuongHieu> th = thuongHieu_bus.getThuongHieuTheoMa(sp.getThuongHieu().getMaTH());
            int soLuong = khoHang_bus.getSoLuongTon("KHO01", sp.getMaSP());
            Object[] row = new Object[]{sp.getMaSP(), sp.getTenSP(), th.get(0).toString(), soLuong, vnd.format(sp.getGiaNhap()), vnd.format(sp.getGiaBan()), Math.round(sp.getGiamGia()) + "%", sp.getVAT(), Math.round(sp.getSoThangBaoHanh()) + " tháng", sp.getTenLoai(), sp.getCauHinh()};
            tbl_ModelProduct.addRow(row);
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

        pnl_thongTin = new javax.swing.JPanel();
        pnl_bodyTT = new javax.swing.JPanel();
        pnl_maSanPham = new javax.swing.JPanel();
        lbl_maSanPham = new javax.swing.JLabel();
        txt_maSanPham = new javax.swing.JTextField();
        pnl_tenSP = new javax.swing.JPanel();
        lbl_tenSP = new javax.swing.JLabel();
        txt_tenSP = new javax.swing.JTextField();
        pnl_thuongHieu = new javax.swing.JPanel();
        lbl_thuongHieu = new javax.swing.JLabel();
        txt_thuongHieu = new javax.swing.JTextField();
        pnl_soLuongTon = new javax.swing.JPanel();
        lbl_soLuongTon = new javax.swing.JLabel();
        txt_soLuongTon = new javax.swing.JTextField();
        pnl_giaNhap = new javax.swing.JPanel();
        lbl_giaNhap = new javax.swing.JLabel();
        txt_giaNhap = new javax.swing.JTextField();
        pnl_giaBan = new javax.swing.JPanel();
        lbl_giaBan = new javax.swing.JLabel();
        txt_giaBan = new javax.swing.JTextField();
        pnl_giamGia = new javax.swing.JPanel();
        lbl_giamGia = new javax.swing.JLabel();
        txt_giamGia = new javax.swing.JTextField();
        pnl_vat = new javax.swing.JPanel();
        lbl_vat = new javax.swing.JLabel();
        txt_vat = new javax.swing.JTextField();
        pnl_loaiSP = new javax.swing.JPanel();
        lbl_loaiSP = new javax.swing.JLabel();
        cmb_loaiSP = new javax.swing.JComboBox<>();
        pnl_baoHanh = new javax.swing.JPanel();
        lbl_baoHanh = new javax.swing.JLabel();
        txt_baoHanh = new javax.swing.JTextField();
        pnl_cauHinh = new javax.swing.JPanel();
        lbl_cauHinh = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(10, 32767));
        scr_2 = new javax.swing.JScrollPane();
        txa_cauHinh = new javax.swing.JTextArea();
        pnl_nutQuanLy = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoaTrang = new javax.swing.JButton();
        pnl_timKiem = new javax.swing.JPanel();
        pnl_headerSearch = new javax.swing.JPanel();
        lbl_headerTen = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        pnl_dieuKhienBtn = new javax.swing.JPanel();
        btn_timKiem = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        pnl_headerOption = new javax.swing.JPanel();
        pnl_headerCauHinh = new javax.swing.JPanel();
        lbl_headerCauHinh = new javax.swing.JLabel();
        scr_cauHinh = new javax.swing.JScrollPane();
        txa_headerCauHinh = new javax.swing.JTextArea();
        pnl_headerLoaiTH = new javax.swing.JPanel();
        pnl_headerLoai = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(40, 32767));
        lbl_headerLoai = new javax.swing.JLabel();
        cmb_headerLoai = new javax.swing.JComboBox<>();
        pnl_headerTH = new javax.swing.JPanel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(40, 32767));
        lbl_headerTH = new javax.swing.JLabel();
        cmb_headerThuongHieu = new javax.swing.JComboBox<String>();
        pnl_danhSach = new javax.swing.JPanel();
        scr_3 = new javax.swing.JScrollPane();
        tbl_products = new javax.swing.JTable();
        pnl_left = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btn_prev = new javax.swing.JButton();
        lbl_soTrang = new javax.swing.JLabel();
        btn_next = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnl_thongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnl_thongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_thongTin.setPreferredSize(new java.awt.Dimension(320, 600));
        pnl_thongTin.setLayout(new java.awt.BorderLayout());

        pnl_bodyTT.setBackground(new java.awt.Color(255, 255, 255));
        pnl_bodyTT.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnl_bodyTT.setPreferredSize(new java.awt.Dimension(140, 20));
        pnl_bodyTT.setLayout(new javax.swing.BoxLayout(pnl_bodyTT, javax.swing.BoxLayout.Y_AXIS));

        pnl_maSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnl_maSanPham.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_maSanPham.setLayout(new javax.swing.BoxLayout(pnl_maSanPham, javax.swing.BoxLayout.LINE_AXIS));

        lbl_maSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_maSanPham.setText("Mã sản phẩm");
        lbl_maSanPham.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_maSanPham.add(lbl_maSanPham);

        txt_maSanPham.setEditable(false);
        txt_maSanPham.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_maSanPham.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_maSanPham.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_maSanPham.add(txt_maSanPham);

        pnl_bodyTT.add(pnl_maSanPham);

        pnl_tenSP.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tenSP.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_tenSP.setLayout(new javax.swing.BoxLayout(pnl_tenSP, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tenSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tenSP.setText("Tên sản phẩm:");
        lbl_tenSP.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_tenSP.add(lbl_tenSP);

        txt_tenSP.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_tenSP.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_tenSP.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_tenSP.add(txt_tenSP);

        pnl_bodyTT.add(pnl_tenSP);

        pnl_thuongHieu.setBackground(new java.awt.Color(255, 255, 255));
        pnl_thuongHieu.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_thuongHieu.setLayout(new javax.swing.BoxLayout(pnl_thuongHieu, javax.swing.BoxLayout.LINE_AXIS));

        lbl_thuongHieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_thuongHieu.setText("Thương hiệu:");
        lbl_thuongHieu.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_thuongHieu.add(lbl_thuongHieu);

        txt_thuongHieu.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_thuongHieu.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_thuongHieu.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_thuongHieu.add(txt_thuongHieu);

        pnl_bodyTT.add(pnl_thuongHieu);

        pnl_soLuongTon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_soLuongTon.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_soLuongTon.setLayout(new javax.swing.BoxLayout(pnl_soLuongTon, javax.swing.BoxLayout.LINE_AXIS));

        lbl_soLuongTon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_soLuongTon.setText("Số lượng tồn:");
        lbl_soLuongTon.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_soLuongTon.add(lbl_soLuongTon);

        txt_soLuongTon.setEditable(false);
        txt_soLuongTon.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_soLuongTon.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_soLuongTon.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_soLuongTon.add(txt_soLuongTon);

        pnl_bodyTT.add(pnl_soLuongTon);

        pnl_giaNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_giaNhap.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_giaNhap.setLayout(new javax.swing.BoxLayout(pnl_giaNhap, javax.swing.BoxLayout.LINE_AXIS));

        lbl_giaNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_giaNhap.setText("Giá nhập:");
        lbl_giaNhap.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_giaNhap.add(lbl_giaNhap);

        txt_giaNhap.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_giaNhap.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_giaNhap.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_giaNhap.add(txt_giaNhap);

        pnl_bodyTT.add(pnl_giaNhap);

        pnl_giaBan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_giaBan.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_giaBan.setLayout(new javax.swing.BoxLayout(pnl_giaBan, javax.swing.BoxLayout.LINE_AXIS));

        lbl_giaBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_giaBan.setText("Giá bán:");
        lbl_giaBan.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_giaBan.add(lbl_giaBan);

        txt_giaBan.setEditable(false);
        txt_giaBan.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_giaBan.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_giaBan.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_giaBan.add(txt_giaBan);

        pnl_bodyTT.add(pnl_giaBan);

        pnl_giamGia.setBackground(new java.awt.Color(255, 255, 255));
        pnl_giamGia.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_giamGia.setLayout(new javax.swing.BoxLayout(pnl_giamGia, javax.swing.BoxLayout.LINE_AXIS));

        lbl_giamGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_giamGia.setText("Giảm giá:");
        lbl_giamGia.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_giamGia.add(lbl_giamGia);

        txt_giamGia.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_giamGia.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_giamGia.setPreferredSize(new java.awt.Dimension(160, 24));
        txt_giamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giamGiaActionPerformed(evt);
            }
        });
        pnl_giamGia.add(txt_giamGia);

        pnl_bodyTT.add(pnl_giamGia);

        pnl_vat.setBackground(new java.awt.Color(255, 255, 255));
        pnl_vat.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_vat.setLayout(new javax.swing.BoxLayout(pnl_vat, javax.swing.BoxLayout.LINE_AXIS));

        lbl_vat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_vat.setText("VAT:");
        lbl_vat.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_vat.add(lbl_vat);

        txt_vat.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_vat.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_vat.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_vat.add(txt_vat);

        pnl_bodyTT.add(pnl_vat);

        pnl_loaiSP.setBackground(new java.awt.Color(255, 255, 255));
        pnl_loaiSP.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_loaiSP.setLayout(new javax.swing.BoxLayout(pnl_loaiSP, javax.swing.BoxLayout.LINE_AXIS));

        lbl_loaiSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_loaiSP.setText("Loại hàng:");
        lbl_loaiSP.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_loaiSP.add(lbl_loaiSP);

        cmb_loaiSP.setMaximumRowCount(6);
        cmb_loaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"", "CPU", "MainBoard", "VGA", "RAM", "Ổ cứng", "Nguồn", "Case", "Tản nhiệt", "Chuột", "Bàn phím" }));
        cmb_loaiSP.setMaximumSize(new java.awt.Dimension(1000, 500));
        cmb_loaiSP.setMinimumSize(new java.awt.Dimension(140, 16));
        cmb_loaiSP.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_loaiSP.add(cmb_loaiSP);

        pnl_bodyTT.add(pnl_loaiSP);

        pnl_baoHanh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_baoHanh.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_baoHanh.setLayout(new javax.swing.BoxLayout(pnl_baoHanh, javax.swing.BoxLayout.LINE_AXIS));

        lbl_baoHanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_baoHanh.setText("Bảo hành (tháng):");
        lbl_baoHanh.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_baoHanh.add(lbl_baoHanh);

        txt_baoHanh.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_baoHanh.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_baoHanh.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_baoHanh.add(txt_baoHanh);

        pnl_bodyTT.add(pnl_baoHanh);

        pnl_cauHinh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_cauHinh.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_cauHinh.setLayout(new javax.swing.BoxLayout(pnl_cauHinh, javax.swing.BoxLayout.LINE_AXIS));

        lbl_cauHinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_cauHinh.setText("Cấu hình:");
        lbl_cauHinh.setPreferredSize(new java.awt.Dimension(138, 138));
        pnl_cauHinh.add(lbl_cauHinh);
        pnl_cauHinh.add(filler2);

        txa_cauHinh.setColumns(20);
        txa_cauHinh.setRows(5);
        txa_cauHinh.setCaretPosition(txa_cauHinh.getDocument().getLength());
        txa_cauHinh.setPreferredSize(new java.awt.Dimension(200, 24));
        scr_2.setViewportView(txa_cauHinh);

        pnl_cauHinh.add(scr_2);

        pnl_bodyTT.add(pnl_cauHinh);

        pnl_thongTin.add(pnl_bodyTT, java.awt.BorderLayout.CENTER);

        pnl_nutQuanLy.setBackground(new java.awt.Color(255, 255, 255));
        pnl_nutQuanLy.setLayout(new java.awt.GridLayout(1, 0));

        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_them.setForeground(new java.awt.Color(102, 102, 102));
        btn_them.setText("Thêm");
        btn_them.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_them.setPreferredSize(new java.awt.Dimension(80, 32));
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        pnl_nutQuanLy.add(btn_them);

        btn_sua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_sua.setForeground(new java.awt.Color(102, 102, 102));
        btn_sua.setText("Sửa");
        btn_sua.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_sua.setPreferredSize(new java.awt.Dimension(80, 40));
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        pnl_nutQuanLy.add(btn_sua);

        btn_xoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_xoaTrang.setForeground(new java.awt.Color(102, 102, 102));
        btn_xoaTrang.setLabel("Xóa trắng");
        btn_xoaTrang.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_xoaTrang.setPreferredSize(new java.awt.Dimension(80, 40));
        btn_xoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaTrangActionPerformed(evt);
            }
        });
        pnl_nutQuanLy.add(btn_xoaTrang);

        pnl_thongTin.add(pnl_nutQuanLy, java.awt.BorderLayout.SOUTH);

        add(pnl_thongTin, java.awt.BorderLayout.EAST);

        pnl_timKiem.setBackground(new java.awt.Color(255, 255, 255));
        pnl_timKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_timKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnl_timKiem.setPreferredSize(new java.awt.Dimension(0, 150));
        pnl_timKiem.setLayout(new javax.swing.BoxLayout(pnl_timKiem, javax.swing.BoxLayout.Y_AXIS));

        pnl_headerSearch.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerSearch.setPreferredSize(new java.awt.Dimension(0, 50));
        pnl_headerSearch.setLayout(new javax.swing.BoxLayout(pnl_headerSearch, javax.swing.BoxLayout.X_AXIS));

        lbl_headerTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_headerTen.setText("Tên sản phẩm:");
        lbl_headerTen.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_headerSearch.add(lbl_headerTen);

        txt_search.setToolTipText("Nhập tên sản phẩm");
        txt_search.setMinimumSize(new java.awt.Dimension(30, 22));
        txt_search.setPreferredSize(new java.awt.Dimension(650, 0));
        pnl_headerSearch.add(txt_search);

        pnl_dieuKhienBtn.setMaximumSize(new java.awt.Dimension(4001111, 2147483647));
        pnl_dieuKhienBtn.setMinimumSize(new java.awt.Dimension(1000, 50));
        pnl_dieuKhienBtn.setPreferredSize(new java.awt.Dimension(200, 50));
        pnl_dieuKhienBtn.setLayout(new javax.swing.BoxLayout(pnl_dieuKhienBtn, javax.swing.BoxLayout.X_AXIS));

        btn_timKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_timKiem.setText("Tìm Kiếm");
        btn_timKiem.setToolTipText("Tìm kiếm");
        btn_timKiem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_timKiem.setIconTextGap(5);
        btn_timKiem.setMaximumSize(new java.awt.Dimension(150, 50));
        btn_timKiem.setMinimumSize(new java.awt.Dimension(150, 50));
        btn_timKiem.setPreferredSize(new java.awt.Dimension(140, 50));
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });
        pnl_dieuKhienBtn.add(btn_timKiem);

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartReset.png"))); // NOI18N
        btn_reset.setToolTipText("Tải lại");
        btn_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reset.setMaximumSize(new java.awt.Dimension(50, 50));
        btn_reset.setMinimumSize(new java.awt.Dimension(50, 50));
        btn_reset.setPreferredSize(new java.awt.Dimension(60, 50));
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        pnl_dieuKhienBtn.add(btn_reset);

        pnl_headerSearch.add(pnl_dieuKhienBtn);

        pnl_timKiem.add(pnl_headerSearch);

        pnl_headerOption.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerOption.setLayout(new java.awt.GridLayout(1, 2));

        pnl_headerCauHinh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerCauHinh.setLayout(new javax.swing.BoxLayout(pnl_headerCauHinh, javax.swing.BoxLayout.LINE_AXIS));

        lbl_headerCauHinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_headerCauHinh.setText("Cấu hình:");
        lbl_headerCauHinh.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_headerCauHinh.add(lbl_headerCauHinh);

        txa_headerCauHinh.setColumns(10);
        txa_headerCauHinh.setRows(5);
        txa_headerCauHinh.setToolTipText("Nhập cấu hình cần tìm");
        scr_cauHinh.setViewportView(txa_headerCauHinh);

        pnl_headerCauHinh.add(scr_cauHinh);

        pnl_headerOption.add(pnl_headerCauHinh);

        pnl_headerLoaiTH.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerLoaiTH.setLayout(new javax.swing.BoxLayout(pnl_headerLoaiTH, javax.swing.BoxLayout.Y_AXIS));

        pnl_headerLoai.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerLoai.setLayout(new javax.swing.BoxLayout(pnl_headerLoai, javax.swing.BoxLayout.LINE_AXIS));
        pnl_headerLoai.add(filler6);

        lbl_headerLoai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_headerLoai.setText("Loại sản phẩm:");
        lbl_headerLoai.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_headerLoai.add(lbl_headerLoai);

        cmb_headerLoai.setMaximumRowCount(10);
        cmb_headerLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "CPU", "MainBoard", "VGA", "RAM", "Ổ cứng", "Nguồn", "Case", "Tản nhiệt", "Chuột", "Bàn phím" }));
        pnl_headerLoai.add(cmb_headerLoai);

        pnl_headerLoaiTH.add(pnl_headerLoai);

        pnl_headerTH.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerTH.setLayout(new javax.swing.BoxLayout(pnl_headerTH, javax.swing.BoxLayout.LINE_AXIS));
        pnl_headerTH.add(filler7);

        lbl_headerTH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_headerTH.setText("Thương hiệu:");
        lbl_headerTH.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_headerTH.add(lbl_headerTH);

        cmb_headerThuongHieu.addItem("Tất cả");
        for (ThuongHieu th : thuongHieu_bus.getAllThuongHieu()) {
            cmb_headerThuongHieu.addItem(th.toString());
        }
        pnl_headerTH.add(cmb_headerThuongHieu);

        pnl_headerLoaiTH.add(pnl_headerTH);

        pnl_headerOption.add(pnl_headerLoaiTH);

        pnl_timKiem.add(pnl_headerOption);

        add(pnl_timKiem, java.awt.BorderLayout.NORTH);

        pnl_danhSach.setBackground(new java.awt.Color(255, 255, 255));
        pnl_danhSach.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_danhSach.setLayout(new java.awt.BorderLayout());

        scr_3.setBackground(new java.awt.Color(255, 255, 255));
        scr_3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scr_3.setToolTipText("");
        scr_3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbl_products.setModel(tbl_ModelProduct);
        tbl_products.setRowHeight(30);
        tbl_products.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_products.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_products.setShowGrid(true);
        tbl_products.setShowVerticalLines(false);
        scr_3.setViewportView(tbl_products);

        pnl_danhSach.add(scr_3, java.awt.BorderLayout.CENTER);

        pnl_left.setBackground(new java.awt.Color(255, 255, 255));
        pnl_left.setLayout(new java.awt.GridLayout(1, 0));
        pnl_left.add(filler1);
        pnl_left.add(filler9);

        btn_prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartPrev.png"))); // NOI18N
        btn_prev.setToolTipText("Trang trước");
        btn_prev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_prev.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_prev.setIconTextGap(10);
        btn_prev.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });
        pnl_left.add(btn_prev);

        lbl_soTrang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_soTrang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_soTrang.setToolTipText("");
        lbl_soTrang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_soTrang.setPreferredSize(new java.awt.Dimension(45, 45));
        pnl_left.add(lbl_soTrang);

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartNext.png"))); // NOI18N
        btn_next.setToolTipText("Trang kế");
        btn_next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_next.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_next.setIconTextGap(10);
        btn_next.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        pnl_left.add(btn_next);
        pnl_left.add(filler8);
        pnl_left.add(filler3);

        pnl_danhSach.add(pnl_left, java.awt.BorderLayout.PAGE_END);

        add(pnl_danhSach, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_btn_timKiemActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if (tieuChuanThem()) {
            String maSP = sanPham_bus.sinhMa();
            String tenSP = txt_tenSP.getText().trim();
            String thuongHieu = txt_thuongHieu.getText().trim();
            double giaNhap = Double.parseDouble(txt_giaNhap.getText().trim());
            double giamGia = Double.parseDouble(txt_giamGia.getText().trim());
            int loai = sanPham_bus.loaiParseInt(cmb_loaiSP.getSelectedItem().toString());
            double VAT = Double.parseDouble(txt_vat.getText().trim());
            int soThangBaoHanh = Integer.parseInt(txt_baoHanh.getText().trim());
            String cauHinh = txa_cauHinh.getText().trim();
            try {
                if (thuongHieu_bus.timMaThuongHieuTheoToString(thuongHieu) == null) {
                    ThuongHieu th = thuongHieu_bus.taoThuongHieu(thuongHieu);
                }
                ThuongHieu TH = thuongHieu_bus.getThuongHieuTheoMa(thuongHieu_bus.timMaThuongHieuTheoToString(thuongHieu)).get(0);

                SanPham sp = new SanPham(maSP, tenSP, giaNhap, giamGia, loai, VAT, TH, soThangBaoHanh, cauHinh);
                if (sanPham_bus.themSanPham(sp)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    xoaTrang();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm không thành công", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(Panel_QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        renderAll();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_xoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaTrangActionPerformed
        // TODO add your handling code here:
        xoaTrang();
    }//GEN-LAST:event_btn_xoaTrangActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        if (tieuChuanSua()) {
            String maSP = txt_maSanPham.getText();
            if (maSP.length() == 0) {
                showMessageFocus("Chưa có mã sản phẩm", txt_maSanPham);
            } else {

                String tenSP = txt_tenSP.getText().trim();
                String thuongHieu = txt_thuongHieu.getText().trim();
                double giaNhap = Double.parseDouble(txt_giaNhap.getText().trim());
                double giamGia = Double.parseDouble(txt_giamGia.getText().trim());
                int loai = sanPham_bus.loaiParseInt(cmb_loaiSP.getSelectedItem().toString());
                double VAT = Double.parseDouble(txt_vat.getText().trim());
                int soThangBaoHanh = Integer.parseInt(txt_baoHanh.getText().trim());
                String cauHinh = txa_cauHinh.getText().replace(";\n", "; ").trim();
                try {
                    if (thuongHieu_bus.timMaThuongHieuTheoToString(thuongHieu) == null) {
                        ThuongHieu th = thuongHieu_bus.taoThuongHieu(thuongHieu);
                    }
                    ThuongHieu TH = thuongHieu_bus.getThuongHieuTheoMa(thuongHieu_bus.timMaThuongHieuTheoToString(thuongHieu)).get(0);

                    SanPham sp = new SanPham(maSP, tenSP, giaNhap, giamGia, loai, VAT, TH, soThangBaoHanh, cauHinh);
                    if (sanPham_bus.capNhatSanPham(maSP, sp)) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                        xoaTrang();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật không thành công", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Panel_QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        renderAll();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        txt_search.setText("");
        txa_headerCauHinh.setText("");
        cmb_headerLoai.setSelectedIndex(0);
        cmb_headerThuongHieu.setSelectedIndex(0);
        renderPage();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        if (page > 1) {
            page--;
            renderPage();
        }
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        if (page < sanPham_bus.getSoTrangMax()) {
            page++;
            renderPage();
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void txt_giamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giamGiaActionPerformed

    public void search() {
        String tenSP = txt_search.getText().trim();
        String loaiSP = cmb_headerLoai.getSelectedItem().toString();
        String thuongHieuSP = cmb_headerThuongHieu.getSelectedItem().toString();
        String cauHinh = txa_headerCauHinh.getText().trim().replace("\n", "");

        if (tenSP.length() == 0 && loaiSP.equals("Tất cả") && thuongHieuSP.equals("Tất cả") && cauHinh.length() == 0) {
            page = 1;
            renderPage();
        } else if (!Pattern.matches("(.*:\\s.+;)*", cauHinh)) {
            JOptionPane.showMessageDialog(this, "Thuộc tính: giá trị;");
            txa_headerCauHinh.selectAll();
            txa_headerCauHinh.requestFocus();
        } else {
            ArrayList<SanPham> ds = sanPham_bus.searchTheoDieuKien(tenSP, loaiSP, thuongHieuSP, cauHinh);
            lbl_soTrang.setText("1/1");
            renderProductTable(ds);
        }
    }

    private void showMessageFocus(String msg, JTextField txt) {
        JOptionPane.showMessageDialog(this, msg);
        txt.selectAll();
        txt.requestFocus();
    }

    private void xoaTrang() {
        txt_maSanPham.setText("");
        txt_tenSP.setText("");
        txt_thuongHieu.setText("");
        txt_soLuongTon.setText("");
        txt_giaNhap.setText("");
        txt_giaBan.setText("");
        txt_giamGia.setText("");
        txt_vat.setText("");
        cmb_loaiSP.setSelectedIndex(0);
        txt_baoHanh.setText("");
        txa_cauHinh.setText("");

    }

    private boolean tieuChuanThem() {
        String maSP = txt_maSanPham.getText();
        if (maSP.length() > 0) {
            showMessageFocus("Mã sản phẩm đã tồn tại", txt_maSanPham);
            return false;
        }
        return tieuChuanChung();
    }

    private boolean tieuChuanSua() {
        String maSP = txt_maSanPham.getText();
        if (maSP.length() == 0) {
            showMessageFocus("Vui lòng chọn sản phẩm cần sửa", txt_maSanPham);
            return false;
        }
        return tieuChuanChung();
    }

    private boolean tieuChuanChung() {
        String tenSP = txt_tenSP.getText().trim();
        String thuongHieu = txt_thuongHieu.getText().trim();
        String strGiaNhap = txt_giaNhap.getText().trim();
        String strGiamGia = txt_giamGia.getText().trim();
        String strVat = txt_vat.getText().trim();
        String strSoThang = txt_baoHanh.getText().trim();
        String cauHinh = txa_cauHinh.getText().replace(";\n", "; ").trim();
        if (tenSP.length() == 0) {
            showMessageFocus("Vui lòng nhập tên sản phẩm", txt_tenSP);
            return false;
        } else if (thuongHieu.length() == 0) {
            showMessageFocus("Vui lòng nhập tên thương hiệu", txt_thuongHieu);
            return false;
        } else if (strGiaNhap.length() <= 0) {
            showMessageFocus("Vui lòng nhập giá nhập", txt_giaNhap);
            return false;
        } else if (strGiamGia.length() <= 0) {
            showMessageFocus("Vui lòng nhập giá giảm", txt_giamGia);
            return false;
        } else if (strVat.length() <= 0) {
            showMessageFocus("Vui lòng nhập giá trị VAT", txt_vat);
            return false;
        } else if (strSoThang.length() <= 0) {
            showMessageFocus("Vui lòng nhập số tháng bảo hành", txt_baoHanh);
            return false;
        } else if (cauHinh.length() <= 0) {
            showMessageFocus("Vui lòng nhập cấu hình sản phẩm", txt_tenSP);
            return false;
        }

        if (!Pattern.matches("^(\\p{L}+-*\\p{L}+\\s*)+-(\\s*\\p{L}+)+$", thuongHieu)) {
            showMessageFocus("Thương hiệu - Quốc gia", txt_thuongHieu);
            return false;
        }

        if (!Pattern.matches("(.*:\\s.+;)+", cauHinh)) {
            JOptionPane.showMessageDialog(this, "Thuộc tính: dữ liệu;");
            txa_cauHinh.selectAll();
            txa_cauHinh.requestFocus();
            return false;
        }

        try {
            Double.parseDouble(strGiaNhap);
        } catch (Exception e) {
            showMessageFocus("Giá nhập không hợp lệ", txt_giaNhap);
            return false;
        }

        try {
            Double.parseDouble(strGiamGia);
        } catch (Exception e) {
            showMessageFocus("Giảm giá không hợp lệ", txt_giamGia);
            return false;
        }

        try {
            Double.parseDouble(strVat);
        } catch (Exception e) {
            showMessageFocus("Giá trị VAT không hợp lệ", txt_vat);
            return false;
        }

        try {
            Integer.parseInt(strSoThang);
        } catch (Exception e) {
            showMessageFocus("Số tháng bảo hành không hợp lệ", txt_baoHanh);
            return false;
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timKiem;
    private javax.swing.JButton btn_xoaTrang;
    private javax.swing.JComboBox<String> cmb_headerLoai;
    private javax.swing.JComboBox<String> cmb_headerThuongHieu;
    private javax.swing.JComboBox<String> cmb_loaiSP;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel lbl_baoHanh;
    private javax.swing.JLabel lbl_cauHinh;
    private javax.swing.JLabel lbl_giaBan;
    private javax.swing.JLabel lbl_giaNhap;
    private javax.swing.JLabel lbl_giamGia;
    private javax.swing.JLabel lbl_headerCauHinh;
    private javax.swing.JLabel lbl_headerLoai;
    private javax.swing.JLabel lbl_headerTH;
    private javax.swing.JLabel lbl_headerTen;
    private javax.swing.JLabel lbl_loaiSP;
    private javax.swing.JLabel lbl_maSanPham;
    private javax.swing.JLabel lbl_soLuongTon;
    private javax.swing.JLabel lbl_soTrang;
    private javax.swing.JLabel lbl_tenSP;
    private javax.swing.JLabel lbl_thuongHieu;
    private javax.swing.JLabel lbl_vat;
    private javax.swing.JPanel pnl_baoHanh;
    private javax.swing.JPanel pnl_bodyTT;
    private javax.swing.JPanel pnl_cauHinh;
    private javax.swing.JPanel pnl_danhSach;
    private javax.swing.JPanel pnl_dieuKhienBtn;
    private javax.swing.JPanel pnl_giaBan;
    private javax.swing.JPanel pnl_giaNhap;
    private javax.swing.JPanel pnl_giamGia;
    private javax.swing.JPanel pnl_headerCauHinh;
    private javax.swing.JPanel pnl_headerLoai;
    private javax.swing.JPanel pnl_headerLoaiTH;
    private javax.swing.JPanel pnl_headerOption;
    private javax.swing.JPanel pnl_headerSearch;
    private javax.swing.JPanel pnl_headerTH;
    private javax.swing.JPanel pnl_left;
    private javax.swing.JPanel pnl_loaiSP;
    private javax.swing.JPanel pnl_maSanPham;
    private javax.swing.JPanel pnl_nutQuanLy;
    private javax.swing.JPanel pnl_soLuongTon;
    private javax.swing.JPanel pnl_tenSP;
    private javax.swing.JPanel pnl_thongTin;
    private javax.swing.JPanel pnl_thuongHieu;
    private javax.swing.JPanel pnl_timKiem;
    private javax.swing.JPanel pnl_vat;
    private javax.swing.JScrollPane scr_2;
    private javax.swing.JScrollPane scr_3;
    private javax.swing.JScrollPane scr_cauHinh;
    private javax.swing.JTable tbl_products;
    private javax.swing.JTextArea txa_cauHinh;
    private javax.swing.JTextArea txa_headerCauHinh;
    private javax.swing.JTextField txt_baoHanh;
    private javax.swing.JTextField txt_giaBan;
    private javax.swing.JTextField txt_giaNhap;
    private javax.swing.JTextField txt_giamGia;
    private javax.swing.JTextField txt_maSanPham;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_soLuongTon;
    private javax.swing.JTextField txt_tenSP;
    private javax.swing.JTextField txt_thuongHieu;
    private javax.swing.JTextField txt_vat;
    // End of variables declaration//GEN-END:variables

}
