package ca.bcit.comp2522.labs.lab07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import java.util.Queue;
import java.util.Objects;
import java.util.ArrayDeque;

/**
 * A class representing a Graph with a number of vertices.
 *
 * @author Maximilian Anderson-Baier
 * @version 2021
 */
public class Graph {

    private static final int PRIME_NUMBER_HASH_CODE = 31;
    private static final int SECOND_PRIME_NUMBER = 17;
    private final ArrayList<Vertex> vertices;
    private final Map<Vertex, ArrayList<Vertex>> paths;

    /**
     * Constructs an object of type graph.
     */
    public Graph() {
        this.vertices = new ArrayList<>();
        this.paths = new HashMap<>();
    }

    /**
     * Returns the total number of vertices within a provided adjacency Matrix.
     *
     * @param matrixFileName String representing a fileName
     * @return an integer representing the number of vertices in a provided adjacency Matrix
     */
    public int getTotalVertexCount(final String matrixFileName) {

        try {
            File file = new File(matrixFileName);
            Scanner scanFile = new Scanner(file);
            int vertexCounter = 0;

            while (scanFile.hasNext()) {
                vertexCounter += 1;
                scanFile.next();
            }
            scanFile.close();
            return vertexCounter;

        } catch (FileNotFoundException error) {
            System.out.println("File does not exist");
            System.exit(-1);
        }
        return 0;
    }

    /**
     * Creates and adds a vertex to this MazeSolver.
     *
     * @param totalMatrixLength an integer representing total number of vertices needed
     */
    public void addVerticesFromMatrix(final int totalMatrixLength) {

        for (int i = 0; i < totalMatrixLength; i++) {
            Vertex matrixVertex = new Vertex(i);
            this.vertices.add(matrixVertex);
        }
    }

    /**
     * Add neighbour vertexes to all vertexes stored within graph.
     *
     * @param matrixFileName string representing a filename to read
     * @throws IOException if file not found;
     */
    public void addVertexNeighbours(final String matrixFileName) throws IOException {

        try {
            BufferedReader readFile = new BufferedReader(new FileReader(matrixFileName));

            String line;
            int counter = 0;

            while ((line = readFile.readLine()) != null) {

                for (int charNum = 0; charNum < line.length(); charNum++) {

                    if (line.charAt(charNum) == '1') {
                        Vertex vertexToAdd = this.vertices.get(charNum);
                        this.vertices.get(counter).addNeighbourVertex(vertexToAdd);
                    }
                }
                counter++;
            }
            readFile.close();

        } catch (FileNotFoundException noFile) {
            System.out.println("File does not exist");
            System.exit(-1);
        }
    }

    /**
     * Prints all vertexes, and vertex relations stored within this instance of Graph.
     */
    public void printAllVertex() {

        System.out.println("-------------------");
        for (int i = 0; i < this.vertices.size(); i++) {
            System.out.println("Vertex " + i + ": "
                    +
                    this.vertices.get(i).getVertexNumber());
        }
        System.out.println("-------------------");

    }

    /**
     * Returns a Vertex stored in this instance of a Vertex
     * at a given index.
     *
     * @param index an integer
     * @return the Vertex stored at the provided index
     */
    public Vertex getVertexAtIndex(final int index) {

        try {
            return this.vertices.get(index);

        } catch (IndexOutOfBoundsException error) {
            System.out.println("Enter a vertex within range.");
            System.exit(-1);
        }
        return null;
    }

    /**
     * Returns a list containing a series of vertices that form the shortest
     * path from startVertex to endVertex.
     *
     * @param startVertex a Vertex
     * @param endVertex   a Vertex
     * @return a list containing the series of vertices composing the shortest path
     * from one vertex to another
     */
    public List<Vertex> findShortestPath(final Vertex startVertex, final Vertex endVertex) {
        List<Vertex> shortestPath = new ArrayList<>();
        Vertex node = endVertex;

        while (node != startVertex) {
            shortestPath.add(node);
            for (Map.Entry<Vertex, ArrayList<Vertex>> key : this.paths.entrySet()) {
                if (key.getValue().contains(node)) {
                    node = key.getKey();
                    break;
                }
            }
        }
        shortestPath.add(node);
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    /**
     * Prints, in a nicely formatted manner, both the vertices and length
     * of the shortest path between two vertices.
     *
     * @param shortPath   a List of Vertex
     * @param startVertex a Vertex
     * @param endVertex   a Vertex
     */
    public void printShortestPath(final List<Vertex> shortPath,
                                  final Vertex startVertex, final Vertex endVertex) {


        if (shortPath.isEmpty() || shortPath.size() == 1) {
            System.out.println("No Path Found!");
        } else {
            int lengthOfPath = shortPath.size() - 1;
            String printString = String.format("The path between Vertex %d and Vertex %d "
                            +
                            "has a length of %d:",
                    startVertex.getVertexNumber(), endVertex.getVertexNumber(), lengthOfPath);

            System.out.println(printString);

            int count = 0;
            for (Vertex vertex : shortPath) {
                System.out.print(vertex);
                if (count < lengthOfPath) {
                    System.out.print("->");
                }
                count++;
            }
        }
    }

    /**
     * Performs a Breadth First Search on this instance of a Graph, adding
     * each vertex and its relational vertices to the instance variable paths.
     *
     * @param startingVertex the Vertex from which the Breadth First Search Starts
     */
    public void breadthFirstSearch(final Vertex startingVertex) {

        Queue<Vertex> queue = new ArrayDeque<>();
        startingVertex.setDiscovered(true);
        queue.add(startingVertex);

        while (!queue.isEmpty()) {

            Vertex currentVertex = queue.remove();
            ArrayList<Vertex> allNeighbours = new ArrayList<>();

            for (Vertex neighbourVertex : currentVertex.getNeighbourList()) {

                if (!neighbourVertex.isDiscovered()) {

                    allNeighbours.add(neighbourVertex);
                    neighbourVertex.setDiscovered(true);
                    queue.add(neighbourVertex);
                }
            }
            this.paths.put(currentVertex, allNeighbours);
        }
    }

    /**
     * Accesses the equality between this instance of a Graph and a passed Object.
     *
     * @param other an object
     * @return boolean true if Object is equal to this instance of a Graph
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Graph graph = (Graph) other;
        return Objects.equals(vertices, graph.vertices) && Objects.equals(paths, graph.paths);
    }

    /**
     * Returns the hashcode for this instance of the Highway Class.
     *
     * @return an integer representing a hashcode
     */
    @Override
    public int hashCode() {
        int hashResult = PRIME_NUMBER_HASH_CODE;
        hashResult = SECOND_PRIME_NUMBER + hashResult + Objects.hash(this.vertices);
        hashResult = SECOND_PRIME_NUMBER * hashResult + Objects.hash(this.paths);
        return hashResult;
    }
}
