package gestisimal;

import java.io.IOException;
import java.io.PrintWriter;


public class AlmacenCSVWriter {

  static final String HEAD = 
      "Nombre,Existencias,PrecioCompra,PrecioVenta,NumeroVentas,StockSeguridad,StockMaximo";
  private Almacen almacen;
  private PrintWriter file;
  
  AlmacenCSVWriter(Almacen almacen) {
    this.almacen = almacen;
  }
  
  void guardarArchivo(String nombreFichero) throws IOException, ExistenciasNoValidasException {
    file = new PrintWriter(nombreFichero);
    file.println(AlmacenCSV.HEAD);
    for (Articulo articulo: almacen) {
      guardarArticulo(articulo);
    }
    file.close();
  }
  private void guardarArticulo(Articulo articulo) throws IOException, ExistenciasNoValidasException {

    file.write("\"" + articulo.getDescripcion() + "\",");
    file.write("\"" + articulo.getExistencias() + "\",");
    file.write("\"" + articulo.getPreCompra() + "\",");
    file.write("\"" + articulo.getPreVenta() + "\",");
    file.write("\"" + articulo.getNumVentas() + "\",");
    file.write("\"" + articulo.getStockSeguridad() + "\",");
    file.write("\"" + articulo.getStockMax() + "\",");
    file.println();

  }
}
