package org.sadtech.vkbot.autoresponder.entity;

import java.util.ArrayList;
import java.util.List;

public class BoxAnswer {

    private String message;
    private String keyboard;
    private Integer idRecipient;
    private Float lat;
    private Float aLong;
    private Integer stickerId;
    private List<String> insertWords;

    public BoxAnswer() {
        insertWords = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public Integer getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(Integer idRecipient) {
        this.idRecipient = idRecipient;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getaLong() {
        return aLong;
    }

    public void setaLong(Float aLong) {
        this.aLong = aLong;
    }

    public Integer getStickerId() {
        return stickerId;
    }

    public void setStickerId(Integer stickerId) {
        this.stickerId = stickerId;
    }

    public List<String> getInsertWords() {
        return insertWords;
    }

    public void setInsertWords(List<String> insertWords) {
        this.insertWords = insertWords;
    }
}
