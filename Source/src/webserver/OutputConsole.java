
package webserver;

import Protocols.HTTP.HTTPRequest;
import javax.swing.JTable;

/**
 *@Purpose Represents the GUI Table, to which output is printed
 * @author Faraaz Malak
 */
public class OutputConsole {

    private JTable outputTable = null;
    private int rowIndex = 0;

    public OutputConsole(JTable table) {
        outputTable = table;

    }

    private void clearTable() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 8; c++) {
                outputTable.setValueAt("", r, c);
            }
        }
    }

    public void printOutput(HTTPRequest httpReq) {
        if (rowIndex > 3) {
            rowIndex = 0;
            clearTable();
        }
        outputTable.setValueAt(httpReq.getRemoteHost(), rowIndex, 0);
        outputTable.setValueAt(httpReq.getRemoteAddr(), rowIndex, 1);
        outputTable.setValueAt(httpReq.getClientPort(), rowIndex, 2);

        outputTable.setValueAt(httpReq.getLocalHost(), rowIndex, 3);
        outputTable.setValueAt(httpReq.getServerName(), rowIndex, 4);
        outputTable.setValueAt(httpReq.getServerPort(), rowIndex, 5);

        outputTable.setValueAt(httpReq.getRequestURI(), rowIndex, 6);

         outputTable.setValueAt(Thread.currentThread().getName(), rowIndex, 7);

        rowIndex += 1;





    }
}
