/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;


import bus.ThongKe_bus;

import java.awt.Color;
import java.awt.Font;

import java.awt.font.TextAttribute;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.AttributedString;
import java.time.LocalDate;

import java.util.ArrayList;

import java.util.Locale;
import javax.swing.JLabel;


import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import entity.sanpham.SanPham;
import entity.share.Utility;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author macbookk
 */
public final class Panel_ThongKe extends javax.swing.JPanel {

    DefaultTableModel model_dsSanPham;
    public final ThongKe_bus tk_bus = new ThongKe_bus();
    private ChartPanel chartPanel, chartPanel_sp;
    

    /**
     * Creates new form Panel_ThongKe
     */
    public Panel_ThongKe() {
        String colname[] = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng bán ra", "Tổng doanh thu"};
       
        model_dsSanPham = new DefaultTableModel(colname, 0);
       
        initComponents();

        chartPanel = new ChartPanel(createBarChart(LocalDate.now().getYear()));
        chartPanel_sp = new ChartPanel(createBarChart1(LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        pnl_chart.add(chartPanel);
        pnl_chart1.add(chartPanel_sp);
        alterTable();
        renderListToTable(tk_bus.get3sanPhamBanChay());
        
        
        jMonth_thang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("month".equals(evt.getPropertyName())) {
                    int month = jMonth_thang.getMonth();
                    int year = jYear_nam.getYear();
                    // Xử lý sự kiện khi người dùng thay đổi dữ liệu trong JCalendar
                    renderListToTable(tk_bus.get3sanPhamBanChay(month+1,year), month+1,year);
                    pnl_chart1.updateUI();
                    chartPanel_sp.setChart(createBarChart1(month+1,year));
                    pnl_chart1.add(chartPanel);
                }
            }
        });
        jYear_nam.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("year".equals(evt.getPropertyName())) {
                    int year = jYear_nam.getYear();
                    int month = jMonth_thang.getMonth();
                    // Xử lý sự kiện khi người dùng thay đổi dữ liệu trong JCalendar
                    renderListToTable(tk_bus.get3sanPhamBanChay(month+1, year), month+1, year);
                    pnl_chart1.updateUI();
                    chartPanel_sp.setChart(createBarChart1(month+1,year));
                    pnl_chart1.add(chartPanel);
                    
                }
            }
        });
        
        jYear_nam1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("year".equals(evt.getPropertyName())) {
                    int year = jYear_nam1.getYear();
                    
                    // Xử lý sự kiện khi người dùng thay đổi dữ liệu trong JCalendar
                    pnl_chart.updateUI();
                    chartPanel.setChart(createBarChart(year));
                    pnl_chart.add(chartPanel);
                    
                }
            }
        });

    }

    // Chart Thong Ke Theo Doanh Thu 12 Thang
    public DefaultCategoryDataset createDataset(int year) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        double total[] = tk_bus.getDoanhThu12Thang(year);
        for(int i=0;i<12;i++){
            dataset.addValue(total[i] / 1000000, "Doanh thu", Integer.toString(i + 1));
        }


        return dataset;
    }
    public JFreeChart createBarChart(int year) {
     
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu theo tháng".toUpperCase(),
                "THÁNG", "DOANH THU (triệu đồng)", createDataset(year),
                PlotOrientation.VERTICAL, true, true, false);

        barChart.getTitle().setPaint(new Color(65,165,238));
        
       
        barChart.getPlot().setBackgroundPaint(new Color(250,250,250));
        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        
        CategoryPlot plot = barChart.getCategoryPlot();
        // Lấy đối tượng CategoryAxis và ValueAxis từ CategoryPlot
        CategoryAxis domainAxis = plot.getDomainAxis();
        ValueAxis rangeAxis = plot.getRangeAxis();

        // Set màu cho title trục x
        Font xLabelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color xLabelColor = new Color(65,165,238);
        AttributedString xLabel = new AttributedString("THÁNG");
        xLabel.addAttribute(TextAttribute.FONT, xLabelFont);
        xLabel.addAttribute(TextAttribute.FOREGROUND, xLabelColor);
        domainAxis.setAttributedLabel(xLabel);

        // Set màu cho title trục y
        Font yLabelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color yLabelColor = new Color(65,165,238);
        AttributedString yLabel = new AttributedString("DOANH THU (triệu đồng)");
        yLabel.addAttribute(TextAttribute.FONT, yLabelFont);
        yLabel.addAttribute(TextAttribute.FOREGROUND, yLabelColor);
        rangeAxis.setAttributedLabel(yLabel);
      
        Color color = new Color(65,165,238);
        for(int i=0;i<12;i++){
            renderer.setSeriesPaint(i, color);
        }
  
        return barChart;

    }
    // Chart Thong Ke Theo San Pham
    public DefaultCategoryDataset createDataset1(int month,int year) {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<SanPham> list_sp = new ArrayList<>();
        list_sp = tk_bus.getsanPham(month, year);
        for(int i=0;i<list_sp.size();i++){
            dataset.addValue(tk_bus.getDoanhThuSanPham(list_sp.get(i).getMaSP(), month, year)/1000000,"Tổng Doanh Thu", list_sp.get(i).getMaSP());
        }

        return dataset;
    }
    public JFreeChart createBarChart1(int month, int year) {
     
        JFreeChart barChart = ChartFactory.createBarChart(
                "Tổng Doanh Thu Từng Sản Phẩm Theo Tháng".toUpperCase(),
                "Tháng", "tổng doanh thu", createDataset1(month,year),
                PlotOrientation.VERTICAL, true, true, false);

        barChart.getTitle().setPaint(new Color(65,165,238));
        
       
        barChart.getPlot().setBackgroundPaint(new Color(250,250,250));
        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        
        CategoryPlot plot = barChart.getCategoryPlot();
        // Lấy đối tượng CategoryAxis và ValueAxis từ CategoryPlot
        CategoryAxis domainAxis = plot.getDomainAxis();
        ValueAxis rangeAxis = plot.getRangeAxis();

        // Set màu cho title trục x
        Font xLabelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color xLabelColor = new Color(65,165,238);
        AttributedString xLabel = new AttributedString("MÃ SẢN PHẨM");
        xLabel.addAttribute(TextAttribute.FONT, xLabelFont);
        xLabel.addAttribute(TextAttribute.FOREGROUND, xLabelColor);
        domainAxis.setAttributedLabel(xLabel);

        // Set màu cho title trục y
        Font yLabelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color yLabelColor = new Color(65,165,238);
        AttributedString yLabel = new AttributedString("TỔNG DOANH THU TRONG THÁNG (triệu đồng)");
        yLabel.addAttribute(TextAttribute.FONT, yLabelFont);
        yLabel.addAttribute(TextAttribute.FOREGROUND, yLabelColor);
        rangeAxis.setAttributedLabel(yLabel);
      
        Color color = new Color(65,165,238);
        for(int i=0;i<12;i++){
            renderer.setSeriesPaint(i, color);
        }
  
        return barChart;

    }
    
    
    public void renderListToTable(ArrayList<SanPham> list) {
        model_dsSanPham.setRowCount(0);
        for (SanPham sp : list) {
            if(tk_bus.getSoLuongBanSanPham(sp.getMaSP())<=0)
                continue;
            model_dsSanPham.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(),tk_bus.getSoLuongBanSanPham(sp.getMaSP()),Utility.getVND(tk_bus.getDoanhThuSanPham(sp.getMaSP()))});
        }

    }

    public void renderListToTable(ArrayList<SanPham> list, int month, int year) {
        model_dsSanPham.setRowCount(0);
        for (SanPham sp : list) {
            
            if(tk_bus.getSoLuongBanSanPham(sp.getMaSP(),month,year)<=0)
                continue;
            model_dsSanPham.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(),tk_bus.getSoLuongBanSanPham(sp.getMaSP(), month, year),Utility.getVND(tk_bus.getDoanhThuSanPham(sp.getMaSP(),month, year))});
        }

    }

    public void alterTable() {
        tbl_sanPham.setRowHeight(50);
        tbl_sanPham.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16));
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(JLabel.RIGHT);
        // set chieu dai cot
//        tbl_sanPham.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_sanPham.getColumnModel().getColumn(0).setPreferredWidth(120);
        tbl_sanPham.getColumnModel().getColumn(1).setPreferredWidth(430);
        tbl_sanPham.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_sanPham.getColumnModel().getColumn(3).setPreferredWidth(150);
      
        // set canh le trai

        tbl_sanPham.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
        tbl_sanPham.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        
        // set khong chinh sua dong trong table
        tbl_sanPham.setDefaultEditor(Object.class, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab_thongKe = new javax.swing.JTabbedPane();
        pnl_thongKeSanPham = new javax.swing.JPanel();
        pnl_header = new javax.swing.JPanel();
        pnl_control = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        lbl_thang = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jMonth_thang = new com.toedter.calendar.JMonthChooser();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jYear_nam = new com.toedter.calendar.JYearChooser();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(32767, 0));
        lbl_logo = new javax.swing.JLabel();
        pnl_topItem = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        pnl_chart1 = new javax.swing.JPanel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 32767));
        pnl_thongKeDoanhThu = new javax.swing.JPanel();
        pnl_control1 = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        lbl_thang1 = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jYear_nam1 = new com.toedter.calendar.JYearChooser();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(32767, 0));
        lbl_logo1 = new javax.swing.JLabel();
        pnl_chart = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 32767));

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        tab_thongKe.setBackground(new java.awt.Color(255, 255, 255));
        tab_thongKe.setForeground(new java.awt.Color(102, 102, 102));
        tab_thongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab_thongKe.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        pnl_thongKeSanPham.setLayout(new java.awt.BorderLayout());

        pnl_header.setBackground(new java.awt.Color(255, 255, 255));
        pnl_header.setPreferredSize(new java.awt.Dimension(1000, 260));
        pnl_header.setLayout(new java.awt.BorderLayout());

        pnl_control.setBackground(new java.awt.Color(255, 255, 255));
        pnl_control.setPreferredSize(new java.awt.Dimension(757, 50));
        pnl_control.setLayout(new javax.swing.BoxLayout(pnl_control, javax.swing.BoxLayout.LINE_AXIS));
        pnl_control.add(filler3);

        lbl_thang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_thang.setText("Thống kê theo : ");
        lbl_thang.setMaximumSize(new java.awt.Dimension(152, 40));
        pnl_control.add(lbl_thang);
        pnl_control.add(filler6);

        jMonth_thang.setBackground(new java.awt.Color(255, 255, 255));
        jMonth_thang.setMaximumSize(new java.awt.Dimension(300, 40));
        jMonth_thang.setMinimumSize(new java.awt.Dimension(100, 27));
        jMonth_thang.setPreferredSize(new java.awt.Dimension(150, 27));
        jMonth_thang.setLocale(new Locale("vi","VN"));
        pnl_control.add(jMonth_thang);
        pnl_control.add(filler2);

        jYear_nam.setMaximumSize(new java.awt.Dimension(120, 40));
        jYear_nam.setMinimumSize(new java.awt.Dimension(120, 23));
        jYear_nam.setPreferredSize(new java.awt.Dimension(120, 23));
        pnl_control.add(jYear_nam);
        pnl_control.add(filler5);

        lbl_logo.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lbl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_chart.png"))); // NOI18N
        pnl_control.add(lbl_logo);

        pnl_header.add(pnl_control, java.awt.BorderLayout.NORTH);

        pnl_topItem.setBackground(new java.awt.Color(255, 255, 255));
        pnl_topItem.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Top 3 sản phẩm có số lượng bán cao nhất trong tháng : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
        pnl_topItem.setLayout(new javax.swing.BoxLayout(pnl_topItem, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tbl_sanPham.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        tbl_sanPham.setModel(model_dsSanPham
        );
        tbl_sanPham.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_sanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_sanPham.setFocusable(false);
        tbl_sanPham.setRowSelectionAllowed(false);
        tbl_sanPham.setShowGrid(true);
        tbl_sanPham.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tbl_sanPham);

        pnl_topItem.add(jScrollPane1);

        pnl_header.add(pnl_topItem, java.awt.BorderLayout.CENTER);

        pnl_thongKeSanPham.add(pnl_header, java.awt.BorderLayout.NORTH);

        pnl_chart1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_chart1.setLayout(new java.awt.BorderLayout());
        pnl_chart1.add(filler10, java.awt.BorderLayout.PAGE_START);

        pnl_thongKeSanPham.add(pnl_chart1, java.awt.BorderLayout.CENTER);

        tab_thongKe.addTab("Thống Kê Sản Phẩm", pnl_thongKeSanPham);

        pnl_thongKeDoanhThu.setLayout(new java.awt.BorderLayout());

        pnl_control1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_control1.setPreferredSize(new java.awt.Dimension(757, 50));
        pnl_control1.setLayout(new javax.swing.BoxLayout(pnl_control1, javax.swing.BoxLayout.LINE_AXIS));
        pnl_control1.add(filler4);

        lbl_thang1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_thang1.setText("Thống kê theo : ");
        lbl_thang1.setMaximumSize(new java.awt.Dimension(152, 40));
        pnl_control1.add(lbl_thang1);
        pnl_control1.add(filler8);

        jYear_nam1.setMaximumSize(new java.awt.Dimension(120, 40));
        jYear_nam1.setMinimumSize(new java.awt.Dimension(120, 23));
        jYear_nam1.setPreferredSize(new java.awt.Dimension(120, 23));
        pnl_control1.add(jYear_nam1);
        pnl_control1.add(filler9);

        lbl_logo1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lbl_logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_chart.png"))); // NOI18N
        pnl_control1.add(lbl_logo1);

        pnl_thongKeDoanhThu.add(pnl_control1, java.awt.BorderLayout.NORTH);

        pnl_chart.setBackground(new java.awt.Color(255, 255, 255));
        pnl_chart.setLayout(new java.awt.BorderLayout());
        pnl_chart.add(filler1, java.awt.BorderLayout.PAGE_START);

        pnl_thongKeDoanhThu.add(pnl_chart, java.awt.BorderLayout.CENTER);

        tab_thongKe.addTab("Thống Kê Doanh Thu", pnl_thongKeDoanhThu);

        add(tab_thongKe, java.awt.BorderLayout.CENTER);
        tab_thongKe.getAccessibleContext().setAccessibleName("Thống kê doanhThu");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private com.toedter.calendar.JMonthChooser jMonth_thang;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYear_nam;
    private com.toedter.calendar.JYearChooser jYear_nam1;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JLabel lbl_logo1;
    private javax.swing.JLabel lbl_thang;
    private javax.swing.JLabel lbl_thang1;
    private javax.swing.JPanel pnl_chart;
    private javax.swing.JPanel pnl_chart1;
    private javax.swing.JPanel pnl_control;
    private javax.swing.JPanel pnl_control1;
    private javax.swing.JPanel pnl_header;
    private javax.swing.JPanel pnl_thongKeDoanhThu;
    private javax.swing.JPanel pnl_thongKeSanPham;
    private javax.swing.JPanel pnl_topItem;
    private javax.swing.JTabbedPane tab_thongKe;
    private javax.swing.JTable tbl_sanPham;
    // End of variables declaration//GEN-END:variables
}
