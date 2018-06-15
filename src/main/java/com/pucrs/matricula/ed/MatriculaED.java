package com.pucrs.matricula.ed;

public class MatriculaED {

  private Long matricula;

  private char verificador;

  public Long getMatricula() {
    return matricula;
  }

  public void setMatricula(Long matricula) {
    this.matricula = matricula;
  }

  public char getVerificador() {
    return verificador;
  }

  public void setVerificador(char verificador) {
    this.verificador = verificador;
  }

  @Override
  public String toString() {
    return matricula + "-" + verificador;
  }

}
