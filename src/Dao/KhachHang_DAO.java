package Dao;

import java.sql.*;
import java.util.ArrayList;
import connectDB.ConnectDB;
import lop.KhachHang;

public class KhachHang_DAO {
    private Connection connection;

    public KhachHang_DAO() {
        this.connection = ConnectDB.getInstance().getConnection();
    }

    // Add customer
    public void themKhachHang(KhachHang khachHang) throws SQLException {
        String sql = "EXEC ThemKhachHang ?, ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, khachHang.getMaKH());
            statement.setString(2, khachHang.getHoTen());
            statement.setString(3, khachHang.getEmail());
            statement.setString(4, khachHang.getSdt());
            statement.setInt(5, khachHang.getDiemTichLuy());
            statement.executeUpdate();
        }
    }

    // Update customer information
    public void capNhatKhachHang(KhachHang khachHang) throws SQLException {
        String sql = "EXEC CapNhatKhachHang ?, ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, khachHang.getMaKH());
            statement.setString(2, khachHang.getHoTen());
            statement.setString(3, khachHang.getEmail());
            statement.setString(4, khachHang.getSdt());
            statement.setInt(5, khachHang.getDiemTichLuy());
            statement.executeUpdate();
        }
    }

    // Delete customer
    public void xoaKhachHang(String maKH) throws SQLException {
        String sql = "EXEC XoaKhachHang ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maKH);
            statement.executeUpdate();
        }
    }

    // Find customer by ID
    public KhachHang timKhachHang(String maKH) throws SQLException {
        String sql = "EXEC TimKhachHang ?";
        KhachHang khachHang = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maKH);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    khachHang = new KhachHang();
                    khachHang.setMaKH(resultSet.getString("maKH"));
                    khachHang.setHoTen(resultSet.getString("hoTen"));
                    khachHang.setEmail(resultSet.getString("email"));
                    khachHang.setSdt(resultSet.getString("sdt"));
                    khachHang.setDiemTichLuy(resultSet.getInt("diemTichLuy"));
                }
            }
        }
        return khachHang;
    }

    // Get list of customers
    public ArrayList<KhachHang> layDanhSachKhachHang() throws SQLException {
        String sql = "EXEC LayDanhSachKhachHang"; // Call the stored procedure
        ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(resultSet.getString("maKH"));
                khachHang.setHoTen(resultSet.getString("hoTen"));
                khachHang.setEmail(resultSet.getString("email"));
                khachHang.setSdt(resultSet.getString("sdt"));
                khachHang.setDiemTichLuy(resultSet.getInt("diemTichLuy"));
                danhSachKhachHang.add(khachHang);
            }
        }
        return danhSachKhachHang;
    }
}
