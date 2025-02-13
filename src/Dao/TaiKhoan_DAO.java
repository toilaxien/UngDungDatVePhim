package Dao;

import java.sql.*;
import java.util.ArrayList;
import connectDB.ConnectDB;
import lop.TaiKhoan;

public class TaiKhoan_DAO {
    private Connection connection;

    public TaiKhoan_DAO() {
        this.connection = ConnectDB.getInstance().getConnection();
    }

    public void themTaiKhoan(TaiKhoan taiKhoan) throws SQLException {
        String sql = "EXEC ThemTaiKhoan ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taiKhoan.getTenDangNhap());
            statement.setString(2, taiKhoan.getMatKhau());
            statement.setString(3, taiKhoan.getMaNV());
            statement.executeUpdate();
        }
    }

    public void capNhatTaiKhoan(TaiKhoan taiKhoan) throws SQLException {
        String sql = "EXEC CapNhatTaiKhoan ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taiKhoan.getTenDangNhap());
            statement.setString(2, taiKhoan.getMatKhau());
            statement.setString(3, taiKhoan.getMaNV());
            statement.executeUpdate();
        }
    }

    public void xoaTaiKhoan(String tenDangNhap) throws SQLException {
        String sql = "EXEC XoaTaiKhoan ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tenDangNhap);
            statement.executeUpdate();
        }
    }

    public TaiKhoan timTaiKhoan(String tenDangNhap) throws SQLException {
        String sql = "EXEC TimTaiKhoan ?";
        TaiKhoan taiKhoan = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tenDangNhap);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    taiKhoan = new TaiKhoan();
                    taiKhoan.setTenDangNhap(resultSet.getString("tenDangNhap"));
                    taiKhoan.setMatKhau(resultSet.getString("matKhau"));
                    taiKhoan.setMaNV(resultSet.getString("maNV"));
                }
            }
        }
        return taiKhoan;
    }

    public ArrayList<TaiKhoan> layDanhSachTaiKhoan() throws SQLException {
        String sql = "EXEC LayDanhSachTaiKhoan";
        ArrayList<TaiKhoan> danhSachTaiKhoan = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenDangNhap(resultSet.getString("tenDangNhap"));
                taiKhoan.setMatKhau(resultSet.getString("matKhau"));
                taiKhoan.setMaNV(resultSet.getString("maNV"));
                danhSachTaiKhoan.add(taiKhoan);
            }
        }
        return danhSachTaiKhoan;
    }
    public boolean kiemTraMaNV(String maNV) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TaiKhoan WHERE MaNV = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maNV);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
        }
        return false; 
    }

}
