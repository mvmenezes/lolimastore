package br.com.storeadmin.controller;

import org.primefaces.model.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@ManagedBean(name="mainBean")
@SessionScoped
public class MainBean implements Serializable{



    private DashboardModel model;
    private ScheduleModel eventModel;
    public MainBean()
    {
        init();
    }

    @PostConstruct
    public void init()
    {

        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();

        column1.addWidget("sports");
        column1.addWidget("finance");

        column2.addWidget("lifestyle");
        column2.addWidget("weather");

        column3.addWidget("politics");

        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);

        eventModel = new DefaultScheduleModel();
        Date d = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(d);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month

        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", d, date.getTime()));


    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }

    public DashboardModel getModel() {
        return model;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }
}
