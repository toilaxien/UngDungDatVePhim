package lop;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String hoTen;
	private String email;
	private String sdt;
	private int diemTichLuy;

	public KhachHang(String maKH, String hoTen, String email, String sdt, int diemTichLuy) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.email = email;
		this.sdt = sdt;
		this.diemTichLuy = diemTichLuy;
	}

	public KhachHang() {
		super();
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diemTichLuy, email, hoTen, maKH, sdt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return diemTichLuy == other.diemTichLuy && Objects.equals(email, other.email)
				&& Objects.equals(hoTen, other.hoTen) && Objects.equals(maKH, other.maKH)
				&& Objects.equals(sdt, other.sdt);
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", email=" + email + ", sdt=" + sdt + ", diemTichLuy="
				+ diemTichLuy + "]";
	}

	public void tichDiem() {
		// TODO - implement KhachHang.tichDiem
		throw new UnsupportedOperationException();
	}

}