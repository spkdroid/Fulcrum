
package com.dija.fulcrum.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prediction {

    @SerializedName("predictions")
    @Expose
    private List<Prediction_> predictions = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Prediction_> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction_> predictions) {
        this.predictions = predictions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
