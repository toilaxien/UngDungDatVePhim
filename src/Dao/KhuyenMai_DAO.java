package Dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import lop.KhuyenMai;

public class KhuyenMai_DAO {
    private Connection connection;

    public KhuyenMai_DAO() {
        this.connection = ConnectDB.getInstance().getConnection();
    }

    public void themKhuyenMai(KhuyenMai khuyenMai) throws SQLException {
        String sql = "EXEC ThemKM ?, ?, ?, ?, ?";  
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, khuyenMai.getMaKM());
            statement.setDate(2, java.sql.Date.valueOf(khuyenMai.getNgayBatDau()));
            statement.setDate(3, java.sql.Date.valueOf(khuyenMai.getNgayKetThuc()));
            statement.setFloat(4, khuyenMai.getGiamGia());
            statement.setString(5, khuyenMai.getMoTa());
            statement.executeUpdate();
        }
    }

    // Update an existing promotion
    public void capNhatKhuyenMai(KhuyenMai khuyenMai) throws SQLException {
        String sql = "EXEC SuaKM ?, ?, ?, ?, ?";  

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, khuyenMai.getMaKM());
            statement.setDate(2, java.sql.Date.valueOf(khuyenMai.getNgayBatDau()));
            statement.setDate(3, java.sql.Date.valueOf(khuyenMai.getNgayKetThuc()));
            statement.setFloat(4, khuyenMai.getGiamGia());
            statement.setString(5, khuyenMai.getMoTa());
            statement.executeUpdate();
        }
    }

    // Delete a promotion
    public void xoaKhuyenMai(String maKM) throws SQLException {
        String sql = "EXEC XoaKM ?";  

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maKM);
            statement.executeUpdate();
        }
    }

    // Find a promotion by ID
    public KhuyenMai timKhuyenMai(String maKM) throws SQLException {
        String sql = "EXEC TimKM ?";  
        KhuyenMai khuyenMai = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maKM);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    khuyenMai = new KhuyenMai();
                    khuyenMai.setMaKM(resultSet.getString("maKM"));
                    khuyenMai.setNgayBatDau(resultSet.getDate("ngayBatDau").toLocalDate());
                    khuyenMai.setNgayKetThuc(resultSet.getDate("ngayKetThuc").toLocalDate());
                    khuyenMai.setGiamGia(resultSet.getFloat("giamGia"));
                    khuyenMai.setMoTa(resultSet.getString("moTa"));
                }
            }
        }
        return khuyenMai;
    }

    // Get list of all promotions
    public List<KhuyenMai> layDanhSachKhuyenMai() throws SQLException {
        String sql = "EXEC LayDanhSachKhuyenMai";  
        List<KhuyenMai> danhSachKhuyenMai = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKM(resultSet.getString("maKM"));
                khuyenMai.setNgayBatDau(resultSet.getDate("ngayBatDau").toLocalDate());
                khuyenMai.setNgayKetThuc(resultSet.getDate("ngayKetThuc").toLocalDate());
                khuyenMai.setGiamGia(resultSet.getFloat("giamGia"));
                khuyenMai.setMoTa(resultSet.getString("moTa"));
                danhSachKhuyenMai.add(khuyenMai);
            }
        }
        return danhSachKhuyenMai;
    }
}
