package lop;

import java.time.LocalDate;
import java.util.Objects;

public class Phim {
	private String maPhim;
	private String ten;
	private String theLoai;
	private String daoDien;
	private String dienVienChinh;
	private int thoiLuong;
	private LocalDate ngayPhatHanh;
	private int gioiHanDoTuoi;
	private String moTaPhim;
	private String ngonNgu;
	private String dinhDang;
	private String nhaSX;

	public Phim(String maPhim, String ten, String theLoai, String daoDien, String dienVienChinh, int thoiLuong,
			LocalDate ngayPhatHanh, int gioiHanDoTuoi, String moTaPhim, String ngonNgu, String dinhDang, String nhaSX) {
		super();
		this.maPhim = maPhim;
		this.ten = ten;
		this.theLoai = theLoai;
		this.daoDien = daoDien;
		this.dienVienChinh = dienVienChinh;
		this.thoiLuong = thoiLuong;
		this.ngayPhatHanh = ngayPhatHanh;
		this.gioiHanDoTuoi = gioiHanDoTuoi;
		this.moTaPhim = moTaPhim;
		this.ngonNgu = ngonNgu;
		this.dinhDang = dinhDang;
		this.nhaSX = nhaSX;
	}

	public Phim() {
		super();
	}

	public Phim(String maPhim) {
		super();
		this.maPhim = maPhim;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public String getDienVienChinh() {
		return dienVienChinh;
	}

	public void setDienVienChinh(String dienVienChinh) {
		this.dienVienChinh = dienVienChinh;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public LocalDate getNgayPhatHanh() {
		return ngayPhatHanh;
	}

	public void setNgayPhatHanh(LocalDate ngayPhatHanh) {
		this.ngayPhatHanh = ngayPhatHanh;
	}

	public int getGioiHanDoTuoi() {
		return gioiHanDoTuoi;
	}

	public void setGioiHanDoTuoi(int gioiHanDoTuoi) {
		this.gioiHanDoTuoi = gioiHanDoTuoi;
	}

	public String getMoTaPhim() {
		return moTaPhim;
	}

	public void setMoTaPhim(String moTaPhim) {
		this.moTaPhim = moTaPhim;
	}

	public String getNgonNgu() {
		return ngonNgu;
	}

	public void setNgonNgu(String ngonNgu) {
		this.ngonNgu = ngonNgu;
	}

	public String getDinhDang() {
		return dinhDang;
	}

	public void setDinhDang(String dinhDang) {
		this.dinhDang = dinhDang;
	}

	public String getNhaSX() {
		return nhaSX;
	}

	public void setNhaSX(String nhaSX) {
		this.nhaSX = nhaSX;
	}

	@Override
	public int hashCode() {
		return Objects.hash(daoDien, dienVienChinh, dinhDang, gioiHanDoTuoi, maPhim, moTaPhim, ngayPhatHanh, ngonNgu,
				nhaSX, ten, theLoai, thoiLuong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phim other = (Phim) obj;
		return Objects.equals(daoDien, other.daoDien) && Objects.equals(dienVienChinh, other.dienVienChinh)
				&& Objects.equals(dinhDang, other.dinhDang) && gioiHanDoTuoi == other.gioiHanDoTuoi
				&& Objects.equals(maPhim, other.maPhim) && moTaPhim == other.moTaPhim
				&& Objects.equals(ngayPhatHanh, other.ngayPhatHanh) && Objects.equals(ngonNgu, other.ngonNgu)
				&& Objects.equals(nhaSX, other.nhaSX) && Objects.equals(ten, other.ten)
				&& Objects.equals(theLoai, other.theLoai) && thoiLuong == other.thoiLuong;
	}

	@Override
	public String toString() {
		return "Phim [maPhim=" + maPhim + ", ten=" + ten + ", theLoai=" + theLoai + ", daoDien=" + daoDien
				+ ", dienVienChinh=" + dienVienChinh + ", thoiLuong=" + thoiLuong + ", ngayPhatHanh=" + ngayPhatHanh
				+ ", gioiHanDoTuoi=" + gioiHanDoTuoi + ", moTaPhim=" + moTaPhim + ", ngonNgu=" + ngonNgu + ", dinhDang="
				+ dinhDang + ", nhaSX=" + nhaSX + "]";
	}

}