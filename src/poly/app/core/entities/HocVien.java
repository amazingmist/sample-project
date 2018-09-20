package poly.app.core.entities;
// Generated Sep 20, 2018 2:53:45 PM by Hibernate Tools 4.3.1



/**
 * HocVien generated by hbm2java
 */
public class HocVien  implements java.io.Serializable {


     private Integer maHv;
     private KhoaHoc khoaHoc;
     private NguoiHoc nguoiHoc;
     private float diem;

    public HocVien() {
    }

    public HocVien(KhoaHoc khoaHoc, NguoiHoc nguoiHoc, float diem) {
       this.khoaHoc = khoaHoc;
       this.nguoiHoc = nguoiHoc;
       this.diem = diem;
    }
   
    public Integer getMaHv() {
        return this.maHv;
    }
    
    public void setMaHv(Integer maHv) {
        this.maHv = maHv;
    }
    public KhoaHoc getKhoaHoc() {
        return this.khoaHoc;
    }
    
    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }
    public NguoiHoc getNguoiHoc() {
        return this.nguoiHoc;
    }
    
    public void setNguoiHoc(NguoiHoc nguoiHoc) {
        this.nguoiHoc = nguoiHoc;
    }
    public float getDiem() {
        return this.diem;
    }
    
    public void setDiem(float diem) {
        this.diem = diem;
    }




}


