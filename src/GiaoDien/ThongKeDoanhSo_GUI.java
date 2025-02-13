package GiaoDien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import quanLy.*;
import lop.*;

public class ThongKeDoanhSo_GUI extends JFrame implements ActionListener {
	private JPanel jLabel_panel;
	private JComboBox comboBoxNgay;
	private JComboBox comboBoxThang;
	private JComboBox comboBoxNam;
	private JLabel jLabel_Ngay;
	private JLabel jLabel_Thang;
	private JLabel jLabel_Nam;
	private JButton btnXoaTrang;
	private JCheckBox cbNgay;
	private JCheckBox cbThang;
	private JCheckBox cbNam;
	private JButton btnThongKe;
	private DefaultTableModel modelTable;
	private JTable table;
	private JTextField tongtext;
	private JButton btnThoat;
	private JMenuItem menuTrangChu;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_21;
	private JMenuItem menuItem_22;
	private JMenuItem menuItem_23;
	private JMenuItem menuItem_24;
	private JMenuItem menuItem_25;
	private JMenuItem menuItem_26;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private JMenuItem menuItem_5;
	private JMenuItem menuItem_27;

	public ThongKeDoanhSo_GUI() {
		super("VXD Cinema - Thống kê doanh thu");

		BackgroundPanel panel1 = new BackgroundPanel();

		Box TraiPanel = Box.createVerticalBox();

		TraiPanel.add(Box.createVerticalStrut(100));

		JLabel jLabel_Title;
		jLabel_Title = new JLabel("NHẬP THÔNG TIN");
		jLabel_Title.setFont(new Font("Times new Rowman", Font.BOLD, 20));
		jLabel_Title.setForeground(Color.black);
		jLabel_panel = new JPanel();

		jLabel_panel.setOpaque(false);
		jLabel_panel.add(jLabel_Title);
		TraiPanel.add(jLabel_panel);
		ImageIcon icon = new ImageIcon("anh\\logo.png");
		this.setIconImage(icon.getImage());

		panel1.add(TraiPanel);

		Box panel4 = Box.createVerticalBox();
		panel4.setOpaque(false); 
		panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Nhập ngày/tháng/năm", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 18), Color.black));
		String[] arrNgay = new String[32];
		String[] arrThang = new String[13];
		String[] arrNam = new String[11];
		arrNgay[0] = "";
		arrThang[0] = "";
		arrNam[0] = "";
		//
		for (int i = 1; i < 32; i++) {
			arrNgay[i] = String.valueOf(i);
		}

		for (int i = 1; i < 13; i++) {
			arrThang[i] = String.valueOf(i);
		}

		int currentYear = java.time.Year.now().getValue();
		for (int i = 1; i < 11; i++) {
			arrNam[i] = String.valueOf(currentYear - (i - 1));
		}

		comboBoxNgay = new JComboBox<>(arrNgay);
		comboBoxThang = new JComboBox<>(arrThang);
		comboBoxNam = new JComboBox<>(arrNam);
		//
		Box box1 = new Box(BoxLayout.Y_AXIS);

		Box box11 = new Box(BoxLayout.X_AXIS);
		jLabel_Ngay = new JLabel("Ngày:   ");
		jLabel_Ngay.setFont(new Font("Arial", Font.PLAIN, 16));
		jLabel_Ngay.setForeground(Color.black);
		box11.add(jLabel_Ngay);
		box11.add(Box.createHorizontalStrut(8));
		box11.add(comboBoxNgay);
		box1.add(box11);
		box1.add(Box.createVerticalStrut(15));

		Box box12 = new Box(BoxLayout.X_AXIS);
		box12.add(jLabel_Thang = new JLabel("Tháng:   "));
		jLabel_Thang.setFont(new Font("Arial", Font.PLAIN, 16));
		jLabel_Thang.setForeground(Color.black);
		box12.add(comboBoxThang);
		box1.add(box12);

		box1.add(Box.createVerticalStrut(15));
		Box box13 = new Box(BoxLayout.X_AXIS);
		jLabel_Nam = new JLabel("Năm:   ");
		box13.add(jLabel_Nam);
		box13.add(Box.createHorizontalStrut(11));
		jLabel_Nam.setFont(new Font("Arial", Font.PLAIN, 16));
		jLabel_Nam.setForeground(Color.black);
		box13.add(comboBoxNam);
		box1.add(box13);
		box1.add(Box.createVerticalStrut(15));
		panel4.add(Box.createVerticalStrut(10));
		panel4.add(box1);
		panel4.add(Box.createVerticalStrut(10));
		TraiPanel.add(Box.createVerticalStrut(20));
		TraiPanel.add(panel4);

		Box panel5 = Box.createVerticalBox();
		panel5.setOpaque(false); // Bật chế độ hiển thị nền
		panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Chọn phương thức thống kê", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 18), Color.black));
		Box box2 = new Box(BoxLayout.X_AXIS);
		box2.add(cbNgay = new JCheckBox("Thống kê theo ngày"));
		cbNgay.setFont(new Font("Arial", Font.PLAIN, 13));
		box2.add(Box.createHorizontalStrut(10));
		box2.add(cbThang = new JCheckBox("Thống kê theo tháng"));
		cbThang.setFont(new Font("Arial", Font.PLAIN, 13));
		box2.add(Box.createHorizontalStrut(10));
		box2.add(cbNam = new JCheckBox("Thống kê theo năm"));
		cbNam.setFont(new Font("Arial", Font.PLAIN, 13));
		panel5.add(Box.createVerticalStrut(10));
		panel5.add(box2);
		panel5.add(Box.createVerticalStrut(20));
		TraiPanel.add(Box.createVerticalStrut(10));
		TraiPanel.add(panel5);

		Box panel6 = Box.createHorizontalBox();
		panel6.setOpaque(false);
		JPanel XoaTrang = new JPanel();
		XoaTrang.setOpaque(false);
		XoaTrang.add(btnXoaTrang = new JButton("Refresh"));
		panel6.add(XoaTrang);
		btnXoaTrang.setIcon(new ImageIcon("anh\\refresh-icon-24x24.png"));

		JPanel thongke = new JPanel();
		thongke.setOpaque(false);
		btnThongKe = new JButton("Thống kê");
		btnThongKe.setIcon(new ImageIcon("anh\\findpage.png"));
		thongke.add(btnThongKe);
		panel6.add(thongke);

		TraiPanel.add(Box.createVerticalStrut(40));
		TraiPanel.add(panel6);
		TraiPanel.add(Box.createVerticalStrut(120));

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

		//////

		// Tạo thanh menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black,2));
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
        //Menu Trang Chủ
		menuTrangChu= new JMenuItem("Trang Chủ");
		menuTrangChu.setOpaque(false);
		menuTrangChu.setContentAreaFilled(false);
		ImageIcon trangchuicon= new ImageIcon("anh\\icons8-home-24.png");
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
		ImageIcon qlNVgocIcon= new ImageIcon("anh\\icons8-manage-30.png");
		
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
		ImageIcon tkgocIcon= new ImageIcon("anh\\Food-List-Ingredients-icon.png");
		menuThongKe.setIcon(tkgocIcon);
		menuThongKe.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuItem_3 = new JMenuItem("Doanh thu");
		
	    ImageIcon doanhthugoc=	new ImageIcon("anh\\increase.png");
		menuItem_3.setIcon(doanhthugoc);
		menuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuItem_3.addActionListener(this);

		menuItem_4 = new JMenuItem("Phim");
		ImageIcon cuonphim=new ImageIcon("anh\\icons8-film-24.png");
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
		ImageIcon trogiupgocIcon= new ImageIcon("anh\\help-circle-icon.png");
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
		add(menuBar, BorderLayout.NORTH);
		////

		// Nội dung khung bên phải
		BackgroundPhaiPanel PhaiPanel = new BackgroundPhaiPanel();
		PhaiPanel.setLayout(new BoxLayout(PhaiPanel, BoxLayout.Y_AXIS));
		PhaiPanel.add(Box.createVerticalStrut(30));
		JLabel jLabel_Title1 = new JLabel("QUẢN LÝ DOANH THU ");
		jLabel_Title1.setFont(new Font("Times new Rowman", Font.BOLD, 30));
		jLabel_Title1.setForeground(Color.black);
		Box jLabel_panel = Box.createHorizontalBox();
		jLabel_panel.setOpaque(false);
		jLabel_panel.add(jLabel_Title1);
		jLabel_panel.add(Box.createHorizontalStrut(170));
		PhaiPanel.add(jLabel_panel);

		Box box5 = new Box(BoxLayout.Y_AXIS);
		box5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)),
				"", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 20),
				Color.black));

		box5.setOpaque(false);
		box5.add(Box.createVerticalStrut(20));

		JPanel tablePanel = createProductAndTicketTablePanel();
		box5.add(tablePanel);
		
		PhaiPanel.add(box5);

		JPanel tongpanel = new JPanel(new FlowLayout());
		tongpanel.setOpaque(false);
		tongpanel.add(Box.createHorizontalStrut(410));
		JLabel tongJLabel = new JLabel("Tổng doanh thu:");
		Font font = new Font("Times new Roman", Font.BOLD, 20);
		tongJLabel.setFont(font);
		tongpanel.add(tongJLabel);
		tongtext = new JTextField();
		tongtext.setPreferredSize(new Dimension(500, 30));
		tongtext.setEditable(false);
		tongpanel.add(tongtext);
		box5.add(tongpanel);
		////

		btnThongKe.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		add(PhaiPanel, BorderLayout.CENTER);
		add(panel1, BorderLayout.WEST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
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
	        productTable.setFont(new Font("Arial", Font.PLAIN, 15));
	        productTable.setRowHeight(25);
	        productTable.getTableHeader().setFont(new Font("Times new roman", Font.BOLD, 15));
	        JScrollPane productScrollPane = new JScrollPane(productTable);
	        productScrollPane.setPreferredSize(new Dimension(600, 150)); 
	        productScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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
	        summaryPanel.add(new JLabel("Tổng Doanh Thu Thực Phẩm: " + String.format("%,d VNĐ", totalRevenue)));

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
	        ticketTable.setFont(new Font("Arial", Font.PLAIN, 15));
	        JScrollPane ticketScrollPane = new JScrollPane(ticketTable);
	        ticketScrollPane.setPreferredSize(new Dimension(600, 150)); 
	        tablePanel.add(ticketScrollPane, BorderLayout.CENTER);
	        ticketTable.setRowHeight(25);
	        ticketTable.getTableHeader().setFont(new Font("Times new roman", Font.BOLD, 15));
	        ticketScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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
	        ticketSummaryPanel.add(new JLabel("Tổng Doanh Thu Vé: " + String.format("%,d VNĐ", totalRevenue)));

	        // Thêm phần tổng kết vào panel chính
	        tablePanel.add(ticketSummaryPanel, BorderLayout.SOUTH);

	        return tablePanel;
	    }

	private ImageIcon scaleIcon(ImageIcon icon, double scale) {
		Image image = icon.getImage();
		int width = (int) (image.getWidth(null) * scale);
		int height = (int) (image.getHeight(null) * scale);
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
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

	private Dimension getSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	class BackgroundPhaiPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPhaiPanel() {
			// Tải hình ảnh
			backgroundImage = new ImageIcon("anh\\NenPhai.png").getImage();
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

	class BackgroundPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPanel() {
			// Tải hình ảnh
			backgroundImage = new ImageIcon("anh\\NenTrai.png").getImage(); 
																			
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
		new ThongKeDoanhSo_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();


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
		if(o.equals(menuItem_2)) {
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
		if(o.equals(menuItem_23)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyPhim_GUI();
		}
		if(o.equals(menuItem_24)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLySuatChieu_GUI();
		}
		if(o.equals(menuItem_25)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyTaiKhoan_GUI();
		}
		if(o.equals(menuItem_26)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyThucPham_GUI();
		}
		if(o.equals(menuItem_5)) {
			this.dispose();
			new TroGiup_GUI();
		}
		if(o.equals(menuTrangChu)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new TrangChuQuanLY_GUI();
		}
		if(o.equals(btnThoat)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new DangNhap_GUI();
		}
		if(o.equals(menuItem_27)) {
			this.dispose();
			ConnectDB.getInstance().connect();
			new QuanLyPhongChieu_GUI();
		}
		if (o.equals(btnThongKe)) {
//			 // Xóa dữ liệu hiện có trong bảng
//            modelTable.setRowCount(0);
//
//            // Kiểm tra các checkbox đã được chọn
//            boolean isNgaySelected = cbNgay.isSelected();
//            boolean isThangSelected = cbThang.isSelected();
//            boolean isNamSelected = cbNam.isSelected();
//
//            // Nếu không có checkbox nào được chọn, không thực hiện thống kê
//            if (!isNgaySelected && !isThangSelected && !isNamSelected) {
//                JOptionPane.showMessageDialog(this,
//                        "Vui lòng chọn ít nhất một tiêu chí thống kê.");
//                return;
//            }
//
//            // Lấy giá trị từ các combobox
//            String selectedDay = comboBoxNgay.getSelectedItem().toString();
//            String selectedMonth = comboBoxThang.getSelectedItem().toString();
//            String selectedYear = comboBoxNam.getSelectedItem().toString();
//
//            List<HoaDon> filteredList = new ArrayList<>();
//            double totalRevenue = 0.0;
//
//            // Lọc danh sách hóa đơn và tính tổng doanh thu theo tiêu chí đã chọn
//            if (isNgaySelected && isThangSelected && isNamSelected) {
//                filteredList = filterByDayMonthYear(selectedDay, selectedMonth, selectedYear);
//                totalRevenue = calculateTotalRevenue(filteredList);
//            } else if (isNgaySelected && isThangSelected) {
//                filteredList = filterByDayMonth(selectedDay, selectedMonth);
//                totalRevenue = calculateTotalRevenue(filteredList);
//            } else if (isNgaySelected && isNamSelected) {
//                filteredList = filterByDayYear(selectedDay, selectedYear);
//                totalRevenue = calculateTotalRevenue(filteredList);
//            } else if (isThangSelected && isNamSelected) {
//                filteredList = filterByMonthYear(selectedMonth, selectedYear);
//                totalRevenue = calculateTotalRevenue(filteredList);
//            } else if (isNgaySelected) {
//                filteredList = filterByDay(selectedDay);
//                totalRevenue = calculateTotalRevenue(filteredList);
//            } else if (isThangSelected) {
//                filteredList = filterByMonth(selectedMonth);
//                totalRevenue = calculateTotalRevenue(filteredList);
//            } else if (isNamSelected) {
//                filteredList = filterByYear(selectedYear);
//                totalRevenue = calculateTotalRevenue(filteredList);
//            }
//
//            // Hiển thị tổng doanh thu
//            updateTableWithFilteredData(filteredList);
//            
//            tongtext.setText(Double.toString(totalRevenue));
		}

	}
//	private List<HoaDon> filterByDayMonthYear(String day, String month, String year) {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//        List<HoaDon> filteredList = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        for (HoaDon hd : list) {
//            cal.setTime(hd.getNgayLapHD());
//            int dayOfHoaDon = cal.get(Calendar.DAY_OF_MONTH);
//            int monthOfHoaDon = cal.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
//            int yearOfHoaDon = cal.get(Calendar.YEAR);
//            if (String.valueOf(dayOfHoaDon).equals(day) && String.valueOf(monthOfHoaDon).equals(month)
//                    && String.valueOf(yearOfHoaDon).equals(year)) {
//                filteredList.add(hd);
//            }
//        }
//        return filteredList;
//    }
//
//    // Hàm lọc hóa đơn theo ngày, tháng
//    private List<HoaDon> filterByDayMonth(String day, String month) {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//        List<HoaDon> filteredList = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        for (HoaDon hd : list) {
//            cal.setTime(hd.getNgayLapHD());
//            int dayOfHoaDon = cal.get(Calendar.DAY_OF_MONTH);
//            int monthOfHoaDon = cal.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
//            if (String.valueOf(dayOfHoaDon).equals(day) && String.valueOf(monthOfHoaDon).equals(month)) {
//                filteredList.add(hd);
//            }
//        }
//        return filteredList;
//    }
//
//    // Hàm lọc hóa đơn theo ngày, năm
//    private List<HoaDon> filterByDayYear(String day, String year) {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//        List<HoaDon> filteredList = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        for (HoaDon hd : list) {
//            cal.setTime(hd.getNgayLapHD());
//            int dayOfHoaDon = cal.get(Calendar.DAY_OF_MONTH);
//            int yearOfHoaDon = cal.get(Calendar.YEAR);
//            if (String.valueOf(dayOfHoaDon).equals(day) && String.valueOf(yearOfHoaDon).equals(year)) {
//                filteredList.add(hd);
//            }
//        }
//        return filteredList;
//    }
//
//    // Hàm lọc hóa đơn theo tháng, năm
//    private List<HoaDon> filterByMonthYear(String month, String year) {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//        List<HoaDon> filteredList = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        for (HoaDon hd : list) {
//            cal.setTime(hd.getNgayLapHD());
//            int monthOfHoaDon = cal.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
//            int yearOfHoaDon = cal.get(Calendar.YEAR);
//            if (String.valueOf(monthOfHoaDon).equals(month) && String.valueOf(yearOfHoaDon).equals(year)) {
//                filteredList.add(hd);
//            }
//        }
//        return filteredList;
//    }
//
//    // Hàm lọc danh sách hóa đơn theo năm
//    private List<HoaDon> filterByYear(String year) {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//        List<HoaDon> filteredList = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        for (HoaDon hd : list) {
//            cal.setTime(hd.getNgayLapHD());
//            int yearOfHoaDon = cal.get(Calendar.YEAR);
//            if (String.valueOf(yearOfHoaDon).equals(year)) {
//                filteredList.add(hd);
//            }
//        }
//        return filteredList;
//    }
//
//    // Hàm lọc hóa đơn theo tháng
//    private List<HoaDon> filterByMonth(String month) {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//        List<HoaDon> filteredList2 = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        for (HoaDon hd : list) {
//            cal.setTime(hd.getNgayLapHD());
//            int monthOfHoaDon = cal.get(Calendar.MONTH) + 1;
//            if (String.valueOf(monthOfHoaDon).equals(month)) {
//                filteredList2.add(hd);
//            }
//        }
//        return filteredList2;
//    }
//
//    // Hàm lọc hóa đơn theo ngày
//    private List<HoaDon> filterByDay(String day) {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//        List<HoaDon> filteredList3 = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        for (HoaDon hd : list) {
//            cal.setTime(hd.getNgayLapHD());
//            int dayOfHoaDon = cal.get(Calendar.DAY_OF_MONTH);
//            if (String.valueOf(dayOfHoaDon).equals(day)) {
//                filteredList3.add(hd);
//            }
//        }
//        return filteredList3;
//    }

//    public void ReadDatabaseToTable() {
//        List<HoaDon> list = HD_dao.getAllHoaDon();
//
//        for (HoaDon hd : list) {
//            modelTable.addRow(new Object[] { hd.getMaHoaDon(), hd.getNhanVien().getHoTen(),
//                    hd.getKhachHang().getTenKH(), hd.getNgayLapHD(), hd.getTongThanhToan(), hd.getGioVao() });
//        }
//    }
	private void resetForm() {
		comboBoxNgay.setSelectedIndex(0);
		comboBoxThang.setSelectedIndex(0);
		comboBoxNam.setSelectedIndex(0);
		cbNgay.setSelected(false);
		cbThang.setSelected(false);
		cbNam.setSelected(false);
		modelTable.setRowCount(0);
//            ReadDatabaseToTable();

	}
}
