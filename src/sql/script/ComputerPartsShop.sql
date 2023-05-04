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

USE [ComputerPartsShop]
GO
/****** Object:  Table [dbo].[ChiTietDonNhap]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDonNhap](
	[maSanPham] [nvarchar](30) NOT NULL,
	[maDonNhap] [nvarchar](30) NOT NULL,
	[soLuongCungCap] [int] NULL,
	[tongTien] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC,
	[maDonNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maSanPham] [nvarchar](30) NOT NULL,
	[maHoaDon] [nvarchar](30) NOT NULL,
	[giaBan] [real] NOT NULL,
	[soLuong] [int] NOT NULL,
	[tongTien] [real] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC,
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietKhoHang]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietKhoHang](
	[maKho] [nvarchar](30) NOT NULL,
	[maSanPham] [nvarchar](30) NOT NULL,
	[soLuongTon] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maKho] ASC,
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DiaChi]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiaChi](
	[maDiaChi] [nvarchar](30) NOT NULL,
	[soNha] [nvarchar](30) NULL,
	[duong] [nvarchar](50) NULL,
	[huyen] [nvarchar](50) NULL,
	[thanhPho] [nvarchar](50) NULL,
	[quocGia] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maDiaChi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonNhapHang]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonNhapHang](
	[maDonNhap] [nvarchar](30) NOT NULL,
	[ngayNhap] [date] NOT NULL,
	[maKho] [nvarchar](30) NOT NULL,
	[maNhaCungCap] [nvarchar](30) NOT NULL,
	[ghiChu] [nvarchar](1000) NULL,
	[tongTien] [real] NOT NULL,
	[daNhan] [bit] NOT NULL,
	[maNhanVien] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maDonNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [nvarchar](30) NOT NULL,
	[ngayLap] [date] NULL,
	[phuongThucThanhToan] [nvarchar](20) NULL,
	[maNhanVien] [nvarchar](30) NULL,
	[maKhachHang] [nvarchar](30) NOT NULL,
	[tongTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](30) NOT NULL,
	[hoTen] [nvarchar](30) NOT NULL,
	[soDienThoai] [nvarchar](10) NOT NULL,
	[ngaySinh] [date] NULL,
	[email] [nvarchar](30) NULL,
	[maSoThue] [nvarchar](25) NULL,
	[maDiaChi] [nvarchar](30) NULL,
	[diemThanhVien] [int] NULL,
	[gioiTinh] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhoHang]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhoHang](
	[maKho] [nvarchar](30) NOT NULL,
	[tenKho] [nvarchar](50) NULL,
	[dienTich] [real] NOT NULL,
	[maDiaChi] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maKho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[maNhaCungCap] [nvarchar](30) NOT NULL,
	[tenNhaCungCap] [nvarchar](40) NULL,
	[soDienThoai] [nvarchar](15) NULL,
	[maSoThue] [nvarchar](25) NULL,
	[email] [nvarchar](40) NULL,
	[maDiaChi] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhaCungCap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nvarchar](30) NOT NULL,
	[hoTen] [nvarchar](50) NOT NULL,
	[soDienThoai] [nvarchar](15) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[email] [nvarchar](50) NULL,
	[chucDanh] [nvarchar](40) NOT NULL,
	[maDiaChi] [nvarchar](30) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[trangThai] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSanPham] [nvarchar](30) NOT NULL,
	[tenSanPham] [nvarchar](100) NOT NULL,
	[giaNhap] [real] NOT NULL,
	[giamGia] [real] NOT NULL,
	[cauHinh] [nvarchar](1000) NULL,
	[soThangBaoHanh] [int] NOT NULL,
	[maLoai] [int] NOT NULL,
	[VAT] [real] NOT NULL,
	[maThuongHieu] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maNhanVien] [nvarchar](30) NOT NULL,
	[matKhau] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThuongHieu]    Script Date: 5/4/2023 1:29:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThuongHieu](
	[maThuongHieu] [nvarchar](30) NOT NULL,
	[tenThuongHieu] [nvarchar](40) NOT NULL,
	[quocGia] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maThuongHieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0001', N'DNH0001', 100, 32000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0001', N'DNH0003', 100, 59000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0001', N'DNH0006', 200, 118000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0002', N'DNH0001', 100, 46000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0002', N'DNH0003', 100, 129000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0002', N'DNH0004', 100, 129000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0003', N'DNH0001', 10, 29000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0003', N'DNH0003', 100, 169000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0004', N'DNH0005', 100, 65000000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0005', N'DNH0002', 3, 1950000)
INSERT [dbo].[ChiTietDonNhap] ([maSanPham], [maDonNhap], [soLuongCungCap], [tongTien]) VALUES (N'SP0008', N'DNH0002', 3, 900000)
GO
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0001', N'HD00001', 345600, 1, 345600)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0001', N'HD00002', 345600, 1, 345600)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0001', N'HD00003', 678500, 1, 678500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0001', N'HD00018', 678500, 14, 9499000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0002', N'HD00007', 1483500, 2, 2967000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0002', N'HD00017', 1483500, 1, 1483500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0002', N'HD00018', 1483500, 2, 2967000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0002', N'HD00020', 1483500, 1, 1483500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0002', N'HD00036', 1483500, 1, 1483500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0003', N'HD00013', 1943500, 1, 1943500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0003', N'HD00018', 1943500, 1, 1943500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0005', N'HD00019', 747500, 3, 2242500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0005', N'HD00024', 747500, 1, 747500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0006', N'HD00008', 1365000, 1, 1365000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0007', N'HD00036', 1870000, 1, 1870000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0008', N'HD00030', 345000, 4, 1380000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0009', N'HD00003', 230000, 1, 230000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0009', N'HD00038', 230000, 20, 4600000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0012', N'HD00017', 483000, 1, 483000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0013', N'HD00003', 1380000, 8, 1.104E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0014', N'HD00008', 1023500, 1, 1023500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0018', N'HD00008', 1368500, 1, 1368500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0023', N'HD00023', 624100, 8, 4992800)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0027', N'HD00014', 1188000, 1, 1188000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0031', N'HD00004', 4885800, 1, 4885800)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0032', N'HD00038', 945200, 18, 1.70136E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0034', N'HD00036', 8638920, 5, 4.31946E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0035', N'HD00036', 4734700, 10, 4.7347E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0036', N'HD00030', 1557300, 8, 1.24584E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0036', N'HD00036', 1557300, 2, 3114600)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0037', N'HD00007', 1206900, 1, 1206900)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0040', N'HD00036', 4418700, 1, 4418700)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0042', N'HD00036', 3070400, 1, 3070400)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0043', N'HD00025', 3404000, 5, 1.702E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0046', N'HD00005', 837810.1, 1, 837810.1)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0046', N'HD00008', 837810.1, 1, 837810.1)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0047', N'HD00036', 564499.5, 3, 1693498.5)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0050', N'HD00015', 1424498.63, 3, 4273496)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0050', N'HD00029', 1424498.63, 100, 142449856)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0061', N'HD00034', 4545000, 6, 2.727E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0064', N'HD00039', 1638000, 10, 1.638E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0067', N'HD00023', 4675356, 4, 18701424)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0067', N'HD00039', 4675356, 40, 187014240)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0074', N'HD00028', 8549760, 10, 8.54976E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0082', N'HD00028', 1980000, 12, 2.376E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0086', N'HD00010', 3675000, 1, 3675000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0089', N'HD00012', 1312500, 1, 1312500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0089', N'HD00040', 1312500, 50, 6.5625E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0090', N'HD00038', 1365000, 30, 4.095E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0091', N'HD00006', 1417500, 1, 1417500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0093', N'HD00011', 2940000, 1, 2940000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0094', N'HD00032', 6500000, 5, 3.25E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0095', N'HD00006', 1870000, 3, 5610000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0096', N'HD00011', 3150000, 5, 1.575E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0096', N'HD00033', 3150000, 131, 4.1265E+08)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0097', N'HD00009', 7500000, 1, 7500000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0097', N'HD00038', 7500000, 50, 3.75E+08)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0098', N'HD00040', 1650000, 86, 1.419E+08)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0099', N'HD00002', 1512000, 1, 1512000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0099', N'HD00005', 1512000, 1, 1512000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0099', N'HD00006', 1512000, 1, 1512000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0099', N'HD00011', 1512000, 3, 4536000)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0099', N'HD00030', 1512000, 15, 2.268E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0109', N'HD00016', 6438850, 10, 6.43885E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0116', N'HD00022', 1575449, 1, 1575449)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0120', N'HD00023', 5134799, 3, 15404397)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0126', N'HD00037', 2662800, 10, 2.6628E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0133', N'HD00010', 2.07E+07, 1, 2.07E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0139', N'HD00037', 9200000, 10, 9.2E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0148', N'HD00009', 9200000, 2, 1.84E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0148', N'HD00021', 9200000, 2, 1.84E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0148', N'HD00030', 9200000, 12, 1.104E+08)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0149', N'HD00037', 1725000, 10, 1.725E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0166', N'HD00037', 1598500, 10, 1.5985E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0167', N'HD00026', 402500, 1, 402500)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0169', N'HD00026', 3218850, 1, 3218850)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0171', N'HD00034', 2758850, 33, 9.104205E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0173', N'HD00031', 3793850, 1, 3793850)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0175', N'HD00009', 1428900, 1, 1428900)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0176', N'HD00027', 3043950, 10, 3.04395E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0176', N'HD00031', 3043950, 1, 3043950)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0177', N'HD00026', 2068850, 1, 2068850)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0178', N'HD00037', 3988950, 10, 3.98895E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0181', N'HD00010', 4598850, 1, 4598850)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0182', N'HD00035', 733950, 60, 4.4037E+07)
INSERT [dbo].[ChiTietHoaDon] ([maSanPham], [maHoaDon], [giaBan], [soLuong], [tongTien]) VALUES (N'SP0183', N'HD00031', 4723950, 3, 1.417185E+07)
GO
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0001', 392)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0002', 346)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0003', 278)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0004', 212)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0005', 44)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0006', 145)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0007', 130)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0008', 72)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0009', 0)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0010', 72)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0011', 3)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0012', 55)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0013', 108)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0014', 102)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0015', 106)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0016', 48)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0017', 145)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0018', 22)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0019', 124)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0020', 174)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0021', 142)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0022', 57)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0023', 183)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0024', 33)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0025', 159)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0026', 145)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0027', 108)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0028', 116)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0029', 85)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0030', 43)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0031', 72)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0032', 164)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0033', 30)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0034', 66)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0035', 116)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0036', 21)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0037', 162)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0038', 163)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0039', 113)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0040', 23)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0041', 0)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0042', 171)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0043', 111)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0044', 3)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0045', 138)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0046', 86)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0047', 52)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0048', 83)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0049', 111)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0050', 86)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0051', 102)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0052', 58)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0053', 111)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0054', 55)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0055', 35)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0056', 40)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0057', 86)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0058', 61)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0059', 49)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0060', 1)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0061', 42)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0062', 56)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0063', 6)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0064', 80)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0065', 55)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0066', 36)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0067', 153)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0068', 8)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0069', 58)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0070', 44)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0071', 167)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0072', 48)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0073', 188)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0074', 112)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0075', 108)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0076', 167)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0077', 55)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0078', 155)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0079', 74)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0080', 140)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0081', 49)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0082', 156)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0083', 72)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0084', 37)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0085', 169)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0086', 135)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0087', 145)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0088', 127)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0089', 139)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0090', 152)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0091', 45)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0092', 29)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0093', 151)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0094', 49)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0095', 6)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0096', 0)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0097', 71)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0098', 99)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0099', 87)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0100', 1)
GO
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0101', 15)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0102', 182)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0103', 45)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0104', 91)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0105', 122)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0106', 91)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0107', 88)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0108', 86)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0109', 120)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0110', 140)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0111', 151)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0112', 193)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0113', 50)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0114', 100)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0115', 194)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0116', 182)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0117', 79)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0118', 88)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0119', 65)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0120', 131)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0121', 76)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0122', 71)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0123', 54)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0124', 21)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0125', 6)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0126', 138)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0127', 67)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0128', 182)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0129', 180)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0130', 182)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0131', 148)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0132', 107)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0133', 125)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0134', 184)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0135', 119)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0136', 9)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0137', 129)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0138', 7)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0139', 83)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0140', 113)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0141', 158)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0142', 192)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0143', 197)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0144', 67)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0145', 175)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0146', 118)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0147', 185)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0148', 174)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0149', 19)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0150', 26)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0151', 83)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0152', 130)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0153', 79)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0154', 150)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0155', 118)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0156', 181)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0157', 100)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0158', 127)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0159', 30)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0160', 30)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0161', 101)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0162', 75)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0163', 60)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0164', 21)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0165', 189)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0166', 172)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0167', 80)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0168', 101)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0169', 58)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0170', 134)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0171', 103)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0172', 52)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0173', 160)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0174', 150)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0175', 157)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0176', 138)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0177', 110)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0178', 23)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0179', 175)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0180', 130)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0181', 159)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0182', 61)
INSERT [dbo].[ChiTietKhoHang] ([maKho], [maSanPham], [soLuongTon]) VALUES (N'KHO01', N'SP0183', 58)
GO
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0000', N'15/9', N'Phạm Văn Chiêu', N'Gò Vấp', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0001', N'132', N'Nguyễn Thái Sơn', N'Thủ Đức', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0002', N'25', N'Cộng Hòa', N'Tân Bình', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0003', N'158/9/8', N'Trường Chinh', N'Bình Thạnh', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0004', N'25/8', N'Nguyễn Thái Học', N'Phú Nhuận', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0005', N'158', N'6', N'Tân Phú', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0006', N'26', N'7', N'1', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0007', N'45/8', N'8', N'2', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0008', N'158/9', N'Hải Thượng Lãng Ông', N'3', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0009', N'688/9', N'10', N'4', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0010', N'11/9', N'11', N'5', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0011', N'121/9', N'12', N'6', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0012', N'', N'', N'', N'', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0013', N'11/9', N'14', N'8', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0014', N'114/9', N'15', N'9', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0015', N'113/9', N'16', N'10', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0016', N'256/8', N'17', N'11', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0017', N'276/8', N'18', N'12', N'Thành phố Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0018', N'276/8', N'Phố Bạch Mai', N'Hai Bà Trưng', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0019', N'', N'', N'', N'', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0020', N'286/8', N'Phố Hoàng Văn Thụ', N'Ba Đình', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0021', N'6/8', N'Phạm Văn Đồng', N'Cầu Giấy', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0022', N'56/8', N'Nguyễn Trãi', N'Thanh Xuân', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0023', N'25/8', N'Phố Láng', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0024', N'26/8', N'Lạc Long Quân', N'Tây Hồ', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0025', N'756/8', N'Phố Vũ Trọng Phụng', N'Thanh Xuân', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0026', N'46/8', N'Hồ Tùng Mậu', N'Cầu Giấy', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0027', N'470/4', N'Phố Nguyễn Khánh Toàn', N'Cầu Giấy', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0028', N'460/88', N'Trần Đại Nghĩa', N'Hai Bà Trưng', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0029', N'46/8/16', N'Phố Lê Văn Lương', N'Thanh Xuân', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0030', N'4', N'Nguyễn Lương Bằng', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0031', N'6/8', N'Phố Đặng Văn Ngữ', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0032', N'59/7', N'Thái Hà', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0033', N'78/9', N'Phố Hào Nam', N'Đống Đa', N'Thành phố Hà Nội', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0034', N'79/12', N'Phan Đình Phùng', N'Hải Châu', N'Đà Nẵng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0035', N'123/47', N'Tôn Đức Thắng', N'Liên Chiểu', N'Đà Nẵng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0036', N'169/48', N'Hùng Vương', N'Hải Châu', N'Đà Nẵng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0037', N'478/21', N'Lê Duẩn', N'Hải Châu', N'Đà Nẵng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0038', N'23/48', N'Bạch Đằng', N'Hải Châu', N'Đà Nẵng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0039', N'46/78', N'Hà Huy Tập', N'Ngô Quyền', N'Hải Phòng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0040', N'4', N'Hà Huy Tập', N'Ngô Quyền', N'Hải Phòng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0041', N'44/78', N'Đằng Lâm', N'Hải An', N'Hải Phòng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0042', N'63/8', N'Lê Hồng Phong', N'Hải An', N'Hải Phòng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0043', N'78', N'Nguyễn Du', N'Hồng Bàng', N'Hải Phòng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0044', N'78/15', N'Tô Hiệu', N'Hồng Bàng', N'Hải Phòng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0045', N'43/2', N'Ngô Quyền', N'Lê Chân', N'Hải Phòng', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0046', N'448', N'Phan Bội Châu', N'Đồng Hới', N'Quảng Bình', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0047', N'439', N'Trường Chinh', N'Đồng Hới', N'Quảng Bình', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0048', N'42/78', N'Lê Thánh Tông', N'Đồng Hới', N'Quảng Bình', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0049', N'12/78', N'Tôn Đức Thắng', N'Tp. Huế', N'Thừa Thiên Huế', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0050', N'7/8', N'Phan Đăng Lưu', N'Tp. Huế', N'Thừa Thiên Huế', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0051', N'78/12', N'Trần Thái Tông', N'Tp. Huế', N'Thừa Thiên Huế', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0052', N'4/6/11', N'Nguyễn Chí Thanh', N'5', N'Tp. Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0053', N'152/3', N'Cống Quỳnh', N'1', N'Tp. Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0054', N'201/23/13', N'Phạm Ngũ Lão', N'Phường 2', N'Tp. Tây Ninh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0055', N'46/78/14', N'Lý Thường Kiệt', N'Phường 4', N'Tp. Tây Ninh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0056', N'78/9', N'Bình Minh', N'Phường 4', N'Tp. Tây Ninh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0057', N'46/78/13', N'Trần Quý Cáp', N'Phường 2', N'Tp. Tây Ninh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0058', N'46/78/147', N'Hùng Vương', N'Phường 2', N'Tp. Tây Ninh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0059', N'781/9', N'Đại lộ Bình Dương', N'Phường Phú Hòa', N'Tp. Thủ Dầu Một', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0060', N'70', N'Lý Thái Tổ', N'Phường Phú Cường', N'Tp. Thủ Dầu Một', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0061', N'12', N'Trần Hưng Đạo', N'Phường Hiệp An', N'Tp. Thủ Dầu Một', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0062', N'58', N'Thủ Khoa Huân', N'Phường Phú Tân', N'Tp. Thủ Dầu Một', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0063', N'7', N'Phạm Ngọc Thạch', N'Phường Chánh Nghĩa', N'Tp. Thủ Dầu Một', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0064', N'1', N'Nguyễn An Ninh', N'Phường Quyết Thắng', N'Tp. Biên Hòa', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0065', N'25', N'Quốc lộ 1A', N'Phường Tân Hiệp', N'Tp. Biên Hòa', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0066', N'12', N'Bùi Thị Xuân', N'Phường Quang Vinh', N'Tp. Biên Hòa', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0067', N'236', N'Nguyễn Thái Bình', N'Phường Tân Tiến', N'Tp. Biên Hòa', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0068', N'201/23/13', N'Phạm Văn Thuận', N'Phường Bửu Long', N'Tp. Biên Hòa', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0069', N'294', N'Đá Hàng', N'Gò Dầu', N'Tây Ninh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0070', N'', N'', N'', N'', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0071', N'4', N'Nguyễn Văn Bảo', N'Gò Vấp', N'Hồ Chí Minh', N'Việt Nam')
INSERT [dbo].[DiaChi] ([maDiaChi], [soNha], [duong], [huyen], [thanhPho], [quocGia]) VALUES (N'DC0072', N'352', N'Phan Huy Ích', N'Gò Vấp', N'Hồ Chí Minh', N'Việt Nam')
GO
INSERT [dbo].[DonNhapHang] ([maDonNhap], [ngayNhap], [maKho], [maNhaCungCap], [ghiChu], [tongTien], [daNhan], [maNhanVien]) VALUES (N'DNH0001', CAST(N'2023-04-16' AS Date), N'KHO01', N'NCC01', N'', 1.07E+08, 1, N'NV0001')
INSERT [dbo].[DonNhapHang] ([maDonNhap], [ngayNhap], [maKho], [maNhaCungCap], [ghiChu], [tongTien], [daNhan], [maNhanVien]) VALUES (N'DNH0002', CAST(N'2023-04-24' AS Date), N'KHO01', N'NCC04', N'Giao trong 3 ngày', 2850000, 1, N'NV0001')
INSERT [dbo].[DonNhapHang] ([maDonNhap], [ngayNhap], [maKho], [maNhaCungCap], [ghiChu], [tongTien], [daNhan], [maNhanVien]) VALUES (N'DNH0003', CAST(N'2023-04-26' AS Date), N'KHO01', N'NCC02', N'Đơn hàng cần giao gấp trước ngày 30/4/2023', 3.57E+08, 1, N'NV0003')
INSERT [dbo].[DonNhapHang] ([maDonNhap], [ngayNhap], [maKho], [maNhaCungCap], [ghiChu], [tongTien], [daNhan], [maNhanVien]) VALUES (N'DNH0004', CAST(N'2023-05-03' AS Date), N'KHO01', N'NCC02', N'', 1.29E+08, 1, N'NV0017')
INSERT [dbo].[DonNhapHang] ([maDonNhap], [ngayNhap], [maKho], [maNhaCungCap], [ghiChu], [tongTien], [daNhan], [maNhanVien]) VALUES (N'DNH0005', CAST(N'2023-05-03' AS Date), N'KHO01', N'NCC01', N'Giao trước 30/4', 6.5E+07, 1, N'NV0017')
INSERT [dbo].[DonNhapHang] ([maDonNhap], [ngayNhap], [maKho], [maNhaCungCap], [ghiChu], [tongTien], [daNhan], [maNhanVien]) VALUES (N'DNH0006', CAST(N'2023-05-03' AS Date), N'KHO01', N'NCC01', N'', 1.18E+08, 1, N'NV0017')
GO
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00001', CAST(N'2022-03-16' AS Date), N'ATM', N'NV0005', N'KH00008', 345600)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00002', CAST(N'2022-04-16' AS Date), N'ATM', N'NV0005', N'KH00009', 1857600)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00003', CAST(N'2022-05-20' AS Date), N'Tiền mặt', N'NV0001', N'KH00001', 11948500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00004', CAST(N'2022-06-20' AS Date), N'Tiền mặt', N'NV0001', N'KH00001', 4885800)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00005', CAST(N'2022-07-20' AS Date), N'Tiền mặt', N'NV0001', N'KH00015', 2349810.1196479797)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00006', CAST(N'2022-08-20' AS Date), N'Tiền mặt', N'NV0001', N'KH00008', 8539500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00007', CAST(N'2022-09-21' AS Date), N'Tiền mặt', N'NV0001', N'KH00001', 4173900)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00008', CAST(N'2022-10-21' AS Date), N'Tiền mặt', N'NV0003', N'KH00008', 4594810.11964798)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00009', CAST(N'2022-11-21' AS Date), N'Tiền mặt', N'NV0003', N'KH00011', 27328900)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00010', CAST(N'2022-12-21' AS Date), N'Tiền mặt', N'NV0003', N'KH00011', 28973850)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00011', CAST(N'2022-04-21' AS Date), N'Tiền mặt', N'NV0003', N'KH00014', 23226000)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00012', CAST(N'2022-01-21' AS Date), N'Tiền mặt', N'NV0003', N'KH00005', 1312500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00013', CAST(N'2022-04-21' AS Date), N'Tiền mặt', N'NV0005', N'KH00007', 1943500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00014', CAST(N'2022-02-21' AS Date), N'Tiền mặt', N'NV0005', N'KH00001', 1188000)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00015', CAST(N'2022-04-21' AS Date), N'Tiền mặt', N'NV0005', N'KH00005', 4273495.7871437073)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00016', CAST(N'2022-03-21' AS Date), N'Ví điện tử', N'NV0005', N'KH00010', 64388500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00017', CAST(N'2022-04-26' AS Date), N'Tiền mặt', N'NV0003', N'KH00001', 1966500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00018', CAST(N'2022-04-26' AS Date), N'Tiền mặt', N'NV0003', N'KH00010', 14409500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00019', CAST(N'2022-05-26' AS Date), N'Ví điện tử', N'NV0003', N'KH00052', 2242500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00020', CAST(N'2022-06-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00028', 1483500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00021', CAST(N'2022-04-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00033', 18400000)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00022', CAST(N'2022-07-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00050', 1575449.0000223368)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00023', CAST(N'2022-08-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00046', 39098621.6614715)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00024', CAST(N'2022-08-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00045', 747500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00025', CAST(N'2022-09-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00044', 17020000)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00026', CAST(N'2022-11-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00042', 5690200)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00027', CAST(N'2022-12-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00040', 30439500)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00028', CAST(N'2022-12-27' AS Date), N'Tiền mặt', N'NV0009', N'KH00035', 109257600)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00029', CAST(N'2022-01-27' AS Date), N'Tiền mặt', N'NV0004', N'KH00052', 142449859.57145691)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00030', CAST(N'2022-02-27' AS Date), N'Tiền mặt', N'NV0004', N'KH00052', 146918400)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00031', CAST(N'2022-03-27' AS Date), N'Tiền mặt', N'NV0004', N'KH00012', 21009650)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00032', CAST(N'2022-04-27' AS Date), N'Tiền mặt', N'NV0004', N'KH00019', 32500000)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00033', CAST(N'2022-04-27' AS Date), N'Tiền mặt', N'NV0004', N'KH00019', 412650000)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00034', CAST(N'2022-04-27' AS Date), N'Tiền mặt', N'NV0004', N'KH00019', 118312050)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00035', CAST(N'2022-04-27' AS Date), N'Tiền mặt', N'NV0004', N'KH00030', 44037000)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00036', CAST(N'2023-01-03' AS Date), N'Tiền mặt', N'NV0007', N'KH00009', 106192298.49739075)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00037', CAST(N'2023-02-08' AS Date), N'ATM', N'NV0007', N'KH00016', 191752500.00041723)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00038', CAST(N'2023-03-16' AS Date), N'ATM', N'NV0010', N'KH00001', 437563600)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00039', CAST(N'2023-04-21' AS Date), N'Tiền mặt', N'NV0010', N'KH00019', 203394246.61216736)
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [phuongThucThanhToan], [maNhanVien], [maKhachHang], [tongTien]) VALUES (N'HD00040', CAST(N'2023-05-03' AS Date), N'ATM', N'NV0010', N'KH00017', 207525000)
GO
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00001', N'No Name', N'0987654321', CAST(N'1990-01-01' AS Date), N'noname@gmail.com', N'', N'DC0001', 46173, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00002', N'Nguyễn Thị Bảo Ngọc', N'0123456789', CAST(N'1992-03-15' AS Date), N'bao.ngoc@gmail.com', N'123456789', N'DC0002', 4500, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00003', N'Lê Văn Cường', N'0909234560', CAST(N'1995-05-20' AS Date), N'cuong.le@gmail.com', NULL, N'DC0003', 1800, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00004', N'Phạm Thị Dung', N'0912345678', CAST(N'1987-12-03' AS Date), N'dung.pham@gmail.com', NULL, N'DC0004', 12100, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00005', N'Ngô Quang Đạt', N'0978113456', CAST(N'1988-08-08' AS Date), N'dat.ngo@gmail.com', NULL, N'DC0005', 2018, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00006', N'Vũ Thanh Hải', N'0967333456', CAST(N'1993-02-27' AS Date), N'thanh.hai@gmail.com', N'987654321', N'DC0006', 5200, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00007', N'Hoàng Thị Hồng', N'0987123456', CAST(N'1986-06-18' AS Date), N'hong.hoang@gmail.com', NULL, N'DC0007', 1644, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00008', N'Đặng Văn Khánh', N'0919123456', CAST(N'1994-09-05' AS Date), N'khanh.dang@gmail.com', NULL, N'DC0008', 5893, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00009', N'Trần Thị Lan', N'0903122456', CAST(N'1989-11-11' AS Date), N'lan.tran@gmail.com', N'456789123', N'DC0009', 12179, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00010', N'Phan Văn Minh', N'0918123456', CAST(N'1991-04-25' AS Date), N'minh.phan@gmail.com', NULL, N'DC0010', 17680, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00011', N'Nguyễn Thị Mỹ Linh', N'0937123451', CAST(N'1990-10-05' AS Date), N'mylinh.nguyen@gmail.com', NULL, N'DC0011', 20410, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00012', N'Phạm Minh Ngọc', N'0983123456', CAST(N'1985-06-10' AS Date), N'ngoc.pham@gmail.com', N'789456123', N'DC0012', 3301, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00013', N'Lê Thị Nhàn', N'0962123456', CAST(N'1996-03-20' AS Date), N'nhan.le@gmail.com', NULL, N'DC0013', 1478, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00014', N'Nguyễn Văn Quân', N'0932123456', CAST(N'1993-11-22' AS Date), N'quan.nguyen@gmail.com', NULL, N'DC0014', 3559, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00015', N'Hoàng Thị Quỳnh', N'0945123456', CAST(N'1992-01-15' AS Date), N'quynh.hoang@gmail.com', NULL, N'DC0015', 14935, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00016', N'Đỗ Văn Sơn', N'0986123486', CAST(N'1988-12-30' AS Date), N'son.do@gmail.com', NULL, N'DC0016', 19295, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00017', N'Trần Thị Tâm', N'0916123456', CAST(N'1990-07-05' AS Date), N'tam.tran@gmail.com', NULL, N'DC0017', 20753, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00018', N'Nguyễn Văn Thắng', N'0988123456', CAST(N'1987-09-18' AS Date), N'thang.nguyen@gmail.com', NULL, N'DC0018', 0, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00019', N'Lê Thị Thanh', N'0968123456', CAST(N'1994-05-25' AS Date), N'thanh.le@gmail.com', N'654321987', N'DC0019', 76685, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00020', N'Ngô Văn Tuấn', N'0935123456', CAST(N'1991-08-12' AS Date), N'tuan.ngo@gmail.com', NULL, N'DC0020', 0, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00021', N'Nguyễn Thị Thu Hà', N'0976123456', CAST(N'1995-02-18' AS Date), N'thuha.nguyen@gmail.com', NULL, N'DC0021', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00022', N'Phạm Minh Tâm', N'0981123456', CAST(N'1993-04-03' AS Date), N'minhtam.pham@gmail.com', N'987654321', N'DC0022', 500, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00023', N'Lê Văn Hùng', N'0967123456', CAST(N'1986-09-12' AS Date), N'hung.le@gmail.com', NULL, N'DC0023', 0, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00024', N'Nguyễn Thị Kim Ngân', N'0938123456', CAST(N'1992-11-27' AS Date), N'kimngan.nguyen@gmail.com', NULL, N'DC0024', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00025', N'Hoàng Văn Tùng', N'0943123456', CAST(N'1990-08-02' AS Date), N'tung.hoang@gmail.com', N'123456789', N'DC0025', 0, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00026', N'Đỗ Thị Hương', N'0904123456', CAST(N'1989-01-30' AS Date), N'huong.do@gmail.com', NULL, N'DC0026', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00027', N'Trần Thị Trang', N'0914123456', CAST(N'1998-06-15' AS Date), N'trang.tran@gmail.com', NULL, N'DC0027', 2580, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00028', N'Nguyễn Văn Tâm', N'0989123356', CAST(N'1996-03-21' AS Date), N'tam.nguyen@gmail.com', NULL, N'DC0028', 10148, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00029', N'Lê Thị Ngọc Ánh', N'0963123456', CAST(N'1991-12-25' AS Date), N'ngocanh.le@gmail.com', N'456789123', N'DC0029', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00030', N'Nguyễn Văn Dũng', N'0939123456', CAST(N'1994-10-10' AS Date), N'dung.nguyen@gmail.com', NULL, N'DC0030', 4404, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00031', N'Lê Thị Hương', N'0969123456', CAST(N'1988-05-16' AS Date), N'huong.le@gmail.com', NULL, N'DC0031', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00032', N'Nguyễn Văn Hải', N'0937123456', CAST(N'1993-09-01' AS Date), N'hai.nguyen@gmail.com', N'987654321', N'DC0032', 0, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00033', N'Trần Thị Huyền', N'0917123456', CAST(N'1990-02-08' AS Date), N'huyen.tran@gmail.com', NULL, N'DC0033', 1840, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00034', N'Nguyễn Thị Ngọc', N'0978123456', CAST(N'1997-07-04' AS Date), N'ngoc.nguyen@gmail.com', NULL, N'DC0000', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00035', N'Phạm Văn Lợi', N'0989123456', CAST(N'1992-12-22' AS Date), N'loi.pham@gmail.com', N'123456789', N'DC0000', 10926, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00036', N'Lê Thị Ánh Nguyệt', N'0456321598', CAST(N'1989-02-14' AS Date), N'lethianhnguyet@gmail.com', N'0123456789', N'DC0012', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00037', N'Phạm Thị Thanh Tâm', N'0909123456', CAST(N'1991-08-22' AS Date), N'phamthanh.tam@yahoo.com', N'9876543210', N'DC0039', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00038', N'Đặng Ngọc Yến Nhi', N'0977123456', CAST(N'1988-12-04' AS Date), N'dangngocyennhi@yahoo.com', N'4567891230', N'DC0060', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00039', N'Lâm Thị Tuyết Mai', N'0918123458', CAST(N'1995-07-29' AS Date), N'lamthituyetmai@gmail.com', N'7890123456', N'DC0019', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00040', N'Nguyễn Thị Bích Liên', N'0918723456', CAST(N'1994-01-12' AS Date), N'nguyenbichlien@gmail.com', NULL, N'DC0045', 3044, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00041', N'Trần Thị Thùy Dương', N'0903123456', CAST(N'1987-11-03' AS Date), N'tranthuyduong@yahoo.com', N'2468013579', N'DC0058', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00042', N'Phan Thị Thu Hà', N'0986123456', CAST(N'1992-04-27' AS Date), N'phanthuha@hotmail.com', N'9876543210', N'DC0059', 569, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00043', N'Lê Thị Bích Thủy', N'0901123456', CAST(N'1990-03-17' AS Date), N'lebichthuy@gmail.com', NULL, N'DC0037', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00044', N'Nguyễn Thị Hồng Nhung', N'0975123456', CAST(N'1998-09-10' AS Date), N'nguyenhongnhung@yahoo.com', NULL, N'DC0055', 1702, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00045', N'Trần Thị Thanh Thúy', N'0906123456', CAST(N'1989-05-06' AS Date), N'tranthanhthuy@gmail.com', N'0123456789', N'DC0065', 7875, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00046', N'Nguyễn Thị Phương Thảo', N'0912123456', CAST(N'1993-12-15' AS Date), N'nguyenphuongthao@yahoo.com', N'9876543210', N'DC0056', 5310, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00047', N'Phạm Thị Hoa Hồng', N'0974123456', CAST(N'1991-02-25' AS Date), N'phamhoahong@gmail.com', NULL, N'DC0049', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00048', N'Ngô Thị Tuyết Mai', N'0911123456', CAST(N'1996-11-18' AS Date), N'ngotuyetmai@hotmail.com', N'4567891230', N'DC0020', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00049', N'Trần Thị Phương Linh', N'0987126456', CAST(N'1994-10-01' AS Date), N'tranthiphuonglinh@yahoo.com', NULL, N'DC0057', 0, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00050', N'Lê Thị Ánh Nguyệt', N'0123695183', CAST(N'1989-02-14' AS Date), N'lethianhnguyet@gmail.com', N'0123456789', N'DC0070', 158, 0)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00051', N'Trần Đình Kiên', N'0159357852', CAST(N'1992-04-27' AS Date), N'kienTran@gmail.com', N'Null', N'DC0071', 0, 1)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoTen], [soDienThoai], [ngaySinh], [email], [maSoThue], [maDiaChi], [diemThanhVien], [gioiTinh]) VALUES (N'KH00052', N'Hồ Thị Như Tâm', N'0213652984', CAST(N'2003-01-01' AS Date), N'Không', N'Null', N'DC0072', 29161, 0)
GO
INSERT [dbo].[KhoHang] ([maKho], [tenKho], [dienTich], [maDiaChi]) VALUES (N'KHO01', N'Kho TP.Hồ Chí Minh', 1000, N'DC0017')
GO
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [soDienThoai], [maSoThue], [email], [maDiaChi]) VALUES (N'NCC01', N'Nova Computer', N'039253683', N'0310458128', N'NovaComputer@nova.vn', N'DC0057')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [soDienThoai], [maSoThue], [email], [maDiaChi]) VALUES (N'NCC02', N'Công ty TNHH An Khang', N'039253683', N'0310458128', N'binhminhphatlaptop@gmail.com', N'DC0045')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [soDienThoai], [maSoThue], [email], [maDiaChi]) VALUES (N'NCC03', N'SYNNEXFPT', N'0358021456', N'0310458128', N'binhminhphatlaptop@gmail.com', N'DC0045')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [soDienThoai], [maSoThue], [email], [maDiaChi]) VALUES (N'NCC04', N'US Supplier Inc.', N'1234567890', N'US123456789', N'info@ussupplier.com', N'DC0040')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [soDienThoai], [maSoThue], [email], [maDiaChi]) VALUES (N'NCC05', N'FPT Corporation', N'19006600', N'0101777796', N'info@fpt.com.vn', N'DC0006')
INSERT [dbo].[NhaCungCap] ([maNhaCungCap], [tenNhaCungCap], [soDienThoai], [maSoThue], [email], [maDiaChi]) VALUES (N'NCC06', N'TGGD Corporation', N'18006936', N'0102141866', N'info@tggd.vn', N'DC0025')
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0001', N'Nguyễn Thị Hồng Nhung', N'0987654321', CAST(N'1995-05-23' AS Date), N'nhung.nguyen@example.com', N'Nhân viên kinh doanh', N'DC0008', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0002', N'Lê Văn Dương', N'0909123456', CAST(N'1990-01-12' AS Date), N'duong.le@example.com', N'Nhân viên kinh doanh', N'DC0001', 1, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0003', N'Phạm Hồng Đăng', N'0918234567', CAST(N'1985-08-11' AS Date), N'dang.pham@example.com', N'Quản lý', N'DC0002', 1, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0004', N'Trần Thị Bích Phương', N'0976543210', CAST(N'1992-11-27' AS Date), N'phuong.tran@example.com', N'Nhân viên kinh doanh', N'DC0003', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0005', N'Nguyễn Minh Thảo', N'0969123456', CAST(N'1997-04-01' AS Date), N'thao.nguyen@example.com', N'Nhân viên kinh doanh', N'DC0004', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0006', N'Phan Thị Thu Hiền', N'0908123456', CAST(N'1988-02-18' AS Date), N'hien.phan@example.com', N'Nhân viên kinh doanh', N'DC0005', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0007', N'Vũ Thị Hoài An', N'0977654321', CAST(N'1991-12-31' AS Date), N'an.vu@example.com', N'Nhân viên kinh doanh', N'DC0006', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0008', N'Trần Văn Đức', N'0912123456', CAST(N'1995-03-25' AS Date), N'duc.tran@example.com', N'Nhân viên kinh doanh', N'DC0007', 1, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0009', N'Nguyễn Thanh Thúy', N'0988123456', CAST(N'1993-07-09' AS Date), N'thuy.nguyen@example.com', N'Nhân viên kinh doanh', N'DC0008', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0010', N'Lê Hoàng Anh', N'0969123456', CAST(N'1998-09-10' AS Date), N'anh.le@example.com', N'Nhân viên kinh doanh', N'DC0009', 1, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0011', N'Trần Đức Anh', N'0978123456', CAST(N'1996-12-01' AS Date), N'anh.tran@example.com', N'Nhân viên kinh doanh', N'DC0010', 1, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0012', N'Phạm Thanh Hương', N'0912123456', CAST(N'1990-05-15' AS Date), N'huong.pham@example.com', N'Nhân viên kinh doanh', N'DC0011', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0013', N'Lê Thị Thanh Trúc', N'0987654321', CAST(N'1988-02-12' AS Date), N'trucle@gmail.com', N'Nhân viên kinh doanh', N'DC0005', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0014', N'Phạm Thị Ngọc Yến', N'0987123456', CAST(N'1992-05-30' AS Date), N'yentran@gmail.com', N'Nhân viên kinh doanh', N'DC0005', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0015', N'Nguyễn Thị Trúc Phương', N'0908123456', CAST(N'1995-08-22' AS Date), N'phuongnguyen2@gmail.com', N'Nhân viên kinh doanh', N'DC0006', 0, 1)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0016', N'Lê Hoàng Nam', N'0978123456', CAST(N'1986-06-25' AS Date), N'namle@gmail.com', N'Nhân viên kinh doanh', N'DC0006', 0, 0)
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [soDienThoai], [ngaySinh], [email], [chucDanh], [maDiaChi], [gioiTinh], [trangThai]) VALUES (N'NV0017', N'Lê Hoàng Khang', N'0383741660', CAST(N'2023-04-26' AS Date), N'iamhoangkhang@icloud.com', N'Nhân viên kinh doanh', N'DC0069', 1, 1)
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0001', N'Chuột Gaming Logitech G502 Hero', 590000, 0, N'Kích thước: 132 x 75 x 40 mm; Trọng lượng sản phẩm: 121 g; Độ dài dây: 200 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0002', N'Chuột Gaming Razer DeathAdder V2', 1290000, 0, N'Kích thước: 127 x 61.7 x 42.7 mm; Trọng lượng sản phẩm: 82 g; Độ dài dây: 200 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 1.8 m; Độ phân giải chuột: 20000 dpi; Độ bền nút nhấn: 70 triệu lần nhấn', 12, 8, 10, N'THHI48')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0003', N'Chuột Gaming Glorious Model O-', 1690000, 0, N'Kích thước: 120 x 63 x 36 mm; Trọng lượng sản phẩm: 58 g; Độ dài dây: 200 cm; Màu sắc: Trắng; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 1.8 m; Độ phân giải chuột: 12000 dpi; Độ bền nút nhấn: 20 triệu lần nhấn', 12, 8, 10, N'THHI49')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0004', N'Chuột không dây Logitech M590', 650000, 0, N'Kích thước: 103 x 64 x 40 mm; Trọng lượng sản phẩm: 101 g; Độ dài dây: N/A; Màu sắc: Đen; Loại chuột: Chuột không dây; Kiểu kết nối: Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0005', N'Chuột không dây Logitech M590', 650000, 0, N'Kích thước: 103 x 64 x 40 mm; Trọng lượng sản phẩm: 101 g; Độ dài dây: N/A; Màu sắc: Đen; Loại chuột: Chuột không dây; Kiểu kết nối: Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0006', N'Chuột Gaming Logitech G502 Lightspeed', 1300000, 10, N'Kích thước: 132 x 75 x 40 mm; Trọng lượng sản phẩm: 114 g; Độ dài dây: 180 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: Không dây/ Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn; Đèn LED: RGB', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0007', N'Chuột Gaming Razer Basilisk V3', 1700000, 5, N'Kích thước: 130 x 60 x 42 mm; Trọng lượng sản phẩm: 92 g; Độ dài dây: 180 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 26000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn; Đèn LED: RGB', 24, 8, 10, N'THHI48')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0008', N'Chuột không dây HP Z3700', 300000, 0, N'Kích thước: 95 x 58 x 23 mm; Trọng lượng sản phẩm: 70 g; Kiểu kết nối: Không dây/ Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1200 dpi', 6, 8, 10, N'THHI02')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0009', N'Chuột không dây Dell WM126', 200000, 0, N'Kích thước: 99 x 63 x 37 mm; Trọng lượng sản phẩm: 100 g; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi', 12, 8, 10, N'THHI03')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0010', N'Chuột không dây Logitech M331 Silent Plus', 350000, 0, N'Kích thước: 105 x 67 x 38 mm; Trọng lượng sản phẩm: 91 g; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Ðộ phân giải chuột: 1000 dpi', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0011', N'Chuột Gaming Razer DeathAdder Elite', 820000, 0, N'Kích thước: 127 x 70 x 44 mm; Trọng lượng sản phẩm: 105 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn', 12, 8, 10, N'THHI48')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0012', N'Chuột Không Dây Logitech M331', 420000, 0, N'Kích thước: 105 x 67 x 38 mm; Trọng lượng sản phẩm: 91 g; Độ dài dây: Không; Màu sắc: Xanh dương; Loại chuột: Chuột Không Dây; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn', 24, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0013', N'Chuột Gaming Asus ROG Gladius II', 1200000, 0, N'Kích thước: 126 x 67 x 45 mm; Trọng lượng sản phẩm: 110 g; Độ dài dây: 200 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2 m; Độ phân giải chuột: 12000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn', 12, 8, 10, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0014', N'Chuột Không Dây Microsoft Arc Mouse', 890000, 0, N'Kích thước: 131 x 55 x 15 mm; Trọng lượng sản phẩm: 82.5 g; Độ dài dây: Không; Màu sắc: Đen; Loại chuột: Chuột Không Dây; Kiểu kết nối: Bluetooth; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn', 12, 8, 10, N'THHI04')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0015', N'Chuột máy tính Logitech G300s', 325000, 10, N'Kích thước: 72 x 41 x 136 mm; Trọng lượng sản phẩm: 144 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 4000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0016', N'Chuột máy tính Logitech G102', 400000, 5, N'Kích thước: 116.6 x 62.15 x 38.2 mm; Trọng lượng sản phẩm: 85 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 8000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0017', N'Chuột máy tính Razer Viper', 2500000, 0, N'Kích thước: 126.73 x 66.2 x 37.81 mm; Trọng lượng sản phẩm: 69 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 70 triệu lần nhấn', 24, 8, 10, N'THHI48')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0018', N'Chuột máy tính Corsair Katar', 1190000, 0, N'Kích thước: 110.7 x 63.9 x 37.8 mm; Trọng lượng sản phẩm: 85 g; Độ dài dây: 210 cm; Màu sắc: Đen; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 2.1 m; Độ phân giải chuột: 10000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn', 24, 8, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0019', N'Chuột gaming Razer DeathAdder Elite', 1125000, 10, N'Kích thước: 127 x 70 x 44 mm; Trọng lượng sản phẩm: 105 g; Loại chuột: Chuột Gaming; Kiểu kết nối: USB; Khoảng cách kết nối: 1.8 m; Độ phân giải chuột: 16000 dpi; Độ bền nút nhấn: 50 triệu lần nhấn; Tốc độ phản hồi: 1ms', 12, 8, 10, N'THHI48')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0020', N'Chuột không dây Logitech M325', 450000, 0, N'Kích thước: 60 x 100 x 33 mm; Trọng lượng sản phẩm: 91 g; Loại chuột: Chuột không dây; Kiểu kết nối: USB; Khoảng cách kết nối: 10 m; Độ phân giải chuột: 1000 dpi; Độ bền nút nhấn: 10 triệu lần nhấn; Thời gian sử dụng pin: 18 tháng', 12, 8, 10, N'THHI01')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0021', N'RAM desktop KINGMAX (1 x 16GB) DDR5 4800MHz (KM-LD5-4800-16GS)', 3690000, 24, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR5; Bus: 48000MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0022', N'RAM desktop KINGMAX (1x8GB) DDR3 1600MHz', 1250000, 20, N'Màu sắc: Xanh lá; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR3; Bus: 1600MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0023', N'RAM laptop KINGMAX (1x4GB) DDR3L 1600MHz', 790000, 36, N'Màu sắc: Xanh lá; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR3L; Bus: 1600MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0024', N'RAM desktop KINGMAX (1x4GB) DDR4 2400MHz', 690000, 20, N'Màu sắc: Đỏ; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 2400MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0025', N'RAM desktop KINGMAX (1x4GB) DDR4 2666MHz', 790000, 53, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 2666MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0026', N'RAM desktop KINGMAX (1x8GB) DDR4 2666MHz', 859000, 19, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 2666MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0027', N'RAM laptop KINGMAX (1x8GB) DDR3L 1600MHz', 1350000, 27, N'Màu sắc: Xanh lá; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR3L; Bus: 1600MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0028', N'RAM desktop G.SKILL Aegis F4-2666C19S-8GIS (1x8GB) DDR4 2666MHz', 759000, 14, N'Màu sắc: Đen, Đỏ; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 2666MHz;', 36, 3, 10, N'THHI06')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0029', N'RAM desktop KLEVV CRAS II KM4Z4GX1N (1x4GB) DDR4 2400MHz', 910000, 2, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 2400MHz;', 36, 3, 10, N'THHI07')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0030', N'RAM KLEVV Bolt 1x4GB DDR4 3000MHz - KM4B4GX1A', 990000, 12, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 4GB; Thế hệ: DDR4; Bus: 3000MHz;', 36, 3, 10, N'THHI07')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0031', N'RAM desktop CORSAIR Vengeance RGB Pro CMW32GX4M2D3000C16 (2x16GB) DDR4 3000MHz', 4790000, 13, N'Màu sắc: Đen; Đèn LED: RBG; Dung lượng: 2 x 16GB; Thế hệ: DDR4; Bus: 3000MHz;', 36, 3, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0032', N'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 8GB) DDR4 3200MHz (KM-LD4-3200-8GHSB)', 1390000, 47, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 3200MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0033', N'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 16GB) DDR4 3200MHz (KM-LD4-3200-16GHSB)', 2590000, 50, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR4; Bus: 3200MHz;', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0034', N'RAM desktop GIGABYTE GP-ARS32G52D5 (2 x 16GB) DDR5 5200MHz (GP-ARS32G52D5)', 7999000, 7, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 2 x 16GB; Thế hệ: DDR5; Bus: 5200MHz;', 36, 3, 10, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0035', N'RAM desktop Lexar D5DU016G-R4800GS2A (1 x 16GB) DDR5 4800MHz', 4190000, 2, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR5; Bus: 4800MHz;', 36, 3, 10, N'THHI10')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0036', N'RAM desktop Lexar LD4AU016G (1 x 16GB) DDR4 3200MHz (B3200GSST)', 1790000, 28, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 16GB; Thế hệ: DDR4; Bus: 3200MHz;', 36, 3, 10, N'THHI10')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0037', N'RAM desktop CRUCIAL CT8G48C40U5 (1 x 8GB) DDR5 4800MHz (CT8G48C40U5)', 1490000, 34, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR5; Bus: 4800MHz;', 36, 3, 10, N'THHI11')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0038', N'RAM desktop Lexar LD4AS008G-B2666GSST (1 x 8GB) DDR4 2666MHz (LD4AS008G-B2666GSST)', 790000, 9, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 1 x 8GB; Thế hệ: DDR4; Bus: 2666MHz;', 36, 3, 10, N'THHI10')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0039', N'RAM desktop CORSAIR CMH64GX5M2B5600C36 (2 x 32GB) DDR5 5600MHz (CMH64GX5M2B5600C36)', 9490000, 5, N'Màu sắc: Đen; Đèn LED: Không LED; Dung lượng: 2 x 32GB; Thế hệ: DDR5; Bus: 5600MHz;', 36, 3, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0040', N'RAM desktop CORSAIR Ram Desktop Corsair DOMINATOR PLATINUM RGB Black Heatspreader (2 x 8GB)', 4290000, 12, N'Màu sắc: Đen; Đèn LED: RGB; Dung lượng: 2 x 8GB; Thế hệ: DDR4; Bus: 3200MHz;', 36, 3, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0041', N'Quạt CPU Noctua NH-D15', 2770000, 0, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 140mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: NâuChiều cao(cm): 165cm; Số vòng quay của quạt(RPM): 1500RMP; Độ ồn(dBA): 24.6dB(A); Khối lượng(kg): 1.32 kgSocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2066, Intel LGA 2011, Intel LGA 2011-3, AMD AM4, AMD AM3, AMD AM3+;', 72, 7, 10, N'THHI12')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0042', N'Quạt CPU Noctua NH-U12S DX-3647', 3040000, 14, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: NâuChiều cao(cm): 158cm; Số vòng quay của quạt(RPM): 2000RMP; Độ ồn(dBA): 22.6dB(A); Khối lượng(kg): 1.018 kgSocket được hỗ trợ: Intel LGA 3647;', 72, 7, 10, N'THHI12')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0043', N'Quạt CPU Noctua NH-U12S DX-3647', 2960000, 0, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 140mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: ĐenChiều cao(cm): 165cm; Số vòng quay của quạt(RPM): 1500RMP; Độ ồn(dBA): 24.6dB(A); Khối lượng(kg): 1.32 kgSocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2066, Intel LGA 2011, Intel LGA 2011-3, AMD AM4, AMD AM3, AMD AM3+;', 72, 7, 10, N'THHI12')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0044', N'Quạt CPU Golden Field ACF-120 RGB', 250000, 0, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm; Màu sắc: Đen; Đèn LED: RGBChiều cao(cm): 11cm; Số vòng quay của quạt(RPM): 1800RPM(PWM) ± 10%; Độ ồn(dBA): <=29dB(A)Socket được hỗ trợ: Intel LGA 1151-v2, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2011, Intel LGA 775, Intel LGA 1366, Intel LGA 2011-3, AMD AM3, AMD AM3+;', 12, 7, 10, N'THHI13')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0045', N'Quạt CPU CM Hyper 212 ARGB', 755000, 7.42, N'Dạng tản nhiệt: Quạt; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Bạc; Đèn LED: RGBSố vòng quay của quạt(RPM): 650-1800 RPM (PWM) ± 5%; Độ ồn(dBA): 8 - 27 DBASocket được hỗ trợ: Intel LGA 1700, Intel LGA 1200, Intel LGA 1151-v2, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, AMD AM4;', 12, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0046', N'Quạt CPU CM Hyper 212 Spectrum V2', 769000, 6.052, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen, Bạc; Đèn LED: RGBChiều cao(cm): 15.7cm; Số vòng quay của quạt(RPM): 650-1800 RPM ± 5%; Độ ồn(dBA): 8 - 27 dBASocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, AMD AM4, Intel LGA 1200;', 12, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0047', N'Quạt CPU Deepcool Gammaxx 400 Red V2 (LED Đỏ)', 570000, 15.965, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 120mm; Chất liệu tản nhiệt: Nhôm; Màu sắc: Đen, Bạc; Đèn LED: ĐỏChiều cao(cm): 15.4cm; Số vòng quay của quạt(RPM): 500~1650 RPM±10%; Độ ồn(dBA): ≤27.8 dB(A);  Khối lượng(kg): 0.606kgSocket được hỗ trợ: Intel LGA 1200, Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1366, AMD AM4, AMD AM3+, AMD AM3, AMD AM2+, AMD AM2;', 12, 7, 10, N'THHI15')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0048', N'Quạt CPU Cooler Master HYPER 212 ARGB TURBO', 1150000, 14, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 2 x 120 mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen; Đèn LED: RGBChiều cao(cm): 15.7cm; Số vòng quay của quạt(RPM): 650-1800 RPM; Độ ồn(dBA): 8 - 27 dBASocket được hỗ trợ: Intel LGA 1151, Intel LGA 1150, Intel LGA 1155, Intel LGA 1156, Intel LGA 2066, Intel LGA 2011, Intel LGA 775, Intel LGA 1366, Intel LGA 2011-3, AMD AM4;', 12, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0049', N'Quạt CPU ID-Cooling SE-207-XT Black (2 fan)', 1190000, 17.731, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 2 x 120 mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen, BạcChiều cao(cm): 15.7cm; Số vòng quay của quạt(RPM): Quạt 1: 700-1800RPM (PWM), Quạt 2: 700-1800RPM (PWM); Khối lượng(kg): 1.3kg (kèm quạt / phụ kiện)Socket được hỗ trợ: Intel LGA 2066, Intel LGA 2011, Intel LGA 1200, Intel LGA 1150, Intel LGA 1151, Intel LGA 1155, Intel LGA 1156, AMD AM4;', 24, 7, 10, N'THHI16')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0050', N'Tản khí Cooler Master MasterAir MA621P TR4 Edition', 1370000, 11.022, N'Dạng tản nhiệt: Tản khí; Kích thước quạt(mm): 1 x 120 mm; Chất liệu tản nhiệt: Nhôm, Đồng; Màu sắc: Đen, Bạc; Đèn LED: RGBChiều cao(cm): 16.45cm; Số vòng quay của quạt(RPM): 600 - 1800 RPM (PWM) ± 10%; Độ ồn(dBA): 31dBA(Max); Khối lượng(kg): 0.87Socket được hỗ trợ: AMD TR4;', 24, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0051', N'CPU Intel Core I5-7500 (3.4GHz - 3.8GHz)', 5970000, 60, N'CPU: Core i5-7500; Series: Core i5; Socket: 1151; Số nhân xử lý: 4; Số luồng xử lý: 4; Kiến trúc: Kaby Lake (14 nm); Code name: Kaby Lake; Thế hệ: Intel Core thế hệ thứ 7; Tốc độ xử lý: 3.4 GHz - 3.8 GHz; Cache: 6MB;', 36, 0, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0052', N'CPU AMD Ryzen R7 1800X (3.6GHz - 4GHz)', 9240000, 57.8, N'CPU: Ryzen 7 1800X; Series: Ryzen 7; Socket: AM4; Số nhân xử lý: 8; Số luồng xử lý: 16; Kiến trúc: Zen (14 nm); Code name: Summit Ridge; Thế hệ: AMD Ryzen thế hệ thứ 1; Tốc độ xử lý: 3.6 GHz - 4 GHz; Cache 16MB;', 36, 0, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0053', N'CPU AMD Ryzen R7 1700X (3.4GHz - 3.8GHz)', 8499000, 54, N'CPU: Ryzen 7 1700X; Series: Ryzen 7; Socket: AM4; Số nhân xử lý: 8; Số luồng xử lý: 16; Kiến trúc: Zen (14 nm); Code name: Summit Ridge; Thế hệ: AMD Ryzen thế hệ thứ 1; Tốc độ xử lý: 3.4 GHz - 3.8 GHz; Cache: 16MB;', 36, 0, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0054', N'CPU Intel Core I5-7600 (3.5GHz - 4.1GHz)', 6230000, 59.88, N'CPU: Core i5-7600; Series: Core i5; Socket: 1151; Số nhân xử lý: 4; Số luồng xử lý: 4; Kiến trúc: Kaby Lake (14 nm); Code name: Kaby Lake; Thế hệ: Intel Core thế hệ thứ 7; Tốc độ xử lý: 3.5 GHz - 4.1 GHz; Cache: 6MB; N', 36, 0, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0055', N'CPU Intel Core I5-8600K (3.6GHz)', 7300000, 52.14, N'CPU: Core i5-8600K; Series: Core i5; Socket: 1151-v2; Số nhân xử lý: 6; Số luồng xử lý: 6; Kiến trúc: Coffee Lake (14 nm); Code name: Coffee Lake; Thế hệ: Intel Core thế hệ thứ 8; Tốc độ xử lý: 3.6 GHz; Cache: 9MB;', 36, 0, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0056', N'CPU INTEL Pentium G6400 (2C/4T, 4.00GHz, 4MB) - 1200', 1899000, 26.33, N'CPU: Pentium G6400; Series: Pentium; Socket: 1200; Số nhân xử lý: 2; Số luồng xử lý: 4; Kiến trúc: Comet Lake; Code name: Comet Lake; Thế hệ: Intel Pentium Gold; Tốc độ xử lý: 4.00GHz;', 36, 0, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0057', N'CPU AMD Ryzen 5 2600 (6C/12T, 3.4 GHz - 3.9 GHz, 16MB) - AM4', 3990000, 40, N'CPU: Ryzen 5 2600; Series: Ryzen 5; Socket: AM4; Số nhân xử lý: 6; Số luồng xử lý: 12; Kiến trúc: Zen+ (12 nm); Code name: Pinnacle Ridge; Thế hệ: AMD Ryzen thế hệ thứ 2; Tốc độ xử lý: 3.4 GHz - 3.9 GHz; Cache: 16MB;', 36, 0, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0058', N'CPU AMD Ryzen 5 2600X (6C/12T, 3.6 GHz - 4.2 GHz, 16MB) - AM4', 4790000, 40, N'CPU: Ryzen 5 2600X; Series: Ryzen 5; Socket: AM4; Số nhân xử lý: 6; Số luồng xử lý: 12; Kiến trúc: Zen+ (12 nm); Code name: Pinnacle Ridge; Thế hệ: AMD Ryzen thế hệ thứ 2; Tốc độ xử lý: 3.6 GHz - 4.2 GHz; Cache: 16MB; N', 36, 0, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0059', N'CPU AMD Ryzen Threadripper 1920X (12C/24T, 3.5 GHz - 4.0 GHz, 39MB) - TR4', 2.22E+07, 66, N'CPU: Ryzen Threadripper 1920X; Series: Ryzen Threadripper; Socket: TR4; Số nhân xử lý: 12; Số luồng xử lý: 24; Kiến trúc: Zen (14 nm); Thế hệ: AMD Ryzen thế hệ thứ 1; Tốc độ xử lý: 3.5 GHz - 4.0 GHz; Cache: 39MB;', 36, 0, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0060', N'CPU Intel Core i7-9700K (8C/8T, 3.6 GHz - 4.9 GHz, 12MB) - LGA 1151-v2', 1.0979E+07, 42.6, N'CPU: Core i7-9700K; Series: Core i7; Socket: 1151-v2; Số nhân xử lý: 8; Số luồng xử lý: 8; Kiến trúc: Coffee Lake (14 nm); Code name: Coffee Lake; Thế hệ: Intel Core thế hệ thứ 9; Tốc độ xử lý: 3.6 GHz - 4.9 GHz; Cache: 12MB;', 36, 0, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0061', N'Main Board ASUS ROG Strix B550-E Gaming (Wi-Fi 6)', 4500000, 5, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 2.5GbE; cổng HDMI; cổng DisplayPort', 24, 1, 1, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0062', N'Main Board MSI MPG B450 TOMAHAWK MAX', 2400000, 10, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 1 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 1GbE; cổng HDMI; cổng DisplayPort', 24, 1, 1, N'THHI20')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0063', N'Main Board Gigabyte B550 AORUS PRO (Wi-Fi 6)', 3500000, 7, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 2.5GbE; cổng HDMI; cổng DisplayPort', 24, 1, 1, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0064', N'Main Board ASRock B450M Steel Legend', 1800000, 15, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 1 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 1GbE; cổng HDMI; cổng DisplayPort', 24, 1, 1, N'THHI21')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0065', N'Main Board ASUS TUF Gaming B460-PLUS (Wi-Fi 6)', 2300000, 8, N'Intel chipset; LGA1200 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 1GbE; cổng HDMI; cổng DisplayPort', 24, 1, 1, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0066', N'Main Board MSI MAG B550 TOMAHAWK (Wi-Fi 6)', 3500000, 10, N'AMD chipset; AM4 socket; 4 khe cắm RAM DDR4; 2 khe cắm M.2; 6 cổng USB 3.2; cổng Ethernet 2.5GbE; cổng HDMI; cổng DisplayPort', 24, 1, 1, N'THHI20')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0067', N'Mainboard ASROCK Z490 Extreme4', 4939000, 11.338, N'Chipset:Z490; Socket:1200; Kích thước:ATX; Khe RAM tối đa:4 khe; Kiểu RAM hỗ trợ:DDR4; Hỗ trợ bộ nhớ tối đa:128GB; Bus RAM hỗ trợ:2800MHz, 2400MHz, 2666MHz, 3200MHz, 3600MHz, 3733MHz, 3866MHz, 4000MHz, 4133MHz, 2933MHz;', 36, 1, 1, N'THHI21')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0068', N'Mainboard ASUS H410M-E', 1640000, 12, N'Chipset: H410; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe;', 36, 1, 1, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0069', N'Mainboard ASUS H410M-CS', 1810000, 10, N'Chipset: H410; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2933MHz, 2800MHz, 2666MHz, 2400MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s; Cổng xuất hình: 1 x HDMI, 1 x VGA/D-sub; Khe PCI: 1 x PCIe 3.0 x16, 1 x PCIe 3.0 x1; Số cổng USB: 2 x USB 3.2 (tối đa 3), 4 x USB 2.0 (tối đa 5); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek ALC887 7.1-Channel High Definition Audio CODEC*;', 36, 1, 1, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0070', N'Mainboard MSI H510M PRO', 2190000, 10, N'Chipset: H510; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2666MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 NVMe; Kiểu khe M.2 hỗ trợ: M.2 NVMe;', 36, 1, 1, N'THHI20')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0071', N'Mainboard GIGABYTE H510M-H', 1990000, 12, N'Chipset: H510; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2400MHz, 2666MHz, 3000MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 1 x VGA/D-sub; Khe PCI: 1 x PCI x161 x PCI x1; Số cổng USB: 2 x USB 3.2 (tối đa 4)4 x USB 2.0/1.1(Tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek® Audio CODECHigh Definition Audio 2/4/5.1/7.1-channel;', 36, 1, 1, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0072', N'Mainboard ASROCK H510M-HDV', 1830000, 7, N'Chipset: H510; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s; Cổng xuất hình: 1 x HDMI, 1 x DVI-D, 1 x VGA/D-sub; Khe PCI: 1 x PCIe 4.0 x16, 1 x PCIe 3.0 x1; Số cổng USB: 2 x USB 3.2 (tối đa 4), 4 x USB 2.0 (tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: 7.1 CH HD Audio (Realtek ALC897 Audio Codec);', 36, 1, 1, N'THHI21')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0073', N'Mainboard ASUS PRIME A320M-K', 1700000, 0, N'Chipset: A320; Socket: AM4; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 32GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 1 x VGA/D-sub; Khe PCI: 3 x PCIe 3.0/2.0 x16, 2 x PCIe 2.0 x1; Đèn LED: Đơn sắc; Số cổng USB: 4 x USB 3.1, hỗ trợ tối đa 2 x USB 3.0, 2 x USB 2.0 (tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek ALC887 8-Channel High Definition Audio CODEC;', 36, 1, 1, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0074', N'Mainboard ASUS ROG MAXIMUS XI GENE', 8906000, 10, N'Chipset: Z390; Socket: 1151-v2; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 64GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3866MHz, 4000MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 4 x M.2 NVMe; Kiểu khe M.2 hỗ trợ: M.2 NVMe; Cổng xuất hình: 1 x HDMI; Khe PCI: 1 x PCIe 3.0 x16, 1 x PCIe 3.0 x4; Đèn LED: RGB; Số cổng USB: 1 x USB Type C, 7 x USB 3.1 (tối đa 12), 6 x USB 2.0; LAN: 1 x LAN 1 Gb/s; Kết nối không dây: Bluetooth 5.0, WiFi 802.11 a/b/g/n/ac; Âm thanh: ROG SupremeFX 8-Channel CODEC;', 36, 1, 1, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0075', N'Mainboard ASUS Prime A320M-E', 1550000, 10.32, N'Chipset: A320; Socket: AM4; Kích thước: Micro-ATX; Khe RAM tối đa: 2 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 32GB; Bus RAM hỗ trợ: 2400MHz, 2666MHz, 3200MHz, 2933MHz, 2133MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 SATA/NVMe, 1 x M.2 SATA; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 1 x DVI-D, 1 x VGA/D-sub; Khe PCI: 2 x PCIe 3.0/2.0 x16, 2 x PCIe 2.0 x1; Số cổng USB: 5 x USB 3.1 (tối đa 7), 0 x USB 2.0 (tối đa 4); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek® ALC887 8-Channel;', 36, 1, 1, N'THHI19')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0076', N'Mainboard GIGABYTE Z490 AORUS PRO AX', 7090000, 8.195, N'Chipset: Z490; Socket: 1200; Kích thước: ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3733MHz, 3866MHz; Lưu trữ: Hỗ trợ Intel Optane, 2 x M.2 SATA/NVMe, 6 x SATA 3 6Gb/s; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI; Khe PCI: 3 x PCI Express x16, 2 x PCI Express x1; Multi-GPU: AMD CrossFire, NVIDIA SLI; Đèn LED: RGB; Số cổng USB: 1 x USB Type-C (tối đa 2), 5x USB 3.2 (tối đa 7), 4x USB 2.0 (tối đa 8); LAN: 1 x LAN 1 Gb/s; Kết nối không dây: WiFi 802.11 ax, Bluetooth 5.1; Âm thanh: Realtek® ALC1220-VB codec;', 36, 1, 1, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0077', N'Mainboard GIGABYTE Z490 Aorus Xtreme', 1.919E+07, 7.613, N'Chipset: Z490; Socket: 1200; Kích thước: Extended-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2800MHz, 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3733MHz, 3866MHz; Lưu trữ: 1 x M.2 NVMe, Hỗ trợ Intel Optane, 2 x M.2 SATA/NVMe, 6 x SATA 3 6Gb/s; Kiểu khe M.2 hỗ trợ: M.2 SATA/NVMe; Cổng xuất hình: 1 x HDMI, 2 x Thunderbolt 3; Khe PCI: 3 x PCI Express x16 slot; Multi-GPU: NVIDIA SLI, AMD CrossFire; Đèn LED: RGB; Số cổng USB: 2 x USB type-C (Tối đa 3), 6 x USB 3.2 (tối đa 8), 2 x USB 2.0 (tối đa 6); LAN: 2 x LAN 1 Gb/s; Kết nối không dây: WiFi 802.11 ax, Bluetooth 5.1; Âm thanh: Realtek ALC1220-VB codec+ESS ES9218P DAC (front panel audio), Realtek ALC1220-VB codec+ESS ES9018K2M DAC+TI OPA1622 operational amplifier (rear panel audio), Support for DTS:X Ultra, High Definition Audio, 2/4/5.1/7.1-channel;', 36, 1, 1, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0078', N'Mainboard GIGABYTE Z490 Aorus Xtreme WF', 2.879E+07, 39, N'Chipset: Z490; Socket: 1200; Kích thước: Extended-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2133MHz, 2400MHz, 2666MHz, 2800MHz, 2933MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3400MHz;', 36, 1, 1, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0079', N'Mainboard MSI MEG Z490 GODLIKE', 2.0599E+07, 12.76, N'Chipset: Z490; Socket: 1200; Kích thước: Extended-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2400MHz, 2666MHz, 3000MHz, 3200MHz, 3300MHz, 3333MHz, 3600MHz, 3733MHz, 3866MHz, 4000MHz;', 36, 1, 1, N'THHI20')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0080', N'Mainboard GIGABYTE B460M-DS3H V2', 2180000, 12.76, N'Chipset: H470; Socket: 1200; Kích thước: Micro-ATX; Khe RAM tối đa: 4 khe; Kiểu RAM hỗ trợ: DDR4; Hỗ trợ bộ nhớ tối đa: 128GB; Bus RAM hỗ trợ: 2133MHz, 2400MHz, 2666MHz, 2933MHz; Lưu trữ: 4 x SATA 3 6Gb/s, 1 x M.2 NVMe, Hỗ trợ Intel Optane; Kiểu khe M.2 hỗ trợ: M.2 NVMe; Cổng xuất hình: 1 x HDMI, 1 x DVI-D, 1 x VGA/D-sub; Khe PCI: 2 x PCI Express x16, 2 x PCI Express x1; Số cổng USB: 3 x USB 3.2 (tối đa 5), 2 x USB 2.0 (tối đa 6); LAN: 1 x LAN 1 Gb/s; Âm thanh: Realtek® Audio CODEC High Definition Audio 2/4/5.1/7.1-channel;', 36, 1, 1, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0081', N'RAM Kingston HyperX Fury 8GB DDR4 3200MHz', 900000, 10, N'Trọng lượng sản phẩm: 50g;Màu sắc: Đen; Dung lượng RAM: 8GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Không; Đèn LED: Không', 12, 3, 10, N'THHI23')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0082', N'RAM Corsair Vengeance RGB Pro 16GB DDR4 3200MHz', 1800000, 5, N'Trọng lượng sản phẩm: 70g;Màu sắc: Trắng; Dung lượng RAM: 16GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB', 12, 3, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0083', N'RAM G.Skill Trident Z Neo 32GB DDR4 3600MHz', 3600000, 0, N'Trọng lượng sản phẩm: 80g;Màu sắc: Đen; Dung lượng RAM: 32GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB', 12, 3, 10, N'THHI06')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0084', N'RAM HyperX Predator 64GB DDR4 3200MHz', 7200000, 15, N'Trọng lượng sản phẩm: 100g;Màu sắc: Đen; Dung lượng RAM: 64GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không', 24, 3, 10, N'THHI23')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0085', N'RAM Adata XPG Spectrix D50 RGB 16GB DDR4 3600MHz', 1900000, 8, N'Trọng lượng sản phẩm: 70g; Màu sắc: Đen; Dung lượng RAM: 16GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB', 12, 3, 10, N'THHI24')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0086', N'RAM Crucial Ballistix RGB 32GB DDR4 3200MHz', 3500000, 10, N'Trọng lượng sản phẩm: 80g; Màu sắc: Trắng; Dung lượng RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB', 24, 3, 10, N'THHI11')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0087', N'RAM Kingston HyperX Fury 16GB DDR4', 1000000, 10, N'Trọng lượng sản phẩm:100g;Màu sắc: Xám; Dung lượng RAM: 16 GB; Loại RAM:	DDR4; Tốc độ RAM: 2666 MHz; Hỗ trợ EEC:	Không; Tản nhiệt: Có; Đèn LED: Không', 36, 3, 10, N'THHI23')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0088', N'RAM Corsair Vengeance LPX 16GB DDR4', 1200000, 10, N'Trọng lượng sản phẩm:100g;Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM:	DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC:	Không; Tản nhiệt: Có; Đèn LED: Không', 36, 3, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0089', N'RAM G.Skill Ripjaws V 16GB DDR4', 1250000, 10, N'Trọng lượng sản phẩm:100g;Màu sắc: Đỏ; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0090', N'RAM Patriot Viper Steel 16GB DDR4', 1300000, 10, N'Trọng lượng sản phẩm:100g;Màu sắc: Xám; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không', 36, 3, 10, N'THHI25')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0091', N'RAM Crucial Ballistix 16GB DDR4', 1350000, 10, N'Trọng lượng sản phẩm:100g; Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED:	RGB', 36, 3, 10, N'THHI11')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0092', N'Apacer RAM 8GB DDR4 2666MHz', 1500000, 5, N'Trọng lượng sản phẩm: 200g; Dung lượng RAM: 8 GB; Loại RAM: DDR4; Tốc độ RAM: 2666 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Không; Đèn LED: Không', 36, 3, 10, N'THHI26')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0093', N'Geil RAM 16GB DDR4 3200MHz', 2800000, 10, N'Trọng lượng sản phẩm: 250g; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: RGB', 36, 3, 10, N'THHI07')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0094', N'Gigabyte AORUS RAM 32GB DDR4 3600MHz', 6500000, 15, N'Trọng lượng sản phẩm: 280g; Dung lượng RAM: 32 GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: RGB', 36, 3, 10, N'THHI09')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0095', N'HP V6 DDR4 8GB 2666MHz', 1700000, 5, N'Trọng lượng sản phẩm: 200g; Dung lượng RAM: 8 GB; Loại RAM: DDR4; Tốc độ RAM: 2666 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Không; Đèn LED: Không', 36, 3, 10, N'THHI02')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0096', N'Kingmax Zeus Dragon DDR4 16GB 3200MHz', 3000000, 10, N'Trọng lượng sản phẩm: 200g; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: Không', 36, 3, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0097', N'Apacer Panther Rage DDR4 32GB 3600MHz', 7500000, 15, N'Trọng lượng sản phẩm: 250g; Dung lượng RAM: 32 GB; Loại RAM: DDR4; Tốc độ RAM: 3600 MHz; Hỗ trợ ECC: Không; Tản nhiệt: Có; Đèn LED: RGB', 36, 3, 10, N'THHI26')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0098', N'Ram Apacer Panther Rage DDR4 16GB (2x8GB) 3000MHz', 1500000, 5, N'Trọng lượng sản phẩm: 200g; Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3000 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB', 36, 3, 10, N'THHI26')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0099', N'Ram Geil EVO POTENZA DDR4 16GB (2x8GB) 2400MHz', 1400000, 7, N'Trọng lượng sản phẩm: 250g; Màu sắc: Đỏ; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 2400 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: Không', 36, 3, 10, N'THHI07')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0100', N'Ram Gigabyte AORUS RGB DDR4 16GB (2x8GB) 3200MHz', 1700000, 10, N'Trọng lượng sản phẩm: 300g; Màu sắc: Đen; Dung lượng RAM: 16 GB; Loại RAM: DDR4; Tốc độ RAM: 3200 MHz; Hỗ trợ EEC: Không; Tản nhiệt: Có; Đèn LED: RGB', 36, 3, 10, N'THHI09')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0101', N'Ổ cứng SSD Transcend 370S 128GB 2.5" SATA 3', 1239000, 5, N'Dung lượng: 128GB; Kết nối: SATA 3; Bộ nhớ NAND: Không; Kích thước: 2.5"; Tốc độ đọc: 560MB/s; Tốc độ ghi: 480MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;', 36, 4, 5, N'THHI37')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0102', N'Ổ cứng HDD Western Digital Blue 1TB 3.5" SATA 3', 1350000, 3, N'Dung lượng: 1TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 64MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Xanh dương;', 24, 4, 10, N'THHI39')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0103', N'Ổ cứng SSD Crucial P3 500GB M.2 NVMe PCIe Gen3', 2250000, 0, N'Dung lượng: 500GB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D NAND; Tốc độ đọc: 3400MB/s; Tốc độ ghi: 3000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;', 36, 4, 10, N'THHI11')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0104', N'Ổ cứng SSD Kingston A2000 256GB M.2 NVMe PCIe Gen3', 1099000, 8, N'Dung lượng: 256GB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D NAND; Tốc độ đọc: 2200MB/s; Tốc độ ghi: 2000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;', 36, 4, 10, N'THHI23')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0105', N'Ổ cứng HDD Seagate BarraCuda 2TB 3.5" SATA 3', 1550000, 0, N'Dung lượng: 2TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 256MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Đen;', 24, 4, 10, N'THHI38')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0106', N'Ổ cứng SSD ADATA XPG SX8200 Pro 512GB M.2 NVMe PCIe Gen3', 1899000, 5, N'Dung lượng: 512GB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D TLC; Tốc độ đọc: 3500MB/s; Tốc độ ghi: 2300MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;', 36, 4, 10, N'THHI24')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0107', N'Ổ cứng SSD Intel 660p 1TB M.2 NVMe PCIe Gen3', 2799000, 0, N'Dung lượng: 1TB; Kết nối: M.2 NVMe PCIe Gen3; Bộ nhớ NAND: 3D TLC; Tốc độ đọc: 1800MB/s; Tốc độ ghi: 1800MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Xanh dương;', 36, 4, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0108', N'Ổ cứng HDD Toshiba P300 3TB 3.5" SATA 3', 2199000, 0, N'Dung lượng: 3TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 64MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Đen;', 24, 4, 10, N'THHI40')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0109', N'Ổ cứng SSD HGST Ultrastar DC HC310 4TB 3.5" SATA 3', 5599000, 0, N'Dung lượng: 4TB; Kết nối: SATA 3; Bộ nhớ NAND: Không; Tốc độ đọc: 255MB/s; Tốc độ ghi: 205MB/s; Kiểu ổ cứng: SSD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Xanh lá cây;', 36, 4, 10, N'THHI41')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0110', N'Ổ cứng HDD Western Digital Red Pro 10TB 3.5" SATA 3', 9899000, 0, N'Dung lượng: 10TB; Kết nối: SATA 3; Tốc độ quay: 7200 RPM; Bufer: 256MB; Kiểu ổ cứng: HDD; Kích thước: 3.5"; Màu sắc của Ổ cứng: Đỏ;', 60, 4, 10, N'THHI39')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0111', N'Nguồn máy tính Golden Field Dragon GTX480 - 400W', 559000, 18, N'Công suất tối đa: 400W; Cáp rời: không hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 1 x 8-pin (6+2) PCIE, 4 x SATA; Quạt làm mát: 1 x 120 mm;', 12, 5, 0.1, N'THHI13')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0112', N'Nguồn máy tính Thermaltake Toughpower GF1 850W 80+ Gold RGB', 2999000, 15, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 140 mm;', 24, 5, 0.1, N'THHI31')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0113', N'Nguồn máy tính XFX ProSeries P1-850S-NLB9 850W 80+ Gold Full Modular', 3499000, 12, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 135 mm;', 24, 5, 0.1, N'THHI32')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0114', N'Nguồn máy tính FSP Dagger Pro 650W 80+ Gold Full Modular', 1999000, 10, N'Công suất tối đa: 650W; Cáp rời: hỗ trợ; Chuẩn kích thước: SFX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 4 x 8-pin (6+2) PCIE, 3 x SATA, 2 x Peripheral; Quạt làm mát: 1 x 92 mm;', 24, 5, 0.1, N'THHI33')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0115', N'Nguồn máy tính Seasonic Prime GX-850 850W 80+ Gold Full Modular', 3499000, 15, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 135 mm;', 36, 5, 0.1, N'THHI34')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0116', N'Nguồn máy tính CoolerMaster MWE 750W 80+ White', 1499000, 0, N'Công suất tối đa: 750W; Cáp rời: không hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 4 x 8-pin (6+2) PCIE, 6 x SATA, 3 x Peripheral; Quạt làm mát: 1 x 120 mm;', 24, 5, 0.1, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0117', N'Nguồn máy tính XFX XTR 550W 80+ Gold Full Modular', 1299000, 8, N'Công suất tối đa: 550W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 2 x 8-pin (6+2) PCIE, 9 x SATA, 4 x Peripheral; Quạt làm mát: 1 x 135 mm;', 24, 5, 0.1, N'THHI32')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0118', N'Nguồn máy tính Huntkey MVP Pro 750W 80+ Gold Full Modular', 1699000, 12, N'Công suất tối đa: 750W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 4 x 8-pin (6+2) PCIE, 10 x SATA, 5 x Peripheral; Quạt làm mát: 1 x 135 mm;', 36, 5, 0.1, N'THHI35')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0119', N'Nguồn máy tính Corsair RM850x 850W 80+ Gold Full Modular', 2499000, 10, N'Công suất tối đa: 850W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 6 x 8-pin (6+2) PCIE, 10 x SATA, 4 x Peripheral; Quạt làm mát: 1 x 135 mm;', 60, 5, 0.1, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0120', N'Nguồn máy tính FSP Hydro PTM+ 1200W 80+ Platinum Full Modular', 5699000, 15, N'Công suất tối đa: 1200W; Cáp rời: hỗ trợ; Chuẩn kích thước: ATX; Số cổng cắm: 1 x 24-pin Main, 2 x 8-pin (4+4) EPS, 8 x 8-pin (6+2) PCIE, 12 x SATA, 6 x Peripheral; Quạt làm mát: 1 x 135 mm;', 48, 5, 0.1, N'THHI33')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0121', N'Case máy tính MSI MAG Series', 2000000, 10, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 45.8 x 23 x 51.4 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";', 24, 6, 0.1, N'THHI20')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0122', N'Case máy tính MSI MPG Series', 2500000, 5, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 47.5 x 23.6 x 51.4 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";', 24, 6, 0.1, N'THHI20')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0123', N'Case máy tính Cooler Master Masterbox Series', 1500000, 12, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 45.2 x 20.8 x 45.5 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";', 24, 6, 0.1, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0124', N'Case máy tính Thermaltake Core Series', 1800000, 15, N'Màu sắc: Đen; Chất liệu: Thép; Kích thước: 46.9 x 21 x 49.8 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 3 x 2.5";', 24, 6, 0.1, N'THHI31')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0125', N'Case máy tính Thermaltake View Series', 3200000, 8, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 52 x 23 x 51 cm; Loại case: Full Tower; Hỗ trợ mainboard: E-ATX, ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 6 x 2.5";', 24, 6, 0.1, N'THHI31')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0126', N'Case máy tính Corsair Obsidian Series', 2800000, 10, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 54 x 23 x 50 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 3 x 3.5", 4 x 2.5";', 24, 6, 0.1, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0127', N'Case máy tính NZXT H Series', 2500000, 12, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 50 x 22 x 48 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";', 24, 6, 0.1, N'THHI28')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0128', N'Case máy tính Cooler Master MasterBox Series', 1500000, 18, N'Màu sắc: Đen; Chất liệu: Thép, Nhựa ABS; Kích thước: 45.6 x 20.8 x 45.8 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 4 x 2.5";', 24, 6, 0.1, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0129', N'Case máy tính Phanteks Eclipse Series', 2800000, 15, N'Màu sắc: Đen; Chất liệu: Thép, Kính cường lực; Kích thước: 45.7 x 21 x 47.2 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 3 x 2.5";', 24, 6, 0.1, N'THHI29')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0130', N'Case máy tính Corsair Carbide Series', 1800000, 10, N'Màu sắc: Trắng; Chất liệu: Thép, Nhựa ABS; Kích thước: 46.6 x 21 x 47.6 cm; Loại case: Mid Tower; Hỗ trợ mainboard: ATX, Micro-ATX, Mini-ITX; Số lượng ổ đĩa hỗ trợ: 2 x 3.5", 3 x 2.5";', 24, 6, 0.1, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0131', N'VGA NVIDIA GeForce RTX 3080', 2.5E+07, 0, N'10GB GDDR6X, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a', 36, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0132', N'VGA AMD Radeon RX 6800 XT', 2E+07, 0, N'16GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4', 36, 2, 10, N'THHI23')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0133', N'VGA NVIDIA GeForce RTX 3070', 1.8E+07, 0, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a', 36, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0134', N'VGA AMD Radeon RX 6700 XT', 1.5E+07, 0, N'12GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4', 36, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0135', N'VGA NVIDIA GeForce RTX 3060 Ti', 1.3E+07, 0, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a', 36, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0136', N'VGA AMD Radeon RX 6600 XT', 1E+07, 0, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4', 36, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0137', N'VGA NVIDIA GeForce RTX 3090', 4E+07, 0, N'24GB GDDR6X, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a', 36, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0138', N'VGA AMD Radeon RX 6900 XT', 3.5E+07, 0, N'16GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4', 36, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0139', N'VGA NVIDIA GeForce GTX 1660 Super', 8000000, 0, N'6GB GDDR6, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 24, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0140', N'VGA AMD Radeon RX 5500 XT', 6000000, 0, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 24, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0141', N'VGA NVIDIA GeForce GTX 1650 Super', 5000000, 0, N'4GB GDDR6, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 24, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0142', N'VGA AMD Radeon RX 580', 4000000, 0, N'8GB GDDR5, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 24, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0143', N'VGA NVIDIA GeForce GT 1030', 2000000, 0, N'2GB GDDR5, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 12, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0144', N'VGA AMD Radeon RX 5700 XT', 1.2E+07, 0, N'8GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 36, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0145', N'VGA NVIDIA GeForce RTX 3060', 1E+07, 0, N'12GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.1, Cổng DisplayPort 1.4a', 36, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0146', N'VGA AMD Radeon RX 550', 1500000, 0, N'4GB GDDR5, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 12, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0147', N'VGA NVIDIA GeForce GT 710', 1000000, 0, N'2GB DDR3, Giao diện PCIe 2.0, Cổng HDMI 1.4, Cổng DVI-D, Cổng VGA', 12, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0148', N'VGA AMD Radeon RX 5600 XT', 8000000, 0, N'6GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 24, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0149', N'VGA NVIDIA GeForce GT 1030 DDR4', 1500000, 0, N'2GB DDR4, Giao diện PCIe 3.0, Cổng HDMI 2.0b, Cổng DisplayPort 1.4', 12, 2, 10, N'THHI22')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0150', N'VGA AMD Radeon RX 5500', 2500000, 0, N'4GB GDDR6, Giao diện PCIe 4.0, Cổng HDMI 2.0b, Cổng DisplayPort 1', 24, 2, 10, N'THHI18')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0151', N'Tản nhiệt CPU Corsair H45', 900000, 5, N'Kích thu?c: 120 x 120 x 25 mm; Ðèn: Không; D?ng t?n nhi?t: T?n nhi?t ch?t l?ng; Công su?t t?n nhi?t: 0.15 A; Ði?n áp: 12 V; Ði?n nang tiêu th?: 1.8 W; T?c d? qu?t: 1700 vòng/phút; Socket h? tr?: Intel LGA 1200, 115x, 1366, 2011, 2011-3, AMD AM2, AM3, FM1, FM2', 12, 7, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0152', N'Tản nhiệt CPU Cooler Master Hyper 212 RGB Black Edition', 800000, 0, N'Kích thước: 120 x 79.6 x 158.8 mm; Ðèn: LED RGB; D?ng t?n nhi?t: T?n khí; Công su?t t?n nhi?t: 0.37 A; Ði?n áp: 12 V; Ði?n nang tiêu th?: 4.44 W; T?c d? qu?t: 650-2000 vòng/phút; Socket h? tr?: Intel LGA 2066, 2011-v3, 2011, 1200, 1151, 1150, 1155, 1156, AMD AM4, AM3+, AM3, AM2+, AM2, FM2+, FM2, FM1', 24, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0153', N'Tản nhiệt CPU Thermaltake UX100', 500000, 10, N'Kích thước: 120 x 120 x 65 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.27 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.24 W; Tốc độ quạt: 1800 vòng/phút; Socket hỗ trợ: Intel LGA 1200, 1151, 1150, 1155, 1156, AMD AM4, FM2+, FM2, FM1, AM3+, AM3, AM2+, AM2', 12, 7, 10, N'THHI31')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0154', N'Tản nhiệt CPU Corsair H100i RGB Platinum SE', 2900000, 10, N'Kích thước: 277 x 120 x 27 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản nước; Công suất tản nhiệt: 0.22 A; Điện áp: 12 V; Điện năng tiêu thụ: 2.88 W; Tốc độ quạt: 2400 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156, Intel LGA 1200, Intel LGA 2011/2066, AMD Socket AM4, AMD Socket sTRX4, AMD Socket TR4;', 12, 7, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0155', N'Tản nhiệt CPU Cooler Master MasterAir MA610P', 1300000, 5, N'Kích thước: 122.3 x 112.8 x 166.5 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.28 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.36 W; Tốc độ quạt: 800-2000 vòng/phút; Socket hỗ trợ: Intel LGA 2066/2011-3/2011/1366/1200/1156/1155/1151/1150, AMD Socket AM4/AM3+/AM3/AM2+/AM2/FM2+/FM2/FM1;', 24, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0156', N'Tản nhiệt CPU Noctua NH-D15 Chromax.Black', 3900000, 0, N'Kích thước: 165 x 150 x 161 mm; Đèn: Không; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.22 A; Điện áp: 12 V; Điện năng tiêu thụ: 2.64 W; Tốc độ quạt: 300-1500 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/2011-0/2011-3/2066, AMD Socket AM2+/AM3+/FM1/FM2+/AM4;', 72, 7, 10, N'THHI12')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0157', N'Tản nhiệt CPU Corsair A500', 720000, 5, N'Kích thước: 169mm x 149mm x 160mm; Đèn: Không; Dạng tản nhiệt: Tản khí kép; Công suất tản nhiệt: 0.23A; Điện áp: 12V; Điện năng tiêu thụ: 2.76W; Tốc độ quạt: 2400 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/1200/2011/2066 và AMD AM2/AM3/AM4/FM1/FM2.', 24, 7, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0158', N'Tản nhiệt CPU Kingmax G50 RGB', 520000, 7, N'Kích thước: 130mm x 130mm x 157mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.2A; Điện áp: 12V; Điện năng tiêu thụ: 2.4W; Tốc độ quạt: 1800 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/1200/1366/2011/2066 và AMD AM2/AM3/AM4/FM1/FM2.', 36, 7, 10, N'THHI05')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0159', N'Tản nhiệt CPU Klevv CRAS X RGB', 840000, 0, N'Kích thước: 128mm x 148mm x 159mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí kép; Công suất tản nhiệt: 0.3A; Điện áp: 12V; Điện năng tiêu thụ: 3.6W; Tốc độ quạt: 1800 vòng/phút; Socket hỗ trợ: Intel LGA 775/1150/1151/1155/1156/1366/2011/2066 và AMD AM2/AM3/AM4/FM1/FM2.', 24, 7, 10, N'THHI07')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0160', N'Cooler Master Hyper 212 RGB Black Edition', 900000, 5, N'Kích thước: 120 x 120 x 25 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.16 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.92 W; Tốc độ quạt: 1500 vòng/phút; Socket hỗ trợ: Không;', 36, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0161', N'Noctua NH-D15S', 1800000, 0, N'Kích thước: 160 x 150 x 135 mm; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.16 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.92 W; Tốc độ quạt: 1500 vòng/phút; Socket hỗ trợ: AMD AM4, AM3(+), FM2(+), Intel LGA1200, LGA115x (LGA1150, LGA1151, LGA1155, LGA1156), LGA2011-0 & LGA2011-3 (Square ILM);', 60, 7, 10, N'THHI12')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0162', N'be quiet! Dark Rock Pro 4', 2100000, 0, N'Kích thước: 145.7 x 136 x 162.8 mm; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.16 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.92 W; Tốc độ quạt: 1500 vòng/phút; Socket hỗ trợ: Intel LGA 1200 / 1150 / 1151 / 1155 / 1156 / 1366 / 2011(-3) Square ILM / 2066, AMD AM4 / AM3(+) / AM2(+) / FM2(+) / FM1;', 36, 7, 10, N'THHI47')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0163', N'Cooler Master Hyper 212 RGB', 570000, 0, N'Kích thước: 120 x 79.6 x 158.8 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.26 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.12 W; Tốc độ quạt: 650 - 2000 RPM; Socket hỗ trợ: Intel: LGA 2066/2011-3/2011/1366/1200/1156/1155/1151/1150/775, AMD: AM4/AM3+/AM3/AM2+/AM2/FM2+/FM2/FM1', 24, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0164', N'Noctua NH-D15', 2000000, 0, N'Kích thước: 165 x 150 x 161 mm; Đèn: Không; Dạng tản nhiệt: Tản khí kép; Công suất tản nhiệt: 0.15 A; Điện áp: 12 V; Điện năng tiêu thụ: 1.8 W; Tốc độ quạt: 300 - 1500 RPM; Socket hỗ trợ: Intel LGA2066, LGA2011-0 & LGA2011-3 (Square ILM), LGA1200, LGA1156, LGA1155, LGA1151, LGA1150 & AMD AM2, AM2+, AM3, AM3+, AM4, FM1, FM2, FM2+ (backplate required), TR4 & sTRX4 (pre-installed).', 36, 7, 10, N'THHI12')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0165', N'Tản nhiệt CPU ID Cooling SE-234-ARGB', 450000, 0, N'Kích thước: 120 x 76 x 154.5 mm; Đèn: LED ARGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.25 A; Điện áp: 12 V; Điện năng tiêu thụ: 3 W; Tốc độ quạt: 700 - 1800 RPM; Socket hỗ trợ: Intel LGA 775/1150/1151/1155/1156/1366/2011/2066, AMD FM1/FM2/FM2+/AM2/AM2+/AM3/AM3+/AM4', 12, 7, 10, N'THHI16')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0166', N'Tản nhiệt CPU Be Quiet! Dark Rock Slim', 1390000, 0, N'Kích thước: 120 x 48.4 x 159.4 mm; Đèn: Không; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.22 A; Điện áp: 12 V; Điện năng tiêu thụ: 2.64 W; Tốc độ quạt: 1200 vòng/phút; Socket hỗ trợ: Intel LGA 1150/1151/1155/1156/1200, AMD AM4', 24, 7, 10, N'THHI47')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0167', N'Tản nhiệt CPU Cooler Master Hyper 212 RGB Black Edition', 350000, 0, N'Kích thước: 120 x 79.6 x 158.8 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản khí; Công suất tản nhiệt: 0.37 A; Điện áp: 12 V; Điện năng tiêu thụ: 4.44 W; Tốc độ quạt: 650 - 2000 vòng/phút; Socket hỗ trợ: AMD: AM4, AM3+, AM3, AM2+, AM2, FM2+, FM2, FM1; Intel: LGA 2066, 2011-v3, 2011, 1366, 1151, 1150, 1155, 1156, 1200', 12, 7, 10, N'THHI14')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0168', N'Tản nhiệt CPU Corsair Hydro Series H100i RGB Platinum SE', 3700000, 5, N'Kích thước: 277 x 120 x 27 mm; Đèn: LED RGB; Dạng tản nhiệt: Tản nước; Công suất tản nhiệt: 0.276 A; Điện áp: 12 V; Điện năng tiêu thụ: 3.31 W; Tốc độ quạt: 2400 vòng/phút; Socket hỗ trợ: AMD: AM2, AM3, AM4, FM1, FM2, sTRX4, TR4; Intel: 1200, 1150, 1151, 1155, 1156, 1366, 2011, 2011-3, 2066', 12, 7, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0169', N'Bàn phím cơ K95 Platinum XT', 2799000, 0, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Có; LED: RGB; Tần số phản hồi: 1000 Hz; Khả năng chống nước: IP32; Các phím Macro: Có', 12, 9, 10, N'THHI08')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0170', N'Bàn phím cơ Ducky One 2 SF', 2499000, 0, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Không; LED: Không; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không', 12, 9, 10, N'THHI06')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0171', N'Bàn phím cơ Keychron K2', 2399000, 0, N'Loại bàn phím: cơ; Kết nối: Bluetooth/USB; Bàn phím số: Có; LED: RGB; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không', 12, 9, 10, N'THHI15')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0172', N'Bàn phím cơ logitech Pok3r RGB', 3499000, 0, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Không; LED: RGB; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không', 12, 9, 10, N'THHI31')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0173', N'Bàn phím cơ FC900R PD', 3299000, 0, N'Loại bàn phím: cơ; Kết nối: USB; Bàn phím số: Có; LED: Không; Tần số phản hồi: 1000 Hz; Khả năng chống nước: Không; Các phím Macro: Không', 12, 9, 10, N'THHI40')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0174', N'Ổ cứng SSD Intel 660p 512GB M.2 NVMe', 2499000, 15, N'Dung lượng: 512GB; Kết nối: M.2 NVMe; Bộ nhớ NAND: TLC; Tốc độ đọc: 1500MB/s; Tốc độ ghi: 1000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Xanh lá cây;', 36, 4, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0175', N'Ổ cứng HDD ADATA HD770G 1TB USB 3.2', 1299000, 5, N'Dung lượng: 1TB; Kết nối: USB 3.2; Tốc độ quay: Không; Kiểu ổ cứng: HDD; Kích thước: 2.5"; Màu sắc của Ổ cứng: Đen;', 24, 4, 10, N'THHI24')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0176', N'Ổ cứng SSD Kingston A2000 1TB M.2 NVMe', 2899000, 10, N'Dung lượng: 1TB; Kết nối: M.2 NVMe; Bộ nhớ NAND: TLC; Tốc độ đọc: 2200MB/s; Tốc độ ghi: 2000MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;', 36, 4, 10, N'THHI23')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0177', N'Ổ cứng HDD Toshiba P300 3TB 7200rpm', 1799000, 0, N'Dung lượng: 3TB; Tốc độ quay: 7200rpm; Kiểu ổ cứng: HDD; Màu sắc của Ổ cứng: Xanh đen;', 24, 4, 10, N'THHI40')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0178', N'Ổ cứng SSD HGST Ultrastar DC SS530 960GB SATA', 3799000, 10, N'Dung lượng: 960GB; Kết nối: SATA; Bộ nhớ NAND: TLC; Tốc độ đọc: 540MB/s; Tốc độ ghi: 485MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;', 36, 4, 10, N'THHI41')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0179', N'Ổ cứng HDD Seagate Barracuda ST2000DM008 2TB 7200rpm', 1299000, 5, N'Dung lượng: 2TB; Tốc độ quay: 7200rpm; Kiểu ổ cứng: HDD; Màu sắc của Ổ cứng: Đen;', 24, 4, 10, N'THHI38')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0180', N'Ổ cứng SSD ADATA XPG SX6000 Lite 256GB M.2 PCIe', 699000, 15, N'Dung lượng: 256GB; Kết nối: M.2 PCIe; Bộ nhớ NAND: TLC; Tốc độ đọc: 1800MB/s; Tốc độ ghi: 1200MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Đen;', 36, 4, 10, N'THHI24')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0181', N'Ổ cứng SSD Intel 660p Series 1TB M.2 PCIe NVMe', 3999000, 0, N'Dung lượng: 1TB; Kết nối: M.2 PCIe NVMe; Bộ nhớ NAND: QLC; Tốc độ đọc: 1800MB/s; Tốc độ ghi: 1800MB/s; Kiểu ổ cứng: SSD; Màu sắc của Ổ cứng: Xám;', 36, 4, 10, N'THHI17')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0182', N'Ổ cứng SSD Transcend 230S 128GB 2.5" SATA 3" SATA 3', 699000, 5, N'Dung lượng: 128GB; Kê´t nô´i: SATA 3; Bộ nhớ NAND: Không; Ki´ch thuo´c: 2.5"; Tô´c dô? do?c: 560MB/s; Tô´c dô? ghi: 520MB/s; Ki?u ? c?ng: SSD; Ma`u sa´c cu?a ? c?ng: Tr?ng;', 36, 4, 5, N'THHI37')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0183', N'Ổ cứng HDD Western Digital Black 4TB 3.5" SATA 3 - WD4005FZBX', 4499000, 5, N'Dung lượng: 4TB; Kê´t nô´i: SATA 3; Bộ nhớ NAND: Không; Kích thước: 3.5; Tốc độ vòng quay: 7200RPM; Cache: 256MB; Kiểu ổ cứng: HDD; Màu sắc của ổ cứng: Ðen;', 36, 4, 5, N'THHI42')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaNhap], [giamGia], [cauHinh], [soThangBaoHanh], [maLoai], [VAT], [maThuongHieu]) VALUES (N'SP0184', N'Chuột không dây Logitech M600', 800000, 0, N'Kích thước: 103 x 64 x 40 mm;
Trọng lượng sản phẩm: 101 g;
Độ dài dây: N/A;
Màu sắc: Đen;
Loại chuột: Chuột không dây;
Kiểu kết nối: Bluetooth;
Khoảng cách kết nối: 10 m;
Độ phân giải chuột: 1000 dpi;
Độ bền nút nhấn: 10 triệu lần nhấn;', 24, 8, 10, N'THHI01')
GO
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0001', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0002', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0003', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0004', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0005', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0006', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0007', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0008', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0009', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0010', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0011', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0012', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0013', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0014', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0015', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0016', N'1111')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau]) VALUES (N'NV0017', N'02032003')
GO
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI01', N'Logitech', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI02', N'HP', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI03', N'Dell', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI04', N'Microsoft', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI05', N'Kingmax', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI06', N'G.Skill', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI07', N'Klevv', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI08', N'Corsair', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI09', N'Gigabyte', N'Ðài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI10', N'Lexar', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI11', N'Cruial', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI12', N'NOCTUA', N'Áo')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI13', N'GOLDEN FIELD', N'Trung Quốc')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI14', N'Cooler Master', N'Hà Lan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI15', N'DEEPCOOL', N'Trung Quốc')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI16', N'ID-COOLING', N'Trung Quốc')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI17', N'Intel', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI18', N'AMD', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI19', N'ASUS', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI20', N'MSI', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI21', N'Asrock', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI22', N'NVIDIA', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI23', N'Kingston', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI24', N'Adata', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI25', N'Patriot', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI26', N'Apacer', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI27', N'Geil', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI28', N'NZXT', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI29', N'Phanteks', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI30', N'Fractal Design', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI31', N'Thermltake', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI32', N'XFX', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI33', N'FSP', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI34', N'Seasonic', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI35', N'Huntkey', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI36', N'AcBel', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI37', N'Transcend', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI38', N'Seagate', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI39', N'Western Digital', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI40', N'Toshiba', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI41', N'HGST', N'Đài Loan')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI42', N'Samsung', N'Hàn quốc')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI43', N'Hitachi', N'Nhật bản')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI44', N'WD', N'Áo')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI45', N'Zotac', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI46', N'Palit', N'Trung Quốc')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI47', N'Be Quiet!', N'Trung Quốc')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI48', N'Razer', N'Mỹ')
INSERT [dbo].[ThuongHieu] ([maThuongHieu], [tenThuongHieu], [quocGia]) VALUES (N'THHI49', N'Glorious', N'Mỹ')
GO
ALTER TABLE [dbo].[ChiTietDonNhap]  WITH CHECK ADD  CONSTRAINT [FKChiTietDon280818] FOREIGN KEY([maDonNhap])
REFERENCES [dbo].[DonNhapHang] ([maDonNhap])
GO
ALTER TABLE [dbo].[ChiTietDonNhap] CHECK CONSTRAINT [FKChiTietDon280818]
GO
ALTER TABLE [dbo].[ChiTietDonNhap]  WITH CHECK ADD  CONSTRAINT [FKChiTietDon825282] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietDonNhap] CHECK CONSTRAINT [FKChiTietDon825282]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FKChiTietHoa141811] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FKChiTietHoa141811]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FKChiTietHoa903135] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FKChiTietHoa903135]
GO
ALTER TABLE [dbo].[ChiTietKhoHang]  WITH CHECK ADD  CONSTRAINT [FKChiTietKho299808] FOREIGN KEY([maKho])
REFERENCES [dbo].[KhoHang] ([maKho])
GO
ALTER TABLE [dbo].[ChiTietKhoHang] CHECK CONSTRAINT [FKChiTietKho299808]
GO
ALTER TABLE [dbo].[ChiTietKhoHang]  WITH CHECK ADD  CONSTRAINT [FKChiTietKho719856] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietKhoHang] CHECK CONSTRAINT [FKChiTietKho719856]
GO
ALTER TABLE [dbo].[DonNhapHang]  WITH CHECK ADD  CONSTRAINT [FKDonNhapHan451254] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[DonNhapHang] CHECK CONSTRAINT [FKDonNhapHan451254]
GO
ALTER TABLE [dbo].[DonNhapHang]  WITH CHECK ADD  CONSTRAINT [FKDonNhapHan502440] FOREIGN KEY([maKho])
REFERENCES [dbo].[KhoHang] ([maKho])
GO
ALTER TABLE [dbo].[DonNhapHang] CHECK CONSTRAINT [FKDonNhapHan502440]
GO
ALTER TABLE [dbo].[DonNhapHang]  WITH CHECK ADD  CONSTRAINT [FKDonNhapHan528637] FOREIGN KEY([maNhaCungCap])
REFERENCES [dbo].[NhaCungCap] ([maNhaCungCap])
GO
ALTER TABLE [dbo].[DonNhapHang] CHECK CONSTRAINT [FKDonNhapHan528637]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKHoaDon550538] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKHoaDon550538]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKHoaDon559341] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKHoaDon559341]
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD  CONSTRAINT [FKKhachHang383195] FOREIGN KEY([maDiaChi])
REFERENCES [dbo].[DiaChi] ([maDiaChi])
GO
ALTER TABLE [dbo].[KhachHang] CHECK CONSTRAINT [FKKhachHang383195]
GO
ALTER TABLE [dbo].[KhoHang]  WITH CHECK ADD  CONSTRAINT [FKKhoHang231296] FOREIGN KEY([maDiaChi])
REFERENCES [dbo].[DiaChi] ([maDiaChi])
GO
ALTER TABLE [dbo].[KhoHang] CHECK CONSTRAINT [FKKhoHang231296]
GO
ALTER TABLE [dbo].[NhaCungCap]  WITH CHECK ADD  CONSTRAINT [FKNhaCungCap660119] FOREIGN KEY([maDiaChi])
REFERENCES [dbo].[DiaChi] ([maDiaChi])
GO
ALTER TABLE [dbo].[NhaCungCap] CHECK CONSTRAINT [FKNhaCungCap660119]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FKNhanVien617893] FOREIGN KEY([maDiaChi])
REFERENCES [dbo].[DiaChi] ([maDiaChi])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FKNhanVien617893]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FKSanPham473948] FOREIGN KEY([maThuongHieu])
REFERENCES [dbo].[ThuongHieu] ([maThuongHieu])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FKSanPham473948]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FKTaiKhoan954915] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FKTaiKhoan954915]
GO
