package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId , int offset , int limit);
    //需要考虑分页，offset是每一页起始行的行号，limit是每一页最多显示的数据


    //@Param指如果参数名字过长，可以取一个别名
    //如果只有一个参数，并且在<if>里使用，这必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);




}
