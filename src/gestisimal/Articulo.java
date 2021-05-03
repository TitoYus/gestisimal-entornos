package gestisimal;

/**
 * Clase Articulo
 * 
 * Estado de Articulo:
 * Codigo
 * Descripcion
 * Precio de Compra
 * Precio de Vente
 * Numero de Unidades (NUNCA NEGATIVAS)
 * Stock de Seguridad
 * Stock Maximo
 * 
 * @author Rafa Yuste
 */

public class Articulo {

  /**
   * Codigo del Articulo
   */
  private int codigo;
  /**
   * Codigo que guarda el anterior para poder crear uno nuevo
   */
  static int codigoAnterior = 0;
  /**
   * Descripcion o Nombre del Articulo
   */
  private String descripcion;
  /**
   * Existencias del Articulo
   */
  private int existencias;
  /**
   * Precio de Compra del Articulo
   */
  private double preCompra;
  /**
   * Precio de Venta del Articulo
   */
  private double preVenta;
  /**
   * Numero de Venta del Articulo
   */
  private int numVentas;
  /**
   * Stock de Seguridad para tener un control de los articulos en el almacen.
   */
  private int stockSeguridad;
  /**
   * Stock Maximo posible para un Articulo
   */
  private int stockMax;

  /**
   * Constructor
   * @param nombre Nombre del Articulo
   * @param existencias Existencias del Articulo
   * @param precioCompra Precio de Compra
   * @param precioVenta Precio Venta del Articulo
   * @param numeroVentas Numero de Ventas del Articulo
   * @param stockSeguro Stock de Seguridad del Articulo
   * @param stockMaximo Stock Maximo del Articulo
   * @throws ExistenciasNoValidasException Existencias Negativas
   * @throws PrecioNegativoException Precio Negativo
   */
  Articulo(String nombre, int existencias, double precioCompra, double precioVenta,
      int numeroVentas, int stockSeguro, int stockMaximo) throws ExistenciasNoValidasException, PrecioNegativoException {

    this.codigo = ++codigoAnterior;
    setDescripcion(nombre);
    setExistencias(existencias);
    setPreCompra(precioCompra);
    setPreVenta(numeroVentas);
    setNumVentas(numeroVentas);
    setStockSeguridad(stockSeguro);
    setStockMax(stockMaximo);
  }

  /**
   * Constructor con código
   * @param codigo
   */
  Articulo(int codigo) {
    this.codigo = codigo;
  }

  /**
   * Metodo que incrementa las existencias de Articulo
   * @param unidades
   * @return boolean
   * @throws ExistenciasNoValidasException
   */
  public boolean incrementar(int unidades) throws ExistenciasNoValidasException {
    return setExistencias(this.existencias+unidades);
  }

  /**
   * Metodo que decrementa las existencias de Articulo
   * @param unidades
   * @return boolean
   * @throws ExistenciasNoValidasException
   */
  public boolean decrementar(int unidades) throws ExistenciasNoValidasException {
    return setExistencias(this.existencias-unidades);
  }

  /**
   * Devuelve un artículo nuevo
   * @param nombre
   * @param existencias
   * @param precioCompra
   * @param precioVenta
   * @param numeroVentas
   * @param stockSeguridad
   * @param stockMaximo
   * @return Objeto Artículo
   * @throws ExistenciasNoValidasException
   * @throws PrecioNegativoException
   */
  public static Articulo nuevoArticulo(String nombre, String existencias, String precioCompra, String precioVenta,
      String numeroVentas, String stockSeguridad, String stockMaximo) throws ExistenciasNoValidasException, PrecioNegativoException {
    Articulo articulo;
    
    int existencias_ = Integer.parseInt(existencias);
    double precioCompra_ = Double.parseDouble(precioCompra);
    double precioVenta_ = Double.parseDouble(precioVenta);
    int numeroVentas_ = Integer.parseInt(numeroVentas);
    int stockSeguridad_ = Integer.parseInt(stockSeguridad);
    int stockMaximo_ = Integer.parseInt(stockMaximo);
    
    articulo = new Articulo(nombre, existencias_, precioCompra_, precioVenta_,
        numeroVentas_, stockSeguridad_, stockMaximo_);

//    if (! nombre.isBlank()) {
//      Articulo.setDescripcion(nombre);
//    }
    return articulo;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Setter de Descripcion
   * @param descripcion
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getExistencias() throws ExistenciasNoValidasException {
    return existencias;
  }

  /**
   * Setter de Existencias
   * @param existencias
   * @return existencias
   * @throws ExistenciasNoValidasException
   */
  public boolean setExistencias(int existencias) throws ExistenciasNoValidasException {
    if (existencias < 0) {
      throw new ExistenciasNoValidasException("Las existencias no pueden ser negativas"); 
    } else  this.existencias = existencias;
    return true;
  }

  public double getPreCompra() {
    return preCompra;
  }

  /**
   * Setter de Precio de Compra
   * @param preCompra
   * @throws PrecioNegativoException
   */
  public void setPreCompra(double preCompra) throws PrecioNegativoException {
    if (preCompra <= 0) {
      throw new PrecioNegativoException("El precio de compra no puede ser negativo ni 0.");
    } else
      this.preCompra = preCompra;
  }

  public double getPreVenta() {
    return preVenta;
  }

  /**
   * Setter de Precio de Venta
   * @param preVenta
   * @throws PrecioNegativoException
   */
  public void setPreVenta(double preVenta) throws PrecioNegativoException{
    if (preVenta <= 0) {
      throw new PrecioNegativoException("El precio de venta no puede ser negativo ni 0");
    } else
      this.preVenta = preVenta;
  }

  public int getNumVentas() {
    return numVentas;
  }

  /**
   * Setter de Numero de Ventas
   * @param numVentas
   */
  public void setNumVentas(int numVentas) {
    this.numVentas = numVentas;
  }

  public int getStockSeguridad() {
    return stockSeguridad;
  }

  /**
   * Setter de Stock Seguro
   * @param stockSeguro
   */
  public void setStockSeguridad(int stockSeguro) {
    this.stockSeguridad = stockSeguro;
  }

  public int getStockMax() {
    return stockMax;
  }

  /**
   * Setter de Stock Maximo
   * @param stockMax
   */
  public void setStockMax(int stockMax) {
    this.stockMax = stockMax;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + codigo;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Articulo other = (Articulo) obj;
    if (codigo != other.codigo)
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    return "Articulo " + descripcion + "[Codigo =" + codigo + ", Existencias = "
        + existencias + ", PrecioCompra = " + preCompra + ", PrecioVenta =" + preVenta + ", NumeroVentas = "
        + numVentas + ", StockSeguro = " + stockSeguridad + ", StockMaximo = " + stockMax + "]\n";
  }
}