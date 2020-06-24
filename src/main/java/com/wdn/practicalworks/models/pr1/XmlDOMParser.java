package com.wdn.practicalworks.models.pr1;

import com.wdn.practicalworks.models.pr1.elements.Group;
import com.wdn.practicalworks.models.pr1.elements.Student;
import com.wdn.practicalworks.models.pr1.elements.Subject;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class XmlDOMParser {
    public static Group parse(String inputfile) {
        Group group = new Group();
        try {
            File inputFile = new File(inputfile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList xmlgroup = doc.getElementsByTagName("student");
            ArrayList<Student> students = new ArrayList<>();
            for (int temp = 0; temp < xmlgroup.getLength(); temp++) {
                Node xmlstudent = xmlgroup.item(temp);
                Student student = new Student();
                NamedNodeMap attributes = xmlstudent.getAttributes();
                student.setFirstname(attributes.getNamedItem("firstname").getNodeValue());
                student.setLastname(attributes.getNamedItem("lastname").getNodeValue());
                student.setGroupnumber(attributes.getNamedItem("groupnumber").getNodeValue());

                NodeList studentChild = xmlstudent.getChildNodes();
                ArrayList<Subject> subjects = new ArrayList<>();
                for (int temp2 = 0; temp2 < studentChild.getLength(); temp2++) {
                    Node xmlsubject = studentChild.item(temp2);
                    if(xmlsubject.getNodeType() == Node.ELEMENT_NODE &&
                            xmlsubject.getNodeName() != null) {
                        NamedNodeMap childAttributes = xmlsubject.getAttributes();
                        if(childAttributes.getLength() > 0) {
                            Subject subject = new Subject();
                            subject.setTitle(childAttributes.getNamedItem("title").getNodeValue());
                            subject.setMark(Integer.parseInt(childAttributes.getNamedItem("mark").getNodeValue()));
                            subjects.add(subject);
                        }

                        if(xmlsubject.hasChildNodes()) {
                            student.setAverage(Float.parseFloat(xmlsubject.getFirstChild().getNodeValue()));
                        }

                    }
                    student.setSubjects(subjects);
                }
                students.add(student);
            }
            group.setStudents(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return group;
    }

    public static boolean validate(String inputfile) throws ParserConfigurationException, IOException, SAXException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(
                    new ErrorHandler() {
                        public void warning(SAXParseException e) throws SAXException {
                            System.out.println("WARNING : " + e.getMessage()); // do nothing
                        }

                        public void error(SAXParseException e) throws SAXException {
                            System.out.println("ERROR : " + e.getMessage());
                            throw e;
                        }

                        public void fatalError(SAXParseException e) throws SAXException {
                            System.out.println("FATAL : " + e.getMessage());
                            throw e;
                        }
                    }
            );
            builder.parse(new InputSource(inputfile));
            return true;
    }

    public static void create_old(String inputfile) {

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("company");
            document.appendChild(root);

            // employee element
            Element employee = document.createElement("employee");

            root.appendChild(employee);

            // set an attribute to staff element
            Attr attr = document.createAttribute("id");
            attr.setValue("10");
            employee.setAttributeNode(attr);

            //you can also use staff.setAttribute("id", "1") for this

            // firstname element
            Element firstName = document.createElement("firstname");
            firstName.appendChild(document.createTextNode("James"));
            employee.appendChild(firstName);

            // lastname element
            Element lastname = document.createElement("lastname");
            lastname.appendChild(document.createTextNode("Harley"));
            employee.appendChild(lastname);

            // email element
            Element email = document.createElement("email");
            email.appendChild(document.createTextNode("james@example.org"));
            employee.appendChild(email);

            // department elements
            Element department = document.createElement("department");
            department.appendChild(document.createTextNode("Human Resources"));
            employee.appendChild(department);

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(inputfile));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static void create(String inputfile, Group group) throws JAXBException{
        group.recalc();
        JAXBContext context = JAXBContext.newInstance(Group.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File output = new File(inputfile);
        marshaller.marshal(group, output);
    }

}
