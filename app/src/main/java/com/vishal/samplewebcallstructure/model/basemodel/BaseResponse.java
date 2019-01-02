package com.vishal.samplewebcallstructure.model.basemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {


    @SerializedName("data")
    @Expose
    private T data;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;



    @SerializedName("current_page")
    @Expose
    private int current_page;


    @SerializedName("total_pages")
    @Expose
    private int total_pages;


    @SerializedName("last_sync_date")
    @Expose
    private String last_sync_date;




    public boolean getStatusAsBoolean() {
        return status.equals("1");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasStatus() {
        return this.status != null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public String getLast_sync_date() {
        return last_sync_date;
    }

    public void setLast_sync_date(String last_sync_date) {
        this.last_sync_date = last_sync_date;
    }


}
