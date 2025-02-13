package lop;

import java.util.Objects;

public class Ve {
	private String maVe;
	private SuatChieu suatChieu;
	private Ghe ghe;
	private double giaVe;
	public Ve(String maVe, SuatChieu suatChieu, Ghe ghe, double giaVe) {
		super();
		this.maVe = maVe;
		this.suatChieu = suatChieu;
		this.ghe = ghe;
		this.giaVe = giaVe;
	}
	public Ve() {
		super();
	}
	public Ve(String maVe) {
		super();
		this.maVe = maVe;
	}
	public String getMaVe() {
		return maVe;
	}
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}
	public SuatChieu getSuatChieu() {
		return suatChieu;
	}
	public void setSuatChieu(SuatChieu suatChieu) {
		this.suatChieu = suatChieu;
	}
	public Ghe getGhe() {
		return ghe;
	}
	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}
	public double getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}
	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", suatChieu=" + suatChieu + ", ghe=" + ghe + ", giaVe=" + giaVe + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(ghe, giaVe, maVe, suatChieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		return Objects.equals(ghe, other.ghe) && Double.doubleToLongBits(giaVe) == Double.doubleToLongBits(other.giaVe)
				&& Objects.equals(maVe, other.maVe) && Objects.equals(suatChieu, other.suatChieu);
	}
	

}