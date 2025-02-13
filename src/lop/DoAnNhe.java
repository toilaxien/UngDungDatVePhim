package lop;

import java.util.Objects;

public class DoAnNhe {
	private String maDoAnNhe;
	private String tenDoAnNhe;
	private float gia;
	private int soLuongCon;

	public DoAnNhe(String maDoAnNhe, String tenDoAnNhe, float gia, int soLuongCon) {
		super();
		this.maDoAnNhe = maDoAnNhe;
		this.tenDoAnNhe = tenDoAnNhe;
		this.gia = gia;
		this.soLuongCon = soLuongCon;
	}

	public DoAnNhe(String maDoAnNhe) {
		super();
		this.maDoAnNhe = maDoAnNhe;
	}

	public DoAnNhe() {
		super();
	}

	public String getMaDoAnNhe() {
		return maDoAnNhe;
	}

	public void setMaDoAnNhe(String maDoAnNhe) {
		this.maDoAnNhe = maDoAnNhe;
	}

	public String getTenDoAnNhe() {
		return tenDoAnNhe;
	}

	public void setTenDoAnNhe(String tenDoAnNhe) {
		this.tenDoAnNhe = tenDoAnNhe;
	}

	public float getGia() {
		return gia;
	}

	public void setGia(float gia) {
		this.gia = gia;
	}

	public int getSoLuongCon() {
		return soLuongCon;
	}

	public void setSoLuongCon(int soLuongCon) {
		this.soLuongCon = soLuongCon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gia, maDoAnNhe, soLuongCon, tenDoAnNhe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoAnNhe other = (DoAnNhe) obj;
		return Float.floatToIntBits(gia) == Float.floatToIntBits(other.gia)
				&& Objects.equals(maDoAnNhe, other.maDoAnNhe) && soLuongCon == other.soLuongCon
				&& Objects.equals(tenDoAnNhe, other.tenDoAnNhe);
	}

	@Override
	public String toString() {
		return "DoAnNhe [maDoAnNhe=" + maDoAnNhe + ", tenDoAnNhe=" + tenDoAnNhe + ", gia=" + gia + ", soLuongCon="
				+ soLuongCon + "]";
	}

}