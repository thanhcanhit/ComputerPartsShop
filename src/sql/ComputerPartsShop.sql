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

CREATE TABLE ChiTietDonNhap (maSanPham nvarchar(30) NOT NULL, maDonNhap nvarchar(30) NOT NULL, soLuongCungCap int NULL, tongTien float(50) NOT NULL, PRIMARY KEY (maSanPham, maDonNhap));
CREATE TABLE ChiTietHoaDon (maSanPham nvarchar(30) NOT NULL, maHoaDon nvarchar(30) NOT NULL, giaBan float(10) NOT NULL, soLuong int NOT NULL, tongTien float(10) NOT NULL, PRIMARY KEY (maSanPham, maHoaDon));
CREATE TABLE ChiTietKhoHang (maKho nvarchar(30) NOT NULL, maSanPham nvarchar(30) NOT NULL, soLuongTon int NOT NULL, PRIMARY KEY (maKho, maSanPham));
CREATE TABLE DiaChi (maDiaChi nvarchar(30) NOT NULL, soNha nvarchar(30) NULL, duong nvarchar(50) NULL, huyen nvarchar(50) NULL, thanhPho nvarchar(50) NULL, quocGia nvarchar(50) NULL, PRIMARY KEY (maDiaChi));
CREATE TABLE DonNhapHang (maDonNhap nvarchar(30) NOT NULL, ngayNhap date NOT NULL, maKho nvarchar(30) NOT NULL, maNhaCungCap nvarchar(30) NOT NULL, ghiChu nvarchar(1000) NULL, tongTien float(10) NOT NULL, daNhan bit NOT NULL, maNhanVien nvarchar(30) NOT NULL, PRIMARY KEY (maDonNhap));
CREATE TABLE HoaDon (maHoaDon nvarchar(30) NOT NULL, ngayLap date NULL, phuongThucThanhToan nvarchar(20) NULL, maNhanVien nvarchar(30) NULL, maKhachHang nvarchar(30) NOT NULL, tongTien float(53) NULL, PRIMARY KEY (maHoaDon));
CREATE TABLE KhachHang (maKhachHang nvarchar(30) NOT NULL, hoTen nvarchar(30) NOT NULL, soDienThoai nvarchar(10) NOT NULL, ngaySinh date NULL, email nvarchar(30) NULL, maSoThue nvarchar(25) NULL, maDiaChi nvarchar(30) NULL, diemThanhVien int NULL, gioiTinh bit NULL, PRIMARY KEY (maKhachHang));
CREATE TABLE KhoHang (maKho nvarchar(30) NOT NULL, tenKho nvarchar(50) NULL, dienTich float(10) NOT NULL, maDiaChi nvarchar(30) NOT NULL, PRIMARY KEY (maKho));
CREATE TABLE NhaCungCap (maNhaCungCap nvarchar(30) NOT NULL, tenNhaCungCap nvarchar(40) NULL, soDienThoai nvarchar(15) NULL, maSoThue nvarchar(25) NULL, email nvarchar(40) NULL, maDiaChi nvarchar(30) NOT NULL, PRIMARY KEY (maNhaCungCap));
CREATE TABLE NhanVien (maNhanVien nvarchar(30) NOT NULL, hoTen nvarchar(50) NOT NULL, soDienThoai nvarchar(15) NOT NULL, ngaySinh date NOT NULL, email nvarchar(50) NULL, chucDanh nvarchar(40) NOT NULL, maDiaChi nvarchar(30) NOT NULL, gioiTinh bit NOT NULL, trangThai bit NOT NULL, PRIMARY KEY (maNhanVien));
CREATE TABLE SanPham (maSanPham nvarchar(30) NOT NULL, tenSanPham nvarchar(100) NOT NULL, giaNhap float(20) NOT NULL, giamGia float(5) NOT NULL, cauHinh nvarchar(1000) NULL, soThangBaoHanh int NOT NULL, maLoai int NOT NULL, VAT float(5) NOT NULL, maThuongHieu nvarchar(30) NOT NULL, PRIMARY KEY (maSanPham));
CREATE TABLE TaiKhoan (maNhanVien nvarchar(30) NOT NULL, matKhau nvarchar(30) NOT NULL, PRIMARY KEY (maNhanVien));
CREATE TABLE ThuongHieu (maThuongHieu nvarchar(30) NOT NULL, tenThuongHieu nvarchar(40) NOT NULL, quocGia nvarchar(50) NOT NULL, PRIMARY KEY (maThuongHieu));
ALTER TABLE DonNhapHang ADD CONSTRAINT FKDonNhapHan451254 FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien);
ALTER TABLE DonNhapHang ADD CONSTRAINT FKDonNhapHan528637 FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap (maNhaCungCap);
ALTER TABLE DonNhapHang ADD CONSTRAINT FKDonNhapHan502440 FOREIGN KEY (maKho) REFERENCES KhoHang (maKho);
ALTER TABLE ChiTietDonNhap ADD CONSTRAINT FKChiTietDon280818 FOREIGN KEY (maDonNhap) REFERENCES DonNhapHang (maDonNhap);
ALTER TABLE KhoHang ADD CONSTRAINT FKKhoHang231296 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE KhachHang ADD CONSTRAINT FKKhachHang383195 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon550538 FOREIGN KEY (maKhachHang) REFERENCES KhachHang (maKhachHang);
ALTER TABLE SanPham ADD CONSTRAINT FKSanPham473948 FOREIGN KEY (maThuongHieu) REFERENCES ThuongHieu (maThuongHieu);
ALTER TABLE ChiTietKhoHang ADD CONSTRAINT FKChiTietKho719856 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham);
ALTER TABLE ChiTietKhoHang ADD CONSTRAINT FKChiTietKho299808 FOREIGN KEY (maKho) REFERENCES KhoHang (maKho);
ALTER TABLE NhanVien ADD CONSTRAINT FKNhanVien617893 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE NhaCungCap ADD CONSTRAINT FKNhaCungCap660119 FOREIGN KEY (maDiaChi) REFERENCES DiaChi (maDiaChi);
ALTER TABLE ChiTietDonNhap ADD CONSTRAINT FKChiTietDon825282 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham);
ALTER TABLE TaiKhoan ADD CONSTRAINT FKTaiKhoan954915 FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa141811 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon559341 FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa903135 FOREIGN KEY (maHoaDon) REFERENCES HoaDon (maHoaDon);


DELETE FROM [dbo].[ChiTietHoaDon]
DELETE FROM [dbo].[ChiTietKhoHang]
DELETE FROM [dbo].[HoaDon]
DELETE FROM [dbo].[ChiTietDonNhap]
DELETE FROM [dbo].[DonNhapHang]
DELETE FROM [dbo].[SanPham]
DELETE FROM [dbo].[ThuongHieu]
DELETE FROM [dbo].[KhachHang]
DELETE FROM [dbo].[TaiKhoan]
DELETE FROM [dbo].[NhanVien]
DELETE FROM [dbo].[KhoHang]
DELETE FROM [dbo].[NhaCungCap]
DELETE FROM [dbo].[DiaChi]


--Thương hiệu
INSERT INTO ThuongHieu (maThuongHieu, tenThuongHieu, quocGia) 
VALUES 
('THHI01', 'Logitech', N'Mỹ'),
('THHI02', 'HP', N'Mỹ'),
('THHI03', 'Dell', N'Mỹ'),
('THHI04', 'Microsoft', N'Mỹ'),
('THHI05', 'Kingmax', N'Đài Loan'),
('THHI06', 'G.Skill', N'Đài Loan'),
('THHI07','Klevv',N'Đài Loan'),
('THHI08', 'Corsair', N'Mỹ'),
('THHI09', 'Gigabyte', 'Đài Loan'),
('THHI10', 'Lexar', N'Mỹ'),
('THHI11', 'Cruial', N'Mỹ'),
('THHI12', 'NOCTUA', N'Áo'),
('THHI13', 'GOLDEN FIELD', N'Trung Quốc'),
('THHI14', 'Cooler Master', N'Hà Lan'),
('THHI15', 'DEEPCOOL', N'Trung Quốc'),
('THHI16', 'ID-COOLING', N'Trung Quốc'),
('THHI17', 'Intel', N'Mỹ'),
('THHI18', 'AMD', N'Mỹ'),
('THHI19', 'ASUS', N'Đài Loan'),
('THHI20', 'MSI', N'Đài Loan'),
('THHI21', 'Asrock', N'Đài Loan'),
('THHI22', 'NVIDIA', N'Mỹ'),
('THHI23', 'Kingston', N'Mỹ'),
('THHI24', 'Adata', N'Đài Loan'),
('THHI25', 'Patriot', N'Mỹ'),
('THHI26', 'Apacer', N'Đài Loan'),
('THHI27', 'Geil', N'Đài Loan'),
('THHI28', 'NZXT', N'Đài Loan'),
('THHI29', 'Phanteks', N'Đài Loan'),
('THHI30', 'Fractal Design', N'Đài Loan'),
('THHI31', 'Thermltake', N'Đài Loan'),
('THHI32', 'XFX', N'Đài Loan'),
('THHI33', 'FSP', N'Đài Loan'),
('THHI34', 'Seasonic', N'Đài Loan'),
('THHI35', 'Huntkey', N'Đài Loan'),
('THHI36', 'AcBel', N'Đài Loan'),
('THHI37', 'Transcend', N'Đài Loan'),
('THHI38', 'Seagate', N'Đài Loan'),
('THHI39', 'Western Digital', N'Đài Loan'),
('THHI40', 'Toshiba', N'Đài Loan'),
('THHI41', 'HGST', N'Đài Loan'),
('THHI42', 'Samsung', N'Hàn quốc'),
('THHI43', 'Hitachi', N'Nhật bản'),
('THHI44', 'WD', N'Áo'),
('THHI45', 'Zotac', N'Mỹ'),
('THHI46', 'Palit', N'Trung Quốc'),
('THHI47', 'Be Quiet!', N'Trung Quốc'),
('THHI48', 'Razer', N'Mỹ'),
('THHI49', 'Glorious', N'Mỹ')


INSERT INTO SanPham ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [maLoai], [VAT], [maThuongHieu], [soThangBaoHanh], [cauHinh])
VALUES
--CHUỘT 8
('SP0001', N'Chuột Gaming Logitech G502 Hero', 590000, 0, 8, 10, 'THHI01', 12, N'Kích thước: 132 x 75 x 40 mm; Trọng lượng sản phẩm: 121 g; Độ dài dây: 200 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn'),
('SP0002', N'Chuột Gaming Razer DeathAdder V2', 1290000, 0, 8, 10, 'THHI48', 12, N'Kích thước: 127 x 61.7 x 42.7 mm; Trọng lượng sản phẩm: 82 g; Độ dài dây: 200 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 1.8 m; Độ phân giải chuột: 20000 dpi; Độ bền nút nhấn: 70 triệu lần nhấn'),
('SP0003', N'Chuột Gaming Glorious Model O-', 1690000, 0, 8, 10, 'THHI49', 12, N'Kích thước: 120 x 63 x 36 mm; Trọng lượng sản phẩm: 58 g; Độ dài dây: 200 cm; Màu sắc: Trắng; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 1.8 m; Độ phân giải chuột: 12000 dpi; Độ bền nút nhấn: 20 triệu lần nhấn'),
('SP0004', N'Chuột không dây Logitech M590', 650000, 0, 8, 10, 'THHI01', 12, N'Kích thước: 103 x 64 x 40 mm; Trọng lượng sản phẩm: 101 g; Độ dài dây: N/A; Màu sắc: Đen; Loại chuột: Chuột không dây; Kiểu kết nối: Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn'),
('SP0005', N'Chuột không dây Logitech M590', 650000, 0, 8, 10, 'THHI01', 12, N'Kích thước: 103 x 64 x 40 mm; Trọng lượng sản phẩm: 101 g; Độ dài dây: N/A; Màu sắc: Đen; Loại chuột: Chuột không dây; Kiểu kết nối: Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn'),
('SP0006', N'Chuột Gaming Logitech G502 Lightspeed', 1300000, 10, 8, 10, N'THHI01', 12, N'Kích thước: 132 x 75 x 40 mm; Trọng lượng sản phẩm: 114 g; Độ dài dây: 180 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: Không dây/ Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn; Đèn LED: RGB'),
('SP0007', N'Chuột Gaming Razer Basilisk V3', 1700000, 5, 8, 10, 'THHI48', 24, N'Kích thước: 130 x 60 x 42 mm; Trọng lượng sản phẩm: 92 g; Độ dài dây: 180 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 26000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn; Đèn LED: RGB'),
('SP0008', N'Chuột không dây HP Z3700', 300000, 0, 8, 10, 'THHI02', 6, N'Kích thước: 95 x 58 x 23 mm; Trọng lượng sản phẩm: 70 g; Kiểu kết nối: Không dây/ Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1200 dpi'),
('SP0009', N'Chuột không dây Dell WM126', 200000, 0, 8, 10, 'THHI03', 12, N'Kích thước: 99 x 63 x 37 mm; Trọng lượng sản phẩm: 100 g; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi'),
('SP0010', N'Chuột không dây Logitech M331 Silent Plus', 350000, 0, 8, 10, N'THHI01', 12, 'Kích thước: 105 x 67 x 38 mm; Trọng lượng sản phẩm: 91 g; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi'),
('SP0011', N'Chuột Gaming Razer DeathAdder Elite', 820000, 0, 8, 10, 'THHI48', 12, N'Kích thước: 127 x 70 x 44 mm; Trọng lượng sản phẩm: 105 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn'),
('SP0012', N'Chuột Không Dây Logitech M331', 420000, 0, 8, 10, 'THHI01', 24, N'Kích thước: 105 x 67 x 38 mm; Trọng lượng sản phẩm: 91 g; Độ dài dây: Không; Màu sắc: Xanh dương; Loại chuột: Chuột Không Dây; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn'),
('SP0013', N'Chuột Gaming Asus ROG Gladius II', 1200000, 0, 8, 10, 'THHI19', 12, N'Kích thước: 126 x 67 x 45 mm; Trọng lượng sản phẩm: 110 g; Độ dài dây: 200 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2 m; Độ phân giải chuột: 12000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn'),
('SP0014', N'Chuột Không Dây Microsoft Arc Mouse', 890000, 0, 8, 10, 'THHI04', 12, N'Kích thước: 131 x 55 x 15 mm; Trọng lượng sản phẩm: 82.5 g; Độ dài dây: Không; Màu sắc: Đen; Loại chuột: Chuột Không Dây; Kiểu kết nối: Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn'),
('SP0015', N'Chuột máy tính Logitech G300s', 325000, 10, 8, 10, 'THHI01', 12, N'Kích thước: 72 x 41 x 136 mm; Trọng lượng sản phẩm: 144 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 4000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn'),
('SP0016', N'Chuột máy tính Logitech G102', 400000, 5, 8, 10, 'THHI01', 12, N'Kích thước: 116.6 x 62.15 x 38.2 mm; Trọng lượng sản phẩm: 85 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 8000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn'),
('SP0017', N'Chuột máy tính Razer Viper', 2500000, 0, 8, 10, 'THHI48', 24, N'Kích thước: 126.73 x 66.2 x 37.81 mm; Trọng lượng sản phẩm: 69 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 70 triệu lần nhấn'),
('SP0018', N'Chuột máy tính Corsair Katar', 1190000, 0, 8, 10, 'THHI08', 24, N'Kích thước: 110.7 x 63.9 x 37.8 mm; Trọng lượng sản phẩm: 85 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 10000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn'),
('SP0019', N'Chuột gaming Razer DeathAdder Elite', 1125000, 10, 8, 10, N'THHI48', 12, N'Kích thước: 127 x 70 x 44 mm; Trọng lượng sản phẩm: 105 g; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 1.8 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn; Tốc độ phản hồi: 1ms'),
('SP0020', N'Chuột không dây Logitech M325', 450000, 0, 8, 10, 'THHI01', 12, N'Kích thước: 60 x 100 x 33 mm; Trọng lượng sản phẩm: 91 g; Loại chuột: Chuột không dây; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn; Thời gian sử dụng pin: 18 tháng'),

--RAM 3
('SP0021', 'RAM desktop KINGMAX (1 x 16GB) DDR5 4800MHz (KM-LD5-4800-16GS)', 3690000, 24, 3, 10, 'THHI05', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR5; Bus: 48000MHz;'),
('SP0022', 'RAM desktop KINGMAX (1x8GB) DDR3 1600MHz', 1250000, 20, 3, 10, 'THHI05', 36, N'Màu sắc: Xanh lá; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR3; Bus: 1600MHz;'),
('SP0023', 'RAM laptop KINGMAX (1x4GB) DDR3L 1600MHz', 790000, 36, 3, 10, 'THHI05', 36, N'Màu sắc: Xanh lá; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR3L; Bus: 1600MHz;'),
('SP0024', 'RAM desktop KINGMAX (1x4GB) DDR4 2400MHz', 690000, 20, 3, 10, 'THHI05', 36, N'Màu sắc: Đỏ; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 2400MHz;'),
('SP0025', 'RAM desktop KINGMAX (1x4GB) DDR4 2666MHz', 790000, 53, 3, 10, 'THHI05', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 2666MHz;'),
('SP0026', 'RAM desktop KINGMAX (1x8GB) DDR4 2666MHz', 859000, 19, 3, 10, 'THHI05', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 2666MHz;'),
('SP0027', 'RAM laptop KINGMAX (1x8GB) DDR3L 1600MHz', 1350000, 27, 3, 10, 'THHI05', 36, N'Màu sắc: Xanh lá; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR3L; Bus: 1600MHz;'),
('SP0028', 'RAM desktop G.SKILL Aegis F4-2666C19S-8GIS (1x8GB) DDR4 2666MHz', 759000, 14, 3, 10, 'THHI06', 36, N'Màu sắc: Đen, Đỏ; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 2666MHz;'),
('SP0029', 'RAM desktop KLEVV CRAS II KM4Z4GX1N (1x4GB) DDR4 2400MHz', 910000, 2, 3, 10, 'THHI07', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 2400MHz;'),
('SP0030', 'RAM KLEVV Bolt 1x4GB DDR4 3000MHz - KM4B4GX1A', 990000, 12, 3, 10, 'THHI07', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 3000MHz;'),
('SP0031', 'RAM desktop CORSAIR Vengeance RGB Pro CMW32GX4M2D3000C16 (2x16GB) DDR4 3000MHz', 4790000, 13, 3, 10, 'THHI08', 36, N'Màu sắc: Đen; Đèn LED: RBG; Dung lượng: 2 x 16GB; Thế hệ: DDR4; Bus: 3000MHz;'),
('SP0032', 'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 8GB) DDR4 3200MHz (KM-LD4-3200-8GHSB)', 1390000, 47, 3, 10, 'THHI05', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 3200MHz;'),
('SP0033', 'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 16GB) DDR4 3200MHz (KM-LD4-3200-16GHSB)', 2590000, 50, 3, 10, 'THHI05', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR4; Bus: 3200MHz;'),
('SP0034', 'RAM desktop GIGABYTE GP-ARS32G52D5 (2 x 16GB) DDR5 5200MHz (GP-ARS32G52D5)', 7999000, 7, 3, 10, 'THHI09', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 2 x 16GB; Thế hệ: DDR5; Bus: 5200MHz;'),
('SP0035', 'RAM desktop Lexar D5DU016G-R4800GS2A (1 x 16GB) DDR5 4800MHz', 4190000, 2, 3, 10, 'THHI10', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR5; Bus: 4800MHz;'),
('SP0036', 'RAM desktop Lexar LD4AU016G (1 x 16GB) DDR4 3200MHz (B3200GSST)', 1790000, 28, 3, 10, 'THHI10', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR4; Bus: 3200MHz;'),
('SP0037', 'RAM desktop CRUCIAL CT8G48C40U5 (1 x 8GB) DDR5 4800MHz (CT8G48C40U5)', 1490000, 34, 3, 10, 'THHI11', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR5; Bus: 4800MHz;'),
('SP0038', 'RAM desktop Lexar LD4AS008G-B2666GSST (1 x 8GB) DDR4 2666MHz (LD4AS008G-B2666GSST)', 790000, 9, 3, 10, 'THHI10', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 2666MHz;'),
('SP0039', 'RAM desktop CORSAIR CMH64GX5M2B5600C36 (2 x 32GB) DDR5 5600MHz (CMH64GX5M2B5600C36)', 9490000, 5, 3, 10, 'THHI08', 36, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 2 x 32GB; Thế hệ: DDR5; Bus: 5600MHz;'),
('SP0040', 'RAM desktop CORSAIR Ram Desktop Corsair DOMINATOR PLATINUM RGB Black Heatspreader (2 x 8GB)', 4290000, 12, 3, 10, 'THHI08', 36, N'Màu sắc: Đen; Đèn LED: RGB; Dung lượng: 2 x 8GB; Thế hệ: DDR4; Bus: 3200MHz;'),


--CPU 0
('SP0041', N'Quạt CPU Noctua NH-D15', 2770000, 0, 0, 10, N'THHI12', 72, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 140mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: NâuChiều cao(cm): 165cm; Số vòng quay của quạt(RPM): 1500RMP; Độ ồn(dBA): 24.6dB(A); Khối lượng(kg): 1.32 kgSocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2066, Intel LGA 2011, Intel LGA 2011-3, AMD AM4, AMD AM3, AMD AM3+;'),
('SP0042', N'Quạt CPU Noctua NH-U12S DX-3647', 3040000, 14, 0, 10, N'THHI12', 72, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: NâuChiều cao(cm): 158cm; Số vòng quay của quạt(RPM): 2000RMP; Độ ồn(dBA): 22.6dB(A); Khối lượng(kg): 1.018 kgSocket được hỗ trợ: Intel LGA 3647;'),
('SP0043', N'Quạt CPU Noctua NH-U12S DX-3647', 2960000, 0, 0, 10, N'THHI12', 72, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 140mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: ĐenChiều cao(cm): 165cm; Số vòng quay của quạt(RPM): 1500RMP; Độ ồn(dBA): 24.6dB(A); Khối lượng(kg): 1.32 kgSocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2066, Intel LGA 2011, Intel LGA 2011-3, AMD AM4, AMD AM3, AMD AM3+;'),
('SP0044', N'Quạt CPU Golden Field ACF-120 RGB', 250000, 0, 0, 10, N'THHI13', 12, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm; Màu sắc: Đen; Đèn LED: RGBChiều cao(cm): 11cm; Số vòng quay của quạt(RPM): 1800RPM(PWM) ± 10%; Độ ồn(dBA): <=29dB(A)Socket được hỗ trợ: Intel LGA 1151-v2, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2011, Intel LGA 775, Intel LGA 1366, Intel LGA 2011-3, AMD AM3, AMD AM3+;'),
('SP0045', N'Quạt CPU CM Hyper 212 ARGB', 755000, 7.42, 0, 10, N'THHI14', 12, N'Dạng tản nhiệt: Quạt; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Bạc; Đèn LED: RGBSố vòng quay của quạt(RPM): 650-1800 RPM (PWM) ± 5%; Độ ồn(dBA): 8 - 27 DBASocket được hỗ trợ: Intel LGA 1700, Intel LGA 1200, Intel LGA 1151-v2, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, AMD AM4;'),
('SP0046', N'Quạt CPU CM Hyper 212 Spectrum V2', 769000, 6.052, 0, 10, N'THHI14', 12, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen, Bạc; Đèn LED: RGBChiều cao(cm): 15.7cm; Số vòng quay của quạt(RPM): 650-1800 RPM ± 5%; Độ ồn(dBA): 8 - 27 dBASocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, AMD AM4, Intel LGA 1200;'),
('SP0047', N'Quạt CPU Deepcool Gammaxx 400 Red V2 (LED Đỏ)', 570000, 15.965, 0, 10, N'THHI15', 12, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm; Màu sắc: Đen, Bạc; Đèn LED: ĐỏChiều cao(cm): 15.4cm; Số vòng quay của quạt(RPM): 500~1650 RPM±10%; Độ ồn(dBA): ≤27.8 dB(A);  Khối lượng(kg): 0.606kgSocket được hỗ trợ: Intel LGA 1200, Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1366, AMD AM4, AMD AM3+, AMD AM3, AMD AM2+, AMD AM2;'),
('SP0048', N'Quạt CPU Cooler Master HYPER 212 ARGB TURBO', 1150000, 14, 0, 10, N'THHI14', 12, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 2 x 120 mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen; Đèn LED: RGBChiều cao(cm): 15.7cm; Số vòng quay của quạt(RPM): 650-1800 RPM; Độ ồn(dBA): 8 - 27 dBASocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2066, Intel LGA 2011, Intel LGA 775, Intel LGA 1366, Intel LGA 2011-3, AMD AM4;'),
('SP0049', N'Quạt CPU ID-Cooling SE-207-XT Black (2 fan)', 1190000, 17.731, 0, 10, N'THHI16', 24, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 2 x 120 mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen, BạcChiều cao(cm): 15.7cm; Số vòng quay của quạt(RPM): Quạt 1: 700-1800RPM (PWM), Quạt 2: 700-1800RPM (PWM); Khối lượng(kg): 1.3kg (kèm quạt / phụ kiện)Socket được hỗ trợ: Intel LGA 2066, Intel LGA 2011, Intel LGA 1200, Intel LGA 1150, Intel LGA 1151, Intel LGA 1155, Intel LGA 1156, AMD AM4;'),
('SP0050', N'Tản khí Cooler Master MasterAir MA621P TR4 Edition', 1370000, 11.022, 0, 10, N'THHI14', 24, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 1 x 120 mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen, Bạc; Đèn LED: RGBChiều cao(cm): 16.45cm; Số vòng quay của quạt(RPM): 600 - 1800 RPM (PWM) ± 10%; Độ ồn(dBA): 31dBA(Max); Khối lượng(kg): 0.87Socket được hỗ trợ: AMD TR4;'),
('SP0051', N'CPU Intel Core I5-7500 (3.4GHz - 3.8GHz)', 5970000, 60, 0, 10, N'THHI17', 36, N'CPU: Core i5-7500; Series: Core i5; Socket: 1151; Số nhân xử lý: 4; Số luồng xử lý: 4; Kiến trúc: Kaby Lake (14 nm); Code name: Kaby Lake; Thế hệ: Intel Core thế hệ thứ 7; Tốc độ xử lý: 3.4 GHz - 3.8 GHz; Cache: 6MB;'),
('SP0052', N'CPU AMD Ryzen R7 1800X (3.6GHz - 4GHz)', 9240000, 57.8, 0, 10, N'THHI18', 36, N'CPU Ryzen: 7 1800X; Series: Ryzen 7; Socket: AM4; Số nhân xử lý: 8; Số luồng xử lý: 16; Kiến trúc: Zen (14 nm); Code name: Summit Ridge; Thế hệ: AMD Ryzen thế hệ thứ 1; Tốc độ xử lý: 3.6 GHz - 4 GHz; Cache 16MB;'),
('SP0053', N'CPU AMD Ryzen R7 1700X (3.4GHz - 3.8GHz)', 8499000, 54, 0, 10, N'THHI18', 36, N'CPU: Ryzen 7 1700X; Series: Ryzen 7; Socket: AM4; Số nhân xử lý: 8; Số luồng xử lý: 16; Kiến trúc: Zen (14 nm); Code name: Summit Ridge; Thế hệ: AMD Ryzen thế hệ thứ 1; Tốc độ xử lý: 3.4 GHz - 3.8 GHz; Cache: 16MB;'),
('SP0054', N'CPU Intel Core I5-7600 (3.5GHz - 4.1GHz)', 6230000, 59.88, 0, 10, N'THHI17', 36, N'CPU: Core i5-7600; Series: Core i5; Socket: 1151; Số nhân xử lý: 4; Số luồng xử lý: 4; Kiến trúc: Kaby Lake (14 nm); Code name: Kaby Lake; Thế hệ: Intel Core thế hệ thứ 7; Tốc độ xử lý: 3.5 GHz - 4.1 GHz; Cache: 6MB; N'),
('SP0055', N'CPU Intel Core I5-8600K (3.6GHz)', 7300000, 52.14, 0, 10, N'THHI17', 36, N'CPU: Core i5-8600K; Series: Core i5; Socket: 1151-v2; Số nhân xử lý: 6; Số luồng xử lý: 6; Kiến trúc: Coffee Lake (14 nm); Code name: Coffee Lake; Thế hệ: Intel Core thế hệ thứ 8; Tốc độ xử lý: 3.6 GHz; Cache: 9MB;'),
('SP0056', N'CPU INTEL Pentium G6400 (2C/4T, 4.00GHz, 4MB) - 1200', 1899000, 26.33, 0, 10, N'THHI17', 36, N'CPU: Pentium G6400; Series: Pentium; Socket: 1200; Số nhân xử lý: 2; Số luồng xử lý: 4; Kiến trúc: Comet Lake; Code name: Comet Lake; Thế hệ: Intel Pentium Gold; Tốc độ xử lý: 4.00GHz;'),
('SP0057', N'CPU AMD Ryzen 5 2600 (6C/12T, 3.4 GHz - 3.9 GHz, 16MB) - AM4', 3990000, 40, 0, 10, N'THHI18', 36, N'CPU: Ryzen 5 2600; Series: Ryzen 5; Socket: AM4; Số nhân xử lý: 6; Số luồng xử lý: 12; Kiến trúc: Zen+ (12 nm); Code name: Pinnacle Ridge; Thế hệ: AMD Ryzen thế hệ thứ 2; Tốc độ xử lý: 3.4 GHz - 3.9 GHz; Cache: 16MB;'),
('SP0058', N'CPU AMD Ryzen 5 2600X (6C/12T, 3.6 GHz - 4.2 GHz, 16MB) - AM4', 4790000, 40, 0, 10, N'THHI18', 36, N'CPU: Ryzen 5 2600X; Series: Ryzen 5; Socket: AM4; Số nhân xử lý: 6; Số luồng xử lý: 12; Kiến trúc: Zen+ (12 nm); Code name: Pinnacle Ridge; Thế hệ: AMD Ryzen thế hệ thứ 2; Tốc độ xử lý: 3.6 GHz - 4.2 GHz; Cache: 16MB; N'),
('SP0059', N'CPU AMD Ryzen Threadripper 1920X (12C/24T, 3.5 GHz - 4.0 GHz, 39MB) - TR4', 22200000, 66, 0, 10, N'THHI18', 36, N'CPU: Ryzen Threadripper 1920X; Series: Ryzen Threadripper; Socket: TR4; Số nhân xử lý: 12; Số luồng xử lý: 24; Kiến trúc: Zen (14 nm); Thế hệ: AMD Ryzen thế hệ thứ 1; Tốc độ xử lý: 3.5 GHz - 4.0 GHz; Cache: 39MB;'),
('SP0060', N'CPU Intel Core i7-9700K (8C/8T, 3.6 GHz - 4.9 GHz, 12MB) - LGA 1151-v2', 10979000, 42.6, 0, 10, N'THHI17', 36, N'CPU: Core i7-9700K; Series: Core i7; Socket: 1151-v2; Số nhân xử lý: 8; Số luồng xử lý: 8; Kiến trúc: Coffee Lake (14 nm); Code name: Coffee Lake; Thế hệ: Intel Core thế hệ thứ 9; Tốc độ xử lý: 3.6 GHz - 4.9 GHz; Cache: 12MB;'),


--MAIN BOARD 1
('SP0061', N'Main Board ASUS ROG Strix B550-E Gaming (Wi-Fi 6)', 4500000, 5, 1, 1, N'THHI19', 24, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 2.5GbE; cổng HDMI; cổng DisplayPort'),
('SP0062', N'Main Board MSI MPG B450 TOMAHAWK MAX', 2400000, 10, 1, 1, N'THHI20', 24, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 1 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 1GbE; cổng HDMI; cổng DisplayPort'),
('SP0063', N'Main Board Gigabyte B550 AORUS PRO (Wi-Fi 6)', 3500000, 7, 1, 1, N'THHI09', 24, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 2.5GbE; cổng HDMI; cổng DisplayPort'),
('SP0064', N'Main Board ASRock B450M Steel Legend', 1800000, 15, 1, 1, N'THHI21', 24, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 1 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 1GbE; cổng HDMI; cổng DisplayPort'),
('SP0065', N'Main Board ASUS TUF Gaming B460-PLUS (Wi-Fi 6)', 2300000, 8, 1, 1, N'THHI19', 24, N'Intel chipset; LGA1200 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 1GbE; cổng HDMI; cổng DisplayPort'),
('SP0066', N'Main Board MSI MAG B550 TOMAHAWK (Wi-Fi 6)', 3500000, 10, 1, 1, N'THHI20', 24, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 2.5GbE; cổng HDMI; cổng DisplayPort'),
('SP0067', N'Mainboard ASROCK Z490 Extreme4', 4939000, 11.338, 1, 1, N'THHI21', 36, N'Chipset:Z490; Socket:1200; Kích thước:ATX; Khe RAM tối đa:4 khe; Kiểu RAM hỗ trợ:DDR4; Hỗ trợ bộ nhớ tối đa:128GB; Bus RAM hỗ trợ:2800MHz, 2400MHz, 2666MHz, 3200MHz, 3600MHz, 3733MHz, 3866MHz, 4000MHz, 4133MHz, 2933MHz;'),
('SP0068', N'Mainboard ASUS H410M-E', 1640000, 12, 1, 1, N'THHI19', 36, N'Chipset: H410; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe;'),
('SP0069', N'Mainboard ASUS H410M-CS', 1810000, 10, 1, 1, N'THHI19', 36, N'Chipset: H410; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2933MHz, 2800MHz, 2666MHz, 2400MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s; Cổng xuất hình: 1 x HDMI, 1 x VGA/D-sub; Khe PCI: 1 x PCIe 3.0 x16, 1 x PCIe 3.0 x1; Số cổng USB: 2 x USB 3.2 (tối đa 3), 4 x USB 2.0 (tối đa 5); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek ALC887 7.1-Channel High Definition Audio CODEC*;'),
('SP0070', N'Mainboard MSI H510M PRO', 2190000, 10, 1, 1, N'THHI20', 36, N'Chipset: H510; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2666MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 NVMe; Kiểu khe M.2 hỗ trợ: M.2 NVMe;'),
('SP0071', N'Mainboard GIGABYTE H510M-H', 1990000, 12, 1, 1, N'THHI09', 36, N'Chipset: H510; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2400MHz, 2666MHz, 3000MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 1 x VGA/D-sub; Khe PCI: 1 x PCI x161 x PCI x1; Số cổng USB: 2 x USB 3.2 (tối đa 4)4 x USB 2.0/1.1(Tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek® Audio CODECHigh Definition Audio 2/4/5.1/7.1-channel;'),
('SP0072', N'Mainboard ASROCK H510M-HDV', 1830000, 7, 1, 1, N'THHI21', 36, N'Chipset: H510; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s; Cổng xuất hình: 1 x HDMI, 1 x DVI-D, 1 x VGA/D-sub; Khe PCI: 1 x PCIe 4.0 x16, 1 x PCIe 3.0 x1; Số cổng USB: 2 x USB 3.2 (tối đa 4), 4 x USB 2.0 (tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: 7.1 CH HD Audio (Realtek ALC897 Audio Codec);'),
('SP0073', N'Mainboard ASUS PRIME A320M-K', 1700000, 0, 1, 1, N'THHI19', 36, N'Chipset: A320; Socket: AM4; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 32GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 1 x VGA/D-sub; Khe PCI: 3 x PCIe 3.0/2.0 x16, 2 x PCIe 2.0 x1; Đèn LED: Đơn sắc; Số cổng USB: 4 x USB 3.1, hỗ trợ tối đa 2 x USB 3.0, 2 x USB 2.0 (tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek ALC887 8-Channel High Definition Audio CODEC;'),
('SP0074', N'Mainboard ASUS ROG MAXIMUS XI GENE', 8906000, 10, 1, 1, N'THHI19', 36, N'Chipset: Z390; Socket: 1151-v2; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3866MHz, 4000MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 4 x M.2 NVMe; Kiểu khe M.2 hỗ trợ: M.2 NVMe; Cổng xuất hình: 1 x HDMI; Khe PCI: 1 x PCIe 3.0 x16, 1 x PCIe 3.0 x4; Đèn LED: RGB; Số cổng USB: 1 x USB Type C, 7 x USB 3.1 (tối đa 12), 6 x USB 2.0; LAN: 1 x LAN 1 Gb/s; Kết nối không dây: Bluetooth 5.0, WiFi 802.11 a/b/g/n/ac; Âm thanh: ROG SupremeFX 8-Channel CODEC;'),
('SP0075', N'Mainboard ASUS Prime A320M-E', 1550000, 10.32, 1, 1, N'THHI19', 36, N'Chipset: A320; Socket: AM4; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 32GB; Bus RAM hỗ trợ: 2400MHz, 2666MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe, 1 x M.2 SATA; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 1 x DVI-D, 1 x VGA/D-sub; Khe PCI: 2 x PCIe 3.0/2.0 x16, 2 x PCIe 2.0 x1; Số cổng USB: 5 x USB 3.1 (tối đa 7), 0 x USB 2.0 (tối đa 4); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek® ALC887 8-Channel;'),
('SP0076', N'Mainboard GIGABYTE Z490 AORUS PRO AX', 7090000, 8.195, 1, 1, N'THHI09', 36, N'Chipset: Z490; Socket: 1200; Kích thước: ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3733MHz, 3866MHz; Lưu trữ: Hỗ trợ Intel Optane, 2 x M.2 SATA/NVMe, 6 x SATA 3 6Gb/s; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI; Khe PCI: 3 x PCI Express x16, 2 x PCI Express x1; Multi-GPU: AMD CrossFire, NVIDIA SLI; Đèn LED: RGB; Số cổng USB: 1 x USB Type-C (tối đa 2), 5x USB 3.2 (tối đa 7), 4x USB 2.0 (tối đa 8); LAN: 1 x LAN 1 Gb/s; Kết nối không dây: WiFi 802.11 ax, Bluetooth 5.1; Âm thanh: Realtek® ALC1220-VB codec;'),
('SP0077', N'Mainboard GIGABYTE Z490 Aorus Xtreme', 19190000, 7.613, 1, 1, N'THHI09', 36, N'Chipset: Z490; Socket: 1200; Kích thước: Extended-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3733MHz, 3866MHz; Lưu trữ: 1 x M.2 NVMe, Hỗ trợ Intel Optane, 2 x M.2 SATA/NVMe, 6 x SATA 3 6Gb/s; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 2 x Thunderbolt 3; Khe PCI: 3 x PCI Express x16 slot; Multi-GPU: NVIDIA SLI, AMD CrossFire; Đèn LED: RGB; Số cổng USB: 2 x USB type-C (Tối đa 3), 6 x USB 3.2 (tối đa 8), 2 x USB 2.0 (tối đa 6); LAN: 2 x LAN 1 Gb/s; Kết nối không dây: WiFi 802.11 ax, Bluetooth 5.1; Âm thanh: Realtek ALC1220-VB codec+ESS ES9218P DAC (front panel audio), Realtek ALC1220-VB codec+ESS ES9018K2M DAC+TI OPA1622 operational amplifier (rear panel audio), Support for DTS:X Ultra, High Definition Audio, 2/4/5.1/7.1-channel;'),
('SP0078', N'Mainboard GIGABYTE Z490 Aorus Xtreme WF', 28790000, 39, 1, 1, N'THHI09', 36, N'Chipset: Z490; Socket: 1200; Kích thước: Extended-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2133MHz, 2400MHz, 2666MHz, 2800MHz, 2933MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3400MHz;'),
('SP0079', N'Mainboard MSI MEG Z490 GODLIKE', 20599000, 12.76, 1, 1, N'THHI20', 36, N'Chipset: Z490; Socket: 1200; Kích thước: Extended-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3733MHz, 3866MHz, 4000MHz;'),
('SP0080', N'Mainboard GIGABYTE B460M-DS3H V2', 2180000, 12.76, 1, 1, N'THHI09', 36, N'Chipset: H470; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2133MHz, 2400MHz, 2666MHz, 2933MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 NVMe, Hỗ trợ Intel Optane; Kiểu khe M.2 hỗ trợ: M.2 NVMe; Cổng xuất hình: 1 x HDMI, 1 x DVI-D, 1 x VGA/D-sub; Khe PCI: 2 x PCI Express x16, 2 x PCI Express x1; Số cổng USB: 3 x USB 3.2 (tối đa 5), 2 x USB 2.0 (tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek® Audio CODEC High Definition Audio 2/4/5.1/7.1-channel;'),


--RAM 3
('SP0081', N'RAM Kingston HyperX Fury 8GB DDR4 3200MHz', 900000, 10, 3, 10, 'THHI23', 12, N'Trọng lượng sản phẩm: 50g;Màu sắc: Đen; Dung lượng RAM: 8GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Không; Đèn LED: Không'),
('SP0082', N'RAM Corsair Vengeance RGB Pro 16GB DDR4 3200MHz', 1800000, 5, 3, 10, 'THHI08', 12, N'Trọng lượng sản phẩm: 70g;Màu sắc: Trắng; Dung lượng RAM: 16GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0083', N'RAM G.Skill Trident Z Neo 32GB DDR4 3600MHz', 3600000, 0, 3, 10, 'THHI06', 12, N'Trọng lượng sản phẩm: 80g;Màu sắc: Đen; Dung lượng RAM: 32GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0084', N'RAM HyperX Predator 64GB DDR4 3200MHz', 7200000, 15, 3, 10, 'THHI23', 24, N'Trọng lượng sản phẩm: 100g;Màu sắc: Đen; Dung lượng RAM: 64GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không'),
('SP0085', N'RAM Adata XPG Spectrix D50 RGB 16GB DDR4 3600MHz', 1900000, 8, 3, 10, 'THHI24', 12, N'Trọng lượng sản phẩm: 70g; Màu sắc: Đen; Dung lượng RAM: 16GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0086', N'RAM Crucial Ballistix RGB 32GB DDR4 3200MHz', 3500000, 10, 3, 10, 'THHI11', 24, N'Trọng lượng sản phẩm: 80g; Màu sắc: Trắng; Dung lượng RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0087', N'RAM Kingston HyperX Fury 16GB DDR4', 1000000, 10, 3, 10, 'THHI23', 36, N'Trọng lượng sản phẩm:100g;Màu sắc: Xám; Dung lượng RAM: 16 GB; Loại RAM:	DDR4; Tốc độ RAM: 2666 MHz; Hỗ trợ EEC:	Không; Tản nhiệt: Có; Đèn LED: Không'),
('SP0088', N'RAM Corsair Vengeance LPX 16GB DDR4', 1200000, 10, 3, 10, 'THHI08', 36, N'Trọng lượng sản phẩm:100g;Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM:	DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC:	Không; Tản nhiệt: Có; Đèn LED: Không'),
('SP0089', N'RAM G.Skill Ripjaws V 16GB DDR4', 1250000, 10, 3, 10, 'THHI05', 36, N'Trọng lượng sản phẩm:100g;Màu sắc: Đỏ; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không'),
('SP0090', N'RAM Patriot Viper Steel 16GB DDR4', 1300000, 10, 3, 10, 'THHI25', 36, N'Trọng lượng sản phẩm:100g;Màu sắc: Xám; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không'),
('SP0091', N'RAM Crucial Ballistix 16GB DDR4', 1350000, 10, 3, 10, 'THHI11', 36, N'Trọng lượng sản phẩm:100g; Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED:	RGB'),
('SP0092', N'Apacer RAM 8GB DDR4 2666MHz', 1500000, 5, 3, 10, 'THHI26', 36, N'Trọng lượng sản phẩm: 200g; Dung lượng RAM: 8 GB; Loại RAM: DDR4; Tốc độ RAM: 2666 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Không; Đèn LED: Không'),
('SP0093', N'Geil RAM 16GB DDR4 3200MHz', 2800000, 10, 3, 10, 'THHI07', 36, N'Trọng lượng sản phẩm: 250g; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0094', N'Gigabyte AORUS RAM 32GB DDR4 3600MHz', 6500000, 15, 3, 10, 'THHI09', 36, N'Trọng lượng sản phẩm: 280g; Dung lượng RAM: 32 GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0095', N'HP V6 DDR4 8GB 2666MHz', 1700000, 5, 3, 10, 'THHI02', 36, N'Trọng lượng sản phẩm: 200g; Dung lượng RAM: 8 GB; Loại RAM: DDR4; Tốc độ RAM: 2666 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Không; Đèn LED: Không'),
('SP0096', N'Kingmax Zeus Dragon DDR4 16GB 3200MHz', 3000000, 10, 3, 10, 'THHI05', 36, N'Trọng lượng sản phẩm: 200g; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: Không'),
('SP0097', N'Apacer Panther Rage DDR4 32GB 3600MHz', 7500000, 15, 3, 10, 'THHI26', 36, N'Trọng lượng sản phẩm: 250g; Dung lượng RAM: 32 GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0098', N'Ram Apacer Panther Rage DDR4 16GB (2x8GB) 3000MHz', 1500000, 5, 3, 10, 'THHI26', 36, N'Trọng lượng sản phẩm: 200g; Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3000 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB'),
('SP0099', N'Ram Geil EVO POTENZA DDR4 16GB (2x8GB) 2400MHz', 1400000, 7, 3, 10, 'THHI07', 36, N'Trọng lượng sản phẩm: 250g; Màu sắc: Đỏ; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 2400 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không'),
('SP0100', N'Ram Gigabyte AORUS RGB DDR4 16GB (2x8GB) 3200MHz', 1700000, 10, 3, 10, 'THHI09', 36, N'Trọng lượng sản phẩm: 300g; Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB'),

--Ổ CỨNG 4 100--->110
('SP0101', N'Ổ cứng SSD Transcend 370S 128GB 2.5" SATA 3', 1239000, 5, 5, 5, 'THHI37', 36, N'Dung lượng: 128GB; Kết nối: SATA 3; Bộ nhớ NAND: Không; Kích thước: 2.5"; Tốc độ đọc: 560MB/s; Tốc độ ghi: 480MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;'),
('SP0102', N'Ổ cứng HDD Western Digital Blue 1TB 3.5" SATA 3', 1350000, 3, 5, 10, 'THHI39', 24, N'Dung lượng: 1TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 64MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Xanh dương;'),
('SP0103', N'Ổ cứng SSD Crucial P3 500GB M.2 NVMe PCIe Gen3', 2250000, 0, 5, 10, 'THHI11', 36, N'Dung lượng: 500GB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D NAND; Tốc độ đọc: 3400MB/s; Tốc độ ghi: 3000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;'),
('SP0104', N'Ổ cứng SSD Kingston A2000 256GB M.2 NVMe PCIe Gen3', 1099000, 8, 5, 10, 'THHI23', 36, N'Dung lượng: 256GB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D NAND; Tốc độ đọc: 2200MB/s; Tốc độ ghi: 2000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;'),
('SP0105', N'Ổ cứng HDD Seagate BarraCuda 2TB 3.5" SATA 3', 1550000, 0, 5, 10, 'THHI38', 24, N'Dung lượng: 2TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 256MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Đen;'),
('SP0106', N'Ổ cứng SSD ADATA XPG SX8200 Pro 512GB M.2 NVMe PCIe Gen3', 1899000, 5, 5, 10, 'THHI24', 36, N'Dung lượng: 512GB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D TLC; Tốc độ đọc: 3500MB/s; Tốc độ ghi: 2300MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;'),
('SP0107', N'Ổ cứng SSD Intel 660p 1TB M.2 NVMe PCIe Gen3', 2799000, 0, 5, 10, 'THHI17', 36, N'Dung lượng: 1TB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D TLC; Tốc độ đọc: 1800MB/s; Tốc độ ghi: 1800MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Xanh dương;'),
('SP0108', N'Ổ cứng HDD Toshiba P300 3TB 3.5" SATA 3', 2199000, 0, 5, 10, 'THHI40', 24, N'Dung lượng: 3TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 64MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Đen;'),
('SP0109', N'Ổ cứng SSD HGST Ultrastar DC HC310 4TB 3.5" SATA 3', 5599000, 0, 5, 10, 'THHI41', 36, N'Dung lượng: 4TB; Kết nối: SATA 3; Bộ nhớ NAND: Không; Tốc độ đọc: 255MB/s; Tốc độ ghi: 205MB/s; Kiểu ổ cứng: SSD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Xanh lá cây;'),
('SP0110', N'Ổ cứng HDD Western Digital Red Pro 10TB 3.5" SATA 3', 9899000, 0, 5, 10, 'THHI39', 60, N'Dung lượng: 10TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 256MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Đỏ;'),
('SP0174', N'Ổ cứng SSD Intel 660p 512GB M.2 NVMe', 2499000, 15, 5, 10, 'THHI17', 36, N'Dung lượng: 512GB; Kết nối: M.2 NVMe; Bộ nhớ NAND: TLC; Tốc độ đọc: 1500MB/s; Tốc độ ghi: 1000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Xanh lá cây;'),
('SP0175', N'Ổ cứng HDD ADATA HD770G 1TB USB 3.2', 1299000, 5, 5, 10, 'THHI24', 24, N'Dung lượng: 1TB; Kết nối: USB 3.2; Tốc độ quay: Không; Kiểu ổ cứng: HDD; Kích thước: 2.5"; Màu sắc của Ổ cứng: Đen;'),
('SP0176', N'Ổ cứng SSD Kingston A2000 1TB M.2 NVMe', 2899000, 10, 5, 10, 'THHI23', 36, N'Dung lượng: 1TB; Kết nối: M.2 NVMe; Bộ nhớ NAND: TLC; Tốc độ đọc: 2200MB/s; Tốc độ ghi: 2000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;'),
('SP0177', N'Ổ cứng HDD Toshiba P300 3TB 7200rpm', 1799000, 0, 5, 10, 'THHI40', 24, N'Dung lượng: 3TB; Tốc độ quay: 7200rpm; Kiểu ổ cứng: HDD; Màu sắc của Ổ cứng: Xanh đen;'),
('SP0178', N'Ổ cứng SSD HGST Ultrastar DC SS530 960GB SATA', 3799000, 10, 5, 10, 'THHI41', 36, N'Dung lượng: 960GB; Kết nối: SATA; Bộ nhớ NAND: TLC; Tốc độ đọc: 540MB/s; Tốc độ ghi: 485MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;'),
('SP0179', N'Ổ cứng HDD Seagate Barracuda ST2000DM008 2TB 7200rpm', 1299000, 5, 5, 10, 'THHI38', 24, N'Dung lượng: 2TB; Tốc độ quay: 7200rpm; Kiểu ổ cứng: HDD; Màu sắc của Ổ cứng: Đen;'),
('SP0180', N'Ổ cứng SSD ADATA XPG SX6000 Lite 256GB M.2 PCIe', 699000, 15, 5, 10, 'THHI24', 36, N'Dung lượng: 256GB; Kết nối: M.2 PCIe; Bộ nhớ NAND: TLC; Tốc độ đọc: 1800MB/s; Tốc độ ghi: 1200MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;'),
('SP0181', N'Ổ cứng SSD Intel 660p Series 1TB M.2 PCIe NVMe', 3999000, 0, 5, 10, 'THHI17', 36, N'Dung lượng: 1TB; Kết nối: M.2 PCIe NVMe; Bộ nhớ NAND: QLC; Tốc độ đọc: 1800MB/s; Tốc độ ghi: 1800MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Xám;'),
('SP0182', N'Ổ cứng SSD Transcend 230S 128GB 2.5" SATA 3" SATA 3', 699000, 5, 4, 5, 'THHI37', 36, 'Dung lượng: 128GB; Kết nối: SATA 3; Bộ nhớ NAND: Không; Kích thước: 2.5"; Tốc độ đọc: 560MB/s; Tốc độ ghi: 520MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Trắng; '),
('SP0183', N'Ổ cứng HDD Western Digital Black 4TB 3.5" SATA 3 - WD4005FZBX', 4499000, 5, 4, 5, 'THHI42', 36, 'Dung lượng: 4TB; Kết nối: SATA 3; Bộ nhớ NAND: Không; Kích thước: 3.5; Tốc độ vòng quay: 7200RPM; Cache: 256MB; Kiểu ổ cứng: HDD; Màu sắc của Ổ cứng: Đen; '),
--NGUỒN 5 111 --->120
('SP0111', N'Nguồn máy tính Golden Field Dragon GTX480 - 400W', 559000, 18, 5, 0.1, 'THHI13', 12, N'Công suất tối đa: 400W; Cáp rời: không hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 1 x 8-pin (6+2) PCIE, 4 x SATA; Quạt làm mát: 1 x 120 mm;'),
('SP0112', N'Nguồn máy tính Thermaltake Toughpower GF1 850W 80+ Gold RGB', 2999000, 15, 5, 0.1, 'THHI31', 24, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 140 mm;'),
('SP0113', N'Nguồn máy tính XFX ProSeries P1-850S-NLB9 850W 80+ Gold Full Modular', 3499000, 12, 5, 0.1, 'THHI32', 24, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 135 mm;'),
('SP0114', N'Nguồn máy tính FSP Dagger Pro 650W 80+ Gold Full Modular', 1999000, 10, 5, 0.1, 'THHI33', 24, N'Công suất tối đa: 650W; Cáp rời: hỗ trợ; Chuẩn kích thước: SFX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 4 x 8-pin (6+2) PCIE, 3 x SATA, 2 x Peripheral; Quạt làm mát: 1 x 92 mm;'),
('SP0115', N'Nguồn máy tính Seasonic Prime GX-850 850W 80+ Gold Full Modular', 3499000, 15, 5, 0.1, 'THHI34', 36, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 135 mm;'),
('SP0116', N'Nguồn máy tính CoolerMaster MWE 750W 80+ White', 1499000, 0, 5, 0.1, 'THHI14', 24, N'Công suất tối đa: 750W; Cáp rời: không hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 4 x 8-pin (6+2) PCIE, 6 x SATA, 3 x Peripheral; Quạt làm mát: 1 x 120 mm;'),
('SP0117', N'Nguồn máy tính XFX XTR 550W 80+ Gold Full Modular', 1299000, 8, 5, 0.1, 'THHI32', 24, N'Công suất tối đa: 550W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 2 x 8-pin (6+2) PCIE, 9 x SATA, 4 x Peripheral; Quạt làm mát: 1 x 135 mm;'),
('SP0118', N'Nguồn máy tính Huntkey MVP Pro 750W 80+ Gold Full Modular', 1699000, 12, 5, 0.1, 'THHI35', 36, N'Công suất tối đa: 750W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 4 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 135 mm;'),
('SP0119', N'Nguồn máy tính Corsair RM850x 850W 80+ Gold Full Modular', 2499000, 10, 5, 0.1, 'THHI08', 60, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 4 x Peripheral; Quạt làm mát: 1 x 135 mm;'),
('SP0120', N'Nguồn máy tính FSP Hydro PTM+ 1200W 80+ Platinum Full Modular', 5699000, 15, 5, 0.1, 'THHI33', 48, N'Công suất tối đa: 1200W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 8 x 8-pin (6+2) PCIE, 12 x SATA, 6 x Peripheral; Quạt làm mát: 1 x 135 mm;'),

--CASE 6 121-->130
('SP0121', N'Case máy tính MSI MAG Series', 2000000, 10, 5, 0.1, 'THHI20', 24, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 45.8 x 23 x 51.4 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";'),
('SP0122', N'Case máy tính MSI MPG Series', 2500000, 5, 5, 0.1, 'THHI20', 24, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 47.5 x 23.6 x 51.4 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";'),
('SP0123', N'Case máy tính Cooler Master Masterbox Series', 1500000, 12, 5, 0.1, 'THHI14', 24, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 45.2 x 20.8 x 45.5 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";'),
('SP0124', N'Case máy tính Thermaltake Core Series', 1800000, 15, 5, 0.1, 'THHI31', 24, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 46.9 x 21 x 49.8 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 3 x 2.5";'),
('SP0125', N'Case máy tính Thermaltake View Series', 3200000, 8, 5, 0.1, 'THHI31', 24, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 52 x 23 x 51 cm; Loại case: Full Tower; Hỗ trợ mainboard: E-ATX, ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 6 x 2.5";'),
('SP0126', N'Case máy tính Corsair Obsidian Series', 2800000, 10, 5, 0.1, 'THHI08', 24, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 54 x 23 x 50 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 3 x 3.5", 4 x 2.5";'),
('SP0127', N'Case máy tính NZXT H Series', 2500000, 12, 5, 0.1, 'THHI28', 24, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 50 x 22 x 48 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";'),
('SP0128', N'Case máy tính Cooler Master MasterBox Series', 1500000, 18, 5, 0.1, 'THHI14', 24, N'Màu sắc: Đen; Chất liệu: Thép, Nhựa ABS; Kích thước: 45.6 x 20.8 x 45.8 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";'),
('SP0129', N'Case máy tính Phanteks Eclipse Series', 2800000, 15, 5, 0.1, 'THHI29', 24, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 45.7 x 21 x 47.2 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 3 x 2.5";'),
('SP0130', N'Case máy tính Corsair Carbide Series', 1800000, 10, 5, 0.1, 'THHI08', 24, N'Màu sắc: Trắng; Chất liệu: Thép, Nhựa ABS; Kích thước: 46.6 x 21 x 47.6 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 3 x 2.5";'),

--VGA 2
('SP0131', N'VGA NVIDIA GeForce RTX 3080', 25000000, 0, 2, 10, 'THHI22', 36, N'10GB GDDR6X, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a'),
('SP0132', N'VGA AMD Radeon RX 6800 XT', 20000000, 0, 2, 10, 'THHI23', 36, N'16GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4'),
('SP0133', N'VGA NVIDIA GeForce RTX 3070', 18000000, 0, 2, 10, 'THHI22', 36, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a'),
('SP0134', N'VGA AMD Radeon RX 6700 XT', 15000000, 0, 2, 10, 'THHI18', 36, N'12GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4'),
('SP0135', N'VGA NVIDIA GeForce RTX 3060 Ti', 13000000, 0, 2, 10, 'THHI22', 36, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a'),
('SP0136', N'VGA AMD Radeon RX 6600 XT', 10000000, 0, 2, 10, 'THHI18', 36, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4'),
('SP0137', N'VGA NVIDIA GeForce RTX 3090', 40000000, 0, 2, 10, 'THHI22', 36, N'24GB GDDR6X, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a'),
('SP0138', N'VGA AMD Radeon RX 6900 XT', 35000000, 0, 2, 10, 'THHI18', 36, N'16GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4'),
('SP0139', N'VGA NVIDIA GeForce GTX 1660 Super', 8000000, 0, 2, 10, 'THHI22', 24, N'6GB GDDR6, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0140', N'VGA AMD Radeon RX 5500 XT', 6000000, 0, 2, 10, 'THHI18', 24, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0141', N'VGA NVIDIA GeForce GTX 1650 Super', 5000000, 0, 2, 10, 'THHI22', 24, N'4GB GDDR6, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0142', N'VGA AMD Radeon RX 580', 4000000, 0, 2, 10, 'THHI18', 24, N'8GB GDDR5, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0143', N'VGA NVIDIA GeForce GT 1030', 2000000, 0, 2, 10, 'THHI22', 12, N'2GB GDDR5, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0144', N'VGA AMD Radeon RX 5700 XT', 12000000, 0, 2, 10, 'THHI18', 36, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0145', N'VGA NVIDIA GeForce RTX 3060', 10000000, 0, 2, 10, 'THHI22', 36, N'12GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a'),
('SP0146', N'VGA AMD Radeon RX 550', 1500000, 0, 2, 10, 'THHI18', 12, N'4GB GDDR5, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0147', N'VGA NVIDIA GeForce GT 710', 1000000, 0, 2, 10, 'THHI22', 12, N'2GB DDR3, Giao diện PCIe 2.0, Cổng HDMI 1.4, Cổng DVI-D, Cổng VGA'),
('SP0148', N'VGA AMD Radeon RX 5600 XT', 8000000, 0, 2, 10, 'THHI18', 24, N'6GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0149', N'VGA NVIDIA GeForce GT 1030 DDR4', 1500000, 0, 2, 10, 'THHI22', 12, N'2GB DDR4, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4'),
('SP0150', N'VGA AMD Radeon RX 5500', 2500000, 0, 2, 10, 'THHI18', 24, N'4GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1'),


--TẢN NHIỆT 7
('SP0151', N'Tản nhiệt CPU Corsair H45', 900000, 5,7, 10, 'THHI08', 12, 'Kích thước: 120 x 120 x 25 mm; Đèn: Không; Dạng tản nhiệt: Tản nhiệt chất lỏng; Công suất tản nhiệt: 0.15 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.8 W; Tốc độ quạt: 1700 vòng/phút; Socket hỗ trợ: Intel LGA 1200, 115x, 1366, 2011, 2011-3, AMD AM2, AM3, FM1, FM2'),
('SP0152', N'Tản nhiệt CPU Cooler Master Hyper 212 RGB Black Edition', 800000, 0,7, 10, 'THHI14', 24, 'Kích thước: 120 x 79.6 x 158.8 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.37 A; Điện áp: 12 V; Điện năng tiêu thụ: 4.44 W; Tốc độ quạt: 650-2000 vòng/phút; Socket hỗ trợ: Intel LGA 2066, 2011-v3, 2011, 1200, 1151, 1150, 1155, 1156, AMD AM4, AM3+, AM3, AM2+, AM2, FM2+, FM2, FM1'),
('SP0153', N'Tản nhiệt CPU Thermaltake UX100', 500000, 10,7, 10, 'THHI31', 12, N'Kích thước: 120 x 120 x 65 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.27 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.24 W; Tốc độ quạt: 1800 vòng/phút; Socket hỗ trợ: Intel LGA 1200, 1151, 1150, 1155, 1156, AMD AM4, FM2+, FM2, FM1, AM3+, AM3, AM2+, AM2'),
('SP0154', N'Tản nhiệt CPU Corsair H100i RGB Platinum SE', 2900000, 10,7, 10, 'THHI08', 12, N'Kích thước: 277 x 120 x 27 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản nước; Công suất tản nhiệt: 0.22 A; Điện áp: 12 V; Điện năng tiêu thụ: 2.88 W; Tốc độ quạt: 2400 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156, Intel LGA 1200, Intel LGA 2011/2066, AMD Socket AM4, AMD Socket sTRX4, AMD Socket TR4;'),
('SP0155', N'Tản nhiệt CPU Cooler Master MasterAir MA610P', 1300000, 5,7, 10, 'THHI14', 24, N'Kích thước: 122.3 x 112.8 x 166.5 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.28 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.36 W; Tốc độ quạt: 800-2000 vòng/phút; Socket hỗ trợ: Intel LGA 2066/2011-3/2011/1366/1200/1156/1155/1151/1150, AMD Socket AM4/AM3+/AM3/AM2+/AM2/FM2+/FM2/FM1;'),
('SP0156', N'Tản nhiệt CPU Noctua NH-D15 Chromax.Black', 3900000, 0,7, 10, 'THHI12', 72, N'Kích thước: 165 x 150 x 161 mm; Đèn: Không; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.22 A; Điện áp: 12 V; Điện năng tiêu thụ: 2.64 W; Tốc độ quạt: 300-1500 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/2011-0/2011-3/2066, AMD Socket AM2+/AM3+/FM1/FM2+/AM4;'),
('SP0157', N'Tản nhiệt CPU Corsair A500', 720000, 5,7, 10, 'THHI08', 24, N'Kích thước: 169mm x 149mm x 160mm; Đèn: Không; Dạng tản nhiệt: Tản khí kép; Công suất tản nhiệt: 0.23A; Điện áp: 12V; Điện năng tiêu thụ: 2.76W; Tốc độ quạt: 2400 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/1200/2011/2066 và AMD AM2/AM3/AM4/FM1/FM2.'),
('SP0158', N'Tản nhiệt CPU Kingmax G50 RGB', 520000, 7,7, 10, 'THHI05', 36, N'Kích thước: 130mm x 130mm x 157mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.2A; Điện áp: 12V; Điện năng tiêu thụ: 2.4W; Tốc độ quạt: 1800 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/1200/1366/2011/2066 và AMD AM2/AM3/AM4/FM1/FM2.'),
('SP0159', N'Tản nhiệt CPU Klevv CRAS X RGB', 840000, 0,7, 10, 'THHI07', 24, N'Kích thước: 128mm x 148mm x 159mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí kép; Công suất tản nhiệt: 0.3A; Điện áp: 12V; Điện năng tiêu thụ: 3.6W; Tốc độ quạt: 1800 vòng/phút; Socket hỗ trợ: Intel LGA 775/1150/1151/1155/1156/1366/2011/2066 và AMD AM2/AM3/AM4/FM1/FM2.'),
('SP0160', N'Cooler Master Hyper 212 RGB Black Edition', 900000, 5,7, 10, 'THHI14', 36, N'Kích thước: 120 x 120 x 25 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.16 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.92 W; Tốc độ quạt: 1500 vòng/phút; Socket hỗ trợ: Không;'),
('SP0161', N'Noctua NH-D15S', 1800000, 0,7, 10, 'THHI12', 60, N'Kích thước: 160 x 150 x 135 mm; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.16 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.92 W; Tốc độ quạt: 1500 vòng/phút; Socket hỗ trợ: AMD AM4, AM3(+), FM2(+), Intel LGA1200, LGA115x (LGA1150, LGA1151, LGA1155, LGA1156), LGA2011-0 & LGA2011-3 (Square ILM);'),
('SP0162', N'be quiet! Dark Rock Pro 4', 2100000, 0,7, 10, 'THHI47', 36, N'Kích thước: 145.7 x 136 x 162.8 mm; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.16 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.92 W; Tốc độ quạt: 1500 vòng/phút; Socket hỗ trợ: Intel LGA 1200 / 1150 / 1151 / 1155 / 1156 / 1366 / 2011(-3) Square ILM / 2066, AMD AM4 / AM3(+) / AM2(+) / FM2(+) / FM1;'),
('SP0163', N'Cooler Master Hyper 212 RGB', 570000, 0,7, 10, 'THHI14', 24, N'Kích thước: 120 x 79.6 x 158.8 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.26 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.12 W; Tốc độ quạt: 650 - 2000 RPM; Socket hỗ trợ: Intel: LGA 2066/2011-3/2011/1366/1200/1156/1155/1151/1150/775, AMD: AM4/AM3+/AM3/AM2+/AM2/FM2+/FM2/FM1'),
('SP0164', N'Noctua NH-D15', 2000000, 0,7, 10, 'THHI12', 36, N'Kích thước: 165 x 150 x 161 mm; Đèn: Không; Dạng tản nhiệt: Tản khí kép; Công suất tản nhiệt: 0.15 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.8 W; Tốc độ quạt: 300 - 1500 RPM; Socket hỗ trợ: Intel LGA2066, LGA2011-0 & LGA2011-3 (Square ILM), LGA1200, LGA1156, LGA1155, LGA1151, LGA1150 & AMD AM2, AM2+, AM3, AM3+, AM4, FM1, FM2, FM2+ (backplate required), TR4 & sTRX4 (pre-installed).'),
('SP0165', N'Tản nhiệt CPU ID Cooling SE-234-ARGB', 450000, 0,7, 10, 'THHI16', 12, N'Kích thước: 120 x 76 x 154.5 mm; Đèn: LED ARGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.25 A; Điện áp: 12 V; Điện năng tiêu thụ: 3 W; Tốc độ quạt: 700 - 1800 RPM; Socket hỗ trợ: Intel LGA 775/1150/1151/1155/1156/1366/2011/2066, AMD FM1/FM2/FM2+/AM2/AM2+/AM3/AM3+/AM4'),
('SP0166', N'Tản nhiệt CPU Be Quiet! Dark Rock Slim', 1390000, 0,7, 10, 'THHI47', 24, N'Kích thước: 120 x 48.4 x 159.4 mm; Đèn: Không; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.22 A; Điện áp: 12 V; Điện năng tiêu thụ: 2.64 W; Tốc độ quạt: 1200 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/1200, AMD AM4'),
('SP0167', N'Tản nhiệt CPU Cooler Master Hyper 212 RGB Black Edition', 350000, 0,7, 10, 'THHI14', 12, N'Kích thước: 120 x 79.6 x 158.8 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.37 A; Điện áp: 12 V; Điện năng tiêu thụ: 4.44 W; Tốc độ quạt: 650 - 2000 vòng/phút; Socket hỗ trợ: AMD: AM4, AM3+, AM3, AM2+, AM2, FM2+, FM2, FM1; Intel: LGA 2066, 2011-v3, 2011, 1366, 1151, 1150, 1155, 1156, 1200'),
('SP0168', N'Tản nhiệt CPU Corsair Hydro Series H100i RGB Platinum SE', 3700000, 5,7, 10, 'THHI08', 12, N'Kích thước: 277 x 120 x 27 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản nước; Công suất tản nhiệt: 0.276 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.31 W; Tốc độ quạt: 2400 vòng/phút; Socket hỗ trợ: AMD: AM2, AM3, AM4, FM1, FM2, sTRX4, TR4; Intel: 1200, 1150, 1151, 1155, 1156, 1366, 2011, 2011-3, 2066'),


--BÀN PHÍM 9
('SP0169', N'Bàn phím cơ K95 Platinum XT', 2799000, 0, 9, 10, 'THHI08', 12, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Có; LED: RGB; Tần số phản hồi: 1000 Hz; Khả năng chống nước: IP32; Các phím Macro: Có'),
('SP0170', N'Bàn phím cơ Ducky One 2 SF', 2499000, 0, 9, 10, 'THHI06', 12, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Không; LED: Không; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không'),
('SP0171', N'Bàn phím cơ Keychron K2', 2399000, 0, 9, 10, 'THHI15', 12, N'Loại bàn phím: cơ; Kết nối: Bluetooth/USB; Bàn phím số: Có; LED: RGB; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không'),
('SP0172', N'Bàn phím cơ logitech Pok3r RGB', 3499000, 0, 9, 10, 'THHI31', 12, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Không; LED: RGB; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không'),
('SP0173', N'Bàn phím cơ FC900R PD', 3299000, 0, 9, 10, 'THHI40', 12, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Có; LED: Không; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không')
--Địa chỉ
INSERT INTO DiaChi (maDiaChi, soNha, duong, huyen, thanhPho, quocgia)
VALUES 
('DC0000','15/9', N'Phạm Văn Chiêu', N'Gò Vấp', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0001','132', N'Nguyễn Thái Sơn', N'Thủ Đức', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0002','25', N'Cộng Hòa', N'Tân Bình', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0003','158/9/8', N'Trường Chinh', N'Bình Thạnh', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0004','25/8', N'Nguyễn Thái Học', N'Phú Nhuận', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0005','158', N'6', N'Tân Phú', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0006','26', N'7', N'1', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0007','45/8', N'8', N'2', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0008','158/9', N'Hải Thượng Lãng Ông', N'3', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0009','688/9', N'10', N'4', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0010','11/9', N'11', N'5', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0011','121/9', N'12', N'6', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0012','2', N'13', N'7', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0013','11/9', N'14', N'8', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0014','114/9', N'15', N'9', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0015','113/9', N'16', N'10', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0016','256/8', N'17', N'11', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0017','276/8', N'18', N'12', N'Thành phố Hồ Chí Minh', N'Việt Nam'),
('DC0018','276/8', N'Phố Bạch Mai', N'Hai Bà Trưng', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0019','326/8', N'Tôn Đức Thắng', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0020','286/8', N'Phố Hoàng Văn Thụ', N'Ba Đình', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0021','6/8', N'Phạm Văn Đồng', N'Cầu Giấy', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0022','56/8', N'Nguyễn Trãi', N'Thanh Xuân', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0023','25/8', N'Phố Láng', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0024','26/8', N'Lạc Long Quân', N'Tây Hồ', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0025','756/8', N'Phố Vũ Trọng Phụng', N'Thanh Xuân', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0026','46/8', N'Hồ Tùng Mậu', N'Cầu Giấy', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0027','470/4', N'Phố Nguyễn Khánh Toàn', N'Cầu Giấy', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0028','460/88', N'Trần Đại Nghĩa', N'Hai Bà Trưng', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0029','46/8/16', N'Phố Lê Văn Lương', N'Thanh Xuân', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0030','4', N'Nguyễn Lương Bằng', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0031','6/8', N'Phố Đặng Văn Ngữ', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0032','59/7', N'Thái Hà', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0033','78/9', N'Phố Hào Nam', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam'),
('DC0034','79/12', N'Phan Đình Phùng', N'Hải Châu', N'Đà Nẵng', N'Việt Nam'),
('DC0035','123/47', N'Tôn Đức Thắng', N'Liên Chiểu', N'Đà Nẵng', N'Việt Nam'),
('DC0036','169/48', N'Hùng Vương', N'Hải Châu', N'Đà Nẵng', N'Việt Nam'),
('DC0037','478/21', N'Lê Duẩn', N'Hải Châu', N'Đà Nẵng', N'Việt Nam'),
('DC0038','23/48', N'Bạch Đằng', N'Hải Châu', N'Đà Nẵng', N'Việt Nam'),
('DC0039','46/78', N'Hà Huy Tập', N'Ngô Quyền', N'Hải Phòng', N'Việt Nam'),
('DC0040','4', N'Hà Huy Tập', N'Ngô Quyền', N'Hải Phòng', N'Việt Nam'),
('DC0041','44/78', N'Đằng Lâm', N'Hải An', N'Hải Phòng', N'Việt Nam'),
('DC0042','63/8', N'Lê Hồng Phong', N'Hải An', N'Hải Phòng', N'Việt Nam'),
('DC0043','78', N'Nguyễn Du', N'Hồng Bàng', N'Hải Phòng', N'Việt Nam'),
('DC0044','78/15', N'Tô Hiệu', N'Hồng Bàng', N'Hải Phòng', N'Việt Nam'),
('DC0045','43/2', N'Ngô Quyền', N'Lê Chân', N'Hải Phòng', N'Việt Nam'),
('DC0046','448', N'Phan Bội Châu', N'Đồng Hới', N'Quảng Bình', N'Việt Nam'),
('DC0047','439', N'Trường Chinh', N'Đồng Hới', N'Quảng Bình', N'Việt Nam'),
('DC0048','42/78', N'Lê Thánh Tông', N'Đồng Hới', N'Quảng Bình', N'Việt Nam'),
('DC0049','12/78', N'Tôn Đức Thắng', N'Tp. Huế', N'Thừa Thiên Huế', N'Việt Nam'),
('DC0050','7/8', N'Phan Đăng Lưu', N'Tp. Huế', N'Thừa Thiên Huế', N'Việt Nam'),
('DC0051','78/12', N'Trần Thái Tông', N'Tp. Huế', N'Thừa Thiên Huế', N'Việt Nam'),
('DC0052','4/6/11', N'Nguyễn Chí Thanh', N'5', N'Tp. Hồ Chí Minh', N'Việt Nam'),
('DC0053','152/3', N'Cống Quỳnh', N'1', N'Tp. Hồ Chí Minh', N'Việt Nam'),
('DC0054','201/23/13', N'Phạm Ngũ Lão', N'Phường 2', N'Tp. Tây Ninh', N'Việt Nam'),
('DC0055','46/78/14', N'Lý Thường Kiệt', N'Phường 4', N'Tp. Tây Ninh', N'Việt Nam'),
('DC0056','78/9', N'Bình Minh', N'Phường 4', N'Tp. Tây Ninh', N'Việt Nam'),
('DC0057','46/78/13', N'Trần Quý Cáp', N'Phường 2', N'Tp. Tây Ninh', N'Việt Nam'),
('DC0058','46/78/147', N'Hùng Vương', N'Phường 2', N'Tp. Tây Ninh', N'Việt Nam'),
('DC0059','781/9', N'Đại lộ Bình Dương', N'Phường Phú Hòa', N'Tp. Thủ Dầu Một', N'Việt Nam'),
('DC0060','70', N'Lý Thái Tổ', N'Phường Phú Cường', N'Tp. Thủ Dầu Một', N'Việt Nam'),
('DC0061','12', N'Trần Hưng Đạo', N'Phường Hiệp An', N'Tp. Thủ Dầu Một', N'Việt Nam'),
('DC0062','58', N'Thủ Khoa Huân', N'Phường Phú Tân', N'Tp. Thủ Dầu Một', N'Việt Nam'),
('DC0063','7', N'Phạm Ngọc Thạch', N'Phường Chánh Nghĩa', N'Tp. Thủ Dầu Một', N'Việt Nam'),
('DC0064','1', N'Nguyễn An Ninh', N'Phường Quyết Thắng', N'Tp. Biên Hòa', N'Việt Nam'),
('DC0065','25', N'Quốc lộ 1A', N'Phường Tân Hiệp', N'Tp. Biên Hòa', N'Việt Nam'),
('DC0066','12', N'Bùi Thị Xuân', N'Phường Quang Vinh', N'Tp. Biên Hòa', N'Việt Nam'),
('DC0067','236', N'Nguyễn Thái Bình', N'Phường Tân Tiến', N'Tp. Biên Hòa', N'Việt Nam'),
('DC0068','201/23/13', N'Phạm Văn Thuận', N'Phường Bửu Long', N'Tp. Biên Hòa', N'Việt Nam')

--Nhân Viên
INSERT INTO nhanvien (maNhanVien, hoTen, gioiTinh, soDienThoai, ngaySinh, email, chucDanh, maDiaChi, trangThai)
VALUES
	('NV0001', N'Nguyễn Thị Hồng Nhung',0, '0987654321', '1995-05-23', 'nhung.nguyen@example.com', N'Nhân viên kinh doanh', 'DC0008',1),
	('NV0002', N'Lê Văn Dương',1, '0909123456', '1990-01-12', 'duong.le@example.com', N'Nhân viên kinh doanh', 'DC0001',1),
	('NV0003', N'Phạm Hồng Đăng',1, '0918234567', '1985-08-11', 'dang.pham@example.com', N'Quản lý', 'DC0002',1),
	('NV0004', N'Trần Thị Bích Phương',0, '0976543210', '1992-11-27', 'phuong.tran@example.com', N'Nhân viên kinh doanh', 'DC0003',1),
	('NV0005', N'Nguyễn Minh Thảo',0, '0969123456', '1997-04-01', 'thao.nguyen@example.com', N'Nhân viên kinh doanh', 'DC0004',1),
	('NV0006', N'Phan Thị Thu Hiền',0, '0908123456', '1988-02-18', 'hien.phan@example.com', N'Nhân viên kinh doanh', 'DC0005',1),
	('NV0007', N'Vũ Thị Hoài An',0, '0977654321', '1991-12-31', 'an.vu@example.com', N'Nhân viên kinh doanh', 'DC0006',1),
	('NV0008', N'Trần Văn Đức',1, '0912123456', '1995-03-25', 'duc.tran@example.com', N'Nhân viên kinh doanh', 'DC0007',1),
	('NV0009', N'Nguyễn Thanh Thúy',0, '0988123456', '1993-07-09', 'thuy.nguyen@example.com', N'Nhân viên kinh doanh', 'DC0008',1),
	('NV0010', N'Lê Hoàng Anh',1, '0969123456', '1998-09-10', 'anh.le@example.com', N'Nhân viên kinh doanh', 'DC0009',1),
	('NV0011', N'Trần Đức Anh',1, '0978123456', '1996-12-01', 'anh.tran@example.com', N'Nhân viên kinh doanh', 'DC0010',1),
	('NV0012', N'Phạm Thanh Hương',0, '0912123456', '1990-05-15', 'huong.pham@example.com', N'Nhân viên kinh doanh', 'DC0011',1),
	('NV0013', N'Lê Thị Thanh Trúc',0, '0987654321', '1988-02-12', 'trucle@gmail.com', N'Nhân viên kinh doanh', 'DC0005',1),
    ('NV0014', N'Phạm Thị Ngọc Yến',0, '0987123456', '1992-05-30', 'yentran@gmail.com', N'Nhân viên kinh doanh', 'DC0005',1),
    ('NV0015', N'Nguyễn Thị Trúc Phương',0, '0908123456', '1995-08-22', 'phuongnguyen2@gmail.com', N'Nhân viên kinh doanh', 'DC0006',1),
    ('NV0016', N'Lê Hoàng Nam',0, '0978123456', '1986-06-25', 'namle@gmail.com', N'Nhân viên kinh doanh', 'DC0006',0)

--Khách hàng
INSERT INTO KhachHang (maKhachHang, hoTen, gioiTinh, soDienThoai, ngaySinh, email, maSoThue, maDiaChi)
VALUES
('KH00001', N'Trần Minh An',1,  '0987654321', '1990-01-01', 'minhan@gmail.com', NULL, 'DC0001'),
('KH00002', N'Nguyễn Thị Bảo Ngọc',0, '0123456789', '1992-03-15', 'bao.ngoc@gmail.com', '123456789', 'DC0002'),
('KH00003', N'Lê Văn Cường',1, '0909123456', '1995-05-20', 'cuong.le@gmail.com', NULL, 'DC0003'),
('KH00004', N'Phạm Thị Dung',0,'0912345678', '1987-12-03', 'dung.pham@gmail.com', NULL, 'DC0004'),
('KH00005', N'Ngô Quang Đạt',1, '0978123456', '1988-08-08', 'dat.ngo@gmail.com', NULL, 'DC0005'),
('KH00006', N'Vũ Thanh Hải',1, '0967123456', '1993-02-27', 'thanh.hai@gmail.com', '987654321', 'DC0006'),
('KH00007', N'Hoàng Thị Hồng',0, '0987123456', '1986-06-18', 'hong.hoang@gmail.com', NULL, 'DC0007'),
('KH00008', N'Đặng Văn Khánh',1, '0919123456', '1994-09-05', 'khanh.dang@gmail.com', NULL, 'DC0008'),
('KH00009', N'Trần Thị Lan',0, '0903123456', '1989-11-11', 'lan.tran@gmail.com', '456789123', 'DC0009'),
('KH00010', N'Phan Văn Minh',1, '0918123456', '1991-04-25', 'minh.phan@gmail.com', NULL, 'DC0010'),
('KH00011', N'Nguyễn Thị Mỹ Linh',2, '0977123456', '1990-10-05', 'mylinh.nguyen@gmail.com', NULL, 'DC0011'),
('KH00012', N'Phạm Minh Ngọc',1, '0983123456', '1985-06-10', 'ngoc.pham@gmail.com', '789456123', 'DC0012'),
('KH00013', N'Lê Thị Nhàn',0, '0962123456', '1996-03-20', 'nhan.le@gmail.com', NULL, 'DC0013'),
('KH00014', N'Nguyễn Văn Quân',1, '0932123456', '1993-11-22', 'quan.nguyen@gmail.com', NULL, 'DC0014'),
('KH00015', N'Hoàng Thị Quỳnh',0, '0945123456', '1992-01-15', 'quynh.hoang@gmail.com', NULL, 'DC0015'),
('KH00016', N'Đỗ Văn Sơn',1, '0906123456', '1988-12-30', 'son.do@gmail.com', NULL, 'DC0016'),
('KH00017', N'Trần Thị Tâm',0, '0916123456', '1990-07-05', 'tam.tran@gmail.com', NULL, 'DC0017'),
('KH00018', N'Nguyễn Văn Thắng',1, '0988123456', '1987-09-18', 'thang.nguyen@gmail.com', NULL, 'DC0018'),
('KH00019', N'Lê Thị Thanh',0, '0968123456', '1994-05-25', 'thanh.le@gmail.com', '654321987', 'DC0019'),
('KH00020', N'Ngô Văn Tuấn',1, '0935123456', '1991-08-12', 'tuan.ngo@gmail.com', NULL, 'DC0020'),
('KH00021', N'Nguyễn Thị Thu Hà',0, '0976123456', '1995-02-18', 'thuha.nguyen@gmail.com', NULL, 'DC0021'),
('KH00022', N'Phạm Minh Tâm',1, '0981123456', '1993-04-03', 'minhtam.pham@gmail.com', '987654321', 'DC0022'),
('KH00023', N'Lê Văn Hùng',1, '0967123456', '1986-09-12', 'hung.le@gmail.com', NULL, 'DC0023'),
('KH00024', N'Nguyễn Thị Kim Ngân',0, '0938123456', '1992-11-27', 'kimngan.nguyen@gmail.com', NULL, 'DC0024'),
('KH00025', N'Hoàng Văn Tùng',1, '0943123456', '1990-08-02', 'tung.hoang@gmail.com', '123456789', 'DC0025'),
('KH00026', N'Đỗ Thị Hương',0, '0904123456', '1989-01-30', 'huong.do@gmail.com', NULL, 'DC0026'),
('KH00027', N'Trần Thị Trang',0, '0914123456', '1998-06-15', 'trang.tran@gmail.com', NULL, 'DC0027'),
('KH00028', N'Nguyễn Văn Tâm',1, '0986123456', '1996-03-21', 'tam.nguyen@gmail.com', NULL, 'DC0028'),
('KH00029', N'Lê Thị Ngọc Ánh',0, '0963123456', '1991-12-25', 'ngocanh.le@gmail.com', '456789123', 'DC0029'),
('KH00030', N'Nguyễn Văn Dũng',1, '0939123456', '1994-10-10', 'dung.nguyen@gmail.com', NULL, 'DC0030'),
('KH00031', N'Lê Thị Hương',0, '0969123456', '1988-05-16', 'huong.le@gmail.com', NULL, 'DC0031'),
('KH00032', N'Nguyễn Văn Hải',1, '0937123456', '1993-09-01', 'hai.nguyen@gmail.com', '987654321', 'DC0032'),
('KH00033', N'Trần Thị Huyền',0, '0917123456', '1990-02-08', 'huyen.tran@gmail.com', NULL, 'DC0033'),
('KH00034', N'Nguyễn Thị Ngọc',0, '0978123456', '1997-07-04', 'ngoc.nguyen@gmail.com', NULL, 'DC0000'),
('KH00035', N'Phạm Văn Lợi',1, '0989123456', '1992-12-22', 'loi.pham@gmail.com', '123456789', 'DC0000'),
('KH00036', N'Lê Thị Ánh Nguyệt',0, '0987654321', '1989-02-14', 'lethianhnguyet@gmail.com', '0123456789', 'DC0012'),
('KH00037', N'Phạm Thị Thanh Tâm',0, '0909123456', '1991-08-22', 'phamthanh.tam@yahoo.com', '9876543210', 'DC0039'),
('KH00038', N'Đặng Ngọc Yến Nhi',0, '0977123456', '1988-12-04', 'dangngocyennhi@yahoo.com', '4567891230', 'DC0060'),
('KH00039', N'Lâm Thị Tuyết Mai',0, '0918123456', '1995-07-29', 'lamtuyetmai@gmail.com', '7890123456', 'DC0019'),
('KH00040', N'Nguyễn Thị Bích Liên',0, '0918123456', '1994-01-12', 'nguyenbichlien@gmail.com', NULL, 'DC0045'),
('KH00041', N'Trần Thị Thùy Dương',0, '0903123456', '1987-11-03', 'tranthuyduong@yahoo.com', '2468013579', 'DC0058'),
('KH00042', N'Phan Thị Thu Hà',0, '0986123456', '1992-04-27', 'phanthuha@hotmail.com', '9876543210', 'DC0059'),
('KH00043', N'Lê Thị Bích Thủy',0, '0901123456', '1990-03-17', 'lebichthuy@gmail.com', NULL, 'DC0037'),
('KH00044', N'Nguyễn Thị Hồng Nhung',0, '0975123456', '1998-09-10', 'nguyenhongnhung@yahoo.com', NULL, 'DC0055'),
('KH00045', N'Trần Thị Thanh Thúy',0,'0906123456', '1989-05-06', 'tranthanhthuy@gmail.com', '0123456789', 'DC0065'),
('KH00046', N'Nguyễn Thị Phương Thảo',0, '0912123456', '1993-12-15', 'nguyenphuongthao@yahoo.com', '9876543210', 'DC0056'),
('KH00047', N'Phạm Thị Hoa Hồng',0, '0974123456', '1991-02-25', 'phamhoahong@gmail.com', NULL, 'DC0049'),
('KH00048', N'Ngô Thị Tuyết Mai',0, '0911123456', '1996-11-18', 'ngotuyetmai@hotmail.com', '4567891230', 'DC0020'),
('KH00049', N'Trần Thị Phương Linh',0, '0987123456', '1994-10-01', 'tranthiphuonglinh@yahoo.com', NULL, 'DC0057')

--Kho Hàng
INSERT INTO KhoHang (maKho, tenKho, dienTich, maDiaChi)
VALUES
('KHO01', N'Kho TP.Hồ Chí Minh', 1000, 'DC0017')

--Nhà cung cấp
INSERT INTO NhaCungCap (maNhaCungCap, tenNhaCungCap, soDienThoai, maSoThue, email, maDiaChi)
VALUES
('NCC01', 'Nova Computer', '039253683', '0310458128', 'NovaComputer@nova.vn', 'DC0057'),
('NCC02', '	Công ty TNHH An Khang', '039253683', '0310458128', 'binhminhphatlaptop@gmail.com','DC0045')

--Tài khoản
INSERT INTO TaiKhoan (maNhanVien, matKhau)
VALUES 
('NV0001', '1111'),
('NV0002', '1111'),
('NV0003', '1111'),
('NV0004', '1111'),
('NV0005', '1111'),
('NV0006', '1111'),
('NV0007', '1111'),
('NV0008', '1111'),
('NV0009', '1111'),
('NV0010', '1111'),
('NV0011', '1111'),
('NV0012', '1111'),
('NV0013', '1111'),
('NV0014', '1111'),
('NV0015', '1111'),
('NV0016', '1111')



--Đơn nhập hàng
INSERT INTO DonNhapHang (maDonNhap, ngayNhap, maKho, maNhaCungCap, ghiChu, tongTien, daNhan, [maNhanVien])
VALUES
('DNH0001', '2023-4-16', 'KHO01', 'NCC01', '', 107000000,'1', 'NV0001')

--Chi tiết
INSERT INTO ChiTietDonNhap(maSanPham, maDonNhap, soLuongCungCap,tongTien)
VALUES
('SP0001', 'DNH0001', 100, 32000000),
('SP0002', 'DNH0001', 100, 46000000),
('SP0003', 'DNH0001', 10, 29000000)

--Hóa đơn
INSERT INTO HoaDon (maHoaDon, ngayLap, phuongThucThanhToan, maNhanVien, maKhachHang, tongTien)
VALUES
('HD00001', '2023-4-16', 'ATM', 'NV0005', 'KH00008', 345600),
('HD00002', '2023-4-16', 'ATM', 'NV0005', 'KH00009', 1857600)

--Chi tiết hóa đơn
INSERT INTO ChiTietHoaDon (maSanPham, maHoaDon, giaBan, soLuong, tongTien)
VALUES
('SP0001', 'HD00001', 345600, 1, 345600),
('SP0001', 'HD00002', 345600, 1, 345600),
('SP0099', 'HD00002', 1512000, 1, 1512000)

--Chi tiết kho hàng
INSERT INTO ChiTietKhoHang(maSanPham, maKho, soLuongTon)
VALUES
('SP0001', 'KHO01', 107),
('SP0002', 'KHO01', 153),
('SP0003', 'KHO01', 180),
('SP0004', 'KHO01', 112),
('SP0005', 'KHO01', 45),
('SP0006', 'KHO01', 146),
('SP0007', 'KHO01', 131),
('SP0008', 'KHO01', 73),
('SP0009', 'KHO01', 21),
('SP0010', 'KHO01', 72),
('SP0011', 'KHO01', 3),
('SP0012', 'KHO01', 56),
('SP0013', 'KHO01', 116),
('SP0014', 'KHO01', 103),
('SP0015', 'KHO01', 106),
('SP0016', 'KHO01', 48),
('SP0017', 'KHO01', 145),
('SP0018', 'KHO01', 23),
('SP0019', 'KHO01', 124),
('SP0020', 'KHO01', 174),
('SP0021', 'KHO01', 142),
('SP0022', 'KHO01', 57),
('SP0023', 'KHO01', 191),
('SP0024', 'KHO01', 33),
('SP0025', 'KHO01', 159),
('SP0026', 'KHO01', 145),
('SP0027', 'KHO01', 109),
('SP0028', 'KHO01', 116),
('SP0029', 'KHO01', 85),
('SP0030', 'KHO01', 43),
('SP0031', 'KHO01', 73),
('SP0032', 'KHO01', 182),
('SP0033', 'KHO01', 30),
('SP0034', 'KHO01', 71),
('SP0035', 'KHO01', 126),
('SP0036', 'KHO01', 31),
('SP0037', 'KHO01', 163),
('SP0038', 'KHO01', 163),
('SP0039', 'KHO01', 113),
('SP0040', 'KHO01', 24),
('SP0041', 'KHO01', 0),
('SP0042', 'KHO01', 172),
('SP0043', 'KHO01', 116),
('SP0044', 'KHO01', 3),
('SP0045', 'KHO01', 138),
('SP0046', 'KHO01', 88),
('SP0047', 'KHO01', 55),
('SP0048', 'KHO01', 83),
('SP0049', 'KHO01', 111),
('SP0050', 'KHO01', 189),
('SP0051', 'KHO01', 102),
('SP0052', 'KHO01', 58),
('SP0053', 'KHO01', 111),
('SP0054', 'KHO01', 55),
('SP0055', 'KHO01', 35),
('SP0056', 'KHO01', 40),
('SP0057', 'KHO01', 86),
('SP0058', 'KHO01', 61),
('SP0059', 'KHO01', 49),
('SP0060', 'KHO01', 1),
('SP0061', 'KHO01', 48),
('SP0062', 'KHO01', 56),
('SP0063', 'KHO01', 6),
('SP0064', 'KHO01', 90),
('SP0065', 'KHO01', 55),
('SP0066', 'KHO01', 36),
('SP0067', 'KHO01', 197),
('SP0068', 'KHO01', 8),
('SP0069', 'KHO01', 58),
('SP0070', 'KHO01', 44),
('SP0071', 'KHO01', 167),
('SP0072', 'KHO01', 48),
('SP0073', 'KHO01', 188),
('SP0074', 'KHO01', 122),
('SP0075', 'KHO01', 108),
('SP0076', 'KHO01', 167),
('SP0077', 'KHO01', 55),
('SP0078', 'KHO01', 155),
('SP0079', 'KHO01', 74),
('SP0080', 'KHO01', 140),
('SP0081', 'KHO01', 49),
('SP0082', 'KHO01', 168),
('SP0083', 'KHO01', 72),
('SP0084', 'KHO01', 37),
('SP0085', 'KHO01', 169),
('SP0086', 'KHO01', 136),
('SP0087', 'KHO01', 145),
('SP0088', 'KHO01', 127),
('SP0089', 'KHO01', 190),
('SP0090', 'KHO01', 182),
('SP0091', 'KHO01', 46),
('SP0092', 'KHO01', 29),
('SP0093', 'KHO01', 152),
('SP0094', 'KHO01', 54),
('SP0095', 'KHO01', 9),
('SP0096', 'KHO01', 136),
('SP0097', 'KHO01', 122),
('SP0098', 'KHO01', 185),
('SP0099', 'KHO01', 107),
('SP0100', 'KHO01', 1),
('SP0101', 'KHO01', 15),
('SP0102', 'KHO01', 182),
('SP0103', 'KHO01', 45),
('SP0104', 'KHO01', 91),
('SP0105', 'KHO01', 122),
('SP0106', 'KHO01', 91),
('SP0107', 'KHO01', 88),
('SP0108', 'KHO01', 86),
('SP0109', 'KHO01', 130),
('SP0110', 'KHO01', 140),
('SP0111', 'KHO01', 151),
('SP0112', 'KHO01', 193),
('SP0113', 'KHO01', 50),
('SP0114', 'KHO01', 100),
('SP0115', 'KHO01', 194),
('SP0116', 'KHO01', 183),
('SP0117', 'KHO01', 79),
('SP0118', 'KHO01', 88),
('SP0119', 'KHO01', 65),
('SP0120', 'KHO01', 134),
('SP0121', 'KHO01', 76),
('SP0122', 'KHO01', 71),
('SP0123', 'KHO01', 54),
('SP0124', 'KHO01', 21),
('SP0125', 'KHO01', 6),
('SP0126', 'KHO01', 148),
('SP0127', 'KHO01', 67),
('SP0128', 'KHO01', 182),
('SP0129', 'KHO01', 180),
('SP0130', 'KHO01', 182),
('SP0131', 'KHO01', 148),
('SP0132', 'KHO01', 107),
('SP0133', 'KHO01', 126),
('SP0134', 'KHO01', 184),
('SP0135', 'KHO01', 119),
('SP0136', 'KHO01', 9),
('SP0137', 'KHO01', 129),
('SP0138', 'KHO01', 7),
('SP0139', 'KHO01', 93),
('SP0140', 'KHO01', 113),
('SP0141', 'KHO01', 158),
('SP0142', 'KHO01', 192),
('SP0143', 'KHO01', 197),
('SP0144', 'KHO01', 67),
('SP0145', 'KHO01', 175),
('SP0146', 'KHO01', 118),
('SP0147', 'KHO01', 185),
('SP0148', 'KHO01', 190),
('SP0149', 'KHO01', 29),
('SP0150', 'KHO01', 26),
('SP0151', 'KHO01', 83),
('SP0152', 'KHO01', 130),
('SP0153', 'KHO01', 79),
('SP0154', 'KHO01', 150),
('SP0155', 'KHO01', 118),
('SP0156', 'KHO01', 181),
('SP0157', 'KHO01', 100),
('SP0158', 'KHO01', 127),
('SP0159', 'KHO01', 30),
('SP0160', 'KHO01', 30),
('SP0161', 'KHO01', 101),
('SP0162', 'KHO01', 75),
('SP0163', 'KHO01', 60),
('SP0164', 'KHO01', 21),
('SP0165', 'KHO01', 189),
('SP0166', 'KHO01', 182),
('SP0167', 'KHO01', 81),
('SP0168', 'KHO01', 101),
('SP0169', 'KHO01', 59),
('SP0170', 'KHO01', 134),
('SP0171', 'KHO01', 136),
('SP0172', 'KHO01', 52),
('SP0173', 'KHO01', 161),
('SP0174', 'KHO01', 150),
('SP0175', 'KHO01', 158),
('SP0176', 'KHO01', 149),
('SP0177', 'KHO01', 111),
('SP0178', 'KHO01', 33),
('SP0179', 'KHO01', 175),
('SP0180', 'KHO01', 130),
('SP0181', 'KHO01', 160),
('SP0182', 'KHO01', 121),
('SP0183', 'KHO01', 61)



--XEM DATA
Select * from [dbo].[ChiTietDonNhap]
Select * from [dbo].[ChiTietHoaDon]
Select * from [dbo].[ChiTietKhoHang]
Select * from [dbo].[DiaChi]
Select * from [dbo].[DonNhapHang]
Select * from [dbo].[HoaDon]
Select * from [dbo].[KhachHang]
Select * from [dbo].[KhoHang]
Select * from [dbo].[NhaCungCap]
Select * from [dbo].[NhanVien]
Select * from [dbo].[SanPham]
Select * from [dbo].[TaiKhoan]
Select * from [dbo].[ThuongHieu]
