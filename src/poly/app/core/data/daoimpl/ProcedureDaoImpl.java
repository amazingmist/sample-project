package poly.app.core.data.daoimpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import poly.app.core.data.dao.ProcedureDao;
import poly.app.core.utils.HibernateUtil;

public class ProcedureDaoImpl<T, R> implements ProcedureDao<T, R> {

    private Class<T> procedureClass;
    private Class<R> recordClass;

    public ProcedureDaoImpl() {
        this.procedureClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.recordClass = (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public Class getProcedureClass() {
        return this.procedureClass;
    }

    public String getProcedureClassName() {
        return this.procedureClass.getSimpleName();
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    private R mappingPojo(Object[] objects) {
        try {
//            Create new instance from pojo class
            R recordObject = this.recordClass.newInstance();

//            Get field array form pojo class
            Field[] fields = recordClass.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
//                Get current field
                Field curField = this.recordClass.getDeclaredField(fields[i].getName());

//                Set can accesible this field
                curField.setAccessible(true);

//                Set value for this field
                curField.set(recordObject, objects[i]);

//                Set can not accesible this field
                curField.setAccessible(false);
            }

            return recordObject;
        } catch (Exception ex) {
            Logger.getLogger(ProcedureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<R> execute(Object... parameters) {
        List<Object[]> list = null;
        List<R> recordList = null;
        Session session = this.getSession();
        try {
//            Build call query
            StringBuilder builder = new StringBuilder("{CALL ");
            builder.append(this.getProcedureClassName());
            builder.append("(");
            String[] chars = new String[parameters.length];
            Arrays.fill(chars, "?");
            String requestInput = new String(String.join(",", chars));
            builder.append(requestInput);
            builder.append(")}");

            Query query = session.createSQLQuery(builder.toString());

//            Set parameter to query
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i, parameters[i]);
            }

//            get result list
            list = query.list();

            recordList = new ArrayList<R>();
            for (Object[] objects : list) {
//              convert object[] to R
                recordList.add(this.mappingPojo(objects));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return recordList;
    }
}