package Dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import lop.Phim;

public class Phim_DAO {
    private Connection connection;

    public Phim_DAO() {
        this.connection = ConnectDB.getInstance().getConnection();
    }

    // Add a new movie
    public void themPhim(Phim phim) throws SQLException {
        String sql = "EXEC ThemPhim ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phim.getMaPhim());
            statement.setString(2, phim.getTen());
            statement.setString(3, phim.getTheLoai());
            statement.setString(4, phim.getDaoDien());
            statement.setString(5, phim.getDienVienChinh());
            statement.setInt(6, phim.getThoiLuong()); 
            statement.setDate(7, Date.valueOf(phim.getNgayPhatHanh())); 
            statement.setInt(8, phim.getGioiHanDoTuoi());
            statement.setString(9, phim.getMoTaPhim()); 
            statement.setString(10, phim.getNgonNgu());
            statement.setString(11, phim.getDinhDang());
            statement.setString(12, phim.getNhaSX());
            statement.executeUpdate();
        }
    }

    // Update movie information
    public void capNhatPhim(Phim phim) throws SQLException {
        String sql = "EXEC SuaPhim ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phim.getMaPhim());
            statement.setString(2, phim.getTen());
            statement.setString(3, phim.getTheLoai());
            statement.setString(4, phim.getDaoDien());
            statement.setString(5, phim.getDienVienChinh());
            statement.setInt(6, phim.getThoiLuong()); 
            statement.setDate(7, Date.valueOf(phim.getNgayPhatHanh())); 
            statement.setInt(8, phim.getGioiHanDoTuoi());
            statement.setString(9, phim.getMoTaPhim()); 
            statement.setString(10, phim.getNgonNgu());
            statement.setString(11, phim.getDinhDang());
            statement.setString(12, phim.getNhaSX());
            statement.executeUpdate();
        }
    }

    // Delete a movie
    public void xoaPhim(String maPhim) throws SQLException {
        String sql = "EXEC XoaPhim ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maPhim);
            statement.executeUpdate();
        }
    }

    // Find movie by ID
    public Phim timPhim(String maPhim) throws SQLException {
        String sql = "EXEC TimPhim ?";
        Phim phim = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maPhim);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    phim = new Phim();
                    phim.setMaPhim(resultSet.getString("maPhim"));
                    phim.setTen(resultSet.getString("ten"));
                    phim.setTheLoai(resultSet.getString("theLoai"));
                    phim.setDaoDien(resultSet.getString("daoDien"));
                    phim.setDienVienChinh(resultSet.getString("dienVienChinh"));
                    phim.setThoiLuong(resultSet.getInt("thoiLuong")); 
                    phim.setNgayPhatHanh(resultSet.getDate("ngayPhatHanh").toLocalDate()); 
                    phim.setGioiHanDoTuoi(resultSet.getInt("gioiHanDoTuoi"));
                    phim.setMoTaPhim(resultSet.getString("moTaPhim"));
                    phim.setNgonNgu(resultSet.getString("ngonNgu"));
                    phim.setDinhDang(resultSet.getString("dinhDang"));
                    phim.setNhaSX(resultSet.getString("nhaSX"));
                }
            }
        }
        return phim;
    }

    // Get list of movies
    public ArrayList<Phim> layDanhSachPhim() throws SQLException {
        String sql = "EXEC LayDanhSachPhim"; 
        ArrayList<Phim> danhSachPhim = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Phim phim = new Phim();
                phim.setMaPhim(resultSet.getString("maPhim"));
                phim.setTen(resultSet.getString("ten"));
                phim.setTheLoai(resultSet.getString("theLoai"));
                phim.setDaoDien(resultSet.getString("daoDien"));
                phim.setDienVienChinh(resultSet.getString("dienVienChinh"));
                phim.setThoiLuong(resultSet.getInt("thoiLuong")); 
                phim.setNgayPhatHanh(resultSet.getDate("ngayPhatHanh").toLocalDate()); 
                phim.setGioiHanDoTuoi(resultSet.getInt("gioiHanDoTuoi"));
                phim.setMoTaPhim(resultSet.getString("moTaPhim")); 
                phim.setNgonNgu(resultSet.getString("ngonNgu"));
                phim.setDinhDang(resultSet.getString("dinhDang"));
                phim.setNhaSX(resultSet.getString("nhaSX"));
                danhSachPhim.add(phim);
            }
        }
        return danhSachPhim;
    }
}
