package Protocols.HTTP;
import Servlet.http.HttpServletResponse;

/**
 *@Purpose Contains an Enumeration of HTTP Response codes and corresponding code explainations
 * @author Faraaz Malak
 */
public class HTTPStatusCodes {

    public enum statusCodes {

        HTTP404("Not Found", HttpServletResponse.SC_NOT_FOUND), HTTP200("OK", HttpServletResponse.SC_OK);
        private String message = null;
        private double code = 0;

        /*
         * Constructor for each enumeration member
         */
        statusCodes(String newMessage, double newCode) {
            message = newMessage;
            code = newCode;

        }

        /*
         * Retuns the message associated with the enumeration message
         */
        public String getMessage() {
            return message + " " + code;
        }
    }
    private statusCodes httpStatusCodes = null;

    /*
     * Retuns code explaination, based on status code
     * @param sc status code
     */
    public String getMessage(int sc) {
        if (sc == 200) {
            return httpStatusCodes.HTTP200.getMessage();
        } else {
            return httpStatusCodes.HTTP404.getMessage();
        }
    }
}
