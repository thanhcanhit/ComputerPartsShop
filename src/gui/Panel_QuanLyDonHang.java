/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import bus.DonNhapHang_bus;
import bus.HoaDon_bus;
import bus.KhachHang_bus;
import bus.NhaCungCap_bus;
import bus.NhanVien_bus;
import bus.SanPham_bus;
import bus.ThuongHieu_bus;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import entity.connguoi.KhachHang;
import entity.connguoi.NhaCungCap;
import entity.connguoi.NhanVien;
import entity.hoadon.ChiTietHoaDon;
import entity.hoadon.HoaDon;
import entity.kho.ChiTietDonNhap;
import entity.kho.DonNhapHang;
import entity.kho.KhoHang;
import entity.sanpham.SanPham;
import entity.sanpham.ThuongHieu;
import entity.share.Utility;

/**
 *
 * @author HP
 */
public final class Panel_QuanLyDonHang extends javax.swing.JPanel {

    private DefaultTableModel tblModel_tab1DanhSachHangHoa;
    private DefaultTableModel tblModel_tab1ChiTietDonNhap;
    private DefaultTableModel tblModel_tab2DanhSachDonNhap;
    private DefaultTableModel tblModel_tab2ChiTietDonNhap;
    private DefaultTableModel tblModel_tab3DanhSachHoaDon;
    private DefaultTableModel tblModel_tab3ChiTietHoaDon;

    private DonNhapHang_bus donNhap_bus;
    private SanPham_bus sanPham_bus;
    private NhaCungCap_bus nhaCungCap_bus;
    private NhanVien_bus nhanVien_bus;
    private ThuongHieu_bus thuongHieu_bus;
    private KhachHang_bus khachHang_bus;

    private NhanVien nhanVien;
    private ArrayList<ChiTietDonNhap> gioHang = new ArrayList<>();
    private double tongTien = 0;
    private HoaDon_bus hoaDon_bus;

    /**
     * Creates new form Panel_QuanLyDonHang
     */
    public Panel_QuanLyDonHang(NhanVien nhanVien) {
        this.nhanVien = nhanVien;

        initDataObject();
        initTableModel();
        initComponents();
        addCustomEvent();
        alterTable();

        renderAllTab1DanhSachSanPham();
        renderCMB_NhaCungCap();
        renderAllTab2DanhSachDonNhap();
        renderAllTab3DanhSachHoaDon();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        jdate_tab3TuNgay.setDate(cal.getTime());

// // Thiết lập giá trị mặc định cho txt_tab3GiaDen
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        jdate_tab3DenNgay.setDate(cal.getTime());
    }

    public void renderCMB_NhaCungCap() {
        cbo_tab1NhaCungCap.removeAllItems();
        for (NhaCungCap ncc : nhaCungCap_bus.getAllNhaCungCap()) {
            cbo_tab1NhaCungCap.addItem(ncc.getMaNCC() + " - " + ncc.getTenNCC());
        }
    }

    public void alterTable() {
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);

//        Table tab 1
        tbl_tab1DanhSachHangHoa.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_tab1DanhSachHangHoa.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_tab1DanhSachHangHoa.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbl_tab1DanhSachHangHoa.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbl_tab1DanhSachHangHoa.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_tab1DanhSachHangHoa.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_tab1DanhSachHangHoa.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        tbl_tab1DanhSachHangHoa.setDefaultEditor(Object.class, null);

        tbl_tab1ChiTietDonNhap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_tab1ChiTietDonNhap.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_tab1ChiTietDonNhap.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbl_tab1ChiTietDonNhap.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbl_tab1ChiTietDonNhap.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_tab1ChiTietDonNhap.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_tab1ChiTietDonNhap.setDefaultEditor(Object.class, null);

//        tbl_tab1ChiTietDonNhap.set;
//        Table tab 2
        tbl_tab2DanhSachDonNhap.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tbl_tab2DanhSachDonNhap.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_tab2DanhSachDonNhap.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbl_tab2DanhSachDonNhap.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_tab2DanhSachDonNhap.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_tab2DanhSachDonNhap.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_tab2DanhSachDonNhap.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbl_tab2DanhSachDonNhap.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_tab2DanhSachDonNhap.setDefaultEditor(Object.class, null);

        tbl_tab2ChiTietDonNhap.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        tbl_tab2ChiTietDonNhap.setDefaultEditor(Object.class, null);

        tbl_tab3DanhSachHoaDon.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        tbl_tab3ChiTietHoaDon.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_tab3ChiTietHoaDon.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        tbl_tab3ChiTietHoaDon.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
        tbl_tab3DanhSachHoaDon.setDefaultEditor(Object.class, null);
        tbl_tab3ChiTietHoaDon.setDefaultEditor(Object.class, null);
    }

    public void addCustomEvent() {
        tbl_tab2DanhSachDonNhap.getSelectionModel().addListSelectionListener(e -> {
            int row = tbl_tab2DanhSachDonNhap.getSelectedRow();
            if (row != -1) {
                String id = tbl_tab2DanhSachDonNhap.getValueAt(row, 0).toString();
                DonNhapHang dn = donNhap_bus.getDonNhapHangTheoMa(id).get(0);
                renderTab2ChiTietDonNhap(dn.getChiTietDonNhap());

//                Set text to right panel
                txt_tab2NgayNhap.setText(dn.getNgayNhap().toString());
                rad_chuaNhan.setSelected(!dn.isDanhan());
                rad_chuaNhan.setEnabled(!dn.isDanhan());
                rad_daNhan.setSelected(dn.isDanhan());
//                cbo_tab2TinhTrang.setSelectedIndex(dn.isDanhan() ? 1 : 0);
//                cbo_tab2TinhTrang.setEnabled(cbo_tab2TinhTrang.getSelectedIndex() == 0);

                txa_tab2GhiChuDonNhap.setText(dn.getGhiChu());

                NhaCungCap ncc = nhaCungCap_bus.getNhaCungCapTheoMa(dn.getNhaCungCap().getMaNCC()).get(0);
                txt_tab2NCC.setText(ncc.getTenNCC());
                txt_tab2TongTien.setText(Utility.getVND(dn.getTongTien()));
            }
        });

        tbl_tab3DanhSachHoaDon.getSelectionModel().addListSelectionListener(e -> {
            int row = tbl_tab3DanhSachHoaDon.getSelectedRow();
            if (row != -1) {
                String id = tblModel_tab3DanhSachHoaDon.getValueAt(row, 0).toString();
                HoaDon hd = hoaDon_bus.getHoaDonTheoMa(id).get(0);
                renderTab3ChiTietHoaDon(hd.getDsChiTiethoaDon());
                renderThongTinHoaDon(hd);
            }
        });
    }

    public void initDataObject() {
        donNhap_bus = new DonNhapHang_bus();
        sanPham_bus = new SanPham_bus();
        nhaCungCap_bus = new NhaCungCap_bus();
        nhanVien_bus = new NhanVien_bus();
        thuongHieu_bus = new ThuongHieu_bus();
        hoaDon_bus = new HoaDon_bus();
        khachHang_bus = new KhachHang_bus();
    }

    public void initTableModel() {

        tblModel_tab3DanhSachHoaDon = new DefaultTableModel(new String[]{"Mã hóa đơn", "Nhân Viên", "Khách Hàng", "Ngày", "Hình Thức Thanh Toán", "Tổng"}, 0);
        tblModel_tab1ChiTietDonNhap = new DefaultTableModel(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng", "Giá Nhập", "Tổng tiền"}, 0);
    }

    public void renderAllTab2DanhSachDonNhap() {
        renderTab2DanhSachDonNhap(donNhap_bus.getAllDonNhapHang());
    }

    public void renderAllTab1DanhSachSanPham() {
        renderTab1DanhSachSanPham(sanPham_bus.getAllSanPham());
    }

    public void renderTab1DanhSachSanPham(ArrayList<SanPham> list) {
        tblModel_tab1DanhSachHangHoa.setRowCount(0);
        for (SanPham sp : list) {
            ThuongHieu th = thuongHieu_bus.getThuongHieuTheoMa(sp.getThuongHieu().getMaTH()).get(0);
            Object[] row = new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), th.toString(), Utility.getVND(sp.getGiaNhap())};
            tblModel_tab1DanhSachHangHoa.addRow(row);
        }
    }

    public void renderAllTab3DanhSachHoaDon() {
        renderTab3DanhSachHoaDon(hoaDon_bus.getAllHoaDon());
    }

    public void renderTab1DanhSachHangHoa(ArrayList<SanPham> list) {
        tblModel_tab1DanhSachHangHoa.setRowCount(0);
        for (SanPham sp : list) {
            ThuongHieu th = thuongHieu_bus.getThuongHieuTheoMa(sp.getThuongHieu().getMaTH()).get(0);
            Object[] row = new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), th.toString(), Utility.getVND(sp.getGiaNhap())};
            tblModel_tab1DanhSachHangHoa.addRow(row);
        }
    }

    public void renderTab1ChiTietDonNhap(ArrayList<ChiTietDonNhap> list) {
        this.tongTien = 0;
        tblModel_tab1ChiTietDonNhap.setRowCount(0);
        for (ChiTietDonNhap ct : list) {
            SanPham sp = sanPham_bus.getSanPhamTheoMa(ct.getSanPham().getMaSP()).get(0);
            ct.setTongTien(ct.getSoLuong() * sp.getGiaNhap());
            tongTien += ct.getTongTien();
            tblModel_tab1ChiTietDonNhap.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), ct.getSoLuong(), Utility.getVND(sp.getGiaNhap()), Utility.getVND(ct.getTongTien())});
        }
        txt_tab1TongTien.setText(Utility.getVND(tongTien));
    }

    public void renderTab1CartTable() {
        renderTab1ChiTietDonNhap(gioHang);
    }

    public void renderTab2DanhSachDonNhap(ArrayList<DonNhapHang> list) {
        tblModel_tab2DanhSachDonNhap.setRowCount(0);
        for (DonNhapHang donNhapHang : list) {
            NhanVien nv = nhanVien_bus.getNhanVienTheoMa(donNhapHang.getNhanVien().getMaNV()).get(0);
            tblModel_tab2DanhSachDonNhap.addRow(new Object[]{donNhapHang.getMaDonNhap(), nv.getMaNV() + " - " + nv.getHoTen(), donNhapHang.getNhaCungCap().getMaNCC(), donNhapHang.getNgayNhap(), donNhapHang.isDanhan() ? "Đã nhận" : "Chưa nhận", donNhapHang.getKhoHang().getMaKho()});
        }
    }

    public void renderTab2ChiTietDonNhap(ArrayList<ChiTietDonNhap> list) {
        tblModel_tab2ChiTietDonNhap.setRowCount(0);
        for (ChiTietDonNhap ct : list) {
            SanPham sp = sanPham_bus.getSanPhamTheoMa(ct.getSanPham().getMaSP()).get(0);
            tblModel_tab2ChiTietDonNhap.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), ct.getSoLuong(), Utility.getVND(sp.getGiaNhap()), Utility.getVND(ct.getSoLuong() * sp.getGiaNhap())});
        }
    }

    public void renderTab3DanhSachHoaDon(ArrayList<HoaDon> list) {
        tblModel_tab3DanhSachHoaDon.setRowCount(0);
        for (HoaDon hoaDon : list) {
            HoaDon hd = hoaDon_bus.getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
            KhachHang kh = khachHang_bus.getKhachHangTheoMa(hoaDon.getKhachHang().getMaKH()).get(0);
            NhanVien nv = nhanVien_bus.getNhanVienTheoMa(hoaDon.getNhanVien().getMaNV()).get(0);
            tblModel_tab3DanhSachHoaDon.addRow(new Object[]{hoaDon.getMaHoaDon(), nv.getHoTen(), kh.getHoTen(), hoaDon.getPhuongThucThanhToan(), hoaDon.getNgayLap(), Utility.getVND(hoaDon.getTongTien())});
        }
    }

    public void renderTab3ChiTietHoaDon(ArrayList<ChiTietHoaDon> list) {
        tblModel_tab3ChiTietHoaDon.setRowCount(0);
        for (ChiTietHoaDon chiTietHoaDon : list) {
            SanPham sp = sanPham_bus.getSanPhamTheoMa(chiTietHoaDon.getSanPham().getMaSP()).get(0);
            tblModel_tab3ChiTietHoaDon.addRow(new Object[]{chiTietHoaDon.getSanPham().getMaSP(), sp.getTenSP(), sp.getTenLoai(), chiTietHoaDon.getSoLuong(), Utility.getVND(sp.getGiaBan()), Utility.getVND(sp.getGiaBan() * chiTietHoaDon.getSoLuong())});
        }
    }

    public void renderThongTinHoaDon(HoaDon hoaDon) {
        HoaDon hd = hoaDon_bus.getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
        KhachHang kh = khachHang_bus.getKhachHangTheoMa(hoaDon.getKhachHang().getMaKH()).get(0);
        NhanVien nv = nhanVien_bus.getNhanVienTheoMa(hoaDon.getNhanVien().getMaNV()).get(0);
        txt_tab3KhachHang.setText(kh.getHoTen());
        txt_tab3SoDienThoai.setText(kh.getSoDT());
        txt_tab3NgayLap.setText(hd.getNgayLap().toString());
        txt_tab3NhanVien.setText(nv.getHoTen());
        txt_tab3TongTien.setText(Utility.getVND(hoaDon.getTongTien()));

    }

    public boolean validateFields() {
        boolean isValid = true;
        String phoneRegex = "^0\\d{9}$";
        String employeeIdRegex = "^NV\\d{4}$";
        String priceRegex = "^\\d+(\\.\\d+)?$";

        // Kiểm tra số điện thoại
        String sdt = txt_tab3SDT.getText().trim();
        if (!sdt.equals("") && !sdt.matches(phoneRegex)) {
            isValid = false;
            // Hiển thị thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!.");
        }

        // Kiểm tra mã nhân viên
        String maNV = txt_tab3MaNhanVien.getText().trim();
        if (!maNV.equals("") && !maNV.matches(employeeIdRegex)) {
            isValid = false;
            // Hiển thị thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ! Vui lòng nhập mã nhân viên có dạng NVxxxx.");
        }

        // Kiểm tra giá từ
        String giaTu = txt_tab3GiaTu.getText().trim();
        if (!giaTu.equals("") && (!giaTu.matches(priceRegex) || Double.parseDouble(giaTu) < 0)) {
            isValid = false;
            // Hiển thị thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(null, "Giá không hợp lệ! Vui lòng nhập số không âm.");
        }

        // Kiểm tra giá đến
        String giaDen = txt_tab3GiaDen.getText().trim();
        if (!giaDen.equals("") && (!giaDen.matches(priceRegex) || Double.parseDouble(giaDen) < 0)) {
            isValid = false;
            // Hiển thị thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(null, "Giá không hợp lệ! Vui lòng nhập số không âm.");
        }

        return isValid;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tab_donHang = new javax.swing.JTabbedPane();
        pnl_themDonDatHang = new javax.swing.JPanel();
        pnl_themDonHangCenter = new javax.swing.JPanel();
        pnl_danhSachHangHoa1 = new javax.swing.JPanel();
        scr_3 = new javax.swing.JScrollPane();
        tbl_tab1DanhSachHangHoa = new javax.swing.JTable();
        pnl_chiTietDonNhap1 = new javax.swing.JPanel();
        scr_4 = new javax.swing.JScrollPane();
        tbl_tab1ChiTietDonNhap = new javax.swing.JTable(tblModel_tab1ChiTietDonNhap);
        pnl_thongTinDonNhapLine2 = new javax.swing.JPanel();
        btn_themHang = new javax.swing.JButton();
        btn_xoaHang = new javax.swing.JButton();
        pnl_thongTinDon1 = new javax.swing.JPanel();
        pnl_ghiChu = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(32767, 4));
        pnl_tab1NhaCungCap = new javax.swing.JPanel();
        lbl_nhaCungCap = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        cbo_tab1NhaCungCap = new javax.swing.JComboBox<>();
        pnl_tab1GhiChu = new javax.swing.JPanel();
        lbl_ghiChuDonNhap = new javax.swing.JLabel();
        pnl_tab1GhiChuText = new javax.swing.JPanel();
        scr_6 = new javax.swing.JScrollPane();
        txa_tab1GhiChuDonNhap = new javax.swing.JTextArea();
        pnl_tongTienPhieuNhap = new javax.swing.JPanel();
        lbl_tongPhieuNhap = new javax.swing.JLabel();
        txt_tab1TongTien = new javax.swing.JTextField();
        pnl_tab1Control = new javax.swing.JPanel();
        btn_tab1HuyDon = new javax.swing.JButton();
        btn_tab1NhapHang = new javax.swing.JButton();
        pnl_timKiemTheoMaSanPham = new javax.swing.JPanel();
        pnl_search = new javax.swing.JPanel();
        txt_tab1Search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_tab1Reset = new javax.swing.JButton();
        pnl_donDatHang = new javax.swing.JPanel();
        pnl_danhSachDon = new javax.swing.JPanel();
        scr_1 = new javax.swing.JScrollPane();
        tbl_tab2DanhSachDonNhap = new javax.swing.JTable();
        pnl_chiTietDonNhap = new javax.swing.JPanel();
        scr_2 = new javax.swing.JScrollPane();
        tbl_tab2ChiTietDonNhap = new javax.swing.JTable();
        pnl_thongTinDon = new javax.swing.JPanel();
        pnl_tab2TinhTrang = new javax.swing.JPanel();
        lbl_tinhTrang = new javax.swing.JLabel();
        pnl_tab2TinhTrangRad = new javax.swing.JPanel();
        rad_chuaNhan = new javax.swing.JRadioButton();
        rad_daNhan = new javax.swing.JRadioButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 8));
        pnl_tab2NgayNhap = new javax.swing.JPanel();
        lbl_ngayNhap = new javax.swing.JLabel();
        txt_tab2NgayNhap = new javax.swing.JTextField();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 8));
        pnl_tab2NhaCungCap = new javax.swing.JPanel();
        lbl_nhaCungCap1 = new javax.swing.JLabel();
        txt_tab2NCC = new javax.swing.JTextField();
        pnl_tab3GhiChu = new javax.swing.JPanel();
        lbl_ghiChuDonNhap1 = new javax.swing.JLabel();
        scr_5 = new javax.swing.JScrollPane();
        txa_tab2GhiChuDonNhap = new javax.swing.JTextArea();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 50));
        pnl_tab2TongTien = new javax.swing.JPanel();
        lbl_tongTien = new javax.swing.JLabel();
        txt_tab2TongTien = new javax.swing.JTextField();
        pnl_hoaDon = new javax.swing.JPanel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        pnl_timKiemHoaDon = new javax.swing.JPanel();
        pnl_tab3Search = new javax.swing.JPanel();
        pnl_tab3SearchB1 = new javax.swing.JPanel();
        pnl_timKiemTheoMa = new javax.swing.JPanel();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lbl_timKiemTheoMaKhachHang = new javax.swing.JLabel();
        filler25 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        txt_tab3SDT = new javax.swing.JTextField();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        pnl_timKiemTheoNgay = new javax.swing.JPanel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lbl_timTuNgay = new javax.swing.JLabel();
        jdate_tab3TuNgay = new com.toedter.calendar.JDateChooser();
        filler27 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lbl_timDenNgay = new javax.swing.JLabel();
        jdate_tab3DenNgay = new com.toedter.calendar.JDateChooser();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        pnl_tab3SearchB2 = new javax.swing.JPanel();
        pnl_timTheoNhaVienThanhToan = new javax.swing.JPanel();
        filler26 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lbl_maNhanVien = new javax.swing.JLabel();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        txt_tab3MaNhanVien = new javax.swing.JTextField();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        pnl_timTheoGiaTien = new javax.swing.JPanel();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lbl_giaTu = new javax.swing.JLabel();
        txt_tab3GiaTu = new javax.swing.JTextField();
        filler28 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lbl_giaDen = new javax.swing.JLabel();
        txt_tab3GiaDen = new javax.swing.JTextField();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        pnl_tab3SearchControl = new javax.swing.JPanel();
        btn_tab3Search = new javax.swing.JButton();
        btn_tab3Reset = new javax.swing.JButton();
        pnl_center = new javax.swing.JPanel();
        scr_7 = new javax.swing.JScrollPane();
        tbl_tab3DanhSachHoaDon = new javax.swing.JTable();
        pnl_thongTinHoaDon = new javax.swing.JPanel();
        src_8 = new javax.swing.JScrollPane();
        tbl_tab3ChiTietHoaDon = new javax.swing.JTable();
        pnl_thongTinChiTietDon = new javax.swing.JPanel();
        b_tongTien1 = new javax.swing.JPanel();
        lbl_tab3KhachHang = new javax.swing.JLabel();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        txt_tab3KhachHang = new javax.swing.JTextField();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lbl_tab3SDT = new javax.swing.JLabel();
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        txt_tab3SoDienThoai = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        b_ngayNhap1 = new javax.swing.JPanel();
        lbl_tab3NgayNhap = new javax.swing.JLabel();
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        txt_tab3NgayLap = new javax.swing.JTextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        b_tinhTrang1 = new javax.swing.JPanel();
        lbl_tab3TinhTrang = new javax.swing.JLabel();
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        txt_tab3NhanVien = new javax.swing.JTextField();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        b_tongTien2 = new javax.swing.JPanel();
        lbl_tongTien1 = new javax.swing.JLabel();
        txt_tab3TongTien = new javax.swing.JTextField();

        buttonGroup1.add(rad_chuaNhan);
        buttonGroup1.add(rad_daNhan);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        tab_donHang.setBackground(new java.awt.Color(255, 255, 255));
        tab_donHang.setForeground(new java.awt.Color(102, 102, 102));
        tab_donHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab_donHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        pnl_themDonDatHang.setLayout(new java.awt.BorderLayout());

        pnl_themDonHangCenter.setLayout(new javax.swing.BoxLayout(pnl_themDonHangCenter, javax.swing.BoxLayout.X_AXIS));

        pnl_danhSachHangHoa1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_danhSachHangHoa1.setPreferredSize(new java.awt.Dimension(600, 427));
        pnl_danhSachHangHoa1.setLayout(new java.awt.BorderLayout());

        scr_3.setBackground(new java.awt.Color(255, 255, 255));
        scr_3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_tab1DanhSachHangHoa.setModel(tblModel_tab1DanhSachHangHoa= new DefaultTableModel(new String[]{"Mã", "Tên", "Loại", "Thương hiệu", "Giá nhập"
        }, 0));
        tbl_tab1DanhSachHangHoa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl_tab1DanhSachHangHoa.setAutoscrolls(false);
        tbl_tab1DanhSachHangHoa.setRowHeight(30);
        tbl_tab1DanhSachHangHoa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_tab1DanhSachHangHoa.setShowGrid(true);
        tbl_tab1DanhSachHangHoa.setShowVerticalLines(false);
        tbl_tab1DanhSachHangHoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tab1DanhSachHangHoaMouseClicked(evt);
            }
        });
        scr_3.setViewportView(tbl_tab1DanhSachHangHoa);

        pnl_danhSachHangHoa1.add(scr_3, java.awt.BorderLayout.CENTER);

        pnl_themDonHangCenter.add(pnl_danhSachHangHoa1);

        pnl_chiTietDonNhap1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_chiTietDonNhap1.setMaximumSize(new java.awt.Dimension(500, 99999));
        pnl_chiTietDonNhap1.setPreferredSize(new java.awt.Dimension(500, 0));
        pnl_chiTietDonNhap1.setLayout(new javax.swing.BoxLayout(pnl_chiTietDonNhap1, javax.swing.BoxLayout.Y_AXIS));

        scr_4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiếp đơn nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_tab1ChiTietDonNhap.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_tab1ChiTietDonNhap.setAutoscrolls(false);
        tbl_tab1ChiTietDonNhap.setDoubleBuffered(true);
        tbl_tab1ChiTietDonNhap.setRowHeight(30);
        tbl_tab1ChiTietDonNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_tab1ChiTietDonNhap.setShowGrid(true);
        tbl_tab1ChiTietDonNhap.setShowVerticalLines(false);
        tbl_tab1ChiTietDonNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tab1ChiTietDonNhapMouseClicked(evt);
            }
        });
        scr_4.setViewportView(tbl_tab1ChiTietDonNhap);
        if (tbl_tab1ChiTietDonNhap.getColumnModel().getColumnCount() > 0) {
            tbl_tab1ChiTietDonNhap.getColumnModel().getColumn(0).setResizable(false);
        }

        pnl_chiTietDonNhap1.add(scr_4);

        pnl_thongTinDonNhapLine2.setPreferredSize(new java.awt.Dimension(300, 50));
        pnl_thongTinDonNhapLine2.setLayout(new java.awt.GridLayout(1, 0));

        btn_themHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_themHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartAdd.png"))); // NOI18N
        btn_themHang.setText("Thêm hàng");
        btn_themHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_themHang.setIconTextGap(20);
        btn_themHang.setPreferredSize(new java.awt.Dimension(150, 60));
        btn_themHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themHangActionPerformed(evt);
            }
        });
        pnl_thongTinDonNhapLine2.add(btn_themHang);

        btn_xoaHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_xoaHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartRemove.png"))); // NOI18N
        btn_xoaHang.setText("Xóa hàng");
        btn_xoaHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_xoaHang.setIconTextGap(20);
        btn_xoaHang.setPreferredSize(new java.awt.Dimension(150, 60));
        btn_xoaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaHangActionPerformed(evt);
            }
        });
        pnl_thongTinDonNhapLine2.add(btn_xoaHang);

        pnl_chiTietDonNhap1.add(pnl_thongTinDonNhapLine2);

        pnl_thongTinDon1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_thongTinDon1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đơn nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_thongTinDon1.setLayout(new javax.swing.BoxLayout(pnl_thongTinDon1, javax.swing.BoxLayout.Y_AXIS));

        pnl_ghiChu.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ghiChu.setLayout(new javax.swing.BoxLayout(pnl_ghiChu, javax.swing.BoxLayout.Y_AXIS));
        pnl_ghiChu.add(filler2);

        pnl_tab1NhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab1NhaCungCap.setPreferredSize(new java.awt.Dimension(0, 50));
        pnl_tab1NhaCungCap.setLayout(new javax.swing.BoxLayout(pnl_tab1NhaCungCap, javax.swing.BoxLayout.LINE_AXIS));

        lbl_nhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nhaCungCap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_nhaCungCap.setText("Nhà cung cấp:");
        pnl_tab1NhaCungCap.add(lbl_nhaCungCap);
        pnl_tab1NhaCungCap.add(filler3);

        cbo_tab1NhaCungCap.setMaximumRowCount(5);
        pnl_tab1NhaCungCap.add(cbo_tab1NhaCungCap);

        pnl_ghiChu.add(pnl_tab1NhaCungCap);

        pnl_tab1GhiChu.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab1GhiChu.setLayout(new java.awt.GridLayout(1, 0));

        lbl_ghiChuDonNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ghiChuDonNhap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ghiChuDonNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_ghiChuDonNhap.setText("Ghi chú:");
        lbl_ghiChuDonNhap.setToolTipText("");
        lbl_ghiChuDonNhap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl_ghiChuDonNhap.setName(""); // NOI18N
        lbl_ghiChuDonNhap.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_tab1GhiChu.add(lbl_ghiChuDonNhap);

        pnl_ghiChu.add(pnl_tab1GhiChu);

        pnl_tab1GhiChuText.setLayout(new java.awt.GridLayout(1, 0));

        txa_tab1GhiChuDonNhap.setColumns(20);
        txa_tab1GhiChuDonNhap.setRows(5);
        txa_tab1GhiChuDonNhap.setTabSize(4);
        scr_6.setViewportView(txa_tab1GhiChuDonNhap);

        pnl_tab1GhiChuText.add(scr_6);

        pnl_ghiChu.add(pnl_tab1GhiChuText);

        pnl_thongTinDon1.add(pnl_ghiChu);

        pnl_tongTienPhieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tongTienPhieuNhap.setPreferredSize(new java.awt.Dimension(134, 30));
        pnl_tongTienPhieuNhap.setLayout(new java.awt.GridLayout(1, 0));

        lbl_tongPhieuNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_tongPhieuNhap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tongPhieuNhap.setText("Tổng:");
        lbl_tongPhieuNhap.setName(""); // NOI18N
        lbl_tongPhieuNhap.setPreferredSize(new java.awt.Dimension(70, 20));
        pnl_tongTienPhieuNhap.add(lbl_tongPhieuNhap);

        txt_tab1TongTien.setEditable(false);
        txt_tab1TongTien.setBackground(new java.awt.Color(255, 255, 255));
        txt_tab1TongTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt_tab1TongTien.setForeground(new java.awt.Color(65, 165, 238));
        txt_tab1TongTien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tab1TongTien.setToolTipText("");
        txt_tab1TongTien.setBorder(null);
        pnl_tongTienPhieuNhap.add(txt_tab1TongTien);

        pnl_thongTinDon1.add(pnl_tongTienPhieuNhap);

        pnl_tab1Control.setPreferredSize(new java.awt.Dimension(100, 50));
        pnl_tab1Control.setLayout(new java.awt.GridLayout(1, 2));

        btn_tab1HuyDon.setBackground(new java.awt.Color(255, 138, 138));
        btn_tab1HuyDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_tab1HuyDon.setForeground(new java.awt.Color(255, 255, 255));
        btn_tab1HuyDon.setText("Hủy đơn");
        btn_tab1HuyDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab1HuyDonActionPerformed(evt);
            }
        });
        pnl_tab1Control.add(btn_tab1HuyDon);

        btn_tab1NhapHang.setBackground(new java.awt.Color(65, 165, 238));
        btn_tab1NhapHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_tab1NhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btn_tab1NhapHang.setText("Nhập hàng");
        btn_tab1NhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tab1NhapHang.setMaximumSize(new java.awt.Dimension(155, 30));
        btn_tab1NhapHang.setPreferredSize(new java.awt.Dimension(100, 60));
        btn_tab1NhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab1NhapHangActionPerformed(evt);
            }
        });
        pnl_tab1Control.add(btn_tab1NhapHang);

        pnl_thongTinDon1.add(pnl_tab1Control);

        pnl_chiTietDonNhap1.add(pnl_thongTinDon1);

        pnl_themDonHangCenter.add(pnl_chiTietDonNhap1);

        pnl_themDonDatHang.add(pnl_themDonHangCenter, java.awt.BorderLayout.CENTER);

        pnl_timKiemTheoMaSanPham.setPreferredSize(new java.awt.Dimension(70, 40));
        pnl_timKiemTheoMaSanPham.setLayout(new javax.swing.BoxLayout(pnl_timKiemTheoMaSanPham, javax.swing.BoxLayout.X_AXIS));

        pnl_search.setPreferredSize(new java.awt.Dimension(0, 50));
        pnl_search.setLayout(new javax.swing.BoxLayout(pnl_search, javax.swing.BoxLayout.X_AXIS));

        txt_tab1Search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tab1Search.setForeground(new java.awt.Color(153, 153, 153));
        txt_tab1Search.setText("Mã sản phẩm");
        txt_tab1Search.setToolTipText("Vui lòng nhập mã sản phẩm");
        txt_tab1Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tab1SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_tab1SearchFocusLost(evt);
            }
        });
        txt_tab1Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tab1SearchKeyPressed(evt);
            }
        });
        pnl_search.add(txt_tab1Search);

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

        btn_tab1Reset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_tab1Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartReset.png"))); // NOI18N
        btn_tab1Reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tab1Reset.setMaximumSize(new java.awt.Dimension(75, 50));
        btn_tab1Reset.setMinimumSize(new java.awt.Dimension(75, 50));
        btn_tab1Reset.setPreferredSize(new java.awt.Dimension(50, 50));
        btn_tab1Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab1ResetActionPerformed(evt);
            }
        });
        pnl_search.add(btn_tab1Reset);

        pnl_timKiemTheoMaSanPham.add(pnl_search);

        pnl_themDonDatHang.add(pnl_timKiemTheoMaSanPham, java.awt.BorderLayout.NORTH);

        tab_donHang.addTab("Tạo đơn nhập hàng", pnl_themDonDatHang);

        pnl_donDatHang.setLayout(new java.awt.GridLayout(1, 2));

        pnl_danhSachDon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_danhSachDon.setLayout(new javax.swing.BoxLayout(pnl_danhSachDon, javax.swing.BoxLayout.X_AXIS));

        scr_1.setBackground(new java.awt.Color(255, 255, 255));
        scr_1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đơn nhập hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_tab2DanhSachDonNhap.setModel(tblModel_tab2DanhSachDonNhap = new DefaultTableModel(new String[]{"Mã", "Nhân viên", "Nhà cung cấp", "Ngày đặt", "Tình trạng", "Kho"
        }, 0));
        tbl_tab2DanhSachDonNhap.setRowHeight(30);
        tbl_tab2DanhSachDonNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_tab2DanhSachDonNhap.setShowGrid(true);
        tbl_tab2DanhSachDonNhap.setShowVerticalLines(false);
        scr_1.setViewportView(tbl_tab2DanhSachDonNhap);

        pnl_danhSachDon.add(scr_1);

        pnl_donDatHang.add(pnl_danhSachDon);

        pnl_chiTietDonNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_chiTietDonNhap.setPreferredSize(new java.awt.Dimension(500, 640));
        pnl_chiTietDonNhap.setLayout(new java.awt.BorderLayout());

        scr_2.setBackground(new java.awt.Color(255, 255, 255));
        scr_2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết đơn nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_tab2ChiTietDonNhap.setModel(tblModel_tab2ChiTietDonNhap = new DefaultTableModel(new String[]{"Mã", "Tên hàng", "Nhóm hàng", "Số lượng", "Đơn giá", "Tổng"
        }, 0));
        tbl_tab2ChiTietDonNhap.setRowHeight(30);
        tbl_tab2ChiTietDonNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_tab2ChiTietDonNhap.setShowGrid(true);
        tbl_tab2ChiTietDonNhap.setShowVerticalLines(false);
        scr_2.setViewportView(tbl_tab2ChiTietDonNhap);
        if (tbl_tab2ChiTietDonNhap.getColumnModel().getColumnCount() > 0) {
            tbl_tab2ChiTietDonNhap.getColumnModel().getColumn(0).setResizable(false);
        }

        pnl_chiTietDonNhap.add(scr_2, java.awt.BorderLayout.CENTER);

        pnl_thongTinDon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_thongTinDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_thongTinDon.setLayout(new javax.swing.BoxLayout(pnl_thongTinDon, javax.swing.BoxLayout.Y_AXIS));

        pnl_tab2TinhTrang.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab2TinhTrang.setLayout(new javax.swing.BoxLayout(pnl_tab2TinhTrang, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tinhTrang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tinhTrang.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tinhTrang.setText("Tình trạng:");
        lbl_tinhTrang.setPreferredSize(new java.awt.Dimension(100, 30));
        pnl_tab2TinhTrang.add(lbl_tinhTrang);

        pnl_tab2TinhTrangRad.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab2TinhTrangRad.setLayout(new java.awt.GridLayout(1, 0));

        rad_chuaNhan.setText("Chưa nhận");
        pnl_tab2TinhTrangRad.add(rad_chuaNhan);

        rad_daNhan.setText("Đã nhận");
        rad_daNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad_daNhanActionPerformed(evt);
            }
        });
        pnl_tab2TinhTrangRad.add(rad_daNhan);

        pnl_tab2TinhTrang.add(pnl_tab2TinhTrangRad);

        pnl_thongTinDon.add(pnl_tab2TinhTrang);
        pnl_thongTinDon.add(filler10);

        pnl_tab2NgayNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab2NgayNhap.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_tab2NgayNhap.setLayout(new javax.swing.BoxLayout(pnl_tab2NgayNhap, javax.swing.BoxLayout.X_AXIS));

        lbl_ngayNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ngayNhap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ngayNhap.setText("Ngày nhập:");
        lbl_ngayNhap.setMinimumSize(new java.awt.Dimension(87, 16));
        lbl_ngayNhap.setPreferredSize(new java.awt.Dimension(100, 30));
        pnl_tab2NgayNhap.add(lbl_ngayNhap);

        txt_tab2NgayNhap.setEditable(false);
        txt_tab2NgayNhap.setColumns(30);
        pnl_tab2NgayNhap.add(txt_tab2NgayNhap);

        pnl_thongTinDon.add(pnl_tab2NgayNhap);
        pnl_thongTinDon.add(filler9);

        pnl_tab2NhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab2NhaCungCap.setLayout(new javax.swing.BoxLayout(pnl_tab2NhaCungCap, javax.swing.BoxLayout.LINE_AXIS));

        lbl_nhaCungCap1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nhaCungCap1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_nhaCungCap1.setText("Nhà cung cấp:");
        lbl_nhaCungCap1.setMinimumSize(new java.awt.Dimension(87, 16));
        lbl_nhaCungCap1.setPreferredSize(new java.awt.Dimension(100, 30));
        pnl_tab2NhaCungCap.add(lbl_nhaCungCap1);

        txt_tab2NCC.setEditable(false);
        pnl_tab2NhaCungCap.add(txt_tab2NCC);

        pnl_thongTinDon.add(pnl_tab2NhaCungCap);

        pnl_tab3GhiChu.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab3GhiChu.setPreferredSize(new java.awt.Dimension(0, 30));
        pnl_tab3GhiChu.setLayout(new java.awt.BorderLayout());

        lbl_ghiChuDonNhap1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ghiChuDonNhap1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_ghiChuDonNhap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_ghiChuDonNhap1.setText("Ghi chú:");
        lbl_ghiChuDonNhap1.setToolTipText("");
        lbl_ghiChuDonNhap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl_ghiChuDonNhap1.setName(""); // NOI18N
        lbl_ghiChuDonNhap1.setPreferredSize(new java.awt.Dimension(100, 30));
        pnl_tab3GhiChu.add(lbl_ghiChuDonNhap1, java.awt.BorderLayout.CENTER);

        pnl_thongTinDon.add(pnl_tab3GhiChu);

        txa_tab2GhiChuDonNhap.setEditable(false);
        txa_tab2GhiChuDonNhap.setColumns(20);
        txa_tab2GhiChuDonNhap.setRows(5);
        scr_5.setViewportView(txa_tab2GhiChuDonNhap);

        pnl_thongTinDon.add(scr_5);
        pnl_thongTinDon.add(filler7);

        pnl_tab2TongTien.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab2TongTien.setLayout(new javax.swing.BoxLayout(pnl_tab2TongTien, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tongTien.setBackground(new java.awt.Color(255, 255, 255));
        lbl_tongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_tongTien.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tongTien.setText("Tổng:");
        lbl_tongTien.setMaximumSize(new java.awt.Dimension(50, 50));
        lbl_tongTien.setMinimumSize(new java.awt.Dimension(50, 50));
        lbl_tongTien.setPreferredSize(new java.awt.Dimension(100, 40));
        pnl_tab2TongTien.add(lbl_tongTien);

        txt_tab2TongTien.setEditable(false);
        txt_tab2TongTien.setBackground(new java.awt.Color(255, 255, 255));
        txt_tab2TongTien.setColumns(20);
        txt_tab2TongTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt_tab2TongTien.setForeground(new java.awt.Color(65, 165, 238));
        txt_tab2TongTien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tab2TongTien.setBorder(null);
        pnl_tab2TongTien.add(txt_tab2TongTien);

        pnl_thongTinDon.add(pnl_tab2TongTien);

        pnl_chiTietDonNhap.add(pnl_thongTinDon, java.awt.BorderLayout.SOUTH);

        pnl_donDatHang.add(pnl_chiTietDonNhap);

        tab_donHang.addTab("Thông tin đơn nhập hàng", pnl_donDatHang);

        pnl_hoaDon.setLayout(new java.awt.BorderLayout());
        pnl_hoaDon.add(filler8, java.awt.BorderLayout.PAGE_END);

        pnl_timKiemHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_timKiemHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_timKiemHoaDon.setMinimumSize(new java.awt.Dimension(693, 8));
        pnl_timKiemHoaDon.setPreferredSize(new java.awt.Dimension(0, 90));
        pnl_timKiemHoaDon.setLayout(new javax.swing.BoxLayout(pnl_timKiemHoaDon, javax.swing.BoxLayout.LINE_AXIS));

        pnl_tab3Search.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab3Search.setLayout(new javax.swing.BoxLayout(pnl_tab3Search, javax.swing.BoxLayout.Y_AXIS));

        pnl_tab3SearchB1.setPreferredSize(new java.awt.Dimension(600, 25));
        pnl_tab3SearchB1.setLayout(new java.awt.GridLayout(1, 0));

        pnl_timKiemTheoMa.setBackground(new java.awt.Color(255, 255, 255));
        pnl_timKiemTheoMa.setLayout(new javax.swing.BoxLayout(pnl_timKiemTheoMa, javax.swing.BoxLayout.LINE_AXIS));
        pnl_timKiemTheoMa.add(filler18);

        lbl_timKiemTheoMaKhachHang.setText("Số điện thoại:");
        pnl_timKiemTheoMa.add(lbl_timKiemTheoMaKhachHang);
        pnl_timKiemTheoMa.add(filler25);

        txt_tab3SDT.setColumns(12);
        pnl_timKiemTheoMa.add(txt_tab3SDT);
        pnl_timKiemTheoMa.add(filler11);

        pnl_tab3SearchB1.add(pnl_timKiemTheoMa);

        pnl_timKiemTheoNgay.setBackground(new java.awt.Color(255, 255, 255));
        pnl_timKiemTheoNgay.setPreferredSize(new java.awt.Dimension(600, 22));
        pnl_timKiemTheoNgay.setLayout(new javax.swing.BoxLayout(pnl_timKiemTheoNgay, javax.swing.BoxLayout.LINE_AXIS));
        pnl_timKiemTheoNgay.add(filler15);

        lbl_timTuNgay.setText("Ngày lập:");
        lbl_timTuNgay.setPreferredSize(new java.awt.Dimension(80, 16));
        pnl_timKiemTheoNgay.add(lbl_timTuNgay);

        jdate_tab3TuNgay.setLocale(new Locale("vi","VN"));
        pnl_timKiemTheoNgay.add(jdate_tab3TuNgay);
        pnl_timKiemTheoNgay.add(filler27);

        lbl_timDenNgay.setText("đến");
        lbl_timDenNgay.setPreferredSize(new java.awt.Dimension(53, 16));
        pnl_timKiemTheoNgay.add(lbl_timDenNgay);

        jdate_tab3DenNgay.setLocale(new Locale("vi","VN"));
        pnl_timKiemTheoNgay.add(jdate_tab3DenNgay);
        pnl_timKiemTheoNgay.add(filler14);

        pnl_tab3SearchB1.add(pnl_timKiemTheoNgay);
        pnl_timKiemTheoNgay.getAccessibleContext().setAccessibleName("");
        pnl_timKiemTheoNgay.getAccessibleContext().setAccessibleDescription("");

        pnl_tab3Search.add(pnl_tab3SearchB1);
        pnl_tab3Search.add(filler6);

        pnl_tab3SearchB2.setPreferredSize(new java.awt.Dimension(600, 25));
        pnl_tab3SearchB2.setLayout(new java.awt.GridLayout(1, 0));

        pnl_timTheoNhaVienThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_timTheoNhaVienThanhToan.setLayout(new javax.swing.BoxLayout(pnl_timTheoNhaVienThanhToan, javax.swing.BoxLayout.LINE_AXIS));
        pnl_timTheoNhaVienThanhToan.add(filler26);

        lbl_maNhanVien.setText("Mã nhân viên:");
        pnl_timTheoNhaVienThanhToan.add(lbl_maNhanVien);
        pnl_timTheoNhaVienThanhToan.add(filler17);

        txt_tab3MaNhanVien.setColumns(12);
        pnl_timTheoNhaVienThanhToan.add(txt_tab3MaNhanVien);
        pnl_timTheoNhaVienThanhToan.add(filler12);

        pnl_tab3SearchB2.add(pnl_timTheoNhaVienThanhToan);

        pnl_timTheoGiaTien.setBackground(new java.awt.Color(255, 255, 255));
        pnl_timTheoGiaTien.setLayout(new javax.swing.BoxLayout(pnl_timTheoGiaTien, javax.swing.BoxLayout.LINE_AXIS));
        pnl_timTheoGiaTien.add(filler16);

        lbl_giaTu.setText("Tổng hóa đơn");
        lbl_giaTu.setPreferredSize(new java.awt.Dimension(80, 16));
        pnl_timTheoGiaTien.add(lbl_giaTu);
        pnl_timTheoGiaTien.add(txt_tab3GiaTu);
        pnl_timTheoGiaTien.add(filler28);

        lbl_giaDen.setText("đến:");
        lbl_giaDen.setPreferredSize(new java.awt.Dimension(53, 16));
        pnl_timTheoGiaTien.add(lbl_giaDen);
        pnl_timTheoGiaTien.add(txt_tab3GiaDen);
        pnl_timTheoGiaTien.add(filler13);

        pnl_tab3SearchB2.add(pnl_timTheoGiaTien);

        pnl_tab3Search.add(pnl_tab3SearchB2);

        pnl_timKiemHoaDon.add(pnl_tab3Search);

        pnl_tab3SearchControl.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tab3SearchControl.setMaximumSize(new java.awt.Dimension(10000, 32767));
        pnl_tab3SearchControl.setMinimumSize(new java.awt.Dimension(25, 50));
        pnl_tab3SearchControl.setPreferredSize(new java.awt.Dimension(0, 70));
        pnl_tab3SearchControl.setLayout(new java.awt.GridLayout(1, 0));

        btn_tab3Search.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_tab3Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_tab3Search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tab3Search.setMaximumSize(new java.awt.Dimension(75, 50));
        btn_tab3Search.setMinimumSize(new java.awt.Dimension(75, 50));
        btn_tab3Search.setPreferredSize(new java.awt.Dimension(10, 20));
        btn_tab3Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab3SearchActionPerformed(evt);
            }
        });
        pnl_tab3SearchControl.add(btn_tab3Search);

        btn_tab3Reset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_tab3Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartReset.png"))); // NOI18N
        btn_tab3Reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tab3Reset.setMaximumSize(new java.awt.Dimension(75, 50));
        btn_tab3Reset.setMinimumSize(new java.awt.Dimension(75, 50));
        btn_tab3Reset.setPreferredSize(new java.awt.Dimension(30, 40));
        btn_tab3Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab3ResetActionPerformed(evt);
            }
        });
        pnl_tab3SearchControl.add(btn_tab3Reset);

        pnl_timKiemHoaDon.add(pnl_tab3SearchControl);

        pnl_hoaDon.add(pnl_timKiemHoaDon, java.awt.BorderLayout.NORTH);

        pnl_center.setBackground(new java.awt.Color(255, 255, 255));
        pnl_center.setLayout(new java.awt.GridLayout(1, 2));

        scr_7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N

        tbl_tab3DanhSachHoaDon.setModel(tblModel_tab3DanhSachHoaDon = new DefaultTableModel(new String[]{"Mã hóa đơn", "Nhân Viên", "Khách Hàng", "Ngày", "Hình Thức Thanh Toán", "Tổng"},0));
        tbl_tab3DanhSachHoaDon.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_tab3DanhSachHoaDon.setFocusCycleRoot(true);
        tbl_tab3DanhSachHoaDon.setRowHeight(30);
        tbl_tab3DanhSachHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbl_tab3DanhSachHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_tab3DanhSachHoaDon.setShowGrid(true);
        tbl_tab3DanhSachHoaDon.setShowVerticalLines(false);
        tbl_tab3DanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tab3DanhSachHoaDonMouseClicked(evt);
            }
        });
        scr_7.setViewportView(tbl_tab3DanhSachHoaDon);

        pnl_center.add(scr_7);

        pnl_thongTinHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_thongTinHoaDon.setLayout(new java.awt.BorderLayout());

        src_8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        src_8.setPreferredSize(new java.awt.Dimension(500, 427));

        tbl_tab3ChiTietHoaDon.setModel(tblModel_tab3ChiTietHoaDon = new DefaultTableModel(new String[]{"Mã", "Tên", "Nhóm hàng", "Số lượng", "Đơn giá", "Tổng"
        }, 0));
        tbl_tab3ChiTietHoaDon.setDoubleBuffered(true);
        tbl_tab3ChiTietHoaDon.setRowHeight(30);
        tbl_tab3ChiTietHoaDon.setShowGrid(true);
        tbl_tab3ChiTietHoaDon.setShowVerticalLines(false);
        src_8.setViewportView(tbl_tab3ChiTietHoaDon);
        if (tbl_tab3ChiTietHoaDon.getColumnModel().getColumnCount() > 0) {
            tbl_tab3ChiTietHoaDon.getColumnModel().getColumn(0).setResizable(false);
        }

        pnl_thongTinHoaDon.add(src_8, java.awt.BorderLayout.CENTER);

        pnl_thongTinChiTietDon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_thongTinChiTietDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_thongTinChiTietDon.setLayout(new javax.swing.BoxLayout(pnl_thongTinChiTietDon, javax.swing.BoxLayout.Y_AXIS));

        b_tongTien1.setBackground(new java.awt.Color(255, 255, 255));
        b_tongTien1.setPreferredSize(new java.awt.Dimension(288, 30));
        b_tongTien1.setLayout(new javax.swing.BoxLayout(b_tongTien1, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tab3KhachHang.setBackground(new java.awt.Color(102, 102, 102));
        lbl_tab3KhachHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tab3KhachHang.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tab3KhachHang.setText("Khách hàng:");
        lbl_tab3KhachHang.setPreferredSize(new java.awt.Dimension(70, 16));
        b_tongTien1.add(lbl_tab3KhachHang);
        b_tongTien1.add(filler19);

        txt_tab3KhachHang.setEditable(false);
        txt_tab3KhachHang.setColumns(20);
        txt_tab3KhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tab3KhachHangActionPerformed(evt);
            }
        });
        b_tongTien1.add(txt_tab3KhachHang);
        b_tongTien1.add(filler21);

        lbl_tab3SDT.setBackground(new java.awt.Color(102, 102, 102));
        lbl_tab3SDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tab3SDT.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tab3SDT.setText("Số điện thoại");
        lbl_tab3SDT.setPreferredSize(new java.awt.Dimension(75, 16));
        b_tongTien1.add(lbl_tab3SDT);
        b_tongTien1.add(filler20);

        txt_tab3SoDienThoai.setEditable(false);
        txt_tab3SoDienThoai.setColumns(10);
        b_tongTien1.add(txt_tab3SoDienThoai);

        pnl_thongTinChiTietDon.add(b_tongTien1);
        pnl_thongTinChiTietDon.add(filler1);

        b_ngayNhap1.setBackground(new java.awt.Color(255, 255, 255));
        b_ngayNhap1.setPreferredSize(new java.awt.Dimension(144, 30));
        b_ngayNhap1.setLayout(new javax.swing.BoxLayout(b_ngayNhap1, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tab3NgayNhap.setBackground(new java.awt.Color(102, 102, 102));
        lbl_tab3NgayNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tab3NgayNhap.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tab3NgayNhap.setText("Ngày lập:");
        lbl_tab3NgayNhap.setPreferredSize(new java.awt.Dimension(70, 16));
        b_ngayNhap1.add(lbl_tab3NgayNhap);
        b_ngayNhap1.add(filler23);

        txt_tab3NgayLap.setEditable(false);
        txt_tab3NgayLap.setColumns(40);
        b_ngayNhap1.add(txt_tab3NgayLap);

        pnl_thongTinChiTietDon.add(b_ngayNhap1);
        pnl_thongTinChiTietDon.add(filler4);

        b_tinhTrang1.setBackground(new java.awt.Color(255, 255, 255));
        b_tinhTrang1.setPreferredSize(new java.awt.Dimension(144, 30));
        b_tinhTrang1.setLayout(new javax.swing.BoxLayout(b_tinhTrang1, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tab3TinhTrang.setBackground(new java.awt.Color(102, 102, 102));
        lbl_tab3TinhTrang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tab3TinhTrang.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tab3TinhTrang.setText("Nhân viên:");
        lbl_tab3TinhTrang.setPreferredSize(new java.awt.Dimension(70, 16));
        b_tinhTrang1.add(lbl_tab3TinhTrang);
        b_tinhTrang1.add(filler24);

        txt_tab3NhanVien.setEditable(false);
        txt_tab3NhanVien.setColumns(40);
        b_tinhTrang1.add(txt_tab3NhanVien);

        pnl_thongTinChiTietDon.add(b_tinhTrang1);
        pnl_thongTinChiTietDon.add(filler5);

        b_tongTien2.setBackground(new java.awt.Color(255, 255, 255));
        b_tongTien2.setForeground(new java.awt.Color(255, 255, 255));
        b_tongTien2.setEnabled(false);
        b_tongTien2.setFocusCycleRoot(true);
        b_tongTien2.setPreferredSize(new java.awt.Dimension(144, 40));
        b_tongTien2.setRequestFocusEnabled(false);
        b_tongTien2.setLayout(new javax.swing.BoxLayout(b_tongTien2, javax.swing.BoxLayout.LINE_AXIS));

        lbl_tongTien1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_tongTien1.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tongTien1.setText("Tổng:");
        lbl_tongTien1.setPreferredSize(new java.awt.Dimension(80, 16));
        b_tongTien2.add(lbl_tongTien1);

        txt_tab3TongTien.setEditable(false);
        txt_tab3TongTien.setBackground(new java.awt.Color(255, 255, 255));
        txt_tab3TongTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt_tab3TongTien.setForeground(new java.awt.Color(65, 165, 238));
        txt_tab3TongTien.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt_tab3TongTien.setBorder(null);
        txt_tab3TongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tab3TongTienActionPerformed(evt);
            }
        });
        b_tongTien2.add(txt_tab3TongTien);

        pnl_thongTinChiTietDon.add(b_tongTien2);

        pnl_thongTinHoaDon.add(pnl_thongTinChiTietDon, java.awt.BorderLayout.SOUTH);

        pnl_center.add(pnl_thongTinHoaDon);

        pnl_hoaDon.add(pnl_center, java.awt.BorderLayout.CENTER);

        tab_donHang.addTab("Thông tin hóa đơn bán hàng", pnl_hoaDon);

        add(tab_donHang, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tab3TongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tab3TongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tab3TongTienActionPerformed

    private void txt_tab3KhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tab3KhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tab3KhachHangActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        search();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_tab1SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tab1SearchKeyPressed
        if (evt.getKeyCode() == 10)
            search();
    }//GEN-LAST:event_txt_tab1SearchKeyPressed

    private void btn_tab1ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab1ResetActionPerformed
        txt_tab1Search.setText("");
        search();
        txt_tab1Search.setText("Mã sản phẩm");
        txt_tab1Search.setForeground(new Color(153, 153, 153));
    }//GEN-LAST:event_btn_tab1ResetActionPerformed

    public void search() {
        String search = txt_tab1Search.getText().trim();
        if (search.length() > 0) {
            renderTab1DanhSachSanPham(sanPham_bus.getSanPhamTheoMa(search));
        } else {
            renderAllTab1DanhSachSanPham();
        }
    }

    public void themHang() {
        int row = tbl_tab1DanhSachHangHoa.getSelectedRow();

        if (row != -1) {
            int soLuong = 0;

            try {
                soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Số lượng nhập:", 1));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng không đúng");
                return;
            }

            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải > 0");
            }

            String maSP = tbl_tab1DanhSachHangHoa.getValueAt(row, 0).toString();
            SanPham sp = sanPham_bus.getSanPhamTheoMa(maSP).get(0);
            ChiTietDonNhap ct = new ChiTietDonNhap(sp, null, soLuong, soLuong * sp.getGiaNhap());
            if (!gioHang.contains(ct)) {
                gioHang.add(ct);
            } else {
                ChiTietDonNhap previous = gioHang.get(gioHang.indexOf(ct));
                previous.setSoLuong(previous.getSoLuong() + soLuong);
            }
            renderTab1CartTable();
        }
    }
    private void btn_themHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themHangActionPerformed
        themHang();
    }//GEN-LAST:event_btn_themHangActionPerformed

    public void xoaHang() {
        int row = tbl_tab1ChiTietDonNhap.getSelectedRow();

        if (row != -1) {

            String maSP = tbl_tab1ChiTietDonNhap.getValueAt(row, 0).toString();

            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sản phẩm " + maSP + " ra khỏi đơn nhập?", "Xóa sản phẩm khỏi đơn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                gioHang.remove(new ChiTietDonNhap(new SanPham(maSP), null, 0, 0));
                renderTab1CartTable();
            }
        }
    }
    private void btn_xoaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaHangActionPerformed
        xoaHang();
    }//GEN-LAST:event_btn_xoaHangActionPerformed

    private void btn_tab3SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab3SearchActionPerformed
// TODO add your handling code here:
        if (validateFields()) {
            String manv = txt_tab3MaNhanVien.getText();
            String sdt = txt_tab3SDT.getText();
            String giaTu = txt_tab3GiaTu.getText();
            String giaDen = txt_tab3GiaDen.getText();
            Date begin = jdate_tab3TuNgay.getDate();
            LocalDate tuNgay = begin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date end = jdate_tab3DenNgay.getDate();
            LocalDate denNgay = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            ArrayList<HoaDon> list = hoaDon_bus.getHoaDonTheoDieuKien(manv, sdt, giaTu, giaDen, tuNgay, denNgay);

            renderTab3DanhSachHoaDon(list);
        }

    }//GEN-LAST:event_btn_tab3SearchActionPerformed

    private void btn_tab3ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab3ResetActionPerformed
        // TODO add your handling code here:
        txt_tab3GiaDen.setText("");
        txt_tab3GiaDen.setText("");
        txt_tab3SDT.setText("");
        txt_tab3MaNhanVien.setText("");
        // Thiết lập giá trị mặc định cho txt_tab3GiaTu
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        jdate_tab3TuNgay.setDate(cal.getTime());

// Thiết lập giá trị mặc định cho txt_tab3GiaDen
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        jdate_tab3DenNgay.setDate(cal.getTime());

        renderTab3DanhSachHoaDon(hoaDon_bus.getAllHoaDon());
    }//GEN-LAST:event_btn_tab3ResetActionPerformed

    public void reset() {
        gioHang = new ArrayList<>();
        renderTab1CartTable();
        cbo_tab1NhaCungCap.setSelectedIndex(0);
        txa_tab1GhiChuDonNhap.setText("");
        txt_tab1TongTien.setText("");
    }
    private void btn_tab1HuyDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab1HuyDonActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy đơn nhập hàng này không?", "Hủy đơn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            reset();
        }
    }//GEN-LAST:event_btn_tab1HuyDonActionPerformed

    private void btn_tab1NhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab1NhapHangActionPerformed
        if (gioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa thêm hàng hóa nào!");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Hoàn tất tạo đơn hàng?", "Tạo đơn hàng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String maDon = donNhap_bus.sinhMa();
            String maNCC = cbo_tab1NhaCungCap.getSelectedItem().toString().substring(0, 5);
            String ghiChu = txa_tab1GhiChuDonNhap.getText().trim();

            DonNhapHang donNhap = new DonNhapHang(maDon, LocalDate.now(), ghiChu, false, new KhoHang("KHO01"), gioHang, new NhaCungCap(maNCC), this.nhanVien, tongTien);

            if (donNhap_bus.themDonNhapHang(donNhap)) {
                JOptionPane.showMessageDialog(this, "Tạo đơn nhập thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                renderAllTab2DanhSachDonNhap();
                reset();
            } else {
                JOptionPane.showMessageDialog(this, "Tạo đơn nhập thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_tab1NhapHangActionPerformed

    private void rad_daNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad_daNhanActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Đơn hàng này đã được giao đến kho?", "Xác nhận đã nhận hàng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int row = tbl_tab2DanhSachDonNhap.getSelectedRow();
            if (row != -1) {
                String maDon = tbl_tab2DanhSachDonNhap.getValueAt(row, 0).toString();
                donNhap_bus.capNhatGiaoDonThanhCong(maDon);
                rad_chuaNhan.setEnabled(false);
            }
        } else {
            rad_chuaNhan.setSelected(true);
        }
    }//GEN-LAST:event_rad_daNhanActionPerformed

    private void tbl_tab1DanhSachHangHoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tab1DanhSachHangHoaMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            themHang();
        }
    }//GEN-LAST:event_tbl_tab1DanhSachHangHoaMouseClicked

    private void tbl_tab1ChiTietDonNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tab1ChiTietDonNhapMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            int row = tbl_tab1ChiTietDonNhap.getSelectedRow();
            ChiTietDonNhap currentItem = gioHang.get(row);
            try {
                int soLuongMoi = Integer.parseInt(JOptionPane.showInputDialog(this, "Số lượng", currentItem.getSoLuong()));
                if (soLuongMoi > 0) {
                    currentItem.setSoLuong(soLuongMoi);
                    renderTab1CartTable();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
            }

        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            xoaHang();
        }
    }//GEN-LAST:event_tbl_tab1ChiTietDonNhapMouseClicked

    private void tbl_tab3DanhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tab3DanhSachHoaDonMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            int row = tbl_tab3DanhSachHoaDon.getSelectedRow();
            String ma = tbl_tab3DanhSachHoaDon.getValueAt(row, 0).toString();
            HoaDon temp = hoaDon_bus.getHoaDonTheoMa(ma).get(0);
            new Frame_HoaDon(temp).setVisible(true);
        }
    }//GEN-LAST:event_tbl_tab3DanhSachHoaDonMouseClicked

    private void txt_tab1SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tab1SearchFocusGained
        if (txt_tab1Search.getText().equals("Mã sản phẩm")) {
            txt_tab1Search.setText("");
            txt_tab1Search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txt_tab1SearchFocusGained

    private void txt_tab1SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tab1SearchFocusLost
        if (txt_tab1Search.getText().trim().equals("")) {
            txt_tab1Search.setText("Mã sản phẩm");
            txt_tab1Search.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txt_tab1SearchFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel b_ngayNhap1;
    private javax.swing.JPanel b_tinhTrang1;
    private javax.swing.JPanel b_tongTien1;
    private javax.swing.JPanel b_tongTien2;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_tab1HuyDon;
    private javax.swing.JButton btn_tab1NhapHang;
    private javax.swing.JButton btn_tab1Reset;
    private javax.swing.JButton btn_tab3Reset;
    private javax.swing.JButton btn_tab3Search;
    private javax.swing.JButton btn_themHang;
    private javax.swing.JButton btn_xoaHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_tab1NhaCungCap;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler25;
    private javax.swing.Box.Filler filler26;
    private javax.swing.Box.Filler filler27;
    private javax.swing.Box.Filler filler28;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private com.toedter.calendar.JDateChooser jdate_tab3DenNgay;
    private com.toedter.calendar.JDateChooser jdate_tab3TuNgay;
    private javax.swing.JLabel lbl_ghiChuDonNhap;
    private javax.swing.JLabel lbl_ghiChuDonNhap1;
    private javax.swing.JLabel lbl_giaDen;
    private javax.swing.JLabel lbl_giaTu;
    private javax.swing.JLabel lbl_maNhanVien;
    private javax.swing.JLabel lbl_ngayNhap;
    private javax.swing.JLabel lbl_nhaCungCap;
    private javax.swing.JLabel lbl_nhaCungCap1;
    private javax.swing.JLabel lbl_tab3KhachHang;
    private javax.swing.JLabel lbl_tab3NgayNhap;
    private javax.swing.JLabel lbl_tab3SDT;
    private javax.swing.JLabel lbl_tab3TinhTrang;
    private javax.swing.JLabel lbl_timDenNgay;
    private javax.swing.JLabel lbl_timKiemTheoMaKhachHang;
    private javax.swing.JLabel lbl_timTuNgay;
    private javax.swing.JLabel lbl_tinhTrang;
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
    private javax.swing.JPanel pnl_search;
    private javax.swing.JPanel pnl_tab1Control;
    private javax.swing.JPanel pnl_tab1GhiChu;
    private javax.swing.JPanel pnl_tab1GhiChuText;
    private javax.swing.JPanel pnl_tab1NhaCungCap;
    private javax.swing.JPanel pnl_tab2NgayNhap;
    private javax.swing.JPanel pnl_tab2NhaCungCap;
    private javax.swing.JPanel pnl_tab2TinhTrang;
    private javax.swing.JPanel pnl_tab2TinhTrangRad;
    private javax.swing.JPanel pnl_tab2TongTien;
    private javax.swing.JPanel pnl_tab3GhiChu;
    private javax.swing.JPanel pnl_tab3Search;
    private javax.swing.JPanel pnl_tab3SearchB1;
    private javax.swing.JPanel pnl_tab3SearchB2;
    private javax.swing.JPanel pnl_tab3SearchControl;
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
    private javax.swing.JRadioButton rad_chuaNhan;
    private javax.swing.JRadioButton rad_daNhan;
    private javax.swing.JScrollPane scr_1;
    private javax.swing.JScrollPane scr_2;
    private javax.swing.JScrollPane scr_3;
    private javax.swing.JScrollPane scr_4;
    private javax.swing.JScrollPane scr_5;
    private javax.swing.JScrollPane scr_6;
    private javax.swing.JScrollPane scr_7;
    private javax.swing.JScrollPane src_8;
    private javax.swing.JTabbedPane tab_donHang;
    private javax.swing.JTable tbl_tab1ChiTietDonNhap;
    private javax.swing.JTable tbl_tab1DanhSachHangHoa;
    private javax.swing.JTable tbl_tab2ChiTietDonNhap;
    private javax.swing.JTable tbl_tab2DanhSachDonNhap;
    private javax.swing.JTable tbl_tab3ChiTietHoaDon;
    private javax.swing.JTable tbl_tab3DanhSachHoaDon;
    private javax.swing.JTextArea txa_tab1GhiChuDonNhap;
    private javax.swing.JTextArea txa_tab2GhiChuDonNhap;
    private javax.swing.JTextField txt_tab1Search;
    private javax.swing.JTextField txt_tab1TongTien;
    private javax.swing.JTextField txt_tab2NCC;
    private javax.swing.JTextField txt_tab2NgayNhap;
    private javax.swing.JTextField txt_tab2TongTien;
    private javax.swing.JTextField txt_tab3GiaDen;
    private javax.swing.JTextField txt_tab3GiaTu;
    private javax.swing.JTextField txt_tab3KhachHang;
    private javax.swing.JTextField txt_tab3MaNhanVien;
    private javax.swing.JTextField txt_tab3NgayLap;
    private javax.swing.JTextField txt_tab3NhanVien;
    private javax.swing.JTextField txt_tab3SDT;
    private javax.swing.JTextField txt_tab3SoDienThoai;
    private javax.swing.JTextField txt_tab3TongTien;
    // End of variables declaration//GEN-END:variables
}
