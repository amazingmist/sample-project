package poly.app.core.procedures;

import java.util.List;
import poly.app.core.data.daoimpl.ProcedureDaoImpl;
import poly.app.core.procedures.record.ThongKeDoanhThuRecord;

public class sp_ThongKeDoanhThu extends ProcedureDaoImpl<sp_ThongKeDoanhThu, ThongKeDoanhThuRecord> {
    private Integer year;
    
    public sp_ThongKeDoanhThu() {
    }
    
    public sp_ThongKeDoanhThu(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<ThongKeDoanhThuRecord> execute() throws NullPointerException{
        if (year == null) {
            throw new NullPointerException();
        }
        
        return super.execute(this.year);
    }
}
