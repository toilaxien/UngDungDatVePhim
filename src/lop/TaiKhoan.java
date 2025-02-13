package lop;

import java.util.Objects;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private String maNV;
	public TaiKhoan(String tenDangNhap, String matKhau, String maNV) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.maNV=maNV;
	}
	
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String tenDangNhap) {
		super();
		this.tenDangNhap = tenDangNhap;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	@Override
	public int hashCode() {
		return Objects.hash(matKhau, tenDangNhap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(matKhau, other.matKhau) && Objects.equals(tenDangNhap, other.tenDangNhap);
	}
	
}