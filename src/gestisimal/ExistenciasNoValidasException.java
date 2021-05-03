package gestisimal;

/**
 * Excepcion de la clase Articulo en la que las existencias no son validas.
 * @author ryust
 */
public class ExistenciasNoValidasException extends Exception {
  public ExistenciasNoValidasException(String mensaje) {
    super(mensaje);
  }
}
