package com.sx.map.service;

import java.util.List;

import com.sx.map.dao.MapDao;
import com.sx.map.po.Point;

/**
 * @author yk
 * Created on 2015-9-17 обнГ03:31:46
 */
public class MapService {
	MapDao md=new MapDao();
	public List<Point> getPointInfo() throws Exception{
		List<Point> pointList=md.getPointInfo();
		return pointList;
	}

}
