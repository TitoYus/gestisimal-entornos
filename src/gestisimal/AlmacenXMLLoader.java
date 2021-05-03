package gestisimal;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AlmacenXMLLoader {

  /**
   * Objeto Almacen que guarda la clase AlmacenXMLLoader
   */
  private Almacen almacen;
  /**
   * Objeto Document que guarda la clase AlmacenXMLLoader
   */
  private Document xml;

  /**
   * Constructor de la clase AlmacenXMLLoader sin contenido
   */
  AlmacenXMLLoader() {
    this.almacen = new Almacen();
  }

  /**
   * Constructor de la clase AlmacenXMLLoader
   * @param almacen
   */
  AlmacenXMLLoader(Almacen almacen) {
    this.almacen = almacen;
  }

  Almacen getAlmacen() {
    return almacen;
  }

  /**
   * Carga el XML
   * @param fileName
   * @throws IOException
   * @throws AlmacenXMLException
   */
  void load(String fileName) throws IOException, AlmacenXMLException {

    try {
      loadDocumentXML(fileName);
      NodeList articulos = xml.getElementsByTagName(AlmacenXML.ARTICULO);
      for (int i = 0; i < articulos.getLength(); i++) {
        Articulo articulo = newArticulo(articulos.item(i));
        almacen.add(articulo);
      }
    } catch (SAXException | IOException | ParserConfigurationException
        | ExistenciasNoValidasException | PrecioNegativoException e) {
      throw new AlmacenXMLException("Error al cargar XML: " + e.getMessage());
    }

  }

  /**
   * Crea un nuevo Articulo
   * @param itemArticulo
   * @return Objeto Articulo
   * @throws ExistenciasNoValidasException
   * @throws PrecioNegativoException
   */
  private static Articulo newArticulo(Node itemArticulo) throws ExistenciasNoValidasException, PrecioNegativoException {

    String nombre = cargarCampoArticuloXML(AlmacenXML.NOMBRE, itemArticulo);
    String existencias = cargarCampoArticuloXML(AlmacenXML.EXISTENIAS, itemArticulo);
    String precioCompra = cargarCampoArticuloXML(AlmacenXML.PRECIOCOMPRA, itemArticulo);
    String precioVenta = cargarCampoArticuloXML(AlmacenXML.PRECIOVENTA, itemArticulo);
    String numeroVentas = cargarCampoArticuloXML(AlmacenXML.NUMEROVENTAS, itemArticulo);
    String stockSeguridad = cargarCampoArticuloXML(AlmacenXML.STOCKSEGURO, itemArticulo);
    String stockMaximo = cargarCampoArticuloXML(AlmacenXML.STOCKMAXIMO, itemArticulo);
    return Articulo.nuevoArticulo(nombre, existencias, precioCompra, precioVenta, numeroVentas, stockSeguridad, stockMaximo);
  }

  /**
   * Carga el Documento XML
   * @param fileName
   * @throws SAXException
   * @throws IOException
   * @throws ParserConfigurationException
   */
  private void loadDocumentXML(String fileName) throws SAXException, IOException, ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    xml = builder.parse(new File(fileName));
    xml.getDocumentElement().normalize();    
  }

  /**
   * Carga los campos del Articulo a partir del XML
   * @param campo
   * @param itemArticulo
   * @return
   */
  private static String cargarCampoArticuloXML(String campo, Node itemArticulo) {
    Element elementoArticulo = (Element) itemArticulo;
    String textField = elementoArticulo.getElementsByTagName(campo).item(0).getTextContent();
    return textField;
  }
}
