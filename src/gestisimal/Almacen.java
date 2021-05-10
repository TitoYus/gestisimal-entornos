package gestisimal;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase Almacen: Guarda un ArrayList de Articulos
 * A�ade articulos
 * Elimina
 * Aumenta Existencias
 * Decrementa Existencias
 * Busca un Articulo concreto.
 * 
 * @author Rafa Yuste
 *
 */

public class Almacen extends ArrayList<Articulo>{

  private static final long serialVersionUID = 1L;

  /**
   * Metodo que a�adir Art�culos al almacen.
   * @param nombre Nombre Articulo
   * @param existencias Existencia Articulo 
   * @param precioCompra Precio de Compra  Articulo
   * @param precioVenta Precio de Venta Articulo
   * @param numeroVentas Numero de Ventas Articulo
   * @param stockSeguro Stock de Seguridad Articulo
   * @param stockMaximo Stock Maximo Articulo
   * @throws ArticuloYaExisteException Excepcion lanzada si el articulo ya existe
   * @throws ExistenciasNoValidasException  Excepcion lanzada si las existencias no son validas
   * @throws PrecioNegativoException Excepcion lanzada si el precio es negativo
   */
  void annadir(String nombre, int existencias, double precioCompra, double precioVenta,
      int numeroVentas, int stockSeguro, int stockMaximo) throws ArticuloYaExisteException, ExistenciasNoValidasException, PrecioNegativoException {

    Articulo articulo = new Articulo(nombre, existencias, precioCompra, precioVenta,
        numeroVentas, stockSeguro, stockMaximo);
    if(!this.contains(articulo)) 
      this.add(articulo);
    else
      throw new ArticuloYaExisteException("El articulo ya existe, no es posible a�adirlo de nuevo.");

  }
  
  /**
   * M�todo que a�ade articulo en almacen
   * @param articulo
   * @throws ArticuloYaExisteException
   */
  void annadir(Articulo articulo) throws ArticuloYaExisteException {
    if (!this.contains(articulo))
      this.add(articulo);
    else 
      throw new ArticuloYaExisteException("El articulo ya existe, no es posible a�adirlo de nuevo.");
  }

  /**
   * Metodo que elimina un objeto Articulo usando el codigo.
   * @param codigo
   * @return
   * @throws ArticuloCodigoNoValidoException 
   */
  boolean eliminar(int codigo) throws ArticuloCodigoNoValidoException {
    //Si el almacen no contiene el Articulo con el codigo indicado, el c�digo se considerar� inv�lido
    if (!this.contains(this.get(this.indexOf(buscarArticulo(codigo))))) throw new ArticuloCodigoNoValidoException("El c�digo no es v�lido");
    return this.remove(new Articulo(codigo));
  }

  /**
   * M�todo que incrementa las existencias de un Articulo 
   * accediento a este a trav�s del metodo incrementar de Almacen.
   * 
   * @param codigo
   * @param existencias
   * @return booleano
   * @throws ArticuloCodigoNoValidoException
   * @throws ExistenciasNoValidasException
   */
  boolean incrementarExistencias(int codigo, int existencias) throws ArticuloCodigoNoValidoException, ExistenciasNoValidasException {
    //Si el almacen no contiene el Articulo con el codigo indicado, el c�digo se considerar� inv�lido
    if (!this.contains(this.get(this.indexOf(buscarArticulo(codigo))))) throw new ArticuloCodigoNoValidoException("El c�digo no es v�lido");
    return this.get(this.indexOf(buscarArticulo(codigo))).incrementar(existencias);
  }

  /**
   * Metodo que decrementa las existencias de un Articulo accediendo a este a trav�s del metodo decrementar de Almacen.
   * @param codigo
   * @param existencias
   * @return booleano
   * @throws ArticuloCodigoNoValidoException
   * @throws ExistenciasNoValidasException
   */
  boolean decrementarExistencias(int codigo, int existencias) throws ArticuloCodigoNoValidoException, ExistenciasNoValidasException {
    //Si el almacen no contiene el Articulo con el codigo indicado, el c�digo se considerar� inv�lido
    if (!this.contains(this.get(this.indexOf(buscarArticulo(codigo))))) throw new ArticuloCodigoNoValidoException("El c�digo no es v�lido");
    return this.get(this.indexOf(buscarArticulo(codigo))).decrementar(existencias);
  }

  /**
   * Metodo que busca un Articulo dentro de Almacen usando el codigo del Articulo
   * @param codigo
   * @return Objeto Art�culo
   */
  Articulo buscarArticulo(int codigo) throws ArticuloCodigoNoValidoException {
    if (!this.contains(this.get(this.indexOf(new Articulo(codigo))))) throw new ArticuloCodigoNoValidoException("El c�digo no es v�lido");
    return this.get(this.indexOf(new Articulo(codigo)));
  }
  
  /**
   * Guarda el CSV
   * @param fileName
   * @throws IOException
   * @throws ExistenciasNoValidasException
   */
  public void saveCSV(String fileName) throws IOException, ExistenciasNoValidasException {
    AlmacenCSV.save(this, fileName);
  }
  
  /**
   * Carga el CSV
   * @param fileName
   * @return Objeto almacen
   * @throws IOException
   * @throws AlmacenCSVException
   * @throws ArticuloYaExisteException
   */
  static public Almacen loadCSV(String fileName) throws IOException, AlmacenCSVException, ArticuloYaExisteException {
    var almacen = AlmacenCSV.load(fileName);
    return almacen;
  }
  
  /**
   * Guarda el XML
   * @param fileName
   * @throws IOException
   * @throws AlmacenXMLException
   */
  public void saveXML(String fileName) throws IOException, AlmacenXMLException {
    AlmacenXML.save(this, fileName);
  }
  
  /**
   * Carga el XML
   * @param fileName
   * @return Objeto Almacen
   * @throws IOException
   * @throws AlmacenXMLException
   */
  static public Almacen loadXML(String fileName) throws IOException, AlmacenXMLException {
    var almacen = AlmacenXML.load(fileName);
    return almacen;
  }

  /**
   * Guarda JSON
   * @param fileName
   * @throws IOException
   */
  public void saveJSON(String fileName) throws IOException {
    AlmacenJSON.save(this, fileName);
  }

  /**
   * Recupera la agenda de un fichero JSON y la devuelve como Almacen.
   * 
   * @param fileName
   * @return Objeto Almacen
   * @throws IOException
   * @throws AlmacenJSONException
   */
  static public Almacen loadJSON(String fileName) throws IOException, AlmacenJSONException {
    var almacen = AlmacenJSON.load(fileName);
    return almacen; 
  }

  @Override
  public String toString() {
    String toString = "Almacen [Articulos=\n";
    for (Articulo e: this) {
      toString += e;
    }
    toString += "]";
    return toString;
  }
  
}
