package org.jobs.models;

import java.util.List;

public class FilterJobData {
    private final List<String> competencias;
    private final String tipo;

    public FilterJobData(List<String> competencias, String tipo) {
        this.competencias = competencias;
        this.tipo = tipo;
    }

    public List<String> getCompetencias() {
        return competencias;
    }

    public String getTipo() {
        return tipo;
    }
}