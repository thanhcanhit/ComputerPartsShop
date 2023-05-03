package entity.sanpham;

import java.util.Objects;

public class ThuongHieu {

    /**
     * Hằng báo lỗi
     */
    public static final String MA_EMPTY = "Mã thương hiệu không được phép rỗng!";
    public static final String TEN_EMPTY = "Tên thương hiệu không được phép rỗng!";
    public static final String QG_EMPTY = "Quốc gia không được rỗng";

    private String maTH;
    private String tenTH;
    private String quocGia;
    
    public ThuongHieu(String maTH) {
        this.maTH = maTH;
    }

    public ThuongHieu(String maTH, String tenTH, String quocGia) throws Exception {
        setMaTH(maTH);
        setTenTH(tenTH);
        setQuocGia(quocGia);
    }

    public String getMaTH() {
        return maTH;
    }

    public void setMaTH(String maTH) throws Exception {
        this.maTH = maTH;
        if (!maTH.equals("")) {
            this.maTH = maTH;
        } else {
            throw new Exception(MA_EMPTY);
        }
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) throws Exception {
        if (!tenTH.equals("")) {
            this.tenTH = tenTH;
        } else {
            throw new Exception(TEN_EMPTY);
        }
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) throws Exception {
        if (!quocGia.equals("")) {
            this.quocGia = quocGia;
        } else {
            throw new Exception(QG_EMPTY);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.maTH);
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
        final ThuongHieu other = (ThuongHieu) obj;
        return Objects.equals(this.maTH, other.maTH);
    }
    
    

    @Override
    public String toString() {
        return this.tenTH + " - " + this.quocGia;
    }
}
