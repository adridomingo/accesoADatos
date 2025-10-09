package Ej10.Modelo;

public class Producto implements Comparable<Producto>{
    int productID;
    String productName;
    int supplierID;
    int categoryID;
    String quantityUnit;
    Double unitPrice;
    int unitStock;
    int unitOrder;
    int reorderLevel;
    int discontinued;

    public Producto(int productID, String productName, int supplierID, int categoryID, String quantityUnit, Double unitPrice, int unitStock, int unitOrder, int reorderLevel, int discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.quantityUnit = quantityUnit;
        this.unitPrice = unitPrice;
        this.unitStock = unitStock;
        this.unitOrder = unitOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitStock() {
        return unitStock;
    }

    public void setUnitStock(int unitStock) {
        this.unitStock = unitStock;
    }

    public int getUnitOrder() {
        return unitOrder;
    }

    public void setUnitOrder(int unitOrder) {
        this.unitOrder = unitOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }

    public Producto() {
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", supplierID=" + supplierID +
                ", categoryID=" + categoryID +
                ", quantityUnit='" + quantityUnit + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitStock=" + unitStock +
                ", unitOrder=" + unitOrder +
                ", reorderLevel=" + reorderLevel +
                ", discontinued=" + discontinued +
                '}';
    }

    public int compareTo(Producto p) {
        if (this.getUnitStock() < p.getUnitStock()) {
            return -1;
        } else if (this.getUnitStock() > p.getUnitStock()) {
            return 1;
        } else {
            return 0;
        }
    }
}
