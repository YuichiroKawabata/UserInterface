package org.kyutech.kawabata.UserInterface.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PrintXML {
	File file;
	public PrintXML(File file) {
		this.file = file;
	}

	public static void main(String[] args) {
		System.out.println("test");
		File filemain = new File("tt.xml");
		PrintXML readXML = new PrintXML(filemain);
		readXML.runner();
	}
	
	public void runner() {
		DocumentBuilderFactory factory;
		DocumentBuilder        builder;
		Node root;
		Node child;
		NodeList XMLdata;
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setIgnoringComments(true);
			factory.setValidating(true);
			root    = builder.parse(this.file);
			
			showNodes(root, "");
		} catch (ParserConfigurationException e0) {
			System.out.println(e0.getMessage());
		} catch (SAXException e1){
			System.out.println(e1.getMessage());
		} catch (IOException e2) {
			System.out.println(e2.getMessage());
		}
	}

	/**
	 * nodeの子ノードを全て表示します。子ノードがない場合はなにも出力しません。 spaceはノードを表示する前の行頭のスペースです。
	 * （再帰処理をしているので注意）
	 */
	public void showNodes(Node node, String space) {
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node2 = nodes.item(i);
			System.out.println(space + "「" + node2.getNodeName() + "/"
					+ node2.getNodeValue() + "/" + getTypeMes(node2) + "」");
			showNodes(node2, space + "    ");
		}
	}

	/** ノードの種類を示します */
	public String getTypeMes(Node node) {
		String s = null;
		switch (node.getNodeType()) {
		case Node.ATTRIBUTE_NODE:
			s = "Attr";
			break;
		case Node.CDATA_SECTION_NODE:
			s = "CDATASection";
			break;
		case Node.COMMENT_NODE:
			s = "Comment";
		case Node.DOCUMENT_FRAGMENT_NODE:
			s = "DocumentFragment";
		case Node.DOCUMENT_NODE:
			s = "Document";
		case Node.DOCUMENT_TYPE_NODE:
			s = "DocumentType";
		case Node.ELEMENT_NODE:
			s = "Element";
		case Node.ENTITY_NODE:
			s = "Entity";
			break;
		case Node.ENTITY_REFERENCE_NODE:
			s = "EntityReference";
		case Node.NOTATION_NODE:
			s = "Notation";
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			s = "ProcessingInstruction";
		case Node.TEXT_NODE:
			s = "Text";
		default:
			break;
		}
		return s;
	}

	private Node getRootNode(File file) {
		// TODO Auto-generated method stub
		// DocumentBuilderインスタンスの生成
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		Document document = null;
		try {
			document = documentBuilder.parse(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return document;
	}
}
