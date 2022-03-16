package ca.bcit.comp2522.labs.lab07;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class for Graph class performing a Breadth First Search.
 *
 * @author Maximilian Anderson-Baier
 */
public final class MazeSolver {

    private MazeSolver() {
    }

    /**
     * Accepts user input for a File Name.
     *
     * @return a string
     */
    public static String getFileName() {
        Scanner fileName = new Scanner(System.in);
        System.out.println("Provide a File Name:");
        return fileName.next();
    }

    /**
     * Accepts user integer input representing a desired starting Vertex.
     *
     * @return an integer
     */
    public static int getStartVertexNumFromUser() {
        Scanner getIndexNum = new Scanner(System.in);
        System.out.println("Enter the starting Vertex: ");
        return getIndexNum.nextInt();
    }

    /**
     * Accepts user integer input representing a desired ending Vertex.
     *
     * @return an integer
     */
    public static int getEndVertexNumFromUser() {
        Scanner getIndexNum = new Scanner(System.in);
        System.out.println("Enter the end Vertex: ");
        return getIndexNum.nextInt();
    }


    /**
     * Drives the program.
     *
     * @param args unused
     * @throws IOException when File does not exist
     */
    public static void main(final String[] args) throws IOException {

        Graph graph = new Graph();
        String userInput = getFileName();

        int totalLength = graph.getTotalVertexCount(userInput);
        graph.addVerticesFromMatrix(totalLength);
        graph.addVertexNeighbours(userInput);

        graph.printAllVertex();
        int firstIndex = getStartVertexNumFromUser();
        int secondIndex = getEndVertexNumFromUser();
        Vertex startingVertex = graph.getVertexAtIndex(firstIndex);
        Vertex secondVertex = graph.getVertexAtIndex(secondIndex);

        graph.breadthFirstSearch(startingVertex);

        List<Vertex> shortList =  graph.findShortestPath(startingVertex, secondVertex);
        graph.printShortestPath(shortList, startingVertex, secondVertex);

    }
}


