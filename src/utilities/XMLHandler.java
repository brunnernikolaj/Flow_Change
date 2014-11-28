/*
 * This file saves reads data from an xml file and saves data to an xml file
 * The methods are static, because the class is not suposed to have eny form of
 * state / contain any data specific for the class
 */
package utilities;

import entity.Game;
import entity.Player;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Date: 26/11 2014
 * Project: Flow Change
 * @author Casper Schultz 
 */
public class XMLHandler {
    
    private String filepath;
    private Player player;
    
    public XMLHandler(String filepath) {
        this.filepath = filepath;
    }
    
    public void setFilepath(String filepath) {
        this.filepath = filepath;  
    }
    
    /**
     * Loads data from an xml file with specific nodes and saves the data in
     * an array, containing game objects
     * 
     * @param filepath
     * @return ArrayList
     */
    public ArrayList<Game> load() {
        
        ArrayList<Game> games = new ArrayList();
        
        try {
            
            File xmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            NodeList nodeList = doc.getElementsByTagName("game");
            
            doc.getDocumentElement().normalize();
            
            for (int i = 0, listLength = nodeList.getLength(); i < listLength; i++) {
 
		Node nNode = nodeList.item(i);
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
                        
                        String playerName = (eElement.getElementsByTagName("player").item(0).getTextContent());
                        int score = Integer.parseInt(eElement.getElementsByTagName("score").item(0).getTextContent());

                        games.add(new Game(new Player(playerName, score)));           
		}       
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return games;
    }
    
    /**
     * Saves games from an arraylist into an xml file, with specific nodes
     * @param games
     * @param filepath
     * @return 
     */
    public boolean save(ArrayList<Game> games) {
        
        try {
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("games");
            doc.appendChild(rootElement);
            
            for (Game g : games) {
                
                // Game element
		Element game = doc.createElement("game");
		rootElement.appendChild(game);
                
                Element player = doc.createElement("player");
		player.appendChild(doc.createTextNode(g.getPlayer().getPlayername()));
		game.appendChild(player);
                
                Element score = doc.createElement("score");
		score.appendChild(doc.createTextNode("" + g.getPlayer().getCredits()));
		game.appendChild(score);
                
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return false;
    }
}
