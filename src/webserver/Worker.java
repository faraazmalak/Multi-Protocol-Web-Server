/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import Protocols.HTTP.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servlet.*;
import Servlet.http.*;

/**
 *
 * @author Faraaz Malak
 */
public class Worker  implements Runnable {

    private ConnectionPool connectionPool = null;
    private ThreadPool threadPool = null;
    final static String CRLF = "\r\n";
    private Socket socket = null;
    private HTTPWorker httpWorker = new HTTPWorker();
    private OutputConsole outputConsole=null;

    public void run() {
        try {
            while (true) {
                socket = connectionPool.getConnection();
                threadPool.removeThread(Thread.currentThread());
                if (socket != null) {

                    httpWorker.setConnection(socket);
                    httpWorker.startWork();
                    httpWorker.closeConnection();
                    outputConsole.printOutput(httpWorker.getHTTPRequest());
                    threadPool.addThread(Thread.currentThread());


                }
            }
        } catch (Exception e) {
            System.err.println("Exception occored while attemting to run thread: " + e.getMessage());
            e.printStackTrace();

        }
    }
   
    public Worker(ConnectionPool newConnectionPool, ThreadPool newThreadPool,OutputConsole newOutputConsole) {
        connectionPool = newConnectionPool;
        threadPool = newThreadPool;
        outputConsole=newOutputConsole;
    }

}
