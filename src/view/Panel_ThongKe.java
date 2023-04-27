/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.toedter.calendar.JCalendar;
import controller.SanPham_bus;
import controller.ThongKe_bus;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.LinearGradientPaint;
import java.awt.color.ColorSpace;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.sanpham.SanPham;
import model.share.Utility;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

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

    /**
     * Creates new form Panel_ThongKe
     */
    public Panel_ThongKe() {
        String colname[] = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng bán ra", "Tổng doanh thu"};
       
        model_dsSanPham = new DefaultTableModel(colname, 0);
       
        initComponents();

        ChartPanel chartPanel = new ChartPanel(createBarChart());

        pnl_chart.add(chartPanel);

        alterTable();
        renderListToTable(tk_bus.get3sanPhamBanChay());
        
        
        jMonth_thang.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("month".equals(evt.getPropertyName())) {
                    int month = jMonth_thang.getMonth();
                    // Xử lý sự kiện khi người dùng thay đổi dữ liệu trong JCalendar
                    renderListToTable(tk_bus.get3sanPhamBanChay(month+1));
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
                    renderListToTable(tk_bus.get3sanPhamBanChay(month+1, year));
                }
            }
        });

    }

    public DefaultCategoryDataset createDataset() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double total[] = tk_bus.getDoanhThu12Thang();

        dataset.addValue(total[0] / 1000000, "Doanh thu", "1");
        dataset.addValue(total[1] / 1000000, "Doanh thu", "2");
        dataset.addValue(total[2] / 1000000, "Doanh thu", "3");
        dataset.addValue(total[3] / 1000000, "Doanh thu", "4");
        dataset.addValue(total[4] / 1000000, "Doanh thu", "5");
        dataset.addValue(total[5] / 1000000, "Doanh thu", "6");
        dataset.addValue(total[6] / 1000000, "Doanh thu", "7");
        dataset.addValue(total[7] / 1000000, "Doanh thu", "8");
        dataset.addValue(total[8] / 1000000, "Doanh thu", "9");
        dataset.addValue(total[9] / 1000000, "Doanh thu", "10");
        dataset.addValue(total[10] / 1000000, "Doanh thu", "11");
        dataset.addValue(total[11] / 1000000, "Doanh thu", "12");

        return dataset;
    }

    public JFreeChart createBarChart() {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu theo tháng".toUpperCase(),
                "THÁNG", "DOANH THU (triệu đồng)", createDataset(),
                PlotOrientation.VERTICAL, true, true, false);

        barChart.getTitle().setPaint(new Color(65,165,238));
       
        barChart.getPlot().setBackgroundPaint(new Color(250,250,250));
        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
      
        Color color = new Color(65,165,238);
        renderer.setSeriesPaint(0, color);
        renderer.setSeriesPaint(1, color);
        renderer.setSeriesPaint(2, color);
        renderer.setSeriesPaint(3, color);
        renderer.setSeriesPaint(4, color);
        renderer.setSeriesPaint(5, color);
        renderer.setSeriesPaint(6, color);
        renderer.setSeriesPaint(7, color);
        renderer.setSeriesPaint(8, color);
        renderer.setSeriesPaint(9, color);
        renderer.setSeriesPaint(10, color);
        renderer.setSeriesPaint(11, color);
        renderer.setSeriesPaint(12, color);
        
        return barChart;

    }

    public void renderListToTable(ArrayList<SanPham> list) {
        model_dsSanPham.setRowCount(0);
        for (SanPham sp : list) {
            String gioiTinh;
            model_dsSanPham.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(),tk_bus.getSoLuongBanSanPham(sp.getMaSP()),Utility.getVND(tk_bus.getDoanhThuSanPham(sp.getMaSP()))});
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
        pnl_chart = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 32767));

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnl_header.setBackground(new java.awt.Color(255, 255, 255));
        pnl_header.setPreferredSize(new java.awt.Dimension(1000, 260));
        pnl_header.setLayout(new java.awt.BorderLayout());

        pnl_control.setBackground(new java.awt.Color(255, 255, 255));
        pnl_control.setPreferredSize(new java.awt.Dimension(757, 50));
        pnl_control.setLayout(new javax.swing.BoxLayout(pnl_control, javax.swing.BoxLayout.LINE_AXIS));
        pnl_control.add(filler3);

        lbl_thang.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
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
        pnl_topItem.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Top sản phẩm bán chạy trong tháng : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 1, 14), new java.awt.Color(65, 165, 238))); // NOI18N
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

        add(pnl_header, java.awt.BorderLayout.NORTH);

        pnl_chart.setBackground(new java.awt.Color(255, 255, 255));
        pnl_chart.setLayout(new java.awt.BorderLayout());
        pnl_chart.add(filler1, java.awt.BorderLayout.PAGE_START);

        add(pnl_chart, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private com.toedter.calendar.JMonthChooser jMonth_thang;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYear_nam;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JLabel lbl_thang;
    private javax.swing.JPanel pnl_chart;
    private javax.swing.JPanel pnl_control;
    private javax.swing.JPanel pnl_header;
    private javax.swing.JPanel pnl_topItem;
    private javax.swing.JTable tbl_sanPham;
    // End of variables declaration//GEN-END:variables
}
