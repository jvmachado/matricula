package com.pucrs.matricula.bd;

import java.util.ArrayList;
import java.util.List;

import com.pucrs.matricula.ed.MatriculaED;

public class MatriculaBD {

  private List<MatriculaED> matriculas;

  public MatriculaBD() {
    this.matriculas = new ArrayList<MatriculaED>();
  }

  public void addMatricula(MatriculaED matricula) throws Exception {
    boolean matriculaCadastrada = matriculas.stream().anyMatch(m -> m.toString().equals(matricula.toString()));
    if(matriculaCadastrada) {
      throw new Exception("Matricula já cadastrada!");
    }
    
    this.matriculas.add(matricula);
  }

  public List<MatriculaED> getMatriculas() {
    return this.matriculas;
  }
}
