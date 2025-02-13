package GiaoDien;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import quanLy.QuanLyNV;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TroGiup_GUI extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    JButton btnGhiChu1, btnGhiChu2, btnGhiChu3, btnGhiChu4, btnGhiChu5, btnGhiChu6;
    JLabel tieuDe, noiDung1, noiDung2, noiDung3, noiDung4, noiDung5,
            noiDung6, noiDung7, noiDung8, noiDung9, noiDung10, noiDung11;
    JPanel panelCauHoi, panelNoiDung;
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
	private JButton btnThoat;
	private JMenuItem menuItem_27;

    public TroGiup_GUI() {
    	super("VXD_TRỢ GIÚP");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1920, 1080);
	    setLocationRelativeTo(null);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    
        setLayout(new BorderLayout());

        taoPanelButton();
        taoPanelNoiDung();
        add(createMenuBar(), BorderLayout.NORTH);
        add(panelCauHoi, BorderLayout.WEST);
        add(panelNoiDung, BorderLayout.CENTER);
        setVisible(true);

	    
    }
    
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
    

    private void taoPanelButton() {
        panelCauHoi = new JPanel();
        panelCauHoi.setLayout(new BoxLayout(panelCauHoi, BoxLayout.Y_AXIS));
        panelCauHoi.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        tieuDe = new JLabel("                        Trợ Giúp");
        tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelCauHoi.add(Box.createVerticalStrut(10));
        panelCauHoi.add(tieuDe);
        panelCauHoi.add(Box.createVerticalStrut(10));

        btnGhiChu1 = taoNut("• Điểm tích lũy được tính và sử dụng như thế nào ?");
        btnGhiChu2 = taoNut("• Tìm và xuất hóa đơn cũ ?");
        btnGhiChu3 = taoNut("• Các câu hỏi khác... ?");
        btnGhiChu4 = taoNut("• Tìm kiếm nhân viên ?");
        btnGhiChu5 = taoNut("• Vấn đề bản quyền");
        btnGhiChu6 = taoNut("• Hướng dẫn thêm nhân viên!");

        panelCauHoi.add(btnGhiChu6);
        panelCauHoi.add(Box.createVerticalStrut(10));
        panelCauHoi.add(btnGhiChu2);
        panelCauHoi.add(Box.createVerticalStrut(10));
        panelCauHoi.add(btnGhiChu4);
        panelCauHoi.add(Box.createVerticalStrut(10));
        panelCauHoi.add(btnGhiChu1);
        panelCauHoi.add(Box.createVerticalStrut(10));
        panelCauHoi.add(btnGhiChu5);
        panelCauHoi.add(Box.createVerticalStrut(10));
        panelCauHoi.add(btnGhiChu3);
        panelCauHoi.add(Box.createVerticalStrut(350));
        Box panel7 = Box.createHorizontalBox();
		panel7.setPreferredSize(new Dimension(20, 75));
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
       new Timer(1000, e -> capNhatGio(lblTime)).start();
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
		
		panelCauHoi.add(panel7);
    }
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
    private JButton taoNut(String text) {
        JButton nut = new JButton(text);
		nut.setContentAreaFilled(false); // Tắt nền
		nut.setOpaque(false); 
        nut.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nut.addActionListener(langNgheSuKienNut);
        nut.setBorderPainted(false);
        return nut;
    }

    private void taoPanelNoiDung() {
        panelNoiDung = new JPanel();
        panelNoiDung.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // panelNoiDung.setLayout(new BorderLayout());
        panelNoiDung.setLayout(new BoxLayout(panelNoiDung, BoxLayout.Y_AXIS));
        panelNoiDung.add(Box.createVerticalStrut(10));
        panelNoiDung
                .add(noiDung1 = new JLabel("  ⁕⁕⁕.Welcome VXD Cinema App. Hãy chọn các mục bên để xem hướng dẫn.⁕⁕⁕"));
        noiDung1.setFont(new Font("Tahoma", Font.BOLD, 18));
    }

    private ActionListener langNgheSuKienNut = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton nut = (JButton) e.getSource();
            if (nut == btnGhiChu1) {
                panelNoiDung.removeAll();
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung1 = new JLabel("  • Điểm tích lũy được tính và sử dụng như thế nào ?"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung2 = new JLabel("  - Khi mua 1 vé khách hàng sẽ nhận được 1 điểm. "
                        + "Số lượng vé sẽ tương đương với số điểm khách hàng tích lũy được."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung3 = new JLabel(
                        "  - Thu ngân hãy hỏi khách hàng có muốn sử dụng điểm không trước khi ấn nút 'Đổi ĐTL'. "));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung4 = new JLabel(
                        "  - Khi nhấn nút 1 điểm sẽ tương đương với 1000đ được giảm thẳng vào hóa đơn thanh toán của khách hàng."));
                noiDung1.setFont(new Font("Tahoma", Font.BOLD, 18));
                noiDung2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung3.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung4.setFont(new Font("Tahoma", Font.PLAIN, 16));
            } else if (nut == btnGhiChu2) {
                panelNoiDung.removeAll();
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung1 = new JLabel("  • Tìm và xuất hóa đơn cũ ?"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung2 = new JLabel(
                        "  - Thu ngân có thể tìm và xuất lại những hóa đơn đã bán trong mục 'Hóa Đơn' hoặc mục 'Thống Kê'."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung3 = new JLabel(
                        "  - Trong 'Hóa Đơn' sẽ hỗ trợ bạn xem lại hóa đơn bằng số điện thoại của khách hàng hoặc mã hóa đơn."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung4 = new JLabel(
                        "  - Trong 'Thống Kê' sẽ hỗ trợ bạn xem lại các hóa đơn theo ngày, tháng, năm.'"));
                noiDung1.setFont(new Font("Tahoma", Font.BOLD, 18));
                noiDung2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung3.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung4.setFont(new Font("Tahoma", Font.PLAIN, 16));
            } else if (nut == btnGhiChu3) {
                panelNoiDung.removeAll();
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung1 = new JLabel("  • Các câu hỏi khác... ?"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung2 = new JLabel("  - Xin lỗi vì sự bất tiện này!"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung3 = new JLabel(
                        "  - Nếu danh sách câu hỏi có sẵn không hỗ trợ được bạn sử dụng các chứ năng của chương trình."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung4 = new JLabel(
                        "  - Xin vui lòng liên hệ Zalo: VXD_Cinema để được hỗ trợ tốt nhất. Xin cảm ơn."));
                noiDung1.setFont(new Font("Tahoma", Font.BOLD, 18));
                noiDung2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung3.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung4.setFont(new Font("Tahoma", Font.PLAIN, 16));
            } else if (nut == btnGhiChu4) {
                panelNoiDung.removeAll();
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung1 = new JLabel("  • Tìm kiếm nhân viên ?"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung
                        .add(noiDung2 = new JLabel("  - Để tìm kiếm Nhân viên vui lòng vào mục 'Quản lí nhân viên'"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung3 = new JLabel(
                        "    + Bên trái màn hình bạn có thể thấy được nút tìm kiếm"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung4 = new JLabel("  +Bạn hãy nhập mã nhân viên bạn muốn tìm vào khung tìm kiếm"));
                noiDung1.setFont(new Font("Tahoma", Font.BOLD, 18));
                noiDung2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung3.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung4.setFont(new Font("Tahoma", Font.PLAIN, 16));
            } else if (nut == btnGhiChu5) {
                panelNoiDung.removeAll();
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung1 = new JLabel("  • Vấn đề bản quyền."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(
                        noiDung2 = new JLabel("  Bản quyền @2024 Nhóm 4 Việt Xuyên Dương. Tất cả các quyền được bảo lưu."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung3 = new JLabel(
                        "  - Thông tin về quyền lợi bản quyền: Ứng dụng này được bảo vệ bởi luật bản quyền và các hiệp định quốc tế về bản quyền. Mọi hành động sao chép, phân phối"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung4 = new JLabel(
                        "  hoặc sử dụng ứng dụng hoặc một phần nào đó của ứng dụng mà không có sự đồng ý trước bằng văn bản từ chủ sở hữu bản quyền là vi phạm pháp luật và có"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung5 = new JLabel("  thể bị truy cứu trách nhiệm dân sự và hình sự."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung6 = new JLabel(
                        "  - Thông tin về việc sử dụng ứng dụng: Người dùng có quyền cài đặt và sử dụng ứng dụng này trên các thiết bị cá nhân của họ. Việc sao chép, sửa đổi hoặc "));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung7 = new JLabel(
                        "  phân phối ứng dụng này mà không có sự đồng ý trước bằng văn bản từ chủ sở hữu bản quyền là không được phép."));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung8 = new JLabel(
                        "  - Thông tin liên hệ: Nếu bạn có bất kỳ câu hỏi hoặc yêu cầu nào về việc sử dụng ứng dụng này hoặc vấn đề liên quan đến bản quyền, vui lòng liên hệ với "));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung9 = new JLabel(
                        "  chúng tôi tại địa chỉ email: toilaxien@gmail.com hoặc số điện thoại: 0946611645."));

                noiDung1.setFont(new Font("Tahoma", Font.BOLD, 18));
                noiDung2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung3.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung4.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung5.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung6.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung7.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung8.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung9.setFont(new Font("Tahoma", Font.PLAIN, 16));
            } else if (nut == btnGhiChu6) {
                panelNoiDung.removeAll();
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung1 = new JLabel("  • Hướng dẫn Order!"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung2 = new JLabel(
                        "  - Vào trang 'Order' ở mục nhân viên → Chọn món mà khách hàng yêu cầu → Chọn size và số lượng → Ấn nút 'Thêm' nước sẽ hiển thị lên bảng Order Drink"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung3 = new JLabel(
                        "  ngay bên cạnh → Hỏi số điện thoại của khách để kiểm xem đã là thành viên chưa để tích điểm hoặc sử dụng điểm tích lũy → Nhập số tiền khách đưa"));
                panelNoiDung.add(Box.createVerticalStrut(10));
                panelNoiDung.add(noiDung4 = new JLabel(
                        "  → Ấn nút 'Thanh Toán' để xuất hóa đơn đưa cho khách hàng hoặc nút 'Thanh Toán Không In' để thanh toán mà không cần in hóa đơn."));
                noiDung1.setFont(new Font("Tahoma", Font.BOLD, 18));
                noiDung2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung3.setFont(new Font("Tahoma", Font.PLAIN, 16));
                noiDung4.setFont(new Font("Tahoma", Font.PLAIN, 16));
            }
            panelNoiDung.revalidate();// Cập nhật giao diện
            panelNoiDung.repaint();
        }
    };
	private ImageIcon scaleIcon(ImageIcon icon, double scale) {
		Image image = icon.getImage();
		int width = (int) (image.getWidth(null) * scale);
		int height = (int) (image.getHeight(null) * scale);
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
    public static void main(String[] args) {
		new TroGiup_GUI();
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
}