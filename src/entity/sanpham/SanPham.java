package entity.sanpham;

import java.util.Objects;

public class SanPham {

    /**
     * Hằng kiểu sản phẩm (Dễ quản lí hơn)
     */
    public static final int CPU = 0;
    public static final int MAINBOARD = 1;
    public static final int VGA = 2;
    public static final int RAM = 3;
    public static final int OCUNG = 4;
    public static final int NGUON = 5;
    public static final int CASE = 6;
    public static final int TANNHIET = 7;
    public static final int CHUOT = 8;
    public static final int BANPHIM = 9;

    /**
     * Hằng báo lỗi
     */
    public static final String MA_EMPTY = "Mã sản phẩm không được phép rỗng!";
    public static final String TEN_EMPTY = "Tên sản phẩm không được phép rỗng!";
    public static final String GIA_NHAP_INVALID = "Giá nhập phải >= 0!";
    public static final String GIAM_GIA_INVALID = "Giảm giá phải từ [0->100]";
    public static final String VAT_INVALID = "VAT giá phải từ [0->100]";
    public static final String LOAI_NOT_FOUND = "Loại sản phẩm không tồn tại!";
    public static final String STBH_INVALID = "Số tháng bảo hành phải >= 0!";
    public static final String THUONG_HIEU_IS_NULL = "Thương hiệu không thể null";

    private String maSP;
    private String tenSP;
    private double giaNhap; // >0
    private double giamGia; // [0-100]%
    private int loai;
    private double VAT;
    private ThuongHieu thuongHieu;
    private int soThangBaoHanh; // > 0
    private String cauHinh; // có thể rỗng

    /**
     *
     * @param maSP
     * @param tenSP
     * @param giaNhap: >= 0
     * @param giamGia: 0 -> 100
     * @param loai: Dùng các const được định nghĩa như SANPHAM.CPU
     * @param VAT: Thuế
     * @param thuongHieu
     * @param soThangBaoHanh
     * @param cauHinh: Chuỗi kết hợp tất cả thông tin cấu hình
     * @throws Exception
     */
    public SanPham(String maSP, String tenSP, double giaNhap, double giamGia, int loai, double VAT, ThuongHieu thuongHieu, int soThangBaoHanh, String cauHinh) throws Exception {
        setMaSP(maSP);
        setTenSP(tenSP);
        setGiaNhap(giaNhap);
        setGiamGia(giamGia);
        setLoai(loai);
        setVAT(VAT);
        setThuongHieu(thuongHieu);
        setSoThangBaoHanh(soThangBaoHanh);
        setCauHinh(cauHinh);
    }

    public SanPham(String maSP) {
        this.maSP = maSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) throws Exception {
        if (!maSP.trim().equals("")) {
            this.maSP = maSP;
        } else {
            throw new Exception(MA_EMPTY);
        }
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) throws Exception {
        if (!tenSP.trim().equals("")) {
            this.tenSP = tenSP;
        } else {
            throw new Exception(TEN_EMPTY);
        }
    }

    public Double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) throws Exception {
        if (giamGia >= 0) {
            this.giamGia = giamGia;
        } else {
            throw new Exception(GIAM_GIA_INVALID);
        }
    }

    public int getLoai() {
        return loai;
    }

    public String getTenLoai() {
        String result = "";
        switch (this.loai) {
            case 0 ->
                result = "CPU";
            case 1 ->
                result = "MainBoard";
            case 2 ->
                result = "VGA";
            case 3 ->
                result = "RAM";
            case 4 ->
                result = "Ổ cứng";
            case 5 ->
                result = "Nguồn";
            case 6 ->
                result = "Case";
            case 7 ->
                result = "Tản nhiệt";
            case 8 ->
                result = "Chuột";
            case 9 ->
                result = "Bàn phím";
        }
        return result;
    }

    public void setLoai(int loai) throws Exception {
        if (loai >= SanPham.CPU && loai <= SanPham.BANPHIM) {
            this.loai = loai;
        } else {
            throw new Exception(LOAI_NOT_FOUND);
        }
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) throws Exception {
        if (VAT < 0 && VAT > 100) {
            throw new Exception(VAT_INVALID);
        }
        this.VAT = VAT;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) throws Exception {
        if (thuongHieu != null) {
            this.thuongHieu = thuongHieu;
        } else {
            throw new Exception(THUONG_HIEU_IS_NULL);
        }
    }

    public int getSoThangBaoHanh() {
        return soThangBaoHanh;
    }

    public void setSoThangBaoHanh(int soThangBaoHanh) throws Exception {
        if (soThangBaoHanh >= 0) {
            this.soThangBaoHanh = soThangBaoHanh;
        } else {
            throw new Exception(STBH_INVALID);
        }
    }

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) throws Exception {
        if (giaNhap >= 0) {
            this.giaNhap = giaNhap;
        } else {
            throw new Exception(GIA_NHAP_INVALID);
        }
    }

    /**
     * Giá bán bằng giá nhập cộng 5% tiền lời + thuế - giảm giá
     *
     * @return
     */
    public double getGiaBan() {
//         return (this.giamGia/100);
        return giaNhap + 0.05 * giaNhap + (this.VAT / 100) * this.giaNhap - (this.giamGia / 100) * this.giaNhap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.maSP);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SanPham other = (SanPham) obj;
        return Objects.equals(this.maSP, other.maSP);
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", giaNhap=" + giaNhap + ", giamGia=" + giamGia + ", loai=" + loai + ", VAT=" + VAT + ", thuongHieu=" + thuongHieu + ", soThangBaoHanh=" + soThangBaoHanh + ", cauHinh=" + cauHinh + '}';
    }

}
