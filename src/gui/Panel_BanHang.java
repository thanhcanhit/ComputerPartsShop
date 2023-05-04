/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import bus.DiaChi_bus;
import bus.HoaDon_bus;
import bus.KhachHang_bus;
import bus.KhoHang_bus;
import bus.SanPham_bus;
import bus.ThuongHieu_bus;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
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
import entity.connguoi.KhachHang;
import entity.connguoi.NhanVien;
import entity.hoadon.ChiTietHoaDon;
import entity.hoadon.HoaDon;
import entity.sanpham.SanPham;
import entity.sanpham.ThuongHieu;
import entity.share.DiaChi;
import entity.share.Utility;

/**
 *
 * @author thanh
 */
public final class Panel_BanHang extends javax.swing.JPanel {

    private final Frame_InputDiaChi frame_diaChi = new Frame_InputDiaChi(this);
    private DefaultTableModel tblModel_product;
    private DefaultTableModel tblModel_carts;
    private ArrayList<ChiTietHoaDon> gioHang = new ArrayList<>();

    // variable
    private int page = 1;
    private double subTotal = 0;
    private KhachHang khach = null;
    private NhanVien nhanVien = null;

    private SanPham_bus sanPham_bus;
    private ThuongHieu_bus thuongHieu_bus;
    private KhoHang_bus khoHang_bus;
    private KhachHang_bus khachHang_bus;
    private DiaChi_bus diaChi_bus;
    private HoaDon_bus hoaDon_bus;

    /**
     * Creates new form Panel_BanHang
     *
     * @param nhanVien: Nhân viên đang sử dụng phầm mềm
     */
    public Panel_BanHang(NhanVien nhanVien) {
        this.nhanVien = nhanVien;

        initDataObject();
        initTableModel();
        initComponents();
        renderPage();
        alterTable();
    }

    public void renderPage() {
        if (lbl_soTrang != null) {
            lbl_soTrang.setText(page + "/" + sanPham_bus.getSoTrangMax());
        }
        renderProductTable(sanPham_bus.getSanPhamTrang(page));
    }

    public void alterTable() {
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);

//        Products Width
        int i = 0;
        tbl_products.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tbl_products.getColumnModel().getColumn(i++).setPreferredWidth(80);
        tbl_products.getColumnModel().getColumn(i++).setPreferredWidth(250);
        tbl_products.getColumnModel().getColumn(i++).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(i++).setPreferredWidth(120);
        tbl_products.getColumnModel().getColumn(i++).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(i++).setPreferredWidth(100);
        tbl_products.getColumnModel().getColumn(i++).setPreferredWidth(150);

//        Align
        tbl_products.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        tbl_products.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        tbl_products.getColumnModel().getColumn(6).setCellRenderer(rightAlign);

        tbl_products.setDefaultEditor(Object.class, null);

//        Cart Width
//        i = 0;
//        tbl_cart.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        tbl_cart.getColumnModel().getColumn(i++).setPreferredWidth(120);
//        tbl_cart.getColumnModel().getColumn(i++).setPreferredWidth(350);
//        tbl_cart.getColumnModel().getColumn(i++).setPreferredWidth(120);
//        tbl_cart.getColumnModel().getColumn(i++).setPreferredWidth(200);
//        tbl_cart.getColumnModel().getColumn(i++).setPreferredWidth(200);
//        Align
        tbl_cart.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
        tbl_cart.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_cart.getColumnModel().getColumn(4).setCellRenderer(rightAlign);

        tbl_cart.setDefaultEditor(Object.class, null);
    }

    public void initDataObject() {
        sanPham_bus = new SanPham_bus();
        thuongHieu_bus = new ThuongHieu_bus();
        khoHang_bus = new KhoHang_bus();
        khachHang_bus = new KhachHang_bus();
        diaChi_bus = new DiaChi_bus();
        hoaDon_bus = new HoaDon_bus();
    }

    public void initTableModel() {
        // Products
        tblModel_product = new DefaultTableModel(new String[]{"Mã", "Tên", "Loại", "Thương hiệu", "Số lượng hiện có", "Giảm giá", "Giá bán"
        }, 0);

        // Carts
        tblModel_carts = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Tổng"
        }, 0);
    }

    public void search() {
        String input = txt_search.getText().trim();

        if (input.length() > 0) {
            ArrayList<SanPham> list = sanPham_bus.getSanPhamTheoMa(input);
            renderProductTable(list);
        } else {
            page = 1;
            renderPage();
        }
    }

    public void renderProductTable(ArrayList<SanPham> list) {
        tblModel_product.setRowCount(0);
        for (SanPham sp : list) {
            ArrayList<ThuongHieu> th = thuongHieu_bus.getThuongHieuTheoMa(sp.getThuongHieu().getMaTH());
            int soLuong = khoHang_bus.getSoLuongTon("KHO01", sp.getMaSP());

            if (soLuong == 0) {
                continue;
            }

            Object[] row = new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), th.get(0).toString(), soLuong, Math.round(sp.getGiamGia()) + "%", Utility.getVND(sp.getGiaBan())};
            tblModel_product.addRow(row);
        }
    }

    public void renderCartTable() {
        ArrayList<ChiTietHoaDon> list = gioHang;
        tblModel_carts.setRowCount(0);
        subTotal = 0;
        for (ChiTietHoaDon sp : list) {
            tblModel_carts.addRow(new Object[]{sp.getSanPham().getMaSP(), sp.getSanPham().getTenSP(), sp.getSoLuong(), Utility.getVND(sp.getGiaBan()), Utility.getVND(sp.getSoLuong() * sp.getGiaBan())});
            subTotal += sp.getGiaBan() * sp.getSoLuong();
        }
        txt_thanhTien.setText(Utility.getVND(subTotal));
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
        lbl_soTrang = new javax.swing.JLabel();
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
        lbl_sdt = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField("", 10);
        pnl_box2 = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_hoTen = new javax.swing.JLabel();
        txt_hoTen = new javax.swing.JTextField();
        cmb_gender = new javax.swing.JComboBox<>();
        pnl_box5 = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_diaChi = new javax.swing.JLabel();
        txt_diaChi = new javax.swing.JTextField();
        pnl_box3 = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_thanhToan = new javax.swing.JLabel();
        cmb_phuongThucThanhToan = new javax.swing.JComboBox<>();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_ten = new javax.swing.JLabel();
        txt_hangTV = new javax.swing.JTextField();
        pnl_box4 = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_thanhTien = new javax.swing.JLabel();
        txt_thanhTien = new javax.swing.JTextField();
        pnl_box6 = new javax.swing.JPanel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(0, 0));
        lbl_tienKhachDua = new javax.swing.JLabel();
        txt_tienKhachDua1 = new javax.swing.JFormattedTextField();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_tienThoi = new javax.swing.JLabel();
        txt_tienThoi = new javax.swing.JTextField("", 10);
        pnl_cartControl = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_export = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnl_search.setBackground(new java.awt.Color(255, 255, 255));
        pnl_search.setPreferredSize(new java.awt.Dimension(0, 50));
        pnl_search.setLayout(new javax.swing.BoxLayout(pnl_search, javax.swing.BoxLayout.X_AXIS));

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_search.setForeground(new java.awt.Color(153, 153, 153));
        txt_search.setText("Mã sản phẩm");
        txt_search.setToolTipText("Vui lòng nhập mã sản phẩm");
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchFocusLost(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });
        pnl_search.add(txt_search);

        btn_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_search.setText("Tìm kiếm");
        btn_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_search.setMaximumSize(new java.awt.Dimension(75, 50));
        btn_search.setMinimumSize(new java.awt.Dimension(75, 50));
        btn_search.setPreferredSize(new java.awt.Dimension(120, 50));
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        pnl_search.add(btn_search);

        add(pnl_search, java.awt.BorderLayout.NORTH);

        pnl_products.setBackground(new java.awt.Color(255, 255, 255));
        pnl_products.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_products.setPreferredSize(new java.awt.Dimension(600, 700));
        pnl_products.setLayout(new java.awt.BorderLayout());

        pnl_productsTable.setBackground(new java.awt.Color(255, 255, 255));
        pnl_productsTable.setLayout(new java.awt.BorderLayout());

        tbl_products.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_products.setModel(tblModel_product);
        tbl_products.setRowHeight(30);
        tbl_products.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_products.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_products.setShowGrid(true);
        tbl_products.setShowVerticalLines(false);
        tbl_products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productsMouseClicked(evt);
            }
        });
        scr_products.setViewportView(tbl_products);

        pnl_productsTable.add(scr_products, java.awt.BorderLayout.CENTER);

        pnl_products.add(pnl_productsTable, java.awt.BorderLayout.CENTER);

        pnl_productsControl.setBackground(new java.awt.Color(255, 255, 255));
        pnl_productsControl.setPreferredSize(new java.awt.Dimension(0, 45));
        pnl_productsControl.setLayout(new javax.swing.BoxLayout(pnl_productsControl, javax.swing.BoxLayout.LINE_AXIS));

        pnl_left.setBackground(new java.awt.Color(255, 255, 255));
        pnl_left.setLayout(new java.awt.GridLayout(1, 0));

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartReset.png"))); // NOI18N
        btn_reset.setToolTipText("Tải lại");
        btn_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reset.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        pnl_left.add(btn_reset);

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

        pnl_productsControl.add(pnl_left);
        pnl_productsControl.add(filler7);

        pnl_addToCart.setLayout(new java.awt.GridLayout(1, 0));

        btn_add.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartAdd.png"))); // NOI18N
        btn_add.setText("Thêm giỏ hàng");
        btn_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_add.setIconTextGap(15);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        pnl_addToCart.add(btn_add);

        pnl_productsControl.add(pnl_addToCart);

        pnl_products.add(pnl_productsControl, java.awt.BorderLayout.SOUTH);

        add(pnl_products, java.awt.BorderLayout.CENTER);

        pnl_cart.setPreferredSize(new java.awt.Dimension(500, 0));
        pnl_cart.setLayout(new java.awt.BorderLayout());

        pnl_cartCenter.setPreferredSize(new java.awt.Dimension(400, 600));
        pnl_cartCenter.setLayout(new java.awt.BorderLayout());

        pnl_cartTable.setBackground(new java.awt.Color(255, 255, 255));
        pnl_cartTable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_cartTable.setPreferredSize(new java.awt.Dimension(400, 400));
        pnl_cartTable.setLayout(new java.awt.BorderLayout());

        tbl_cart.setModel(tblModel_carts);
        tbl_cart.setRowHeight(30);
        tbl_cart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbl_cart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_cart.setShowGrid(true);
        tbl_cart.setShowVerticalLines(false);
        tbl_cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cartMouseClicked(evt);
            }
        });
        scr_cart.setViewportView(tbl_cart);

        pnl_cartTable.add(scr_cart, java.awt.BorderLayout.CENTER);

        pnl_cartFooter.setLayout(new java.awt.GridLayout(1, 0));

        btn_remove.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartRemove.png"))); // NOI18N
        btn_remove.setText("Xóa khỏi giỏ hàng");
        btn_remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_remove.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_remove.setIconTextGap(10);
        btn_remove.setPreferredSize(new java.awt.Dimension(100, 45));
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });
        pnl_cartFooter.add(btn_remove);

        pnl_cartTable.add(pnl_cartFooter, java.awt.BorderLayout.PAGE_END);

        pnl_cartCenter.add(pnl_cartTable, java.awt.BorderLayout.CENTER);

        pnl_orderInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnl_orderInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_orderInfo.setPreferredSize(new java.awt.Dimension(400, 280));
        pnl_orderInfo.setLayout(new javax.swing.BoxLayout(pnl_orderInfo, javax.swing.BoxLayout.Y_AXIS));

        pnl_box1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box1.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box1.setLayout(new javax.swing.BoxLayout(pnl_box1, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box1.add(filler1);

        lbl_sdt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_sdt.setForeground(new java.awt.Color(102, 102, 102));
        lbl_sdt.setText("SĐT");
        lbl_sdt.setPreferredSize(new java.awt.Dimension(90, 70));
        pnl_box1.add(lbl_sdt);

        txt_sdt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_sdt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_sdtFocusLost(evt);
            }
        });
        txt_sdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_sdtKeyPressed(evt);
            }
        });
        pnl_box1.add(txt_sdt);

        pnl_orderInfo.add(pnl_box1);

        pnl_box2.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box2.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box2.setLayout(new javax.swing.BoxLayout(pnl_box2, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box2.add(filler2);

        lbl_hoTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_hoTen.setForeground(new java.awt.Color(102, 102, 102));
        lbl_hoTen.setText("Họ tên");
        lbl_hoTen.setPreferredSize(new java.awt.Dimension(90, 70));
        pnl_box2.add(lbl_hoTen);

        txt_hoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hoTen.setPreferredSize(new java.awt.Dimension(100, 0));
        pnl_box2.add(txt_hoTen);

        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nữ", "Nam" }));
        pnl_box2.add(cmb_gender);

        pnl_orderInfo.add(pnl_box2);

        pnl_box5.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box5.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box5.setLayout(new javax.swing.BoxLayout(pnl_box5, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box5.add(filler6);

        lbl_diaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_diaChi.setForeground(new java.awt.Color(102, 102, 102));
        lbl_diaChi.setText("Địa chỉ");
        lbl_diaChi.setPreferredSize(new java.awt.Dimension(90, 70));
        pnl_box5.add(lbl_diaChi);

        txt_diaChi.setEditable(false);
        txt_diaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_diaChi.setPreferredSize(new java.awt.Dimension(100, 0));
        txt_diaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_diaChiMouseClicked(evt);
            }
        });
        pnl_box5.add(txt_diaChi);

        pnl_orderInfo.add(pnl_box5);

        pnl_box3.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box3.setPreferredSize(new java.awt.Dimension(400, 40));
        pnl_box3.setLayout(new javax.swing.BoxLayout(pnl_box3, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box3.add(filler3);

        lbl_thanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_thanhToan.setForeground(new java.awt.Color(102, 102, 102));
        lbl_thanhToan.setText("Thanh toán");
        lbl_thanhToan.setPreferredSize(new java.awt.Dimension(90, 70));
        pnl_box3.add(lbl_thanhToan);

        cmb_phuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "ATM", "Ví điện tử" }));
        cmb_phuongThucThanhToan.setPreferredSize(new java.awt.Dimension(100, 22));
        cmb_phuongThucThanhToan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_phuongThucThanhToanItemStateChanged(evt);
            }
        });
        pnl_box3.add(cmb_phuongThucThanhToan);
        pnl_box3.add(filler5);

        lbl_ten.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ten.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ten.setText("Hạng TV");
        lbl_ten.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_box3.add(lbl_ten);

        txt_hangTV.setEditable(false);
        txt_hangTV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hangTV.setPreferredSize(new java.awt.Dimension(0, 0));
        pnl_box3.add(txt_hangTV);

        pnl_orderInfo.add(pnl_box3);

        pnl_box4.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box4.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box4.setLayout(new javax.swing.BoxLayout(pnl_box4, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box4.add(filler4);

        lbl_thanhTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_thanhTien.setForeground(new java.awt.Color(102, 102, 102));
        lbl_thanhTien.setText("Thành tiền");
        lbl_thanhTien.setPreferredSize(new java.awt.Dimension(90, 70));
        pnl_box4.add(lbl_thanhTien);

        txt_thanhTien.setEditable(false);
        txt_thanhTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_thanhTien.setForeground(new java.awt.Color(65, 165, 238));
        pnl_box4.add(txt_thanhTien);

        pnl_orderInfo.add(pnl_box4);

        pnl_box6.setBackground(new java.awt.Color(255, 255, 255));
        pnl_box6.setPreferredSize(new java.awt.Dimension(400, 30));
        pnl_box6.setLayout(new javax.swing.BoxLayout(pnl_box6, javax.swing.BoxLayout.LINE_AXIS));
        pnl_box6.add(filler8);

        lbl_tienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tienKhachDua.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tienKhachDua.setText("Tiền khách đưa");
        lbl_tienKhachDua.setPreferredSize(new java.awt.Dimension(90, 70));
        pnl_box6.add(lbl_tienKhachDua);

        txt_tienKhachDua1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_tienKhachDua1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tienKhachDua1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tienKhachDua1KeyPressed(evt);
            }
        });
        pnl_box6.add(txt_tienKhachDua1);
        pnl_box6.add(filler9);

        lbl_tienThoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tienThoi.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tienThoi.setText("Tiền trả lại");
        lbl_tienThoi.setPreferredSize(new java.awt.Dimension(80, 70));
        pnl_box6.add(lbl_tienThoi);

        txt_tienThoi.setEditable(false);
        txt_tienThoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnl_box6.add(txt_tienThoi);

        pnl_orderInfo.add(pnl_box6);

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
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        pnl_cartControl.add(btn_cancel);

        btn_export.setBackground(new java.awt.Color(65, 165, 238));
        btn_export.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_export.setForeground(new java.awt.Color(242, 242, 242));
        btn_export.setText("XUẤT");
        btn_export.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });
        pnl_cartControl.add(btn_export);

        pnl_cart.add(pnl_cartControl, java.awt.BorderLayout.SOUTH);

        add(pnl_cart, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        search();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        txt_search.setText("");
        page = 1;
        renderPage();
        txt_search.setText("Mã sản phẩm");
        txt_search.setForeground(new Color(153, 153, 153));
    }//GEN-LAST:event_btn_resetActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == 10)
            search();
    }//GEN-LAST:event_txt_searchKeyPressed

    public void updateDiaChi(DiaChi dc) {
        txt_diaChi.setText(dc.toString());
    }

    public void themHang() {
        String s_quantity = JOptionPane.showInputDialog(this, "Số lượng", 1);

        try {
            int row = tbl_products.getSelectedRow();

            if (row != -1) {
                int quantity = Integer.parseInt(s_quantity);
                if (quantity <= 0) {
                    return;
                }

                String maSanPham = tbl_products.getValueAt(row, 0).toString();
                SanPham sp = sanPham_bus.getSanPhamTheoMa(maSanPham).get(0);

                int soLuongTon = khoHang_bus.getSoLuongTon("KHO01", sp.getMaSP());

                if (quantity > soLuongTon) {
                    JOptionPane.showMessageDialog(this, "Số lượng trong kho không đủ!", "Kho không đủ hàng", JOptionPane.PLAIN_MESSAGE);
                    return;
                }

                ChiTietHoaDon ct = new ChiTietHoaDon(sp, null, quantity, sp.getGiaBan());

                if (gioHang.contains(ct)) {
                    int previousSL = gioHang.get(gioHang.indexOf(ct)).getSoLuong();

                    if (previousSL + quantity > soLuongTon) {
                        JOptionPane.showMessageDialog(this, "Số lượng trong kho không đủ!", "Kho không đủ hàng", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    gioHang.get(gioHang.indexOf(ct)).setSoLuong(previousSL + quantity);
                } else {

                    gioHang.add(ct);
                }
                renderCartTable();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Lỗi đầu vào", JOptionPane.PLAIN_MESSAGE);
        }
    }
    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        themHang();
    }//GEN-LAST:event_btn_addActionPerformed

    public void xoaHang() {
        int row = tbl_cart.getSelectedRow();

        if (row != -1) {
            String maSP = tbl_cart.getValueAt(row, 0).toString();
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa " + maSP + " ra khỏi giỏ?", "Xóa hàng ra khỏi giỏ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                gioHang.remove(row);
                renderCartTable();
            }
        }
    }
    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        xoaHang();
    }//GEN-LAST:event_btn_removeActionPerformed

    private void txt_sdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sdtKeyPressed
        if (evt.getKeyCode() == 10) {
            getKhachHangNeuTonTai();
        }
    }//GEN-LAST:event_txt_sdtKeyPressed

    private void txt_sdtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_sdtFocusLost
        if (txt_sdt.getText().length() > 0)
            getKhachHangNeuTonTai();
    }//GEN-LAST:event_txt_sdtFocusLost

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Hủy bỏ hóa đơn hiện tại?", "Xác nhận hủy hóa đơn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            resetAll();
        }
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        if (page < sanPham_bus.getSoTrangMax()) {
            page++;
            renderPage();
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        if (page > 1) {
            page--;
            renderPage();
        }
    }//GEN-LAST:event_btn_prevActionPerformed

    private void txt_diaChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_diaChiMouseClicked
        frame_diaChi.setVisible(true);
        frame_diaChi.getDiaChi();
    }//GEN-LAST:event_txt_diaChiMouseClicked

    public void showMessageFocus(String msg, JTextField txt) {
        JOptionPane.showMessageDialog(this, msg);
        txt.selectAll();
        txt.requestFocus();
    }
    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        if (gioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa thêm hàng vào giỏ");
            return;
        }

        String sdt = txt_sdt.getText().trim();
        String hoTen = txt_hoTen.getText().trim();
        boolean gioiTinh = cmb_gender.getSelectedIndex() == 1;
        int diemThem = Long.valueOf(Math.round(subTotal / 10000)).intValue();

//                Validate
        if (!Pattern.matches("0\\d{9}", sdt)) {
            showMessageFocus("Số điện thoại không hợp lệ", txt_sdt);
            return;
        }

        if (!Pattern.matches("^\\p{L}+\\s+\\p{L}+.*$", hoTen)) {
            showMessageFocus("Họ tên phải ít nhất 2 từ", txt_hoTen);
            return;
        }

        if (khach == null) {
            try {
                DiaChi dc = frame_diaChi.getDiaChi();
                if (dc.getThanhPho().trim().length() == 0) {
                    showMessageFocus("Bạn chưa điền địa chỉ", txt_diaChi);
                    return;
                }

//                  Kiem tra xem diaChi ton tai trong database chua
                String maDiaChiInDatabase = diaChi_bus.getMaDiaChi(dc);

                // Khong co thi them co thi dung lai
                if (maDiaChiInDatabase == null) {
                    diaChi_bus.themDiaChi(dc);
                } else {
                    dc.setMaDiaChi(maDiaChiInDatabase);
                }

                khach = new KhachHang(khachHang_bus.sinhMa(), null, hoTen, sdt, "Không", LocalDate.of(2003, 1, 1), dc, gioiTinh, diemThem);
                khachHang_bus.themKhachHang(khach);
            } catch (Exception ex) {
                Logger.getLogger(Panel_BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            khachHang_bus.congDiemKhachHang(khach.getMaKH(), diemThem);
        }

//        Phat sinh hoa don
        String maHoaDon = hoaDon_bus.sinhMa();
        HoaDon hoaDon = new HoaDon(maHoaDon, LocalDate.now(), cmb_phuongThucThanhToan.getSelectedItem().toString(), nhanVien, khach, gioHang, subTotal);

        if (hoaDon_bus.themHoaDon(hoaDon)) {
            // Reset
            resetAll();
            new Frame_HoaDon(hoaDon).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại");
        }

    }//GEN-LAST:event_btn_exportActionPerformed

    private void tbl_productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productsMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            themHang();
        }
    }//GEN-LAST:event_tbl_productsMouseClicked

    private void tbl_cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cartMouseClicked

        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            int row = tbl_cart.getSelectedRow();
            if (row != -1) {
                ChiTietHoaDon ct = gioHang.get(row);

                try {
                    int newSoLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Thay đổi số lượng", ct.getSoLuong()));
                    ct.setSoLuong(newSoLuong);

                    if (newSoLuong <= 0) {
                        JOptionPane.showConfirmDialog(this, "Số lượng phải > 0");
                        return;
                    }

                    String maSanPham = tbl_products.getValueAt(row, 0).toString();
                    SanPham sp = sanPham_bus.getSanPhamTheoMa(maSanPham).get(0);

                    int soLuongTon = khoHang_bus.getSoLuongTon("KHO01", sp.getMaSP());

                    if (newSoLuong > soLuongTon) {
                        JOptionPane.showMessageDialog(this, "Số lượng trong kho không đủ!", "Kho không đủ hàng", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }

                    renderCartTable();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                }
            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            xoaHang();
        }

    }//GEN-LAST:event_tbl_cartMouseClicked

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
        if (txt_search.getText().equals("Mã sản phẩm")) {
            txt_search.setText("");
            txt_search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusLost
        if (txt_search.getText().trim().equals("")) {
            txt_search.setText("Mã sản phẩm");
            txt_search.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_searchFocusLost

    private void cmb_phuongThucThanhToanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_phuongThucThanhToanItemStateChanged
        if (cmb_phuongThucThanhToan.getSelectedItem().toString().equalsIgnoreCase("Tiền mặt")) {
            txt_tienKhachDua1.setEnabled(true);
            txt_tienKhachDua1.setText("");
            txt_tienThoi.setText("");
        } else {
            txt_tienKhachDua1.setEnabled(false);
            txt_tienKhachDua1.setText(NumberFormat.getInstance().format(subTotal));
            txt_tienThoi.setText("0");
        }
    }//GEN-LAST:event_cmb_phuongThucThanhToanItemStateChanged

    private void txt_tienKhachDua1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tienKhachDua1KeyPressed
        if (evt.getKeyCode() == 10)
            handleTienKhachDua();
    }//GEN-LAST:event_txt_tienKhachDua1KeyPressed

    public void handleTienKhachDua() {
        String s_khachDua = txt_tienKhachDua1.getText().trim();
        if (!txt_thanhTien.getText().equals("")) {

            try {
                double khachDua = NumberFormat.getInstance().parse(s_khachDua).doubleValue();
                double tienThoi = khachDua - subTotal;
                if (tienThoi < 0) {
                    showMessageFocus("Tiền khách ít hơn thành tiền", txt_tienKhachDua1);
                    return;
                }
                txt_tienThoi.setText(Utility.getVND(tienThoi));
            } catch (Exception e) {
                showMessageFocus("Tiền khách đưa không hợp lệ", txt_tienKhachDua1);
            }
        }
    }

    public void resetAll() {
        gioHang = new ArrayList<>();
        renderCartTable();
        txt_thanhTien.setText("");
        txt_sdt.setText("");
        txt_hangTV.setText("");
        txt_hoTen.setText("");
        txt_diaChi.setText("");
        txt_tienKhachDua1.setText("");
        txt_tienThoi.setText("");
        cmb_phuongThucThanhToan.setSelectedIndex(0);
        cmb_gender.setSelectedIndex(0);
        khach = null;
        subTotal = 0;
        renderPage();
    }

    private void renderKhachHang() {
        txt_hoTen.setEditable(false);
        txt_hoTen.setText(khach.getHoTen());
        txt_hangTV.setText(khach.getHang());
        cmb_gender.setSelectedIndex(khach.isGioiTinh() ? 1 : 0);
        try {
            String diaChi = diaChi_bus.getDiaChiTheoMa(khach.getDiaChi().getMaDiaChi()).toString();
            txt_diaChi.setText(diaChi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getKhachHangNeuTonTai() {
        String sdtInput = txt_sdt.getText();
        if (Pattern.matches("\\d{10}", sdtInput)) {
            KhachHang kh = khachHang_bus.getKhachHangTheoSDT(sdtInput);
            if (kh != null) {
                this.khach = kh;
                cmb_gender.setEnabled(false);
                renderKhachHang();
            } else {
                this.khach = null;
                txt_hoTen.setEditable(true);
                txt_hoTen.setText("");
                txt_diaChi.setText("");
                txt_hangTV.setText("");
                cmb_gender.setEnabled(true);
                txt_hoTen.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.DEFAULT_OPTION);
            txt_sdt.selectAll();
            txt_sdt.requestFocus();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> cmb_gender;
    private javax.swing.JComboBox<String> cmb_phuongThucThanhToan;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel lbl_diaChi;
    private javax.swing.JLabel lbl_hoTen;
    private javax.swing.JLabel lbl_sdt;
    private javax.swing.JLabel lbl_soTrang;
    private javax.swing.JLabel lbl_ten;
    private javax.swing.JLabel lbl_thanhTien;
    private javax.swing.JLabel lbl_thanhToan;
    private javax.swing.JLabel lbl_tienKhachDua;
    private javax.swing.JLabel lbl_tienThoi;
    private javax.swing.JPanel pnl_addToCart;
    private javax.swing.JPanel pnl_box1;
    private javax.swing.JPanel pnl_box2;
    private javax.swing.JPanel pnl_box3;
    private javax.swing.JPanel pnl_box4;
    private javax.swing.JPanel pnl_box5;
    private javax.swing.JPanel pnl_box6;
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
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_hangTV;
    private javax.swing.JTextField txt_hoTen;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_thanhTien;
    private javax.swing.JFormattedTextField txt_tienKhachDua1;
    private javax.swing.JTextField txt_tienThoi;
    // End of variables declaration//GEN-END:variables
}
