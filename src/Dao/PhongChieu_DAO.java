package Dao;

import java.sql.*;
import java.util.ArrayList;
import connectDB.ConnectDB;
import lop.PhongChieu;

public class PhongChieu_DAO {
    private Connection connection;

    public PhongChieu_DAO() {
        this.connection = ConnectDB.getInstance().getConnection();
    }

    // Add a new cinema room
    public void themPhongChieu(PhongChieu phongChieu) throws SQLException {
        String sql = "EXEC ThemPhongChieu ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phongChieu.getMaPhong());
            statement.setInt(2, phongChieu.getSoGhe());
            statement.setString(3, phongChieu.getTrangThai());
            statement.executeUpdate();
        }
    }

    // Update cinema room information
    public void capNhatPhongChieu(PhongChieu phongChieu) throws SQLException {
        String sql = "EXEC CapNhatPhongChieu ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phongChieu.getMaPhong());
            statement.setInt(2, phongChieu.getSoGhe());
            statement.setString(3, phongChieu.getTrangThai());
            statement.executeUpdate();
        }
    }

    // Delete a cinema room by its ID
    public void xoaPhongChieu(String maPhong) throws SQLException {
        String sql = "EXEC XoaPhongChieu ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maPhong);
            statement.executeUpdate();
        }
    }

    // Find a cinema room by ID
    public PhongChieu timPhongChieu(String maPhong) throws SQLException {
        String sql = "EXEC TimPhongChieu ?";
        PhongChieu phongChieu = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maPhong);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    phongChieu = new PhongChieu();
                    phongChieu.setMaPhong(resultSet.getString("maPhong"));
                    phongChieu.setSoGhe(resultSet.getInt("soGhe"));
                    phongChieu.setTrangThai(resultSet.getString("trangThai"));
                }
            }
        }
        return phongChieu;
    }

    // Get list of all cinema rooms
    public ArrayList<PhongChieu> layDanhSachPhongChieu() throws SQLException {
        String sql = "EXEC LayDanhSachPhongChieu";
        ArrayList<PhongChieu> danhSachPhongChieu = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                PhongChieu phongChieu = new PhongChieu();
                phongChieu.setMaPhong(resultSet.getString("maPhong"));
                phongChieu.setSoGhe(resultSet.getInt("soGhe"));
                phongChieu.setTrangThai(resultSet.getString("trangThai"));
                danhSachPhongChieu.add(phongChieu);
            }
        }
        return danhSachPhongChieu;
    }
}
