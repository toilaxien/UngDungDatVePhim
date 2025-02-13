package GiaoDien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Dao.KhuyenMai_DAO;
import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import quanLy.*;
import lop.*;

public class QuanLyKhuyenMai_GUI extends JFrame implements ActionListener {

	private DefaultTableModel modeltable;
	private JTable table;

	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btLamMoi;
	private JButton btnTim;
	private JButton btnThoat;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private JMenuItem menuItem_5;
	private JMenuItem menuTrangChu;
	private JMenuItem menuItem_21;
	private JMenuItem menuItem_22;
	private JMenuItem menuItem_23;
	private JMenuItem menuItem_24;
	private JMenuItem menuItem_25;
	private JMenuItem menuItem_26;
	private JTextField txtMaKM;
	private JTextField txtNgayBatDau;
	private JTextField txtNgayKetThuc;
	private JTextField txtGiamGia;
	private JTextField txtMoTa;
	private JMenuItem menuItem_27;
	private KhuyenMai_DAO khuyenMai_DAO;

	public QuanLyKhuyenMai_GUI() {
		super("VXD Cinema - QUẢN LÝ KHUYẾN MÃI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		khuyenMai_DAO = new KhuyenMai_DAO();
		ImageIcon icon = new ImageIcon("anh\\logo.png");
		this.setIconImage(icon.getImage());
		add(createMenuBar(), BorderLayout.NORTH);

		add(createTraiPanel(), BorderLayout.WEST);
		add(createPhaiPanel(), BorderLayout.CENTER);
		
		setVisible(true);
	}

	// Tạo thanh menu
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		menuBar.setLayout(new FlowLayout());
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 15));

		JLabel logoLabel = new JLabel();
		ImageIcon logoIcon = new ImageIcon("anh\\circular_image_50x50.png");
		logoLabel.setIcon(logoIcon);
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		menuBar.add(Box.createHorizontalStrut(5));
		menuBar.add(logoLabel);
		menuBar.add(Box.createHorizontalStrut(5));
		// Menu Trang Chủ
		menuTrangChu = new JMenuItem("Trang Chủ");
		menuTrangChu.setOpaque(false);
		menuTrangChu.setContentAreaFilled(false);
		ImageIcon trangchuicon = new ImageIcon("anh\\icons8-home-24.png");
		menuTrangChu.setIcon(trangchuicon);
		menuTrangChu.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuTrangChu.setPreferredSize(new Dimension(145, 50));

		menuBar.add(menuTrangChu);
		menuBar.setPreferredSize(new Dimension(10, 60));
		menuTrangChu.addActionListener(this);
		menuTrangChu.addMouseListener(new MouseAdapter() {
			private Font originalFont = menuTrangChu.getFont(); // Lưu lại font gốc

			@Override
			public void mouseEntered(MouseEvent e) {

				menuTrangChu.setContentAreaFilled(false);

				// Phóng to kích thước font
				menuTrangChu.setFont(originalFont.deriveFont(originalFont.getSize() + 2f));

				// Phóng to icon
				menuTrangChu.setIcon(scaleIcon(trangchuicon, 1.5)); // Tăng kích thước icon lên 1.5 lần
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuTrangChu.setForeground(UIManager.getColor("Menu.foreground"));
				menuTrangChu.setBackground(UIManager.getColor("Menu.background"));

				// Khôi phục lại kích thước font ban đầu
				menuTrangChu.setFont(originalFont);
				// Khôi phục icon ban đầu
				menuTrangChu.setIcon(trangchuicon);
			}
		});
		// Menu Nhân viên
		JMenu menuNhanVien = new JMenu("QUẢN LÝ");
		menuNhanVien.setPreferredSize(new Dimension(150, 50));
		ImageIcon qlNVgocIcon = new ImageIcon("anh\\icons8-manage-30.png");

		menuNhanVien.setIcon(qlNVgocIcon);
		menuNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuItem_2 = new JMenuItem("Quản lý Nhân Viên");
		ImageIcon originalIcon = new ImageIcon("anh\\changes.png");
		menuItem_2.setIcon(originalIcon);
		menuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuItem_2.addActionListener(this);
		menuNhanVien.add(menuItem_2);
		menuItem_21 = new JMenuItem("Quản lý khách hàng");
		ImageIcon originalIcon1 = new ImageIcon("anh\\icons8-client-24.png");
		menuItem_21.setIcon(originalIcon1);
		menuItem_21.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuItem_21.addActionListener(this);
		menuNhanVien.add(menuItem_21);

		menuItem_22 = new JMenuItem("Quản lý khuyến mãi");
		ImageIcon originalIcon2 = new ImageIcon("anh\\icons8-promotion-24.png");
		menuItem_22.setIcon(originalIcon2);
		menuItem_22.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuItem_22.addActionListener(this);
		menuNhanVien.add(menuItem_22);

		menuItem_23 = new JMenuItem("Quản lý phim");
		ImageIcon originalIcon3 = new ImageIcon("anh\\icons8-film-24.png");
		menuItem_23.setIcon(originalIcon3);
		menuItem_23.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuItem_23.addActionListener(this);
		menuNhanVien.add(menuItem_23);

		menuItem_24 = new JMenuItem("Quản lý suất chiếu");
		ImageIcon originalIcon4 = new ImageIcon("anh\\icons8-calendar-24.png");
		menuItem_24.setIcon(originalIcon4);
		menuItem_24.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuItem_24.addActionListener(this);
		menuNhanVien.add(menuItem_24);

		menuItem_25 = new JMenuItem("Quản lý tài khoản");
		ImageIcon originalIcon5 = new ImageIcon("anh\\users-icon.png");
		menuItem_25.setIcon(originalIcon5);
		menuItem_25.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuItem_25.addActionListener(this);
		menuNhanVien.add(menuItem_25);

		menuItem_26 = new JMenuItem("Quản lý thực phẩm");
		ImageIcon originalIcon6 = new ImageIcon("anh\\icons8-restaurant-24.png");
		menuItem_26.setIcon(originalIcon6);
		menuItem_26.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuItem_26.addActionListener(this);
		menuNhanVien.add(menuItem_26);

		menuItem_27 = new JMenuItem("Quản lý phòng chiếu");
		ImageIcon originalIcon7 = new ImageIcon("anh\\icons8-cinema-24.png");
		menuItem_27.setIcon(originalIcon7);
		menuItem_27.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuItem_27.addActionListener(this);
		menuNhanVien.add(menuItem_27);
		menuBar.add(menuNhanVien);

		menuNhanVien.addMouseListener(new MouseAdapter() {
			private Font originalFont = menuNhanVien.getFont(); // Lưu lại font gốc

			@Override
			public void mouseEntered(MouseEvent e) {

				menuNhanVien.setContentAreaFilled(false);

				// Phóng to kích thước font
				menuNhanVien.setFont(originalFont.deriveFont(originalFont.getSize() + 2f));

				// Phóng to icon
				menuNhanVien.setIcon(scaleIcon(qlNVgocIcon, 1.5)); // Tăng kích thước icon lên 1.5 lần
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuNhanVien.setForeground(UIManager.getColor("Menu.foreground"));
				menuNhanVien.setBackground(UIManager.getColor("Menu.background"));

				// Khôi phục lại kích thước font ban đầu
				menuNhanVien.setFont(originalFont);
				// Khôi phục icon ban đầu
				menuNhanVien.setIcon(qlNVgocIcon);
			}
		});

		// Menu Thống kê
		JMenu menuThongKe = new JMenu("Thống kê");
		menuThongKe.setPreferredSize(new Dimension(130, 50));
		ImageIcon tkgocIcon = new ImageIcon("anh\\Food-List-Ingredients-icon.png");
		menuThongKe.setIcon(tkgocIcon);
		menuThongKe.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuItem_3 = new JMenuItem("Doanh thu");

		ImageIcon doanhthugoc = new ImageIcon("anh\\increase.png");
		menuItem_3.setIcon(doanhthugoc);
		menuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuItem_3.addActionListener(this);

		menuItem_4 = new JMenuItem("Phim");
		ImageIcon cuonphim = new ImageIcon("anh\\icons8-film-24.png");
		Image cp = cuonphim.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
		ImageIcon cuonphimgoc = new ImageIcon(cp);
		menuItem_4.setIcon(cuonphimgoc);
		menuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuItem_4.addActionListener(this);

		menuThongKe.add(menuItem_3);

		menuThongKe.add(menuItem_4);
		menuBar.add(menuThongKe);

		menuThongKe.addMouseListener(new MouseAdapter() {
			private Font originalFont = menuThongKe.getFont(); // Lưu lại font gốc

			@Override
			public void mouseEntered(MouseEvent e) {

				menuThongKe.setContentAreaFilled(false);

				// Phóng to kích thước font
				menuThongKe.setFont(originalFont.deriveFont(originalFont.getSize() + 2f));

				// Phóng to icon
				menuThongKe.setIcon(scaleIcon(tkgocIcon, 1.5)); // Tăng kích thước icon lên 1.5 lần
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuThongKe.setForeground(UIManager.getColor("Menu.foreground"));
				menuThongKe.setBackground(UIManager.getColor("Menu.background"));

				// Khôi phục lại kích thước font ban đầu
				menuThongKe.setFont(originalFont);
				// Khôi phục icon ban đầu
				menuThongKe.setIcon(tkgocIcon);
			}
		});

		// Menu trợ giúp
		JMenu menuTroGiup = new JMenu("Trợ giúp");
		ImageIcon trogiupgocIcon = new ImageIcon("anh\\help-circle-icon.png");
		menuTroGiup.setIcon(trogiupgocIcon);
		menuTroGiup.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuTroGiup.setPreferredSize(new Dimension(130, 50));
		menuItem_5 = new JMenuItem("Hướng dẫn sử dụng");
		menuItem_5.setIcon(new ImageIcon("anh\\instructions.png"));
		menuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuTroGiup.add(menuItem_5);

		menuBar.add(menuTroGiup);
		menuBar.setPreferredSize(new Dimension(10, 60));

		menuTroGiup.addMouseListener(new MouseAdapter() {
			private Font originalFont = menuTroGiup.getFont(); // Lưu lại font gốc

			@Override
			public void mouseEntered(MouseEvent e) {

				menuTroGiup.setContentAreaFilled(false);

				// Phóng to kích thước font
				menuTroGiup.setFont(originalFont.deriveFont(originalFont.getSize() + 2f));

				// Phóng to icon
				menuTroGiup.setIcon(scaleIcon(trogiupgocIcon, 1.5)); // Tăng kích thước icon lên 1.5 lần
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuTroGiup.setForeground(UIManager.getColor("Menu.foreground"));
				menuTroGiup.setBackground(UIManager.getColor("Menu.background"));

				// Khôi phục lại kích thước font ban đầu
				menuTroGiup.setFont(originalFont);
				// Khôi phục icon ban đầu
				menuTroGiup.setIcon(trogiupgocIcon);
			}
		});
		return menuBar;
	}

	// Tạo phần panel bên trái
	private JPanel createTraiPanel() {
		BackgroundPanel panel1 = new BackgroundPanel();
		Box TraiPanel = Box.createVerticalBox();

		TraiPanel.add(Box.createVerticalStrut(100));

		JLabel jLabel_Title = new JLabel("NHẬP THÔNG TIN");
		jLabel_Title.setFont(new Font("Times new Rowman", Font.BOLD, 20));
		jLabel_Title.setForeground(Color.black);
		JPanel jLabel_panel = new JPanel();
		jLabel_panel.setOpaque(false);
		jLabel_panel.add(jLabel_Title);
		TraiPanel.add(jLabel_panel);

		TraiPanel.add(createInputPanel()); // Thêm phần nhập thông tin
		TraiPanel.add(createButtonPanel()); // Thêm phần các chức năng
		TraiPanel.add(Box.createVerticalStrut(220));

		Box panel7 = Box.createHorizontalBox();
		panel7.setPreferredSize(new Dimension(400, 75));
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
		btnThoat.addActionListener(this);
		ImageIcon icon1 = new ImageIcon("anh\\đăng suất.jpg");
		Image ico = icon1.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(ico);
		btnThoat.setIcon(icon2);
		btnThoat.setContentAreaFilled(false); // Tắt nền
		btnThoat.setOpaque(false); // Tắt độ mờ
		btnThoat.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pThoat.add(btnThoat);
		pThoat.add(Box.createVerticalStrut(5));
		panel7.add(pThoat);
		new Timer(1000, e -> capNhatGio(lblTime)).start();
		TraiPanel.add(panel7);
		panel1.add(TraiPanel);
		panel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		return panel1;
	}

	// Panel nhập thông tin
	private Box createInputPanel() {
		Box panel4 = Box.createVerticalBox();
		panel4.add(Box.createHorizontalStrut(400));
		panel4.setOpaque(false);
		panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Thông tin khuyến mãi", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 18), Color.black));

		panel4.add(createLabeledInputBox("Mã khuyến mãi:  ", txtMaKM = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		
		panel4.add(createLabeledInputBox("Ngày bắt đầu:   ", txtNgayBatDau = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		addPlaceholder(txtNgayBatDau, "yyyy-MM-dd");
		panel4.add(createLabeledInputBox("Ngày kết thúc: ", txtNgayKetThuc = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		addPlaceholder(txtNgayKetThuc, "yyyy-MM-dd");
		panel4.add(createLabeledInputBox("Giảm giá: ", txtGiamGia = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Mô tả: ", txtMoTa = new JTextField(20)));

		return panel4;
	}


    public static void addPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }

        });
    }
	// Cập nhật ngày hiện tại vào nhãn hiển thị
	private void capNhatNgay(JLabel label) {
		java.time.LocalDate now = java.time.LocalDate.now();
		String formattedDate = now.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		label.setText(formattedDate);
		label.setForeground(Color.black);
	}

	// Cập nhật giờ hiện tại vào nhãn hiển thị mỗi giây
	private void capNhatGio(JLabel label) {
		java.time.LocalTime now = java.time.LocalTime.now();
		String formattedTime = now.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
		label.setText(formattedTime);
		label.setForeground(Color.black);
	}

	// Tạo hộp nhập liệu
	private Box createLabeledInputBox(String label, Component inputField) {
		Box box = Box.createHorizontalBox();
		JLabel lbl = new JLabel(label);
		lbl.setFont(new Font("Tohama", Font.BOLD, 15));
		lbl.setPreferredSize(new Dimension(150, 25));
		box.add(lbl);
		box.add(inputField);
		return box;
	}

	// Panel các chức năng
	private Box createButtonPanel() {
		Box panelChucNang = Box.createVerticalBox();
		panelChucNang.add(Box.createHorizontalStrut(350));
		panelChucNang.setOpaque(false);
		panelChucNang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Các chức năng", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 18), Color.black));

		Box ChucNang = Box.createHorizontalBox();
		ChucNang.add(btnThem = new JButton("Thêm mã giảm giá"));
		ChucNang.add(Box.createHorizontalStrut(10));
		ChucNang.add(btnXoa = new JButton("Xóa mã giảm giá"));

		Box ChucNang2 = Box.createHorizontalBox();
		ChucNang2.add(btnSua = new JButton("Sửa thông tin"));
		ChucNang2.add(Box.createHorizontalStrut(10));
		ChucNang2.add(btLamMoi = new JButton("Làm mới"));
		ChucNang2.add(Box.createHorizontalStrut(10));
		ChucNang2.add(btnTim = new JButton("Tìm kiếm"));

		panelChucNang.add(ChucNang);
		panelChucNang.add(Box.createVerticalStrut(10));
		panelChucNang.add(ChucNang2);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btLamMoi.addActionListener(this);

		return panelChucNang;
	}

	// Tạo phần panel bên phải
	private JPanel createPhaiPanel() {
		BackgroundPhaiPanel PhaiPanel = new BackgroundPhaiPanel();
		PhaiPanel.setLayout(new BoxLayout(PhaiPanel, BoxLayout.Y_AXIS));
		PhaiPanel.add(Box.createVerticalStrut(30));

		JLabel jLabel_Title1 = new JLabel("QUẢN LÝ KHUYẾN MÃI ");
		jLabel_Title1.setFont(new Font("Times new Rowman", Font.BOLD, 30));
		jLabel_Title1.setForeground(Color.black);
		Box jLabel_panel = Box.createHorizontalBox();
		jLabel_panel.setOpaque(false);
		jLabel_panel.add(jLabel_Title1);
		jLabel_panel.add(Box.createHorizontalStrut(100));
		PhaiPanel.add(jLabel_panel);

		PhaiPanel.add(createTablePanel());
		PhaiPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

		return PhaiPanel;
	}

	// Panel hiển thị bảng nhân viên
	private Box createTablePanel() {
		Box box5 = Box.createVerticalBox();
		box5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Danh sách khuyến mãi", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 20), Color.black));
		box5.setOpaque(false);

		String[] columns = { "Mã giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá", "Mô tả" };
		modeltable = new DefaultTableModel(columns, 0);
		table = new JTable(modeltable);
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		loadDataToTable();
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		box5.add(scroll);
		box5.add(Box.createVerticalStrut(100));
		return box5;
	}

	private Dimension getSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	class BackgroundPhaiPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPhaiPanel() {
			// Tải hình ảnh
			backgroundImage = new ImageIcon("anh\\NenPhai.png").getImage();
			setBorder(BorderFactory.createLineBorder(Color.black));
		}

		public void setPreferredSize(int i, int j) {
			// TODO Auto-generated method stub

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Vẽ hình nền
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	private ImageIcon scaleIcon(ImageIcon icon, double scale) {
		Image image = icon.getImage();
		int width = (int) (image.getWidth(null) * scale);
		int height = (int) (image.getHeight(null) * scale);
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}

	class BackgroundPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPanel() {
			// Tải hình ảnh
			backgroundImage = new ImageIcon("anh\\NenTrai.png").getImage(); // Thay đổi thành đường dẫn hình
			setBorder(BorderFactory.createLineBorder(Color.black, 3)); // ảnh của bạn
		}

		public void setPreferredSize(int i, int j) {
			// TODO Auto-generated method stub

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Vẽ hình nền
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public static void main(String[] args) {
		ConnectDB.getInstance().connect();
		new QuanLyKhuyenMai_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				themKhuyenMai();
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(btnXoa)) {
			xoaKhuyenMai();
		}
		if (o.equals(btnSua)) {
			suaKhuyenMai();
		}
		if (o.equals(btLamMoi)) {
			clearField();
		}
		if (o.equals(btnTim)) {
			timKhuyenMai();
		}

		if (o.equals(menuItem_3)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new ThongKeDoanhSo_GUI();
		}
		if (o.equals(menuItem_4)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new ThongKePhim_GUI();
		}
		if (o.equals(menuItem_2)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyNhanVien_GUI();
		}
		if (o.equals(menuItem_21)) {
			this.dispose();
			new QuanLyKhachHang_GUI();
		}
		if (o.equals(menuItem_22)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyKhuyenMai_GUI();
		}
		if (o.equals(menuItem_23)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyPhim_GUI();
		}
		if (o.equals(menuItem_24)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLySuatChieu_GUI();
		}
		if (o.equals(menuItem_25)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyTaiKhoan_GUI();
		}
		if (o.equals(menuItem_26)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyThucPham_GUI();
		}
		if (o.equals(menuItem_5)) {
			this.dispose();
			new TroGiup_GUI();
		}
		if (o.equals(menuTrangChu)) {
			this.dispose();
			new TrangChuQuanLY_GUI();
		}
		if (o.equals(btnThoat)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new DangNhap_GUI();
		}
		if (o.equals(menuItem_27)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyPhongChieu_GUI();
		}

	}

	private void themKhuyenMai() throws HeadlessException, SQLException {
	    // Biểu thức chính quy cho định dạng ngày và các trường khác
	    String maKMPattern = "^[A-Za-z0-9]{1,10}$"; // Mã KM tối đa 10 ký tự alphanumeric
	    String datePattern = "^\\d{4}-\\d{2}-\\d{2}$"; // Định dạng ngày: yyyy-MM-dd

	    // Lấy dữ liệu từ các trường
	    String maKM = txtMaKM.getText();
	    String ngayBatDauStr = txtNgayBatDau.getText();
	    String ngayKetThucStr = txtNgayKetThuc.getText();
	    String giamGiaStr = txtGiamGia.getText();
	    String moTa = txtMoTa.getText();

	    // Kiểm tra biểu thức chính quy cho mã khuyến mãi
	    if (!maKM.matches(maKMPattern)) {
	        JOptionPane.showMessageDialog(this, "Mã khuyến mãi không hợp lệ! Chỉ chứa chữ và số, tối đa 10 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Kiểm tra định dạng ngày bắt đầu và ngày kết thúc
	    if (!ngayBatDauStr.matches(datePattern)) {
	        JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ! Định dạng phải là yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (!ngayKetThucStr.matches(datePattern)) {
	        JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ! Định dạng phải là yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    float giamGia;
	    try {
	        giamGia = Float.parseFloat(giamGiaStr);
	        if (giamGia < 0 || giamGia > 100) {
	            JOptionPane.showMessageDialog(this, "Giảm giá không hợp lệ! Phải là số dương và không vượt quá 100%.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Giảm giá không hợp lệ! Phải là một số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Kiểm tra trùng mã khuyến mãi
	    if (khuyenMai_DAO.timKhuyenMai(maKM) != null) {
	        JOptionPane.showMessageDialog(this, "Mã khuyến mãi đã tồn tại! Vui lòng nhập mã khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	 // Chuyển đổi ngày từ chuỗi sang LocalDate
	    LocalDate ngayBatDau = LocalDate.parse(ngayBatDauStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	    LocalDate ngayKetThuc = LocalDate.parse(ngayKetThucStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	    // Tạo đối tượng KhuyenMai
	    KhuyenMai khuyenMai = new KhuyenMai(maKM, ngayBatDau, ngayKetThuc, giamGia, moTa);

	    try {
	        // Thêm khuyến mãi vào CSDL
	        khuyenMai_DAO.themKhuyenMai(khuyenMai);

	        // Thêm khuyến mãi vào bảng hiển thị
	        Object[] rowData = { khuyenMai.getMaKM(), khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc(),
	                khuyenMai.getGiamGia(), khuyenMai.getMoTa() };
	        modeltable.addRow(rowData);

	        // Hiển thị thông báo thành công
	        JOptionPane.showMessageDialog(this, "Khuyến mãi đã được thêm!");
	        clearField();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	}

	private void xoaKhuyenMai() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String maKM = (String) modeltable.getValueAt(selectedRow, 0);

			try {
				khuyenMai_DAO.xoaKhuyenMai(maKM);
				modeltable.removeRow(selectedRow);
				JOptionPane.showMessageDialog(this, "Khuyến mãi đã được xóa!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Xóa khuyến mãi thất bại: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn một khuyến mãi để xóa.");
		}
	}

	private void suaKhuyenMai() {
		String maKM = JOptionPane.showInputDialog(this, "Nhập mã khuyến mãi cần sửa:");

		if (maKM != null && !maKM.trim().isEmpty()) {
			try {
				KhuyenMai khuyenMai = khuyenMai_DAO.timKhuyenMai(maKM);

				if (khuyenMai != null) {
					JPanel panel = new JPanel(new GridLayout(0, 2));

					JTextField txtNgayBatDau = new JTextField(khuyenMai.getNgayBatDau().toString());
					JTextField txtNgayKetThuc = new JTextField(khuyenMai.getNgayKetThuc().toString());
					JTextField txtGiamGia = new JTextField(String.valueOf(khuyenMai.getGiamGia()));
					JTextField txtMoTa = new JTextField(khuyenMai.getMoTa());

					panel.add(new JLabel("Ngày bắt đầu:"));
					panel.add(txtNgayBatDau);
					panel.add(new JLabel("Ngày kết thúc:"));
					panel.add(txtNgayKetThuc);
					panel.add(new JLabel("Giảm giá:"));
					panel.add(txtGiamGia);
					panel.add(new JLabel("Mô tả:"));
					panel.add(txtMoTa);

					int result = JOptionPane.showConfirmDialog(this, panel, "Sửa thông tin khuyến mãi",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						KhuyenMai updatedKhuyenMai = new KhuyenMai(maKM, LocalDate.parse(txtNgayBatDau.getText()),
								LocalDate.parse(txtNgayKetThuc.getText()), Float.parseFloat(txtGiamGia.getText()),
								txtMoTa.getText());

						khuyenMai_DAO.capNhatKhuyenMai(updatedKhuyenMai);

						int selectedRow = table.getSelectedRow();
						modeltable.setValueAt(updatedKhuyenMai.getNgayBatDau(), selectedRow, 1);
						modeltable.setValueAt(updatedKhuyenMai.getNgayKetThuc(), selectedRow, 2);
						modeltable.setValueAt(updatedKhuyenMai.getGiamGia(), selectedRow, 3);
						modeltable.setValueAt(updatedKhuyenMai.getMoTa(), selectedRow, 4);

						JOptionPane.showMessageDialog(this, "Thông tin khuyến mãi đã được cập nhật!");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi với mã: " + maKM, "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thất bại: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Mã khuyến mãi không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void timKhuyenMai() {
		String maKM = JOptionPane.showInputDialog(this, "Nhập mã khuyến mãi cần tìm:");

		if (maKM == null || maKM.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Mã khuyến mãi không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			KhuyenMai khuyenMai = khuyenMai_DAO.timKhuyenMai(maKM);

			if (khuyenMai != null) {
				txtMaKM.setText(khuyenMai.getMaKM());
				txtNgayBatDau.setText(khuyenMai.getNgayBatDau().toString());
				txtNgayKetThuc.setText(khuyenMai.getNgayKetThuc().toString());
				txtGiamGia.setText(String.valueOf(khuyenMai.getGiamGia()));
				txtMoTa.setText(khuyenMai.getMoTa());

				for (int i = 0; i < modeltable.getRowCount(); i++) {
					if (modeltable.getValueAt(i, 0).equals(maKM)) {
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
						JOptionPane.showMessageDialog(this, "Tìm thấy khuyến mãi!");
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi trên bảng!");
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi với mã: " + maKM);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tìm kiếm khuyến mãi thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadDataToTable() {
		try {
			ArrayList<KhuyenMai> danhSachKM = (ArrayList<KhuyenMai>) khuyenMai_DAO.layDanhSachKhuyenMai();
			modeltable.setRowCount(0);

			for (KhuyenMai km : danhSachKM) {
				Object[] rowData = { km.getMaKM(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getGiamGia(),
						km.getMoTa() };
				modeltable.addRow(rowData);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearField() {
		txtMaKM.setText("");
		txtNgayBatDau.setText("");
		txtNgayKetThuc.setText("");
		txtGiamGia.setText("");
		txtMoTa.setText("");
	}




	
}
