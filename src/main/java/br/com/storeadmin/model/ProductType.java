package br.com.storeadmin.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PRODUCT_TYPE")
@NamedQuery(name="ProductType.findAll", query="SELECT a FROM ProductType a")
public class ProductType implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="PRT_ID")
    long Id;
    @Column(name="PRT_CODE")
    String code;
    @Column(name="PRT_DESCRIPTION")
    String description;
    @Column(name="PRT_NUMBER_CODE")
    String NumberCode;


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

}
