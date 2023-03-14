/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.context;

/**
 *
 * @author carine
 */
public class StockInsuffisantException extends Exception {

    /**
     * Creates a new instance of <code>StockInsuffisantException</code> without
     * detail message.
     */
    public StockInsuffisantException() {
    }

    /**
     * Constructs an instance of <code>StockInsuffisantException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StockInsuffisantException(String msg) {
        super(msg);
    }
}
