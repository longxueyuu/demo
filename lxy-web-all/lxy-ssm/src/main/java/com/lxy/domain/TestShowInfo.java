package com.lxy.domain;

import java.io.Serializable;

public class TestShowInfo implements Serializable {

    private String cinemaName;
    private String movieName;
    private String date;
    private String lang;
    private String dim;
    private String hallId;
    private String hallName;

    private int ticketingStatus;
    private double price;
    private int showType;
    private int type;
    private String label;
    private int ticketType;
    private String guest;
    private boolean sell = true;
    private String showTag;


    private int auditStatus;
    private double srcPurPrice;
    private double settlePrice;
    private String outCinemaId;
    private int serverFlag;
    private String adminLang;
    private String adminImax;


    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDim() {
        return dim;
    }

    public void setDim(String dim) {
        this.dim = dim;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getTicketingStatus() {
        return ticketingStatus;
    }

    public void setTicketingStatus(int ticketingStatus) {
        this.ticketingStatus = ticketingStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getTicketType() {
        return ticketType;
    }

    public void setTicketType(int ticketType) {
        this.ticketType = ticketType;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public boolean isSell() {
        return sell;
    }

    public void setSell(boolean sell) {
        this.sell = sell;
    }

    public String getShowTag() {
        return showTag;
    }

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public double getSrcPurPrice() {
        return srcPurPrice;
    }

    public void setSrcPurPrice(double srcPurPrice) {
        this.srcPurPrice = srcPurPrice;
    }

    public double getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(double settlePrice) {
        this.settlePrice = settlePrice;
    }

    public String getOutCinemaId() {
        return outCinemaId;
    }

    public void setOutCinemaId(String outCinemaId) {
        this.outCinemaId = outCinemaId;
    }

    public int getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(int serverFlag) {
        this.serverFlag = serverFlag;
    }

    public String getAdminLang() {
        return adminLang;
    }

    public void setAdminLang(String adminLang) {
        this.adminLang = adminLang;
    }

    public String getAdminImax() {
        return adminImax;
    }

    public void setAdminImax(String adminImax) {
        this.adminImax = adminImax;
    }
}
