package br.com.storeadmin.controller;

import br.com.storeadmin.dao.ProductTypeDAO;
import br.com.storeadmin.model.Product;
import br.com.storeadmin.model.ProductType;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "productController")
@SessionScoped
public class ProductBean implements Serializable {

    private Product product;
    private ProductTypeDAO dao;
    private List<ProductType> types;

    private final String NAVIGATE_TO_HOME = "index33.xhtml?faces-redirect=true";

    @PostConstruct
    public void init() {
        initProduct();
    }

    public ProductBean() {
        initProduct();
    }

    public void initProduct()
    {
        product = new Product();
        types = new ArrayList<ProductType>();
        dao = new ProductTypeDAO();
    }

    public void insertProduct()
    {
        int i = 0;
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

    public void refreshProductType()
    {
        types = dao.findAll();
    }
}
