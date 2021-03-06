package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="RETAILER")
public class Retailer extends Person  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="RTL_ID")
    private long id;
    @Column(name="RTL_NAME")
    private String name;
    @Column(name="RTL_CPF")
    private String cpf;
    @Column(name="RTL_CNPJ")
    private String CNPJ;
    @Column(name="RTL_GENDER")
    private String gender;
    @Column(name="RTL_EMAIL")
    private String email;
    @OneToMany(mappedBy = "factory")
    private List<Contact> contacts;
    @OneToMany(mappedBy = "factory")
    private List<Address> addresses;
    @Column(name="RTL_INSTAGRAM")
    private String instagram;
    @Column(name="RTL_FACEBOOK")
    private String facebook;
    @Column(name="RTL_NOTE")
    private String note;
    @Column(name="RTL_CODE")
    private String code;
    @Column(name="RTL_CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name="RTL_MOD_DATE")
    private Date modifiedDate;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}
