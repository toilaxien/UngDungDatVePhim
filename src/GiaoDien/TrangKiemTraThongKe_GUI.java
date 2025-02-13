package GiaoDien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TrangKiemTraThongKe_GUI extends JFrame {
    public TrangKiemTraThongKe_GUI() {
        setTitle("Thống Kê Doanh Thu");
        Dimension kichThuocManHinh = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(kichThuocManHinh.width, 678);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thống Kê Doanh Thu", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Bảng thông tin sản phẩm và vé
        JPanel tablePanel = createProductAndTicketTablePanel();
        panel.add(tablePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createProductAndTicketTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1)); // Sử dụng GridLayout để chia thành 2 phần

        // Thêm bảng sản phẩm
        panel.add(createProductTablePanel());

        // Thêm bảng vé
        panel.add(createTicketTablePanel());

        return panel;
    }

    private JPanel createProductTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề bảng sản phẩm
        String[] productColumnNames = {"Mã SP", "Ngày Bán", "Giờ Bán", "Đơn Giá", "Số Lượng", "Nhân Viên"};

        // Lấy ngày và giờ hiện tại
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Định dạng ngày và giờ
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Tạo bảng với dữ liệu sản phẩm
        Object[][] productData = {
            {"B001", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "25,000 VNĐ", "2", "Nguyễn Văn A"},
            {"N002", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "15,000 VNĐ", "3", "Trần Thị B"},
            {"B003", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "30,000 VNĐ", "4", "Lê Văn C"},
            {"B004", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "20,000 VNĐ", "1", "Nguyễn Thị D"},
            {"B005", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "10,000 VNĐ", "5", "Trần Văn E"},
            {"B006", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "5,000 VNĐ", "10", "Phạm Thị F"}
        };

        JTable productTable = new JTable(productData, productColumnNames);
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setPreferredSize(new Dimension(600, 150)); // Kích thước ưa thích cho bảng
        tablePanel.add(productScrollPane, BorderLayout.CENTER);

        // Border cho bảng sản phẩm
        TitledBorder productBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Thông Tin Sản Phẩm Bán Được");
        productBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
        productScrollPane.setBorder(productBorder);

        // Tổng cộng số lượng và doanh thu sản phẩm
        int totalQuantity = 0;
        int totalRevenue = 0;

        for (Object[] row : productData) {
            totalQuantity += Integer.parseInt(row[4].toString());
            totalRevenue += Integer.parseInt(row[3].toString().replace(",", "").replace(" VNĐ", ""));
        }

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(1, 2));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Tổng Kết Bán Bắp và Nước"));
        summaryPanel.add(new JLabel("Tổng Số Lượng: " + totalQuantity));
        summaryPanel.add(new JLabel("Tổng Doanh Thu: " + String.format("%,d VNĐ", totalRevenue)));

        // Thêm phần tổng kết vào panel chính
        tablePanel.add(summaryPanel, BorderLayout.SOUTH);

        return tablePanel;
    }

    private JPanel createTicketTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề bảng vé
        String[] ticketColumnNames = {"Mã Vé", "Ngày Bán", "Giờ Bán", "Đơn Giá", "Số Lượng", "Nhân Viên"};

        // Lấy ngày và giờ hiện tại
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Định dạng ngày và giờ
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Tạo bảng với dữ liệu vé
        Object[][] ticketData = {
            {"V001", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "50,000 VNĐ", "1", "Nguyễn Văn A"},
            {"V002", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "50,000 VNĐ", "2", "Trần Thị B"},
            {"V003", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "60,000 VNĐ", "3", "Lê Văn C"},
            {"V004", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "55,000 VNĐ", "1", "Nguyễn Thị D"},
            {"V005", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "45,000 VNĐ", "4", "Trần Văn E"},
            {"V006", currentDate.format(dateFormatter), currentTime.format(timeFormatter), "40,000 VNĐ", "2", "Phạm Thị F"}
        };

        JTable ticketTable = new JTable(ticketData, ticketColumnNames);
        ticketTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane ticketScrollPane = new JScrollPane(ticketTable);
        ticketScrollPane.setPreferredSize(new Dimension(600, 150)); 
        tablePanel.add(ticketScrollPane, BorderLayout.CENTER);

        // Border cho bảng vé
        TitledBorder ticketBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Thông Tin Vé Bán Được");
        ticketBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
        ticketScrollPane.setBorder(ticketBorder);

        // Tổng cộng số lượng và doanh thu vé
        int totalQuantity = 0;
        int totalRevenue = 0;

        for (Object[] row : ticketData) {
            totalQuantity += Integer.parseInt(row[4].toString());
            totalRevenue += Integer.parseInt(row[3].toString().replace(",", "").replace(" VNĐ", ""));
        }

        JPanel ticketSummaryPanel = new JPanel();
        ticketSummaryPanel.setLayout(new GridLayout(1, 2));
        ticketSummaryPanel.setBorder(BorderFactory.createTitledBorder("Tổng Kết Bán Vé"));
        ticketSummaryPanel.add(new JLabel("Tổng Số Lượng: " + totalQuantity));
        ticketSummaryPanel.add(new JLabel("Tổng Doanh Thu: " + String.format("%,d VNĐ", totalRevenue)));

        // Thêm phần tổng kết vào panel chính
        tablePanel.add(ticketSummaryPanel, BorderLayout.SOUTH);

        return tablePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrangKiemTraThongKe_GUI::new);
    }
}
