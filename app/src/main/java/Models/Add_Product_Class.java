
package Models;


public class Add_Product_Class {
    private Integer connection;
    private Integer productadd;

    public Add_Product_Class(Integer connection, Integer productadd) {
        this.connection = connection;
        this.productadd = productadd;
    }

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getProductadd() {
        return productadd;
    }

    public void setProductadd(Integer productadd) {
        this.productadd = productadd;
    }

    @Override
    public String toString() {
        return "Add_Product_Class{" +
                "connection=" + connection +
                ", productadd=" + productadd +
                '}';
    }
}