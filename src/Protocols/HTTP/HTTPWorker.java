/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocols.HTTP;


import webserver.OutputStream;
import java.io.*;
import java.net.*;
import Servlet.http.*;

/**
 *@Purpose Handles co-ordination of HTTP Request and Response messages and writes data to socket
 * connection. This is used by Worker Thread
 * @author Faraaz Malak
 */
public class HTTPWorker extends HttpServlet {

  
    private String CRLF = "\r\n";
    private Socket socketConnection = null;
    private HTTPRequest httpReq = null;
    private HTTPResponse httpResp = null;

 
    /*
     * Sets socket Connection
     */
    public void setConnection(Socket newSocket) {
        socketConnection = newSocket;
    }

    /*
     * Processes Get Requests made by the client
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

        httpReq = (HTTPRequest) req;

        //Sets Content-Type header of HTTP Response Message
        httpResp.setContentType(httpReq.getHeader("Accept"));

        OutputStream os =null;

        //Writes data to socket connection
        try {

            os = new OutputStream(new DataOutputStream(socketConnection.getOutputStream()));
            os.print(httpResp.getStatusLine());
            //Send the content type line.
            if (httpResp.getHeaderLines() != null) {
                os.print(httpResp.getHeaderLines());
            }

            // Send a blank line to indicate the end of the header lines.
            os.print(CRLF);
            byte[] buffer = httpResp.getData();
            for (int i = 0; i < buffer.length; i++) {
                byte currentByte = buffer[i];
                os.print(currentByte);
            }
            os.flush();
        } catch (IOException ex) {
            System.err.println("Error occoured while writing data to output stream: " + ex);
        }
    

    }

    /*
     * Closes Socket Connection
     */
    public void closeConnection() {
       
        if (socketConnection != null) {
            try {
                socketConnection.close();
            } catch (IOException ex) {
                System.err.println("Error Closing socket connection: " + ex.getMessage());
            }
        }
    }

    /*
     * Returns HTTP Request Message
     */
    public HTTPRequest getHTTPRequest()
    {
        return httpReq;
    }

    /*
     * Starts processing client request and then writes data to output stream
     */
    public void startWork() {

        httpReq = new HTTPRequest(socketConnection);

        httpResp = new HTTPResponse(httpReq.getProtocol(), httpReq.getRequestURI(),socketConnection);

        doGet(httpReq, httpResp);
        httpReq.getRemoteAddr();
    }
}
