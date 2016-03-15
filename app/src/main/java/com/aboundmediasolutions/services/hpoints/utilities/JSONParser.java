package com.aboundmediasolutions.services.hpoints.utilities;

import com.aboundmediasolutions.services.hpoints.models.RewardData;
import com.aboundmediasolutions.services.hpoints.models.UserData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public static List<UserData> parseUserData(String content) {
        try {
            JSONArray array = new JSONArray(content);
            List<UserData> userDataList = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                UserData userData = new UserData();

                userData.setUserID(jsonObject.getInt("userID"));
                userData.setEmail(jsonObject.getString("email"));
                userData.setName(jsonObject.getString("name"));
                userData.setScore(jsonObject.getInt("score"));
                userData.setFlags(jsonObject.getInt("flags"));
                userData.setCreated(jsonObject.getInt("created"));

                userDataList.add(userData);
            }
            return userDataList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<RewardData> parseRewardData(String content) {
        try {
            JSONArray array = new JSONArray(content);
            List<RewardData> rewardDataList = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                RewardData rewardData = new RewardData();

                rewardData.setRewardID(jsonObject.getInt("rewardID"));
                rewardData.setRewardName(jsonObject.getString("rewardName"));
                rewardData.setRewardDetails(jsonObject.getString("rewardDetails"));
                rewardData.setRewardCost(jsonObject.getInt("rewardCost"));

                rewardDataList.add(rewardData);
            }
            return rewardDataList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}