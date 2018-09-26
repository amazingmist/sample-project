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

public class ProcedureDaoImpl<T, POJO> implements ProcedureDao<T, POJO> {

    private Class<T> spClass;
    private Class<POJO> pojoClass;

    public ProcedureDaoImpl() {
        this.spClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.pojoClass = (Class<POJO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public Class getProcedureClass() {
        return this.spClass;
    }

    public String getProcedureClassName() {
        return this.spClass.getSimpleName();
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    private POJO mappingPojo(Object[] objects) {
        try {
//            Create new instance from pojo class
            POJO pojoObject = this.pojoClass.newInstance();

//            Get field array form pojo class
            Field[] fields = pojoClass.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
//                Get current field
                Field curField = this.pojoClass.getDeclaredField(fields[i].getName());

//                Set can accesible this field
                curField.setAccessible(true);

//                Set value for this field
                curField.set(pojoObject, objects[i]);

//                Set can not accesible this field
                curField.setAccessible(false);
            }

            return pojoObject;
        } catch (Exception ex) {
            Logger.getLogger(ProcedureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<POJO> execute(Object... parameters) {
        List<Object[]> list = null;
        List<POJO> pojoList = null;
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

            pojoList = new ArrayList<POJO>();
            for (Object[] objects : list) {
//              convert object[] to POJO
                pojoList.add(this.mappingPojo(objects));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pojoList;
    }
}
