package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import connectDB.ConnectDB;

public class TrangSuatChieu_GUI extends JFrame implements ActionListener, MouseListener {
	private JMenuItem menuTrangChu;
	private JComponent menuNhanVien;
	private String maSuatChieu;

	public TrangSuatChieu_GUI() {
		// ---------------------------------------Tiêu
		// đề---------------------------------
		setTitle("Trang Theo Dõi Suất Chiếu");
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 678);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		// ---------------------------------------Tiêu
		// đề---------------------------------

		// Tạo panel chính
		JPanel mainPanel = new JPanel(new BorderLayout());

		// --------------------------Thêm thanh menu vào đầu trang--------------------
		ImageIcon icon = new ImageIcon("Anh/logo.png");
		this.setIconImage(icon.getImage());
		JMenuBar menuBar = createMenuBar();
		setJMenuBar(menuBar);
		// --------------------------Thêm thanh menu vào đầu trang--------------------

		// --------------------------- Panel tiêu đề---------------------------------
		JPanel panelTieuDe = new JPanel();
		panelTieuDe.setBackground(Color.white);
		panelTieuDe.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		JLabel tieuDe = new JLabel("Trang Theo Dõi Suất Chiếu");
		tieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		panelTieuDe.add(tieuDe);
		mainPanel.add(panelTieuDe, BorderLayout.NORTH);
		// --------------------------- Panel tiêu đề---------------------------------

		// -----------------------------Khung ngay chieu----------------------
		Box cPanel = Box.createVerticalBox();
		JPanel khungNgayChieu = taoKhungNgayChieu();
		cPanel.add(khungNgayChieu);

		// -----------------------------Khung ngay chieu----------------------

		// ------------------ Tạo panel chứa các panel thông tin phim với bố cục hàng ngang----------------------
		JPanel panelChuaThongTinPhim = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 40));
		panelChuaThongTinPhim.setBackground(Color.white);	
		String sql = "SELECT maPhim FROM Phim";
		try {
			ConnectDB db = ConnectDB.getInstance();
			db.connect();
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maPhim = rs.getString("maPhim");
				panelChuaThongTinPhim.add(taoPanelThongTinPhim(maPhim));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
		}
		
		
		// Đặt panel chứa thông tin phim vào JScrollPane và ẩn thanh cuộn ngang
		JScrollPane scrollPane = new JScrollPane(panelChuaThongTinPhim);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		cPanel.add(scrollPane);
		mainPanel.add(cPanel, BorderLayout.CENTER);

		// Tạo panel chứa nút điều hướng
		JPanel panelNutDieuHuong = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Tạo panel chứa nút trái
		JPanel panelNutTrai = new JPanel(new GridBagLayout());
		panelNutTrai.setBackground(Color.white);
		JButton btnLeft = createNavigationButton("Anh/anhtrai.png");
		panelNutTrai.add(btnLeft);

		// Tạo panel chứa nút phải
		JPanel panelNutPhai = new JPanel(new GridBagLayout());
		panelNutPhai.setBackground(Color.white);
		JButton btnRight = createNavigationButton("Anh/anhphai.png");
		panelNutPhai.add(btnRight);

		// Thêm sự kiện cho nút trái và phải để cuộn JScrollPane với hiệu ứng mượt mà
		addScrollFunctionality(btnLeft, scrollPane, -310); // Cuộn trái
		addScrollFunctionality(btnRight, scrollPane, 290); // Cuộn phải

		// Đặt panel điều hướng vào main panel
		mainPanel.add(panelNutTrai, BorderLayout.WEST);
		mainPanel.add(panelNutPhai, BorderLayout.EAST);
		mainPanel.add(panelNutDieuHuong, BorderLayout.SOUTH);

		// Đặt panel chính là nội dung của JFrame
		setContentPane(mainPanel);
		setVisible(true);
	}

	// ----------------------------------Tạo khung ngày chiếu-----------------
	// Hàm tạo danh sách nút ngày chiếu
	private JPanel taoKhungNgayChieu() {
		JPanel khungNgayChieu = new JPanel();
		khungNgayChieu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Tùy chỉnh bố cục cho các nút
		khungNgayChieu.setBackground(Color.white);
		khungNgayChieu.setBorder(BorderFactory.createEmptyBorder());

		LocalDate hnay = LocalDate.now();

		for (int i = 0; i < 20; i++) {
			JButton nutNgayChieu = new JButton(String.valueOf(hnay.getDayOfMonth()));
			nutNgayChieu.setPreferredSize(new Dimension(60, 40)); // Đặt kích thước cho các nút ngày chiếu
			nutNgayChieu.setFont(new Font("Arial", Font.BOLD, 14));
			nutNgayChieu.setBackground(new Color(135, 206, 250)); // Màu xanh nhẹ
			nutNgayChieu.setForeground(Color.white);

			// Thêm sự kiện cho nút ngày chiếu
			nutNgayChieu.addActionListener(e -> {
				String ngayChieuDuocChon = ((JButton) e.getSource()).getText();
				JOptionPane.showMessageDialog(this, "Ngày chiếu được chọn: " + ngayChieuDuocChon);
			});

			khungNgayChieu.add(nutNgayChieu);
			hnay = hnay.plusDays(1); // Cộng thêm 1 ngày cho lần lặp tiếp theo
		}

		return khungNgayChieu;
	}

	// -----------------------------------Hàm tạo menu bar
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Tùy Chọn");
		menu.setFont(new Font("Segoe UI", Font.BOLD, 14));

		// Menu Trang Chủ
		menuTrangChu = new JMenuItem("Trang Chủ");
		menuTrangChu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuTrangChu.addActionListener(this);
		menu.add(menuTrangChu);

		// Menu Nhân Viên
		menuNhanVien = new JMenuItem("Quản Lý Nhân Viên");
		menuNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuNhanVien.addMouseListener(this);
		menu.add(menuNhanVien);

		menuBar.add(menu);
		return menuBar;
	}

//		try {
//			ConnectDB db = ConnectDB.getInstance();
//			db.connect();
//	        Connection con = db.getConnection();
//	        Statement stmt = con.createStatement();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

	// Hàm tạo nút điều hướng
	private JButton createNavigationButton(String iconPath) {
		ImageIcon originalIcon = new ImageIcon(iconPath);
		Image img = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JButton button = new JButton(new ImageIcon(img));
		button.setPreferredSize(new Dimension(50, 50));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setIcon(new ImageIcon(originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setIcon(new ImageIcon(originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			}
		});

		return button;
	}

	// Hàm thêm chức năng cuộn cho nút
	private void addScrollFunctionality(JButton button, JScrollPane scrollPane, int scrollAmount) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
				Timer timer = new Timer(5, null);
				timer.addActionListener(new ActionListener() {
					int targetPosition = Math.max(0, horizontalScrollBar.getValue() + scrollAmount); // Vị trí cuộn mong
																										// muốn

					@Override
					public void actionPerformed(ActionEvent e) {
						int currentPosition = horizontalScrollBar.getValue();
						if ((scrollAmount < 0 && currentPosition > targetPosition)
								|| (scrollAmount > 0 && currentPosition < targetPosition)) {
							horizontalScrollBar.setValue(currentPosition + (scrollAmount < 0 ? -10 : 10)); // Di chuyển
																											// từng bước
																											// nhỏ
						} else {
							((Timer) e.getSource()).stop(); // Dừng khi đến vị trí mong muốn
						}
					}
				});
				timer.start();
			}
		});
	}

	// -------------------------------Ham tao panel thong tin SuatChieu
	// Hàm tạo panel thông tin phim với dữ liệu mẫu
	private JPanel taoPanelThongTinPhim(String maPhim) {
		JPanel panelThongTinPhim = new JPanel();
		panelThongTinPhim.setLayout(new BoxLayout(panelThongTinPhim, BoxLayout.Y_AXIS));
		panelThongTinPhim.setBackground(Color.white);
		panelThongTinPhim.setPreferredSize(new Dimension(260, 390));
		panelThongTinPhim.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Thông Tin Phim", TitledBorder.CENTER, TitledBorder.TOP));

		String sql = "SELECT * FROM Phim WHERE maPhim = ?";
		try {
			ConnectDB db = ConnectDB.getInstance();
			db.connect();
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhim);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String tenPhim = rs.getString("ten");
				maPhim = rs.getString("maPhim");
				String duongDanAnh = "PosterPhim/" + maPhim + ".png";

				// ----------------------Anh phim-----------------
				JPanel anhPanel = new JPanel();
				anhPanel.setBackground(Color.white);
				JLabel lblAnhPhim = new JLabel();
				lblAnhPhim.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
				lblAnhPhim.setPreferredSize(new Dimension(180, 270));
				lblAnhPhim.setHorizontalAlignment(SwingConstants.CENTER);
				ImageIcon icon = new ImageIcon(duongDanAnh);
				if (icon.getIconWidth() == -1) {
					icon = new ImageIcon("PosterPhim/default.png");
				}
				Image img = icon.getImage().getScaledInstance(180, 270, Image.SCALE_SMOOTH);
				lblAnhPhim.setIcon(new ImageIcon(img));
				anhPanel.add(lblAnhPhim);
				panelThongTinPhim.add(anhPanel);

				// ----------Thông tin phim-----------------
				JPanel panelChiTiet = new JPanel();
				JLabel labelTenPhim = new JLabel(tenPhim);
				panelChiTiet.setBackground(Color.white);
				panelChiTiet.setBorder(BorderFactory.createEmptyBorder());
				Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
				panelChiTiet.add(labelTenPhim);
				//-----------------Thêm Thông tin---------------------
				// Thêm các trường thông tin phim


				// --------------------------Cac nut Suat Chieu

				// Các nút suất chiếu
				JPanel panelSuatChieu = taoNutGioChieu(maPhim);
				
				
				panelThongTinPhim.add(panelChiTiet);
				panelThongTinPhim.add(panelSuatChieu);
			}

			// Cập nhật giao diện
			panelThongTinPhim.revalidate();
			panelThongTinPhim.repaint();

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
		}

		return panelThongTinPhim;
	}
	
	private JPanel taoNutGioChieu(String maPhim) {
		JPanel panelSuatChieu = new JPanel();
		panelSuatChieu.setLayout(new BoxLayout(panelSuatChieu, BoxLayout.X_AXIS));
		panelSuatChieu.setBackground(Color.white);
		String sql = "SELECT * From SuatChieu WHERE maPhim = ?";
		try {
			ConnectDB db = ConnectDB.getInstance();
			db.connect();
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhim);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				//Dinh dang thoi gian
				String gioChieu = rs.getString("gioChieu");
				maSuatChieu = rs.getString("maSuatChieu");
				SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
				
			    Date date = inputFormat.parse(gioChieu);
			    String formattedTime = outputFormat.format(date);
			    JButton btnSuat1 = new JButton(formattedTime);

				btnSuat1.setPreferredSize(new Dimension(80, 25));
				btnSuat1.setBackground(Color.red);
				btnSuat1.setForeground(Color.white);
				btnSuat1.setFocusPainted(false);
				btnSuat1.setBorder(BorderFactory.createLineBorder(Color.red));
				btnSuat1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				btnSuat1.addActionListener(e -> {
					new TrangBanVe_GUI(maSuatChieu);
				});
				
				panelSuatChieu.add(btnSuat1);
				panelSuatChieu.add(Box.createHorizontalStrut(20));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return panelSuatChieu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuTrangChu) {
			// Chuyển đến trang chủ
			new TrangChu_GUI(); // Thay thế bằng trang chính
			this.dispose(); // Đóng cửa sổ hiện tại
		} else if (e.getSource() == menuNhanVien) {
			// Chuyển đến trang quản lý nhân viên
			new TrangChu_GUI(); // Thay thế bằng trang quản lý nhân viên
			this.dispose(); // Đóng cửa sổ hiện tại
		}
	}

	public static void main(String[] args) {
		new TrangSuatChieu_GUI(); // Khởi tạo giao diện
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
