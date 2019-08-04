package com.ljl.community.model;

/**
 * Created by Azz-ll on 2019/8/3
 */
public class User {
    private Integer id;
    private String name;
    private String token;
    private String accountId;
    private long gtmCreate;
    private long gtmModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public long getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(long gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public long getGtmModified() {
        return gtmModified;
    }

    public void setGtmModified(long gtmModified) {
        this.gtmModified = gtmModified;
    }
}
