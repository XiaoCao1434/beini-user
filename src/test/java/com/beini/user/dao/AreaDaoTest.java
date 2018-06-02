package com.beini.user.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beini.user.pojo.Area;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
	@Autowired
	private AreaDao areaDao;
	@Test
	public void queryAreaTest() {
		List<Area> list = areaDao.queryArea();
		assertEquals(2,list.size());
	}

	@Test
	public void queryAreaByIdTest() {
		Area area = areaDao.queryAreaById(1);
		assertEquals("福建",area.getAreaName());
	}
	@Transactional
	@Test
	public void insertAreaTest() {
		Area area = new Area();
		area.setAreaName("三明");
		int result = areaDao.insertArea(area);
		assertEquals(1,result);
	}
	@Transactional
	@Test
	public void updateAreaTest() {
		Area area = new Area();
		area.setAreaId(2);
		area.setAreaName("大田");
		int result = areaDao.updateArea(area);
		assertEquals(1,result);
	}
	@Transactional
	@Test
	public void deleteAreaTest() {
		int result = areaDao.deleteArea(2);
		assertEquals(1,result);
	}
}
