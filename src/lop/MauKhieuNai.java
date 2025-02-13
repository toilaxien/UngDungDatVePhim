package lop;

import java.util.Objects;

public class MauKhieuNai {
	private String maKieuNai;
	private NhanVien nhanVien;
	private String maKH;
	private String lyDoKhieuNai;
	private String trangThai;
	public MauKhieuNai(String maKieuNai, NhanVien nhanVien, String maKH, String lyDoKhieuNai, String trangThai) {
		super();
		this.maKieuNai = maKieuNai;
		this.nhanVien = nhanVien;
		this.maKH = maKH;
		this.lyDoKhieuNai = lyDoKhieuNai;
		this.trangThai = trangThai;
	}
	public MauKhieuNai() {
		super();
	}
	
	public MauKhieuNai(String maKieuNai) {
		super();
		this.maKieuNai = maKieuNai;
	}
	public String getMaKieuNai() {
		return maKieuNai;
	}
	public void setMaKieuNai(String maKieuNai) {
		this.maKieuNai = maKieuNai;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getLyDoKhieuNai() {
		return lyDoKhieuNai;
	}
	public void setLyDoKhieuNai(String lyDoKhieuNai) {
		this.lyDoKhieuNai = lyDoKhieuNai;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(lyDoKhieuNai, maKH, maKieuNai, nhanVien, trangThai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MauKhieuNai other = (MauKhieuNai) obj;
		return Objects.equals(lyDoKhieuNai, other.lyDoKhieuNai) && Objects.equals(maKH, other.maKH)
				&& Objects.equals(maKieuNai, other.maKieuNai) && Objects.equals(nhanVien, other.nhanVien)
				&& Objects.equals(trangThai, other.trangThai);
	}
	@Override
	public String toString() {
		return "MauKhieuNai [maKieuNai=" + maKieuNai + ", nhanVien=" + nhanVien + ", maKH=" + maKH + ", lyDoKhieuNai="
				+ lyDoKhieuNai + ", trangThai=" + trangThai + "]";
	}


}