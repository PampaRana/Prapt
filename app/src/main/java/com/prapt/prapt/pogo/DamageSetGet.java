package com.prapt.prapt.pogo;

public class DamageSetGet {
    private String damageId;
    private String damageName;
    private String damageItem;
    private String damageRequestAmount;
    private String damageDate;
    private String statues;
    public DamageSetGet(String damageId,String damageName,
                        String damageItem,String damageRequestAmount,String damageDate,String statues){
        this.damageId=damageId;
        this.damageName=damageName;
        this.damageItem=damageItem;
        this.damageRequestAmount=damageRequestAmount;
        this.damageDate=damageDate;
        this.statues=statues;
    }

    public String getDamageId() {
        return damageId;
    }

    public void setDamageId(String damageId) {
        this.damageId = damageId;
    }

    public String getDamageName() {
        return damageName;
    }

    public void setDamageName(String damageName) {
        this.damageName = damageName;
    }

    public String getDamageItem() {
        return damageItem;
    }

    public void setDamageItem(String damageItem) {
        this.damageItem = damageItem;
    }

    public String getDamageRequestAmount() {
        return damageRequestAmount;
    }

    public void setDamageRequestAmount(String damageRequestAmount) {
        this.damageRequestAmount = damageRequestAmount;
    }

    public String getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(String damageDate) {
        this.damageDate = damageDate;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }
}
