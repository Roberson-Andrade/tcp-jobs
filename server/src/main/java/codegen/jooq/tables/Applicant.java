/*
 * This file is generated by jOOQ.
 */
package codegen.jooq.tables;


import codegen.jooq.Keys;
import codegen.jooq.Public;
import codegen.jooq.tables.Token.TokenPath;
import codegen.jooq.tables.records.ApplicantRecord;

import java.util.Collection;

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
public class Applicant extends TableImpl<ApplicantRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.APPLICANT</code>
     */
    public static final Applicant APPLICANT = new Applicant();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ApplicantRecord> getRecordType() {
        return ApplicantRecord.class;
    }

    /**
     * The column <code>PUBLIC.APPLICANT.EMAIL</code>.
     */
    public final TableField<ApplicantRecord, String> EMAIL = createField(DSL.name("EMAIL"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.APPLICANT.NAME</code>.
     */
    public final TableField<ApplicantRecord, String> NAME = createField(DSL.name("NAME"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.APPLICANT.PASSWORD</code>.
     */
    public final TableField<ApplicantRecord, String> PASSWORD = createField(DSL.name("PASSWORD"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private Applicant(Name alias, Table<ApplicantRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Applicant(Name alias, Table<ApplicantRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>PUBLIC.APPLICANT</code> table reference
     */
    public Applicant(String alias) {
        this(DSL.name(alias), APPLICANT);
    }

    /**
     * Create an aliased <code>PUBLIC.APPLICANT</code> table reference
     */
    public Applicant(Name alias) {
        this(alias, APPLICANT);
    }

    /**
     * Create a <code>PUBLIC.APPLICANT</code> table reference
     */
    public Applicant() {
        this(DSL.name("APPLICANT"), null);
    }

    public <O extends Record> Applicant(Table<O> path, ForeignKey<O, ApplicantRecord> childPath, InverseForeignKey<O, ApplicantRecord> parentPath) {
        super(path, childPath, parentPath, APPLICANT);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ApplicantPath extends Applicant implements Path<ApplicantRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> ApplicantPath(Table<O> path, ForeignKey<O, ApplicantRecord> childPath, InverseForeignKey<O, ApplicantRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ApplicantPath(Name alias, Table<ApplicantRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ApplicantPath as(String alias) {
            return new ApplicantPath(DSL.name(alias), this);
        }

        @Override
        public ApplicantPath as(Name alias) {
            return new ApplicantPath(alias, this);
        }

        @Override
        public ApplicantPath as(Table<?> alias) {
            return new ApplicantPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ApplicantRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_2;
    }

    private transient TokenPath _token;

    /**
     * Get the implicit to-many join path to the <code>PUBLIC.TOKEN</code> table
     */
    public TokenPath token() {
        if (_token == null)
            _token = new TokenPath(this, null, Keys.CONSTRAINT_4C.getInverseKey());

        return _token;
    }

    @Override
    public Applicant as(String alias) {
        return new Applicant(DSL.name(alias), this);
    }

    @Override
    public Applicant as(Name alias) {
        return new Applicant(alias, this);
    }

    @Override
    public Applicant as(Table<?> alias) {
        return new Applicant(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Applicant rename(String name) {
        return new Applicant(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Applicant rename(Name name) {
        return new Applicant(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Applicant rename(Table<?> name) {
        return new Applicant(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Applicant where(Condition condition) {
        return new Applicant(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Applicant where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Applicant where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Applicant where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Applicant where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Applicant where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Applicant where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Applicant where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Applicant whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Applicant whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}