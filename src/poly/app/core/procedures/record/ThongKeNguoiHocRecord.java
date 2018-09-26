package poly.app.core.procedures.record;

import java.math.BigInteger;
import java.sql.Date;

public class ThongKeNguoiHocRecord {
    private Integer nam;
    private BigInteger soLuong;
    private Date ngayGhiDanhDau;
    private Date ngayGhiDanhCuoi;  

    public ThongKeNguoiHocRecord() {
    }

    public ThongKeNguoiHocRecord(Integer nam, BigInteger soLuong, Date ngayGhiDanhDau, Date ngayGhiDanhCuoi) {
        this.nam = nam;
        this.soLuong = soLuong;
        this.ngayGhiDanhDau = ngayGhiDanhDau;
        this.ngayGhiDanhCuoi = ngayGhiDanhCuoi;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public BigInteger getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(BigInteger soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayGhiDanhDau() {
        return ngayGhiDanhDau;
    }

    public void setNgayGhiDanhDau(Date ngayGhiDanhDau) {
        this.ngayGhiDanhDau = ngayGhiDanhDau;
    }

    public Date getNgayGhiDanhCuoi() {
        return ngayGhiDanhCuoi;
    }

    public void setNgayGhiDanhCuoi(Date ngayGhiDanhCuoi) {
        this.ngayGhiDanhCuoi = ngayGhiDanhCuoi;
    }

    @Override
    public String toString() {
        return "ThongKeGhiDanh{" + "nam=" + nam + ", soLuong=" + soLuong + ", ngayGhiDanhDau=" + ngayGhiDanhDau + ", ngayGhiDanhCuoi=" + ngayGhiDanhCuoi + '}';
    }
}