/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import bus.DiaChi_bus;
import bus.KhachHang_bus;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;
import entity.connguoi.KhachHang;
import entity.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class Panel_QuanLyKhachHang extends javax.swing.JPanel {
    private Frame_InputDiaChi frame_diaChi ;
    private KhachHang_bus KH_bus = new KhachHang_bus();
    private DiaChi_bus DC_bus = new DiaChi_bus();
    private ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
    private DefaultTableModel model_dsKhachHang;
    /**
     * Creates new form Panel_QuanLyKhachHang
     */
    public Panel_QuanLyKhachHang() {
        String col[] = {"Mã khách hàng","Họ tên","Điểm thành viên","Số điện thoại","Mã số thuế","Địa chỉ","Năm sinh","Giới tính","Email"};
        model_dsKhachHang= new DefaultTableModel(col,0);
        listKH = KH_bus.getAllKhachHang();
        initComponents();
        alterTable();
        
        tbl_dsKhachHang.getSelectionModel().addListSelectionListener((e)->{
            int row = tbl_dsKhachHang.getSelectedRow();
            if(row!=-1){
                txt_maKH.setText(model_dsKhachHang.getValueAt(row, 0).toString());
                txt_tenKH.setText(model_dsKhachHang.getValueAt(row, 1).toString());
                txt_email.setText(model_dsKhachHang.getValueAt(row, 8).toString());
                txt_soDT.setText(model_dsKhachHang.getValueAt(row, 3).toString());
                txt_hangThanhVien.setText(model_dsKhachHang.getValueAt(row, 2).toString());
                txt_diaChi.setText(model_dsKhachHang.getValueAt(row, 5).toString());
                if (txt_diaChi.getText().trim().length() > 0) {
                    String ma = (model_dsKhachHang.getValueAt(row, 0).toString());
                    String maDC = KH_bus.getMaDiaChi(ma);
                    DiaChi dc = DC_bus.getDiaChiTheoMa(maDC);
                    frame_diaChi = new Frame_InputDiaChi(this, dc);
                } else {
                    frame_diaChi = new Frame_InputDiaChi(this);
                }
                txt_maSoThue.setText(model_dsKhachHang.getValueAt(row, 4).toString());
                LocalDate ngaySinh = (LocalDate) model_dsKhachHang.getValueAt(row, 6);
                //convert LocalDate to Date
                txt_date.setDate(Date.from(ngaySinh.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                String gt = model_dsKhachHang.getValueAt(row, 7).toString();
                if(gt.equals("Nam"))
                    cmb_gioiTinh.setSelectedItem("Nam");
                else
                    cmb_gioiTinh.setSelectedItem("Nữ");
            }
           
        });
        
        renderAll();
        
        
        
        
    }
    public void alterTable(){
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);
        // set chieu dai cot
        tbl_dsKhachHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_dsKhachHang.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_dsKhachHang.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbl_dsKhachHang.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_dsKhachHang.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_dsKhachHang.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_dsKhachHang.getColumnModel().getColumn(5).setPreferredWidth(200);
        tbl_dsKhachHang.getColumnModel().getColumn(6).setPreferredWidth(100);
        tbl_dsKhachHang.getColumnModel().getColumn(7).setPreferredWidth(100);
        tbl_dsKhachHang.getColumnModel().getColumn(8).setPreferredWidth(200);
        // set canh le trai
        tbl_dsKhachHang.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
        tbl_dsKhachHang.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tbl_dsKhachHang.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
        tbl_dsKhachHang.getColumnModel().getColumn(6).setCellRenderer(rightAlign);
        
        // set khong chinh sua dong trong table
        tbl_dsKhachHang.setDefaultEditor(Object.class, null);
    }
    
    public void renderAll(){
        ArrayList<KhachHang> list = KH_bus.getAllKhachHang();
        renderListToTable(list);
    }
     public void renderListToTable(ArrayList<KhachHang> listKH) {
        model_dsKhachHang.setRowCount(0);
        for(KhachHang kh:listKH){
            String maST;
            if(kh.getMaSoThue()==null || kh.getMaSoThue().equalsIgnoreCase("NULL")){
                maST = "";
            }else
                maST=kh.getMaSoThue();
            String gioiTinh="";
            if(kh.isGioiTinh()){
                gioiTinh="Nam";
            }else{
                gioiTinh="Nữ";
            }
           model_dsKhachHang.addRow(new Object[] {kh.getMaKH(),kh.getHoTen(),kh.getDiemThanhVien(),kh.getSoDT(),maST,kh.getDiaChi(),kh.getNamSinh(),gioiTinh,kh.getEmail()});
        }
        
    }
     
     public void timKiem(){
        String soDT = txt_timKiem.getText().trim();
        if(soDT.length()>0){
            ArrayList<KhachHang> list = new ArrayList<KhachHang>();
            list = KH_bus.getKhachHangTheoSoDT(soDT);
            renderListToTable(list);
        }else
            renderAll();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_ttKhachHang = new javax.swing.JPanel();
        pnl_avata = new javax.swing.JPanel();
        lbl_img = new javax.swing.JLabel();
        pnl_ttKH = new javax.swing.JPanel();
        pnl_maKH = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_maKH = new javax.swing.JLabel();
        txt_maKH = new javax.swing.JTextField();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_tenKH = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_tenKH = new javax.swing.JLabel();
        txt_tenKH = new javax.swing.JTextField();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_hangThanhVien = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_hangThanhVien = new javax.swing.JLabel();
        txt_hangThanhVien = new javax.swing.JTextField();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_diaChi = new javax.swing.JPanel();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_diaChi = new javax.swing.JLabel();
        txt_diaChi = new javax.swing.JTextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_soDT = new javax.swing.JPanel();
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_soDT = new javax.swing.JLabel();
        txt_soDT = new javax.swing.JTextField();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_maSoThue = new javax.swing.JPanel();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_maSoThue = new javax.swing.JLabel();
        txt_maSoThue = new javax.swing.JTextField();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_email = new javax.swing.JPanel();
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_namSinh = new javax.swing.JPanel();
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        lbl_namSinh = new javax.swing.JLabel();
        txt_date = new com.toedter.calendar.JDateChooser();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(3, 0));
        lbl_gioiTinh = new javax.swing.JLabel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(10, 0));
        cmb_gioiTinh = new javax.swing.JComboBox<>();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_capNhat = new javax.swing.JPanel();
        btn_themKH = new javax.swing.JButton();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 10), new java.awt.Dimension(0, 32767));
        btn_capNhatKH = new javax.swing.JButton();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        pnl_xoaTrang = new javax.swing.JPanel();
        btn_xoaTrang = new javax.swing.JButton();
        pnl_dsKhachHang = new javax.swing.JPanel();
        scr_1 = new javax.swing.JScrollPane();
        tbl_dsKhachHang = new javax.swing.JTable();
        pnl_control = new javax.swing.JPanel();
        pnl_timKiem = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(32767, 0));
        txt_timKiem = new javax.swing.JTextField();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(32767, 0));
        btn_timKiem = new javax.swing.JButton();
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(5, 32767));
        btn_reset = new javax.swing.JButton();
        filler25 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnl_ttKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ttKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_ttKhachHang.setPreferredSize(new java.awt.Dimension(400, 300));
        pnl_ttKhachHang.setLayout(new java.awt.BorderLayout());

        pnl_avata.setBackground(new java.awt.Color(255, 255, 255));
        pnl_avata.setLayout(new java.awt.BorderLayout());

        lbl_img.setBackground(new java.awt.Color(255, 255, 255));
        lbl_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlynhanvien/user-4.png"))); // NOI18N
        pnl_avata.add(lbl_img, java.awt.BorderLayout.CENTER);

        pnl_ttKH.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ttKH.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl_ttKH.setPreferredSize(new java.awt.Dimension(338, 370));
        pnl_ttKH.setLayout(new javax.swing.BoxLayout(pnl_ttKH, javax.swing.BoxLayout.Y_AXIS));

        pnl_maKH.setBackground(new java.awt.Color(255, 255, 255));
        pnl_maKH.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_maKH.setLayout(new javax.swing.BoxLayout(pnl_maKH, javax.swing.BoxLayout.LINE_AXIS));
        pnl_maKH.add(filler1);

        lbl_maKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_maKH.setForeground(new java.awt.Color(102, 102, 102));
        lbl_maKH.setText("Mã khách hàng:");
        lbl_maKH.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_maKH.add(lbl_maKH);

        txt_maKH.setEditable(false);
        txt_maKH.setMinimumSize(new java.awt.Dimension(250, 30));
        txt_maKH.setPreferredSize(new java.awt.Dimension(250, 30));
        txt_maKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maKHActionPerformed(evt);
            }
        });
        pnl_maKH.add(txt_maKH);

        pnl_ttKH.add(pnl_maKH);

        filler8.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler8);

        pnl_tenKH.setBackground(new java.awt.Color(255, 255, 255));
        pnl_tenKH.setMinimumSize(new java.awt.Dimension(65, 30));
        pnl_tenKH.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_tenKH.setLayout(new javax.swing.BoxLayout(pnl_tenKH, javax.swing.BoxLayout.LINE_AXIS));
        pnl_tenKH.add(filler2);

        lbl_tenKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tenKH.setForeground(new java.awt.Color(102, 102, 102));
        lbl_tenKH.setText("Tên khách hàng: ");
        lbl_tenKH.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_tenKH.add(lbl_tenKH);

        txt_tenKH.setMinimumSize(new java.awt.Dimension(250, 30));
        txt_tenKH.setPreferredSize(new java.awt.Dimension(250, 30));
        pnl_tenKH.add(txt_tenKH);

        pnl_ttKH.add(pnl_tenKH);

        filler7.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler7);

        pnl_hangThanhVien.setBackground(new java.awt.Color(255, 255, 255));
        pnl_hangThanhVien.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_hangThanhVien.setLayout(new javax.swing.BoxLayout(pnl_hangThanhVien, javax.swing.BoxLayout.LINE_AXIS));
        pnl_hangThanhVien.add(filler3);

        lbl_hangThanhVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_hangThanhVien.setForeground(new java.awt.Color(102, 102, 102));
        lbl_hangThanhVien.setText("Điểm thành viên: ");
        lbl_hangThanhVien.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_hangThanhVien.add(lbl_hangThanhVien);

        txt_hangThanhVien.setEditable(false);
        txt_hangThanhVien.setMinimumSize(new java.awt.Dimension(250, 30));
        txt_hangThanhVien.setPreferredSize(new java.awt.Dimension(250, 30));
        pnl_hangThanhVien.add(txt_hangThanhVien);

        pnl_ttKH.add(pnl_hangThanhVien);

        filler17.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler17);

        pnl_diaChi.setBackground(new java.awt.Color(255, 255, 255));
        pnl_diaChi.setMinimumSize(new java.awt.Dimension(65, 30));
        pnl_diaChi.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_diaChi.setLayout(new javax.swing.BoxLayout(pnl_diaChi, javax.swing.BoxLayout.LINE_AXIS));
        pnl_diaChi.add(filler19);

        lbl_diaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_diaChi.setForeground(new java.awt.Color(102, 102, 102));
        lbl_diaChi.setText("Địa chỉ:");
        lbl_diaChi.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_diaChi.add(lbl_diaChi);

        txt_diaChi.setEditable(false);
        txt_diaChi.setMinimumSize(new java.awt.Dimension(250, 30));
        txt_diaChi.setPreferredSize(new java.awt.Dimension(250, 30));
        txt_diaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_diaChiMouseClicked(evt);
            }
        });
        pnl_diaChi.add(txt_diaChi);

        pnl_ttKH.add(pnl_diaChi);

        filler6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler6);

        pnl_soDT.setBackground(new java.awt.Color(255, 255, 255));
        pnl_soDT.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_soDT.setLayout(new javax.swing.BoxLayout(pnl_soDT, javax.swing.BoxLayout.LINE_AXIS));
        pnl_soDT.add(filler20);

        lbl_soDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_soDT.setForeground(new java.awt.Color(102, 102, 102));
        lbl_soDT.setText("Số điện thoại:");
        lbl_soDT.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_soDT.add(lbl_soDT);

        txt_soDT.setMinimumSize(new java.awt.Dimension(250, 30));
        txt_soDT.setPreferredSize(new java.awt.Dimension(250, 30));
        pnl_soDT.add(txt_soDT);

        pnl_ttKH.add(pnl_soDT);

        filler5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler5);

        pnl_maSoThue.setBackground(new java.awt.Color(255, 255, 255));
        pnl_maSoThue.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_maSoThue.setLayout(new javax.swing.BoxLayout(pnl_maSoThue, javax.swing.BoxLayout.LINE_AXIS));
        pnl_maSoThue.add(filler21);

        lbl_maSoThue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_maSoThue.setForeground(new java.awt.Color(102, 102, 102));
        lbl_maSoThue.setText("Mã số thuế: ");
        lbl_maSoThue.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_maSoThue.add(lbl_maSoThue);

        txt_maSoThue.setMinimumSize(new java.awt.Dimension(250, 30));
        txt_maSoThue.setPreferredSize(new java.awt.Dimension(250, 30));
        pnl_maSoThue.add(txt_maSoThue);

        pnl_ttKH.add(pnl_maSoThue);

        filler9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler9);

        pnl_email.setBackground(new java.awt.Color(255, 255, 255));
        pnl_email.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_email.setLayout(new javax.swing.BoxLayout(pnl_email, javax.swing.BoxLayout.LINE_AXIS));
        pnl_email.add(filler22);

        lbl_email.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_email.setForeground(new java.awt.Color(102, 102, 102));
        lbl_email.setText("Email: ");
        lbl_email.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_email.add(lbl_email);

        txt_email.setMinimumSize(new java.awt.Dimension(250, 30));
        txt_email.setPreferredSize(new java.awt.Dimension(250, 30));
        pnl_email.add(txt_email);

        pnl_ttKH.add(pnl_email);

        filler11.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler11);

        pnl_namSinh.setBackground(new java.awt.Color(255, 255, 255));
        pnl_namSinh.setPreferredSize(new java.awt.Dimension(180, 40));
        pnl_namSinh.setLayout(new javax.swing.BoxLayout(pnl_namSinh, javax.swing.BoxLayout.LINE_AXIS));
        pnl_namSinh.add(filler23);

        lbl_namSinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_namSinh.setForeground(new java.awt.Color(102, 102, 102));
        lbl_namSinh.setText("Năm sinh: ");
        lbl_namSinh.setPreferredSize(new java.awt.Dimension(110, 18));
        pnl_namSinh.add(lbl_namSinh);

        txt_date.setDate(new java.util.Date(1682490693000L));
        txt_date.setMaximumSize(new java.awt.Dimension(100000, 2147483647));
        txt_date.setMinimumSize(new java.awt.Dimension(100, 23));
        txt_date.setPreferredSize(new java.awt.Dimension(55, 30));
        txt_date.setLocale(new Locale("vi","VN"));
        pnl_namSinh.add(txt_date);
        pnl_namSinh.add(filler16);

        lbl_gioiTinh.setForeground(new java.awt.Color(102, 102, 102));
        lbl_gioiTinh.setText("Giới tính:");
        pnl_namSinh.add(lbl_gioiTinh);
        pnl_namSinh.add(filler15);

        cmb_gioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cmb_gioiTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmb_gioiTinh.setPreferredSize(new java.awt.Dimension(65, 30));
        pnl_namSinh.add(cmb_gioiTinh);

        pnl_ttKH.add(pnl_namSinh);

        filler10.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler10);

        pnl_capNhat.setBackground(new java.awt.Color(255, 255, 255));
        pnl_capNhat.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        pnl_capNhat.setMinimumSize(new java.awt.Dimension(65, 30));
        pnl_capNhat.setPreferredSize(new java.awt.Dimension(180, 50));
        pnl_capNhat.setLayout(new javax.swing.BoxLayout(pnl_capNhat, javax.swing.BoxLayout.LINE_AXIS));

        btn_themKH.setBackground(new java.awt.Color(65, 165, 238));
        btn_themKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_themKH.setForeground(new java.awt.Color(255, 255, 255));
        btn_themKH.setText("Thêm Khách Hàng");
        btn_themKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_themKH.setMaximumSize(new java.awt.Dimension(150, 50));
        btn_themKH.setPreferredSize(new java.awt.Dimension(190, 50));
        btn_themKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themKHActionPerformed(evt);
            }
        });
        pnl_capNhat.add(btn_themKH);

        filler12.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_capNhat.add(filler12);

        btn_capNhatKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_capNhatKH.setForeground(new java.awt.Color(102, 102, 102));
        btn_capNhatKH.setText("Cập Nhật Thông Tin");
        btn_capNhatKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_capNhatKH.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        btn_capNhatKH.setPreferredSize(new java.awt.Dimension(190, 50));
        btn_capNhatKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capNhatKHActionPerformed(evt);
            }
        });
        pnl_capNhat.add(btn_capNhatKH);

        pnl_ttKH.add(pnl_capNhat);

        filler18.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        pnl_ttKH.add(filler18);

        pnl_xoaTrang.setBackground(new java.awt.Color(255, 255, 255));
        pnl_xoaTrang.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        pnl_xoaTrang.setPreferredSize(new java.awt.Dimension(180, 30));
        pnl_xoaTrang.setLayout(new javax.swing.BoxLayout(pnl_xoaTrang, javax.swing.BoxLayout.LINE_AXIS));

        btn_xoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_xoaTrang.setForeground(new java.awt.Color(102, 102, 102));
        btn_xoaTrang.setText("Xóa trắng");
        btn_xoaTrang.setToolTipText("");
        btn_xoaTrang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_xoaTrang.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        btn_xoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaTrangActionPerformed(evt);
            }
        });
        pnl_xoaTrang.add(btn_xoaTrang);

        pnl_ttKH.add(pnl_xoaTrang);

        pnl_avata.add(pnl_ttKH, java.awt.BorderLayout.PAGE_END);

        pnl_ttKhachHang.add(pnl_avata, java.awt.BorderLayout.CENTER);

        add(pnl_ttKhachHang, java.awt.BorderLayout.EAST);

        pnl_dsKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnl_dsKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_dsKhachHang.setPreferredSize(new java.awt.Dimension(600, 370));
        pnl_dsKhachHang.setLayout(new java.awt.BorderLayout());

        scr_1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scr_1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbl_dsKhachHang.setModel(model_dsKhachHang);
        tbl_dsKhachHang.setRowHeight(30);
        tbl_dsKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_dsKhachHang.setShowGrid(true);
        tbl_dsKhachHang.setShowVerticalLines(false);
        scr_1.setViewportView(tbl_dsKhachHang);

        pnl_dsKhachHang.add(scr_1, java.awt.BorderLayout.CENTER);

        add(pnl_dsKhachHang, java.awt.BorderLayout.CENTER);

        pnl_control.setBackground(new java.awt.Color(255, 255, 255));
        pnl_control.setPreferredSize(new java.awt.Dimension(726, 50));
        pnl_control.setLayout(new javax.swing.BoxLayout(pnl_control, javax.swing.BoxLayout.LINE_AXIS));

        pnl_timKiem.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        pnl_timKiem.setPreferredSize(new java.awt.Dimension(500, 50));
        pnl_timKiem.setLayout(new javax.swing.BoxLayout(pnl_timKiem, javax.swing.BoxLayout.LINE_AXIS));
        pnl_timKiem.add(filler4);

        txt_timKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_timKiem.setForeground(new java.awt.Color(153, 153, 153));
        txt_timKiem.setText("Số điện thoại");
        txt_timKiem.setToolTipText("Nhập số điện thoại");
        txt_timKiem.setPreferredSize(new java.awt.Dimension(300, 40));
        txt_timKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusLost(evt);
            }
        });
        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyPressed(evt);
            }
        });
        pnl_timKiem.add(txt_timKiem);
        pnl_timKiem.add(filler13);

        btn_timKiem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_timKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banhang/cartSearch.png"))); // NOI18N
        btn_timKiem.setText("Tìm kiếm");
        btn_timKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_timKiem.setMaximumSize(new java.awt.Dimension(151, 340));
        btn_timKiem.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });
        pnl_timKiem.add(btn_timKiem);
        pnl_timKiem.add(filler24);

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
        pnl_timKiem.add(filler25);

        pnl_control.add(pnl_timKiem);

        add(pnl_control, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_maKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maKHActionPerformed

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        timKiem();
    }//GEN-LAST:event_btn_timKiemActionPerformed

    private void btn_xoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaTrangActionPerformed
        // TODO add your handling code here:
        txt_maKH.setText("");
        txt_tenKH.setText("");
        txt_email.setText("");
        txt_soDT.setText("");
        txt_hangThanhVien.setText("");
        txt_diaChi.setText("");
        cmb_gioiTinh.setSelectedIndex(0);
        txt_maSoThue.setText("");
    }//GEN-LAST:event_btn_xoaTrangActionPerformed

    public void showMessageFocus(String msg, JTextField txt) {
        JOptionPane.showMessageDialog(this, msg);
        txt.selectAll();
        txt.requestFocus();
    }
    
    private void btn_themKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themKHActionPerformed
        // TODO add your handling code here:
        String  maKH = KH_bus.sinhMa();
        String hoTen = txt_tenKH.getText();
        String email = txt_email.getText();
        String soDT = txt_soDT.getText();
        String maST = txt_maSoThue.getText();
        String gt =  cmb_gioiTinh.getSelectedItem().toString();
        
        if(!Pattern.matches("^\\p{L}+\\s+\\p{L}+.*$", hoTen)){
            showMessageFocus("Họ tên không hợp lệ", txt_tenKH);
            return;
        }
        if(txt_diaChi.getText().trim().length()<=0){
            showMessageFocus("Địa chỉ không được rỗng", txt_diaChi);
            return;
        }
        if(!Pattern.matches("0\\d{9}", soDT)){
            showMessageFocus("Số điện thoại không hợp lệ", txt_soDT);
            return;
        }
        if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)){
            showMessageFocus("Email không hợp lệ", txt_email);
            return;
        }
        
        
        if(maST.trim().length()<=0)
            maST = null;
        DiaChi dc = frame_diaChi.getDiaChi();
        boolean gioiTinh;
        if(gt.equals("Nữ"))
            gioiTinh=false;
        else
            gioiTinh=true;
        Date ns = txt_date.getDate();
        LocalDate ngaySinh = ns.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if((LocalDate.now().getYear()-ngaySinh.getYear())<3){
            JOptionPane.showMessageDialog(this, "Khách hàng phải trên 3 tuổi");
            return;
        }
        try {
            KhachHang kh = new KhachHang(maKH, maST, hoTen, soDT, email, ngaySinh, dc, gioiTinh, 0);
            int count=0;
            for(KhachHang k:listKH){
                if(kh.getSoDT().equals(k.getSoDT()))
                {
                    JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại !");
                    count++;
                }
            }
            if(count==0 && KH_bus.themKhachHang(kh)){
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
                txt_maKH.setText("");
                txt_tenKH.setText("");
                txt_email.setText("");
                txt_soDT.setText("");
                txt_hangThanhVien.setText("");
                txt_diaChi.setText("");
                cmb_gioiTinh.setSelectedIndex(0);
                txt_maSoThue.setText("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        renderAll();
    }//GEN-LAST:event_btn_themKHActionPerformed

    public void updateDiaChi(DiaChi dc){
        txt_diaChi.setText(dc.toString());
    }
    private void txt_diaChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_diaChiMouseClicked
        // TODO add your handling code here:
        if(txt_diaChi.getText().trim().length()>0){
               int row = tbl_dsKhachHang.getSelectedRow();
               if(row==-1)
                    return;
               String maKH = (model_dsKhachHang.getValueAt(row, 0).toString());
               String maDC = KH_bus.getMaDiaChi(maKH);
               DiaChi dc = DC_bus.getDiaChiTheoMa(maDC);
               frame_diaChi = new Frame_InputDiaChi(this,dc);
              
           }
           else{
               frame_diaChi = new Frame_InputDiaChi(this);
           }
           frame_diaChi.setVisible(true);
           frame_diaChi.getDiaChi();
    }//GEN-LAST:event_txt_diaChiMouseClicked

    private void txt_timKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyPressed
                // TODO add your handling code here:
        if(evt.getKeyCode()==10)
            timKiem();
    }//GEN-LAST:event_txt_timKiemKeyPressed

    private void btn_capNhatKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capNhatKHActionPerformed
        // TODO add your handling code here:
            int row = tbl_dsKhachHang.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(this, "Chọn khách hàng cần sửa !");
                return;
            }
            String  maKH = txt_maKH.getText();
            String hoTen = txt_tenKH.getText();
            String email = txt_email.getText();
            String soDT = txt_soDT.getText();
            String maST = txt_maSoThue.getText();
            String gt =  cmb_gioiTinh.getSelectedItem().toString();
            
            
            int diem = Integer.parseInt(txt_hangThanhVien.getText());
            
           
            
            if(!Pattern.matches("^\\p{L}+\\s+\\p{L}+.*$", hoTen)){
                showMessageFocus("Họ tên không hợp lệ", txt_tenKH);
                return;
            }
            if(txt_diaChi.getText().trim().length()<=0){
                showMessageFocus("Địa chỉ không được rỗng", txt_diaChi);
                return;
            }
            if(!Pattern.matches("0\\d{9}", soDT)){
                showMessageFocus("Số điện thoại không hợp lệ", txt_soDT);
                return;
            }
            if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)){
                showMessageFocus("Email không hợp lệ", txt_email);
                return;
            }
            
            
            DiaChi dc = frame_diaChi.getDiaChi();
            dc.setMaDiaChi(KH_bus.getMaDiaChi(maKH));
            
            boolean gioiTinh;
            if(gt.equals("Nữ"))
                gioiTinh=false;
            else
                gioiTinh=true;
            Date ns = txt_date.getDate();
            LocalDate ngaySinh = ns.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();    
        try {
            KhachHang kh = new KhachHang(maKH, maST, hoTen, soDT, email, ngaySinh, dc, gioiTinh, diem);
            if(KH_bus.capNhatKhachHang(maKH, kh))
                JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
            else
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại !");
            renderAll();
            
            
                    
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_capNhatKHActionPerformed

    private void txt_timKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusGained
        if (txt_timKiem.getText().equals("Số điện thoại")) {
            txt_timKiem.setText("");
            txt_timKiem.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txt_timKiemFocusGained

    private void txt_timKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusLost
        if (txt_timKiem.getText().trim().equals("")) {
            txt_timKiem.setText("Số điện thoại");
            txt_timKiem.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_timKiemFocusLost

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        renderAll();
        txt_timKiem.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_capNhatKH;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_themKH;
    private javax.swing.JButton btn_timKiem;
    private javax.swing.JButton btn_xoaTrang;
    private javax.swing.JComboBox<String> cmb_gioiTinh;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler25;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel lbl_diaChi;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_gioiTinh;
    private javax.swing.JLabel lbl_hangThanhVien;
    private javax.swing.JLabel lbl_img;
    private javax.swing.JLabel lbl_maKH;
    private javax.swing.JLabel lbl_maSoThue;
    private javax.swing.JLabel lbl_namSinh;
    private javax.swing.JLabel lbl_soDT;
    private javax.swing.JLabel lbl_tenKH;
    private javax.swing.JPanel pnl_avata;
    private javax.swing.JPanel pnl_capNhat;
    private javax.swing.JPanel pnl_control;
    private javax.swing.JPanel pnl_diaChi;
    private javax.swing.JPanel pnl_dsKhachHang;
    private javax.swing.JPanel pnl_email;
    private javax.swing.JPanel pnl_hangThanhVien;
    private javax.swing.JPanel pnl_maKH;
    private javax.swing.JPanel pnl_maSoThue;
    private javax.swing.JPanel pnl_namSinh;
    private javax.swing.JPanel pnl_soDT;
    private javax.swing.JPanel pnl_tenKH;
    private javax.swing.JPanel pnl_timKiem;
    private javax.swing.JPanel pnl_ttKH;
    private javax.swing.JPanel pnl_ttKhachHang;
    private javax.swing.JPanel pnl_xoaTrang;
    private javax.swing.JScrollPane scr_1;
    private javax.swing.JTable tbl_dsKhachHang;
    private com.toedter.calendar.JDateChooser txt_date;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_hangThanhVien;
    private javax.swing.JTextField txt_maKH;
    private javax.swing.JTextField txt_maSoThue;
    private javax.swing.JTextField txt_soDT;
    private javax.swing.JTextField txt_tenKH;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
