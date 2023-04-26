/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.sun.jarsigner.ContentSignerParameters;
import controller.KhoHang_bus;
import controller.SanPham_bus;
import controller.ThuongHieu_bus;
import java.text.NumberFormat;
import java.text.ParseException;
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
import model.sanpham.SanPham;
import model.sanpham.ThuongHieu;

/**
 *
 * @author nxnam
 */
public class Panel_QuanLySanPham extends javax.swing.JPanel {

    private DefaultTableModel tbl_ModelProduct;
    private String[] str_TenCot = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Thương hiệu", "Giá nhập", "Giá bán", "Giảm giá", "VAT", "Bảo hành", "Loại hàng", "Cấu hình"};
    private SanPham_bus sanPham_bus = new SanPham_bus();
    private ThuongHieu_bus thuongHieu_bus = new ThuongHieu_bus();
    NumberFormat vnd = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));

    /**
     * Creates new form Panel_QuanLySanPham
     */
    public Panel_QuanLySanPham() {
        tbl_ModelProduct = new DefaultTableModel(str_TenCot, 0);
        
        initComponents();
        
        initTableModel();
        alterTable();
        
        
        
        tbl_Products.getSelectionModel().addListSelectionListener((e)->{
            int row = tbl_Products.getSelectedRow();
            if(row != -1) {
                txtMaSanPham.setText(tbl_ModelProduct.getValueAt(row, 0).toString());
                txt_Tensp.setText(tbl_ModelProduct.getValueAt(row, 1).toString());
                txt_ThuongHieu.setText(tbl_ModelProduct.getValueAt(row, 2).toString());  
                txt_GiaNhap.setText(tbl_ModelProduct.getValueAt(row, 3).toString());
                txt_GiaBan.setText(tbl_ModelProduct.getValueAt(row, 4).toString());
                txt_GiamGia.setText(tbl_ModelProduct.getValueAt(row, 5).toString());
                txt_VAT.setText(tbl_ModelProduct.getValueAt(row, 6).toString());
                txt_BaoHanh.setText(tbl_ModelProduct.getValueAt(row, 7).toString());
                String txt_LH = tbl_ModelProduct.getValueAt(row, 8).toString();
                cmb_Loaisp.setSelectedItem(txt_LH);
                txtarea_CauHinh.setText(tbl_ModelProduct.getValueAt(row, 9).toString());

            }
        });
        
    }
    
    private void alterTable() {
        
        tbl_Products.setDefaultEditor(Object.class, null);
        
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);
        
        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);
        
        tbl_Products.getColumnModel().getColumn(0).setCellRenderer(leftAlign);
        tbl_Products.getColumnModel().getColumn(1).setCellRenderer(leftAlign);
        tbl_Products.getColumnModel().getColumn(2).setCellRenderer(leftAlign);
        tbl_Products.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_Products.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        tbl_Products.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        tbl_Products.getColumnModel().getColumn(6).setCellRenderer(rightAlign);
        tbl_Products.getColumnModel().getColumn(7).setCellRenderer(leftAlign);
        tbl_Products.getColumnModel().getColumn(8).setCellRenderer(leftAlign);
        tbl_Products.getColumnModel().getColumn(9).setCellRenderer(leftAlign);
        
        
        tbl_Products.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_Products.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_Products.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbl_Products.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Products.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_Products.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_Products.getColumnModel().getColumn(5).setPreferredWidth(65);
        tbl_Products.getColumnModel().getColumn(6).setPreferredWidth(65);
        tbl_Products.getColumnModel().getColumn(7).setPreferredWidth(70);
        tbl_Products.getColumnModel().getColumn(8).setPreferredWidth(65);
        tbl_Products.getColumnModel().getColumn(9).setPreferredWidth(500);
    }
    
    private void initTableModel() {
        ArrayList<SanPham> list = new ArrayList<>();
        renderProductTable(sanPham_bus.getAllSanPham());
    }
    
    public void searchTheoTen() {
        String input = txt_search.getText().trim();
        if (input.length() > 0) {
            ArrayList<SanPham> list = sanPham_bus.timSanPhamTheoTen(input);
            renderProductTable(list);
        } 
    }
    
    public void searchTheoCauHinh() {
        String input = txa_headerCauHinh.getText().trim();
        if(input.length() >= 0) {
            ArrayList<SanPham> list = sanPham_bus.timSanPhamTheoCauHinh(input);
            renderProductTable(list);
        }
    }
    
    public void searchTheoLoai() {
        String input =  cmb_headerLoai.getSelectedItem().toString();
         ArrayList<SanPham> list = sanPham_bus.timSanPhamTheoLoai(input);
         renderProductTable(list); 
    }
    
     public void searchTheoThuongHieu() {
        String input = cmb_headerThuongHieu.getSelectedItem().toString();
        ArrayList<SanPham> list = sanPham_bus.timSanPhamTheoThuongHieu(input);
        renderProductTable(list);
    }
    
    
     public void renderAll(){
        ArrayList<SanPham> list = sanPham_bus.getAllSanPham();
        renderProductTable(list);
     }
    
    private void renderProductTable(ArrayList<SanPham> list) {
        tbl_ModelProduct.setRowCount(0);
        
        for (SanPham sp : list) {
            ArrayList<ThuongHieu> th = thuongHieu_bus.getThuongHieuTheoMa(sp.getThuongHieu().getMaTH());
            Object[] row = new Object[]{sp.getMaSP(), sp.getTenSP(), th.get(0).toString(), vnd.format(sp.getGiaNhap()), vnd.format(sp.getGiaBan()), Math.round(sp.getGiamGia()) + "%", sp.getVAT(), Math.round(sp.getSoThangBaoHanh()) + " tháng", sp.getTenLoai(), sp.getCauHinh()};
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

        jTextField2 = new javax.swing.JTextField();
        pnl_ThongTin = new javax.swing.JPanel();
        pnl_body_TT = new javax.swing.JPanel();
        pnl_MaSanPham = new javax.swing.JPanel();
        lbl_MaSanPham = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        pnl_Tensp = new javax.swing.JPanel();
        lbl_Tensp = new javax.swing.JLabel();
        txt_Tensp = new javax.swing.JTextField();
        pnl_ThuongHieu = new javax.swing.JPanel();
        lbl_ThuongHieu = new javax.swing.JLabel();
        txt_ThuongHieu = new javax.swing.JTextField();
        pnl_GiaNhap = new javax.swing.JPanel();
        lbl_GiaNhap = new javax.swing.JLabel();
        txt_GiaNhap = new javax.swing.JTextField();
        pnl_GiaBan = new javax.swing.JPanel();
        lbl_GiaBan = new javax.swing.JLabel();
        txt_GiaBan = new javax.swing.JTextField();
        pnl_GiamGia = new javax.swing.JPanel();
        lbl_GiamGia = new javax.swing.JLabel();
        txt_GiamGia = new javax.swing.JTextField();
        pnl_VAT = new javax.swing.JPanel();
        lbl_VAT = new javax.swing.JLabel();
        txt_VAT = new javax.swing.JTextField();
        pnl_Loaisp = new javax.swing.JPanel();
        lbl_Loaisp = new javax.swing.JLabel();
        cmb_Loaisp = new javax.swing.JComboBox<>();
        pnl_BaoHanh = new javax.swing.JPanel();
        lbl_BaoHanh = new javax.swing.JLabel();
        txt_BaoHanh = new javax.swing.JTextField();
        pnl_CauHinh = new javax.swing.JPanel();
        lbl_CauHinh = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jScrollPane2 = new javax.swing.JScrollPane();
        txtarea_CauHinh = new javax.swing.JTextArea();
        pnl_NutQuanLy = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_XoaTrang = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        pnl_TimKiem = new javax.swing.JPanel();
        pnl_headerSearch = new javax.swing.JPanel();
        lbl_headerTen = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        pnl_searchBtn = new javax.swing.JPanel();
        btn_TimKiem = new javax.swing.JButton();
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
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        pnl_DanhSach = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_Products = new javax.swing.JTable();

        jTextField2.setText("jTextField2");

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnl_ThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_ThongTin.setPreferredSize(new java.awt.Dimension(320, 600));
        pnl_ThongTin.setLayout(new java.awt.BorderLayout());

        pnl_body_TT.setBackground(new java.awt.Color(255, 255, 255));
        pnl_body_TT.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnl_body_TT.setPreferredSize(new java.awt.Dimension(140, 20));
        pnl_body_TT.setLayout(new javax.swing.BoxLayout(pnl_body_TT, javax.swing.BoxLayout.Y_AXIS));

        pnl_MaSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnl_MaSanPham.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_MaSanPham.setLayout(new javax.swing.BoxLayout(pnl_MaSanPham, javax.swing.BoxLayout.LINE_AXIS));

        lbl_MaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_MaSanPham.setText("Mã sản phẩm");
        lbl_MaSanPham.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_MaSanPham.add(lbl_MaSanPham);

        txtMaSanPham.setEditable(false);
        txtMaSanPham.setMaximumSize(new java.awt.Dimension(1000, 500));
        txtMaSanPham.setMinimumSize(new java.awt.Dimension(140, 16));
        txtMaSanPham.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_MaSanPham.add(txtMaSanPham);

        pnl_body_TT.add(pnl_MaSanPham);

        pnl_Tensp.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Tensp.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_Tensp.setLayout(new javax.swing.BoxLayout(pnl_Tensp, javax.swing.BoxLayout.LINE_AXIS));

        lbl_Tensp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Tensp.setText("Tên sản phẩm:");
        lbl_Tensp.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_Tensp.add(lbl_Tensp);

        txt_Tensp.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_Tensp.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_Tensp.setPreferredSize(new java.awt.Dimension(160, 24));
        txt_Tensp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenspActionPerformed(evt);
            }
        });
        pnl_Tensp.add(txt_Tensp);

        pnl_body_TT.add(pnl_Tensp);

        pnl_ThuongHieu.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ThuongHieu.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_ThuongHieu.setLayout(new javax.swing.BoxLayout(pnl_ThuongHieu, javax.swing.BoxLayout.LINE_AXIS));

        lbl_ThuongHieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ThuongHieu.setText("Thương hiệu:");
        lbl_ThuongHieu.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_ThuongHieu.add(lbl_ThuongHieu);

        txt_ThuongHieu.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_ThuongHieu.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_ThuongHieu.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_ThuongHieu.add(txt_ThuongHieu);

        pnl_body_TT.add(pnl_ThuongHieu);

        pnl_GiaNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_GiaNhap.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_GiaNhap.setLayout(new javax.swing.BoxLayout(pnl_GiaNhap, javax.swing.BoxLayout.LINE_AXIS));

        lbl_GiaNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_GiaNhap.setText("Giá nhập:");
        lbl_GiaNhap.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_GiaNhap.add(lbl_GiaNhap);

        txt_GiaNhap.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_GiaNhap.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_GiaNhap.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_GiaNhap.add(txt_GiaNhap);

        pnl_body_TT.add(pnl_GiaNhap);

        pnl_GiaBan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_GiaBan.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_GiaBan.setLayout(new javax.swing.BoxLayout(pnl_GiaBan, javax.swing.BoxLayout.LINE_AXIS));

        lbl_GiaBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_GiaBan.setText("Giá bán:");
        lbl_GiaBan.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_GiaBan.add(lbl_GiaBan);

        txt_GiaBan.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_GiaBan.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_GiaBan.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_GiaBan.add(txt_GiaBan);

        pnl_body_TT.add(pnl_GiaBan);

        pnl_GiamGia.setBackground(new java.awt.Color(255, 255, 255));
        pnl_GiamGia.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_GiamGia.setLayout(new javax.swing.BoxLayout(pnl_GiamGia, javax.swing.BoxLayout.LINE_AXIS));

        lbl_GiamGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_GiamGia.setText("Giảm giá:");
        lbl_GiamGia.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_GiamGia.add(lbl_GiamGia);

        txt_GiamGia.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_GiamGia.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_GiamGia.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_GiamGia.add(txt_GiamGia);

        pnl_body_TT.add(pnl_GiamGia);

        pnl_VAT.setBackground(new java.awt.Color(255, 255, 255));
        pnl_VAT.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_VAT.setLayout(new javax.swing.BoxLayout(pnl_VAT, javax.swing.BoxLayout.LINE_AXIS));

        lbl_VAT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_VAT.setText("VAT:");
        lbl_VAT.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_VAT.add(lbl_VAT);

        txt_VAT.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_VAT.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_VAT.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_VAT.add(txt_VAT);

        pnl_body_TT.add(pnl_VAT);

        pnl_Loaisp.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Loaisp.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_Loaisp.setLayout(new javax.swing.BoxLayout(pnl_Loaisp, javax.swing.BoxLayout.LINE_AXIS));

        lbl_Loaisp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Loaisp.setText("Loại hàng:");
        lbl_Loaisp.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_Loaisp.add(lbl_Loaisp);

        cmb_Loaisp.setMaximumRowCount(6);
        cmb_Loaisp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU", "MainBoard", "VGA", "RAM", "Ổ cứng", "Nguồn", "Case", "Tản nhiệt", "Chuột", "Bàn phím" }));
        cmb_Loaisp.setMaximumSize(new java.awt.Dimension(1000, 500));
        cmb_Loaisp.setMinimumSize(new java.awt.Dimension(140, 16));
        cmb_Loaisp.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_Loaisp.add(cmb_Loaisp);

        pnl_body_TT.add(pnl_Loaisp);

        pnl_BaoHanh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_BaoHanh.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_BaoHanh.setLayout(new javax.swing.BoxLayout(pnl_BaoHanh, javax.swing.BoxLayout.LINE_AXIS));

        lbl_BaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_BaoHanh.setText("Bảo hành (tháng):");
        lbl_BaoHanh.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_BaoHanh.add(lbl_BaoHanh);

        txt_BaoHanh.setMaximumSize(new java.awt.Dimension(1000, 500));
        txt_BaoHanh.setMinimumSize(new java.awt.Dimension(140, 16));
        txt_BaoHanh.setPreferredSize(new java.awt.Dimension(160, 24));
        pnl_BaoHanh.add(txt_BaoHanh);

        pnl_body_TT.add(pnl_BaoHanh);

        pnl_CauHinh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_CauHinh.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_CauHinh.setLayout(new javax.swing.BoxLayout(pnl_CauHinh, javax.swing.BoxLayout.LINE_AXIS));

        lbl_CauHinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_CauHinh.setText("Cấu hình:");
        lbl_CauHinh.setPreferredSize(new java.awt.Dimension(138, 138));
        pnl_CauHinh.add(lbl_CauHinh);
        pnl_CauHinh.add(filler2);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtarea_CauHinh.setColumns(20);
        txtarea_CauHinh.setRows(5);
        txtarea_CauHinh.setPreferredSize(new java.awt.Dimension(200, 24));
        jScrollPane2.setViewportView(txtarea_CauHinh);

        pnl_CauHinh.add(jScrollPane2);

        pnl_body_TT.add(pnl_CauHinh);

        pnl_ThongTin.add(pnl_body_TT, java.awt.BorderLayout.CENTER);

        pnl_NutQuanLy.setBackground(new java.awt.Color(255, 255, 255));
        pnl_NutQuanLy.setLayout(new java.awt.GridLayout(1, 0));

        btn_Them.setText("Thêm");
        btn_Them.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_Them.setPreferredSize(new java.awt.Dimension(80, 32));
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        pnl_NutQuanLy.add(btn_Them);

        btn_XoaTrang.setLabel("Xóa trắng");
        btn_XoaTrang.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_XoaTrang.setPreferredSize(new java.awt.Dimension(80, 40));
        btn_XoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaTrangActionPerformed(evt);
            }
        });
        pnl_NutQuanLy.add(btn_XoaTrang);

        btn_Sua.setText("Sửa");
        btn_Sua.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_Sua.setPreferredSize(new java.awt.Dimension(80, 40));
        pnl_NutQuanLy.add(btn_Sua);

        pnl_ThongTin.add(pnl_NutQuanLy, java.awt.BorderLayout.SOUTH);

        add(pnl_ThongTin, java.awt.BorderLayout.EAST);

        pnl_TimKiem.setBackground(new java.awt.Color(255, 255, 255));
        pnl_TimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnl_TimKiem.setPreferredSize(new java.awt.Dimension(0, 150));
        pnl_TimKiem.setLayout(new javax.swing.BoxLayout(pnl_TimKiem, javax.swing.BoxLayout.Y_AXIS));

        pnl_headerSearch.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerSearch.setPreferredSize(new java.awt.Dimension(0, 50));
        pnl_headerSearch.setLayout(new javax.swing.BoxLayout(pnl_headerSearch, javax.swing.BoxLayout.X_AXIS));

        lbl_headerTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_headerTen.setText("Tên sản phẩm:");
        lbl_headerTen.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_headerSearch.add(lbl_headerTen);

        txt_search.setPreferredSize(new java.awt.Dimension(650, 0));
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        pnl_headerSearch.add(txt_search);

        pnl_searchBtn.setMaximumSize(new java.awt.Dimension(4001111, 2147483647));
        pnl_searchBtn.setMinimumSize(new java.awt.Dimension(1000, 50));
        pnl_searchBtn.setPreferredSize(new java.awt.Dimension(140, 50));
        pnl_searchBtn.setLayout(new java.awt.BorderLayout());

        btn_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_TimKiem.setMaximumSize(new java.awt.Dimension(400, 1000));
        btn_TimKiem.setMinimumSize(new java.awt.Dimension(400, 50));
        btn_TimKiem.setPreferredSize(new java.awt.Dimension(200, 50));
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });
        pnl_searchBtn.add(btn_TimKiem, java.awt.BorderLayout.CENTER);

        pnl_headerSearch.add(pnl_searchBtn);

        pnl_TimKiem.add(pnl_headerSearch);

        pnl_headerOption.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerOption.setLayout(new java.awt.GridLayout(1, 2));

        pnl_headerCauHinh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_headerCauHinh.setLayout(new javax.swing.BoxLayout(pnl_headerCauHinh, javax.swing.BoxLayout.LINE_AXIS));

        lbl_headerCauHinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_headerCauHinh.setText("Cấu hình:");
        lbl_headerCauHinh.setPreferredSize(new java.awt.Dimension(120, 0));
        pnl_headerCauHinh.add(lbl_headerCauHinh);

        txa_headerCauHinh.setColumns(20);
        txa_headerCauHinh.setRows(5);
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

        pnl_TimKiem.add(pnl_headerOption);
        pnl_TimKiem.add(filler12);

        add(pnl_TimKiem, java.awt.BorderLayout.NORTH);

        pnl_DanhSach.setBackground(new java.awt.Color(255, 255, 255));
        pnl_DanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_DanhSach.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setToolTipText("");
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbl_Products.setModel(tbl_ModelProduct);
        tbl_Products.setRowHeight(30);
        jScrollPane3.setViewportView(tbl_Products);

        pnl_DanhSach.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        add(pnl_DanhSach, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
       // TODO add your handling code here:
       searchTheoThuongHieu();
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void txt_TenspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenspActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        searchTheoTen();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void showMessageFocus (String msg, JTextField txt) {
        JOptionPane.showMessageDialog(this, msg);
        txt.selectAll();
        txt.requestFocus();
    }
    
    private void xoaTrang () {
        txtMaSanPham.setText("");
        txt_Tensp.setText("");
        txt_ThuongHieu.setText("");
        txt_GiaNhap.setText("");
        txt_GiaBan.setText("");
        txt_GiamGia.setText("");
        txt_VAT.setText("");
        cmb_Loaisp.setSelectedIndex(0);
        txt_BaoHanh.setText("");
        txtarea_CauHinh.setText("");
        
    }
    
    private boolean tieuChuanDauVao(String tenSP, String thuongHieu, String strGiaNhap, 
            String strGiamGia, String strloai, String strVat, String strSoThang, String cauHinh) {
        
        if(tenSP.length()<=0) {
            showMessageFocus("Vui lòng nhập tên sản phẩm", txt_Tensp);
            return false;
        }
        
        else if(thuongHieu.length()<=0){
            showMessageFocus("Vui lòng nhập tên thương hiệu", txt_ThuongHieu);
            return false;
        }
        
        else if(strGiaNhap.length()<=0){
            showMessageFocus("Vui lòng nhập giá nhập", txt_GiaNhap);
            return false;
        }
        else if(!Pattern.matches("^[0-9]*\\.?[0-9]+$", strGiaNhap)) {
            showMessageFocus("Giá nhập không hợp lệ", txt_GiaNhap);
            return false;
        }
        
        else if(strGiamGia.length()<=0){
            showMessageFocus("Vui lòng nhập giá giảm", txt_GiamGia);
            return false;
        }
        else if(!Pattern.matches("^[0-9]*\\.?[0-9]+$", strGiamGia)) {
            showMessageFocus("Giảm giá không hợp lệ", txt_GiamGia);
            return false;
        }
        
        else if(strVat.length()<=0){
            showMessageFocus("Vui lòng nhập giá trị VAT", txt_VAT);
            return false;
        }
        else if (!Pattern.matches("^[0-9]*\\.?[0-9]+$", strVat)){
            showMessageFocus("Giá trị VAT không hợp lệ", txt_VAT);
            return false;
        }
        
        else if(strSoThang.length()<=0){
            showMessageFocus("Vui lòng nhập số tháng bảo hành", txt_BaoHanh);
            return false;
        }
        else if(!Pattern.matches("^[1-9][0-9]*$", strSoThang)){
            showMessageFocus("Số tháng bảo hành không hợp lệ", txt_BaoHanh);
            return false;
        }
        
        else if(cauHinh.length()<=0){
            showMessageFocus("Vui lòng nhập cấu hình sản phẩm", txt_Tensp);
            return false;
        }
        return true;
        
    }
    
    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        String maSP = sanPham_bus.sinhMa();
        String tenSP = txt_Tensp.getText().trim();
        String thuongHieu = txt_ThuongHieu.getText().trim();
        String strGiaNhap = txt_GiaNhap.getText().trim();
        String strGiamGia = txt_GiamGia.getText().trim();
        String strloai = ((String) cmb_Loaisp.getSelectedItem()).trim();
        String strVat = txt_VAT.getText().trim();
        String strSoThang = txt_BaoHanh.getText().trim();
        String cauHinh = txtarea_CauHinh.getText().trim();
        
        
       
        if(tieuChuanDauVao(tenSP, thuongHieu, strGiaNhap, strGiamGia, strloai, strVat, strSoThang, cauHinh)) {
            try {
                double giaNhap = Double.parseDouble(strGiaNhap);
                double giamGia = Double.parseDouble(strGiamGia);
                double VAT = Double.parseDouble(strVat);
                int soThangBaoHanh = Integer.parseInt(strSoThang);
                int loai = sanPham_bus.loaiParseInt(strloai);
//                ThuongHieu TH = thuongHieu_bus.taoThuongHieu(thuongHieu);
                SanPham sp = new SanPham(maSP, tenSP, giaNhap, giamGia, loai, VAT, null, soThangBaoHanh, cauHinh);
                if(sanPham_bus.themSanPham(sp)){
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    xoaTrang();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Thêm không thành công", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(Panel_QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào chưa đúng tiêu chuẩn", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        renderAll();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaTrangActionPerformed
        // TODO add your handling code here:
//        xoaTrang();
        txtMaSanPham.setText("");
        txt_Tensp.setText("hang dep");
        txt_ThuongHieu.setText("mi - hoa");
        txt_GiaNhap.setText("12");
        txt_GiaBan.setText("12");
        txt_GiamGia.setText("1");
        txt_VAT.setText("1");
        cmb_Loaisp.setSelectedIndex(0);
        txt_BaoHanh.setText("12");
        txtarea_CauHinh.setText("tot");
    }//GEN-LAST:event_btn_XoaTrangActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_XoaTrang;
    private javax.swing.JComboBox<String> cmb_Loaisp;
    private javax.swing.JComboBox<String> cmb_headerLoai;
    private javax.swing.JComboBox<String> cmb_headerThuongHieu;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbl_BaoHanh;
    private javax.swing.JLabel lbl_CauHinh;
    private javax.swing.JLabel lbl_GiaBan;
    private javax.swing.JLabel lbl_GiaNhap;
    private javax.swing.JLabel lbl_GiamGia;
    private javax.swing.JLabel lbl_Loaisp;
    private javax.swing.JLabel lbl_MaSanPham;
    private javax.swing.JLabel lbl_Tensp;
    private javax.swing.JLabel lbl_ThuongHieu;
    private javax.swing.JLabel lbl_VAT;
    private javax.swing.JLabel lbl_headerCauHinh;
    private javax.swing.JLabel lbl_headerLoai;
    private javax.swing.JLabel lbl_headerTH;
    private javax.swing.JLabel lbl_headerTen;
    private javax.swing.JPanel pnl_BaoHanh;
    private javax.swing.JPanel pnl_CauHinh;
    private javax.swing.JPanel pnl_DanhSach;
    private javax.swing.JPanel pnl_GiaBan;
    private javax.swing.JPanel pnl_GiaNhap;
    private javax.swing.JPanel pnl_GiamGia;
    private javax.swing.JPanel pnl_Loaisp;
    private javax.swing.JPanel pnl_MaSanPham;
    private javax.swing.JPanel pnl_NutQuanLy;
    private javax.swing.JPanel pnl_Tensp;
    private javax.swing.JPanel pnl_ThongTin;
    private javax.swing.JPanel pnl_ThuongHieu;
    private javax.swing.JPanel pnl_TimKiem;
    private javax.swing.JPanel pnl_VAT;
    private javax.swing.JPanel pnl_body_TT;
    private javax.swing.JPanel pnl_headerCauHinh;
    private javax.swing.JPanel pnl_headerLoai;
    private javax.swing.JPanel pnl_headerLoaiTH;
    private javax.swing.JPanel pnl_headerOption;
    private javax.swing.JPanel pnl_headerSearch;
    private javax.swing.JPanel pnl_headerTH;
    private javax.swing.JPanel pnl_searchBtn;
    private javax.swing.JScrollPane scr_cauHinh;
    private javax.swing.JTable tbl_Products;
    private javax.swing.JTextArea txa_headerCauHinh;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txt_BaoHanh;
    private javax.swing.JTextField txt_GiaBan;
    private javax.swing.JTextField txt_GiaNhap;
    private javax.swing.JTextField txt_GiamGia;
    private javax.swing.JTextField txt_Tensp;
    private javax.swing.JTextField txt_ThuongHieu;
    private javax.swing.JTextField txt_VAT;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextArea txtarea_CauHinh;
    // End of variables declaration//GEN-END:variables

    


}
