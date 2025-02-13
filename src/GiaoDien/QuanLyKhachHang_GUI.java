package GiaoDien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Dao.KhachHang_DAO;
import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import quanLy.*;
import lop.*;

public class QuanLyKhachHang_GUI extends JFrame implements ActionListener {

	private DefaultTableModel modelTable;
	private JTable table;

	private JTextField txtHoTen;

	private JTextField txtEmail;

	private JTextField txtSDT;

	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btLamMoi;
	private JButton btnTim;
	private JTextField txtMaKH;
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
	private JTextField txtTL;
	private JMenuItem menuItem_27;
	private KhachHang_DAO khachHang_DAO;

	public QuanLyKhachHang_GUI() {
		super("VXD Cinema - Quản lý khách hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		khachHang_DAO = new KhachHang_DAO();
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
				"Thông tin khách hàng", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 18), Color.black));

		panel4.add(createLabeledInputBox("Mã khách hàng:  ", txtMaKH = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Tên khách hàng:   ", txtHoTen = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Email: ", txtEmail = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Số điện thoại: ", txtSDT = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Điểm tích lũy: ", txtTL = new JTextField(20)));

		return panel4;
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
		ChucNang.add(btnThem = new JButton("Thêm khách hàng"));
		ChucNang.add(Box.createHorizontalStrut(10));
		ChucNang.add(btnXoa = new JButton("Xóa khách hàng"));

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

		JLabel jLabel_Title1 = new JLabel("QUẢN LÝ KHÁCH HÀNG");
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
				"Danh sách khách hàng", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 20), Color.black));
		box5.setOpaque(false);

		String[] columns = { "Mã khách hàng", "Tên khách hàng", "Email", "Số điện thoại", "Điểm tích lũy" };
		modelTable = new DefaultTableModel(columns, 0);
		table = new JTable(modelTable);
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		table.setRowHeight(25);
		loadDataToTable();
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		box5.add(scroll);
		box5.add(Box.createVerticalStrut(100));
		return box5;
	}



	class BackgroundPhaiPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPhaiPanel() {
			backgroundImage = new ImageIcon("anh\\NenPhai.png").getImage();
			setBorder(BorderFactory.createLineBorder(Color.black));
		}

		public void setPreferredSize(int i, int j) {

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
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
			
			backgroundImage = new ImageIcon("anh\\NenTrai.png").getImage(); 
			setBorder(BorderFactory.createLineBorder(Color.black, 3)); 
		}

		public void setPreferredSize(int i, int j) {
			

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
		new QuanLyKhachHang_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				themKhachHang();
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(btnXoa)) {
			xoaKhachHang();
		}
		if (o.equals(btnSua)) {
			suaKhachHang();
		}
		if (o.equals(btLamMoi)) {
			clearField();
		}
		if (o.equals(btnTim)) {
			timKhachHang();
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
			ConnectDB.getInstance().connect();
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

	private void themKhachHang() throws HeadlessException, SQLException  {
	    // Biểu thức chính quy cho từng trường dữ liệu
	    String maKHPattern = "^[A-Za-z0-9]{1,10}$"; 
	    String hoTenPattern = "^[a-zA-Z\\s]+$"; 
	    String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$"; 
	    String sdtPattern = "^\\d{10}$"; 

	    // Lấy dữ liệu từ các trường
	    String maKH = txtMaKH.getText();
	    String hoTen = txtHoTen.getText();
	    String email = txtEmail.getText();
	    String sdt = txtSDT.getText();
	    String diemTLStr = txtTL.getText();

	    // Kiểm tra biểu thức chính quy
	    if (!maKH.matches(maKHPattern)) {
	        JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ! Chỉ chứa chữ và số, tối đa 10 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (!hoTen.matches(hoTenPattern)) {
	        JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ! Chỉ chứa chữ cái và khoảng trắng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (!email.matches(emailPattern)) {
	        JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (!sdt.matches(sdtPattern)) {
	        JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ! Phải có 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    int diemTL;
	    try {
	        diemTL = Integer.parseInt(diemTLStr);
	        if (diemTL < 0) {
	            JOptionPane.showMessageDialog(null, "Điểm tích lũy không hợp lệ! Phải là số nguyên dương.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Điểm tích lũy không hợp lệ! Phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (khachHang_DAO.timKhachHang(maKH) != null) {
	        JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại! Vui lòng nhập mã khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Tạo đối tượng KhachHang
	    KhachHang khachHang = new KhachHang(maKH, hoTen, email, sdt, diemTL);

	    try {
	        khachHang_DAO.themKhachHang(khachHang);
	        Object[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getEmail(), khachHang.getSdt(), khachHang.getDiemTichLuy() };
	        modelTable.addRow(rowData);

	        JOptionPane.showMessageDialog(null, "Khách hàng đã được thêm!");
	        clearField();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	}


	// Delete customer
	private void xoaKhachHang() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String maKH = (String) modelTable.getValueAt(selectedRow, 0);

			try {
				khachHang_DAO.xoaKhachHang(maKH);
				modelTable.removeRow(selectedRow);
				JOptionPane.showMessageDialog(null, "Khách hàng đã được xóa!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Hãy chọn một khách hàng để xóa.");
		}
	}

	// Update customer information
	private void suaKhachHang() {
		String maKH = JOptionPane.showInputDialog("Nhập mã khách hàng cần sửa:");

		if (maKH != null && !maKH.trim().isEmpty()) {
			try {
				KhachHang khachHang = khachHang_DAO.timKhachHang(maKH);

				if (khachHang != null) {
					JPanel panel = new JPanel(new GridLayout(0, 2));

					JTextField txtHoTen = new JTextField(khachHang.getHoTen());
					JTextField txtEmail = new JTextField(khachHang.getEmail());
					JTextField txtSDT = new JTextField(khachHang.getSdt());
					JTextField txtTL = new JTextField(String.valueOf(khachHang.getDiemTichLuy()));

					panel.add(new JLabel("Tên khách hàng:"));
					panel.add(txtHoTen);
					panel.add(new JLabel("Email:"));
					panel.add(txtEmail);
					panel.add(new JLabel("Số điện thoại:"));
					panel.add(txtSDT);
					panel.add(new JLabel("Điểm tích lũy:"));
					panel.add(txtTL);

					int result = JOptionPane.showConfirmDialog(null, panel, "Sửa thông tin khách hàng",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						KhachHang updatedKhachHang = new KhachHang(maKH, txtHoTen.getText(), txtEmail.getText(),
								txtSDT.getText(), Integer.parseInt(txtTL.getText()));
						khachHang_DAO.capNhatKhachHang(updatedKhachHang);

						int selectedRow = table.getSelectedRow();
						modelTable.setValueAt(updatedKhachHang.getHoTen(), selectedRow, 1);
						modelTable.setValueAt(updatedKhachHang.getEmail(), selectedRow, 2);
						modelTable.setValueAt(updatedKhachHang.getSdt(), selectedRow, 3);
						modelTable.setValueAt(updatedKhachHang.getDiemTichLuy(), selectedRow, 4);

						JOptionPane.showMessageDialog(null, "Thông tin khách hàng đã được cập nhật!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã: " + maKH, "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Find customer
	private void timKhachHang() {
		String maKH = JOptionPane.showInputDialog("Nhập mã khách hàng cần tìm:");

		if (maKH == null || maKH.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Mã khách hàng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			KhachHang khachHang = khachHang_DAO.timKhachHang(maKH);

			if (khachHang != null) {
				txtMaKH.setText(khachHang.getMaKH());
				txtHoTen.setText(khachHang.getHoTen());
				txtEmail.setText(khachHang.getEmail());
				txtSDT.setText(khachHang.getSdt());
				txtTL.setText(String.valueOf(khachHang.getDiemTichLuy()));

				for (int i = 0; i < modelTable.getRowCount(); i++) {
					if (modelTable.getValueAt(i, 0).equals(maKH)) {
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
						JOptionPane.showMessageDialog(null, "Tìm thấy khách hàng!");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng trên bảng!");
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã: " + maKH);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Tìm kiếm khách hàng thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Load data to table
	private void loadDataToTable() {
		try {
			ArrayList<KhachHang> danhSachKH = (ArrayList<KhachHang>) khachHang_DAO.layDanhSachKhachHang();
			modelTable.setRowCount(0);

			for (KhachHang kh : danhSachKH) {
				Object[] rowData = { kh.getMaKH(), kh.getHoTen(), kh.getEmail(), kh.getSdt(), kh.getDiemTichLuy() };
				modelTable.addRow(rowData);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Tải dữ liệu thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearField() {
		txtMaKH.setText("");
		txtHoTen.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtTL.setText("");
	}
}
