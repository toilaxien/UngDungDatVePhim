package GiaoDien;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrangBanVe_GUI extends JFrame {
	private DefaultListModel<String> cartModel;
	private JPanel seatSectionsPanel;
	private double totalPrice;
	private JLabel totalLabel;

	public TrangBanVe_GUI(String maSuatChieu) {
		setTitle("Trang Bán Vé");
		Dimension kichThuocManHinh = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(kichThuocManHinh.width, 678);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		cartModel = new DefaultListModel<>();
		JPanel contentPanel = createContentPanel();
		add(contentPanel);

		setVisible(true);
	}
//----------------------Tao noi dung-----------
	private JPanel createContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);

		JPanel seatSelectionPanel = createSeatSelectionPanel();
		JPanel orderInfoPanel = createOrderInfoPanel();

		panel.add(seatSelectionPanel, BorderLayout.NORTH);
		panel.add(orderInfoPanel, BorderLayout.CENTER);

		return panel;
	}
//----------------Them Panel Phim, Ghe, Bap vao---------------
	private JPanel createSeatSelectionPanel() {
		JPanel seatPanel = new JPanel(new BorderLayout());
		seatPanel.setBackground(Color.white);

		// Tiêu đề cho quầy bán vé
		JLabel seatTitle = new JLabel("QUẦY BÁN VÉ", JLabel.CENTER);
		seatTitle.setFont(new Font("Arial", Font.BOLD, 30));
		seatTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		// Tạo nút Home và panel tiêu đề
		JButton homeButton = new JButton("Home");
		homeButton.setFont(new Font("Arial", Font.BOLD, 16));

		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(seatTitle, BorderLayout.CENTER);
		titlePanel.add(homeButton, BorderLayout.EAST);

		seatPanel.add(titlePanel, BorderLayout.NORTH);

		// Panel chứa các khu vực ghế và thông tin bổ sung
		JPanel seatAreaPanel = new JPanel(new GridBagLayout());
		seatAreaPanel.setBackground(Color.white);
		seatAreaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Panel thông tin phim
		JPanel movieInfoPanel = createMovieInfoPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 10, 0, 10);
		gbc.weightx = 0.3;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		seatAreaPanel.add(movieInfoPanel, gbc);

		// Panel cho các khu vực ghế (A, B, C)
		seatSectionsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		seatSectionsPanel.setBackground(Color.white);
		char[] sections = { 'A', 'B', 'C' };
		for (char section : sections) {
			JPanel seatSection = createSeatSection(6, 5, section);
			seatSectionsPanel.add(seatSection);
		}

		TitledBorder border = BorderFactory
				.createTitledBorder(BorderFactory.createLineBorder(new Color(255, 51, 51), 2), "Khu Vực Ghế");
		border.setTitleFont(new Font("Arial", Font.BOLD, 24));
		border.setTitleColor(Color.BLACK);
		border.setTitleJustification(TitledBorder.CENTER);
		seatSectionsPanel.setBorder(border);

		gbc.gridx = 1;
		gbc.weightx = 0.6;
		gbc.fill = GridBagConstraints.BOTH;
		seatAreaPanel.add(seatSectionsPanel, gbc);

		// Panel chọn bắp rang bơ và nước ngọt
		JPanel snackSelectionPanel = createSnackSelectionPanel();
		gbc.gridx = 2;
		gbc.weightx = 0.4;
		seatAreaPanel.add(snackSelectionPanel, gbc);

		seatPanel.add(seatAreaPanel, BorderLayout.CENTER);
		return seatPanel;
	}

	// ---------------Tạo ghế----------------------
	private JPanel createSeatSection(int rows, int seatsPerRow, char sectionName) {
		JPanel sectionPanel = new JPanel(new GridLayout(rows, seatsPerRow));
		sectionPanel.setBorder(BorderFactory.createTitledBorder("Khu Vực " + sectionName));

		String sql = "SELECT maGhe FROM Ghe WHERE maGhe LIKE ?";

		try {
			// Mở lại kết nối nếu cần thiết
			Connection con = ConnectDB.getConnection();
			if (con == null || con.isClosed()) {
				System.out.println("Kết nối đến cơ sở dữ liệu không hợp lệ.");
				return sectionPanel;
			}

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, sectionName + "%");

				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						String maGhe = rs.getString("maGhe");
						JButton button = new JButton(maGhe);
						button.setFont(new Font("Arial", Font.BOLD, 13));
						button.setPreferredSize(new Dimension(30, 30));
						button.setForeground(Color.WHITE);
						button.setMargin(new Insets(0, 0, 0, 0));
						button.setHorizontalAlignment(SwingConstants.CENTER);
						button.setVerticalAlignment(SwingConstants.CENTER);
						button.setBackground(Color.green);
						button.addActionListener(new SeatButtonActionListener(button));
						sectionPanel.add(button);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sectionPanel;
	}
//---------------------------------Tao Panel do an---------------------
	private JPanel createSnackSelectionPanel() {
	    JPanel snackPanel = new JPanel();
	    snackPanel.setLayout(new BoxLayout(snackPanel, BoxLayout.Y_AXIS));
	    snackPanel.setBackground(Color.white);
	    snackPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));
	    
	    // Câu truy vấn SQL để lấy tất cả các món ăn nhẹ
	    String sql = "SELECT * FROM DoAnNhe";
	    try {
	        Connection con = ConnectDB.getConnection();
	        if (con == null || con.isClosed()) {
	            System.out.println("Kết nối đến cơ sở dữ liệu không hợp lệ.");
	            return snackPanel;
	        }

	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            try (ResultSet rs = stmt.executeQuery()) {
	                
	                // Tạo viền có tiêu đề cho panel chọn đồ ăn nhẹ
	                TitledBorder border = BorderFactory.createTitledBorder(
	                    BorderFactory.createLineBorder(new Color(255, 51, 51), 2), "Chọn Bắp và Nước");
	                border.setTitleFont(new Font("Arial", Font.BOLD, 18));
	                border.setTitleJustification(TitledBorder.CENTER);
	                snackPanel.setBorder(BorderFactory.createCompoundBorder(border, snackPanel.getBorder()));
	                
	                // Duyệt qua kết quả và thêm các món ăn nhẹ vào panel
	                while (rs.next()) {
	                    String maDoAnNhe = rs.getString("maDoAnNhe");
	                    String tenDoAnNhe = rs.getString("tenDoAnNhe");
	                    double gia = rs.getDouble("gia");
	                    String imagePath = "Anh/" + maDoAnNhe + ".jpg";

	                    // Thêm từng món ăn nhẹ từ cơ sở dữ liệu
	                    snackPanel.add(createSnackPanel(tenDoAnNhe, gia, imagePath));
	                    snackPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Khởi tạo và thêm nhãn tổng tiền vào panel
	    totalLabel = new JLabel("Tổng tiền: 0 VNĐ"); // Khởi tạo với tổng bắt đầu
	    totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    snackPanel.add(totalLabel); // Thêm vào panel snack

	    return snackPanel;
	}
//-------------------Tao mot mon an----------------------------------
	private JPanel createSnackPanel(String name, double price, String imagePath) {
	    JPanel snackPanel = new JPanel();
	    snackPanel.setLayout(new BoxLayout(snackPanel, BoxLayout.Y_AXIS));

	    // Tạo nhãn cho tên món ăn nhẹ
	    JLabel label = new JLabel("Chọn " + name + ":");
	    label.setFont(new Font("Arial", Font.BOLD, 16));
	    label.setAlignmentX(Component.CENTER_ALIGNMENT);
	    snackPanel.add(label);

	    // Tạo ảnh cho món ăn nhẹ
	    ImageIcon originalIcon = new ImageIcon(imagePath);
	    Image scaledImage = originalIcon.getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH);
	    JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
	    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    snackPanel.add(imageLabel);

	    // Tạo nhãn hiển thị giá của món ăn
	    JLabel priceLabel = new JLabel("Giá: " + String.format("%,.0f", price) + " VNĐ");
	    priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    snackPanel.add(priceLabel);

	    // Tạo panel cho số lượng
	    JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    snackPanel.add(quantityPanel);

	    // Tạo JSpinner để chọn số lượng
	    JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
	    quantityPanel.add(spinner);

	    // Tạo nút thêm vào giỏ hàng
	    JButton addButton = new JButton("+");
	    addButton.addActionListener(e -> {
	        int quantity = (int) spinner.getValue(); // Lấy số lượng từ JSpinner
	        double itemTotalPrice = price * quantity; // Tính tổng tiền cho món ăn

	        // Thêm món vào giỏ hàng
	        cartModel.addElement(name + " x " + quantity + " - " + String.format("%,.0f", itemTotalPrice) + " VNĐ");

	        // Cập nhật tổng tiền
	        totalPrice += itemTotalPrice;
	        totalLabel.setText("Tổng tiền: " + String.format("%,.0f", totalPrice) + " VNĐ"); // Cập nhật nhãn tổng tiền
	    });
	    quantityPanel.add(addButton);

	    return snackPanel; // Trả về panel chứa tất cả thông tin
	}


//------------------------Thong tin phim--------------------------
	private JPanel createMovieInfoPanel() {
		JPanel movieInfoPanel = new JPanel();
		movieInfoPanel.setLayout(new BoxLayout(movieInfoPanel, BoxLayout.Y_AXIS));
		movieInfoPanel.setBackground(Color.white);
		movieInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

		TitledBorder border = BorderFactory
				.createTitledBorder(BorderFactory.createLineBorder(new Color(255, 51, 51), 2), "Thông tin phim");
		border.setTitleFont(new Font("Times New Roman", Font.BOLD, 22));
		border.setTitleJustification(TitledBorder.CENTER);
		movieInfoPanel.setBorder(BorderFactory.createCompoundBorder(border, movieInfoPanel.getBorder()));

		String ten = "Chưa có dữ liệu";
		String thoiLuong = "Chưa có dữ liệu";
		String imagePath = "Anh/phim.jpg";
		String ngayChieu = "";
		String gioChieu = "";
		String sql = "SELECT SuatChieu.*, Phim.* FROM SuatChieu JOIN Phim ON SuatChieu.maPhim = Phim.maPhim WHERE SuatChieu.maSuatChieu = 'SC1'";
		try {
			ConnectDB db = ConnectDB.getInstance();
			db.connect();
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, "maSuatChieu");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ten = rs.getString("ten");
				thoiLuong = rs.getString("thoiLuong") + " phút";
				imagePath = "PosterPhim/" + rs.getString("maPhim") + ".png";
				ngayChieu = rs.getString("ngayChieu");
				
				String gioChieuChuaF = rs.getString("gioChieu");
				SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
				
			    Date date = inputFormat.parse(gioChieuChuaF);
			    gioChieu = outputFormat.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Hiển thị ảnh phim
		ImageIcon originalIcon = new ImageIcon(imagePath);
		Image scaledImage = originalIcon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
		JLabel movieImageLabel = new JLabel(new ImageIcon(scaledImage));
		movieImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		movieInfoPanel.add(movieImageLabel);

		movieInfoPanel.add(Box.createVerticalStrut(10)); // Thêm khoảng cách giữa ảnh và tiêu đề

		// Hiển thị tên phim
		JLabel movieTitleLabel = new JLabel(ten);
		movieTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		movieTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		movieInfoPanel.add(movieTitleLabel);

		movieInfoPanel.add(Box.createVerticalStrut(5)); // Thêm khoảng cách giữa tên phim và thời lượng

		// Hiển thị thời lượng phim
		JLabel movieDurationLabel = new JLabel("Ngày chiếu: " + ngayChieu);
		movieDurationLabel.setFont(new Font("Arial", Font.BOLD, 18));
		movieDurationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		movieInfoPanel.add(movieDurationLabel);
		movieInfoPanel.add(Box.createVerticalStrut(5));
		
		//Hiển thị giờ chiếu
		JLabel gioChieuLabel = new JLabel("Giờ chiếu: " + gioChieu);
		gioChieuLabel.setFont(new Font("Arial", Font.BOLD, 18));
		gioChieuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		movieInfoPanel.add(gioChieuLabel);
		movieInfoPanel.add(Box.createVerticalStrut(5));
		
		return movieInfoPanel;
	}

	//------------------------TrangThaiVe--------------------------------
	private void capNhatTrangThaiGhe(String maGhe, String trangThai) {
		String sql = "UPDATE Ghe SET trangThai = ? WHERE maGhe = ?";
		try {
			ConnectDB db = ConnectDB.getInstance();
			db.connect();
			Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, trangThai); // Cập nhật trạng thái
			pstmt.setString(2, maGhe); // Cập nhật ghế
			int soLuongDaDoi = pstmt.executeUpdate(); // Thực thi câu lệnh SQL

			if (soLuongDaDoi > 0) {
				System.out.println("Cập nhật thành công ghế: " + maGhe + " với trạng thái: " + trangThai);
			} else {
				System.out.println("Không tìm thấy ghế: " + maGhe + " để cập nhật.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi cập nhật trạng thái ghế: " + e.getMessage());
		}
	}

	//------------------Tao Order--------------------------
	private JPanel createOrderInfoPanel() {
		JPanel orderPanel = new JPanel(new BorderLayout());
		orderPanel.setBackground(Color.white);
		orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

		totalLabel = new JLabel("Tổng tiền: 0 VNĐ");
		totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
		totalLabel.setForeground(new Color(255, 51, 51));
		totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		orderPanel.add(totalLabel, BorderLayout.NORTH);

		JList<String> cartList = new JList<>(cartModel);
		JScrollPane scrollPane = new JScrollPane(cartList);
		orderPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		JButton clearButton = new JButton("Xóa Giỏ Hàng");
		clearButton.addActionListener(e -> {
			cartModel.clear();
			totalPrice = 0;
			totalLabel.setText("Tổng tiền: 0 VNĐ");

			// Đổi màu ghế thành màu xanh và kích hoạt lại
			for (Component component : seatSectionsPanel.getComponents()) {
				if (component instanceof JPanel) {
					for (Component seat : ((JPanel) component).getComponents()) {
						if (seat instanceof JButton) {
							seat.setBackground(Color.GREEN);
							seat.setEnabled(true);
						}
					}
				}
			}
		});
		buttonPanel.add(clearButton);

		JButton purchaseButton = new JButton("Mua Vé");
		purchaseButton.addActionListener(e -> {
			StringBuilder sb = new StringBuilder();
			for (Component component : seatSectionsPanel.getComponents()) {
				if (component instanceof JPanel) {
					for (Component seat : ((JPanel) component).getComponents()) {
						if (seat instanceof JButton && seat.getBackground() == Color.YELLOW) {
							String seatName = ((JButton) seat).getText(); // Cast to JButton here
							sb.append(seatName).append("\n");
						}
					}
				}
			}

			if (sb.length() == 0) {
				JOptionPane.showMessageDialog(this, "Giỏ hàng trống!");
			} else {
				// Thêm ghế vào giỏ hàng
				for (String seat : sb.toString().split("\n")) {
					cartModel.addElement(seat + " - Giá: 50,000 VNĐ");
				}
				totalPrice += 50000 * sb.toString().split("\n").length;
				totalLabel.setText("Tổng tiền: " + String.format("%,.0f", totalPrice) + " VNĐ");

				// Đổi màu ghế thành màu đỏ, vô hiệu hóa và cập nhật trạng thái
				for (String seat : sb.toString().split("\n")) {
					for (Component component : seatSectionsPanel.getComponents()) {
						if (component instanceof JPanel) {
							for (Component seatButton : ((JPanel) component).getComponents()) {
								if (seatButton instanceof JButton && seatButton.getToolkit().equals(seat)) {
									seatButton.setEnabled(false);
									seatButton.setBackground(Color.RED);
									capNhatTrangThaiGhe(seat, "đã đặt"); // Cập nhật trạng thái ghế
								}
							}
						}
					}
				}
			}
		});
		buttonPanel.add(purchaseButton);

		JButton payButton = new JButton("Thanh Toán");
		payButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(this,
					"Thanh toán thành công!\nTổng tiền: " + String.format("%,.0f", totalPrice) + " VNĐ");
			cartModel.clear();
			totalPrice = 0;
			totalLabel.setText("Tổng tiền: 0 VNĐ");

			// Đổi màu ghế thành màu đỏ và vô hiệu hóa
			for (Component component : seatSectionsPanel.getComponents()) {
				if (component instanceof JPanel) {
					for (Component seat : ((JPanel) component).getComponents()) {
						if (seat instanceof JButton && seat.getBackground() == Color.YELLOW) {
							seat.setBackground(Color.RED);
							seat.setEnabled(false);
							String seatName = ((JButton) seat).getText();
							capNhatTrangThaiGhe(seatName, "đã đặt"); // Cập nhật trạng thái ghế
						}
					}
				}
			}
		});
		buttonPanel.add(payButton);

		JButton removeButton = new JButton("Xóa Khỏi Đơn Hàng");
		removeButton.addActionListener(e -> {
			String selectedSeat = cartList.getSelectedValue();
			if (selectedSeat != null) {
				cartModel.removeElement(selectedSeat);
				totalPrice -= 50000; // Giả định giá vé là 50,000 VNĐ
				totalLabel.setText("Tổng tiền: " + String.format("%,.0f", totalPrice) + " VNĐ");

				// Đặt lại màu ghế nếu ghế đã được xóa khỏi đơn hàng
				String seatName = selectedSeat.split(" - ")[0]; // Lấy tên ghế từ chuỗi
				for (Component component : seatSectionsPanel.getComponents()) {
					if (component instanceof JPanel) {
						for (Component seat : ((JPanel) component).getComponents()) {
							if (seat instanceof JButton && ((JButton) seat).getText().equals(seatName)) {
								seat.setBackground(Color.GREEN); // Đặt lại màu ghế
								seat.setEnabled(true); // Kích hoạt ghế lại
								capNhatTrangThaiGhe(seatName, "trống"); // Cập nhật trạng thái ghế về "trống"
							}
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn ghế để xóa khỏi đơn hàng!");
			}
		});
		buttonPanel.add(removeButton);

		orderPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Cập nhật trạng thái ghế từ cơ sở dữ liệu
		updateSeatStatusFromDB();

		return orderPanel;
	}

	private void updateSeatStatusFromDB() {
		String sql = "SELECT * FROM Ghe";
		try {
			ConnectDB db = ConnectDB.getInstance();
			db.connect();
			Connection con = db.getConnection();
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						String seatName = rs.getString("maGhe");
						String status = rs.getString("trangThai");

						for (Component component : seatSectionsPanel.getComponents()) {
							if (component instanceof JPanel) {
								for (Component seat : ((JPanel) component).getComponents()) {
									if (seat instanceof JButton && ((JButton) seat).getText().equals(seatName)) {
										if (status.equals("đã đặt")) {
											seat.setBackground(Color.RED);
											seat.setEnabled(false);
										} else {
											seat.setBackground(Color.GREEN);
											seat.setEnabled(true);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class SeatButtonActionListener implements ActionListener {
		private final JButton seatButton;

		public SeatButtonActionListener(JButton button) {
			this.seatButton = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (seatButton.getBackground() == Color.GREEN) {
				seatButton.setBackground(Color.YELLOW);
			} else if (seatButton.getBackground() == Color.YELLOW) {
				seatButton.setBackground(Color.GREEN);
			} else if (seatButton.getBackground() == Color.RED) {
				JOptionPane.showMessageDialog(seatButton, "Ghế này đã được đặt!");
			}
		}
	}

	public static void main(String[] args) {
		new TrangBanVe_GUI("SC1");
	}
}
