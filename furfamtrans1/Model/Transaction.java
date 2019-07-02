package com.amarted.furfamtrans1.Model;

public class Transaction {

    // Set properties
    private String date;
    private String amount;
    private String portion;
    private String type;
    private String paidBy;
    private String paidTo;
    private String category;
    private boolean hasDiscount;
    private String discount;
    private String user1Portion;
    private String user2Portion;
    private String user3Portion;
    private String lastUpdated;
    private String user;
    private int id;

    public Transaction (){
    }

    public Transaction (int id, String lastUpdated, String user, String date, String amount, String type, String paidBy, String paidTo, String category, boolean hasDiscount,
                        String portion, String discount, String user1Portion, String user2Portion, String user3Portion) {
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.user = user;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.category = category;
        this.hasDiscount = hasDiscount;
        this.portion = portion;
        this.discount = discount;
        this.user1Portion = user1Portion;
        this.user2Portion = user2Portion;
        this.user3Portion = user3Portion;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUser1Portion() {
        return user1Portion;
    }

    public void setUser1Portion(String user1Portion) {
        this.user1Portion = user1Portion;
    }

    public String getUser2Portion() {
        return user2Portion;
    }

    public void setUser2Portion(String user2Portion) {
        this.user2Portion = user2Portion;
    }

    public String getUser3Portion() {
        return user3Portion;
    }

    public void setUser3Portion(String user3Portion) {
        this.user3Portion = user3Portion;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
