package gestisimal;

/**
 * Excepcion de la clase Articulo en la que el precio es negativo.
 * @author ryust
 */
public class PrecioNegativoException extends Exception {
  public PrecioNegativoException(String mensaje) {
    super(mensaje);
  }
}
