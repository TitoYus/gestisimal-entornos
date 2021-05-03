package gestisimal;

import java.io.IOException;
import java.util.Scanner;
import com.google.gson.*;

/**
 * Test en el que se comprueba que las clases Articulo y Almacen funcionan correctamente.
 * Además de incluir metodos propios para ayudar a la salida de excepciones y a la petición
 * valores al usuario.
 * 
 * POSIBLES MEJORAS:
 * Al crear un Articulo que no sea valido el código podría volver a su valor anterior para así no tener codigos vacíos.
 * Si no tenemos Articulos en nuestro Almacen y elegimos las opciones de buscar X Artículo
 * podríamos no lanzar un error, sino pedir de nuevo las opciones.
 * Reducir la cantidad de veces que se han escrito los mensajes de Excepciones.
 * Crear un metodo para cada peticion de valores(Creo que sería más rápido, pero más tedioso).
 * 
 * @author Rafa Yuste
 *
 */

public class TestGestisimal {

  static Almacen almacen = new Almacen();

  static int opcion;
  static Menu menu = new Menu("Almacen", new String[] {
      "Añadir Artículo","Mostrar Almacen","Mostrar Artículo",
      "Eliminar Articulo","Incrementar Existencias", "Decrementar Existencias",
      "Importar CSV", "Exportar CSV", "Importar XML", "Exportar XML",
      "Importar JSON","Exportar JSON","Terminar"
  });

  /**
   * Test de la clase Gestisimal
   * @param args
   * @throws ArticuloYaExisteException
   * @throws ArticuloCodigoNoValidoException
   * @throws ExistenciasNoValidasException
   */
  public static void main (String [] args) throws ArticuloYaExisteException, ArticuloCodigoNoValidoException, ExistenciasNoValidasException {

    do {
      opcion = menu.elegir();
      try {
        switch (opcion) {
          case 1:
            annadirArticulo();
            break;
          case 2:
            System.out.println(almacen);
            break;
          case 3:
            System.out.println(almacen.buscarArticulo(pedirCodigo()));
            break;
          case 4:
            eliminarArticulo();
            break;
          case 5:
            incrementarExistenciasArticulo();
            break;
          case 6:
            decrementarExistenciasArticulo();
            break;
          case 7:
            importarCSV();
            break;
          case 8:
            exportarCSV();
            break;
          case 9:
            importarXML();
            break;
          case 10:
            exportarXML();
            break;
          case 11:
            importarJSON();
            break;
          case 12:
            exportarJSON();
            break;
          case 13:
            System.out.println("Programa Terminado. :C");
        }
      } catch (ArticuloYaExisteException  e) {
        e.printStackTrace();
      } catch (ExistenciasNoValidasException e) {
        e.printStackTrace();
      } catch (ArticuloCodigoNoValidoException e) {
        e.printStackTrace();
      }
    } while (opcion != 13);
  }

  private static void exportarJSON() {
    try {
      String fichero = pedirFichero("Nombre del fichero donde exportar a JSON el almacen:");
      almacen.saveJSON(fichero);

    } catch (IOException e) {
      System.err.println("No se ha podido exportar a JSON: " + e.getMessage() + "\n");
    }    
  }
  

  private static void importarJSON() {
    try {
      String fichero = pedirFichero("Nombre del fichero JSON");
      almacen = Almacen.loadJSON(fichero);

    } catch (IOException e) {
      System.err.println("Ha habido problemas al cargar el fichero.\n");
    } catch (AlmacenJSONException e) {
      System.err.println("Ha habido problemas con el formato del JSON: " + e.getMessage() + "\n");
    }    
  }

  /**
   * 
   */
  private static void exportarXML() {
    try {
      String fichero = pedirFichero("Introduce el nombre del fichero XML donde vas a crear el Almacen");
      almacen.saveXML(fichero);
    } catch (AlmacenXMLException | IOException e) {
      System.err.println("No se ha podido exportar a XML: " + e.getMessage() + "\n");
    }
  }

  /**
   * 
   */
  private static void importarXML() {
    try {
      String fichero = pedirFichero("Nombre del fichero XML");
      almacen = Almacen.loadXML(fichero);
    } catch (AlmacenXMLException | IOException e) {
      e.printStackTrace();
    }

  }

  private static void exportarCSV() {
    try {
      String fichero = pedirFichero("Introduce el nombre del fichero CSV donde vas a crear el Almacen");
      almacen.saveCSV(fichero);
    } catch (IOException | ExistenciasNoValidasException e) {
      e.printStackTrace();
    }
  }

  private static void importarCSV() {
    try {
      String fichero = pedirFichero("Introduce un fichero CSV");
      almacen = Almacen.loadCSV(fichero);

    } catch (IOException | AlmacenCSVException | ArticuloYaExisteException e) {
      e.printStackTrace();
    }
    System.out.println("Carga Finalizada\n");
  }

  private static String pedirFichero(String mensaje) {
    Scanner s = new Scanner(System.in);
    System.out.println("\n" + mensaje + ": ");
    String fichero = s.nextLine();
    System.out.println();
    return fichero;
  }

  private static void incrementarExistenciasArticulo() {
    Scanner s = new Scanner (System.in);

    System.out.println("Introduce el código del Articulo a usar: ");
    int codigo = s.nextInt();
    System.out.println("Introduce ahora las existencias a aumentar: ");
    int existencias = s.nextInt();

    try {
      almacen.incrementarExistencias(codigo, existencias);
    } catch (ArticuloCodigoNoValidoException | ExistenciasNoValidasException e) {
      e.printStackTrace();
    }

  }

  /**
   * 
   * @throws ArticuloCodigoNoValidoException
   * @throws ExistenciasNoValidasException
   */
  private static void decrementarExistenciasArticulo() throws ArticuloCodigoNoValidoException, ExistenciasNoValidasException {
    Scanner s = new Scanner (System.in);

    System.out.println("Introduce el cÃ³digo del Articulo a usar: ");
    int codigo = s.nextInt();
    System.out.println("Introduce ahora las existencias a decrementar: ");
    int existencias = s.nextInt();

    almacen.decrementarExistencias(codigo, existencias);
  }

  /**
   * 
   * @throws ArticuloYaExisteException
   * @throws ExistenciasNoValidasException
   */
  private static void annadirArticulo() throws ArticuloYaExisteException, ExistenciasNoValidasException {
    Scanner s = new Scanner (System.in);

    System.out.println("Introduce los siguientes datos:\n Nombre:");
    String nombre = s.next();
    System.out.println("Existencias: ");
    int existencias = s.nextInt();

    System.out.println("Precio de Compra: ");
    double precioCompra = s.nextDouble();
    System.out.println("Precio de Venta: ");
    double precioVenta = s.nextDouble();
    System.out.println("Numero de Ventas: ");
    int numeroVentas = s.nextInt();
    System.out.println("Stock Seguro: ");
    int stockSeguro = s.nextInt();
    System.out.println("Stock MÃ¡ximo: ");
    int stockMaximo = s.nextInt();


    try {
      almacen.annadir(nombre, existencias, precioCompra, precioVenta, numeroVentas, stockSeguro, stockMaximo);
    } catch (ArticuloYaExisteException e) {
      e.printStackTrace();
    } catch (ExistenciasNoValidasException e) {
      e.printStackTrace();
    } catch (PrecioNegativoException e) {
      e.printStackTrace();
    } 

  }

  /**
   * 
   * @return Código
   */
  private static int pedirCodigo() {
    Scanner s = new Scanner (System.in);

    System.out.println("Introduce el codigo del ArtÃ­culo a mostrar: ");
    int codigo = s.nextInt();
    return codigo;
  }

  private static void eliminarArticulo() {
    Scanner s = new Scanner (System.in);

    System.out.println("Escribe el cÃ³digo del ArtÃ­culo que quieres eliminar: ");
    int codigo = s.nextInt();

    try {
      almacen.eliminar(codigo);
    } catch (ArticuloCodigoNoValidoException e) {
      e.printStackTrace();
    }
  }
}