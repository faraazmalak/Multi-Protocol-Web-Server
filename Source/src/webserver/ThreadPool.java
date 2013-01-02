/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.util.ArrayList;

/**
 *@Purpose: Represents a Pool of worker threads, which are assigned a socket connection, as soon as
 * one is recieved
 * @author Faraaz Malak
 */
public class ThreadPool {

    private ArrayList<Thread> idleThreadPool = new ArrayList<Thread>();
    private int totalThreads = 5;
    private ConnectionPool connectionPool = null;
    private OutputConsole outputConsole = null;

    /*
     * Default Cosntructor
     */
    public ThreadPool(){}

    /*
     * Overloaded constructor
     * @param theTotalThreads Maximum number of threads the pool can have
     */
    public ThreadPool(int theTotalThreads) {
        totalThreads = theTotalThreads;

    }


    /*
     * Sets GUI based output console, to which output will be printed
     */
    public void setOutputConsole(OutputConsole newOutputConsole) {
        outputConsole = newOutputConsole;
    }

    /*
     * Adds the thread back to the pool of idel threads, after it has finished serving the client
     * @param t Thread to be added to the pool
     */
    synchronized public boolean addThread(Thread t) {
        long id = t.getId();
        for (int i = 0; i < idleThreadPool.size(); i++) {
            if (idleThreadPool.get(i).getId() == id) {
                return false;
            }
        }
        idleThreadPool.add(t);
       
        return true;
    }

     /*
     * Removes thread from the pool of idel threads, as soon as it is assigned a socket connection
     * @param t Thread to be removed
     */
    synchronized public boolean removeThread(Thread t) {
        long id = t.getId();
        for (int i = 0; i < idleThreadPool.size(); i++) {
            if (idleThreadPool.get(i).getId() == id) {
                idleThreadPool.remove(i);
             
                return true;
            }
        }

        return false;
    }

    /*
     * Sets connectionPool
     */
    public void setConnectionPool(ConnectionPool thePool) {
        connectionPool = thePool;
    }

    /*
     * Initializes pool of idle threads, as per the value of totalThreads variable
     */
    public boolean initializePool() {
        if (connectionPool == null) {
            return false;
        } else {
            for (int i = 0; i < totalThreads; i++) {

                Worker workThread = new Worker(connectionPool, this, outputConsole);
                Thread t = new Thread(workThread, "WorkThread " + (i+1));

                t.start();
                idleThreadPool.add(t);
            }

            if (idleThreadPool.size() == totalThreads) {
                return true;
            } else {
                return false;
            }
        }

    }
}
