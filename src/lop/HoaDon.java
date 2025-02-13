package lop;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private Ve ve;
	private DoAnNhe doAnNhe;
	private int soLuongDoAn;
	private String maKH;
	private LocalDate ngayLap;

	public HoaDon(String maHoaDon, Ve ve, DoAnNhe doAnNhe, int soLuongDoAn, String maKH, LocalDate ngayLap) {
		super();
		this.maHoaDon = maHoaDon;
		this.ve = ve;
		this.doAnNhe = doAnNhe;
		this.soLuongDoAn = soLuongDoAn;
		this.maKH = maKH;
		this.ngayLap = ngayLap;
	}

	public HoaDon() {
		super();
	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Ve getVe() {
		return ve;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
	}

	public DoAnNhe getDoAnNhe() {
		return doAnNhe;
	}

	public void setDoAnNhe(DoAnNhe doAnNhe) {
		this.doAnNhe = doAnNhe;
	}

	public int getSoLuongDoAn() {
		return soLuongDoAn;
	}

	public void setSoLuongDoAn(int soLuongDoAn) {
		this.soLuongDoAn = soLuongDoAn;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ve=" + ve + ", doAnNhe=" + doAnNhe + ", soLuongDoAn=" + soLuongDoAn
				+ ", maKH=" + maKH + ", ngayLap=" + ngayLap + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(doAnNhe, maHoaDon, maKH, ngayLap, soLuongDoAn, ve);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(doAnNhe, other.doAnNhe) && Objects.equals(maHoaDon, other.maHoaDon)
				&& Objects.equals(maKH, other.maKH) && Objects.equals(ngayLap, other.ngayLap)
				&& soLuongDoAn == other.soLuongDoAn && Objects.equals(ve, other.ve);
	}

	public double tinhThanhTien() {
		double giaVe = ve.getGiaVe();
		double giaDoAn = doAnNhe.getGia()*soLuongDoAn;
		double thanhTien = giaVe + giaDoAn;
		return thanhTien;
	}

}