package Protocols.HTTP;

import Servlet.http.Cookie;
import Servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import Servlet.*;
import Servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 *@Purpose Represents HTTP Request Message. This class extends HTTPMessage
 * and implements HttpServletRequest
 * @author Faraaz Malak
 */
public class HTTPRequest extends HTTPMessage implements HttpServletRequest {

   
   

    /*
     * Cosntructor.
     * @param newSocket Socket connection
     */
    public HTTPRequest(Socket newSocket) {
        socketConnection = newSocket;
        initialize();
    }

    /*
     * Initializes HTTP Request Message
     */
    private void initialize() {
        BufferedReader bufferedReader = null;


        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));

            //Gets Request Line
            StringTokenizer tokens = new StringTokenizer(bufferedReader.readLine());

            //Skip GET
            tokens.nextToken();

            //Gets Resource URI from Request Line
            requestURI = "." + tokens.nextToken();

            //Gets HTTP Version from Request Line
            String httpVersion = tokens.nextToken();

            //Initializes httpProtocol member to httpVersion retrieved from Request Line
            httpProtocol.setVersion(httpVersion);

            //Read the header lines
            String tempHeaderLine = null;
            while ((tempHeaderLine = bufferedReader.readLine()).length() > 0) {
                if (tempHeaderLine.length() > 0) {
                    StringTokenizer tok = new StringTokenizer(tempHeaderLine, ":");
                    headerLines.put(tok.nextToken().trim(), tok.nextToken().trim());
                   
                }
            }

        } catch (IOException ex) {
            System.err.println("Unkown io exception occoured while reading HTTP Request" + ex.getMessage() + " end of message");
        } catch (Exception e) {
            System.err.println("nkown exception occoured while reading HTTP Request: " + e);
        }
    }

    /*
     * Returns HTTP Protocol name and version
     */
    public String getProtocol() {
        return httpProtocol.getVersion();

    }

    /*
     * Returns host name on which the server is running
     * @return Host name on which server is running
     */
    public String getServerName() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "Host Address Unknown";
        }

    }

    /*
     * Returns the port number on which server is running
     */
    public int getServerPort() {


        return socketConnection.getLocalPort();
    }

    /*
     * Returns the port number used by the client (browser)
     */
    public int getClientPort() {

        return socketConnection.getPort();
    }

    /*
     * Returns the port number on which server is running
     */
    public String getRemoteAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            System.err.println("Unkown Host Exception Caught");
        }
        return "";

    }

    /*
     * Returns the host name on which the client is running
     */
    public String getRemoteHost() {
        return socketConnection.getInetAddress().getCanonicalHostName();
    }

    /*
     * Gets host name on which the server is running
     */
    public String getLocalHost() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException ex) {
            return "Unknown Host";
        }

    }

    /*
     * Returns the URI of the resource
     */
    public String getRequestURI() {
        return requestURI;
    }

    public boolean isRequestedSessionIdFromUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getDateHeader(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Enumeration getHeaderNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getContentLength() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getContentType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getScheme() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRealPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ServletInputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getParameter(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getParameterValues(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Enumeration getParameterNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getAttribute(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BufferedReader getReader() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCharacterEncoding() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Cookie[] getCookies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getMethod() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getServletPath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPathInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPathTranslated() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getQueryString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRemoteUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAuthType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public HttpSession getSession(boolean create) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRequestedSessionId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRequestedSessionIdValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRequestedSessionIdFromCookie() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getHeader(String name) {
        return headerLines.get(name);

    }

    public int getIntHeader(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
