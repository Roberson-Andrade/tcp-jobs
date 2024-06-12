package org.jobs.models;

public class FilteredJob {
    private int idVaga;
    private String nome;
    private int faixaSalarial;
    private String descricao;
    private String estado;
    private String competencias;

    public FilteredJob(int idVaga, String nome, int faixaSalarial, String descricao, String estado, String competencias) {
        this.idVaga = idVaga;
        this.nome = nome;
        this.faixaSalarial = faixaSalarial;
        this.descricao = descricao;
        this.estado = estado;
        this.competencias = competencias;
    }

    // Getters and setters
    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFaixaSalarial() {
        return faixaSalarial;
    }

    public void setFaixaSalarial(int faixaSalarial) {
        this.faixaSalarial = faixaSalarial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCompetencias() {
        return competencias;
    }

    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }
}
