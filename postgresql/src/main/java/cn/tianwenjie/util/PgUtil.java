package cn.tianwenjie.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

/**
 * PG工具类
 *
 * @author tianwj
 * @date 2021/11/30 17:20
 */
public class PgUtil {
    private static PGSimpleDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new PGSimpleDataSource();
            dataSource.setServerNames(new String[]{"172.30.176.217"});
            dataSource.setPortNumbers(new int[5432]);
            dataSource.setDatabaseName("postgis");
            dataSource.setUser("postgres");
            dataSource.setPassword("123456");
        }

        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

}
