package org.egibide.lectorrss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by widemos on 28/3/15.
 */
public class ParserSAX extends DefaultHandler {

    // Parser SAX para leer un feed RSS que contiene noticias

    // Aquí dejaremos los objetos extraídos
    private List<Noticia> lista;

    // Variables temporales para procesar el XML
    private String temp;
    private Noticia n;

    public ParserSAX() {
        lista = new ArrayList<Noticia>();
    }

    public List<Noticia> parseDocument(InputStream is) {

        // Patrón "factoría de objetos"
        SAXParserFactory spf = SAXParserFactory.newInstance();

        // Si queremos validación contra el DTD lo ponemos a true
        spf.setValidating(false);

        try {
            // Fabricar una instancia del parser
            SAXParser sp = spf.newSAXParser();

            // Procesar el fichero y registrar esta clase para los "callback"
            sp.parse(is, this);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParserSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ParserSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParserSAX.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        // Hemos encontrado el comienzo de un elemento
        if (qName.equalsIgnoreCase("item")) {
            n = new Noticia();
        } else if (qName.equalsIgnoreCase("enclosure")) {
            if (n != null && n.getUrlImagen() == null)
                n.setUrlImagen(attributes.getValue("url"));
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        // Contenido del elemento
        temp = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // Hemos encontrado la etiqueta de cierre
        if (qName.equalsIgnoreCase("item")) {
            lista.add(n);
        } else if (qName.equalsIgnoreCase("title")) {
            if (n != null)
                n.setTitulo(temp);
        } else if (qName.equalsIgnoreCase("description")) {
            if (n != null)
                n.setResumen(temp);
        }
    }


    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.println("SAX: Error en XML (línea " + e.getLineNumber() + "): " + e.getMessage());
    }

}
