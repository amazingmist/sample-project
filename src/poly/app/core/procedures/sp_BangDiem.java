package poly.app.core.procedures;

import java.util.List;
import poly.app.core.procedures.record.BangDiemRecord;
import poly.app.core.data.daoimpl.ProcedureDaoImpl;

public class sp_BangDiem extends ProcedureDaoImpl<sp_BangDiem, BangDiemRecord> {
    private Integer maKhoaHoc;

    public sp_BangDiem() {
    }

    public sp_BangDiem(Integer maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public Integer getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(Integer maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }
    
    public List<BangDiemRecord> execute() throws NullPointerException{
        if (maKhoaHoc == null) {
            throw new NullPointerException();
        }
        
        return super.execute(this.maKhoaHoc);
    }
}