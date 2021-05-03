package gestisimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase para gestionar men�s de forma que las opciones se almacenan en un ArrayList.
 */

import java.util.Scanner;

public class Menu {
  
  String titulo;
  List<String> opciones;
  
  static Menu menu = new Menu("Almacen", new String[] {
  
      
  });  
  //Constructor Del Men�
  public Menu(String titulo, String... opciones) {
    this.titulo = titulo;
    this.opciones = new ArrayList<>(Arrays.asList(opciones));
  }

  public int elegir() {
    // Mostramos el men�
    System.out.println(this.titulo);
    System.out.println("-".repeat(this.titulo.length()) +"\n");
    
    for (int i = 0; i < this.opciones.size(); i++) {
      System.out.println((i+1) + ". " + this.opciones.get(i));
    }
    System.out.print("\nIntroduce una opci�n: ");
    
    // Leo la opci�n
    Scanner s = new Scanner(System.in);
    int opcion = s.nextInt();
    
    while (opcion <= 0 || opcion > this.opciones.size()) {
      System.out.print("Opci�n incorrecta, introduzca otra: ");
      opcion = s.nextInt();
    }
    
    return opcion;
  }

  @Override
  public String toString() {
    return "Menu [titulo=" + titulo + ", opciones=" + opciones + "]";
  }

}