package starting;
import connectDB.ConnectDB;
public class Starting {
	public static void main(String[] args) {
		// Tạo kết nối
		ConnectDB db = ConnectDB.getInstance();
		db.connect();

		// Kiểm tra kết nối
		if (db.getConnection() != null) {
			System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
		} else {
			System.out.println("Kết nối đến cơ sở dữ liệu thất bại!");
		}

		// Đóng kết nối
		db.disconnect();
	}
}
