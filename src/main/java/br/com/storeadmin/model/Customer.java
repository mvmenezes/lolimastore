package br.com.storeadmin.model;

import java.io.Serializable;
import java.util.Date;

public class Customer extends Person implements Serializable {

    private Date birthDate;

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
