package poly.app.core.procedures.record;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ThongKeDoanhThuRecord {
    private String tenChuyenDe;
    private BigInteger soKhoaHoc;
    private BigInteger soHocVien;
    private BigDecimal doanhThu;
    private Integer hocPhiCaoNhat;
    private Integer hocPhiThapNhat;
    private BigDecimal hocPhiTrungBinh;

    public ThongKeDoanhThuRecord() {
    }

    public ThongKeDoanhThuRecord(String tenChuyenDe, BigInteger soKhoaHoc, BigInteger soHocVien, BigDecimal doanhThu, Integer hocPhiCaoNhat, Integer hocPhiThapNhat, BigDecimal hocPhiTrungBinh) {
        this.tenChuyenDe = tenChuyenDe;
        this.soKhoaHoc = soKhoaHoc;
        this.soHocVien = soHocVien;
        this.doanhThu = doanhThu;
        this.hocPhiCaoNhat = hocPhiCaoNhat;
        this.hocPhiThapNhat = hocPhiThapNhat;
        this.hocPhiTrungBinh = hocPhiTrungBinh;
    }

    public String getTenChuyenDe() {
        return tenChuyenDe;
    }

    public void setTenChuyenDe(String tenChuyenDe) {
        this.tenChuyenDe = tenChuyenDe;
    }

    public BigInteger getSoKhoaHoc() {
        return soKhoaHoc;
    }

    public void setSoKhoaHoc(BigInteger soKhoaHoc) {
        this.soKhoaHoc = soKhoaHoc;
    }

    public BigInteger getSoHocVien() {
        return soHocVien;
    }

    public void setSoHocVien(BigInteger soHocVien) {
        this.soHocVien = soHocVien;
    }

    public BigDecimal getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(BigDecimal doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Integer getHocPhiCaoNhat() {
        return hocPhiCaoNhat;
    }

    public void setHocPhiCaoNhat(Integer hocPhiCaoNhat) {
        this.hocPhiCaoNhat = hocPhiCaoNhat;
    }

    public Integer getHocPhiThapNhat() {
        return hocPhiThapNhat;
    }

    public void setHocPhiThapNhat(Integer hocPhiThapNhat) {
        this.hocPhiThapNhat = hocPhiThapNhat;
    }

    public BigDecimal getHocPhiTrungBinh() {
        return hocPhiTrungBinh;
    }

    public void setHocPhiTrungBinh(BigDecimal hocPhiTrungBinh) {
        this.hocPhiTrungBinh = hocPhiTrungBinh;
    }

    

    @Override
    public String toString() {
        return "DoanhThuChuyenDe{" + "tenChuyenDe=" + tenChuyenDe + ", soKhoaHoc=" + soKhoaHoc + ", soHocVien=" + soHocVien + ", doanhThu=" + doanhThu + ", hocPhiCaoNhat=" + hocPhiCaoNhat + ", hocPhiThapNhat=" + hocPhiThapNhat + ", hocPhiTrungBinh=" + hocPhiTrungBinh + '}';
    }   
}