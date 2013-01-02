package Protocols.HTTP;

import webserver.Resource;
import Servlet.ServletOutputStream;
import Servlet.http.Cookie;
import Servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;


import webserver.OutputStream;

/**
 *@Purpose Represents HTTP Response Message. This class extends HTTPMessage
 * @author Faraaz Malak
 */
public class HTTPResponse extends HTTPMessage implements HttpServletResponse {

    //Status line message code
    private int msgStatusCode = -1;
    //Response message to be included in <body></body>
    private String message = null;
    //Status Line code meaning
    private HTTPStatusCodes statusMsg = new HTTPStatusCodes();


    /*
     * Cosntructor
     * @param protocolVersion HTTP Protocol version
     * @fileName Resource URI
     * @param newConnection Socket Connection
     */
    public HTTPResponse(String protocolVersion, String fileName, Socket newConnection) {

        httpProtocol.setVersion(protocolVersion);
        requestURI = fileName;
        socketConnection = newConnection;
        initialize();
    }

    /*
     * Initialize HTTP Response Message
     */
    private void initialize() {
        body.setURI(requestURI);

        if (body.getDataReadStatus() == true) {
            setStatus(SC_OK);
        } else if(body.getDataReadStatus()==false){
            if (body.isResourceExists() == false) {
               
                sendError(SC_NOT_FOUND, "<html><body><h1>404:File Not Found</h1></body></html>");


            }
        }

    }

    /*
     * Returns HTTP Response Message's Status Line
     */
    public String getStatusLine() {

        return httpProtocol.getVersion() + " " + statusMsg.getMessage(msgStatusCode) + CRLF;

    }

    /*
     * Returns Heder Lines
     */
    public String getHeaderLines() {
        String headers = "";
        Set<String> keySet = headerLines.keySet();
        Iterator it = keySet.iterator();



        while (it.hasNext()) {
            String key = (String) it.next();

            headers += key + ":" + headerLines.get(key) + CRLF;

        }

        return headers;
    }

    /*
     * Returns byte data read from file
     */
    public byte[] getData() {
        if (body.getDataReadStatus()==true) {
            return body.getData();
        } else {
            return message.getBytes();

        }

    }

    /*
     * Sets status code of Status Line
     * @param sc status code
     */
    public void setStatus(int sc) {
        msgStatusCode = sc;
    }

    /*
     * Sets response header
     * @param name header name
     * @param value Header value
     */
    public void setHeader(String name, String value) {
        if (name != null && value != null) {

            headerLines.put(name, value);

        }
    }

    /*
     * Sets eeror message, which is sent in response body
     * @param sc status code
     * @param msg Error message
     */
    public void sendError(int sc, String msg) {
        msgStatusCode = sc;
        message = msg;

    }

    /*
     * Sets status code, to be included in status line
     * @param sc status code
     */
    public void sendError(int sc) {
        msgStatusCode = sc;

    }

    /*
     * SetsContent-Type header, based on Accept header
     * @param type Accept header indicating type of content client is expecting
     */
    public void setContentType(String type) {
        if ((requestURI.endsWith(".htm") || requestURI.endsWith(".html")) && type.contains("text/html")) {
            setHeader("Content-Type", "text/html");
        } else {
            setHeader("Content-Type", "application/octet-stream");
        }


    }

    /*
     * Returns Output stream, to which content is written
     */
    public ServletOutputStream getOutputStream() throws IOException {
        return new OutputStream(new DataOutputStream(socketConnection.getOutputStream()));
    }

    public void addCookie(Cookie cookie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsHeader(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setStatus(int sc, String sm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setIntHeader(String name, int value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDateHeader(String name, long date) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void sendRedirect(String location) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String encodeUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String encodeRedirectUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setContentLength(int len) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PrintWriter getWriter() {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    public String getCharacterEncoding() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
