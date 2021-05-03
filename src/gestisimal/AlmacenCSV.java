package gestisimal;

import java.io.IOException;

/**
 * 
 * @author ryust
 *
 */
class AlmacenCSV {
  static final String HEAD = 
      "Nombre,Existencias,PrecioCompra,PrecioVenta,NumeroVentas,StockSeguridad,StockMaximo";
  
  /**
   * 
   * @param almacen
   * @param archivo
   * @throws IOException
   * @throws ExistenciasNoValidasException
   */
  static void save(Almacen almacen, String archivo) throws IOException, ExistenciasNoValidasException {
    var writer = new AlmacenCSVWriter(almacen);
    writer.guardarArchivo(archivo);
  }
  
  /**
   * 
   * @param archivo
   * @return Objeto Almacen
   * @throws IOException
   * @throws AlmacenCSVException
   * @throws ArticuloYaExisteException
   */
  static Almacen load(String archivo) throws IOException, AlmacenCSVException, ArticuloYaExisteException {
    var reader = new AlmacenCSVLoader();
    reader.load(archivo);
    return reader.getAlmacen();
  }
}
