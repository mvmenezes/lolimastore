package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="PRODUCT_TYPE")
public class ProductType implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="PRT_ID")
    private long Id;
    @Column(name="PRT_CODE")
    private String code;
    @Column(name="PRT_DESCRIPTION")
    private String description;
    @Column(name="PRT_NUMBER_CODE")
    private String NumberCode;
    @Column(name="PRT_STANDARD")
    private String StandardType;
    @OneToMany(mappedBy = "type")
    private List<Product> products;
    @OneToOne
    @JoinColumn(name="ATC_ID")
    private AttributesClass attributeClass;


    public void setCode(String code) {
        this.code = code;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberCode(String numberCode) {
        NumberCode = numberCode;
    }

    public String getCode() {
        return code;
    }

    public long getId() {
        return Id;
    }

    public String getDescription() {
        return description;
    }

    public String getNumberCode() {
        return NumberCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStandardType() {
        return StandardType;
    }

    public void setStandardType(String standardType) {
        StandardType = standardType;
    }

    public void setAttributeClass(AttributesClass attributeClass) {
        this.attributeClass = attributeClass;
    }

    public AttributesClass getAttributeClass() {
        return attributeClass;
    }
}
