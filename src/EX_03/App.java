package EX_03;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;

public class App {
    private static final String FILENAME = "staff.xml";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        // Iniciamos la clase que nos permite iniciar la "fábrica" de documentos XML
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            // Opcional, pero se recomienda para evitar ataques XXE (XML External Entities)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // Iniciamos DocumentBuilder para analizar un archivo XML
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Abrimos el fichero y lo analizamos con db.parse
            Document doc = db.parse("https://www.xataka.com/sitemap_index.xml");

            // La normalización es opcional, pero recomendado para XML mal formateados
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Elemento raiz :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // Obtenemos todos los staffs en una lista de nodos que podemos recorrer
            NodeList list = doc.getElementsByTagName("sitemap");
            int file_Id = 0;


            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node= list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Obtener el atributo de identificación del staff
                    String id = element.getAttribute("sitemap");

                    // Obtener el texto de los diferentes elementos.
                    String loc = element.getElementsByTagName("loc").item(0).getTextContent();
                    System.out.println(loc);


                    Document doc2 = db.parse((loc));
                    doc2.getDocumentElement().normalize();
                    NodeList list2 = doc2.getElementsByTagName("url");
                    URL url = new URL(loc);
                    String path = url.getPath().replace('/', '_');
                    System.out.println(path);

                    try (PrintStream docu = new PrintStream("C:\\Users\\nil\\IdeaProjects\\XML\\xml\\" + path + ".txt")) {

                    }


                    for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
                        Node node2 = list2.item(temp2);
                        if (node2.getNodeType() == node2.ELEMENT_NODE) {
                            Element element2 = (Element) node2;


                            String id2 = element2.getAttribute("loc");
                            String loc2;


                            loc2 = element2.getElementsByTagName("loc").item(0).getTextContent();
                            loc2 = loc2 + "\n";
                            try {
                                Files.write(Paths.get("C:\\Users\\nil\\IdeaProjects\\XML\\xml\\" + path + ".txt"), loc2.getBytes(), StandardOpenOption.APPEND);
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }

            }catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
