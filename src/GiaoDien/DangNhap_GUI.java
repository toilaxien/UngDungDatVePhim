package GiaoDien;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import Dao.TaiKhoan_DAO;
import connectDB.ConnectDB;
import lop.TaiKhoan;

import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import java.awt.SystemColor;


public class DangNhap_GUI extends JFrame implements KeyListener,ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel_All;
	private JButton jButton_Login, jButton_clear;
	private PlaceholderTextField text_User;
	private PlaceholderPasswordField text_Password;
	private JCheckBox cbManager;
	private TaiKhoan_DAO taiKhoan_DAO;


	public DangNhap_GUI() {
		super("VXD Cinema - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        taiKhoan_DAO= new TaiKhoan_DAO();
		// Set logo
		ImageIcon icon = new ImageIcon("anh\\logo.png");
		this.setIconImage(icon.getImage());

		jPanel_All = new JPanel();
		jPanel_All.setForeground(new Color(255, 255, 255));
		jPanel_All.setBackground(new Color(255, 255, 255));
		jPanel_All.setBorder(null);

		getContentPane().add(jPanel_All);
		jPanel_All.setLayout(new BorderLayout(0, 0));

		JPanel jPanel_Login = new JPanel();
		jPanel_Login.setBackground(new Color(255, 255, 255));
		jPanel_All.add(jPanel_Login);
		jPanel_Login.setLayout(null);
		
		text_User = new PlaceholderTextField("Username");
		text_User.setBackground(new Color(192, 192, 192));
		text_User.setFont(new Font("Tahoma", Font.PLAIN, 15));	
		text_User.setBounds(550, 322, 400, 36);
		jPanel_Login.add(text_User);
		text_User.setColumns(20);

		JCheckBox cbHidePassword = new JCheckBox("Hide Password !");
		cbHidePassword.setForeground(new Color(255, 255, 255));
		cbHidePassword.setBackground(new Color(255, 255, 255));
		cbHidePassword.setBounds(549, 442, 120, 34);
		cbHidePassword.setOpaque(false);
		jPanel_Login.add(cbHidePassword);

		cbManager = new JCheckBox("You are Manager");
		cbManager.setForeground(new Color(255, 255, 255));
		cbManager.setBackground(new Color(255, 255, 255));
		cbManager.setBounds(819, 442, 130, 34);
		cbManager.setOpaque(false);
		jPanel_Login.add(cbManager);
		cbHidePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbHidePassword.isSelected()) {
					// Nếu được chọn, ẩn mật khẩu
					text_Password.setEchoChar((char) 0);
				} else {
					// Nếu không được chọn, hiển thị mật khẩu
					text_Password.setEchoChar('\u25cf'); 
				}
			}
		});

		jButton_Login = new JButton("Login");
		jButton_Login.setForeground(new Color(255, 255, 255));
		jButton_Login.setContentAreaFilled(false);
		jButton_Login.setOpaque(true);
	   

		jButton_Login.setBackground(new Color(255, 0, 51));
		jButton_Login.setFont(new Font("Tahoma", Font.BOLD, 18));
		jButton_Login.setBounds(590, 520, 120, 45);
		jPanel_Login.add(jButton_Login);

		text_Password = new PlaceholderPasswordField("Password");
		text_Password.setBounds(550, 390, 400, 36);
		jPanel_Login.add(text_Password);

		JSeparator separator = new JSeparator();
		separator.setBounds(464, 495, 558, 15);
		jPanel_Login.add(separator);

		jButton_clear = new JButton("Clear");
		jButton_clear.setForeground(new Color(255, 255, 255));
		jButton_clear.setContentAreaFilled(false);
		jButton_clear.setOpaque(true);

		jButton_clear.setFont(new Font("Tahoma", Font.BOLD, 18));
		jButton_clear.setBackground(new Color(51, 51, 51));
		jButton_clear.setBounds(774, 520, 120, 45);
		jPanel_Login.add(jButton_clear);

		JLabel jLabel_Title = new JLabel("Welcome to VXD Cinema !");
		jLabel_Title.setForeground(SystemColor.textHighlightText);
		jLabel_Title.setFont(new Font("Monotype Corsiva", Font.BOLD, 70));
		jLabel_Title.setBounds(402, 200, 730, 72);
		jPanel_Login.add(jLabel_Title);

		JPanel transparentPanel = new JPanel();
		transparentPanel.setBackground(new Color(0, 0, 0, 90)); 
		transparentPanel.setBounds(368, 157, 764, 477);
		jPanel_Login.add(transparentPanel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("anh\\Background_DangNhap.png"));
		lblNewLabel.setBounds(0, 0, 1541, 841);
		jPanel_Login.add(lblNewLabel);
		
		jButton_clear.addActionListener(this);
		jButton_Login.addActionListener(this);
		setVisible(true);
		

	}

	public static void main(String[] args) {
		ConnectDB.getInstance().connect();
        new DangNhap_GUI();
        
	
	}


	public class PlaceholderTextField extends JTextField {
		private static final long serialVersionUID = 1L;
		private String placeholder;

		public PlaceholderTextField(String placeholder) {
			this.placeholder = placeholder;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (getText().isEmpty()) {
				g.setColor(Color.GRAY);
				g.setFont(new Font("Arial", Font.PLAIN, 16));
				g.drawString(placeholder, getInsets().left, g.getFontMetrics().getHeight() + getInsets().top);
			}
		}
	}

	public class PlaceholderPasswordField extends JPasswordField {
		private static final long serialVersionUID = 1L;
		private String placeholder;

		public PlaceholderPasswordField(String placeholder) {
			this.placeholder = placeholder;
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (getText().isEmpty()) {
				g.setColor(Color.GRAY);
				g.setFont(new Font("Arial", Font.PLAIN, 16));
				g.drawString(placeholder, getInsets().left, g.getFontMetrics().getHeight() + getInsets().top);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (e.getSource() == text_User) {
				text_Password.requestFocus();
			} else if (e.getSource() == text_Password) {
				jButton_Login.doClick();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o= e.getSource();
		if(o.equals(jButton_clear)) {
			text_User.setText("");
			text_Password.setText("");
		}
		String tenQuanLy= "VietXuyenDuong";
		String matkhau="2004";
		String mkNhapVao=text_Password.getText();
		String tkNhapVao=text_User.getText();
		if(o.equals(jButton_Login)) {
			if(cbManager.isSelected()==true && matkhau.equals(mkNhapVao) && tkNhapVao.equals(tenQuanLy) ) {
				ConnectDB.getInstance().connect();
				this.dispose();
				new TrangChuQuanLY_GUI();
			}else if (kiemTraDangNhap(tkNhapVao, mkNhapVao)==true && cbManager.isSelected()==false) {
				this.dispose();
				new TrangChu_GUI();
			}else {
				JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng");
			}
		}
		
	}
	

	public boolean kiemTraDangNhap(String tenDangNhap, String matKhau) {
        try {
            ArrayList<TaiKhoan> danhSachTaiKhoan = taiKhoan_DAO.layDanhSachTaiKhoan();
            for (TaiKhoan taiKhoan : danhSachTaiKhoan) {
                if (taiKhoan.getTenDangNhap().equals(tenDangNhap) && taiKhoan.getMatKhau().equals(matKhau)) {
                    return true; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }
	

	

}
