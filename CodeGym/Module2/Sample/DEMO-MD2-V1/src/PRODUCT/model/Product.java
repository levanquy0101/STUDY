package PRODUCT.model;

public class Product extends ProductParent{

    private String status;
    private int typeCode;
    private String about;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Product() {
    }

    public Product(int id, String name, String status, int typeCode, String about) {
        super(id, name);
        this.status = status;
        this.typeCode = typeCode;
        this.about = about;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", status='" + status + '\'' +
                ", typeCode=" + typeCode +
                ", about='" + about + '\'' +
                '}';
    }
}
