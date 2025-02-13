package Dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import connectDB.ConnectDB;
import lop.Phim;
import lop.PhongChieu;
import lop.SuatChieu;

public class SuatChieu_DAO {
    private Connection connection;

    public SuatChieu_DAO() {
        this.connection = ConnectDB.getInstance().getConnection();
    }

    // Add a new showtime
//    public void themSuatChieu(SuatChieu suatChieu) throws SQLException {
//        String sql = "EXEC ThemSuatChieu ?, ?, ?, ?, ?, ?, ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, suatChieu.getMaSuatChieu());
//            statement.setString(2, suatChieu.getPhim().getMaPhim());  // Assuming getMaPhim() exists in Phim
//            statement.setString(3, suatChieu.getPhongChieu().getMaPhong());
//            statement.setString(4, suatChieu.getDinhDang());
//            statement.setDouble(5, suatChieu.getGiaVe());           
//            statement.setDate(6, Date.valueOf(suatChieu.getngayChieu()));
//            statement.setTime(7, Time.valueOf(suatChieu.getGioChieu()));
//            statement.executeUpdate();
//        }
//    }
    public void themSuatChieu(SuatChieu suatChieu) throws SQLException {
        // Kiểm tra nếu maPhim đã tồn tại trong bảng Phim
        String checkPhimSql = "SELECT COUNT(*) FROM Phim WHERE maPhim = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkPhimSql)) {
            checkStatement.setString(1, suatChieu.getPhim().getMaPhim());
            ResultSet rs = checkStatement.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                throw new SQLException("Phim với maPhim " + suatChieu.getPhim().getMaPhim() + " không tồn tại.");
            }
        }

        // Nếu maPhim tồn tại, tiếp tục thực hiện chèn vào SuatChieu
        String sql = "EXEC ThemSuatChieu ?, ?, ?, ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, suatChieu.getMaSuatChieu());
            statement.setString(2, suatChieu.getPhim().getMaPhim());
            statement.setString(3, suatChieu.getPhongChieu().getMaPhong());
            statement.setString(4, suatChieu.getDinhDang());
            statement.setDouble(5, suatChieu.getGiaVe());
            statement.setDate(6, Date.valueOf(suatChieu.getngayChieu()));
            statement.setTime(7, Time.valueOf(suatChieu.getGioChieu()));
            statement.executeUpdate();
        }
    }

    // Update showtime information
    public void capNhatSuatChieu(SuatChieu suatChieu) throws SQLException {
        String sql = "EXEC CapNhatSuatChieu ?, ?, ?, ?, ?, ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, suatChieu.getMaSuatChieu());
            statement.setString(2, suatChieu.getPhim().getMaPhim());
            statement.setString(3, suatChieu.getPhongChieu().getMaPhong());
            statement.setString(4, suatChieu.getDinhDang());
            statement.setDouble(5, suatChieu.getGiaVe());            
            statement.setDate(6, Date.valueOf(suatChieu.getngayChieu()));
            statement.setTime(7, Time.valueOf(suatChieu.getGioChieu()));
            statement.executeUpdate();
        }
    }

    // Delete a showtime by its ID
    public void xoaSuatChieu(String maSuatChieu) throws SQLException {
        String sql = "EXEC XoaSuatChieu ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maSuatChieu);
            statement.executeUpdate();
        }
    }

    // Find a showtime by ID
    public SuatChieu timSuatChieu(String maSuatChieu) throws SQLException {
        String sql = "EXEC TimSuatChieu ?";
        SuatChieu suatChieu = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maSuatChieu);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    suatChieu = new SuatChieu();
                    suatChieu.setMaSuatChieu(resultSet.getString("maSuatChieu"));
                    Phim phim = new Phim();
                    phim.setMaPhim(resultSet.getString("maPhim"));  // Assuming Phim class has setMaPhim()
                    suatChieu.setPhim(phim);
                    
                    PhongChieu phongChieu = new PhongChieu();
                    phongChieu.setMaPhong(resultSet.getString("maPhong"));
                    suatChieu.setPhongChieu(phongChieu);
                    suatChieu.setDinhDang(resultSet.getString("dinhDang"));
                    suatChieu.setGiaVe(resultSet.getDouble("giaVe"));
                    suatChieu.setngayChieu((resultSet.getDate("ngayChieu").toLocalDate()));
                    suatChieu.setGioChieu((resultSet.getTime("gioChieu").toLocalTime()));
                }
            }
        }
        return suatChieu;
    }

    // Get list of all showtimes
    public ArrayList<SuatChieu> layDanhSachSuatChieu() throws SQLException {
        String sql = "EXEC LayDanhSachSuatChieu";
        ArrayList<SuatChieu> danhSachSuatChieu = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                SuatChieu suatChieu = new SuatChieu();
                suatChieu.setMaSuatChieu(resultSet.getString("maSuatChieu"));
                
                Phim phim = new Phim();
                phim.setMaPhim(resultSet.getString("maPhim"));
                suatChieu.setPhim(phim);

                PhongChieu phongChieu = new PhongChieu();
                phongChieu.setMaPhong(resultSet.getString("maPhong"));
                suatChieu.setPhongChieu(phongChieu);
                suatChieu.setDinhDang(resultSet.getString("dinhDang"));
                suatChieu.setGiaVe(resultSet.getDouble("giaVe"));                
                suatChieu.setngayChieu((resultSet.getDate("ngayChieu").toLocalDate()));
                suatChieu.setGioChieu((resultSet.getTime("gioChieu").toLocalTime()));
                danhSachSuatChieu.add(suatChieu);
            }
        }
        return danhSachSuatChieu;
    }
}
