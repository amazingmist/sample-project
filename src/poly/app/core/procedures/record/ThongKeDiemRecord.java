package poly.app.core.procedures.record;

import java.math.BigInteger;

public class ThongKeDiemRecord{
    private String tenChuyenDe;
    private BigInteger soHocVien;
    private Float diemCaoNhat;
    private Float diemThapNhat;
    private Double diemTrungBinh;

    public ThongKeDiemRecord() {
    }

    public ThongKeDiemRecord(String tenChuyenDe, BigInteger soHocVien, Float diemCaoNhat, Float diemThapNhat, Double diemTrungBinh) {
        this.tenChuyenDe = tenChuyenDe;
        this.soHocVien = soHocVien;
        this.diemCaoNhat = diemCaoNhat;
        this.diemThapNhat = diemThapNhat;
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getTenChuyenDe() {
        return tenChuyenDe;
    }

    public void setTenChuyenDe(String tenChuyenDe) {
        this.tenChuyenDe = tenChuyenDe;
    }

    public BigInteger getSoHocVien() {
        return soHocVien;
    }

    public void setSoHocVien(BigInteger soHocVien) {
        this.soHocVien = soHocVien;
    }

    public Float getDiemCaoNhat() {
        return diemCaoNhat;
    }

    public void setDiemCaoNhat(Float diemCaoNhat) {
        this.diemCaoNhat = diemCaoNhat;
    }

    public Float getDiemThapNhat() {
        return diemThapNhat;
    }

    public void setDiemThapNhat(Float diemThapNhat) {
        this.diemThapNhat = diemThapNhat;
    }

    public Double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(Double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    @Override
    public String toString() {
        return "DiemChuyenDe{" + "tenChuyenDe=" + tenChuyenDe + ", soHocVien=" + soHocVien + ", diemCaoNhat=" + diemCaoNhat + ", diemThapNhat=" + diemThapNhat + ", diemTrungBinh=" + diemTrungBinh + '}';
    }
}