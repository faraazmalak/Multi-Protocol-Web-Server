/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import Servlet.*;

/**
 *@Purpose Represents a server, which listens for incoming connections
 * @author Faraaz Malak
 */
public class Server extends ServerSocket implements Runnable {

    private ThreadPool threadPool = new ThreadPool();
    private ConnectionPool connectionPool = new ConnectionPool();
    private int portNumber = -1;

    /*
     * Constructor for Server
     * @param newPortNumber Port number server will be listening incoming connections on
     */
    public Server(int newPortNumber) throws IOException {
        super(newPortNumber);
        portNumber = newPortNumber;

System.out.println(portNumber);

    }

    /*
     * Sets the GUI based output console, where output will be printed
     */
    public void setOutputConsole(OutputConsole newOutputConsole) {
        threadPool.setOutputConsole(newOutputConsole);
    }

    /*
     * Listens for incoming connections
     */
    public void checkIncomingConnection() {
        Socket socket = null;
        while (true) {
            try {
                socket = this.accept();

                //When a new connection arrives, add it to the socket pool
                connectionPool.setSocket(socket);
            } catch (IOException e) {
                System.err.println("Unknown exception occored while listening for incoming connections: " + e.getMessage());

            }
        }


    }

    /*
     * Starts the server. The server starts to listen for incoming connections
     */
    public void run() {

        threadPool.setConnectionPool(connectionPool);
        if (threadPool.initializePool() == true) {
            checkIncomingConnection();
        } else {
            System.err.println("Failed to initialize server");
        }

    }
}
