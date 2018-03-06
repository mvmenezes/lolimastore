package br.com.storeadmin.controller;

import br.com.storeadmin.model.Product;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "productController")
@ViewScoped
public class ProductBean implements Serializable {

    private Product product;

    private final String NAVIGATE_TO_HOME = "index?faces-redirect=true";

    @PostConstruct
    public void init() {
        initProduct();
    }

    public ProductBean() {
        initProduct();
    }

    public void initProduct() {
        product = new Product();
    }

    public String insertProduct() {
        return NAVIGATE_TO_HOME;
    }

    public String importProduct() {
        return NAVIGATE_TO_HOME;
    }

    public String exportProduct() {
        return NAVIGATE_TO_HOME;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
