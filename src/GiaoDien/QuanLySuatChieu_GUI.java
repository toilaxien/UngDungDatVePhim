package GiaoDien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Dao.Phim_DAO;
import Dao.PhongChieu_DAO;
import Dao.SuatChieu_DAO;
import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import quanLy.*;
import lop.*;

public class QuanLySuatChieu_GUI extends JFrame implements ActionListener {

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
	private JTextField txtMaSuatChieu;
	private JComboBox<String> txtPhim;

	private JComboBox<String> txtPhongChieu;
	private JTextField txtDinhDang;
	private JTextField txtGiaVe;
	private JMenuItem menuItem_27;
	private SuatChieu_DAO suatChieu_DAO;
	private JTextField txtGioChieu;
	private JTextField txtNgayChieu;

	public QuanLySuatChieu_GUI() {
		super("VXD Cinema - Quản lý suất chiếu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIcon icon = new ImageIcon("anh\\logo.png");
		this.setIconImage(icon.getImage());
		add(createMenuBar(), BorderLayout.NORTH);
		suatChieu_DAO = new SuatChieu_DAO();
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

		TraiPanel.add(Box.createVerticalStrut(50));

		JLabel jLabel_Title = new JLabel("NHẬP THÔNG TIN");
		jLabel_Title.setFont(new Font("Times new Rowman", Font.BOLD, 20));
		jLabel_Title.setForeground(Color.black);
		JPanel jLabel_panel = new JPanel();
		jLabel_panel.setOpaque(false);
		jLabel_panel.add(jLabel_Title);
		TraiPanel.add(jLabel_panel);

		TraiPanel.add(createInputPanel()); // Thêm phần nhập thông tin
		TraiPanel.add(Box.createVerticalStrut(40));
		TraiPanel.add(createButtonPanel()); // Thêm phần các chức năng
		TraiPanel.add(Box.createVerticalStrut(160));

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
        Phim_DAO phimDao = new Phim_DAO();
        PhongChieu_DAO phongChieuDao = new PhongChieu_DAO();
		Box panel4 = Box.createVerticalBox();
		panel4.add(Box.createHorizontalStrut(400));
		panel4.setOpaque(false);
		panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Thông tin suất chiếu", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 18), Color.black));

		panel4.add(createLabeledInputBox("Mã suất chiếu:  ", txtMaSuatChieu = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Phim:   ", txtPhim = createComboBoxPhim(phimDao))); 
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Ngày chiếu: ", txtNgayChieu =new JTextField(20))); 
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Giờ chiếu: ", txtGioChieu =new JTextField(20))); 
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Phòng chiếu: ", txtPhongChieu = createComboBoxPhongChieu(phongChieuDao))); 
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Định dạng: ", txtDinhDang = new JTextField(20)));
		panel4.add(Box.createVerticalStrut(5));
		panel4.add(createLabeledInputBox("Giá vé: ", txtGiaVe = new JTextField(20)));

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
		ChucNang.add(btnThem = new JButton("Thêm suất chiếu"));
		ChucNang.add(Box.createHorizontalStrut(10));
		ChucNang.add(btnXoa = new JButton("Xóa suất chiếu"));

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

		JLabel jLabel_Title1 = new JLabel("QUẢN LÝ SUẤT CHIẾU ");
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
				"Danh sách suất chiếu", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 20), Color.black));
		box5.setOpaque(false);

		String[] columns = { "Mã suất chiếu", "Phim", "Phòng chiếu", "Định dạng", "Giá vé", "Ngày chiếu","Giờ chiếu" };

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
		new QuanLySuatChieu_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themSuatChieu();
		}
		if (o.equals(btnXoa)) {
			xoaSuatChieu();
		}
		if (o.equals(btnSua)) {
			suaSuatChieu();
		}
		if (o.equals(btLamMoi)) {
			clearField();
		}
		if (o.equals(btnTim)) {
			timSuatChieu();
		}

		if (o.equals(menuItem_3)) {
			this.dispose();
			new ThongKeDoanhSo_GUI();
		}
		if (o.equals(menuItem_4)) {
			this.dispose();
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

	private void themSuatChieu() {
	    try {
	        String maSuatChieu = txtMaSuatChieu.getText();
	        String maPhim = (String) txtPhim.getSelectedItem(); // Adjust if using JComboBox
	        LocalDate ngayChieu = LocalDate.parse(txtNgayChieu.getText()); // Ensure correct format
	        LocalTime GioChieu = LocalTime.parse(txtGioChieu.getText()); // Ensure correct format
	        String maPhong = (String) txtPhongChieu.getSelectedItem(); // Adjust if using JComboBox
	        String dinhDang = txtDinhDang.getText();
	        double giaVe = Double.parseDouble(txtGiaVe.getText());

	        // Check for duplicate show code
	        if (suatChieu_DAO.timSuatChieu(maSuatChieu) !=null) {
	            JOptionPane.showMessageDialog(this, "Mã suất chiếu đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Create SuatChieu object and add it
	        SuatChieu suatChieu = new SuatChieu(maSuatChieu, new Phim(maPhim), ngayChieu, GioChieu, new PhongChieu(maPhong),
	                dinhDang, giaVe);

	        suatChieu_DAO.themSuatChieu(suatChieu); // Call DAO method to add show

	        Object[] rowData = { suatChieu.getMaSuatChieu(), maPhim, maPhong, dinhDang, giaVe, ngayChieu, GioChieu  };
	        modeltable.addRow(rowData);
	        JOptionPane.showMessageDialog(this, "Suat chiếu đã được thêm!");
	        clearField(); // Clear input fields after adding
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Thêm suất chiếu thất bại: " + e.getMessage(), "Lỗi",
	                JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void xoaSuatChieu() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String maSuatChieu = (String) modeltable.getValueAt(selectedRow, 0);
			try {
				suatChieu_DAO.xoaSuatChieu(maSuatChieu);
				modeltable.removeRow(selectedRow);
				JOptionPane.showMessageDialog(this, "Suất chiếu đã được xóa!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Xóa suất chiếu thất bại: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn một suất chiếu để xóa.");
		}
	}

	private void suaSuatChieu() {
	    String maSuatChieu = JOptionPane.showInputDialog(this, "Nhập mã suất chiếu cần sửa:");
        Phim_DAO phimDao= new Phim_DAO();
        PhongChieu_DAO phongChieuDao= new PhongChieu_DAO();
	    if (maSuatChieu != null && !maSuatChieu.trim().isEmpty()) {
	        try {
	            SuatChieu suatChieu = suatChieu_DAO.timSuatChieu(maSuatChieu);
	            for (int i = 0; i < modeltable.getRowCount(); i++) {
					if (modeltable.getValueAt(i, 0).equals(maSuatChieu)) {
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
					}
				}
	            if (suatChieu != null) {
	                JPanel panel = new JPanel(new GridLayout(0, 2));
	                
	                // Tạo JComboBox cho Phim và chọn Phim hiện tại
	                JComboBox<String> comboBoxPhim = createComboBoxPhim(phimDao);
	                comboBoxPhim.setSelectedItem(suatChieu.getPhim().getMaPhim()); 

	                // Tạo JComboBox cho PhongChieu và chọn PhongChieu hiện tại
	                JComboBox<String> comboBoxPhongChieu = createComboBoxPhongChieu(phongChieuDao);
	                comboBoxPhongChieu.setSelectedItem(suatChieu.getPhongChieu().getMaPhong());

	                JTextField txtNgayChieu = new JTextField(suatChieu.getngayChieu().toString());
	                JTextField txtGioChieu = new JTextField(suatChieu.getGioChieu().toString());
	                JTextField txtDinhDang = new JTextField(suatChieu.getDinhDang());
	                JTextField txtGiaVe = new JTextField(String.valueOf(suatChieu.getGiaVe()));

	                panel.add(new JLabel("Phim:"));
	                panel.add(comboBoxPhim); // Sử dụng JComboBox cho Phim
	                panel.add(new JLabel("Ngày chiếu:"));
	                panel.add(txtNgayChieu);
	                panel.add(new JLabel("Giờ chiếu:"));
	                panel.add(txtGioChieu);
	                panel.add(new JLabel("Phòng chiếu:"));
	                panel.add(comboBoxPhongChieu); // Sử dụng JComboBox cho PhongChieu
	                panel.add(new JLabel("Định dạng:"));
	                panel.add(txtDinhDang);
	                panel.add(new JLabel("Giá vé:"));
	                panel.add(txtGiaVe);

	                int result = JOptionPane.showConfirmDialog(this, panel, "Sửa thông tin suất chiếu", JOptionPane.OK_CANCEL_OPTION);

	                if (result == JOptionPane.OK_OPTION) {
	                
	                    SuatChieu updatedSuatChieu = new SuatChieu(
	                        maSuatChieu,
	                        new Phim((String) comboBoxPhim.getSelectedItem()), 
	                        LocalDate.parse(txtNgayChieu.getText()),
	                        LocalTime.parse(txtGioChieu.getText()),
	                        new PhongChieu((String) comboBoxPhongChieu.getSelectedItem()), 
	                        txtDinhDang.getText(),
	                        Double.parseDouble(txtGiaVe.getText())
	                    );

	                    suatChieu_DAO.capNhatSuatChieu(updatedSuatChieu);

	                    int selectedRow = table.getSelectedRow();
	                    modeltable.setValueAt(comboBoxPhim.getSelectedItem(), selectedRow, 1);
	                    modeltable.setValueAt(comboBoxPhongChieu.getSelectedItem(), selectedRow, 2);
	                    modeltable.setValueAt(txtDinhDang.getText(), selectedRow, 3);
	                    modeltable.setValueAt(Double.parseDouble(txtGiaVe.getText()), selectedRow, 4);
	                    modeltable.setValueAt(txtNgayChieu.getText(), selectedRow, 5);
	                    modeltable.setValueAt(txtGioChieu.getText(), selectedRow, 6);
	                    
	                    JOptionPane.showMessageDialog(this, "Thông tin suất chiếu đã được cập nhật!");
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Không tìm thấy suất chiếu với mã: " + maSuatChieu, "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Cập nhật suất chiếu thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Mã suất chiếu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void timSuatChieu() {
		String maSuatChieu = JOptionPane.showInputDialog(this, "Nhập mã suất chiếu cần tìm:");

		if (maSuatChieu == null || maSuatChieu.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Mã suất chiếu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			SuatChieu suatChieu = suatChieu_DAO.timSuatChieu(maSuatChieu);

			if (suatChieu != null) {
				txtMaSuatChieu.setText(suatChieu.getMaSuatChieu());
				txtPhim.setSelectedItem((suatChieu.getPhim().getMaPhim()));
				txtNgayChieu.setText(suatChieu.getngayChieu().toString());
				txtGioChieu.setText(suatChieu.getGioChieu().toString());
				txtPhongChieu.setSelectedItem((suatChieu.getPhongChieu().getMaPhong()));
				txtDinhDang.setText(suatChieu.getDinhDang());
				txtGiaVe.setText(String.valueOf(suatChieu.getGiaVe()));

				for (int i = 0; i < modeltable.getRowCount(); i++) {
					if (modeltable.getValueAt(i, 0).equals(maSuatChieu)) {
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
						JOptionPane.showMessageDialog(this, "Tìm thấy suất chiếu!");
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy suất chiếu trên bảng!");
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy suất chiếu với mã: " + maSuatChieu);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tìm suất chiếu thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadDataToTable() {
		try {
			ArrayList<SuatChieu> danhSachSuatChieu = (ArrayList<SuatChieu>) suatChieu_DAO.layDanhSachSuatChieu();
			modeltable.setRowCount(0); // Clear existing rows

			for (SuatChieu suatChieu : danhSachSuatChieu) {
				Object[] rowData = { suatChieu.getMaSuatChieu(), suatChieu.getPhim().getMaPhim(), suatChieu.getPhongChieu().getMaPhong(), suatChieu.getDinhDang(),
						suatChieu.getGiaVe(),
						suatChieu.getngayChieu(),suatChieu.getGioChieu() };
				modeltable.addRow(rowData);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearField() {
		txtMaSuatChieu.setText("");
		txtPhim.setSelectedIndex(-1); 
		txtNgayChieu.setText("");
		txtGioChieu.setText("");
		txtPhongChieu.setSelectedIndex(-1); 
		txtDinhDang.setText("");
		txtGiaVe.setText("");
	}
	 private JComboBox<String> createComboBoxPhim(Phim_DAO phimDao) {
	        JComboBox<String> comboBoxPhim = new JComboBox<>();
	        try {
	            ArrayList<Phim> danhSachPhim = phimDao.layDanhSachPhim();
	            for (Phim phim : danhSachPhim) {
	                comboBoxPhim.addItem(phim.getMaPhim()); // Thêm tên phim vào JComboBox
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return comboBoxPhim; // Trả về JComboBox cho phim
	    }
		private JComboBox<String> createComboBoxPhongChieu(PhongChieu_DAO phongChieuDao) {
	        JComboBox<String> comboBoxPhongChieu = new JComboBox<>();
	        try {
	            ArrayList<PhongChieu> danhSachPhongChieu = phongChieuDao.layDanhSachPhongChieu();
	            for (PhongChieu phongChieu : danhSachPhongChieu) {
	                comboBoxPhongChieu.addItem(phongChieu.getMaPhong()); // Thêm mã phòng vào JComboBox
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return comboBoxPhongChieu; // Trả về JComboBox cho phòng chiếu
	    }
}
