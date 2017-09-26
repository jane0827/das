package com.jane.das.model;


public class JsonModel {
    private String status;
    private Object object;

    public JsonModel() {

    }

    public JsonModel(String status, String object) {
        this.status = status;
        this.object = object;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
