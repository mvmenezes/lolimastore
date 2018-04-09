package br.com.storeadmin.model.Store;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Tag")
public class Tag  implements Serializable {


    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="TAG_ID")
    private long id;
    @Column(name="TAG_TEXT")
    private String tag;
    @ManyToOne
    @JoinColumn(name="PRD_ID")
    private Product product;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
