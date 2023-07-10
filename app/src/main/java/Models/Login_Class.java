
package Models;


public class Login_Class {

    private Integer connection;
    private Integer result;
    private UserData userdata;

    public Login_Class(Integer connection, Integer result, UserData userdata) {
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

    public UserData getUserdata() {
        return userdata;
    }

    public void setUserdata(UserData userdata) {
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