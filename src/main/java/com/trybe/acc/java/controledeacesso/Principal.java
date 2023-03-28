package com.trybe.acc.java.controledeacesso;

import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;

public class Principal {
  /**
   * Método principal.
   */
  public static void main(String[] args) {
    // ESCREVA SEU CÓDIGO AQUI
    Locale.setDefault(Locale.US);

    Scanner scanner = new Scanner(System.in);
    ArrayList<Short> ages = new ArrayList<>();
    short age, respCode = 0;

    do {
      System.out.println(
        "Entre com o número correspondente à opção desejada:\n" +
        "1 - Acessar o estabelecimento\n" +
        "2 - Finalizar sistema e mostrar relatório\n"
      );
      respCode = scanner.nextByte();

      if (respCode == 1) {
        System.out.println("Entre com a sua idade:");
        age = scanner.nextByte();
        ages.add(age);

        if (age < 18) {
          System.out.println("Pessoa cliente menor de idade, catraca liberada!");
        } else if (age >= 18 && age < 50) {
          System.out.println("Pessoa adulta, catraca liberada!");
        } else {
          System.out.println("Pessoa adulta a partir de 50, catraca liberada!");
        }
      }
    } while (respCode != 2);

    genReport(ages);
    scanner.close();
  }

  static void genReport(ArrayList<Short> ages) {
    int totalNumberPeople = ages.size();
    int numberPeopleLess18 = doQuantitativeAnalysis(1, ages);
    int adultPeople = doQuantitativeAnalysis(2, ages);
    int numberPeopleMore50 = doQuantitativeAnalysis(3, ages);
    float numberPeopleLess18p = doPercentageAnalysis(1, ages);
    float adultPeoplep = doPercentageAnalysis(2, ages);
    float numberPeopleMore50p = doPercentageAnalysis(3, ages);

    System.out.println(
      "\n----- Quantidade -----\n" +
      "menores: " + numberPeopleLess18 + "\n" +
      "adultas: " + adultPeople + "\n" +
      "a partir de 50: " + numberPeopleMore50 + "\n" +
      "\n----- Percentual -----\n" +
      "menores: " + numberPeopleLess18p + "\n" +
      "adultas: " + adultPeoplep + "\n" +
      "a partir de 50: " + numberPeopleMore50p + "\n" +
      "\nTOTAL: " + ages.size()
    );
  }

  static int doQuantitativeAnalysis(int param, ArrayList<Short> ages) {
    int result = 0;

    for (short age : ages) {
      if (param == 1 && age < 18) result += 1;
      if (param == 2 && (age >= 18 && age < 50)) result += 1;
      if (param == 3 && age >= 50)result += 1;
    }

    return result;
  }

  static float doPercentageAnalysis(int param, ArrayList<Short> ages) {
    ArrayList<Short> results = new ArrayList<>();

    for (short age : ages) {
      if (param == 1 && age < 18) results.add(age);
      if (param == 2 && (age >= 18 && age < 50)) results.add(age);
      if (param == 3 && age >= 50) results.add(age);
    }

    float percentage = (results.size() / ages.size()) * 100;
    return percentage;
  }
}
