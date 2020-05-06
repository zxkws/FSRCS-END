package edu.wbu.fsrcs.entity;

import edu.wbu.fsrcs.utils.IdWorker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dynamic extends Page {
    private String dynamicId;
    private String userId;
    private String dynamicTitle;
    private String dynamicContent;
    private List<Comment> comments;
    private Date createDate;
    private Date updateDate;
}
