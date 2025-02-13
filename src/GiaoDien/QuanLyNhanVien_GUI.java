package GiaoDien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Dao.NhanVien_DAO;
import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import quanLy.*;
import lop.*;

public class QuanLyNhanVien_GUI extends JFrame implements ActionListener {
	private String[] chucVu1 = { "Chọn công việc", "Phục vụ", "Thu ngân", "Lao công" };
	private JTable table;
	private JTextField txtHoTen;
	private JTextField txtNgaySinh;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtDiaChi;

	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btLamMoi;
	private JButton btnTim;
	private JTextField txtMaNV;
	private JTextField txtCaLamViec;
	private JTextField txtNgayVaoLam;
	private JComboBox<String> txtCongViec;
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
	private DefaultTableModel modelTable;
	private JMenuItem menuItem_27;
	private NhanVien_DAO nhanVienDAO;

	public QuanLyNhanVien_GUI() {
	    super("VXD Cinema - Quản lý nhân viên");
	    nhanVienDAO = new NhanVien_DAO();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1920, 1080);
	    setLocationRelativeTo(null);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		TraiPanel.add(Box.createVerticalStrut(100));

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
	    panel1.add(TraiPanel);
	    panel1.setBorder(BorderFactory.createLineBorder(Color.black,2));
	    return panel1;
	}

	// Panel nhập thông tin
	private Box createInputPanel() {
	    Box panel4 = Box.createVerticalBox();
	    panel4.setOpaque(false);
	    panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
	            "Thông tin nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
	            new Font("Arial", Font.BOLD, 18), Color.black));

	    panel4.add(createLabeledInputBox("Mã nhân viên:  ", txtMaNV = new JTextField(20)));
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Tên nhân viên:   ", txtHoTen = new JTextField(20)));
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Ngày sinh: ", txtNgaySinh = new JTextField(20)));
	    addPlaceholder(txtNgaySinh, "yyyy-MM-dd");
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Ngày vào làm: ", txtNgayVaoLam = new JTextField(20)));
	    addPlaceholder(txtNgayVaoLam, "yyyy-MM-dd");
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Email: ", txtEmail = new JTextField(20)));
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Số điện thoại: ", txtSDT = new JTextField(20)));
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Công việc: ", txtCongViec = new JComboBox<>(chucVu1)));
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Địa chỉ: ", txtDiaChi = new JTextField(20)));
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(createLabeledInputBox("Ca làm việc: ", txtCaLamViec = new JTextField(20)));

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
	    lbl.setPreferredSize(new Dimension(125, 25));
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
	    ChucNang.add(btnThem = new JButton("Thêm nhân viên"));
	    ChucNang.add(Box.createHorizontalStrut(10));
	    ChucNang.add(btnXoa = new JButton("Xóa nhân viên"));

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

	    JLabel jLabel_Title1 = new JLabel("QUẢN LÝ NHÂN VIÊN");
	    jLabel_Title1.setFont(new Font("Times new Rowman", Font.BOLD, 30));
	    jLabel_Title1.setForeground(Color.black);
	    Box jLabel_panel = Box.createHorizontalBox();
	    jLabel_panel.setOpaque(false);
	    jLabel_panel.add(jLabel_Title1);
	    jLabel_panel.add(Box.createHorizontalStrut(100));
	    PhaiPanel.add(jLabel_panel);

	    PhaiPanel.add(createTablePanel());
	    PhaiPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));

	    return PhaiPanel;
	}

	// Panel hiển thị bảng nhân viên
	private Box createTablePanel() {
	    Box box5 = Box.createVerticalBox();
	    box5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
	            "Danh sách nhân viên", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
	            new Font("Arial", Font.BOLD, 20), Color.black));
	    box5.setOpaque(false);

	    String[] columns = { "Mã nhân viên", "Tên nhân viên", "Ngày Sinh", "Ngày vào làm", "Email", "Số điện thoại", "Công việc", "Địa chỉ", "Ca Làm Việc" };
	    modelTable = new DefaultTableModel(columns, 0);
	    table = new JTable(modelTable);
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
			 setBorder(BorderFactory.createLineBorder(Color.black, 3));														// ảnh của bạn
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
		new QuanLyNhanVien_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
		    themNhanVien();
		}
		if (o.equals(btnXoa)) {
            xoaNhanVien();
		}
		if (o.equals(btnSua)) {
            suaNhanVien();
		}
		if (o.equals(btLamMoi)) {
			clearField();
		}
		if (o.equals(btnTim)) {
			searchEmployee();
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
		if(o.equals(menuItem_2)) {
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

	}

	private void themNhanVien() {
	    // Retrieve input field data
	    String maNV = txtMaNV.getText().trim();
	    String hoTen = txtHoTen.getText().trim();
	    String diaChi = txtDiaChi.getText().trim();
	    String ngaySinh = txtNgaySinh.getText().trim();
	    String ngayVaoLam = txtNgayVaoLam.getText().trim();
	    String email = txtEmail.getText().trim();
	    String sdt = txtSDT.getText().trim();
	    String congViec = (String) txtCongViec.getSelectedItem();
	    String caLamViec = txtCaLamViec.getText().trim();

	    // Validate required fields
	    if (maNV.isEmpty() || hoTen.isEmpty() || diaChi.isEmpty() || ngaySinh.isEmpty() || 
	        ngayVaoLam.isEmpty() || email.isEmpty() || sdt.isEmpty() || 
	        congViec == null || caLamViec.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Validate Email
	    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    if (!email.matches(emailRegex)) {
	        JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Lỗi", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Validate Phone Number
	    String phoneRegex = "^\\d{10,11}$";
	    if (!sdt.matches(phoneRegex)) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! (10-11 chữ số)", "Lỗi", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Validate Dates (yyyy-MM-dd)
	    String dateRegex = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
	    if (!ngaySinh.matches(dateRegex)) {
	        JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ! (yyyy-MM-dd)", "Lỗi", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    if (!ngayVaoLam.matches(dateRegex)) {
	        JOptionPane.showMessageDialog(this, "Ngày vào làm không hợp lệ! (yyyy-MM-dd)", "Lỗi", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Check for duplicate employee ID
	    try {
	        if (nhanVienDAO.timNhanVien(maNV) != null) { // Method to check for duplicate ID
	            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!", "Lỗi", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra mã nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Create a new NhanVien object with validated input data
	    NhanVien nhanVien = new NhanVien(maNV, hoTen, diaChi, ngaySinh, ngayVaoLam, email, sdt, congViec, caLamViec);

	    // Add to SQL database using DAO method
	    try {
	        nhanVienDAO.themNhanVien(nhanVien); // Adjusted to use the correct method name
	        // Add new row to JTable
	        Object[] rowData = {
	            nhanVien.getMaNV(),
	            nhanVien.getHoTen(),
	            nhanVien.getDiaChi(),
	            nhanVien.getNgaySinh(),
	            nhanVien.getNgayVaoLam(),
	            nhanVien.getEmail(),
	            nhanVien.getSdt(),
	            nhanVien.getCongViec(),
	            nhanVien.getCaLamViec()
	        };
	        modelTable.addRow(rowData);
	        JOptionPane.showMessageDialog(this, "Nhân viên đã được thêm!");
	        clearField();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	}


	private void xoaNhanVien() {
	    // Get selected row in JTable
	    int selectedRow = table.getSelectedRow();
	    if (selectedRow != -1) {
	        String maNV = (String) modelTable.getValueAt(selectedRow, 0); // Retrieve the employee ID from the selected row
	        
	        // Delete from SQL database
	        try {
	            nhanVienDAO.xoaNhanVien(maNV); // Adjusted to use the correct method name
	            // Remove row from JTable
	            modelTable.removeRow(selectedRow);
	            JOptionPane.showMessageDialog(this, "Nhân viên đã được xóa!");
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Hãy chọn một nhân viên để xóa.");
	    }
	}

//	private void suaNhanVien() {
//	    // Get selected row in JTable
//	    int selectedRow = table.getSelectedRow();
//	    if (selectedRow != -1) {
//	        // Create updated NhanVien object with input field data
//	        NhanVien nhanVien = new NhanVien(
//	            txtMaNV.getText(),
//	            txtHoTen.getText(),
//	            txtDiaChi.getText(),
//	            txtNgaySinh.getText(),
//	            txtNgayVaoLam.getText(),
//	            txtEmail.getText(),
//	            txtSDT.getText(),
//	            (String) txtCongViec.getSelectedItem(),
//	            txtCaLamViec.getText()
//	        );
//
//	        // Update in SQL database
//	        try {
//	            nhanVienDAO.capNhatNhanVien(nhanVien); // Adjusted to use the correct method name
//	            // Update JTable row
//	            modelTable.setValueAt(nhanVien.getHoTen(), selectedRow, 1);
//	            modelTable.setValueAt(nhanVien.getDiaChi(), selectedRow, 2);
//	            modelTable.setValueAt(nhanVien.getNgaySinh(), selectedRow, 3);
//	            modelTable.setValueAt(nhanVien.getNgayVaoLam(), selectedRow, 4);
//	            modelTable.setValueAt(nhanVien.getEmail(), selectedRow, 5);
//	            modelTable.setValueAt(nhanVien.getSdt(), selectedRow, 6);
//	            modelTable.setValueAt(nhanVien.getCongViec(), selectedRow, 7);
//	            modelTable.setValueAt(nhanVien.getCaLamViec(), selectedRow, 8);
//
//	            JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được cập nhật!");
//	        } catch (Exception e) {
//	            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//	        }
//	    } else {
//	        JOptionPane.showMessageDialog(this, "Hãy chọn một nhân viên để sửa.");
//	    }
//	}
	private void suaNhanVien() {
	    // Yêu cầu người dùng nhập mã nhân viên
	    String maNV = JOptionPane.showInputDialog(this, "Nhập mã nhân viên cần sửa:");

	    if (maNV != null && !maNV.trim().isEmpty()) {
	        try {
	            // Tìm nhân viên trong cơ sở dữ liệu
	            NhanVien nhanVien = nhanVienDAO.timNhanVien(maNV);

	            if (nhanVien != null) {
	                // Tạo một panel để chứa các trường nhập liệu
	                JPanel panel = new JPanel(new GridLayout(0, 2));
	                
	                // Tạo các trường nhập liệu và điền thông tin hiện tại
	                JTextField txtHoTen = new JTextField(nhanVien.getHoTen());
	                JTextField txtDiaChi = new JTextField(nhanVien.getDiaChi());
	                JTextField txtNgaySinh = new JTextField(nhanVien.getNgaySinh());
	                JTextField txtNgayVaoLam = new JTextField(nhanVien.getNgayVaoLam());
	                JTextField txtEmail = new JTextField(nhanVien.getEmail());
	                JTextField txtSDT = new JTextField(nhanVien.getSdt());
	                JComboBox<String> txtCongViec = new JComboBox<>(chucVu1); // chucVu1 là danh sách công việc
	                txtCongViec.setSelectedItem(nhanVien.getCongViec());
	                JTextField txtCaLamViec = new JTextField(nhanVien.getCaLamViec());

	                // Thêm các trường vào panel
	                panel.add(new JLabel("Họ tên:"));
	                panel.add(txtHoTen);
	                panel.add(new JLabel("Địa chỉ:"));
	                panel.add(txtDiaChi);
	                panel.add(new JLabel("Ngày sinh:"));
	                panel.add(txtNgaySinh);
	                panel.add(new JLabel("Ngày vào làm:"));
	                panel.add(txtNgayVaoLam);
	                panel.add(new JLabel("Email:"));
	                panel.add(txtEmail);
	                panel.add(new JLabel("Số điện thoại:"));
	                panel.add(txtSDT);
	                panel.add(new JLabel("Công việc:"));
	                panel.add(txtCongViec);
	                panel.add(new JLabel("Ca làm việc:"));
	                panel.add(txtCaLamViec);

	                // Hiển thị hộp thoại nhập thông tin mới
	                int result = JOptionPane.showConfirmDialog(this, panel, "Sửa thông tin nhân viên", JOptionPane.OK_CANCEL_OPTION);
	                
	                // Kiểm tra nếu người dùng nhấn OK
	                if (result == JOptionPane.OK_OPTION) {
	                    // Tạo đối tượng NhanVien mới với thông tin đã nhập
	                    NhanVien updatedNhanVien = new NhanVien(
	                        maNV, // Mã nhân viên không thay đổi
	                        txtHoTen.getText(),
	                        txtDiaChi.getText(),
	                        txtNgaySinh.getText(),
	                        txtNgayVaoLam.getText(),
	                        txtEmail.getText(),
	                        txtSDT.getText(),
	                        (String) txtCongViec.getSelectedItem(),
	                        txtCaLamViec.getText()
	                    );

	                    // Cập nhật thông tin trong cơ sở dữ liệu
	                    nhanVienDAO.capNhatNhanVien(updatedNhanVien);

	                    // Cập nhật trong JTable
	                    int selectedRow = table.getSelectedRow();
	                    modelTable.setValueAt(updatedNhanVien.getHoTen(), selectedRow, 1);
	                    modelTable.setValueAt(updatedNhanVien.getDiaChi(), selectedRow, 2);
	                    modelTable.setValueAt(updatedNhanVien.getNgaySinh(), selectedRow, 3);
	                    modelTable.setValueAt(updatedNhanVien.getNgayVaoLam(), selectedRow, 4);
	                    modelTable.setValueAt(updatedNhanVien.getEmail(), selectedRow, 5);
	                    modelTable.setValueAt(updatedNhanVien.getSdt(), selectedRow, 6);
	                    modelTable.setValueAt(updatedNhanVien.getCongViec(), selectedRow, 7);
	                    modelTable.setValueAt(updatedNhanVien.getCaLamViec(), selectedRow, 8);

	                    JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được cập nhật!");
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã: " + maNV, "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void searchEmployee() {
	    // Prompt user for employee ID
	    String maNV = JOptionPane.showInputDialog(this, "Nhập mã nhân viên cần tìm:");

	    // Check if employee ID is empty
	    if (maNV == null || maNV.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Call the search method with the provided employee ID
	    try {
	        NhanVien nhanVien = nhanVienDAO.timNhanVien(maNV); // Adjusted to use the correct method name
	        
	        if (nhanVien != null) {
	            // Set the form fields with employee data
	        	txtMaNV.setText(nhanVien.getMaNV());
	            txtHoTen.setText(nhanVien.getHoTen());
	            txtDiaChi.setText(nhanVien.getDiaChi());
	            txtNgaySinh.setText(nhanVien.getNgaySinh());
	            txtNgayVaoLam.setText(nhanVien.getNgayVaoLam());
	            txtEmail.setText(nhanVien.getEmail());
	            txtSDT.setText(nhanVien.getSdt());
	            txtCongViec.setSelectedItem(nhanVien.getCongViec());
	            txtCaLamViec.setText(nhanVien.getCaLamViec());

	            // Highlight and select the corresponding row in JTable
	            for (int i = 0; i < modelTable.getRowCount(); i++) {
	                if (modelTable.getValueAt(i, 0).equals(maNV)) {
	                    table.setRowSelectionInterval(i, i);  // Select the row
	                    table.scrollRectToVisible(table.getCellRect(i, 0, true));  // Scroll to row if needed
	                    JOptionPane.showMessageDialog(this, "Tìm thấy nhân viên!");
	                    return; // Employee found, exit the loop
	                }
	            }
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên trên bảng!");
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã: " + maNV);
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Tìm kiếm nhân viên thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void loadDataToTable() {
	    try {
	        ArrayList<NhanVien> danhSachNV = (ArrayList<NhanVien>) nhanVienDAO.layDanhSachNhanVien(); // Adjusted to use the correct method name
	        modelTable.setRowCount(0); // Clear existing data

	        for (NhanVien nv : danhSachNV) {
	            Object[] rowData = {
	                nv.getMaNV(), nv.getHoTen(), nv.getNgaySinh(),
	                nv.getNgayVaoLam(), nv.getEmail(), nv.getSdt(), nv.getCongViec(),nv.getDiaChi(), nv.getCaLamViec()
	            };
	            modelTable.addRow(rowData);
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Tải dữ liệu thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void clearField() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtNgaySinh.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtCaLamViec.setText("");
		txtCongViec.setSelectedIndex(0);
		txtNgayVaoLam.setText("");
	}
}
