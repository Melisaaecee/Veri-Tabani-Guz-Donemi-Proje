
package com.kutuphane.akillikutuphane.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitap")
public class CezaSistemi {

    
    @Id
    @Column(name = "ceza_miktarı")
    private int ceza_miktarı;

    public int getCeza_miktarı() {
        return ceza_miktarı;
    }

    public void setCeza_miktarı(int ceza_miktarı) {
        this.ceza_miktarı = ceza_miktarı;
    }

}
