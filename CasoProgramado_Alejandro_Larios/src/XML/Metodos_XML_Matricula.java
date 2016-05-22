/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import vista.FRM_Matricula;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class Metodos_XML_Matricula {

    FRM_Matricula frm_Matricula;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document document;
    ArrayList titulos;
    ArrayList valores;
    Element raiz, principal;
    String arregloInformacion[];
    Source source;
    Result result;
    Result console;
    Transformer transformer;
    String nombreArchivo;

    public Metodos_XML_Matricula(FRM_Matricula frm_Matricula) {
        this.frm_Matricula = frm_Matricula;
        nombreArchivo = "Matriculas";

        if (cargarXML()) {
            System.out.println("Ya existe un archivo XML_Matriculas creado, ya fue cargado y puede proceder a utilizarlo");
        } else {
            crearXML();
            System.out.println("No existía un archivo XML_Matriculas creado, ya fue creado y puede proceder a utilizarlo");
        }

        arregloInformacion = new String[4];
        titulos = new ArrayList();
        valores = new ArrayList();
    }

    public void crearXML() {
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "xml", null);
            document.setXmlVersion("1.0");
            source = new DOMSource(document);
            result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));

            console = new StreamResult(System.out);

            transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(source, result);
            transformer.transform(source, console);

        } catch (Exception e) {
            System.err.println("Error al crear el archivo XML_Matriculas: " + e);
        }
    }

    public boolean cargarXML() {
        boolean cargo = false;
        try {

            File fXmlFile = new File(nombreArchivo + ".xml");
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(fXmlFile);
            document.getDocumentElement().normalize();
            cargo = true;

            NodeList nList = document.getElementsByTagName("Matricula");
            Node nNode = nList.item(0);
            raiz = (Element) nNode;

        } catch (Exception e) {
            System.out.println("Error al cargar el archivo XML_Matriculas" + e);
        }
        return cargo;
    }

    public boolean consultarMatricula(String codigo) {
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Matricula");
        Node tag = null, datoContenido = null;

        boolean itemEncontrado = false;
        int contador = 0;

        for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
            Node item = listaDeItems.item(contadorItems);
            NodeList datosItem = item.getChildNodes();
            for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                tag = datosItem.item(contadorTags);
                datoContenido = tag.getFirstChild();

                if (tag.getNodeName().equals("codigo") && datoContenido.getNodeValue().equals("" + codigo)) {
                    itemEncontrado = true;
                }
                if (itemEncontrado && contador < 4) {
                    arregloInformacion[contador] = datoContenido.getNodeValue();
                    contador++;
                }
            }
            frm_Matricula.agregarInformacionTabla(arregloInformacion);
        }
        return itemEncontrado;
    }

    public void agregarMatricula(String arregloInformacion[]) {
        try {

            raiz = document.createElement("Matricula");
            principal = document.createElement("Matricula");
            document.getDocumentElement().appendChild(raiz);

            Element valor0 = document.createElement("codigo");
            Text text0 = document.createTextNode(arregloInformacion[0]);
            Element valor1 = document.createElement("cedula");
            Text text1 = document.createTextNode(arregloInformacion[1]);
            Element valor2 = document.createElement("nombreEstudiante");
            Text text2 = document.createTextNode(arregloInformacion[2]);
            Element valor3 = document.createElement("sigla");
            Text text3 = document.createTextNode(arregloInformacion[3]);

            raiz.appendChild(valor0);
            valor0.appendChild(text0);
            raiz.appendChild(valor1);
            valor1.appendChild(text1);
            raiz.appendChild(valor2);
            valor2.appendChild(text2);
            raiz.appendChild(valor3);
            valor3.appendChild(text3);

            source = new DOMSource(document);
            result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
            console = new StreamResult(System.out);
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);

        } catch (Exception e) {
            System.err.println("Error al guardar en XML_Matriculas: " + e);
        }
    }

    public void eliminarMatricula(String informacion[]) {
        Element raiz = document.getDocumentElement();
        NodeList listaDeItems = raiz.getElementsByTagName("Matricula");
        Node tag = null, datoContenido = null, tag2 = null, datoContenido2 = null;;

        try {
            for (int contadorItems = 0; contadorItems < listaDeItems.getLength(); contadorItems++) {
                Node item = listaDeItems.item(contadorItems);
                NodeList datosItem = item.getChildNodes();
                Node item2 = listaDeItems.item(contadorItems);
                NodeList datosItem2 = item2.getChildNodes();

                for (int contadorTags = 0; contadorTags < datosItem.getLength(); contadorTags++) {
                    tag = datosItem.item(contadorTags);
                    datoContenido = tag.getFirstChild();

                    tag2 = datosItem2.item(contadorTags);
                    datoContenido2 = tag2.getFirstChild();

                    if (tag.getNodeName().equals("codigo") && datoContenido.getNodeValue().equals("" + informacion[0]) && tag2.getNodeName().equals("sigla") && datoContenido2.getNodeValue().equals("" + informacion[1])) {
                        raiz.removeChild(item);
                        source = new DOMSource(document);
                        result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
                        console = new StreamResult(System.out);
                        transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(source, result);
                        transformer.transform(source, console);
                    }
                }
                System.out.println("No se encuentra");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar en XML_Matriculas: " + e);
        }
    }
}
