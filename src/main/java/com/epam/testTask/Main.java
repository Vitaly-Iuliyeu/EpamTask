package com.epam.testTask;

import com.epam.testTask.model.Company;
import com.epam.testTask.xml.DOMParser;
import com.epam.testTask.xml.XMLFileReader;
import com.epam.testTask.xml.XMLFileWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Node rootNode = null;
    private static NodeList rootChild = null;
    private static Document document = null;

    public static void main(String[] args) {

        XMLFileReader xmlFileReader = new XMLFileReader("src/main/resources/input.xml");
        try {
            document = xmlFileReader.buildDocument();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        if (document != null) {
            rootNode = document.getFirstChild();
        }
        if (rootNode != null) {
            rootChild = rootNode.getChildNodes();
        }

        DOMParser domParser = new DOMParser();
        if (rootChild != null) {
            domParser.parseDevices(rootChild);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("count of employee: ");
        Company company = new Company(scanner.nextInt(), domParser.getDeviceList());

        new XMLFileWriter(company).toXML();

    }

}
