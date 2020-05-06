package edu.wbu.fsrcs.service;

import edu.wbu.fsrcs.dao.CommentDao;
import edu.wbu.fsrcs.dao.DynamicDao;
import edu.wbu.fsrcs.dao.UserDao;
import edu.wbu.fsrcs.entity.Comment;
import edu.wbu.fsrcs.entity.Dynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DynamicService {
    @Autowired
    DynamicDao dynamicDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;

    public void addDynamic(Dynamic dynamic){
        dynamic.setCreateDate(new Date());
        dynamicDao.add(dynamic);
    }
    public Map<String, Object> queryDynamic(){
        int total = dynamicDao.queryTotal();
        List<Dynamic> list = dynamicDao.query();
        for (Dynamic dynamic:list){
            String dynamicId = dynamic.getDynamicId();
            List<Comment> comments = commentDao.queryById(dynamicId);
            for (Comment c: comments) {
                String userId = c.getUserId();
                c.setUserId(userDao.queryUserById(userId).getNickName());
            }
            dynamic.setComments(comments);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("total", total);
        map.put("list", list);
        return map;
    }
    public Map<String, Object> queryDynamicByUserId(Dynamic dynamic){
        String userId = dynamic.getUserId();
        int offset = dynamic.getOffset();
        int pageSize = dynamic.getPageSize();
        int total = dynamicDao.queryTotalByUserId(userId);
        List<Dynamic> list =  dynamicDao.queryByUserId(userId, offset, pageSize);
        for (Dynamic dy:list){
            String dynamicId = dy.getDynamicId();
            List<Comment> comments = commentDao.queryById(dynamicId);
            dy.setComments(comments);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("total", total);
        map.put("list", list);
        return map;
    }
    public void updateDynamic(Dynamic dynamic){
        dynamic.setUpdateDate(new Date());
        dynamicDao.update(dynamic);
    }
    public void deleteDynamic(Dynamic dynamic){
        dynamicDao.delete(dynamic);
    }
}
