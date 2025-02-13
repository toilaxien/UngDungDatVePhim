package lop;

import java.util.Objects;

public class PhongChieu {
	private String maPhong;
	private int soGhe;
	private String trangThai;
	public PhongChieu(String maPhong, int soGhe, String trangThai) {
		super();
		this.maPhong = maPhong;
		this.soGhe = soGhe;
		this.trangThai = trangThai;
	}
	public PhongChieu() {
		super();
	}
	public PhongChieu(String maPhong) {
		super();
		this.maPhong = maPhong;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public int getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhong, soGhe, trangThai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongChieu other = (PhongChieu) obj;
		return Objects.equals(maPhong, other.maPhong) && soGhe == other.soGhe
				&& Objects.equals(trangThai, other.trangThai);
	}
	@Override
	public String toString() {
		return "PhongChieu [maPhong=" + maPhong + ", soGhe=" + soGhe + ", trangThai=" + trangThai + "]";
	}

}