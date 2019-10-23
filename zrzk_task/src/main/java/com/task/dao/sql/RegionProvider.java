package com.task.dao.sql;

import com.task.pojo.Region;
import org.apache.ibatis.jdbc.SQL;

public class RegionProvider {
    /**
     * 动态修改上限,下限
     * @param region
     * @return
     */
    public String getUpdate(Region region){
        SQL sql = new SQL(){
            {
                UPDATE("region");
                if(region.getSmall() != null){
                    SET("small=#{small}");
                }
                if(region.getBig() != null){
                    SET("big=#{big}");
                }
                WHERE("genre_id=#{genreId}");
            }
        };
        return sql.toString();
    }
}
