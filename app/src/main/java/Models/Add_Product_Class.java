
package Models;


public class Add_Product_Class {
    private Integer connection;
    private Integer productaddd;

    public Add_Product_Class(Integer connection, Integer productaddd) {
        this.connection = connection;
        this.productaddd = productaddd;
    }

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getProductaddd() {
        return productaddd;
    }

    public void setProductaddd(Integer productaddd) {
        this.productaddd = productaddd;
    }

    @Override
    public String toString() {
        return "Add_Product_Class{" +
                "connection=" + connection +
                ", productaddd=" + productaddd +
                '}';
    }
}