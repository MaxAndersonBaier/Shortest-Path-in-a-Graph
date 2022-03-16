package ca.bcit.comp2522.labs.lab07;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A class representing a Vertex within a graph.
 *
 * @author Maximilian Anderson-Baier
 * @version 2021
 */
public class Vertex {

    private static final int PRIME_NUMBER_HASH_CODE = 31;
    private static final int SECOND_PRIME_NUMBER = 17;
    private final int vertexNumber;
    private final ArrayList<Vertex> neighbourList;
    private boolean discovered;

    /**
     * Constructs an instance of a Vertex object.
     *
     * @param number an integer representing and ID number
     */
    public Vertex(final int number) {
        this.vertexNumber = number;
        this.neighbourList = new ArrayList<>();
        this.discovered = false;
    }

    /**
     * Adds a neighbouring, connected vertex to this instance's NeighbourList.
     *
     * @param neighbour Vertex
     */
    public void addNeighbourVertex(final Vertex neighbour) {
        this.neighbourList.add(neighbour);
    }

    /**
     * Return this Vertex's identification number.
     *
     * @return this vertex's identification number as an integer
     */
    public int getVertexNumber() {
        return vertexNumber;
    }

    /**
     * Returns a boolean value stored within discovered for this instance of Vertex.
     *
     * @return a boolean representing whether a vertex has been discovered
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     * Sets a new boolean value for discovered.
     *
     * @param discovered a boolean
     */
    public void setDiscovered(final boolean discovered) {
        this.discovered = discovered;
    }

    /**
     * Return neighbourList stored in this instance of Vertex.
     *
     * @return an ArrayList of Vertex contained in neighbourList
     */
    public ArrayList<Vertex> getNeighbourList() {
        return neighbourList;
    }

    /**
     * Returns a String representation of this Vertex.
     *
     * @return a string representation of this vertex
     */
    @Override
    public String toString() {
        return "Vertex "
                +
                vertexNumber;
    }

    /**
     * Accesses the equality between this instance of a Vertex and a passed Object.
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
        Vertex vertex = (Vertex) other;
        return vertexNumber == vertex.vertexNumber
                &&
                discovered == vertex.discovered
                &&
                Objects.equals(neighbourList, vertex.neighbourList);
    }

    /**
     * Returns the hashcode for this instance of Vertex.
     *
     * @return an integer representing a hashcode
     */
    @Override
    public int hashCode() {
        int hashResult = PRIME_NUMBER_HASH_CODE;
        hashResult = SECOND_PRIME_NUMBER + hashResult + Objects.hash(this.vertexNumber);
        return hashResult;
    }
}
