
package com.kutuphane.akillikutuphane.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ceza_sistemi")
public class CezaSistemi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ceza_id;

    @Column(name = "ceza_miktarı")
    private Integer ceza_miktarı;

    public Integer getCeza_id() {
        return ceza_id;
    }

    public void setCeza_id(Integer ceza_id) {
        this.ceza_id = ceza_id;
    }

    public Integer getCeza_miktarı() {
        return ceza_miktarı;
    }

    public void setCeza_miktarı(Integer ceza_miktarı) {
        this.ceza_miktarı = ceza_miktarı;
    }

}
