package Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register_Class {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("result")
    @Expose
    private Integer result;

    public Register_Class(Integer connection, Integer result) {
        this.connection = connection;
        this.result = result;
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

    @Override
    public String toString() {
        return "Register_Class{" +
                "connection=" + connection +
                ", result=" + result +
                '}';
    }
}