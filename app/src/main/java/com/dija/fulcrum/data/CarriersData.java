package com.dija.fulcrum.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarriersData
{

    @SerializedName("insurance_carriers")
    @Expose
    private List<String> insuranceCarriers = null;

    public List<String> getInsuranceCarriers() {
        return insuranceCarriers;
    }

    public void setInsuranceCarriers(List<String> insuranceCarriers) {
        this.insuranceCarriers = insuranceCarriers;
    }

}

