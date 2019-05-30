package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        String stringToZip = "hola mama";
        List<Node> characters = getChars(stringToZip);

        System.out.println(characters);
        Node node = codeHufmann(characters);
        System.out.println(node);

        String code = generateCode (node, stringToZip);
        System.out.print(code);

    }

    private static String generateCode(Node node, String stringToZip) {
        String code = "";
        for (int i= 0 ; i < stringToZip.length(); i++){
            List<String> acum = new ArrayList<>();
            acum.add("");
            code += getCode2(stringToZip.charAt(i), node, "")+" ";
            acum.clear();
            acum.add("");
        }
        return code;
    }

    private static String getCode2(char charAt, Node node, String s) {
        if (charAt == node.getCaracter()) {
            return s;
        }
        if (node.getRightSon() != null) {
            String val = getCode2(charAt, node.getRightSon(), s+"0");
            if (val != null)
                return val;
        }
        if (node.getLeftSon() != null) {
            String val = getCode2(charAt, node.getLeftSon(), s + "1");
            if (val != null)
                return val;

        }
        return null;
}


    private static List<Node> getChars(String stringToZip) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0 ; i < stringToZip.length() ; i++){
            if (!contains(nodes,(stringToZip.charAt(i)))) {
                Double charFrequency = getFrequency(stringToZip, i);
                Node node = new Node(stringToZip.charAt(i), charFrequency, null, null);
                nodes.add(node);
            }
        }
        Collections.sort(nodes);
        return nodes;
    }

    private static boolean contains(List<Node> nodes, char charAt) {
        for (Node node : nodes){
            if (node.getCaracter() == charAt)
                return true;
        }
        return false;
    }

    private static Double getFrequency(String stringToZip, int i) {
        Double acum = 0d ;
        for (int j = 0 ; j < stringToZip.length(); j++){
            if (stringToZip.charAt(j) == stringToZip.charAt(i))
                acum = acum +1;
        }
        return acum/stringToZip.length();
    }

    private static Node codeHufmann (List<Node> list){
        while (list.size() != 1){
            Node node1 = list.get(0);
            Node node2 = list.get(1);
            Node nodeFather = new Node('0', node1.getFrecuency()+node2.getFrecuency(), node1, node2 );
            list.add(nodeFather);
            list.remove(0); list.remove(0);
            Collections.sort(list);
        }
        return list.get(0);
    }


    public static class Node implements Comparable{


        private char caracter;
        private Double frecuency;
        private Node rightSon;
        private Node leftSon;

        public Node(char caracter, Double frecuency, Node rightSon, Node leftSon) {
            this.caracter = caracter;
            this.frecuency = frecuency;
            this.rightSon = rightSon;
            this.leftSon = leftSon;
        }


        public char getCaracter() {
            return caracter;
        }

        public void setCaracter(char caracter) {
            this.caracter = caracter;
        }

        public Double getFrecuency() {
            return frecuency;
        }

        public void setFrecuency(Double frecuency) {
            this.frecuency = frecuency;
        }

        public Node getRightSon() {
            return rightSon;
        }

        public void setRightSon(Node rightSon) {
            this.rightSon = rightSon;
        }

        public Node getLeftSon() {
            return leftSon;
        }

        public void setLeftSon(Node leftSon) {
            this.leftSon = leftSon;
        }

        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;
            if (this.frecuency > other.getFrecuency())
                return 1;
            else if (this.frecuency < other.getFrecuency())
                return -1;
            else return 0;
        }
    }

}


