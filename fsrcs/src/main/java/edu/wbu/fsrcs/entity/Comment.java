package edu.wbu.fsrcs.entity;

import edu.wbu.fsrcs.utils.IdWorker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String commentId;
    private String userId;
    private String comment;
    private String remedyId;
    private Date createDate;
}
