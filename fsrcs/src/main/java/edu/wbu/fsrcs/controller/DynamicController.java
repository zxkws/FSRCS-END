package edu.wbu.fsrcs.controller;

import edu.wbu.fsrcs.entity.Dynamic;
import edu.wbu.fsrcs.entity.Result;
import edu.wbu.fsrcs.entity.ResultCode;
import edu.wbu.fsrcs.entity.User;
import edu.wbu.fsrcs.service.DynamicService;
import edu.wbu.fsrcs.service.UserService;
import edu.wbu.fsrcs.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/fsrcs/dynamic")
public class DynamicController {

    public static final String MEDICINE = "医师";
    @Autowired
    DynamicService dynamicService;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Result create(@RequestBody Dynamic dynamic){
        dynamic.setDynamicId((new IdWorker()).nextId());
        String userId = dynamic.getUserId();
        User user = userService.queryUserById(userId);
        if (MEDICINE.equals(user.getUserFlag())) {
            dynamic.setDynamicTitle(dynamic.getDynamicTitle() + "(医用小偏方)");
        } else {
            dynamic.setDynamicTitle(dynamic.getDynamicTitle() + "(非医用小偏方)");
        }
        dynamicService.addDynamic(dynamic);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Dynamic dynamic){
        dynamicService.updateDynamic(dynamic);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Dynamic dynamic){
        dynamicService.deleteDynamic(dynamic);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/query")
    public Result query(){
        Map<String,Object> dynamics = dynamicService.queryDynamic();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(dynamics);
        return result;
    }

    @PostMapping("/select")
    public Result select(@RequestBody Dynamic dynamic){
         Map<String,Object> dynamics = dynamicService.queryDynamicByUserId(dynamic);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(dynamics);
        return result;
    }


}
