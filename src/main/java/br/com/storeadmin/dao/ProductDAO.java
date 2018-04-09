package br.com.storeadmin.dao;

import br.com.storeadmin.model.Store.Product;

public class ProductDAO extends GenericDAO<Product>
{
    public ProductDAO()
    {
        super(Product.class);
    }


}
