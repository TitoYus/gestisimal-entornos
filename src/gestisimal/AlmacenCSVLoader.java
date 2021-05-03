package gestisimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author ryust
 *
 */
class AlmacenCSVLoader {
  
  static final String HEAD = 
      "Nombre,Existencias,PrecioCompra,PrecioVenta,NumeroVentas,StockSeguridad,StockMaximo";
  
  private Almacen almacen;
  private BufferedReader file;

  AlmacenCSVLoader() {
    this.almacen = new Almacen();
  }

  AlmacenCSVLoader(Almacen almacen) {
    this.almacen = almacen;
  }

  Almacen getAlmacen() {
    return almacen;
  }

  /**
   * 
   * @param fileName
   * @throws IOException
   * @throws AlmacenCSVException
   * @throws ArticuloYaExisteException
   */
  void load(String fileName) throws IOException, AlmacenCSVException, ArticuloYaExisteException {
    file = Files.newBufferedReader(Paths.get(fileName));
    validarHead();

    String linea;
    while ((linea = file.readLine()) != null) {
      Articulo articulo = nuevoArticuloCSV(linea);
      almacen.annadir(articulo);
    }
    file.close();
  }

  /**
   * 
   * @throws IOException
   * @throws AlmacenCSVException
   */
  private void validarHead() throws IOException, AlmacenCSVException {
    String head = file.readLine().trim();
    if (! HEAD.equalsIgnoreCase(head)) {
      throw new AlmacenCSVException("Cabecera errÃ³nea en el CSV.");
    }
  }

  /**
   * 
   * @param linea
   * @return Objeto Artículo
   * @throws AlmacenCSVException
   */
  private static Articulo nuevoArticuloCSV(String linea) throws AlmacenCSVException {
    validarArticuloCSV(linea);
    String[] camposArticulo = linea.split("\",");

    String nombre = camposArticulo[0].replace("\"", "");
    String existencias = camposArticulo[1].replace("\"", "");
    String precioCompra = camposArticulo[2].replace("\"", "");
    String precioVenta = camposArticulo[3].replace("\"", "");
    String numeroVentas = camposArticulo[4].replace("\"", "");
    String stockSeguridad = camposArticulo[5].replace("\"", "");
    String stockMaximo = camposArticulo[6].replace("\"", "");


    Articulo articulo = null;

    try {
      articulo = Articulo.nuevoArticulo(nombre, existencias, precioCompra, 
          precioVenta, numeroVentas, stockSeguridad, stockMaximo);
    } catch (ExistenciasNoValidasException | PrecioNegativoException e) {
      throw new AlmacenCSVException(linea + ": " + e.getMessage());
    }


    return articulo;
  }

  /**
   * 
   * @param linea
   * @throws AlmacenCSVException
   */
  private static void validarArticuloCSV(String linea) throws AlmacenCSVException {
    int numCamposArticulo = HEAD.split(",").length;
    int numCamposLinea = linea.split("\",").length;

    if (numCamposLinea != numCamposArticulo)
      throw new AlmacenCSVException(linea + ": no es un formato vï¿½lido para convertirlo en Artï¿½culo.");
  }

}
