package com.prapt.prapt.pogo;

public class DamageSetGetview {
    private String damageId;
    private String damageName;
    private String damageType;
    private String damageRaisedQty;
    private String damageApprovedQty;
    private int image;
    private String status;
    public DamageSetGetview(String damageId,String damageName,
                        String damageType,String damageRaisedQty,String damageApprovedQty,int image,String status){
        this.damageId=damageId;
        this.damageName=damageName;
        this.damageType=damageType;
        this.damageRaisedQty=damageRaisedQty;
        this.damageApprovedQty=damageApprovedQty;
        this.image=image;
        this.status=status;
    }

    public int getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDamageRaisedQty() {
        return damageRaisedQty;
    }

    public void setDamageRaisedQty(String damageRaisedQty) {
        this.damageRaisedQty = damageRaisedQty;
    }

    public String getDamageApprovedQty() {
        return damageApprovedQty;
    }

    public void setDamageApprovedQty(String damageApprovedQty) {
        this.damageApprovedQty = damageApprovedQty;
    }
}
