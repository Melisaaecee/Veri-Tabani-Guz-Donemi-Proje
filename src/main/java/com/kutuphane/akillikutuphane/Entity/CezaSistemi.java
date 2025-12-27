
package com.kutuphane.akillikutuphane.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ceza_sistemi")
public class CezaSistemi {
    @Id
    @Column(name = "ödünc_id")
    private Integer oduncId;

    @OneToOne
    @MapsId //ödünç_id yi kopyalar ,id'nin ödünç alanından gelmesini sağlar
    @JoinColumn(name = "ödünc_id")
    private OduncAlmaSistemi odunc;

    @Column(name = "ceza_miktarı")
    private Integer cezaMiktari;

    public Integer getCezaMiktari() {
        return cezaMiktari;
    }

    public void setCezaMiktari(Integer cezaMiktari) {
        this.cezaMiktari = cezaMiktari;
    }

    public OduncAlmaSistemi getOdunc() {
        return odunc;
    }

    public void setOdunc(OduncAlmaSistemi odunc) {
        this.odunc = odunc;
    }

    public Integer getOduncId() {
        return oduncId;
    }

    public void setOduncId(Integer oduncId) {
        this.oduncId = oduncId;
    }

}
