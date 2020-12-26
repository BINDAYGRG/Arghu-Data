package com.arghudata.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Binaya Gurung on 17/03/18.
 */

public class Contact {

    @SerializedName("id_arghu") private int Id;
    @SerializedName("namenep") private String Namenep;
    @SerializedName("nameeng") private String Nameeng;
    @SerializedName("oldaddresseng") private String Oldaddresseng;
    @SerializedName("oldaddressnep") private String Oldaddressnep;
    @SerializedName("currentaddresseng") private String Currentaddresseng;
    @SerializedName("currentaddressnep") private String Currentaddressnep;
    @SerializedName("vetieng") private String Vetieng;
    @SerializedName("vetinep") private String Vetinep;
    @SerializedName("syaieng") private String Syaieng;
    @SerializedName("syainep") private String Syainep;
    @SerializedName("reference") private String Reference;

    public int getId() {
        return Id;
    }

    public String getNamenep() {
        return Namenep;
    }

    public String getOldaddressnep() {
        return Oldaddressnep;
    }

    public String getNameeng() {
        return Nameeng;
    }

    public String getOldaddresseng() {
        return Oldaddresseng;
    }

    public String getCurrentaddresseng() {
        return Currentaddresseng;
    }

    public String getCurrentaddressnep() {
        return Currentaddressnep;
    }

    public String getVetieng() {
        return Vetieng;
    }

    public String getVetinep() {
        return Vetinep;
    }

    public String getSyaieng() {
        return Syaieng;
    }

    public String getSyainep() {
        return Syainep;
    }

    public String getReference() {
        return Reference;
    }

}
