package webserver;


import Servlet.*;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 *@Purpose: Represents outputStream, to ServletSocket data is written to
 * @author Faraaz Malak
 */
public class OutputStream extends ServletOutputStream {

    private DataOutputStream os = null;

    public OutputStream(DataOutputStream newOs) {
        os = newOs;

    }

    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void print(String s) {
        try {
            os.writeBytes(s);

        } catch (IOException ex) {
            System.err.println("Error occoured while writing output to socket: " + ex.getMessage());

        }
    }

    public void print(byte b) {
        try {
            os.write(b);

        } catch (IOException ex) {
            System.err.println("Error occoured while writing output to socket: " + ex.getMessage());

        }
    }

    public void flush() {
        try {
            os.flush();
        } catch (IOException ex) {
            System.err.println("IO Error occoured while writing output to socket: " + ex.getMessage());
        }
    }

    public void close() {
        try {
            os.close();
        } catch (IOException ex) {
            System.err.println("IO Error occoured while writing output to socket: " + ex.getMessage());
        }
    }
}
