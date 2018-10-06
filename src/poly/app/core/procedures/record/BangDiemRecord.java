package poly.app.core.procedures.record;

public class BangDiemRecord {

    private String maNH;
    private String hoTen;
    private Float diem;

    public BangDiemRecord() {
    }

    public BangDiemRecord(String maNH, String hoTen, float diem) {
        this.maNH = maNH;
        this.hoTen = hoTen;
        this.diem = diem;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Float getDiem() {
        return diem;
    }

    public void setDiem(Float diem) {
        this.diem = diem;
    }

    public String getXepLoai() {
        String xepLoai = "";
        if (diem < 0) {
            xepLoai = "Chưa nhập";
        } else if (diem < 3) {
            xepLoai = "Kém";
        } else if (diem < 5) {
            xepLoai = "Yếu";
        } else if (diem < 6.5) {
            xepLoai = "Trung bình";
        } else if (diem < 7.5) {
            xepLoai = "Khá";
        } else if (diem < 9) {
            xepLoai = "Giỏi";
        }else{
            xepLoai = "Xuất sắc";
        }
        return xepLoai;
    }

    @Override
    public String toString() {
        return "DiemHocVien{" + "maNH=" + maNH + ", hoTen=" + hoTen + ", diem=" + diem + "}";
    }
}
