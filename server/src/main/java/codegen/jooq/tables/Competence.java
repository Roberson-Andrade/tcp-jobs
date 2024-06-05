/*
 * This file is generated by jOOQ.
 */
package codegen.jooq.tables;


import codegen.jooq.Keys;
import codegen.jooq.Public;
import codegen.jooq.tables.ApplicantCompetence.ApplicantCompetencePath;
import codegen.jooq.tables.records.CompetenceRecord;

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
public class Competence extends TableImpl<CompetenceRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.COMPETENCE</code>
     */
    public static final Competence COMPETENCE = new Competence();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CompetenceRecord> getRecordType() {
        return CompetenceRecord.class;
    }

    /**
     * The column <code>PUBLIC.COMPETENCE.ID</code>.
     */
    public final TableField<CompetenceRecord, String> ID = createField(DSL.name("ID"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    private Competence(Name alias, Table<CompetenceRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Competence(Name alias, Table<CompetenceRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>PUBLIC.COMPETENCE</code> table reference
     */
    public Competence(String alias) {
        this(DSL.name(alias), COMPETENCE);
    }

    /**
     * Create an aliased <code>PUBLIC.COMPETENCE</code> table reference
     */
    public Competence(Name alias) {
        this(alias, COMPETENCE);
    }

    /**
     * Create a <code>PUBLIC.COMPETENCE</code> table reference
     */
    public Competence() {
        this(DSL.name("COMPETENCE"), null);
    }

    public <O extends Record> Competence(Table<O> path, ForeignKey<O, CompetenceRecord> childPath, InverseForeignKey<O, CompetenceRecord> parentPath) {
        super(path, childPath, parentPath, COMPETENCE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class CompetencePath extends Competence implements Path<CompetenceRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> CompetencePath(Table<O> path, ForeignKey<O, CompetenceRecord> childPath, InverseForeignKey<O, CompetenceRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private CompetencePath(Name alias, Table<CompetenceRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public CompetencePath as(String alias) {
            return new CompetencePath(DSL.name(alias), this);
        }

        @Override
        public CompetencePath as(Name alias) {
            return new CompetencePath(alias, this);
        }

        @Override
        public CompetencePath as(Table<?> alias) {
            return new CompetencePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<CompetenceRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_F;
    }

    private transient ApplicantCompetencePath _applicantCompetence;

    /**
     * Get the implicit to-many join path to the
     * <code>PUBLIC.APPLICANT_COMPETENCE</code> table
     */
    public ApplicantCompetencePath applicantCompetence() {
        if (_applicantCompetence == null)
            _applicantCompetence = new ApplicantCompetencePath(this, null, Keys.CONSTRAINT_A4.getInverseKey());

        return _applicantCompetence;
    }

    @Override
    public Competence as(String alias) {
        return new Competence(DSL.name(alias), this);
    }

    @Override
    public Competence as(Name alias) {
        return new Competence(alias, this);
    }

    @Override
    public Competence as(Table<?> alias) {
        return new Competence(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Competence rename(String name) {
        return new Competence(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Competence rename(Name name) {
        return new Competence(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Competence rename(Table<?> name) {
        return new Competence(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Competence where(Condition condition) {
        return new Competence(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Competence where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Competence where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Competence where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Competence where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Competence where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Competence where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Competence where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Competence whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Competence whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
