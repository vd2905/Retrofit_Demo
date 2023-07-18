package Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_Class {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("userdata")
    @Expose
    private Userdata userdata;

    public Login_Class(Integer connection, Integer result, Userdata userdata) {
        this.connection = connection;
        this.result = result;
        this.userdata = userdata;
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

    public Userdata getUserdata() {
        return userdata;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }

    @Override
    public String toString() {
        return "Login_Class{" +
                "connection=" + connection +
                ", result=" + result +
                ", userdata=" + userdata +
                '}';
    }
}