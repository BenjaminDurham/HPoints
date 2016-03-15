package com.aboundmediasolutions.services.hpoints.models;

public class RewardData {

    private String rewardName, rewardDetails, rewardImage;
    private int rewardID, rewardCost;

    public int getRewardID() {
        return rewardID;
    }

    public void setRewardID(int rewardID) {
        this.rewardID = rewardID;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardDetails() {
        return rewardDetails;
    }

    public void setRewardDetails(String rewardDetails) {
        this.rewardDetails = rewardDetails;
    }

    public int getRewardCost() {
        return rewardCost;
    }

    public void setRewardCost(int rewardCost) {
        this.rewardCost = rewardCost;
    }

    public String getRewardImage() {
        return rewardImage;
    }

    public void setRewardImage(String rewardImage) {
        this.rewardImage = rewardImage;
    }
}
