package br.com.storeadmin.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean implements Serializable{


    private String page;
    public MenuBean()
    {
        init();
    }

    @PostConstruct
    public void init()
    {
        page = "mainpage";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRegCustomer()
    {
        this.page = "/customer/customerfront";
    }
    public void setRegProduct()
    {
        this.page = "productfront";
    }
    public void setRAdress()
    {
        this.page = "/template/register/addresses";
    }
}
