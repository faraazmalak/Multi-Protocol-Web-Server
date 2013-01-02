package webserver;

import java.net.*;
import java.util.*;

/**
 *@Purpose A class, which represents a pool of socket connections. Threads pick up socket connections from
 * this pool
 * @author Faraaz Malak
 */
public class ConnectionPool {

    private ArrayList<Socket> socketPool = new ArrayList<Socket>();
    final private int MAXSOCKETS = 5;

    /*
     * Returns true, ii socket pool size is greater than MAXSOCKETS. Else, false is returned
     */
    public boolean isLimitReached() {
        if (socketPool.size() >= MAXSOCKETS) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * This method is called by all threads. As soon as a new socket is available in the pool,
     * one of the threads is notified and assigned a socket connection
     */
    synchronized public Socket getConnection() {

        while (true) {

            if (socketPool.size() == 0) {
                try {
                    wait();

                } catch (InterruptedException ex) {
                    System.err.println("Thread interrupted exception occoured: " + ex.getMessage());

                }

            }
            Socket returnSocket = socketPool.remove(0);
            return returnSocket;
        }


    }

    /*
     * Adds a new socket to the socketPool
     */
    synchronized public void setSocket(Socket newSocket) {

        socketPool.add(newSocket);
        notify();
    }
}
