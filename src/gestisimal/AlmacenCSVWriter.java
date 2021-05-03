package gestisimal;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Escribe el CSV.
 * @author ryust
 */
public class AlmacenCSVWriter {

  /*
   * Head del CSV
   */
  static final String HEAD = 
      "Nombre,Existencias,PrecioCompra,PrecioVenta,NumeroVentas,StockSeguridad,StockMaximo";
  /**
   * Objeto Almacen
   */
  private Almacen almacen;
  /**
   * Objeto PrintWriter
   */
  private PrintWriter file;
  
  AlmacenCSVWriter(Almacen almacen) {
    this.almacen = almacen;
  }
  
  /**
   * Guarda el archivo CSV
   * @param nombreFichero
   * @throws IOException
   * @throws ExistenciasNoValidasException
   */
  void guardarArchivo(String nombreFichero) throws IOException, ExistenciasNoValidasException {
    file = new PrintWriter(nombreFichero);
    file.println(AlmacenCSV.HEAD);
    for (Articulo articulo: almacen) {
      guardarArticulo(articulo);
    }
    file.close();
  }
  
  /**
   * Guardar Articulo
   * @param articulo
   * @throws IOException
   * @throws ExistenciasNoValidasException
   */
  private void guardarArticulo(Articulo articulo) throws IOException, ExistenciasNoValidasException {

    file.write("\"" + articulo.getDescripcion() + "\",");
    file.write("\"" + articulo.getExistencias() + "\",");
    file.write("\"" + articulo.getPreCompra() + "\",");
    file.write("\"" + articulo.getPreVenta() + "\",");
    file.write("\"" + articulo.getNumVentas() + "\",");
    file.write("\"" + articulo.getStockSeguridad() + "\",");
    file.write("\"" + articulo.getStockMax() + "\",");
    file.println();

  }
}
