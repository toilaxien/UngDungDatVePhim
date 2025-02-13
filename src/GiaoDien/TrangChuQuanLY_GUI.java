package GiaoDien;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrangChuQuanLY_GUI extends JFrame implements ActionListener{

    private JButton btnThoat;

	public TrangChuQuanLY_GUI() {
        ImageIcon icon = new ImageIcon("anh\\logo.png");
		this.setIconImage(icon.getImage());
        setTitle("VXD Cinema - Trang chủ quản lý");
        Dimension kichThuocManHinh = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1920, 1080);

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//      setResizable(false);
        setVisible(true);
    }

    // Tạo panel bên trái chứa thông tin nhân viên và thời gian
    private JPanel taoPanelBenTrai() {
        JPanel panelTrai = new JPanel();
        panelTrai.setLayout(new BoxLayout(panelTrai, BoxLayout.Y_AXIS));
        panelTrai.setBackground(new Color(255, 100, 0));
        panelTrai.setBorder(new MatteBorder(2, 0, 2, 2, Color.BLACK));
        panelTrai.add(Box.createVerticalStrut(20));

        JLabel lblThongTin = new JLabel("Welcome to VXD Cinema");
        lblThongTin.setForeground(Color.white);
        lblThongTin.setFont(new Font("Monotype Corsiva", Font.BOLD, 28));
        lblThongTin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTrai.add(lblThongTin);
        panelTrai.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel pAnhVaThongTin = taoPanelAnhVaThongTin();
        pAnhVaThongTin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTrai.add(pAnhVaThongTin);

        Box panel7 = Box.createHorizontalBox();
		panel7.setPreferredSize(new Dimension(400,75));
        panel7.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		Box pDateTime = Box.createVerticalBox();
		pDateTime.setBackground(new Color(255, 100, 0));

		JPanel pDate = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pDate.setOpaque(false);
		ImageIcon iconDate = new ImageIcon("anh\\date.png");
		Image imgDate = iconDate.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
		JLabel lblDateIcon = new JLabel(new ImageIcon(imgDate));

		JLabel lblDate = new JLabel();
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDate.setForeground(Color.white);
		capNhatNgay(lblDate);
		pDate.add(lblDateIcon);
		pDate.add(lblDate);

		JPanel pTime = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pTime.setOpaque(false);
		ImageIcon iconTime = new ImageIcon("anh\\ho.png");
		Image imgTime = iconTime.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		JLabel lblTimeIcon = new JLabel(new ImageIcon(imgTime));

		JLabel lblTime = new JLabel();
		lblTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTime.setForeground(Color.white);
		capNhatGio(lblTime);
		pTime.add(lblTimeIcon);
		pTime.add(lblTime);

		pDateTime.add(pDate);
		pDateTime.add(pTime);

		panel7.add(pDateTime);

		Box pThoat = Box.createVerticalBox();
		btnThoat = new JButton("Đăng suất");
		btnThoat.setBackground(new Color(255, 255, 255)); // Màu nền (ví dụ: vàng)
		btnThoat.setForeground(Color.BLACK);
		btnThoat.addActionListener(this);
		ImageIcon icon1 = new ImageIcon("anh\\đăng suất.jpg");
		Image ico = icon1.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(ico);
		btnThoat.setIcon(icon2);
		
		btnThoat.setOpaque(false); // Tắt độ mờ
		btnThoat.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pThoat.add(btnThoat);
		pThoat.add(Box.createVerticalStrut(5));
		panel7.add(pThoat);
		new Timer(1000, e -> capNhatGio(lblTime)).start();
        panelTrai.add(panel7);
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

        ImageIcon iconNhanVien = new ImageIcon("anh\\logo.png");
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
        pChucNang.add(Box.createVerticalStrut(100));

        JLabel tieuDeChucNang = new JLabel("Các chức năng của quản lý", JLabel.CENTER);
        tieuDeChucNang.setFont(new Font("Arial", Font.BOLD, 30));
        tieuDeChucNang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pChucNang.add(tieuDeChucNang);
        pChucNang.add(Box.createVerticalStrut(20));

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelNut.setBackground(Color.white);

        themNutChucNang(panelNut, "Quản lý nhân viên", Color.WHITE, "anh\\icons8-staff-50.png");
        themNutChucNang(panelNut, "Quản lý khách hàng", Color.WHITE, "anh\\icons8-client-50.png");
        themNutChucNang(panelNut, "Quản lý phim", Color.WHITE, "anh\\icons8-film-50.png");
        themNutChucNang(panelNut, "Quản lý suất chiếu", Color.WHITE, "anh\\icons8-calendar-50.png");
        themNutChucNang(panelNut, "Quản lý khuyến mãi", Color.WHITE, "anh\\icons8-promotion-50.png");
        themNutChucNang(panelNut, "Quản lý tài khoản", Color.WHITE, "anh\\icons8-account-50.png");
        themNutChucNang(panelNut, "Quản lý thực phẩm", Color.WHITE, "anh\\icons8-knife-and-fork-50.png");
        themNutChucNang(panelNut, "Quản lý phòng chiếu", Color.WHITE, "anh\\icons8-cinema-48.png");
        themNutChucNang(panelNut, "Thống kê doanh thu", Color.WHITE, "anh\\icons8-revenue-50.png");
        themNutChucNang(panelNut, "Thống kê phim", Color.WHITE, "anh\\icons8-film-50 (1).png");
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
        


        button.addActionListener(e -> {
            // Tắt giao diện hiện tại
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
            if (parentFrame != null) {
                parentFrame.dispose(); // Đóng cửa sổ hiện tại
             // Lấy văn bản của nút
            }

            if ("Quản lý nhân viên".equals(text)) {
            	ConnectDB.getInstance().connect();
                new QuanLyNhanVien_GUI();
            } else if ("Quản lý khách hàng".equals(text)) {
            	ConnectDB.getInstance().connect();
                new QuanLyKhachHang_GUI();
            } else if ("Quản lý phim".equals(text)) {
            	ConnectDB.getInstance().connect();
                new QuanLyPhim_GUI();
            } else if ("Quản lý suất chiếu".equals(text)) {
                new QuanLySuatChieu_GUI();
            } else if ("Quản lý khuyến mãi".equals(text)) {
            	ConnectDB.getInstance().connect();
                new QuanLyKhuyenMai_GUI();
            } else if ("Quản lý tài khoản".equals(text)) {
            	ConnectDB.getInstance().connect();
                new QuanLyTaiKhoan_GUI();
            } else if ("Quản lý thực phẩm".equals(text)) {
            	ConnectDB.getInstance().connect();
                new QuanLyThucPham_GUI();
            } else if ("Thống kê doanh thu".equals(text)) {
            	ConnectDB.getInstance().connect();
                new ThongKeDoanhSo_GUI();
            } else if ("Thống kê phim".equals(text)) {
            	ConnectDB.getInstance().connect();
                new ThongKePhim_GUI();
            } else if ("Quản lý phòng chiếu".equals(text)) {
            	ConnectDB.getInstance().connect();
                new QuanLyPhongChieu_GUI();
            }
        });


        panel.add(button);
    }

    public static void main(String[] args) {
        new TrangChuQuanLY_GUI();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object= e.getSource();
		if(object.equals(btnThoat)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new DangNhap_GUI();
		}

		
	}
}
