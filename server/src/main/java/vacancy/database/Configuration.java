package vacancy.database;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.SQLException;

public class Configuration {
    private final JdbcDataSource dataSource;

    public Configuration() {
        dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:~/server/target/jooq-codegen;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("123");
    }

    public DSLContext createDSLContext() throws SQLException {
        return DSL.using(dataSource.getConnection(), SQLDialect.MYSQL);
    }
}
