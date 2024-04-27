package vacancy.database;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.SQLException;

public class Configuration {
    private final HikariDataSource ds;

    public Configuration() {
        ds = new HikariDataSource();
        ds.setDriverClassName("org.h2.jdbcx.JdbcDataSource");
        ds.setJdbcUrl("jdbc:h2:./target/database;DB_CLOSE_DELAY=-1");
        ds.setUsername("sa");
        ds.setPassword("");
    }

    public DSLContext createDSLContext() throws SQLException {
        return DSL.using(ds, SQLDialect.H2);
    }
}
