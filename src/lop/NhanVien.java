package lop;

import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private String diaChi;
	private String ngaySinh;
	private String ngayVaoLam;
	private String email;
	private String sdt;
	private String congViec;
	private String caLamViec;
	public NhanVien(String maNV, String hoTen, String diaChi, String ngaySinh, String ngayVaoLam, String email,
			String sdt, String congViec, String caLamViec) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
		this.email = email;
		this.sdt = sdt;
		this.congViec = congViec;
		this.caLamViec = caLamViec;
	}
	public NhanVien() {
		super();
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(String ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
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
	public String getCongViec() {
		return congViec;
	}
	public void setCongViec(String congViec) {
		this.congViec = congViec;
	}
	public String getCaLamViec() {
		return caLamViec;
	}
	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}
	@Override
	public int hashCode() {
		return Objects.hash(caLamViec, congViec, diaChi, email, hoTen, maNV, ngaySinh, ngayVaoLam, sdt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(caLamViec, other.caLamViec) && Objects.equals(congViec, other.congViec)
				&& Objects.equals(diaChi, other.diaChi) && Objects.equals(email, other.email)
				&& Objects.equals(hoTen, other.hoTen) && Objects.equals(maNV, other.maNV)
				&& Objects.equals(ngaySinh, other.ngaySinh) && Objects.equals(ngayVaoLam, other.ngayVaoLam)
				&& Objects.equals(sdt, other.sdt);
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh
				+ ", ngayVaoLam=" + ngayVaoLam + ", email=" + email + ", sdt=" + sdt + ", congViec=" + congViec
				+ ", caLamViec=" + caLamViec + "]";
	}

}