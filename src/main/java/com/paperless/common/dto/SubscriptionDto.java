package com.paperless.common.dto;

public class SubscriptionDto {
    private long id;
    private String name;
    private boolean showInWebsite;

    private PackageTableDto packageTable;

    private Long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShowInWebsite() {
        return showInWebsite;
    }

    public void setShowInWebsite(boolean showInWebsite) {
        this.showInWebsite = showInWebsite;
    }

    public PackageTableDto getPackageTable() {
        return packageTable;
    }

    public void setPackageTable(PackageTableDto packageTable) {
        this.packageTable = packageTable;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
