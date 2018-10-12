package poly.app.core.helper;

public class ObjectStructureHelper {
    public static final String[] NHANVIEN_PROPERTIES = {"maNv", "hoTen", "dienThoai", "email", "vaiTro"};
    public static final String[] NHANVIEN_TABLE_IDENTIFIERS = {"Mã nhân viên", "Họ và tên", "Điện thoại", "Email", "Vai trò"};
    
    public static final String[] THONGKENGUOIHOC_TABLE_IDENTIFIERS = {"Năm", "Số người học", "Đầu tiên", "Sau cùng"};
    public static final String[] THONGKEDIEM_TABLE_IDENTIFIERS = {"Mã người học", "Họ và tên", "Điểm", "Xếp loại"};
    public static final String[] THONGKETONGHOPDIEM_TABLE_IDENTIFIERS = {"Chuyên đề", "Số học viên", "Điểm cao nhất", "Điểm thấp nhất", "Điểm trung bình"};
    public static final String[] THONGKEDOANHTHU_TABLE_IDENTIFIERS = {"Chuyên đề", "Số khoá", "Số học viên", "Doanh thu", "HP cao nhất", "HP thấp nhất", "Trung bình"};
    
    public static final String[] NGUOIHOC_PROPERTIES = {"maNh", "hoTen", "dienThoai", "email", "ngayDk"};
    public static final String[] NGUOIHOC_TABLE_IDENTIFIERS = {"Mã người học", "Họ và tên", "Điện thoại", "Email", "Ngày đăng ký"};
    
    public static final String[] CHUYENDE_PROPERTIES = {"maCd", "tenCd", "hocPhi", "thoiLuong", "hinh"};
    public static final String[] CHUYENDE_TABLE_IDENTIFIERS = {"Mã chuyên đề", "Tên chuyên đề", "Học phí", "Thời lượng", "Hình"};
    
    public static final String[] KHOAHOC_PROPERTIES = {"maKh", "chuyenDe", "thoiLuong", "hocPhi", "ngayKg", "nhanVien", "ngayTao"};
    public static final String[] KHOAHOC_TABLE_IDENTIFIERS = {"Mã khoá học", "Mã chuyên đề", "Thời lượng", "Học phí","Ngày khai giảng", "Người tạo", "Ngày tạo"};
    
    public static final String[] HOCVIEN_PROPERTIES = {"maHv", "nguoiHoc", "nguoiHoc", "diem"};
    public static final String[] HOCVIEN_TABLE_IDENTIFIERS = {"Mã học viên", "Mã người học", "Họ và tên", "Điểm số", "Xếp loại", "Xoá"};
    
    public static final String[] HOCVIENKHAC_TABLE_IDENTIFIERS = {"Mã người học", "Họ và tên", "Điểm số"};
}
