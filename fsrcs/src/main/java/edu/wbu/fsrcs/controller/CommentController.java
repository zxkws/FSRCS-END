package edu.wbu.fsrcs.controller;

import edu.wbu.fsrcs.entity.Comment;
import edu.wbu.fsrcs.entity.Result;
import edu.wbu.fsrcs.entity.ResultCode;
import edu.wbu.fsrcs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fsrcs/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/insert")
    public Result insertComment(@RequestBody Comment comment){
        commentService.insertComment(comment);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/query")
    public Result queryComments(@RequestParam("remedyId") String remedyId){
        commentService.queryComments(remedyId);
        return new Result(ResultCode.SUCCESS);
    }
}
