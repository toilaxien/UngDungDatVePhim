package GiaoDien;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class TrangChu_GUI extends JFrame {

    public TrangChu_GUI() {
        setTitle("Trang Thống Kê");
        Dimension kichThuocManHinh = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(kichThuocManHinh.width, 678);

        JPanel panelTrai = taoPanelBenTrai();
        JLabel tieuDe = new JLabel("Quản Lý Bán Hàng", JLabel.CENTER);
        tieuDe.setFont(new Font("Arial", Font.BOLD, 30));
        tieuDe.setForeground(new Color(255, 223, 0));
        tieuDe.setPreferredSize(new Dimension(getWidth(), 60));
        add(tieuDe, BorderLayout.NORTH);

        JPanel panelChucNang = taoPanelChucNang();

        setLayout(new BorderLayout());
        add(panelTrai, BorderLayout.WEST);
        add(panelChucNang, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // Tạo panel bên trái chứa thông tin nhân viên và thời gian
    private JPanel taoPanelBenTrai() {
        JPanel panelTrai = new JPanel();
        panelTrai.setLayout(new BoxLayout(panelTrai, BoxLayout.Y_AXIS));
        panelTrai.setBackground(new Color(255, 100, 0));
        panelTrai.setBorder(new MatteBorder(2, 0, 2, 2, Color.BLACK));
        panelTrai.add(Box.createVerticalStrut(20));

        JLabel lblThongTin = new JLabel("Thông Tin Nhân Viên");
        lblThongTin.setForeground(Color.white);
        lblThongTin.setFont(new Font("Times New Roman", Font.BOLD, 28));
        lblThongTin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTrai.add(lblThongTin);
        panelTrai.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel pAnhVaThongTin = taoPanelAnhVaThongTin();
        pAnhVaThongTin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTrai.add(pAnhVaThongTin);

        JPanel pDateTime = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pDateTime.setBackground(new Color(255, 100, 0));

        ImageIcon iconDate = new ImageIcon("Anh\\date.png");
        Image imgDate = iconDate.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        JLabel lblDateIcon = new JLabel(new ImageIcon(imgDate));

        JLabel lblDate = new JLabel();
        lblDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDate.setForeground(Color.white);
        capNhatNgay(lblDate);

        ImageIcon iconTime = new ImageIcon("Anh\\ho.png");
        Image imgTime = iconTime.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        JLabel lblTimeIcon = new JLabel(new ImageIcon(imgTime));

        JLabel lblTime = new JLabel();
        lblTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTime.setForeground(Color.white);
        capNhatGio(lblTime);

        pDateTime.add(lblDateIcon);
        pDateTime.add(lblDate);
        pDateTime.add(lblTimeIcon);
        pDateTime.add(lblTime);

        new Timer(1000, e -> capNhatGio(lblTime)).start();

        panelTrai.add(pDateTime);
        panelTrai.setMaximumSize(new Dimension(300, Short.MAX_VALUE));

        return panelTrai;
    }

    // Cập nhật ngày hiện tại vào nhãn hiển thị
    private void capNhatNgay(JLabel label) {
        java.time.LocalDate now = java.time.LocalDate.now();
        String formattedDate = now.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        label.setText(formattedDate);
    }

    // Cập nhật giờ hiện tại vào nhãn hiển thị mỗi giây
    private void capNhatGio(JLabel label) {
        java.time.LocalTime now = java.time.LocalTime.now();
        String formattedTime = now.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        label.setText(formattedTime);
    }

    // Tạo panel chứa ảnh nhân viên và thông tin chi tiết
    private JPanel taoPanelAnhVaThongTin() {
        JPanel pAnhVaThongTin = new JPanel();
        pAnhVaThongTin.setLayout(new BoxLayout(pAnhVaThongTin, BoxLayout.Y_AXIS));
        pAnhVaThongTin.setBackground(new Color(255, 100, 0));

        ImageIcon iconNhanVien = new ImageIcon("Anh\\NV1.jpg");
        JLabel labelHinhNhanVien = new JLabel(new ImageIcon(iconNhanVien.getImage().getScaledInstance(190, 180, Image.SCALE_SMOOTH)));
        labelHinhNhanVien.setAlignmentX(Component.CENTER_ALIGNMENT);
        pAnhVaThongTin.add(labelHinhNhanVien);

        pAnhVaThongTin.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel pThongTin = taoPanelThongTin();
        pThongTin.setAlignmentX(Component.CENTER_ALIGNMENT);
        pAnhVaThongTin.add(pThongTin);

        return pAnhVaThongTin;
    }

    // Tạo panel chứa thông tin chi tiết nhân viên như mã, họ tên, chức vụ, và số điện thoại
    private JPanel taoPanelThongTin() {
        JPanel pThongTin = new JPanel(new GridBagLayout());
        pThongTin.setBackground(new Color(255, 100, 0));
        pThongTin.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = { "Mã Nhân Viên:", "Họ Tên:", "Chức Vụ:", "Số Điện Thoại:" };
        String[] values = { "12345", "Ngô Bình Xuyên", "Nhân Viên", "0123456789" };

        for (int i = 0; i < labels.length; i++) {
            themLabelVaGiaTri(pThongTin, gbc, labels[i], values[i], i);
        }

        return pThongTin;
    }

    // Thêm nhãn và giá trị vào một dòng trong panel thông tin nhân viên
    private void themLabelVaGiaTri(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.BOLD, 19));
        label.setForeground(Color.white);

        JTextField textField = new JTextField(valueText);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        textField.setBackground(Color.white);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.CENTER);

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    // Tạo panel chứa các nút chức năng như bán vé, báo cáo, kiểm tra lịch
    private JPanel taoPanelChucNang() {
        JPanel pChucNang = new JPanel();
        pChucNang.setLayout(new BoxLayout(pChucNang, BoxLayout.Y_AXIS));
        pChucNang.setBackground(Color.white);
        pChucNang.setBorder(new MatteBorder(2, 0, 2, 2, Color.BLACK));
        pChucNang.add(Box.createVerticalStrut(20));

        JLabel tieuDeChucNang = new JLabel("Quản Lý Bán Hàng", JLabel.CENTER);
        tieuDeChucNang.setFont(new Font("Arial", Font.BOLD, 30));
        tieuDeChucNang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pChucNang.add(tieuDeChucNang);
        pChucNang.add(Box.createVerticalStrut(20));

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelNut.setBackground(Color.white);

        themNutChucNang(panelNut, "Bán Vé", Color.WHITE, "Anh\\cinema.png");
        themNutChucNang(panelNut, "Báo Cáo Quản Lý", Color.WHITE, "Anh\\quanli.png");
        themNutChucNang(panelNut, "Kiểm Tra Lịch Chiếu", Color.WHITE, "Anh\\lich.png");
        themNutChucNang(panelNut, "Tình Trạng Phòng", Color.WHITE, "Anh\\nha.png");
        themNutChucNang(panelNut, "Kiểm tra thống kê", Color.WHITE, "Anh\\thongke.png");
        themNutChucNang(panelNut, "Trang Chủ", Color.WHITE, "Anh\\home.png");

        pChucNang.add(panelNut);

        return pChucNang;
    }

    // Thêm nút chức năng vào panel, mỗi nút có ảnh và văn bản liên quan
    private static void themNutChucNang(JPanel panel, String text, Color color, String iconPath) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setFont(new Font("Arial", Font.BOLD, 18));

        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));

        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.NORTH);

        MatteBorder matteBorder = new MatteBorder(3, 3, 3, 3, Color.DARK_GRAY);
        button.setBorder(matteBorder);
        button.setPreferredSize(new Dimension(200, 200));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Image scaledImg = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImg));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
            }
        });
     // Thêm hành động khi nhấn vào nút
        button.addActionListener(e -> {
            if ("Bán Vé".equals(text)) {
                new TrangSuatChieu_GUI(); 
            }
            if ("Tình Trạng Phòng".equals(text)) {
//            	new TrangTinhTrangPhong_GUI();
			}
            if ("Báo Cáo Quản Lý".equals(text)) {
//            	new TrangBaoCaoQuanLi_GUI();
			}
        });

        panel.add(button);
    }

    public static void main(String[] args) {
        new TrangChu_GUI();
    }
}
