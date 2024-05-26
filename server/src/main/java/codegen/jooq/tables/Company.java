/*
 * This file is generated by jOOQ.
 */
package codegen.jooq.tables;


import codegen.jooq.Keys;
import codegen.jooq.Public;
import codegen.jooq.tables.records.CompanyRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
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
public class Company extends TableImpl<CompanyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.COMPANY</code>
     */
    public static final Company COMPANY = new Company();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CompanyRecord> getRecordType() {
        return CompanyRecord.class;
    }

    /**
     * The column <code>PUBLIC.COMPANY.CODE</code>.
     */
    public final TableField<CompanyRecord, Integer> CODE = createField(DSL.name("CODE"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.COMPANY.BUSINESS_NAME</code>.
     */
    public final TableField<CompanyRecord, String> BUSINESS_NAME = createField(DSL.name("BUSINESS_NAME"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.COMPANY.SECTOR</code>.
     */
    public final TableField<CompanyRecord, String> SECTOR = createField(DSL.name("SECTOR"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.COMPANY.DESCRIPTION</code>.
     */
    public final TableField<CompanyRecord, String> DESCRIPTION = createField(DSL.name("DESCRIPTION"), SQLDataType.VARCHAR(1000000000), this, "");

    /**
     * The column <code>PUBLIC.COMPANY.EMAIL</code>.
     */
    public final TableField<CompanyRecord, String> EMAIL = createField(DSL.name("EMAIL"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.COMPANY.PASSWORD</code>.
     */
    public final TableField<CompanyRecord, String> PASSWORD = createField(DSL.name("PASSWORD"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.COMPANY.CNPJ</code>.
     */
    public final TableField<CompanyRecord, String> CNPJ = createField(DSL.name("CNPJ"), SQLDataType.VARCHAR(18).nullable(false), this, "");

    private Company(Name alias, Table<CompanyRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Company(Name alias, Table<CompanyRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>PUBLIC.COMPANY</code> table reference
     */
    public Company(String alias) {
        this(DSL.name(alias), COMPANY);
    }

    /**
     * Create an aliased <code>PUBLIC.COMPANY</code> table reference
     */
    public Company(Name alias) {
        this(alias, COMPANY);
    }

    /**
     * Create a <code>PUBLIC.COMPANY</code> table reference
     */
    public Company() {
        this(DSL.name("COMPANY"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CompanyRecord, Integer> getIdentity() {
        return (Identity<CompanyRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<CompanyRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_6;
    }

    @Override
    public List<UniqueKey<CompanyRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.CONSTRAINT_63, Keys.CONSTRAINT_637);
    }

    @Override
    public Company as(String alias) {
        return new Company(DSL.name(alias), this);
    }

    @Override
    public Company as(Name alias) {
        return new Company(alias, this);
    }

    @Override
    public Company as(Table<?> alias) {
        return new Company(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Company rename(String name) {
        return new Company(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Company rename(Name name) {
        return new Company(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Company rename(Table<?> name) {
        return new Company(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Company where(Condition condition) {
        return new Company(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Company where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Company where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Company where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Company where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Company where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Company where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Company where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Company whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Company whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
