/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocols.HTTP;

import java.net.Socket;
import webserver.Resource;
import java.util.HashMap;

/**
 *@Purpose Abstract Class to represent HTTPMessage. HTTPRequest and HTTPResponse classes extend this class
 * @author Faraaz Malak
 */
abstract public class HTTPMessage {

    protected HTTPProtocol httpProtocol = new HTTPProtocol();
    protected HashMap<String, String> headerLines = new HashMap<String, String>();

    protected Resource body = new Resource();
    protected String requestURI = null;
    
    protected String CRLF = "\r\n";
    protected Socket socketConnection = null;

    /*
     * Class constructor
     */
    public HTTPMessage(HTTPProtocol newProtocol) {
        httpProtocol = newProtocol;

    }

    /*
     * Overloaded constructor
     */
    public HTTPMessage() {
    }

   
}
