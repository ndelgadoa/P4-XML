package EX_02;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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


            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Obtener el atributo de identificación del staff
                    String id = element.getAttribute("sitemap");

                    // Obtener el texto de los diferentes elementos.
                    String loc = element.getElementsByTagName("loc").item(0).getTextContent();
                    System.out.println(loc);


            Document doc2 = db.parse((loc));
            doc2.getDocumentElement().normalize();
            System.out.println("Elemento raiz :" + doc2.getDocumentElement().getNodeName());
            System.out.println("------");

            NodeList list2 = doc2.getElementsByTagName("url");

            for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
                Node node2 = list2.item(temp2);

                if (node2.getNodeType() == node2.ELEMENT_NODE) {
                    Element element2 = (Element) node2;
                    String id2 = element2.getAttribute("loc");
                    String loc2;
                    if (element2.getElementsByTagName("loc").item(0) == null) {

                    } else {
                        loc2 = element2.getElementsByTagName("loc").item(0).getTextContent();
                        System.out.println("Link2" + loc2);


                    }
                }
            }
                }
            }





            } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
