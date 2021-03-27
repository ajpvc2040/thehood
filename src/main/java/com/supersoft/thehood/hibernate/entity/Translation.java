package com.supersoft.thehood.hibernate.entity;

public class Translation {

    private int translationId;
    private String translationCode;
    private String language;
    private String text;
    public int getTranslationId() {
        return translationId;
    }
    public void setTranslationId(int translationId) {
        this.translationId = translationId;
    }
    public String getTranslationCode() {
        return translationCode;
    }
    public void setTranslationCode(String translationCode) {
        this.translationCode = translationCode;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
}
