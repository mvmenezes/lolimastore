package br.com.storeadmin.controller;


import br.com.storeadmin.dao.CustomerDAO;
import br.com.storeadmin.dao.GenericDAO;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

@ManagedBean(eager = true)
@ApplicationScoped
public class BeanApplication extends BeanModel implements Bean{

    public  BeanApplication()
    {

    }



    @Override
    public void save(ActionEvent e) {

    }

    @Override
    public void remove() {

    }

    @Override
    public void add() {

    }

    @Override
    public void init() {
        CustomerDAO dao = new CustomerDAO();
    }

    @Override
    public void refreshStaticInformation() {

    }

    @Override
    public void lastValidation() {

    }
}
