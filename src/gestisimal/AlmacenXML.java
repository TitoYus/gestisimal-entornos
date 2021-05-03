package gestisimal;

import java.io.IOException;

class AlmacenXML {

  static final String ALMACEN = "Almacen";
  static final String ARTICULO = "Articulo";
  static final String NOMBRE = "Nombre";
  static final String EXISTENIAS = "Existencias";
  static final String PRECIOCOMPRA = "PrecioCompra";
  static final String PRECIOVENTA = "PrecioVenta";
  static final String NUMEROVENTAS = "NumeroVentas";
  static final String STOCKSEGURO = "StockSeguro";
  static final String STOCKMAXIMO = "StockMaximo";

  /**
   * Guarda XML
   * @param almacen
   * @param fileName
   * @throws IOException
   * @throws AlmacenXMLException
   */
  static void save(Almacen almacen, String fileName) throws IOException, AlmacenXMLException {
    var writer = new AlmacenXMLWriter(almacen);
    writer.save(fileName);
  }

  /**
   * Carga XML
   * @param fileName
   * @return Objeto Almacen
   * @throws IOException
   * @throws AlmacenXMLException
   */
  static Almacen load(String fileName) throws IOException, AlmacenXMLException {
    var reader = new AlmacenXMLLoader();
    reader.load(fileName);
    return reader.getAlmacen();
  }
}
