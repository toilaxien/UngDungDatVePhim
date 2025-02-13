package lop;

import java.util.Objects;

public class Ghe {

	private String maGhe;
	private String trangThai;
	public Ghe(String maGhe, String trangThai) {
		super();
		this.maGhe = maGhe;
		this.trangThai = trangThai;
	}
	public Ghe() {
		super();
	}
	public Ghe(String maGhe) {
		super();
		this.maGhe = maGhe;
	}
	public String getMaGhe() {
		return maGhe;
	}
	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maGhe, trangThai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ghe other = (Ghe) obj;
		return Objects.equals(maGhe, other.maGhe) && Objects.equals(trangThai, other.trangThai);
	}
	@Override
	public String toString() {
		return "Ghe [maGhe=" + maGhe + ", trangThai=" + trangThai + "]";
	}

}