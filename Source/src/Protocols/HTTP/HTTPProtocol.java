package Protocols.HTTP;

import Protocols.Protocol;

/**
 *@Purpose: Represents HTTP Protocol & its version. Objects of this class are used by HTTPRequest and HTTPResponse
 * classes
 * @author Faraaz Malak
 */
public class HTTPProtocol extends Protocol {

   
    public final static String HTTP10="HTTP/1.0";
    public final static String HTTP11="HTTP/1.1";
     private String protocolVersion = HTTP11;
    /*
     * Constructor
     * @param newVersion HTTP protocol version
     */
    public HTTPProtocol(String newVersion) {
        super("HTTP");
        setVersion(newVersion);
    }

    /*
     * Overloaded Default constructor
     */
    public HTTPProtocol() {
        super("HTTP");

    }

    /*
     * Returns HTTP Protocol Version
     * @return HTTP Protocol name and version
     */
    public String getVersion() {
        return protocolVersion;
    }

    /*
     * Sets HTTP Protocol versio
     * @param HTTP Protocol version
     */
    public void setVersion(String newVersion) {

      protocolVersion=newVersion;
    }
}
