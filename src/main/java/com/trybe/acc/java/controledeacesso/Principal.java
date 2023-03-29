package com.trybe.acc.java.controledeacesso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

  /**
   * Método principal.
   */
  public static void main(String[] args) {
    // ESCREVA SEU CÓDIGO AQUI
    Locale.setDefault(Locale.US);

    Scanner scanner = new Scanner(System.in);
    ArrayList<Short> ages = new ArrayList<>();
    short age = 0;
    byte respCode = 0;

    do {
      System.out.println("Entre com o número correspondente à opção desejada:");
      System.out.println("1 - Acessar o estabelecimento");
      System.out.println("2 - Finalizar sistema e mostrar relatório");
      respCode = scanner.nextByte();

      if (respCode == 1) {
        System.out.println("Entre com a sua idade:");
        age = scanner.nextShort();
        ages.add(age);

        if (age < 18) {
          System.out.println("Pessoa cliente menor de idade, catraca liberada!");
        } else if (age >= 18 && age < 50) {
          System.out.println("Pessoa adulta, catraca liberada!");
        } else {
          System.out.println("Pessoa adulta a partir de 50, catraca liberada!");
        }
      } else if (respCode != 2) {
        System.out.println("Entre com uma opção válida!");
      }
    } while (respCode != 2);

    genReport(ages);
    scanner.close();
  }

  static void genReport(ArrayList<Short> ages) {
    short numberPeopleLess18 = doQuantitativeAnalysis(1, ages);
    short adultPeople = doQuantitativeAnalysis(2, ages);
    short numberPeopleMore50 = doQuantitativeAnalysis(3, ages);
    float numberPeopleLess18p = doPercentageAnalysis(1, ages);
    float adultPeoplep = doPercentageAnalysis(2, ages);
    float numberPeopleMore50p = doPercentageAnalysis(3, ages);

    System.out.println("\n----- Quantidade -----");
    System.out.print("menores: ");
    System.out.println(numberPeopleLess18);
    System.out.print("adultas: ");
    System.out.println(adultPeople);
    System.out.print("a partir de 50: ");
    System.out.println(numberPeopleMore50);
    System.out.println("\n----- Percentual -----");
    System.out.print("menores: ");
    System.out.print(numberPeopleLess18p);
    System.out.println("%");
    System.out.print("adultas: ");
    System.out.print(adultPeoplep);
    System.out.println("%");
    System.out.print("a partir de 50: ");
    System.out.print(numberPeopleMore50p);
    System.out.println("%");
    System.out.print("\nTOTAL: ");
    System.out.println(ages.size());
  }

  static short doQuantitativeAnalysis(int param, ArrayList<Short> ages) {
    short result = 0;

    for (short age : ages) {
      if (param == 1 && age < 18) {
        result += 1;
      }
      if (param == 2 && (age >= 18 && age < 50)) {
        result += 1;
      }
      if (param == 3 && age >= 50) {
        result += 1;
      }
    }

    return result;
  }

  static float doPercentageAnalysis(int param, ArrayList<Short> ages) {
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    ArrayList<Short> results = new ArrayList<>();

    for (short age : ages) {
      if (param == 1 && age < 18) {
        results.add(age);
      }
      if (param == 2 && (age >= 18 && age < 50)) {
        results.add(age);
      }
      if (param == 3 && age >= 50) {
        results.add(age);
      }
    }

    float dividend = results.size();
    float divider = ages.size();
    float percentage = (dividend / divider) * 100;

    return Float.parseFloat(decimalFormat.format(percentage));
  }
}
