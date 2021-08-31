package com.arghudata.test;

/**
 * Created by Binaya Gurung on 17/03/2021.
 */

public class ContactsSQLite {

    private String nameeng;
    private String namenep;
    private String oldaddresseng;
    private String oldaddressnep;
    private String currentaddresseng;
    private String currentaddressnep;
    private String vetieng;
    private String vetinep;
    private String syaieng;
    private String syainep;
    private String reference;


    ContactsSQLite(String nameeng, String namenep, String oldaddresseng, String oldaddressnep, String currentaddresseng, String currentaddressnep, String vetieng, String vetinep, String syaieng, String syainep, String reference) {
        this.nameeng = nameeng;
        this.namenep = namenep;
        this.oldaddresseng = oldaddresseng;
        this.oldaddressnep = oldaddressnep;
        this.currentaddresseng = currentaddresseng;
        this.currentaddressnep = currentaddressnep;
        this.vetieng = vetieng;
        this.vetinep = vetinep;
        this.syaieng = syaieng;
        this.syainep = syainep;
        this.reference = reference;
    }


    String getNameeng() {
        return nameeng;
    }
    public void setNameeng(String nameeng) {
        this.nameeng = nameeng;
    }

    public String getNamenep() {
        return namenep;
    }
    public void setNamenep(String namenep) {
        this.namenep = namenep;
    }

    String getOldaddresseng() {
        return oldaddresseng;
    }
    public void setOldaddresseng(String oldaddresseng) {
        this.oldaddresseng = oldaddresseng;
    }

    String getOldaddressnep() {
        return oldaddressnep;
    }
    public void setOldaddressnep(String oldaddressnep) {
        this.oldaddressnep = oldaddressnep;
    }

    String getCurrentaddresseng() {
        return currentaddresseng;
    }
    public void setCurrentaddresseng(String currentaddresseng) {
        this.currentaddresseng = currentaddresseng;
    }

    String getCurrentaddressnep() {
        return currentaddressnep;
    }
    public void setCurrentaddressnep(String currentaddressnep) {
        this.currentaddressnep = currentaddressnep;
    }

    String getVetieng() {
        return vetieng;
    }
    public void setVetieng(String vetieng) {
        this.vetieng = vetieng;
    }

    String getVetinep() {
        return vetinep;
    }
    public void setVetinep(String vetinep) {
        this.vetinep = vetinep;
    }

    String getSyaieng() {
        return syaieng;
    }
    public void setSyaieng(String syaieng) {
        this.syaieng = syaieng;
    }

    String getSyainep() {
        return syainep;
    }
    public void setSyainep(String syainep) {
        this.syainep = syainep;
    }

    String getReference() {
        return reference;
    }
    public void setReferenct(String reference) {
        this.reference = reference;
    }

}