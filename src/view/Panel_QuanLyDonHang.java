/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.hoadon.ChiTietHoaDon;
import model.hoadon.HoaDon;
import model.kho.ChiTietDonNhap;
import model.kho.DonNhapHang;
import model.sanpham.SanPham;
import model.sanpham.ThuongHieu;


/**
 *
 * @author HP
 */
public class Panel_QuanLyDonHang extends javax.swing.JPanel {
    private DefaultTableModel tblModel_danhSachHangHoa;
    private DefaultTableModel tblModel_chiTietDonNhap1;
    private DefaultTableModel tblModel_danhSachDonDaDat;
    private DefaultTableModel tblModel_chiTietDonDaDat;
    private DefaultTableModel tblModel_danhSachHoaDon;
    private DefaultTableModel tblModel_chiTietHoaDon;
        
    /**
     * Creates new form Panel_QuanLyDonHang
     */
    public Panel_QuanLyDonHang() {
        initComponents();
        initTableModel();
    }

    
//    public class Panel_BanHang extends javax.swing.JPanel {
//
//    private DefaultTableModel tblModel_product;
//    private DefaultTableModel tblModel_carts;
//
//    /**
//     * Creates new form Panel_BanHang
//     */
//    public Panel_BanHang() {
//        initTableModel();
//        initComponents();
//    }
//
    public void initTableModel() {
        // Products
//        tblModel_product = new DefaultTableModel(new String[]{"Mã", "Tên", "Loại", "Thương hiệu", "Số lượng", "Giá bán"
//        }, 0);
//
//        // Carts
//        tblModel_carts = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Số lượng", "Đơn giá"
//        }, 0);

        ArrayList<SanPham> list = new ArrayList<>();
        try {
            list.add(new SanPham("sp1", "chuot1", 200, 10, SanPham.CHUOT, 8, new ThuongHieu("th1", "Itel", "Trung"), 5, "DPI:3000"));
            list.add(new SanPham("sp2", "chuot2", 200, 10, SanPham.CHUOT, 8, new ThuongHieu("th1", "Itel", "Trung"), 5, "DPI:3000"));
            list.add(new SanPham("sp3", "chuot3", 200, 10, SanPham.CHUOT, 8, new ThuongHieu("th1", "Itel", "Trung"), 5, "DPI:3000"));
            renderDanhSachHangHoa(list);
//            renderChiTietDonNhap(list);
        } catch (Exception e) {

        }
    }
//
    public void renderDanhSachHangHoa(ArrayList<SanPham> list) {
        tblModel_danhSachHangHoa.setRowCount(0);
        for (SanPham sp : list) {
            Object[] row = new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), sp.getThuongHieu().toString(), 0, sp.getGiaBan()};
            tblModel_danhSachHangHoa.addRow(row);
        }
    }
//
    public void renderChiTietDonNhap(ArrayList<ChiTietDonNhap> list) {
        tblModel_chiTietDonNhap1.setRowCount(0);
        for (ChiTietDonNhap sp : list) {
            tblModel_chiTietDonNhap1.addRow(new Object[]{sp.getSanPham().getMaSP(), sp.getSanPham().getTenSP(), sp.getSanPham().getLoai(), sp.getSanPham().getGiaNhap(),});
        }
    }
    
    public void renderDanhSachDonDaDat(ArrayList<DonNhapHang> list){
        tblModel_danhSachDonDaDat.setRowCount(0);
        for (DonNhapHang donNhapHang : list) {
            tblModel_chiTietDonDaDat.addRow(new Object[]{donNhapHang.getMaDonNhap(), "donNhapHang.getNhanVien().getMaNhanVien", "donNhapHang.getNhaCungCap().getMaNhaCungCap", donNhapHang.getNgayNhap(), donNhapHang.isDanhan()});
        }
    }
    
    public void renderChiTietDonDaDat(ArrayList<ChiTietDonNhap> list){
        tblModel_chiTietDonDaDat.setRowCount(0);
        for (ChiTietDonNhap chiTietDonDaNhap : list) {
            tblModel_chiTietDonDaDat.addRow(new Object[]{chiTietDonDaNhap.getSanPham().getMaSP(), chiTietDonDaNhap.getSanPham().getTenSP(), chiTietDonDaNhap.getSanPham().getLoai(), chiTietDonDaNhap.getSoLuong(), chiTietDonDaNhap.getSanPham().getGiaBan(), chiTietDonDaNhap.getSoLuong()*chiTietDonDaNhap.getSanPham().getGiaBan()});
        }
    }
    
    public void renderDanhSachHoaDon(ArrayList<HoaDon> list){
        tblModel_danhSachHoaDon.setRowCount(0);
        for (HoaDon hoaDon : list) {
            tblModel_danhSachHoaDon.addRow(new Object[] {hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTen(), hoaDon.getKhachHang().getHoTen(), hoaDon.getPhuongThucThanhToan(), hoaDon.getNgayLap()});
        }
    }
    
    public void renderChiTietHoaDon(ArrayList<ChiTietHoaDon> list){
        tblModel_chiTietHoaDon.setRowCount(0);
        for (ChiTietHoaDon chiTietHoaDon : list) {
            tblModel_chiTietHoaDon.addRow(new Object[]{chiTietHoaDon.getSanPham().getMaSP(), chiTietHoaDon.getSanPham().getTenSP(), chiTietHoaDon.getSanPham().getLoai(), chiTietHoaDon.getSoLuong(), chiTietHoaDon.getSanPham().getGiaBan(),chiTietHoaDon.getSoLuong()*chiTietHoaDon.getSanPham().getGiaBan()});
        }
    }
    
//    public void renderDanhSachDonDaDat(ArrayList<SanPham> list) {
//        tblModel_chiTietDonNhap1.setRowCount(0);
//        for (SanPham sp : list) {
//            tblModel_chiTietDonNhap1.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), 0, sp.getGiaBan()});
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        tab_donHang = new javax.swing.JTabbedPane();
        pnl_themDonDatHang = new javax.swing.JPanel();
        pnl_themDonHangCenter = new javax.swing.JPanel();
        pnl_danhSachHangHoa1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_danhSachHangHoa = new javax.swing.JTable();
        pnl_chiTietDonNhap1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_chiTietDonNhap1 = new javax.swing.JTable();
        pnl_thongTinDonNhapLine2 = new javax.swing.JPanel();
        btn_themHang = new javax.swing.JButton();
        btn_xoaHang = new javax.swing.JButton();
        pnl_thongTinDon1 = new javax.swing.JPanel();
        pnl_ghiChu = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(32767, 4));
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbl_nhaCungCap = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        cbo_nhaCungCap = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lbl_ghiChuDonNhap = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txa_ghiChuDonNhap = new javax.swing.JTextArea();
        pnl_tongTienPhieuNhap = new javax.swing.JPanel();
        lbl_tongPhieuNhap = new javax.swing.JLabel();
        txt_tongTienPhieuNhap = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_nhapHang = new javax.swing.JButton();
        pnl_timKiemTheoMaSanPham = new javax.swing.JPanel();
        pnl_search = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        pnl_donDatHang = new javax.swing.JPanel();
        pnl_danhSachDon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachDonDaDat = new javax.swing.JTable();
        pnl_chiTietDonNhap = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_chiTietDonNhap = new javax.swing.JTable();
        pnl_thongTinDon = new javax.swing.JPanel();
        pnl_ngayNhaptinhTrang = new javax.swing.JPanel();
        b_ngayNhap = new javax.swing.JPanel();
        lbl_ngayNhap = new javax.swing.JLabel();
        txt_ngayNhap = new javax.swing.JTextField();
        b_tinhTrang = new javax.swing.JPanel();
        lbl_tinhTrang = new javax.swing.JLabel();
        txt_tinhTrang = new javax.swing.JTextField();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 8));
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lbl_nhaCungCap1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(32767, 8));
        jPanel5 = new javax.swing.JPanel();
        lbl_ghiChuDonNhap1 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        txa_ghiChuDonNhap1 = new javax.swing.JTextArea();
        b_tongTien = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbl_tongTien = new javax.swing.JLabel();
        txt_tongTien = new javax.swing.JTextField();
        pnl_hoaDon = new javax.swing.JPanel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        pnl_timKiemHoaDon = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        pnl_timKiemTheoMa = new javax.swing.JPanel();
        lbl_timKiemTheoMaKhachHang = new javax.swing.JLabel();
        txt_timKiemTheoMaKhachHang = new javax.swing.JTextField();
        pnl_timKiemTheoNgay = new javax.swing.JPanel();
        lbl_timTuNgay = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        lbl_timDenNgay = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        jPanel9 = new javax.swing.JPanel();
        pnl_timTheoNhaVienThanhToan = new javax.swing.JPanel();
        lbl_maNhanVien = new javax.swing.JLabel();
        txt_maNhanVien = new javax.swing.JTextField();
        pnl_timTheoGiaTien = new javax.swing.JPanel();
        lbl_giaTu = new javax.swing.JLabel();
        txt_giaTu = new javax.swing.JTextField();
        lbl_giaDen = new javax.swing.JLabel();
        txt_giaDen = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        btn_search2 = new javax.swing.JButton();
        btn_search1 = new javax.swing.JButton();
        pnl_center = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_danhSachHangHoa2 = new javax.swing.JTable();
        pnl_thongTinHoaDon = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        table_danhSachHoaDon = new javax.swing.JTable();
        pnl_thongTinChiTietDon = new javax.swing.JPanel();
        b_tongTien1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        b_ngayNhap1 = new javax.swing.JPanel();
        lbl_ngayNhap2 = new javax.swing.JLabel();
        txt_ngayNhap2 = new javax.swing.JTextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        b_tinhTrang1 = new javax.swing.JPanel();
        lbl_tinhTrang2 = new javax.swing.JLabel();
        txt_tinhTrang2 = new javax.swing.JTextField();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        b_tongTien2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbl_tongTien1 = new javax.swing.JLabel();
        txt_tongTien1 = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        pnl_themDonDatHang.setLayout(new java.awt.BorderLayout());

        pnl_themDonHangCenter.setLayout(new javax.swing.BoxLayout(pnl_themDonHangCenter, javax.swing.BoxLayout.X_AXIS));

        pnl_danhSachHangHoa1.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_danhSachHangHoa.setModel(tblModel_danhSachHangHoa= new DefaultTableModel(new String[]{"Mã", "Tên", "Loại", "Thương hiệu", "Số lượng", "Giá bán"
        }, 0));
        jScrollPane3.setViewportView(tbl_danhSachHangHoa);

        pnl_danhSachHangHoa1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        pnl_themDonHangCenter.add(pnl_danhSachHangHoa1);

        pnl_chiTietDonNhap1.setLayout(new javax.swing.BoxLayout(pnl_chiTietDonNhap1, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiếp đơn nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        table_chiTietDonNhap1.setModel(tblModel_chiTietDonNhap1 = new DefaultTableModel(new String[]{"Mã", "Tên", "Loại", "Số lượng", "Đơn giá", "Tổng"
        }, 0));
        jScrollPane4.setViewportView(table_chiTietDonNhap1);
        if (table_chiTietDonNhap1.getColumnModel().getColumnCount() > 0) {
            table_chiTietDonNhap1.getColumnModel().getColumn(0).setResizable(false);
        }

        pnl_chiTietDonNhap1.add(jScrollPane4);

        pnl_thongTinDonNhapLine2.setPreferredSize(new java.awt.Dimension(300, 40));
        pnl_thongTinDonNhapLine2.setLayout(new java.awt.GridLayout(1, 0));

        btn_themHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_themHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/donhang/addtobasket.png"))); // NOI18N
        btn_themHang.setText("Thêm hàng");
        btn_themHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_themHang.setIconTextGap(10);
        btn_themHang.setPreferredSize(new java.awt.Dimension(150, 50));
        pnl_thongTinDonNhapLine2.add(btn_themHang);

        btn_xoaHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoaHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/donhang/removefromcart.png"))); // NOI18N
        btn_xoaHang.setText("Xóa hàng");
        btn_xoaHang.setIconTextGap(10);
        btn_xoaHang.setPreferredSize(new java.awt.Dimension(150, 50));
        btn_xoaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaHangActionPerformed(evt);
            }
        });
        pnl_thongTinDonNhapLine2.add(btn_xoaHang);

        pnl_chiTietDonNhap1.add(pnl_thongTinDonNhapLine2);

        pnl_thongTinDon1.setLayout(new javax.swing.BoxLayout(pnl_thongTinDon1, javax.swing.BoxLayout.Y_AXIS));

        pnl_ghiChu.setLayout(new javax.swing.BoxLayout(pnl_ghiChu, javax.swing.BoxLayout.Y_AXIS));
        pnl_ghiChu.add(filler2);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        lbl_nhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nhaCungCap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_nhaCungCap.setText("Nhà cung cấp:");
        jPanel8.add(lbl_nhaCungCap);
        jPanel8.add(filler3);

        cbo_nhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_nhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_nhaCungCapActionPerformed(evt);
            }
        });
        jPanel8.add(cbo_nhaCungCap);

        jPanel4.add(jPanel8);

        pnl_ghiChu.add(jPanel4);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        lbl_ghiChuDonNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ghiChuDonNhap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ghiChuDonNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_ghiChuDonNhap.setText("Ghi chú:");
        lbl_ghiChuDonNhap.setToolTipText("");
        lbl_ghiChuDonNhap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl_ghiChuDonNhap.setName(""); // NOI18N
        lbl_ghiChuDonNhap.setPreferredSize(new java.awt.Dimension(80, 16));
        jPanel1.add(lbl_ghiChuDonNhap);

        pnl_ghiChu.add(jPanel1);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        txa_ghiChuDonNhap.setColumns(20);
        txa_ghiChuDonNhap.setRows(5);
        jScrollPane5.setViewportView(txa_ghiChuDonNhap);

        jPanel15.add(jScrollPane5);

        pnl_ghiChu.add(jPanel15);

        pnl_thongTinDon1.add(pnl_ghiChu);

        pnl_tongTienPhieuNhap.setPreferredSize(new java.awt.Dimension(134, 30));
        pnl_tongTienPhieuNhap.setLayout(new java.awt.GridLayout(1, 0));

        lbl_tongPhieuNhap.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_tongPhieuNhap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tongPhieuNhap.setText("Tổng:");
        lbl_tongPhieuNhap.setName(""); // NOI18N
        lbl_tongPhieuNhap.setPreferredSize(new java.awt.Dimension(70, 20));
        pnl_tongTienPhieuNhap.add(lbl_tongPhieuNhap);

        txt_tongTienPhieuNhap.setEditable(false);
        txt_tongTienPhieuNhap.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_tongTienPhieuNhap.setBorder(null);
        txt_tongTienPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tongTienPhieuNhapActionPerformed(evt);
            }
        });
        pnl_tongTienPhieuNhap.add(txt_tongTienPhieuNhap);

        pnl_thongTinDon1.add(pnl_tongTienPhieuNhap);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btn_nhapHang.setBackground(new java.awt.Color(65, 165, 238));
        btn_nhapHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_nhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btn_nhapHang.setText("Nhập hàng");
        btn_nhapHang.setMaximumSize(new java.awt.Dimension(155, 30));
        btn_nhapHang.setPreferredSize(new java.awt.Dimension(100, 60));
        btn_nhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nhapHangActionPerformed(evt);
            }
        });
        jPanel3.add(btn_nhapHang);

        pnl_thongTinDon1.add(jPanel3);

        pnl_chiTietDonNhap1.add(pnl_thongTinDon1);

        pnl_themDonHangCenter.add(pnl_chiTietDonNhap1);

        pnl_themDonDatHang.add(pnl_themDonHangCenter, java.awt.BorderLayout.CENTER);

        pnl_timKiemTheoMaSanPham.setPreferredSize(new java.awt.Dimension(70, 40));
        pnl_timKiemTheoMaSanPham.setLayout(new javax.swing.BoxLayout(pnl_timKiemTheoMaSanPham, javax.swing.BoxLayout.X_AXIS));

        pnl_search.setBackground(new java.awt.Color(255, 255, 255));
        pnl_search.setPreferredSize(new java.awt.Dimension(0, 50));
        pnl_search.setLayout(new javax.swing.BoxLayout(pnl_search, javax.swing.BoxLayout.X_AXIS));

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_search.setToolTipText("Vui lòng nhập mã sản phẩm");
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
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
        pnl_search.add(btn_search);

        pnl_timKiemTheoMaSanPham.add(pnl_search);

        pnl_themDonDatHang.add(pnl_timKiemTheoMaSanPham, java.awt.BorderLayout.NORTH);

        tab_donHang.addTab("Nhập hàng", pnl_themDonDatHang);

        pnl_donDatHang.setLayout(new java.awt.GridLayout(1, 2));

        pnl_danhSachDon.setLayout(new javax.swing.BoxLayout(pnl_danhSachDon, javax.swing.BoxLayout.X_AXIS));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đơn đã đặt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_danhSachDonDaDat.setModel(tblModel_danhSachDonDaDat = new DefaultTableModel(new String[]{"Mã", "Nhân viên", "Nhà cung cấp", "Ngày đặt", "Tình trạng", "Kho"
        }, 0));
        jScrollPane1.setViewportView(tbl_danhSachDonDaDat);

        pnl_danhSachDon.add(jScrollPane1);

        pnl_donDatHang.add(pnl_danhSachDon);

        pnl_chiTietDonNhap.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết đơn nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_chiTietDonNhap.setModel(tblModel_chiTietDonDaDat = new DefaultTableModel(new String[]{"Mã", "Tên hàng", "Nhóm hàng", "Số lượng", "Đơn giá", "Tổng"
        }, 0));
        jScrollPane2.setViewportView(tbl_chiTietDonNhap);
        if (tbl_chiTietDonNhap.getColumnModel().getColumnCount() > 0) {
            tbl_chiTietDonNhap.getColumnModel().getColumn(0).setResizable(false);
        }

        pnl_chiTietDonNhap.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnl_thongTinDon.setLayout(new javax.swing.BoxLayout(pnl_thongTinDon, javax.swing.BoxLayout.Y_AXIS));

        pnl_ngayNhaptinhTrang.setLayout(new javax.swing.BoxLayout(pnl_ngayNhaptinhTrang, javax.swing.BoxLayout.X_AXIS));

        b_ngayNhap.setLayout(new javax.swing.BoxLayout(b_ngayNhap, javax.swing.BoxLayout.LINE_AXIS));

        lbl_ngayNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ngayNhap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ngayNhap.setText("Ngày nhập:");
        lbl_ngayNhap.setMinimumSize(new java.awt.Dimension(87, 16));
        lbl_ngayNhap.setPreferredSize(new java.awt.Dimension(87, 16));
        b_ngayNhap.add(lbl_ngayNhap);

        txt_ngayNhap.setEditable(false);
        txt_ngayNhap.setColumns(30);
        b_ngayNhap.add(txt_ngayNhap);

        pnl_ngayNhaptinhTrang.add(b_ngayNhap);

        b_tinhTrang.setLayout(new javax.swing.BoxLayout(b_tinhTrang, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tinhTrang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tinhTrang.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tinhTrang.setText("Tình trạng:");
        lbl_tinhTrang.setPreferredSize(new java.awt.Dimension(65, 16));
        b_tinhTrang.add(lbl_tinhTrang);

        txt_tinhTrang.setEditable(false);
        txt_tinhTrang.setColumns(34);
        txt_tinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tinhTrangActionPerformed(evt);
            }
        });
        b_tinhTrang.add(txt_tinhTrang);

        pnl_ngayNhaptinhTrang.add(b_tinhTrang);

        pnl_thongTinDon.add(pnl_ngayNhaptinhTrang);
        pnl_thongTinDon.add(filler9);

        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        lbl_nhaCungCap1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nhaCungCap1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_nhaCungCap1.setText("Nhà cung cấp:");
        lbl_nhaCungCap1.setMinimumSize(new java.awt.Dimension(87, 16));
        lbl_nhaCungCap1.setPreferredSize(new java.awt.Dimension(87, 16));
        jPanel14.add(lbl_nhaCungCap1);

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel14.add(jTextField1);

        jPanel13.add(jPanel14);

        pnl_thongTinDon.add(jPanel13);
        pnl_thongTinDon.add(filler10);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.X_AXIS));

        lbl_ghiChuDonNhap1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ghiChuDonNhap1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ghiChuDonNhap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_ghiChuDonNhap1.setText("Ghi chú:");
        lbl_ghiChuDonNhap1.setToolTipText("");
        lbl_ghiChuDonNhap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl_ghiChuDonNhap1.setName(""); // NOI18N
        lbl_ghiChuDonNhap1.setPreferredSize(new java.awt.Dimension(80, 16));
        jPanel5.add(lbl_ghiChuDonNhap1);

        jTextField4.setEditable(false);
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setBorder(null);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField4);

        pnl_thongTinDon.add(jPanel5);

        txa_ghiChuDonNhap1.setEditable(false);
        txa_ghiChuDonNhap1.setColumns(20);
        txa_ghiChuDonNhap1.setRows(5);
        jScrollPane9.setViewportView(txa_ghiChuDonNhap1);

        pnl_thongTinDon.add(jScrollPane9);

        b_tongTien.setPreferredSize(new java.awt.Dimension(315, 50));
        b_tongTien.setLayout(new java.awt.GridLayout(1, 4));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tongTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_tongTien.setText("Tổng:");
        lbl_tongTien.setMaximumSize(new java.awt.Dimension(50, 25));
        lbl_tongTien.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel6.add(lbl_tongTien);

        txt_tongTien.setEditable(false);
        txt_tongTien.setColumns(20);
        txt_tongTien.setBorder(null);
        txt_tongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tongTienActionPerformed(evt);
            }
        });
        jPanel6.add(txt_tongTien);

        b_tongTien.add(jPanel6);

        pnl_thongTinDon.add(b_tongTien);

        pnl_chiTietDonNhap.add(pnl_thongTinDon, java.awt.BorderLayout.SOUTH);

        pnl_donDatHang.add(pnl_chiTietDonNhap);

        tab_donHang.addTab("Đơn nhập hàng", pnl_donDatHang);

        pnl_hoaDon.setLayout(new java.awt.BorderLayout());
        pnl_hoaDon.add(filler8, java.awt.BorderLayout.PAGE_END);

        pnl_timKiemHoaDon.setPreferredSize(new java.awt.Dimension(70, 70));
        pnl_timKiemHoaDon.setLayout(new javax.swing.BoxLayout(pnl_timKiemHoaDon, javax.swing.BoxLayout.LINE_AXIS));

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setPreferredSize(new java.awt.Dimension(600, 25));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        pnl_timKiemTheoMa.setLayout(new javax.swing.BoxLayout(pnl_timKiemTheoMa, javax.swing.BoxLayout.LINE_AXIS));

        lbl_timKiemTheoMaKhachHang.setText("Số điện thoại:");
        pnl_timKiemTheoMa.add(lbl_timKiemTheoMaKhachHang);

        txt_timKiemTheoMaKhachHang.setColumns(12);
        txt_timKiemTheoMaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemTheoMaKhachHangActionPerformed(evt);
            }
        });
        pnl_timKiemTheoMa.add(txt_timKiemTheoMaKhachHang);

        jPanel10.add(pnl_timKiemTheoMa);

        pnl_timKiemTheoNgay.setPreferredSize(new java.awt.Dimension(600, 22));
        pnl_timKiemTheoNgay.setLayout(new javax.swing.BoxLayout(pnl_timKiemTheoNgay, javax.swing.BoxLayout.LINE_AXIS));

        lbl_timTuNgay.setText("Từ ngày:");
        lbl_timTuNgay.setPreferredSize(new java.awt.Dimension(53, 16));
        pnl_timKiemTheoNgay.add(lbl_timTuNgay);
        pnl_timKiemTheoNgay.add(jDateChooser3);

        lbl_timDenNgay.setText("Đến ngày:");
        pnl_timKiemTheoNgay.add(lbl_timDenNgay);
        pnl_timKiemTheoNgay.add(jDateChooser4);

        jPanel10.add(pnl_timKiemTheoNgay);
        pnl_timKiemTheoNgay.getAccessibleContext().setAccessibleName("");
        pnl_timKiemTheoNgay.getAccessibleContext().setAccessibleDescription("");

        jPanel12.add(jPanel10);
        jPanel12.add(filler6);

        jPanel9.setPreferredSize(new java.awt.Dimension(600, 25));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        pnl_timTheoNhaVienThanhToan.setLayout(new javax.swing.BoxLayout(pnl_timTheoNhaVienThanhToan, javax.swing.BoxLayout.LINE_AXIS));

        lbl_maNhanVien.setText("Mã nhân viên:");
        pnl_timTheoNhaVienThanhToan.add(lbl_maNhanVien);

        txt_maNhanVien.setColumns(12);
        txt_maNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maNhanVienActionPerformed(evt);
            }
        });
        pnl_timTheoNhaVienThanhToan.add(txt_maNhanVien);

        jPanel9.add(pnl_timTheoNhaVienThanhToan);

        pnl_timTheoGiaTien.setLayout(new javax.swing.BoxLayout(pnl_timTheoGiaTien, javax.swing.BoxLayout.LINE_AXIS));

        lbl_giaTu.setText("Từ:");
        lbl_giaTu.setPreferredSize(new java.awt.Dimension(53, 16));
        pnl_timTheoGiaTien.add(lbl_giaTu);

        txt_giaTu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giaTuActionPerformed(evt);
            }
        });
        pnl_timTheoGiaTien.add(txt_giaTu);

        lbl_giaDen.setText("Đến:");
        lbl_giaDen.setPreferredSize(new java.awt.Dimension(53, 16));
        pnl_timTheoGiaTien.add(lbl_giaDen);

        txt_giaDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giaDenActionPerformed(evt);
            }
        });
        pnl_timTheoGiaTien.add(txt_giaDen);

        jPanel9.add(pnl_timTheoGiaTien);

        jPanel12.add(jPanel9);

        pnl_timKiemHoaDon.add(jPanel12);

        jPanel16.setMaximumSize(new java.awt.Dimension(10000, 32767));
        jPanel16.setMinimumSize(new java.awt.Dimension(25, 50));
        jPanel16.setPreferredSize(new java.awt.Dimension(0, 70));
        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        btn_search2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_search2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_search2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_search2.setMaximumSize(new java.awt.Dimension(75, 50));
        btn_search2.setMinimumSize(new java.awt.Dimension(75, 50));
        btn_search2.setPreferredSize(new java.awt.Dimension(10, 20));
        jPanel16.add(btn_search2);

        btn_search1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_search1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartReset.png"))); // NOI18N
        btn_search1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_search1.setMaximumSize(new java.awt.Dimension(75, 50));
        btn_search1.setMinimumSize(new java.awt.Dimension(75, 50));
        btn_search1.setPreferredSize(new java.awt.Dimension(30, 40));
        btn_search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search1ActionPerformed(evt);
            }
        });
        jPanel16.add(btn_search1);

        pnl_timKiemHoaDon.add(jPanel16);

        pnl_hoaDon.add(pnl_timKiemHoaDon, java.awt.BorderLayout.NORTH);

        pnl_center.setLayout(new java.awt.GridLayout(1, 2));

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        table_danhSachHangHoa2.setModel(tblModel_danhSachHoaDon = new DefaultTableModel(new String[]{"Mã", "Nhân viên","Khách hàng", "Hình thức thanh toán", "Ngày",
        }, 0));
        jScrollPane7.setViewportView(table_danhSachHangHoa2);

        pnl_center.add(jScrollPane7);

        pnl_thongTinHoaDon.setLayout(new java.awt.BorderLayout());

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        jScrollPane8.setPreferredSize(new java.awt.Dimension(500, 427));

        table_danhSachHoaDon.setModel(tblModel_chiTietHoaDon = new DefaultTableModel(new String[]{"Mã", "Tên", "Nhóm hàng", "Số lượng", "Đơn giá", "Tổng"
        }, 0));
        jScrollPane8.setViewportView(table_danhSachHoaDon);
        if (table_danhSachHoaDon.getColumnModel().getColumnCount() > 0) {
            table_danhSachHoaDon.getColumnModel().getColumn(0).setResizable(false);
        }

        pnl_thongTinHoaDon.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        pnl_thongTinChiTietDon.setLayout(new javax.swing.BoxLayout(pnl_thongTinChiTietDon, javax.swing.BoxLayout.Y_AXIS));

        b_tongTien1.setPreferredSize(new java.awt.Dimension(288, 30));
        b_tongTien1.setLayout(new javax.swing.BoxLayout(b_tongTien1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Khách hàng:");
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 16));
        b_tongTien1.add(jLabel3);

        jTextField2.setEditable(false);
        jTextField2.setColumns(20);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        b_tongTien1.add(jTextField2);
        b_tongTien1.add(filler7);

        jLabel4.setBackground(new java.awt.Color(102, 102, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Số điện thoại");
        jLabel4.setPreferredSize(new java.awt.Dimension(75, 16));
        b_tongTien1.add(jLabel4);

        jTextField3.setEditable(false);
        jTextField3.setColumns(10);
        b_tongTien1.add(jTextField3);

        pnl_thongTinChiTietDon.add(b_tongTien1);
        pnl_thongTinChiTietDon.add(filler1);

        b_ngayNhap1.setPreferredSize(new java.awt.Dimension(144, 30));
        b_ngayNhap1.setLayout(new javax.swing.BoxLayout(b_ngayNhap1, javax.swing.BoxLayout.LINE_AXIS));

        lbl_ngayNhap2.setBackground(new java.awt.Color(102, 102, 102));
        lbl_ngayNhap2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ngayNhap2.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ngayNhap2.setText("Ngày lập:");
        lbl_ngayNhap2.setPreferredSize(new java.awt.Dimension(70, 16));
        b_ngayNhap1.add(lbl_ngayNhap2);

        txt_ngayNhap2.setEditable(false);
        txt_ngayNhap2.setColumns(40);
        b_ngayNhap1.add(txt_ngayNhap2);

        pnl_thongTinChiTietDon.add(b_ngayNhap1);
        pnl_thongTinChiTietDon.add(filler4);

        b_tinhTrang1.setPreferredSize(new java.awt.Dimension(144, 30));
        b_tinhTrang1.setLayout(new javax.swing.BoxLayout(b_tinhTrang1, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tinhTrang2.setBackground(new java.awt.Color(102, 102, 102));
        lbl_tinhTrang2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tinhTrang2.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tinhTrang2.setText("Nhân viên:");
        lbl_tinhTrang2.setPreferredSize(new java.awt.Dimension(70, 16));
        b_tinhTrang1.add(lbl_tinhTrang2);

        txt_tinhTrang2.setEditable(false);
        txt_tinhTrang2.setColumns(40);
        b_tinhTrang1.add(txt_tinhTrang2);

        pnl_thongTinChiTietDon.add(b_tinhTrang1);
        pnl_thongTinChiTietDon.add(filler5);

        b_tongTien2.setForeground(new java.awt.Color(255, 255, 255));
        b_tongTien2.setEnabled(false);
        b_tongTien2.setFocusCycleRoot(true);
        b_tongTien2.setPreferredSize(new java.awt.Dimension(144, 40));
        b_tongTien2.setRequestFocusEnabled(false);
        b_tongTien2.setLayout(new javax.swing.BoxLayout(b_tongTien2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.X_AXIS));
        b_tongTien2.add(jPanel7);

        lbl_tongTien1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_tongTien1.setText("Tổng:");
        lbl_tongTien1.setPreferredSize(new java.awt.Dimension(80, 16));
        b_tongTien2.add(lbl_tongTien1);

        txt_tongTien1.setEditable(false);
        txt_tongTien1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_tongTien1.setBorder(null);
        txt_tongTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tongTien1ActionPerformed(evt);
            }
        });
        b_tongTien2.add(txt_tongTien1);

        pnl_thongTinChiTietDon.add(b_tongTien2);

        pnl_thongTinHoaDon.add(pnl_thongTinChiTietDon, java.awt.BorderLayout.SOUTH);

        pnl_center.add(pnl_thongTinHoaDon);

        pnl_hoaDon.add(pnl_center, java.awt.BorderLayout.CENTER);

        tab_donHang.addTab("Hóa đơn bán hàng", pnl_hoaDon);

        add(tab_donHang, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_timKiemTheoMaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemTheoMaKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiemTheoMaKhachHangActionPerformed

    private void txt_giaTuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giaTuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giaTuActionPerformed

    private void txt_giaDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giaDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giaDenActionPerformed

    private void txt_maNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maNhanVienActionPerformed

    private void txt_tongTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tongTien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tongTien1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void txt_tongTienPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tongTienPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tongTienPhieuNhapActionPerformed

    private void btn_xoaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_xoaHangActionPerformed

    private void btn_nhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nhapHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nhapHangActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_tongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tongTienActionPerformed

    private void cbo_nhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_nhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_nhaCungCapActionPerformed

    private void txt_tinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tinhTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tinhTrangActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btn_search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_search1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel b_ngayNhap;
    private javax.swing.JPanel b_ngayNhap1;
    private javax.swing.JPanel b_tinhTrang;
    private javax.swing.JPanel b_tinhTrang1;
    private javax.swing.JPanel b_tongTien;
    private javax.swing.JPanel b_tongTien1;
    private javax.swing.JPanel b_tongTien2;
    private javax.swing.JButton btn_nhapHang;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_search1;
    private javax.swing.JButton btn_search2;
    private javax.swing.JButton btn_themHang;
    private javax.swing.JButton btn_xoaHang;
    private javax.swing.JComboBox<String> cbo_nhaCungCap;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lbl_ghiChuDonNhap;
    private javax.swing.JLabel lbl_ghiChuDonNhap1;
    private javax.swing.JLabel lbl_giaDen;
    private javax.swing.JLabel lbl_giaTu;
    private javax.swing.JLabel lbl_maNhanVien;
    private javax.swing.JLabel lbl_ngayNhap;
    private javax.swing.JLabel lbl_ngayNhap2;
    private javax.swing.JLabel lbl_nhaCungCap;
    private javax.swing.JLabel lbl_nhaCungCap1;
    private javax.swing.JLabel lbl_timDenNgay;
    private javax.swing.JLabel lbl_timKiemTheoMaKhachHang;
    private javax.swing.JLabel lbl_timTuNgay;
    private javax.swing.JLabel lbl_tinhTrang;
    private javax.swing.JLabel lbl_tinhTrang2;
    private javax.swing.JLabel lbl_tongPhieuNhap;
    private javax.swing.JLabel lbl_tongTien;
    private javax.swing.JLabel lbl_tongTien1;
    private javax.swing.JPanel pnl_center;
    private javax.swing.JPanel pnl_chiTietDonNhap;
    private javax.swing.JPanel pnl_chiTietDonNhap1;
    private javax.swing.JPanel pnl_danhSachDon;
    private javax.swing.JPanel pnl_danhSachHangHoa1;
    private javax.swing.JPanel pnl_donDatHang;
    private javax.swing.JPanel pnl_ghiChu;
    private javax.swing.JPanel pnl_hoaDon;
    private javax.swing.JPanel pnl_ngayNhaptinhTrang;
    private javax.swing.JPanel pnl_search;
    private javax.swing.JPanel pnl_themDonDatHang;
    private javax.swing.JPanel pnl_themDonHangCenter;
    private javax.swing.JPanel pnl_thongTinChiTietDon;
    private javax.swing.JPanel pnl_thongTinDon;
    private javax.swing.JPanel pnl_thongTinDon1;
    private javax.swing.JPanel pnl_thongTinDonNhapLine2;
    private javax.swing.JPanel pnl_thongTinHoaDon;
    private javax.swing.JPanel pnl_timKiemHoaDon;
    private javax.swing.JPanel pnl_timKiemTheoMa;
    private javax.swing.JPanel pnl_timKiemTheoMaSanPham;
    private javax.swing.JPanel pnl_timKiemTheoNgay;
    private javax.swing.JPanel pnl_timTheoGiaTien;
    private javax.swing.JPanel pnl_timTheoNhaVienThanhToan;
    private javax.swing.JPanel pnl_tongTienPhieuNhap;
    private javax.swing.JTabbedPane tab_donHang;
    private javax.swing.JTable table_chiTietDonNhap1;
    private javax.swing.JTable table_danhSachHangHoa2;
    private javax.swing.JTable table_danhSachHoaDon;
    private javax.swing.JTable tbl_chiTietDonNhap;
    private javax.swing.JTable tbl_danhSachDonDaDat;
    private javax.swing.JTable tbl_danhSachHangHoa;
    private javax.swing.JTextArea txa_ghiChuDonNhap;
    private javax.swing.JTextArea txa_ghiChuDonNhap1;
    private javax.swing.JTextField txt_giaDen;
    private javax.swing.JTextField txt_giaTu;
    private javax.swing.JTextField txt_maNhanVien;
    private javax.swing.JTextField txt_ngayNhap;
    private javax.swing.JTextField txt_ngayNhap2;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_timKiemTheoMaKhachHang;
    private javax.swing.JTextField txt_tinhTrang;
    private javax.swing.JTextField txt_tinhTrang2;
    private javax.swing.JTextField txt_tongTien;
    private javax.swing.JTextField txt_tongTien1;
    private javax.swing.JTextField txt_tongTienPhieuNhap;
    // End of variables declaration//GEN-END:variables
}
