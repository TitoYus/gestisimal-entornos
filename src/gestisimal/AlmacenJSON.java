package gestisimal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.*;

/**
 * Carga y Guarda el Almacen en JSON
 * @author ryust
 *
 */
public class AlmacenJSON {

  /**
   * Guardar JSON
   * @param almacen
   * @param fileName
   * @throws IOException
   */
  static void save(Almacen almacen, String fileName) throws IOException {
      Gson gson = new Gson();
      String json = gson.toJson(almacen);
      Files.writeString(Paths.get(fileName), json);
    }
  
  /**
   * Cargar JSON
   * @param fileName
   * @return Objeto Almacen
   * @throws IOException
   * @throws AlmacenJSONException
   */
  public static Almacen load(String fileName) throws IOException, AlmacenJSONException {
    try {
      String json = Files.readString(Paths.get(fileName)); 
      Gson gson = new Gson();
      Almacen almacen = gson.fromJson(json, Almacen.class);
      return almacen;

    } catch (com.google.gson.JsonParseException e) {
      throw new AlmacenJSONException(e.getMessage());
    }
  }
  
}
