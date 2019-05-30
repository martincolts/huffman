package com.company;

import java.util.*;

public class Main {

    public static List<String> list = new ArrayList();
    public static String codeFinal = "";

    public static void main(String[] args) {


        String stringToZip = "hola mama";
        List<Node> caracteres = getCaracters (stringToZip);

        System.out.println(caracteres);
        Node node = codeHufmann(caracteres);
        System.out.println(node);

        String code = generateCode (node, stringToZip);
        System.out.print(code);

    }

    private static String generateCode(Node node, String stringToZip) {
        String code = "";
        for (int i= 0 ; i < stringToZip.length(); i++){
            List<String> acum = new ArrayList<>();
            acum.add("");
            code += getCode(stringToZip.charAt(i), node, acum)+" ";
            acum.clear();
            acum.add("");
        }
        return code;
    }

    private static String getCode(char charAt, Node node, List<String> acum) {
        if (charAt == node.getCaracter()) {
            for (String s : list) {
                acum.add( acum.get(0)+ s);
                acum.remove(0);
            }
            list = new ArrayList<>();
            return acum.get(0);
        }
        if (node.getRightSon() != null) {
            list.add("0");
            getCode(charAt, node.getRightSon(), acum);
            if (list != null && list.size() != 0)
                list.remove(list.size()-1);
        }
        if (node.getLeftSon() != null) {
            list.add("1");
            getCode(charAt, node.getLeftSon(), acum);
            if (list != null && list.size() != 0)
                list.remove(list.size()-1);
        }
        return acum.get(0);
    }

    private static List<Node> getCaracters(String stringToZip) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0 ; i < stringToZip.length() ; i++){
            if (!contiene(nodes,(stringToZip.charAt(i)))) {
                Double charFrecuency = getFrecuency(stringToZip, i);
                Node node = new Node(stringToZip.charAt(i), charFrecuency, null, null);
                nodes.add(node);
            }
        }
        Collections.sort(nodes);
        return nodes;
    }

    private static boolean contiene(List<Node> nodes, char charAt) {
        for (Node node : nodes){
            if (node.getCaracter() == charAt)
                return true;
        }
        return false;
    }

    private static Double getFrecuency(String stringToZip, int i) {
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


