package com.example.kpsc.model;

import android.net.Uri;

import java.io.Serializable;
import java.util.HashMap;

public class Operation implements Serializable {
    private String nameOperation;
    private String typeOfWork;
    private int wipBefore;
    private int wipAfter;
    private int id;
    private Uri pathImage;
    private int labelTypeWork;
    private int timeOfWork;
    private int timeSetup;
    private HashMap<String,String> description;
    public Operation() {
    }

    public Operation(String nameOperation, int id, Uri pathImage) {
        this.nameOperation = nameOperation;
        this.id = id;
        this.pathImage = pathImage;
    }


    public int getLabelTypeWork() {
        return labelTypeWork;
    }

    public void setLabelTypeWork(int labelTypeWork) {
        this.labelTypeWork = labelTypeWork;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public int getWipBefore() {
        return wipBefore;
    }

    public void setWipBefore(int wipBefore) {
        this.wipBefore = wipBefore;
    }

    public int getWipAfter() {
        return wipAfter;
    }

    public void setWipAfter(int wipAfter) {
        this.wipAfter = wipAfter;
    }

    public String getNameOperation() {
        return nameOperation;
    }

    public void setNameOperation(String nameOperation) {
        this.nameOperation = nameOperation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uri getPathImage() {
        return pathImage;
    }

    public void setPathImage(Uri pathImage) {
        this.pathImage = pathImage;
    }

    public int getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(int timeOfWork) {
        this.timeOfWork = timeOfWork;
    }

    public int getTimeSetup() {
        return timeSetup;
    }

    public void setTimeSetup(int timeSetup) {
        this.timeSetup = timeSetup;
    }

    public HashMap<String, String> getDescription() {
        return description;
    }

    public void setDescription(HashMap<String, String> description) {
        this.description = description;
    }
}
