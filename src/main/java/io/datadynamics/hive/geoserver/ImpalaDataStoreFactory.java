package io.datadynamics.hive.geoserver;

import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.JDBCDataStoreFactory;
import org.geotools.jdbc.SQLDialect;

import java.io.IOException;
import java.util.Map;

public class ImpalaDataStoreFactory extends JDBCDataStoreFactory {

    public static final Param HOST = new Param("host", String.class, "Impala Host", true);
    public static final Param PORT = new Param("port", Integer.class, "Impala Port", true, 21050);
    public static final Param DATABASE = new Param("database", String.class, "Database", true);
    public static final Param USER = new Param("user", String.class, "User", false);
    public static final Param PASSWD = new Param("passwd", String.class, "Password", false);
    public static final Param AUTHMECH = new Param("authmech", String.class, "AuthMech", false);

    @Override
    public String getDisplayName() {
        return "Impala JDBC DataStore";
    }

    @Override
    protected String getDatabaseID() {
        return "impala";
    }

    @Override
    public String getDescription() {
        return "Impala/Hive DataStore";
    }

    @Override
    protected String getDriverClassName() {
        return "com.cloudera.impala.jdbc.Driver";
    }

    @Override
    protected SQLDialect createSQLDialect(JDBCDataStore jdbcDataStore) {
        return new ImpalaDialect(jdbcDataStore);
    }

    @Override
    protected String getValidationQuery() {
        return "SELECT 1";
    }

    @Override
    protected String getJDBCUrl(Map params) throws IOException {
        String host = (String) HOST.lookUp(params);
        Integer port = (Integer) PORT.lookUp(params);
        String database = (String) DATABASE.lookUp(params);
        String username = (String) USER.lookUp(params);
        String passwd = (String) PASSWD.lookUp(params);
        String authMech = (String) AUTHMECH.lookUp(params);
        return String.format("jdbc:impala://%s:%d/%s;UID=%s;PASSWD=%s;AuthMech=%s;", host, port, database, username, passwd, authMech);
    }
}