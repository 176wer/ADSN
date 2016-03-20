/**
 * Project Name:ADSN
 * File Name:XMLReader.java
 * Package Name:control
 * Date:2016年1月1日下午3:57:56
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package control;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.Experiment;

/**
 * ClassName:XMLReader <br/>
 * Function:  单例模式对XML文档进行修改
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月1日 下午3:57:56 <br/>
 * @author   zhao
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class XMLReader {
private List<Experiment> list=new ArrayList<Experiment>();
 
public static XMLReader xmlReader=new XMLReader();

public static XMLReader getXMLReader(){
	return xmlReader;
}

 
 
/**  
* @Title: getExperiments  
* @Description: TODO 
* @return List    返回类型  
* @throws  
*/
public List getExperiments(){
	return list;
}
private XMLReader(){
	SAXReader reader=new SAXReader();
	try {
		Document doc=reader.read(XMLReader.class.getResource("/config/experiment.xml"));
		Element ids=(Element)doc.selectObject("/experiment/id"); 
		//获取实验次数
	    String id=ids.getStringValue();
		Element numbers=(Element)doc.selectObject("/experiment/previous");
	    List<Element> elements=numbers.elements();
	    for(Element ele:elements){
	    	 String str=ele.getText();
	    	  String[] s=str.split("-");
	    	  Experiment experiment=new Experiment();
	    	  experiment.setId(s[0]);
	    	  experiment.setMaxId(id);
	    	  experiment.setDirections(s[1]);
	    	  experiment.setDate(s[2]);
	    	  list.add(experiment);
	    }
		
	} catch (DocumentException e) {
		e.printStackTrace();
		
	}
}
public static void main(String[] agrs){
	XMLReader xml=XMLReader.getXMLReader();
 
}
}

