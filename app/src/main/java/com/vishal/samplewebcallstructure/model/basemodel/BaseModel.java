package com.vishal.samplewebcallstructure.model.basemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseModel<T> {
    @SerializedName("request")
    @Expose
    private String requestName;

    @SerializedName("para")
    @Expose
    private T model;

    public BaseModel(String requestName, T model) {
        this.requestName = requestName;
        this.model = model;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
