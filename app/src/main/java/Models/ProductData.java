
package Models;

public class ProductData {
    private int id;
    private int sellerid;
    private String name;
    private String stock;
    private String price;
    private String category;
    private String image;

    public ProductData(int id, int sellerid, String name, String stock, String price, String category, String image) {
        this.id = id;
        this.sellerid = sellerid;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "id=" + id +
                ", sellerid=" + sellerid +
                ", name='" + name + '\'' +
                ", stock='" + stock + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}