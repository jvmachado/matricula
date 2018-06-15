package com.pucrs.ir.test;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.pucrs.matricula.bd.MatriculaBD;
import com.pucrs.matricula.ed.MatriculaED;
import com.pucrs.matricula.rn.MatriculaRN;

public class AppTest {

  private MatriculaRN matriculaRN;

  private MatriculaBD matriculaBD;

  @Before
  public void preparaTeste() {
    matriculaRN = new MatriculaRN();
    matriculaBD = new MatriculaBD();
  }

  @Test
  public void deveGerarMatriculaDezDigitos() {
    MatriculaED matriculaGerada = matriculaRN.geraMatricula(Calendar.getInstance());

    assertEquals(10, matriculaGerada.toString().length());
  }

  @Test
  public void deveConterTracoNoPenultimoCaracterDaMatricula() {
    MatriculaED matriculaGerada = matriculaRN.geraMatricula(Calendar.getInstance());

    assertEquals('-', matriculaGerada.toString().charAt(8));
  }

  @Test
  public void deveConterOAnoNosPrimeiroDigitosMatricula() {
    MatriculaED matriculaGerada = matriculaRN.geraMatricula(Calendar.getInstance());

    DateFormat df = new SimpleDateFormat("yy");
    String ano = df.format(Calendar.getInstance().getTime());

    assertEquals(ano, matriculaGerada.toString().substring(0, 2));
  }

  @Test
  public void deveConterOSemestreAposOAnoMatricula() {
    Calendar dataMatricula = Calendar.getInstance();
    dataMatricula.set(Calendar.MONTH, Calendar.JANUARY);

    MatriculaED matriculaGerada = matriculaRN.geraMatricula(dataMatricula);
    assertEquals("01", matriculaGerada.toString().substring(2, 4));

    dataMatricula.set(Calendar.MONTH, Calendar.JULY);

    matriculaGerada = matriculaRN.geraMatricula(dataMatricula);
    assertEquals("02", matriculaGerada.toString().substring(2, 4));
  }

  @Test
  public void deveTerODigitoVerificadorIgualAUltimoDigitoSomaMatricula() {
    Calendar dataMatricula = Calendar.getInstance();
    dataMatricula.set(Calendar.MONTH, Calendar.JANUARY);

    MatriculaED matriculaGerada = matriculaRN.geraMatricula(dataMatricula);
    matriculaGerada.setMatricula(22222222L);

    char digitoVerificador = matriculaRN.geraDigitoVerificador(matriculaGerada.getMatricula());
    assertEquals('6', digitoVerificador);
  }

  @Test(expected = Exception.class)
  public void naoDevePoderAdicionarMatriculasRepetidas() throws Exception {
    MatriculaED matriculaGerada = matriculaRN.geraMatricula(Calendar.getInstance());
    matriculaGerada.setMatricula(22222222L);
    char digitoVerificador = matriculaRN.geraDigitoVerificador(matriculaGerada.getMatricula());
    matriculaGerada.setVerificador(digitoVerificador);
    matriculaBD.addMatricula(matriculaGerada);

    MatriculaED matriculaGerada2 = matriculaRN.geraMatricula(Calendar.getInstance());
    matriculaGerada2.setMatricula(22222222L);
    matriculaGerada2.setVerificador(digitoVerificador);

    matriculaBD.addMatricula(matriculaGerada2);

  }

}
