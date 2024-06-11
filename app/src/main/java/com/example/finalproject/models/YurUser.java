package com.example.finalproject.models;

public class YurUser {
    private String orgName;
    private String okpo;
    private String regNum;
    private String leadName;
    private String leadPos;
    private String repName;
    private String legalAddr;
    private String actualAddr;
    private String email;
    private String inn;
    private String username;
    private String password;
    private String password2;
    private String accountNumber;

    public String getOrgName() {
        return orgName;
    }

    public YurUser(String orgName, String okpo, String regNum, String leadName, String leadPos, String repName, String legalAddr, String actualAddr, String email, String inn, String userName, String password1, String accountNumber) {
        this.orgName = orgName;
        this.okpo = okpo;
        this.regNum = regNum;
        this.leadName = leadName;
        this.leadPos = leadPos;
        this.repName = repName;
        this.legalAddr = legalAddr;
        this.actualAddr = actualAddr;
        this.email = email;
        this.inn = inn;
        this.username = userName;
        this.password = password1;
        this.password2 = password1;
        this.accountNumber = accountNumber;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadPos() {
        return leadPos;
    }

    public void setLeadPos(String leadPos) {
        this.leadPos = leadPos;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getLegalAddr() {
        return legalAddr;
    }

    public void setLegalAddr(String legalAddr) {
        this.legalAddr = legalAddr;
    }

    public String getActualAddr() {
        return actualAddr;
    }

    public void setActualAddr(String actualAddr) {
        this.actualAddr = actualAddr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getUserName() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password1) {
        this.password = password1;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
