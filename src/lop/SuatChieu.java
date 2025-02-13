package lop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SuatChieu {
	private String maSuatChieu;
	private Phim phim;
	private LocalDate ngayChieu;
	private LocalTime gioChieu;
	private PhongChieu phongChieu;
	private String dinhDang;
	private double giaVe;
	public SuatChieu(String maSuatChieu, Phim phim, LocalDate ngayChieu ,LocalTime gioChieu, PhongChieu phongChieu, String dinhDang,
			double giaVe) {
		super();
		this.maSuatChieu = maSuatChieu;
		this.phim = phim;
		this.ngayChieu = ngayChieu;
		this.phongChieu = phongChieu;
		this.dinhDang = dinhDang;
		this.giaVe = giaVe;
		this.gioChieu=gioChieu;
	}
	public SuatChieu() {
		super();
	}
	public SuatChieu(String maSuatChieu) {
		super();
		this.maSuatChieu = maSuatChieu;
	}
	public String getMaSuatChieu() {
		return maSuatChieu;
	}
	public void setMaSuatChieu(String maSuatChieu) {
		this.maSuatChieu = maSuatChieu;
	}
	public Phim getPhim() {
		return phim;
	}
	public void setPhim(Phim phim) {
		this.phim = phim;
	}
	public LocalDate getngayChieu() {
		return ngayChieu;
	}
	public void setngayChieu(LocalDate ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
	public PhongChieu getPhongChieu() {
		return phongChieu;
	}
	public void setPhongChieu(PhongChieu phongChieu) {
		this.phongChieu = phongChieu;
	}
	public String getDinhDang() {
		return dinhDang;
	}
	public void setDinhDang(String dinhDang) {
		this.dinhDang = dinhDang;
	}
	public double getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}
	
	public LocalTime getGioChieu() {
		return gioChieu;
	}
	public void setGioChieu(LocalTime gioChieu) {
		this.gioChieu = gioChieu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dinhDang, giaVe, maSuatChieu, ngayChieu, phim, phongChieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuatChieu other = (SuatChieu) obj;
		return Objects.equals(dinhDang, other.dinhDang)
				&& Double.doubleToLongBits(giaVe) == Double.doubleToLongBits(other.giaVe)
				&& Objects.equals(maSuatChieu, other.maSuatChieu) && Objects.equals(ngayChieu, other.ngayChieu)
				&& Objects.equals(phim, other.phim) && Objects.equals(phongChieu, other.phongChieu);
	}
	@Override
	public String toString() {
		return "SuatChieu [maSuatChieu=" + maSuatChieu + ", phim=" + phim + ", ngayChieu=" + ngayChieu
				+ ", phongChieu=" + phongChieu + ", dinhDang=" + dinhDang + ", giaVe=" + giaVe + "]";
	}
	

}