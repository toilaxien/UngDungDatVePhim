package GiaoDien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Dao.ThucPham_DAO;
import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import quanLy.*;
import lop.*;

public class QuanLyThucPham_GUI extends JFrame implements ActionListener {

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
	private JTextField txtMaDoAnNhe;
	private JTextField txtTenDoAnNhe;
	private JTextField txtGia;
	private JTextField txtSoLuongCon;
	private JMenuItem menuItem_27;
	private ThucPham_DAO thucPham_DAO;

	public QuanLyThucPham_GUI() {
		super("VXD Cinema - Quản lý thực phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		ImageIcon icon = new ImageIcon("anh\\logo.png");
		this.setIconImage(icon.getImage());
		add(createMenuBar(), BorderLayout.NORTH);
		thucPham_DAO = new ThucPham_DAO();
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
				"Thông tin đồ ăn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 18), Color.black));

		panel4.add(createLabeledInputBox("Mã đồ ăn nhẹ:  ", txtMaDoAnNhe = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Tên đồ ăn nhẹ:   ", txtTenDoAnNhe = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Giá: ", txtGia = new JTextField(20))); // Nhập giá
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Số lượng còn: ", txtSoLuongCon = new JTextField(20))); // Nhập số lượng còn

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
		ChucNang.add(btnThem = new JButton("Thêm mã đồ ăn"));
		ChucNang.add(Box.createHorizontalStrut(10));
		ChucNang.add(btnXoa = new JButton("Xóa đồ ăn nhẹ"));

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

		JLabel jLabel_Title1 = new JLabel("QUẢN LÝ THỰC PHẨM ");
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
		box5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Danh sách đồ ăn",
				TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 20), Color.black));
		box5.setOpaque(false);

		String[] columns = { "Mã đồ ăn nhẹ", "Tên đồ ăn nhẹ", "Giá", "Số lượng còn" };

		modeltable = new DefaultTableModel(columns, 0);
		table = new JTable(modeltable);
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
		new QuanLyThucPham_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themDoAnNhe();
		}
		if (o.equals(btnXoa)) {
			xoaDoAnNhe();
		}
		if (o.equals(btnSua)) {
			suaDoAnNhe();
		}
		if (o.equals(btLamMoi)) {
			clearField();
		}
		if (o.equals(btnTim)) {
			timDoAnNhe();
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

	private void themDoAnNhe() {
	    try {
	        String maDoAnNhe = txtMaDoAnNhe.getText();
	        String tenDoAnNhe = txtTenDoAnNhe.getText();
	        float gia = Float.parseFloat(txtGia.getText());
	        int soLuongCon = Integer.parseInt(txtSoLuongCon.getText());

	        // Check for duplicate snack code
	        if (thucPham_DAO.timDoAnNhe(maDoAnNhe) !=null) {
	            JOptionPane.showMessageDialog(this, "Mã món ăn nhẹ đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        DoAnNhe doAnNhe = new DoAnNhe(maDoAnNhe, tenDoAnNhe, gia, soLuongCon);
	        thucPham_DAO.themDoAnNhe(doAnNhe); // Call DAO method to add the snack

	        // Add to the table model
	        Object[] rowData = { doAnNhe.getMaDoAnNhe(), doAnNhe.getTenDoAnNhe(), doAnNhe.getGia(),
	                doAnNhe.getSoLuongCon() };
	        modeltable.addRow(rowData);
	        JOptionPane.showMessageDialog(this, "Món ăn nhẹ đã được thêm!");
	        clearField(); // Clear input fields after adding
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Thêm món ăn nhẹ thất bại: " + e.getMessage(), "Lỗi",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	}


	private void xoaDoAnNhe() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String maDoAnNhe = (String) modeltable.getValueAt(selectedRow, 0);
			try {
				thucPham_DAO.xoaDoAnNhe(maDoAnNhe); // Gọi phương thức DAO để xóa món ăn nhẹ
				modeltable.removeRow(selectedRow);
				JOptionPane.showMessageDialog(this, "Món ăn nhẹ đã được xóa!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Xóa món ăn nhẹ thất bại: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn một món ăn nhẹ để xóa.");
		}
	}

	private void suaDoAnNhe() {
		String maDoAnNhe = JOptionPane.showInputDialog(this, "Nhập mã món ăn nhẹ cần sửa:");

		if (maDoAnNhe != null && !maDoAnNhe.trim().isEmpty()) {
			try {
				DoAnNhe doAnNhe = thucPham_DAO.timDoAnNhe(maDoAnNhe); // Gọi phương thức DAO để tìm món ăn nhẹ

				if (doAnNhe != null) {
					JPanel panel = new JPanel(new GridLayout(0, 2));
					JTextField txtTenDoAnNhe = new JTextField(doAnNhe.getTenDoAnNhe());
					JTextField txtGia = new JTextField(String.valueOf(doAnNhe.getGia()));
					JTextField txtSoLuongCon = new JTextField(String.valueOf(doAnNhe.getSoLuongCon()));

					panel.add(new JLabel("Tên món ăn nhẹ:"));
					panel.add(txtTenDoAnNhe);
					panel.add(new JLabel("Giá:"));
					panel.add(txtGia);
					panel.add(new JLabel("Số lượng còn:"));
					panel.add(txtSoLuongCon);

					int result = JOptionPane.showConfirmDialog(this, panel, "Sửa thông tin món ăn nhẹ",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						DoAnNhe updatedDoAnNhe = new DoAnNhe(maDoAnNhe, txtTenDoAnNhe.getText(),
								Float.parseFloat(txtGia.getText()), Integer.parseInt(txtSoLuongCon.getText()));
						thucPham_DAO.capNhatDoAnNhe(updatedDoAnNhe); // Gọi phương thức DAO để cập nhật món ăn nhẹ

						int selectedRow = table.getSelectedRow();
						modeltable.setValueAt(txtTenDoAnNhe.getText(), selectedRow, 1); // Cập nhật tên món ăn nhẹ trong
																						// bảng
						modeltable.setValueAt(txtGia.getText(), selectedRow, 2); // Cập nhật giá trong bảng
						modeltable.setValueAt(txtSoLuongCon.getText(), selectedRow, 3); // Cập nhật số lượng còn trong
																						// bảng

						JOptionPane.showMessageDialog(this, "Thông tin món ăn nhẹ đã được cập nhật!");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy món ăn nhẹ với mã: " + maDoAnNhe, "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Cập nhật món ăn nhẹ thất bại: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Mã món ăn nhẹ không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void timDoAnNhe() {
		String maDoAnNhe = JOptionPane.showInputDialog(this, "Nhập mã món ăn nhẹ cần tìm:");

		if (maDoAnNhe == null || maDoAnNhe.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Mã món ăn nhẹ không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			DoAnNhe doAnNhe = thucPham_DAO.timDoAnNhe(maDoAnNhe); // Gọi phương thức DAO để tìm món ăn nhẹ

			if (doAnNhe != null) {
				txtMaDoAnNhe.setText(doAnNhe.getMaDoAnNhe());
				txtTenDoAnNhe.setText(doAnNhe.getTenDoAnNhe());
				txtGia.setText(String.valueOf(doAnNhe.getGia()));
				txtSoLuongCon.setText(String.valueOf(doAnNhe.getSoLuongCon()));

				for (int i = 0; i < modeltable.getRowCount(); i++) {
					if (modeltable.getValueAt(i, 0).equals(maDoAnNhe)) {
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
						JOptionPane.showMessageDialog(this, "Tìm thấy món ăn nhẹ!");
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy món ăn nhẹ trên bảng!");
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy món ăn nhẹ với mã: " + maDoAnNhe);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tìm món ăn nhẹ thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadDataToTable() {
		try {
			ArrayList<DoAnNhe> danhSachDoAnNhe = thucPham_DAO.layDanhSachDoAnNhe(); // Gọi phương thức DAO để lấy danh
																					// sách món ăn nhẹ
			modeltable.setRowCount(0); // Xóa các hàng hiện tại

			for (DoAnNhe doAnNhe : danhSachDoAnNhe) {
				Object[] rowData = { doAnNhe.getMaDoAnNhe(), doAnNhe.getTenDoAnNhe(), doAnNhe.getGia(),
						doAnNhe.getSoLuongCon() };
				modeltable.addRow(rowData); // Thêm từng món ăn nhẹ vào bảng
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearField() {
		txtMaDoAnNhe.setText("");
		txtTenDoAnNhe.setText("");
		txtGia.setText("");
		txtSoLuongCon.setText("");
	}

}
