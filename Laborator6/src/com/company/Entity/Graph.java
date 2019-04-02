package com.company.Entity;

import com.company.shapes.EdgeShape;
import com.company.shapes.NodeShape;


import java.awt.*;
import java.util.*;

public class Graph {

    private Set<NodeShape> nodeList;
    private Set<EdgeShape> edgeList;

    private ArrayList<Pair<Integer,Integer>> positionNodes;
    private ArrayList<Pair< Pair<Integer,Integer>,Pair<Integer,Integer>>> positionEdge;

    private ArrayList<Pair<Integer,Integer>> edgeMargins ;

    private Map<Pair<Integer,Integer>,NodeShape> nodeMap;
    private Map<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>,EdgeShape > edgeMap;

    private Color color;

    public Graph() {
        nodeList = new HashSet<>();
        edgeList = new HashSet<>();

        positionEdge= new ArrayList<>();
        positionNodes= new ArrayList<>();

        edgeMargins= new ArrayList<>();

        nodeMap = new HashMap<>();
        edgeMap = new HashMap<>();

        color=getRandomColor();
    }

    public void reset(){
        edgeMargins.clear();
        nodeMap.clear();
        edgeMargins.clear();
        positionNodes.clear();
        positionEdge.clear();
        nodeList.clear();
        edgeList.clear();
    }

    public Set<NodeShape> getNodeList() {
        return nodeList;
    }

    public void setNodeList(Set<NodeShape> nodeList) {
        this.nodeList = nodeList;
    }

    public Set<EdgeShape> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Set<EdgeShape> edgeList) {
        this.edgeList = edgeList;
    }

    public void  addNode(NodeShape nodeShape){
        nodeList.add(nodeShape);
    }

    public void addEdge(EdgeShape edgeShape){
        edgeList.add(edgeShape);
    }

    public void drawEdgeAt(Pair<Integer,Integer> point1 , Pair<Integer,Integer> point2){
        EdgeShape edge = getEdgeShape(point1,point2);
        Pair coordonate = new Pair<>(point1,point2);
        edgeList.add(edge);
        positionEdge.add(coordonate);
        edgeMap.put(coordonate,edge);
    }


    public void drawShapeAt(int x, int y) {
        NodeShape node=getNodeShape(x, y);
        Pair<Integer, Integer> coordonate = new Pair<>(x,y);
        nodeList.add(node);
        positionNodes.add(coordonate);
        nodeMap.put(coordonate,node);

    }


    private  EdgeShape getEdgeShape(Pair<Integer,Integer> point1 , Pair<Integer,Integer> point2){
        return new EdgeShape(point1.getKey(),point1.getValue(),point2.getKey(),point2.getValue());
    }

    private NodeShape getNodeShape(int x, int y){

        int radius=50;
        NodeShape nodeShape = new NodeShape(x,y,radius);
        nodeShape.setColor(this.color);
        return nodeShape;

    }

    public void init(){
        edgeMargins.clear();
    }

    public int foundInShape(int x, int y, int radius, boolean drawEdge, boolean delete){


        if(!positionNodes.isEmpty()){
            for (Pair<Integer, Integer> position: positionNodes) {
                if ((Math.abs( position.getKey()-x)<=radius) && (Math.abs( position.getValue()-y)<=radius) ){
                    if(drawEdge) {
                        edgeMargins.add(position);
                        if (edgeMargins.size() == 2) {

                            drawEdgeAt(edgeMargins.get(0), edgeMargins.get(1));
                            edgeMargins.clear();
                        }
                    }
                    if(delete)
                    {
                        positionNodes.remove(position);
                        nodeList.remove(nodeMap.get(position));
                        nodeMap.remove(position);


                        positionEdge.stream()
                                .filter(pairPairPair -> pairPairPair.getKey().equals(position) || pairPairPair.getValue().equals(position))
                                .forEach(pairPairPair -> {
                                    edgeList.remove(edgeMap.get(pairPairPair));
                                    edgeMap.remove(pairPairPair);
                                } );

                        positionEdge.removeIf(pairPairPair -> pairPairPair.getKey().equals(position) || pairPairPair.getValue().equals(position));

                    }

                    return 1;
                }
            }
        }

        return -1;
    }

    public void drawEdge(Graphics2D g, EdgeShape edgeShape){

        g.setPaint(this.color);
        g.fill(edgeShape);
        g.draw(edgeShape);
    }

    public void drawNode(Graphics2D g, NodeShape nodeShape){
        g.setPaint(nodeShape.getColor());
        g.fill(nodeShape);
        g.setPaint(this.color);
        g.draw(nodeShape);
    }

    public Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}