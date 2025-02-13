package Dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import lop.NhanVien;

public class NhanVien_DAO {
    private Connection connection;

    public NhanVien_DAO() {
       this.connection = ConnectDB.getInstance().getConnection();
    }

    // Date format for parsing input dates
    private static final String INPUT_DATE_FORMAT = "yyyy-MM-dd"; // Input format for dates
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat(INPUT_DATE_FORMAT);

    // Add employee
    public void themNhanVien(NhanVien nhanVien) throws SQLException {
        String sql = "EXEC ThemNV ?, ?, ?, ?, ?, ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nhanVien.getMaNV());
            statement.setString(2, nhanVien.getHoTen());
            statement.setString(3, nhanVien.getDiaChi());
            statement.setDate(4, convertStringToSqlDate(nhanVien.getNgaySinh())); // Convert ngaySinh
            statement.setDate(5, convertStringToSqlDate(nhanVien.getNgayVaoLam())); // Convert ngayVaoLam
            statement.setString(6, nhanVien.getEmail());
            statement.setString(7, nhanVien.getSdt());
            statement.setString(8, nhanVien.getCongViec());
            statement.setString(9, nhanVien.getCaLamViec());
            statement.executeUpdate();
        }
    }

    // Update employee information
    public void capNhatNhanVien(NhanVien nhanVien) throws SQLException {
        String sql = "EXEC SuaNV ?, ?, ?, ?, ?, ?, ?, ?, ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nhanVien.getMaNV());
            statement.setString(2, nhanVien.getHoTen());
            statement.setString(3, nhanVien.getDiaChi());
            statement.setDate(4, convertStringToSqlDate(nhanVien.getNgaySinh())); // Convert ngaySinh
            statement.setDate(5, convertStringToSqlDate(nhanVien.getNgayVaoLam())); // Convert ngayVaoLam
            statement.setString(6, nhanVien.getEmail());
            statement.setString(7, nhanVien.getSdt());
            statement.setString(8, nhanVien.getCongViec());
            statement.setString(9, nhanVien.getCaLamViec());
            statement.executeUpdate();
        }
    }

    // Method to convert String to java.sql.Date
    private java.sql.Date convertStringToSqlDate(String dateString) throws SQLException {
        try {
            Date parsedDate = inputDateFormat.parse(dateString);
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            throw new SQLException("Invalid date format: " + dateString, e);
        }
    }

    // Delete employee
    public void xoaNhanVien(String maNV) throws SQLException {
        String sql = "EXEC XoaNV ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maNV);
            statement.executeUpdate();
        }
    }

    // Find employee by ID
    public NhanVien timNhanVien(String maNV) throws SQLException {
        String sql = "EXEC TimNV ?";
        NhanVien nhanVien = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maNV);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nhanVien = new NhanVien();
                    nhanVien.setMaNV(resultSet.getString("maNV"));
                    nhanVien.setHoTen(resultSet.getString("hoTen"));
                    nhanVien.setDiaChi(resultSet.getString("diaChi"));
                    nhanVien.setNgaySinh(resultSet.getString("ngaySinh")); 
                    nhanVien.setNgayVaoLam(resultSet.getString("ngayVaoLam"));
                    nhanVien.setEmail(resultSet.getString("email"));
                    nhanVien.setSdt(resultSet.getString("sdt"));
                    nhanVien.setCongViec(resultSet.getString("congViec"));
                    nhanVien.setCaLamViec(resultSet.getString("caLamViec"));
                }
            }
        }
        return nhanVien;
    }

    // Get list of employees
    public ArrayList<NhanVien> layDanhSachNhanVien() throws SQLException {
        String sql = "EXEC LayDanhSachNhanVien"; // Call the stored procedure
        ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(resultSet.getString("maNV"));
                nhanVien.setHoTen(resultSet.getString("hoTen"));
                nhanVien.setDiaChi(resultSet.getString("diaChi"));
                nhanVien.setNgaySinh(resultSet.getString("ngaySinh")); // Consider parsing if needed
                nhanVien.setNgayVaoLam(resultSet.getString("ngayVaoLam")); // Consider parsing if needed
                nhanVien.setEmail(resultSet.getString("email"));
                nhanVien.setSdt(resultSet.getString("sdt"));
                nhanVien.setCongViec(resultSet.getString("congViec"));
                nhanVien.setCaLamViec(resultSet.getString("caLamViec"));
                danhSachNhanVien.add(nhanVien);
            }
        }
        return danhSachNhanVien;
    }
}
