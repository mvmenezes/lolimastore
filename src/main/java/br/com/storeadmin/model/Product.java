package br.com.storeadmin.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="PRODUCT")
@NamedQuery(name="Product.findAll", query="SELECT a FROM Product a")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="PRD_ID")
    private int id;
    @Column(name="PRD_CODE")
    private String code;
    @Column(name="PRD_DESCRIPTION")
    private String description;
    @Column(name="PRD_COST_PRICE")
    private double costPrice;
    @Column(name="PRD_DESCOUNT")
    private int descount;
    @Column(name="PRD_SALE_PRICE")
    private double salePrice;
    @Column(name="PRD_NUMBER")
    private int number;
    @Column(name="PRD_QTY")
    private int quantity;
    @Column(name="PRD_COLOR")
    private String color;
    @Column(name="PRD_SIZE")
    private double size;
    @Column(name="PRD_GROSS_WEIGHT")
    private double grossWeight;
    @Column(name="PRD_NET_WEIGHT")
    private double netWeight;
    @Column(name="PRD_TYPE")
    private ProductType type;
    @Column(name="PRD_TAG")
    private List<String> tag;
    @Column(name="PRD_IMAGE")
    private List<String> images;
    @Column(name="PRD_ADDITIONAL")
    private String additional;
    @Column(name="PRD_MIN_QTY")
    private int minquantity;
    @Column(name="PRD_COMISSION")
    private double comission;
    @Column(name="FCT_ID")
    private Factory factory;
    @Column(name="")
    private Person responsable;
    @Column(name="PRD_BAR_CODE")
    private String barcode;


    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public int getDescount() {
        return descount;
    }

    public void setDescount(int descount) {
        this.descount = descount;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public Factory getFactory() {
        return factory;
    }

    public double getComission() {
        return comission;
    }

    public int getMinquantity() {
        return minquantity;
    }

    public Person getResponsable() {
        return responsable;
    }

    public String getAdditional() {
        return additional;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setAdditional(String aditional) {
        this.additional = aditional;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    public void setMinquantity(int minquantity) {
        this.minquantity = minquantity;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public void setResponsable(Person responsable) {
        this.responsable = responsable;
    }
}
