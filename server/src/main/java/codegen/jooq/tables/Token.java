/*
 * This file is generated by jOOQ.
 */
package codegen.jooq.tables;


import codegen.jooq.Keys;
import codegen.jooq.Public;
import codegen.jooq.tables.Applicant.ApplicantPath;
import codegen.jooq.tables.records.TokenRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Token extends TableImpl<TokenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.TOKEN</code>
     */
    public static final Token TOKEN = new Token();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TokenRecord> getRecordType() {
        return TokenRecord.class;
    }

    /**
     * The column <code>PUBLIC.TOKEN.ID</code>.
     */
    public final TableField<TokenRecord, String> ID = createField(DSL.name("ID"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TOKEN.APPLICANT_EMAIL</code>.
     */
    public final TableField<TokenRecord, String> APPLICANT_EMAIL = createField(DSL.name("APPLICANT_EMAIL"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private Token(Name alias, Table<TokenRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Token(Name alias, Table<TokenRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>PUBLIC.TOKEN</code> table reference
     */
    public Token(String alias) {
        this(DSL.name(alias), TOKEN);
    }

    /**
     * Create an aliased <code>PUBLIC.TOKEN</code> table reference
     */
    public Token(Name alias) {
        this(alias, TOKEN);
    }

    /**
     * Create a <code>PUBLIC.TOKEN</code> table reference
     */
    public Token() {
        this(DSL.name("TOKEN"), null);
    }

    public <O extends Record> Token(Table<O> path, ForeignKey<O, TokenRecord> childPath, InverseForeignKey<O, TokenRecord> parentPath) {
        super(path, childPath, parentPath, TOKEN);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TokenPath extends Token implements Path<TokenRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TokenPath(Table<O> path, ForeignKey<O, TokenRecord> childPath, InverseForeignKey<O, TokenRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TokenPath(Name alias, Table<TokenRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TokenPath as(String alias) {
            return new TokenPath(DSL.name(alias), this);
        }

        @Override
        public TokenPath as(Name alias) {
            return new TokenPath(alias, this);
        }

        @Override
        public TokenPath as(Table<?> alias) {
            return new TokenPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<TokenRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_4;
    }

    @Override
    public List<ForeignKey<TokenRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_4C);
    }

    private transient ApplicantPath _applicant;

    /**
     * Get the implicit join path to the <code>PUBLIC.APPLICANT</code> table.
     */
    public ApplicantPath applicant() {
        if (_applicant == null)
            _applicant = new ApplicantPath(this, Keys.CONSTRAINT_4C, null);

        return _applicant;
    }

    @Override
    public Token as(String alias) {
        return new Token(DSL.name(alias), this);
    }

    @Override
    public Token as(Name alias) {
        return new Token(alias, this);
    }

    @Override
    public Token as(Table<?> alias) {
        return new Token(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Token rename(String name) {
        return new Token(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Token rename(Name name) {
        return new Token(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Token rename(Table<?> name) {
        return new Token(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Token where(Condition condition) {
        return new Token(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Token where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Token where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Token where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Token where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Token where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Token where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Token where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Token whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Token whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
