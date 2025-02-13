package lop;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
	private String maKM;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private float giamGia;
	private String moTa;

	public KhuyenMai(String maKM, LocalDate ngayBatDau, LocalDate ngayKetThuc, float giamGia, String moTa) {
		super();
		this.maKM = maKM;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.giamGia = giamGia;
		this.moTa = moTa;
	}

	public KhuyenMai() {
		super();
	}

	public KhuyenMai(String maKM) {
		super();
		this.maKM = maKM;
	}

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public float getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(float giamGia) {
		this.giamGia = giamGia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(giamGia, maKM, moTa, ngayBatDau, ngayKetThuc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Float.floatToIntBits(giamGia) == Float.floatToIntBits(other.giamGia) && Objects.equals(maKM, other.maKM)
				&& Objects.equals(moTa, other.moTa) && Objects.equals(ngayBatDau, other.ngayBatDau)
				&& Objects.equals(ngayKetThuc, other.ngayKetThuc);
	}

	@Override
	public String toString() {
		return "KhuyenMai [maKM=" + maKM + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", giamGia="
				+ giamGia + ", moTa=" + moTa + "]";
	}

}