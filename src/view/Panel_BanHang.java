/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.sanpham.SanPham;
import model.sanpham.ThuongHieu;

/**
 *
 * @author thanh
 */
public class Panel_BanHang extends javax.swing.JPanel {

    private DefaultTableModel tblModel_product;
    private DefaultTableModel tblModel_carts;

    /**
     * Creates new form Panel_BanHang
     */
    public Panel_BanHang() {
        initTableModel();
        initComponents();
    }

    public void initTableModel() {
        // Products
        tblModel_product = new DefaultTableModel(new String[]{"Mã", "Tên", "Loại", "Thương hiệu", "Số lượng", "Giá bán"
        }, 0);

        // Carts
        tblModel_carts = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Số lượng", "Đơn giá"
        }, 0);

        ArrayList<SanPham> list = new ArrayList<>();
        try {
            list.add(new SanPham("sp1", "chuot1", 200, 10, SanPham.CHUOT, 8, new ThuongHieu("th1", "Itel", "Trung"), 5, "DPI:3000"));
            list.add(new SanPham("sp2", "chuot2", 200, 10, SanPham.CHUOT, 8, new ThuongHieu("th1", "Itel", "Trung"), 5, "DPI:3000"));
            list.add(new SanPham("sp3", "chuot3", 200, 10, SanPham.CHUOT, 8, new ThuongHieu("th1", "Itel", "Trung"), 5, "DPI:3000"));
            renderProductTable(list);
            renderCartTable(list);
        } catch (Exception e) {

        }
    }

    public void renderProductTable(ArrayList<SanPham> list) {
        tblModel_product.setRowCount(0);
        for (SanPham sp : list) {
            Object[] row = new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), sp.getThuongHieu().toString(), 0, sp.getGiaBan()};
            tblModel_product.addRow(row);
        }
    }

    public void renderCartTable(ArrayList<SanPham> list) {
        tblModel_carts.setRowCount(0);
        for (SanPham sp : list) {
            tblModel_carts.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), 0, sp.getGiaBan()});
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

        pnl_search = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        pnl_products = new javax.swing.JPanel();
        pnl_productsTable = new javax.swing.JPanel();
        scr_products = new javax.swing.JScrollPane();
        tbl_products = new javax.swing.JTable();
        pnl_productsControl = new javax.swing.JPanel();
        pnl_left = new javax.swing.JPanel();
        btn_reset = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_next = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        pnl_addToCart = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        pnl_cart = new javax.swing.JPanel();
        pnl_cartCenter = new javax.swing.JPanel();
        pnl_cartTable = new javax.swing.JPanel();
        scr_cart = new javax.swing.JScrollPane();
        tbl_cart = new javax.swing.JTable();
        pnl_cartFooter = new javax.swing.JPanel();
        btn_remove = new javax.swing.JButton();
        pnl_orderInfo = new javax.swing.JPanel();
        pnl_box1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_sdt1 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        pnl_box2 = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_sdt = new javax.swing.JLabel();
        txt_hoTen = new javax.swing.JTextField();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_ten = new javax.swing.JLabel();
        txt_hangTV = new javax.swing.JTextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        pnl_box3 = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_sdt4 = new javax.swing.JLabel();
        txt_tongTien = new javax.swing.JTextField();
        lbl_ten1 = new javax.swing.JLabel();
        txt_giamGia = new javax.swing.JTextField();
        pnl_box4 = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_sdt3 = new javax.swing.JLabel();
        txt_thanhTien = new javax.swing.JTextField();
        pnl_cartControl = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_export = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnl_search.setBackground(new java.awt.Color(255, 255, 255));
        pnl_search.setPreferredSize(new java.awt.Dimension(0, 50));
        pnl_search.setLayout(new javax.swing.BoxLayout(pnl_search, javax.swing.BoxLayout.X_AXIS));

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_search.setToolTipText("Vui lòng nhập mã sản phẩm");
        pnl_search.add(txt_search);

        btn_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_search.setText("Tìm kiếm");
        btn_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_search.setMaximumSize(new java.awt.Dimension(75, 50));
        btn_search.setMinimumSize(new java.awt.Dimension(75, 50));
        btn_search.setPreferredSize(new java.awt.Dimension(120, 50));
        pnl_search.add(btn_search);

        add(pnl_search, java.awt.BorderLayout.NORTH);

        pnl_products.setBackground(new java.awt.Color(255, 255, 255));
        pnl_products.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_products.setPreferredSize(new java.awt.Dimension(600, 700));
        pnl_products.setLayout(new java.awt.BorderLayout());

        pnl_productsTable.setBackground(new java.awt.Color(255, 255, 255));
        pnl_productsTable.setLayout(new java.awt.BorderLayout());

        tbl_products.setAutoCreateRowSorter(true);
        tbl_products.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_products.setModel(tblModel_product);
        tbl_products.setRowHeight(30);
        tbl_products.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbl_products.setShowGrid(true);
        scr_products.setViewportView(tbl_products);

        pnl_productsTable.add(scr_products, java.awt.BorderLayout.CENTER);

        pnl_products.add(pnl_productsTable, java.awt.BorderLayout.CENTER);

        pnl_productsControl.setBackground(new java.awt.Color(255, 255, 255));
        pnl_productsControl.setPreferredSize(new java.awt.Dimension(0, 45));
        pnl_productsControl.setLayout(new javax.swing.BoxLayout(pnl_productsControl, javax.swing.BoxLayout.LINE_AXIS));

        pnl_left.setBackground(new java.awt.Color(255, 255, 255));
        pnl_left.setLayout(new java.awt.GridLayout());

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartReset.png"))); // NOI18N
        btn_reset.setToolTipText("Tải lại");
        btn_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reset.setPreferredSize(new java.awt.Dimension(45, 45));
        pnl_left.add(btn_reset);

        btn_prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartPrev.png"))); // NOI18N
        btn_prev.setToolTipText("Trang trước");
        btn_prev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_prev.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_prev.setIconTextGap(10);
        btn_prev.setPreferredSize(new java.awt.Dimension(45, 45));
        pnl_left.add(btn_prev);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("1/3");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(45, 45));
        pnl_left.add(jLabel1);

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartNext.png"))); // NOI18N
        btn_next.setToolTipText("Trang kế");
        btn_next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_next.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_next.setIconTextGap(10);
        btn_next.setPreferredSize(new java.awt.Dimension(45, 45));
        pnl_left.add(btn_next);

        pnl_productsControl.add(pnl_left);
        pnl_productsControl.add(filler7);

        pnl_addToCart.setLayout(new java.awt.GridLayout());

        btn_add.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartAdd.png"))); // NOI18N
        btn_add.setText("Thêm giỏ hàng");
        btn_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_add.setIconTextGap(15);
        pnl_addToCart.add(btn_add);

        pnl_productsControl.add(pnl_addToCart);

        pnl_products.add(pnl_productsControl, java.awt.BorderLayout.SOUTH);

        add(pnl_products, java.awt.BorderLayout.CENTER);

        pnl_cart.setPreferredSize(new java.awt.Dimension(400, 0));
        pnl_cart.setLayout(new java.awt.BorderLayout());

        pnl_cartCenter.setPreferredSize(new java.awt.Dimension(400, 600));
        pnl_cartCenter.setLayout(new java.awt.BorderLayout());

        pnl_cartTable.setBackground(new java.awt.Color(255, 255, 255));
        pnl_cartTable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_cartTable.setPreferredSize(new java.awt.Dimension(400, 400));
        pnl_cartTable.setLayout(new java.awt.BorderLayout());

        tbl_cart.setModel(tblModel_carts);
        tbl_cart.setRowHeight(30);
        scr_cart.setViewportView(tbl_cart);

        pnl_cartTable.add(scr_cart, java.awt.BorderLayout.CENTER);

        pnl_cartFooter.setLayout(new java.awt.GridLayout());

        btn_remove.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartRemove.png"))); // NOI18N
        btn_remove.setText("Xóa khỏi giỏ hàng");
        btn_remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_remove.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_remove.setIconTextGap(10);
        btn_remove.setPreferredSize(new java.awt.Dimension(100, 45));
        pnl_cartFooter.add(btn_remove);

        pnl_cartTable.add(pnl_cartFooter, java.awt.BorderLayout.PAGE_END);

        pnl_cartCenter.add(pnl_cartTable, java.awt.BorderLayout.CENTER);

        pnl_orderInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnl_orderInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_orderInfo.setPreferredSize(new java.awt.Dimension(400, 200));
        pnl_orderInfo.setLayout(new javax.swing.BoxLayout(pnl_orderInfo, javax.swing.BoxLayout.Y_AXIS));

        pnl_box1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box1.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box1.setLayout(new javax.swing.BoxLayout(pnl_box1, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box1.add(filler1);

        lbl_sdt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_sdt1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_sdt1.setText("SĐT");
        lbl_sdt1.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_box1.add(lbl_sdt1);

        txt_sdt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_sdt.setText("032242183");
        pnl_box1.add(txt_sdt);

        pnl_orderInfo.add(pnl_box1);

        pnl_box2.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box2.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box2.setLayout(new javax.swing.BoxLayout(pnl_box2, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box2.add(filler2);

        lbl_sdt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_sdt.setForeground(new java.awt.Color(102, 102, 102));
        lbl_sdt.setText("Họ tên");
        lbl_sdt.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_box2.add(lbl_sdt);

        txt_hoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hoTen.setText("Nguyễn Thanh Cảnh");
        txt_hoTen.setPreferredSize(new java.awt.Dimension(100, 0));
        pnl_box2.add(txt_hoTen);
        pnl_box2.add(filler5);

        lbl_ten.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ten.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ten.setText("Hạng TV");
        lbl_ten.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_box2.add(lbl_ten);

        txt_hangTV.setEditable(false);
        txt_hangTV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hangTV.setText("Vàng");
        txt_hangTV.setPreferredSize(new java.awt.Dimension(0, 0));
        pnl_box2.add(txt_hangTV);
        pnl_box2.add(filler6);

        pnl_orderInfo.add(pnl_box2);

        pnl_box3.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box3.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box3.setLayout(new javax.swing.BoxLayout(pnl_box3, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box3.add(filler3);

        lbl_sdt4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_sdt4.setForeground(new java.awt.Color(102, 102, 102));
        lbl_sdt4.setText("Tổng tiền");
        lbl_sdt4.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_box3.add(lbl_sdt4);

        txt_tongTien.setEditable(false);
        txt_tongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tongTien.setText("1,000,000");
        txt_tongTien.setPreferredSize(new java.awt.Dimension(100, 0));
        pnl_box3.add(txt_tongTien);

        lbl_ten1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ten1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ten1.setText("Giảm giá");
        lbl_ten1.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_box3.add(lbl_ten1);

        txt_giamGia.setEditable(false);
        txt_giamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_giamGia.setText("100,000");
        txt_giamGia.setPreferredSize(new java.awt.Dimension(0, 0));
        pnl_box3.add(txt_giamGia);

        pnl_orderInfo.add(pnl_box3);

        pnl_box4.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box4.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box4.setLayout(new javax.swing.BoxLayout(pnl_box4, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box4.add(filler4);

        lbl_sdt3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_sdt3.setForeground(new java.awt.Color(102, 102, 102));
        lbl_sdt3.setText("Thành tiền");
        lbl_sdt3.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_box4.add(lbl_sdt3);

        txt_thanhTien.setEditable(false);
        txt_thanhTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_thanhTien.setForeground(new java.awt.Color(65, 165, 238));
        txt_thanhTien.setText("900,000");
        pnl_box4.add(txt_thanhTien);

        pnl_orderInfo.add(pnl_box4);

        pnl_cartCenter.add(pnl_orderInfo, java.awt.BorderLayout.SOUTH);

        pnl_cart.add(pnl_cartCenter, java.awt.BorderLayout.CENTER);

        pnl_cartControl.setBackground(new java.awt.Color(255, 255, 255));
        pnl_cartControl.setPreferredSize(new java.awt.Dimension(400, 50));
        pnl_cartControl.setLayout(new java.awt.GridLayout(1, 2));

        btn_cancel.setBackground(new java.awt.Color(255, 153, 153));
        btn_cancel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_cancel.setForeground(new java.awt.Color(242, 242, 242));
        btn_cancel.setText("HỦY BỎ");
        btn_cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_cartControl.add(btn_cancel);

        btn_export.setBackground(new java.awt.Color(65, 165, 238));
        btn_export.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_export.setForeground(new java.awt.Color(242, 242, 242));
        btn_export.setText("XUẤT");
        btn_export.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_cartControl.add(btn_export);

        pnl_cart.add(pnl_cartControl, java.awt.BorderLayout.SOUTH);

        add(pnl_cart, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_sdt;
    private javax.swing.JLabel lbl_sdt1;
    private javax.swing.JLabel lbl_sdt3;
    private javax.swing.JLabel lbl_sdt4;
    private javax.swing.JLabel lbl_ten;
    private javax.swing.JLabel lbl_ten1;
    private javax.swing.JPanel pnl_addToCart;
    private javax.swing.JPanel pnl_box1;
    private javax.swing.JPanel pnl_box2;
    private javax.swing.JPanel pnl_box3;
    private javax.swing.JPanel pnl_box4;
    private javax.swing.JPanel pnl_cart;
    private javax.swing.JPanel pnl_cartCenter;
    private javax.swing.JPanel pnl_cartControl;
    private javax.swing.JPanel pnl_cartFooter;
    private javax.swing.JPanel pnl_cartTable;
    private javax.swing.JPanel pnl_left;
    private javax.swing.JPanel pnl_orderInfo;
    private javax.swing.JPanel pnl_products;
    private javax.swing.JPanel pnl_productsControl;
    private javax.swing.JPanel pnl_productsTable;
    private javax.swing.JPanel pnl_search;
    private javax.swing.JScrollPane scr_cart;
    private javax.swing.JScrollPane scr_products;
    private javax.swing.JTable tbl_cart;
    private javax.swing.JTable tbl_products;
    private javax.swing.JTextField txt_giamGia;
    private javax.swing.JTextField txt_hangTV;
    private javax.swing.JTextField txt_hoTen;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_thanhTien;
    private javax.swing.JTextField txt_tongTien;
    // End of variables declaration//GEN-END:variables
}
