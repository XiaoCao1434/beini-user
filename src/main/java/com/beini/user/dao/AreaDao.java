package com.beini.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.beini.user.pojo.Area;

/**
 * Area数据访问层接口
 * 
 * @author lb_chen
 */
@Mapper
public interface AreaDao {
	List<Area> queryArea();

	Area queryAreaById(int areaId);

	int insertArea(Area area);

	int updateArea(Area area);

	int deleteArea(int areaId);
}
