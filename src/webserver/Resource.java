package webserver;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

/**
 *@Purpose Represents a resource, which is requested by the client
 * @author Faraaz Malak
 */
public class Resource {

    private File resource = null;
    private byte[] buffer = new byte[2024];
    private boolean dataRead = false;

    /*
     * Sets Resource URI and reads the resource into byte array
     */
    public void setURI(String newURI) {
        resource = new File(newURI);
        readResource();


    }


    /*
     * Returns data read from resource as an array of bytes
     */
    public byte[] getData() {


        return buffer;
    }

    /*
     * Returns true if resource exists, alse false
     */
    public boolean isResourceExists() {
        return resource.exists();
    }

    /*
     * Returns true if both resource exists and data has been successfully read from the resource.
     * else returns false
     */
    public boolean getDataReadStatus() {
        if (dataRead == true && resource.exists() == true) {
            return true;
        } else {
            return false;
        }


    }

    /*
     * Reads data from resource into a byte array
     */
    private void readResource() {

        FileInputStream fis = null;

        try {

            if (resource.exists() == true) {
                fis = new FileInputStream(resource.getPath());


                while ((fis.read(buffer)) != -1) {
                }
            }


        } catch (Exception ex) {
            System.err.println("Error occoured while reading requested file: " + ex.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }


                if (buffer.length > 0) {
                    dataRead = true;
                }

            } catch (IOException ex) {
                System.err.println("IO error coocured while clsoing output stream: " + ex.getMessage());
            }
        }
    }
}
