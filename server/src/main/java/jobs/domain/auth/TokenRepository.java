package jobs.domain.auth;

import codegen.jooq.tables.Token;
import codegen.jooq.tables.records.TokenRecord;
import org.jooq.DSLContext;

import java.util.UUID;

public class TokenRepository {
    private final DSLContext ctx;

    public TokenRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public TokenRecord create(String email) {
        var tokenRecord = ctx.newRecord(Token.TOKEN);

        tokenRecord.setEmail(email);
        tokenRecord.setId(UUID.randomUUID().toString());

        int stored = tokenRecord.store();

        if (stored == 1) {
            return tokenRecord;
        }

        return null;
    }

    public void deleteById(String id) {
        ctx.deleteFrom(Token.TOKEN).where(Token.TOKEN.ID.eq(id)).execute();
    }
}
