package edu.wbu.fsrcs.dao;

import edu.wbu.fsrcs.entity.Reward;

public interface RewardDao {
    Reward query(Reward reward);
    int add(Reward reward);
}
