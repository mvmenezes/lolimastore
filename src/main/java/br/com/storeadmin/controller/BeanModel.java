package br.com.storeadmin.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class BeanModel {


    protected final String MSG_ID_ADDRESS = "msgAddress";
    protected final String MSG_ID_GROW_INDEX = "GrowMessage";
    protected final String MSG_ID_CONTACT = "msgContact";
    protected final String MSG_ID_IMPORT = "messageImport";



    @PostConstruct
    public void start()
    {
        init();
        refreshStaticInformation();
        lastValidation();
    }

    public abstract void init();
    public abstract void refreshStaticInformation();
    public abstract void lastValidation();

    public void addMsgInfo(String componentID, String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msg));
    }
    public void addMsgWarn(String componentID,String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
    }

    public void addMsgError(String componentID,String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
    }

    public void addMsgFatal(String componentID,String msg) {
        FacesContext.getCurrentInstance().addMessage(componentID, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal!", msg));
    }
    public void addMsgInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msg));
    }

    public void addMsgWarn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
    }

    public void addMsgError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
    }

    public void addMsgFatal(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro Fatal!", msg));
    }


}
