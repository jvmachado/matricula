package com.pucrs.matricula.rn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import com.pucrs.matricula.ed.MatriculaED;

public class MatriculaRN {

  public MatriculaED geraMatricula(Calendar dataMatricula) {
    MatriculaED matriculaED = new MatriculaED();
    matriculaED.setMatricula(geraNroMatricula(dataMatricula));
    matriculaED.setVerificador(geraDigitoVerificador(matriculaED.getMatricula()));

    return matriculaED;
  }

  private Long geraNroMatricula(Calendar dataMatricula) {
    long min = (long) Math.pow(10, 4 - 1);
    Long matricula = ThreadLocalRandom.current().nextLong(min, min * 10);
    String ano = getAnoMatricula(dataMatricula);
    String semestre = getSemestreMatricula(dataMatricula);
    return Long.valueOf(ano + semestre + matricula.toString());

  }

  private String getSemestreMatricula(Calendar dataMatricula) {
    Integer mes = dataMatricula.get(Calendar.MONTH) + 1;
    return Double.valueOf(mes) / 6 <= 1 ? "01" : "02";
  }

  private String getAnoMatricula(Calendar dataMatricula) {
    DateFormat df = new SimpleDateFormat("yy");
    String ano = df.format(dataMatricula.getTime());
    return ano;
  }

  public char geraDigitoVerificador(Long matricula) {
    Integer soma = 0;
    for (char c : matricula.toString().toCharArray()) {
      soma += Character.getNumericValue(c);
    }

    String somaString = soma.toString();

    return somaString.charAt(somaString.length() - 1);

  }

}
