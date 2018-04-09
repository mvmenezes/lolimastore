package br.com.storeadmin.controller;


import javax.faces.event.ActionEvent;

public interface Bean {

    void save(ActionEvent e);
    void remove();
    void add();
}
