package edu.wbu.fsrcs.dao;

import edu.wbu.fsrcs.entity.Dynamic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicDao {
    int add(Dynamic dynamic);
    int delete(Dynamic dynamic);
    int update(Dynamic dynamic);
    List<Dynamic> query();
    int queryTotal();
    List<Dynamic> queryByUserId(@Param("userId") String userId, @Param("offset") int offset, @Param("pageSize") int pageSize);
    int queryTotalByUserId(@Param("userId") String userId);
}
