/*
 * This file is generated by jOOQ.
 */
package codegen.jooq.tables.records;


import codegen.jooq.tables.Competence;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CompetenceRecord extends UpdatableRecordImpl<CompetenceRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.COMPETENCE.ID</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.COMPETENCE.ID</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CompetenceRecord
     */
    public CompetenceRecord() {
        super(Competence.COMPETENCE);
    }

    /**
     * Create a detached, initialised CompetenceRecord
     */
    public CompetenceRecord(String id) {
        super(Competence.COMPETENCE);

        setId(id);
        resetChangedOnNotNull();
    }
}
