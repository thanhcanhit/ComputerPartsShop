-- Tạo database ở đường dẫn sql mặc định
USE master
GO
if exists (select * from sysdatabases where name='ComputerPartsShop')
		drop database ComputerPartsShop
GO

DECLARE @device_directory nvarchar(520)
SELECT @device_directory = SUBSTRING(filename, 1, CHARINDEX(N'master.mdf', LOWER(filename)) - 1)
FROM master.dbo.sysaltfiles WHERE dbid = 1 AND fileid = 1

EXECUTE (N'CREATE DATABASE ComputerPartsShop
  ON PRIMARY (NAME = N''ComputerPartsShop'', FILENAME = N''' + @device_directory + N'ComputerPartsShop.mdf'')
  LOG ON (NAME = N''ComputerPartsShop_log'',  FILENAME = N''' + @device_directory + N'ComputerPartsShop.ldf'')')

GO
use ComputerPartsShop;

-- Tạo các bảng
CREATE TABLE ChiTietDonNhap (maNhaCungCap nvarchar(30) NOT NULL, maSanPham nvarchar(30) NOT NULL, maPhieuNhap nvarchar(30) NOT NULL, soLuongCungCap int NULL, PRIMARY KEY (maNhaCungCap, maSanPham, maPhieuNhap));
CREATE TABLE ChiTietHoaDon (maSanPham nvarchar(30) NOT NULL, maHoaDon nvarchar(30) NOT NULL, giaBan float(10) NOT NULL, soLuong int NOT NULL, tongTien float(10) NOT NULL, PRIMARY KEY (maSanPham, maHoaDon));
CREATE TABLE ChiTietKhoHang (maKho nvarchar(30) NOT NULL, maSanPham nvarchar(30) NOT NULL, soLuongTon int NOT NULL, PRIMARY KEY (maKho, maSanPham));
CREATE TABLE DiaChi (maDiaChi nvarchar(30) NOT NULL, duong nvarchar(25) NULL, huyen nvarchar(25) NULL, thanhPho nvarchar(25) NULL, quocGia nvarchar(25) NULL, PRIMARY KEY (maDiaChi));
CREATE TABLE DonNhapHang (maPhieuNhap nvarchar(30) NOT NULL, ngayNhapKho int NULL, maKho nvarchar(30) NULL, PRIMARY KEY (maPhieuNhap));
CREATE TABLE HoaDon (maHoaDon nvarchar(30) NOT NULL, ngayLap date NULL, phuongThucThanhToan nvarchar(20) NULL, maNhanVien nvarchar(30) NULL, maKhachHang nvarchar(30) NOT NULL, PRIMARY KEY (maHoaDon));
CREATE TABLE KhachHang (maKhachHang nvarchar(30) NOT NULL, hoTen nvarchar(30) NOT NULL, soDienThoai nvarchar(10) NOT NULL, ngaySinh date NULL, email nvarchar(30) NULL, maSoThue nvarchar(25) NULL, maDiaChi nvarchar(30) NULL, PRIMARY KEY (maKhachHang));
CREATE TABLE KhoHang (maKho nvarchar(30) NOT NULL, tenKho nvarchar(50) NULL, maHang int NULL, dienTich float(10) NOT NULL, maDiaChi nvarchar(30) NOT NULL, PRIMARY KEY (maKho));
CREATE TABLE NhaCungCap (maNhaCungCap nvarchar(30) NOT NULL, tenNhaCungCap nvarchar(40) NULL, soDienThoai nvarchar(10) NULL, maSoThue nvarchar(25) NULL, email nvarchar(40) NULL, maDiaChi nvarchar(30) NOT NULL, PRIMARY KEY (maNhaCungCap));
CREATE TABLE NhanVien (maNhanVien nvarchar(30) NOT NULL, hoTen nvarchar(40) NOT NULL, soDienThoai nvarchar(10) NOT NULL, ngaySinh date NOT NULL, email nvarchar(50) NULL, chucVu nvarchar(25) NOT NULL, maQuanLy nvarchar(30) NOT NULL, maDiaChi nvarchar(30) NOT NULL, PRIMARY KEY (maNhanVien));
CREATE TABLE PhieuBaoHanh (maPhieuBaoHanh nvarchar(30) NOT NULL, maHoaDon nvarchar(30) NOT NULL, maSanPham nvarchar(30) NOT NULL, PRIMARY KEY (maPhieuBaoHanh));
CREATE TABLE SanPham (maSanPham nvarchar(30) NOT NULL, tenSanPham nvarchar(300) NOT NULL, giaNhap float(10) NOT NULL, giamGia float(10) NOT NULL, cauHinh nvarchar(255) NULL, soThangBaoHang int NOT NULL, loai nvarchar(25) NOT NULL, maThuongHieu nvarchar(30) NOT NULL, VAT float(10) NOT NULL, PRIMARY KEY (maSanPham));
CREATE TABLE TaiKhoan (soTaiKhoan nvarchar(15) NOT NULL, matKhau nvarchar(30) NOT NULL, maNhanVien nvarchar(30) NOT NULL, PRIMARY KEY (soTaiKhoan));
CREATE TABLE ThuongHieu (maThuongHieu nvarchar(50) NOT NULL, tenThuongHieu nvarchar(40) NOT NULL, quocGia nvarchar(50) NOT NULL, PRIMARY KEY (maThuongHieu));

--Tạo ràng buộc
ALTER TABLE DonNhapHang ADD CONSTRAINT FKDonNhapHan397345 FOREIGN KEY (maKho) REFERENCES KhoHang (maKho);
ALTER TABLE ChiTietDonNhap ADD CONSTRAINT FKChiTietDon227278 FOREIGN KEY (maPhieuNhap) REFERENCES DonNhapHang (maPhieuNhap);
ALTER TABLE PhieuBaoHanh ADD CONSTRAINT FKPhieuBaoHa638995 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham);
ALTER TABLE KhoHang ADD CONSTRAINT FKKhoHang823067 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE PhieuBaoHanh ADD CONSTRAINT FKPhieuBaoHa77447 FOREIGN KEY (maHoaDon) REFERENCES HoaDon (maHoaDon);
ALTER TABLE KhachHang ADD CONSTRAINT FKKhachHang328831 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon340527 FOREIGN KEY (maKhachHang) REFERENCES KhachHang (maKhachHang);
ALTER TABLE SanPham ADD CONSTRAINT FKSanPham375257 FOREIGN KEY (maThuongHieu) REFERENCES ThuongHieu (maThuongHieu);
ALTER TABLE ChiTietKhoHang ADD CONSTRAINT FKChiTietKho612013 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham);
ALTER TABLE ChiTietKhoHang ADD CONSTRAINT FKChiTietKho194713 FOREIGN KEY (maKho) REFERENCES KhoHang (maKho);
ALTER TABLE NhanVien ADD CONSTRAINT FKNhanVien672257 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE NhaCungCap ADD CONSTRAINT FKNhaCungCap660119 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE ChiTietDonNhap ADD CONSTRAINT FKChiTietDon402598 FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap (maNhaCungCap);
ALTER TABLE ChiTietDonNhap ADD CONSTRAINT FKChiTietDon465003 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham);
ALTER TABLE TaiKhoan ADD CONSTRAINT FKTaiKhoan222100 FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa190059 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon235233 FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa248392 FOREIGN KEY (maHoaDon) REFERENCES HoaDon (maHoaDon);
ALTER TABLE NhanVien ADD CONSTRAINT FKNhanVien638696 FOREIGN KEY (maQuanLy) REFERENCES NhanVien (maNhanVien);