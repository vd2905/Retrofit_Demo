package Models;

import java.util.List;


public class View_Product_Class {

    private Integer connection;
    private Integer result;
    private List<ProductData> productdata;

    public View_Product_Class(Integer connection, Integer result, List<ProductData> productdata) {
        this.connection = connection;
        this.result = result;
        this.productdata = productdata;
    }

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<ProductData> getProductdata() {
        return productdata;
    }

    public void setProductdata(List<ProductData> productdata) {
        this.productdata = productdata;
    }

    @Override
    public String toString() {
        return "View_Product_Class{" +
                "connection=" + connection +
                ", result=" + result +
                ", productdata=" + productdata +
                '}';
    }
}
