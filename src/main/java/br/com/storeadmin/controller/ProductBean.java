package br.com.storeadmin.controller;

import br.com.storeadmin.model.Product;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ProductBean {

    public Product product;

    private final String NAVIGATE_TO_HOME = "home";

    @PostConstruct
    public void init() {
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


}
