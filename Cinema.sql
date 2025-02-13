USE [master]
GO

/****** Object:  Database [CINEMA]    Script Date: 10/30/2024 10:24:57 PM ******/
CREATE DATABASE [CINEMA]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CINEMA', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.XUYEN\MSSQL\DATA\CINEMA.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CINEMA_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.XUYEN\MSSQL\DATA\CINEMA_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CINEMA].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [CINEMA] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [CINEMA] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [CINEMA] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [CINEMA] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [CINEMA] SET ARITHABORT OFF 
GO

ALTER DATABASE [CINEMA] SET AUTO_CLOSE ON 
GO

ALTER DATABASE [CINEMA] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [CINEMA] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [CINEMA] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [CINEMA] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [CINEMA] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [CINEMA] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [CINEMA] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [CINEMA] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [CINEMA] SET  ENABLE_BROKER 
GO

ALTER DATABASE [CINEMA] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [CINEMA] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [CINEMA] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [CINEMA] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [CINEMA] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [CINEMA] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [CINEMA] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [CINEMA] SET RECOVERY SIMPLE 
GO

ALTER DATABASE [CINEMA] SET  MULTI_USER 
GO

ALTER DATABASE [CINEMA] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [CINEMA] SET DB_CHAINING OFF 
GO

ALTER DATABASE [CINEMA] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [CINEMA] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO

ALTER DATABASE [CINEMA] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [CINEMA] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO

ALTER DATABASE [CINEMA] SET QUERY_STORE = ON
GO

ALTER DATABASE [CINEMA] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO

ALTER DATABASE [CINEMA] SET  READ_WRITE 
GO

USE CINEMA;
CREATE TABLE NhanVien (
    maNV NVARCHAR(50) PRIMARY KEY,      -- Mã nhân viên là khóa chính
    hoTen NVARCHAR(100) NOT NULL,       -- Họ tên nhân viên
    diaChi NVARCHAR(255),               -- Địa chỉ
    ngaySinh DATE,                      -- Ngày sinh
    ngayVaoLam DATE,                    -- Ngày vào làm
    email NVARCHAR(100),                -- Email
    sdt NVARCHAR(15),                   -- Số điện thoại
    congViec NVARCHAR(50),              -- Công việc
    caLamViec NVARCHAR(50)              -- Ca làm việc
);
GO

-- Stored procedure lấy danh sách nhân viên
CREATE PROCEDURE LayDanhSachNhanVien
AS
BEGIN
    SELECT maNV, hoTen, diaChi, ngaySinh, ngayVaoLam, email, sdt, congViec, caLamViec
    FROM NhanVien;
END
GO

-- Stored procedure thêm nhân viên
CREATE PROCEDURE ThemNV
    @maNV NVARCHAR(50),
    @hoTen NVARCHAR(100),
    @diaChi NVARCHAR(255),
    @ngaySinh DATE,
    @ngayVaoLam DATE,
    @email NVARCHAR(100),
    @sdt NVARCHAR(15),
    @congViec NVARCHAR(50),
    @caLamViec NVARCHAR(50)
AS
BEGIN
    INSERT INTO NhanVien (maNV, hoTen, diaChi, ngaySinh, ngayVaoLam, email, sdt, congViec, caLamViec)
    VALUES (@maNV, @hoTen, @diaChi, @ngaySinh, @ngayVaoLam, @email, @sdt, @congViec, @caLamViec);
END
GO

-- Stored procedure xóa nhân viên
CREATE PROCEDURE XoaNV
    @maNV NVARCHAR(50)
AS
BEGIN
    DELETE FROM NhanVien
    WHERE maNV = @maNV;
END
GO

-- Stored procedure cập nhật nhân viên
CREATE PROCEDURE SuaNV
    @maNV NVARCHAR(50),
    @hoTen NVARCHAR(100),
    @diaChi NVARCHAR(255),
    @ngaySinh DATE,
    @ngayVaoLam DATE,
    @email NVARCHAR(100),
    @sdt NVARCHAR(15),
    @congViec NVARCHAR(50),
    @caLamViec NVARCHAR(50)
AS
BEGIN
    UPDATE NhanVien
    SET hoTen = @hoTen,
        diaChi = @diaChi,
        ngaySinh = @ngaySinh,
        ngayVaoLam = @ngayVaoLam,
        email = @email,
        sdt = @sdt,
        congViec = @congViec,
        caLamViec = @caLamViec
    WHERE maNV = @maNV;
END
GO

-- Stored procedure tìm kiếm nhân viên
CREATE PROCEDURE TimNV
    @maNV NVARCHAR(50)
AS
BEGIN
    SELECT maNV, hoTen, diaChi, ngaySinh, ngayVaoLam, email, sdt, congViec, caLamViec
    FROM NhanVien
    WHERE maNV = @maNV;
END
GO



USE CINEMA;
CREATE TABLE KhuyenMai (
    maKM VARCHAR(50) PRIMARY KEY,
    ngayBatDau DATE,
    ngayKetThuc DATE,
    giamGia FLOAT CHECK (giamGia >= 0 AND giamGia <= 100), -- Giảm giá từ 0% đến 100%
    moTa NVARCHAR(255)
);
GO

CREATE PROCEDURE ThemKM
    @maKM VARCHAR(50),
    @ngayBatDau DATE,
    @ngayKetThuc DATE,
    @giamGia FLOAT,
    @moTa NVARCHAR(255)
AS
BEGIN
    INSERT INTO KhuyenMai (maKM, ngayBatDau, ngayKetThuc, giamGia, moTa)
    VALUES (@maKM, @ngayBatDau, @ngayKetThuc, @giamGia, @moTa);
END;
GO

CREATE PROCEDURE SuaKM
    @maKM VARCHAR(50),
    @ngayBatDau DATE,
    @ngayKetThuc DATE,
    @giamGia FLOAT,
    @moTa NVARCHAR(255)
AS
BEGIN
    UPDATE KhuyenMai
    SET ngayBatDau = @ngayBatDau,
        ngayKetThuc = @ngayKetThuc,
        giamGia = @giamGia,
        moTa = @moTa
    WHERE maKM = @maKM;
END;
GO

CREATE PROCEDURE XoaKM
    @maKM VARCHAR(50)
AS
BEGIN
    DELETE FROM KhuyenMai
    WHERE maKM = @maKM;
END;
GO

CREATE PROCEDURE TimKM
    @maKM VARCHAR(50)
AS
BEGIN
    SELECT *
    FROM KhuyenMai
    WHERE maKM = @maKM;
END;
GO

CREATE PROCEDURE LayDanhSachKhuyenMai
AS
BEGIN
    SELECT *
    FROM KhuyenMai;
END;
GO



USE CINEMA;

CREATE TABLE Phim (
    maPhim VARCHAR(50) PRIMARY KEY,
    ten VARCHAR(255) NOT NULL,
    theLoai VARCHAR(100),
    daoDien VARCHAR(100),
    dienVienChinh VARCHAR(255),
    thoiLuong INT, 
    ngayPhatHanh DATE,
    gioiHanDoTuoi INT,
    moTaPhim TEXT,
    ngonNgu VARCHAR(50),
    dinhDang VARCHAR(50),
    nhaSX VARCHAR(100)
);
GO

CREATE PROCEDURE ThemPhim
    @maPhim VARCHAR(50),
    @ten VARCHAR(255),
    @theLoai VARCHAR(100),
    @daoDien VARCHAR(100),
    @dienVienChinh VARCHAR(255),
    @thoiLuong INT, 
    @ngayPhatHanh DATE,
    @gioiHanDoTuoi INT,
    @moTaPhim TEXT,
    @ngonNgu VARCHAR(50),
    @dinhDang VARCHAR(50),
    @nhaSX VARCHAR(100)
AS
BEGIN
    INSERT INTO Phim (maPhim, ten, theLoai, daoDien, dienVienChinh, thoiLuong, 
                      ngayPhatHanh, gioiHanDoTuoi, moTaPhim, ngonNgu, dinhDang, nhaSX)
    VALUES (@maPhim, @ten, @theLoai, @daoDien, @dienVienChinh, @thoiLuong,
            @ngayPhatHanh, @gioiHanDoTuoi, @moTaPhim, @ngonNgu, @dinhDang, @nhaSX);
END;
GO

CREATE PROCEDURE SuaPhim
    @maPhim VARCHAR(50),
    @ten VARCHAR(255),
    @theLoai VARCHAR(100),
    @daoDien VARCHAR(100),
    @dienVienChinh VARCHAR(255),
    @thoiLuong INT,
    @ngayPhatHanh DATE,
    @gioiHanDoTuoi INT,
    @moTaPhim TEXT,
    @ngonNgu VARCHAR(50),
    @dinhDang VARCHAR(50),
    @nhaSX VARCHAR(100)
AS
BEGIN
    UPDATE Phim
    SET ten = @ten,
        theLoai = @theLoai,
        daoDien = @daoDien,
        dienVienChinh = @dienVienChinh,
        thoiLuong = @thoiLuong,
        ngayPhatHanh = @ngayPhatHanh,
        gioiHanDoTuoi = @gioiHanDoTuoi,
        moTaPhim = @moTaPhim,
        ngonNgu = @ngonNgu,
        dinhDang = @dinhDang,
        nhaSX = @nhaSX
    WHERE maPhim = @maPhim;
END;
GO

CREATE PROCEDURE XoaPhim
    @maPhim VARCHAR(50)
AS
BEGIN
    DELETE FROM Phim
    WHERE maPhim = @maPhim;
END;
GO

CREATE PROCEDURE TimPhim
    @maPhim VARCHAR(50)
AS
BEGIN
    SELECT *
    FROM Phim
    WHERE maPhim = @maPhim;
END;
GO

CREATE PROCEDURE LayDanhSachPhim
AS
BEGIN
    SELECT *
    FROM Phim;
END;
GO


USE CINEMA;
CREATE TABLE KhachHang (
    maKH NVARCHAR(50) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    sdt NVARCHAR(15) NOT NULL,
    diemTichLuy INT NOT NULL
);
GO

CREATE PROCEDURE ThemKhachHang
    @maKH NVARCHAR(50),
    @hoTen NVARCHAR(100),
    @email NVARCHAR(100),
    @sdt NVARCHAR(15),
    @diemTichLuy INT
AS
BEGIN
    INSERT INTO KhachHang (maKH, hoTen, email, sdt, diemTichLuy)
    VALUES (@maKH, @hoTen, @email, @sdt, @diemTichLuy);
END
GO

CREATE PROCEDURE CapNhatKhachHang
    @maKH NVARCHAR(50),
    @hoTen NVARCHAR(100),
    @email NVARCHAR(100),
    @sdt NVARCHAR(15),
    @diemTichLuy INT
AS
BEGIN
    UPDATE KhachHang
    SET hoTen = @hoTen,
        email = @email,
        sdt = @sdt,
        diemTichLuy = @diemTichLuy
    WHERE maKH = @maKH;
END
GO

CREATE PROCEDURE XoaKhachHang
    @maKH NVARCHAR(50)
AS
BEGIN
    DELETE FROM KhachHang
    WHERE maKH = @maKH;
END
GO

CREATE PROCEDURE TimKhachHang
    @maKH NVARCHAR(50)
AS
BEGIN
    SELECT * FROM KhachHang
    WHERE maKH = @maKH;
END
GO

CREATE PROCEDURE LayDanhSachKhachHang
AS
BEGIN
    SELECT * FROM KhachHang;
END
GO



USE CINEMA;
CREATE TABLE PhongChieu (
    maPhong VARCHAR(10) PRIMARY KEY,
    soGhe INT,
    trangThai VARCHAR(50)
);
GO

CREATE PROCEDURE ThemPhongChieu
    @maPhong VARCHAR(10),
    @soGhe INT,
    @trangThai VARCHAR(50)
AS
BEGIN
    INSERT INTO PhongChieu (maPhong, soGhe, trangThai)
    VALUES (@maPhong, @soGhe, @trangThai);
END;
GO
CREATE PROCEDURE CapNhatPhongChieu
    @maPhong VARCHAR(10),
    @soGhe INT,
    @trangThai VARCHAR(50)
AS
BEGIN
    UPDATE PhongChieu
    SET soGhe = @soGhe,
        trangThai = @trangThai
    WHERE maPhong = @maPhong;
END;
GO

CREATE PROCEDURE XoaPhongChieu
    @maPhong VARCHAR(10)
AS
BEGIN
    DELETE FROM PhongChieu
    WHERE maPhong = @maPhong;
END;
GO

CREATE PROCEDURE TimPhongChieu
    @maPhong VARCHAR(10)
AS
BEGIN
    SELECT maPhong, soGhe, trangThai
    FROM PhongChieu
    WHERE maPhong = @maPhong;
END;
GO

CREATE PROCEDURE LayDanhSachPhongChieu
AS
BEGIN
    SELECT maPhong, soGhe, trangThai
    FROM PhongChieu;
END;
GO



USE CINEMA;
CREATE TABLE SuatChieu (
    maSuatChieu VARCHAR(10) PRIMARY KEY,
    maPhim VARCHAR(50) NOT NULL,
    maPhong VARCHAR(10) NOT NULL,
    dinhDang NVARCHAR(50),
    giaVe DECIMAL(10, 2) NOT NULL,
	ngayChieu DATE NOT NULL,
	gioChieu TIME NOT NULL,
    FOREIGN KEY (maPhim) REFERENCES Phim(maPhim),
    FOREIGN KEY (maPhong) REFERENCES PhongChieu(maPhong)
);

GO

USE CINEMA;
GO
CREATE PROCEDURE ThemSuatChieu (
    @maSuatChieu VARCHAR(10),
    @maPhim VARCHAR(10),
    @maPhong VARCHAR(10),
    @dinhDang NVARCHAR(50),
    @giaVe DECIMAL(10, 2),
    @ngayChieu DATE,
    @gioChieu TIME
)
AS
BEGIN
    INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, ngayChieu, gioChieu)
    VALUES (@maSuatChieu, @maPhim, @maPhong, @dinhDang, @giaVe, @ngayChieu, @gioChieu);
END;
GO


CREATE PROCEDURE CapNhatSuatChieu (
    @maSuatChieu VARCHAR(10),
    @maPhim VARCHAR(10),
    @maPhong VARCHAR(10),
    @dinhDang NVARCHAR(50),
    @giaVe DECIMAL(10, 2),
    @ngayChieu DATE,
    @gioChieu TIME
)
AS
BEGIN
    UPDATE SuatChieu
    SET maPhim = @maPhim,
        maPhong = @maPhong,
        dinhDang = @dinhDang,
        giaVe = @giaVe,
        ngayChieu = @ngayChieu,
        gioChieu = @gioChieu
    WHERE maSuatChieu = @maSuatChieu;
END;
GO


CREATE PROCEDURE XoaSuatChieu (
    @maSuatChieu VARCHAR(10)
)
AS
BEGIN
    DELETE FROM SuatChieu
    WHERE maSuatChieu = @maSuatChieu;
END;

GO

CREATE PROCEDURE TimSuatChieu (
    @maSuatChieu VARCHAR(10)
)
AS
BEGIN
    SELECT SC.maSuatChieu, SC.ngayChieu, SC.gioChieu, SC.dinhDang, SC.giaVe,
           P.maPhim, P.ten, PC.maPhong, PC.soGhe, PC.trangThai
    FROM SuatChieu SC
    JOIN Phim P ON SC.maPhim = P.maPhim
    JOIN PhongChieu PC ON SC.maPhong = PC.maPhong
    WHERE SC.maSuatChieu = @maSuatChieu;
END;
GO

CREATE PROCEDURE LayDanhSachSuatChieu
AS
BEGIN
    SELECT SC.maSuatChieu, SC.ngayChieu, SC.gioChieu, SC.dinhDang, SC.giaVe,
           P.maPhim, P.ten, PC.maPhong, PC.soGhe, PC.trangThai
    FROM SuatChieu SC
    JOIN Phim P ON SC.maPhim = P.maPhim
    JOIN PhongChieu PC ON SC.maPhong = PC.maPhong;
END;
GO


USE CINEMA
CREATE TABLE TaiKhoan (
    tenDangNhap NVARCHAR(50) PRIMARY KEY,
    matKhau NVARCHAR(50) NOT NULL,
	maNV NVARCHAR(50),
	FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);
GO

-- Thủ tục thêm tài khoản
CREATE PROCEDURE ThemTaiKhoan
    @TenDangNhap NVARCHAR(50),
    @MatKhau NVARCHAR(50),
    @MaNV NVARCHAR(50)
AS
BEGIN
    INSERT INTO TaiKhoan (tenDangNhap, matKhau, maNV)
    VALUES (@TenDangNhap, @MatKhau, @MaNV);
END
GO

-- Thủ tục cập nhật tài khoản
CREATE PROCEDURE CapNhatTaiKhoan
    @TenDangNhap NVARCHAR(50),
    @MatKhau NVARCHAR(50),
    @MaNV NVARCHAR(50)
AS
BEGIN
    UPDATE TaiKhoan
    SET matKhau = @MatKhau,
        maNV = @MaNV
    WHERE tenDangNhap = @TenDangNhap;
END
GO

-- Thủ tục xóa tài khoản
CREATE PROCEDURE XoaTaiKhoan
    @TenDangNhap NVARCHAR(50)
AS
BEGIN
    DELETE FROM TaiKhoan
    WHERE tenDangNhap = @TenDangNhap;
END
GO

-- Thủ tục tìm tài khoản
CREATE PROCEDURE TimTaiKhoan
    @TenDangNhap NVARCHAR(50)
AS
BEGIN
    SELECT tenDangNhap, matKhau, maNV
    FROM TaiKhoan
    WHERE tenDangNhap = @TenDangNhap;
END
GO

-- Thủ tục lấy danh sách tài khoản
CREATE PROCEDURE LayDanhSachTaiKhoan
AS
BEGIN
    SELECT tenDangNhap, matKhau, maNV
    FROM TaiKhoan;
END
GO

USE CINEMA
CREATE TABLE DoAnNhe (
    maDoAnNhe NVARCHAR(50) PRIMARY KEY,
    tenDoAnNhe NVARCHAR(100) NOT NULL,
    gia FLOAT NOT NULL,
    soLuongCon INT NOT NULL
);
GO
CREATE PROCEDURE ThemDoAnNhe
    @maDoAnNhe NVARCHAR(50),
    @tenDoAnNhe NVARCHAR(100),
    @gia FLOAT,
    @soLuongCon INT
AS
BEGIN
    INSERT INTO DoAnNhe (maDoAnNhe, tenDoAnNhe, gia, soLuongCon)
    VALUES (@maDoAnNhe, @tenDoAnNhe, @gia, @soLuongCon);
END
GO

CREATE PROCEDURE CapNhatDoAnNhe
    @maDoAnNhe NVARCHAR(50),
    @tenDoAnNhe NVARCHAR(100),
    @gia FLOAT,
    @soLuongCon INT
AS
BEGIN
    UPDATE DoAnNhe
    SET tenDoAnNhe = @tenDoAnNhe,
        gia = @gia,
        soLuongCon = @soLuongCon
    WHERE maDoAnNhe = @maDoAnNhe;
END
GO

CREATE PROCEDURE XoaDoAnNhe
    @maDoAnNhe NVARCHAR(50)
AS
BEGIN
    DELETE FROM DoAnNhe
    WHERE maDoAnNhe = @maDoAnNhe;
END
GO
CREATE PROCEDURE TimDoAnNhe
    @maDoAnNhe NVARCHAR(50)
AS
BEGIN
    SELECT *
    FROM DoAnNhe
    WHERE maDoAnNhe = @maDoAnNhe;
END
GO

CREATE PROCEDURE LayDanhSachDoAnNhe
AS
BEGIN
    SELECT *
    FROM DoAnNhe;
END
GO



USE CINEMA
-- Insert 20 customers using the ThemKhachHang stored procedure
EXEC ThemKhachHang 'KH001', 'Nguyen Van An', 'an.nguyen@example.com', '0123456789', 100;
EXEC ThemKhachHang 'KH002', 'Tran Thi Binh', 'binh.tran@example.com', '0123456790', 150;
EXEC ThemKhachHang 'KH003', 'Le Hoang Long', 'long.le@example.com', '0123456791', 200;
EXEC ThemKhachHang 'KH004', 'Pham Van Dung', 'dung.pham@example.com', '0123456792', 250;
EXEC ThemKhachHang 'KH005', 'Nguyen Thi Lan', 'lan.nguyen@example.com', '0123456793', 300;
EXEC ThemKhachHang 'KH006', 'Bui Thanh Tuan', 'tuan.bui@example.com', '0123456794', 350;
EXEC ThemKhachHang 'KH007', 'Dang Thu Hang', 'hang.dang@example.com', '0123456795', 400;
EXEC ThemKhachHang 'KH008', 'Doan Minh Quan', 'quan.doan@example.com', '0123456796', 450;
EXEC ThemKhachHang 'KH009', 'Vu Tuan Kiet', 'kiet.vu@example.com', '0123456797', 500;
EXEC ThemKhachHang 'KH010', 'Ngo Bao Chau', 'chau.ngo@example.com', '0123456798', 550;
EXEC ThemKhachHang 'KH011', 'Truong Gia Bao', 'bao.truong@example.com', '0123456799', 600;
EXEC ThemKhachHang 'KH012', 'Pham Van Hau', 'hau.pham@example.com', '0123456800', 650;
EXEC ThemKhachHang 'KH013', 'Nguyen Thu Hien', 'hien.nguyen@example.com', '0123456801', 700;
EXEC ThemKhachHang 'KH014', 'Tran Van Tien', 'tien.tran@example.com', '0123456802', 750;
EXEC ThemKhachHang 'KH015', 'Le Minh Tri', 'tri.le@example.com', '0123456803', 800;
EXEC ThemKhachHang 'KH016', 'Hoang Thi Mai', 'mai.hoang@example.com', '0123456804', 850;
EXEC ThemKhachHang 'KH017', 'Vo Thi Hong', 'hong.vo@example.com', '0123456805', 900;
EXEC ThemKhachHang 'KH018', 'Dinh Van Phu', 'phu.dinh@example.com', '0123456806', 950;
EXEC ThemKhachHang 'KH019', 'Nguyen Bao An', 'an.nguyen@example.com', '0123456807', 1000;
EXEC ThemKhachHang 'KH020', 'Le Van Hieu', 'hieu.le@example.com', '0123456808', 1050;

USE CINEMA
-- Insert 20 employees using the ThemNV stored procedure
EXEC ThemNV 'NV001', 'Nguyen Van An', '123 Tran Phu, Ha Noi', '1985-03-15', '2020-01-05', 'an.nguyen@example.com', '0912345678', 'Manager', 'Morning';
EXEC ThemNV 'NV002', 'Tran Thi Binh', '456 Le Loi, HCMC', '1990-07-20', '2019-05-10', 'binh.tran@example.com', '0912345679', 'Accountant', 'Afternoon';
EXEC ThemNV 'NV003', 'Le Hoang Long', '789 Nguyen Trai, Da Nang', '1987-02-25', '2021-02-15', 'long.le@example.com', '0912345680', 'Sales', 'Night';
EXEC ThemNV 'NV004', 'Pham Van Dung', '321 Kim Ma, Ha Noi', '1992-11-18', '2022-03-10', 'dung.pham@example.com', '0912345681', 'Marketing', 'Morning';
EXEC ThemNV 'NV005', 'Nguyen Thi Lan', '654 Ba Trieu, HCMC', '1989-05-06', '2018-04-20', 'lan.nguyen@example.com', '0912345682', 'HR', 'Afternoon';
EXEC ThemNV 'NV006', 'Bui Thanh Tuan', '987 Hoang Hoa Tham, Ha Noi', '1991-09-22', '2021-07-01', 'tuan.bui@example.com', '0912345683', 'Developer', 'Night';
EXEC ThemNV 'NV007', 'Dang Thu Hang', '111 Phan Dinh Phung, Da Nang', '1993-01-12', '2020-09-15', 'hang.dang@example.com', '0912345684', 'Designer', 'Morning';
EXEC ThemNV 'NV008', 'Doan Minh Quan', '222 Nguyen Hue, HCMC', '1986-06-30', '2017-11-23', 'quan.doan@example.com', '0912345685', 'Sales', 'Afternoon';
EXEC ThemNV 'NV009', 'Vu Tuan Kiet', '333 Vo Thi Sau, Ha Noi', '1995-03-18', '2019-12-05', 'kiet.vu@example.com', '0912345686', 'Support', 'Night';
EXEC ThemNV 'NV010', 'Ngo Bao Chau', '444 Hang Bai, Ha Noi', '1990-10-28', '2021-08-15', 'chau.ngo@example.com', '0912345687', 'IT Support', 'Morning';
EXEC ThemNV 'NV011', 'Truong Gia Bao', '555 Hai Ba Trung, HCMC', '1988-04-10', '2018-03-01', 'bao.truong@example.com', '0912345688', 'Accountant', 'Afternoon';
EXEC ThemNV 'NV012', 'Pham Van Hau', '666 Pasteur, HCMC', '1994-02-19', '2020-10-22', 'hau.pham@example.com', '0912345689', 'Developer', 'Night';
EXEC ThemNV 'NV013', 'Nguyen Thu Hien', '777 Ly Thuong Kiet, Ha Noi', '1985-08-17', '2017-06-14', 'hien.nguyen@example.com', '0912345690', 'Marketing', 'Morning';
EXEC ThemNV 'NV014', 'Tran Van Tien', '888 Quang Trung, Da Nang', '1992-12-05', '2022-01-12', 'tien.tran@example.com', '0912345691', 'Designer', 'Afternoon';
EXEC ThemNV 'NV015', 'Le Minh Tri', '999 Dinh Tien Hoang, Ha Noi', '1989-09-09', '2021-05-01', 'tri.le@example.com', '0912345692', 'Manager', 'Night';
EXEC ThemNV 'NV016', 'Hoang Thi Mai', '100 Nguyen Thi Minh Khai, HCMC', '1991-06-03', '2019-08-15', 'mai.hoang@example.com', '0912345693', 'Support', 'Morning';
EXEC ThemNV 'NV017', 'Vo Thi Hong', '200 Pham Ngu Lao, Da Nang', '1987-04-20', '2020-02-28', 'hong.vo@example.com', '0912345694', 'HR', 'Afternoon';
EXEC ThemNV 'NV018', 'Dinh Van Phu', '300 Le Van Sy, HCMC', '1984-11-13', '2016-10-10', 'phu.dinh@example.com', '0912345695', 'Sales', 'Night';
EXEC ThemNV 'NV019', 'Nguyen Bao An', '400 Ly Thai To, Ha Noi', '1993-03-25', '2021-03-03', 'an.nguyen@example.com', '0912345696', 'IT Support', 'Morning';
EXEC ThemNV 'NV020', 'Le Van Hieu', '500 Bui Thi Xuan, Ha Noi', '1990-12-18', '2022-07-30', 'hieu.le@example.com', '0912345697', 'Developer', 'Afternoon';


USE CINEMA;
CREATE TABLE Ghe (
    maGhe VARCHAR(10) PRIMARY KEY,
    trangThai VARCHAR(50),
	maPhong VARCHAR(10),
	FOREIGN KEY (maPhong) REFERENCES PhongChieu(maPhong)
);
GO

USE CINEMA;
CREATE TABLE Ve (
    maVe VARCHAR(10) PRIMARY KEY,
    maSuatChieu VARCHAR(10),
	maGhe VARCHAR(10),
	giaVe FLOAT,
	FOREIGN KEY (maGhe) REFERENCES Ghe(maGhe),
	FOREIGN KEY (maSuatChieu) REFERENCES SuatChieu( maSuatChieu),
);
GO

USE CINEMA;
CREATE TABLE HoaDon (
    maHoaDon VARCHAR(10) PRIMARY KEY,
    maVe VARCHAR(10),
	maDoAnNhe NVARCHAR(50),
	soLuongDoAN INT,
	ngayLap DATE,
	FOREIGN KEY (maVe) REFERENCES Ve(maVe),
	FOREIGN KEY (maDoAnNhe) REFERENCES DoAnNhe(maDoAnNhe)
);
GO

USE CINEMA
CREATE TABLE MauKhieuNai (
    maKhieuNai VARCHAR(10) PRIMARY KEY,
    maNV NVARCHAR(50),
	maKH NVARCHAR(50),
	lyDoKhieuNai NVARCHAR(200),
	trangThai NVARCHAR(200),
	FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
	FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);
GO

USE CINEMA
INSERT INTO Phim (maPhim, ten, theLoai, daoDien, dienVienChinh, thoiLuong, ngayPhatHanh, gioiHanDoTuoi, moTaPhim, ngonNgu, dinhDang, nhaSX)
VALUES (N'BoPhimBiAn', N'Bộ Phim Bí Ẩn', N'Bí ẩn', N'Bí ẩn', N'Diễn Viên Chính', 98, '2024-11-08', 16, N'Bí ẩn', N'Tiếng Việt', '2D', N'Bí ẩn');
go
INSERT INTO Phim (maPhim, ten, theLoai, daoDien, dienVienChinh, thoiLuong, ngayPhatHanh, gioiHanDoTuoi, moTaPhim, ngonNgu, dinhDang, nhaSX)
VALUES (N'MatMaDo', N'MẬT MÃ ĐỎ', N'Hài, Hành Ðộng, Phiêu Lưu', N'Jake Kasdan', N'Dwayne Johnson; Chris Evans; Lucy Liu', 125, '2024-11-08', 13, N'Sau khi Ông già Noel (mật danh: Red One) bị bắt cóc, Trưởng An ninh Bắc Cực (Dwayne Johnson) phải hợp tác với thợ săn tiền thưởng khét tiếng nhất thế giới (Chris Evans) trong một nhiệm vụ kỳ tính xuyên lục địa để giải cứu Giáng Sinh.', N'Tiếng Anh - Phụ đề Tiếng Việt', '2D Phụ đề', N'Dwayne Johnson, Hiram Garcia, Dany Garcia, Jake Kasdan, Melvin Mar, Chris Morgan');
go
INSERT INTO Phim (maPhim, ten, theLoai, daoDien, dienVienChinh, thoiLuong, ngayPhatHanh, gioiHanDoTuoi, moTaPhim, ngonNgu, dinhDang, nhaSX)
VALUES (N'NgayXuaCoMotChuyenTinh', N'NGÀY XƯA CÓ MỘT CHUYỆN TÌNH', N'Tình cảm', N'Trịnh Ðình Lê Minh', N'Avin Lu, Ngọc Xuân, Đỗ Nhật Hoàng, Thanh Tú, Bảo Tiên, Hạo Khang', 135, '2024-10-08', 16, N'Ngày Xưa Có Một Chuyện Tình xoay quanh câu chuyện tình bạn, tình yêu giữa hai chàng trai và một cô gái từ thuở ấu thơ cho đến khi trưởng thành, phải đối mặt với những thử thách của số phận. Trải dài trong 4 giai đoạn từ năm 1987 - 2000, ba người bạn cùng tuổi - Vinh, Miến, Phúc đã cùng yêu, cùng bị ngã vào đời, va vấp và vượt qua.', N'Tiếng Việt - Phụ đề Tiếng Anh', '2D Phụ đề', N'Bí ẩnHKFilm, CJ HK Entertainment và SATE');
go
INSERT INTO Phim (maPhim, ten, theLoai, daoDien, dienVienChinh, thoiLuong, ngayPhatHanh, gioiHanDoTuoi, moTaPhim, ngonNgu, dinhDang, nhaSX)
VALUES (N'RobotHoangDa', N'ROBOT HOANG DÃ', N'Gia đình, Hoạt Hình, Khoa Học Viễn Tưởng, Phiêu Lưu', N'Chris Sanders', N'Lupita Nyong o, Pedro Pascal, Catherine O’hara, Bill Nighy,...', 102, '2024-11-10', 0, N'Cuộc phiêu lưu hoành tráng theo chân hành trình của một robot — đơn vị ROZZUM 7134, gọi tắt là Roz. Roz vô tình dạt vào hoang đảo sau một sự cố và nơi đây trở thành địa điểm sống mới của cô. Tại đây, Roz kết thân và nhận nuôi một chú ngỗng con, đặt tên là Brightbill. Roz và Brightbill dần dần thân thiết với các bạn thú trên đảo, song sau đó phải chống chọi, bảo vệ “nhà mới” trước sự xâm lăng của nhà máy từng sản xuất ra Roz.', N'Tiếng Anh - Phụ đề Tiếng Việt; Lồng tiếng', '2D', N'Jeff Hermann');
go
INSERT INTO Phim (maPhim, ten, theLoai, daoDien, dienVienChinh, thoiLuong, ngayPhatHanh, gioiHanDoTuoi, moTaPhim, ngonNgu, dinhDang, nhaSX)
VALUES (N'VuaSuTu', N'MUFASA: VUA SƯ TỬ', N' Gia đình, Phiêu Lưu', N'Barry Jenkins', N'Aaron Pierre, Kelvin Harrison Jr., Seth Rogen, Billy Eichner, John Kani', 120, '2024-12-20', 0, N'Sau Simba thì tới lượt Mufusa cũng lên live action Mufasa: Vua Sư Tử - phần tiền truyện về Mufasa - cha của Simba trong bộ phim Vua Sư Tử huyền thoại, dự kiến khởi chiếu tháng 12.2024', N'Tiếng Anh với phụ đề tiếng Việt và lồng tiếng Việt', '2D', N'Adele Romanski, Mark Ceryak');
go
INSERT INTO Phim (maPhim, ten, theLoai, daoDien, dienVienChinh, thoiLuong, ngayPhatHanh, gioiHanDoTuoi, moTaPhim, ngonNgu, dinhDang, nhaSX)
VALUES (N'Venom', N'VENOM: KÈO CUỐI', N'Hành Động, Khoa Học Viễn Tưởng, Phiêu Lưu, Thần thoại', N'Kelly Marcel', N'Tom Hardy, Juno Temple, Chiwetel Ejiofor, Clark Backo, Stephen Graham', 109, '2024-10-25', 13, N'Đây là phần phim cuối cùng và hoành tráng nhất về cặp đôi Venom và Eddie Brock (Tom Hardy). Sau khi dịch chuyển từ Vũ trụ Marvel trong ‘Spider-man: No way home’ (2021) trở về thực tại, Eddie Brock giờ đây cùng Venom sẽ phải đối mặt với ác thần Knull hùng mạnh - kẻ tạo ra cả chủng tộc Symbiote và những thế lực đang rình rập khác. Cặp đôi Eddie và Venom sẽ phải đưa ra lựa quyết định khốc liệt để hạ màn kèo cuối này.', N'Tiếng Anh - Phụ đề Tiếng Việt', '2D', N'Avi Arad, Matt Tolmach, Amy Pascal, Kelly Marcel, Tom Hardy, Hutch Parker');
go
USE CINEMA
-- Thêm phòng chiếu P1
EXEC ThemPhongChieu @maPhong = 'P1', @soGhe = 100, @trangThai = 'Còn trống';
GO
-- Thêm phòng chiếu P2
EXEC ThemPhongChieu @maPhong = 'P2', @soGhe = 150, @trangThai = 'Còn trống';
GO
-- Thêm phòng chiếu P3
EXEC ThemPhongChieu @maPhong = 'P3', @soGhe = 200, @trangThai = 'Còn trống';
GO
USE CINEMA
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC1', 'NgayXuaCoMotChuyenTinh', 'P1', '2D', 50000, '15:00:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC2', 'MatMaDo', 'P1', '2D', 50000, '18:00:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC3', 'Venom', 'P1', '2D', 50000, '20:00:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC4', 'Venom', 'P1', '2D', 50000, '23:00:00', '2024-11-06');
go


INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC5', 'MatMaDo', 'P2', '2D', 50000, '15:00:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC6', 'VuaSuTu', 'P2', '2D', 50000, '18:00:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC7', 'BoPhimBiAn', 'P2', '2D', 50000, '21:30:00', '2024-11-06');
go

INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC8', 'RobotHoangDa', 'P3', '2D', 50000, '15:00:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC9', 'NgayXuaCoMotChuyenTinh', 'P3', '2D', 50000, '17:30:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC10', 'BoPhimBiAn', 'P1', '2D', 50000, '20:00:00', '2024-11-06');
go
INSERT INTO SuatChieu (maSuatChieu, maPhim, maPhong, dinhDang, giaVe, gioChieu, ngayChieu)
VALUES ('SC11', 'NgayXuaCoMotChuyenTinh', 'P3', '2D', 50000, '22:30:00', '2024-11-06');
go