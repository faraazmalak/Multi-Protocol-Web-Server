/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocols;

/**
 *@Purpose: Abstract Protocol class, from which specific protocols, such as HTTP inherit
 * @author Faraaz Malak
 */
abstract public class Protocol {

    private String name = null;


    /*
     * Constructor to initialize the protocol with a name
     * @newName Protocol Name
     */
    public Protocol(String newName) {
        name = newName;

    }

    /*
     * Set protocol name
     * @newName Protocol Name
     */
    protected void setName(String newName) {
        name = newName;
    }

    /*
     * Get protocol name
     * @return Protocol Name
     */
    protected String getName() {
        return name;
    }
}
