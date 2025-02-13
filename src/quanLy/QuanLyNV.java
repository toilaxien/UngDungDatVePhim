package quanLy;

import java.util.ArrayList;
import java.util.List;

import lop.NhanVien;

public class QuanLyNV extends QuanLy {
	private ArrayList<NhanVien> danhSachNhanVien;

	public QuanLyNV() {
		// TODO Auto-generated constructor stub
		this.danhSachNhanVien = new ArrayList<NhanVien>();
	}
	public int getSoLuongNhanVien() {
		return danhSachNhanVien.size();
	}
	// Thêm nhân viên
	public boolean themNhanVien(NhanVien nhanVien) {
		if(danhSachNhanVien.contains(nhanVien)) {
			return false;
		}
		return danhSachNhanVien.add(nhanVien);
	}

	// Xóa nhân viên
	public boolean xoaNhanVien(String maNV) {
		return danhSachNhanVien.removeIf(nv -> nv.getMaNV().equals(maNV));
	}

	// Tìm kiếm nhân viên theo mã
	public NhanVien timNhanVien(String maNV) {
		for (NhanVien nv : danhSachNhanVien) {
			if (nv.getMaNV().equals(maNV)) {
				return nv;
			}
		}
		return null;
	}

	
}
