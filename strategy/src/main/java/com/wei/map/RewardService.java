package com.wei.map;

public class RewardService {
    public void issueReward(String rewardType,Object... params){
//        Strategy strategy = StrategyContext.getStrategy(rewardType);
        Strategy strategyV2 = StrategyContext.getStrategyV2(rewardType);
//        strategy.issue(params);
        strategyV2.issue(params);
    }

    public static void main(String[] args) {
        RewardService rewardService = new RewardService();
        rewardService.issueReward("FoodV2","1");
    }
}
