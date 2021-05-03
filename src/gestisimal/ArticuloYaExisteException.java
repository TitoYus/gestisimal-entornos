package gestisimal;

/**
 * Excepcion en la que el objeto Articulo ya existe
 * @author ryust
 */
public class ArticuloYaExisteException extends Exception {
  public ArticuloYaExisteException(String mensaje) {
    super(mensaje);
  }
}
