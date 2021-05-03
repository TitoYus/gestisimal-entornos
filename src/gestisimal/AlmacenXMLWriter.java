package gestisimal;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AlmacenXMLWriter {
  
  private Almacen almacen;
  private Document xml;
  
  /**
   * Constructor de la clase AlmacenXMLWriter
   * @param almacen
   */
  AlmacenXMLWriter(Almacen almacen){
    this.almacen = almacen;
  }
  
  /**
   * Guarda el XML
   * @param fileName
   * @throws AlmacenXMLException
   * @throws IOException
   */
  void save(String fileName) throws AlmacenXMLException, IOException {
    try {   
      createDocumentXML();
      saveRoot();
      for (Articulo articulo : almacen) {
        saveArticulo(articulo);
      }
      saveFileXML(fileName);

      //Posible mejora
    } catch (ParserConfigurationException | TransformerException | ExistenciasNoValidasException e) {
      throw new AlmacenXMLException("Error al generar XML: " + e.getMessage());
    }
  }

  /**
   * Crea Documento XML
   * @throws ParserConfigurationException
   */
  private void createDocumentXML() throws ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    xml = builder.newDocument();    
  }
  
  /**
   * Guarda el Elemento Raiz
   */
  private void saveRoot() {
    Element root = xml.createElement(AlmacenXML.ALMACEN);
    xml.appendChild(root);
  }
  
  /**
   * Guardar Articulo
   * @param articulo
   * @throws ExistenciasNoValidasException
   */
  private void saveArticulo(Articulo articulo) throws ExistenciasNoValidasException {
    Element raiz = xml.getDocumentElement();

    Element articuloElemento = xml.createElement(AlmacenXML.ARTICULO);
    raiz.appendChild(articuloElemento);

    guardarCampoArticuloXML(AlmacenXML.NOMBRE, articulo.getDescripcion(), articuloElemento);
    guardarCampoArticuloXML(AlmacenXML.EXISTENIAS, articulo.getExistencias(), articuloElemento);
    guardarCampoArticuloXML(AlmacenXML.NUMEROVENTAS, articulo.getNumVentas(), articuloElemento);
    guardarCampoArticuloXML(AlmacenXML.PRECIOCOMPRA, articulo.getPreCompra(), articuloElemento);
    guardarCampoArticuloXML(AlmacenXML.PRECIOVENTA, articulo.getPreVenta(), articuloElemento);
    guardarCampoArticuloXML(AlmacenXML.STOCKMAXIMO, articulo.getStockMax(), articuloElemento);
    guardarCampoArticuloXML(AlmacenXML.STOCKSEGURO, articulo.getStockSeguridad(), articuloElemento);
  }
  
  /**
   * Guardar Campos de Articulo STRING
   * @param campo
   * @param valor
   * @param articuloElemento
   */
  private void guardarCampoArticuloXML(String campo, String valor, Element articuloElemento) {
    Document xml = articuloElemento.getOwnerDocument();
    Element campoElement = xml.createElement(campo);
    campoElement.appendChild(xml.createTextNode(valor));
    articuloElemento.appendChild(campoElement);
  }
  
  /**
   * Guarda Campos de Articulo DOUBLE
   * @param campo
   * @param valor
   * @param articuloElemento
   */
  private void guardarCampoArticuloXML(String campo, double valor, Element articuloElemento) {
    //Valor a Cadena
    String valorCadena = Double.toString(valor);

    Document xml = articuloElemento.getOwnerDocument();
    Element campoElement = xml.createElement(campo);
    campoElement.appendChild(xml.createTextNode(valorCadena));
    articuloElemento.appendChild(campoElement);
  }
  
  /**
   * Guardar Campos de Articulo INT
   * @param campo
   * @param valor
   * @param articuloElemento
   */
  private void guardarCampoArticuloXML(String campo, int valor, Element articuloElemento) {
    String valorCadena = Integer.toString(valor);

    Document xml = articuloElemento.getOwnerDocument();
    Element campoElement = xml.createElement(campo);
    campoElement.appendChild(xml.createTextNode(valorCadena));
    articuloElemento.appendChild(campoElement);
  }
  
  /**
   * Guardar Fichero XML
   * @param fileName
   * @throws IOException
   * @throws TransformerException
   */
  private void saveFileXML(String fileName) throws IOException, TransformerException {
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    DOMSource xmlSource = new DOMSource(xml);
    StreamResult output = new StreamResult(new FileWriter(fileName));
    transformer.transform(xmlSource, output);
  }
}
