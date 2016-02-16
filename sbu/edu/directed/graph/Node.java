package sbu.edu.directed.graph;

import java.util.LinkedList;

public class Node {
	
	private LinkedList<Node> toNodes = null;
	private LinkedList<Node> fromNodes = null;
	private String link = null;
	
	public Node(String link){
		this.link = link;
	}
	
	public boolean isThisNode(String link){
		if(this.link.equals(link)){
			return true;
		}
		
		return false;
	}
	
	public boolean equals(Node node){
		if(this.link.equals(node.getLink())){
			return true;
		}
		
		return false;
	}
		
	public LinkedList<Node> getToNodes() {
		return toNodes;
	}

	public void setToNodes(LinkedList<Node> toNodes) {
		this.toNodes = toNodes;
	}

	public LinkedList<Node> getFromNodes() {
		return fromNodes;
	}

	public void setFromNodes(LinkedList<Node> fromNodes) {
		this.fromNodes = fromNodes;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}	
}
