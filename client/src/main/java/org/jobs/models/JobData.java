package org.jobs.models;

import javafx.collections.ObservableList;

public class JobData {
  private final String nome;
  private final int faixaSalarial;
  private final String descricao;
  private final String estado;
  private final ObservableList<String> competencias;

  public JobData(String nome, int faixaSalarial, String descricao, String estado, ObservableList<String> competencias) {
    this.nome = nome;
    this.faixaSalarial = faixaSalarial;
    this.descricao = descricao;
    this.estado = estado;
    this.competencias = competencias;
  }

  public String getNome() {
    return nome;
  }

  public int getFaixaSalarial() {
    return faixaSalarial;
  }

  public String getDescricao() {
    return descricao;
  }

  public String getEstado() {
    return estado;
  }

  public ObservableList<String> getCompetencias() {
    return competencias;
  }
}