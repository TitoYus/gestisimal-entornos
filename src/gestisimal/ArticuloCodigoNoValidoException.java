package gestisimal;

/**
 * Excepcion del codigo en la clase Articulo
 * @author ryust
 */
public class ArticuloCodigoNoValidoException extends Exception {
  public ArticuloCodigoNoValidoException(String mensaje) {
    super(mensaje);
  }
}
