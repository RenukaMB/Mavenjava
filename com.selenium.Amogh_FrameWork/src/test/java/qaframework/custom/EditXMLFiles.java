package qaframework.custom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




public class EditXMLFiles{
	WebDriver wb_driver;
	WaitForElement wait;

	/*******************************************************************************************
	 ' Description  : Function to modify the XML content 
	 ' Date         : 22-Sep-2015
	 ' Author       : Pallavi
	 *******************************************************************************************/
	  public EditXMLFiles modifyXML(String strFileName, String[] StrOldValue, String[] StrNewValue, String[] strNode)
	    throws Exception {
		
	   File file = new File(strFileName);
	   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	   dbf.setCoalescing(true);
	   DocumentBuilder db = dbf.newDocumentBuilder();
	   Document doc = db.parse(file);

	   EditXMLFiles obj = new EditXMLFiles();
	   for(int i=0;i<strNode.length;i++){
	   obj.changeValue(doc, StrOldValue[i], StrNewValue[i], strNode[i]);
	   }
	   obj.save(file, doc);
	   return this;
	  }
	  
	  /*******************************************************************************************
	  ' Description  : Function to modify the XML content 
	  ' Date         : 22-Sep-2015
	 ' Author        : Pallavi
	  *******************************************************************************************/
	  public void changeValue(Document doc, String oldValue, String NewValue,
	    String strNode) throws Exception {
	   Element root = doc.getDocumentElement();
	   NodeList childNodes = root.getElementsByTagName(strNode);

	   for (int i = 0; i < childNodes.getLength(); i++) {
	    NodeList subChildNodes = childNodes.item(i).getChildNodes();

	    for (int j = 0; j < subChildNodes.getLength(); j++) {

	     if (subChildNodes.item(j).getTextContent().equals(oldValue)) {
	      subChildNodes.item(j).setTextContent(NewValue);
	     }

	    }
	   }

	  }
	  /*******************************************************************************************
	  ' Description  : Function to modify the XML content 
	  ' Date         : 22-Sep-2015
	 ' Author        : Pallavi
	     ******************************************************************************************/
	  public void save(File file, Document doc) throws Exception {
	   TransformerFactory factory1 = TransformerFactory.newInstance();
	   Transformer transformer = factory1.newTransformer();
	   transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	   StringWriter writer = new StringWriter();
	   StreamResult result = new StreamResult(writer);
	   DOMSource source = new DOMSource(doc);
	   transformer.transform(source, result);
	   String s = writer.toString();
	   System.out.println(s);

	   FileWriter fileWriter = new FileWriter(file);
	   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	   bufferedWriter.write(s);
	   bufferedWriter.flush();
	   bufferedWriter.close();
	  }
	  
	  /*******************************************************************************************
	  ' Description  : Function to get file's path
	  ' Date         : 25-Aug-2015
	  ' Author       : Pallavi
	     ******************************************************************************************/
	  public String getFilePath(String strPath) throws Exception {
	  PathsProperties objAP = new PathsProperties();
		Properties pathProps = objAP.Read_FilePath();
		String FILE_PATH = pathProps.getProperty(strPath);
		String[] fileName = FILE_PATH.split("/");
		File file = new File(fileName[fileName.length - 1]);
		String path = file.getAbsolutePath();
		FILE_PATH = path.replaceAll(fileName[fileName.length - 1], FILE_PATH);
	   return FILE_PATH;
	  }
	  
	  /***************************************************************
	  'Description :Function to copy the XML file.  
	  'Date        : 22-Sep-2015
	 ' Author      : Pallavi
	  ***************************************************************/
	  public EditXMLFiles copyXML(String strFileName,
	    String strCopiedFileName) throws IOException {

	   InputStream inStream = null;
	   OutputStream outStream = null;

	   File Originalfile = new File(strFileName);
	   File Copiedfile = new File(strCopiedFileName);

	   inStream = new FileInputStream(Originalfile);
	   outStream = new FileOutputStream(Copiedfile);

	   byte[] buffer = new byte[1024];
	   int length;
	   // copy the file content in bytes
	   while ((length = inStream.read(buffer)) > 0) {
	    outStream.write(buffer, 0, length);
	   }
	   inStream.close();
	   outStream.close();

	   System.out.println("File copying is successful!");
	   return this;
	  }
	  
	  /***************************************************************
	  'Description :Function to read the XML file.  
	  'Date   	   :04/09/2015
	  'Author  	   :Deepa
	  ***************************************************************/
	public String readXML(String tagName, String strFilePath) throws Exception {
        String fXmlFile = getFilePath(strFilePath);
		//File fXmlFile = new File(
			//	"C:\\workspace_Mobile\\com.qsgsoft.eICSMobile\\src\\test\\resources\\SupportFiles\\CopiedMobileTestData.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		String strPreconditionvalue = doc.getElementsByTagName(tagName).item(0)
				.getTextContent();
		return strPreconditionvalue;
	}
	
	 /***************************************************************
	  'Description :Function to read the XML file.  
	  'Date   	   :04/09/2015
	  'Author  	   :Deepa
	  ***************************************************************/
//	public String readTestDataXML(String tagName) throws Exception {
//      String fXmlFile = getFilePath("MobileTestData_Path");
//		//File fXmlFile = new File(
//			//	"C:\\workspace_Mobile\\com.qsgsoft.eICSMobile\\src\\test\\resources\\SupportFiles\\CopiedMobileTestData.xml");
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		Document doc = dBuilder.parse(fXmlFile);
//		doc.getDocumentElement().normalize();
//		String strPreconditionvalue = doc.getElementsByTagName(tagName).item(0)
//				.getTextContent();
//		return strPreconditionvalue;
//	}
}
