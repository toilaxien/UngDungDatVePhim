package Dao;

import java.sql.*;
import java.util.ArrayList;
import connectDB.ConnectDB;
import lop.DoAnNhe;

public class ThucPham_DAO {
    private Connection connection;

    public ThucPham_DAO() {
        this.connection = ConnectDB.getInstance().getConnection();
    }

    // Thêm món ăn nhẹ
    public void themDoAnNhe(DoAnNhe doAnNhe) throws SQLException {
        String sql = "EXEC ThemDoAnNhe ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, doAnNhe.getMaDoAnNhe());
            statement.setString(2, doAnNhe.getTenDoAnNhe());
            statement.setFloat(3, doAnNhe.getGia());
            statement.setInt(4, doAnNhe.getSoLuongCon());
            statement.executeUpdate();
        }
    }

    // Cập nhật thông tin món ăn nhẹ
    public void capNhatDoAnNhe(DoAnNhe doAnNhe) throws SQLException {
        String sql = "EXEC CapNhatDoAnNhe ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, doAnNhe.getMaDoAnNhe());
            statement.setString(2, doAnNhe.getTenDoAnNhe());
            statement.setFloat(3, doAnNhe.getGia());
            statement.setInt(4, doAnNhe.getSoLuongCon());
            statement.executeUpdate();
        }
    }

    // Xóa món ăn nhẹ theo mã
    public void xoaDoAnNhe(String maDoAnNhe) throws SQLException {
        String sql = "EXEC XoaDoAnNhe ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maDoAnNhe);
            statement.executeUpdate();
        }
    }

    // Tìm món ăn nhẹ theo mã
    public DoAnNhe timDoAnNhe(String maDoAnNhe) throws SQLException {
        String sql = "EXEC TimDoAnNhe ?";
        DoAnNhe doAnNhe = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maDoAnNhe);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    doAnNhe = new DoAnNhe();
                    doAnNhe.setMaDoAnNhe(resultSet.getString("maDoAnNhe"));
                    doAnNhe.setTenDoAnNhe(resultSet.getString("tenDoAnNhe"));
                    doAnNhe.setGia(resultSet.getFloat("gia"));
                    doAnNhe.setSoLuongCon(resultSet.getInt("soLuongCon"));
                }
            }
        }
        return doAnNhe;
    }

    // Lấy danh sách tất cả món ăn nhẹ
    public ArrayList<DoAnNhe> layDanhSachDoAnNhe() throws SQLException {
        String sql = "EXEC LayDanhSachDoAnNhe";
        ArrayList<DoAnNhe> danhSachDoAnNhe = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                DoAnNhe doAnNhe = new DoAnNhe();
                doAnNhe.setMaDoAnNhe(resultSet.getString("maDoAnNhe"));
                doAnNhe.setTenDoAnNhe(resultSet.getString("tenDoAnNhe"));
                doAnNhe.setGia(resultSet.getFloat("gia"));
                doAnNhe.setSoLuongCon(resultSet.getInt("soLuongCon"));
                
                danhSachDoAnNhe.add(doAnNhe);
            }
        }
        return danhSachDoAnNhe;
    }
}
