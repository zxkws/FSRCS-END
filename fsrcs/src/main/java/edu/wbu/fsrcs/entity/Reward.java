package edu.wbu.fsrcs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    private String dynamicId;
    private String userId;
    private Date rewardDate;
    private Date rewardAmount;
}
