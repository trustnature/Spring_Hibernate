package xyz.tuny.jx.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import xyz.tuny.jx.dao.RzlogDAO;
import xyz.tuny.jx.model.Rzlog;
import xyz.tuny.jx.service.IRzlogService;

@Service("rzlogServiceImpl")
public class RzlogServiceImpl implements IRzlogService {
	private static final Logger logger = Logger.getLogger(RzlogServiceImpl.class);
	private RzlogDAO baseDao;
	
	@Override
	public String saveOrUpdate(String xmlStr) {
		try{
			String sqm = getSv(xmlStr,"<sqm>","</sqm>");
			String opttime = getSv(xmlStr,"<datetime>","</datetime>");
			String qysh = getSv(xmlStr,"<qysh>","</qysh>");
			String qymc = getSv(xmlStr,"<qymc>","</qymc>");
			Rzlog rzlog = new Rzlog();
			rzlog.setSqm(sqm);
			rzlog.setOperatime(opttime);
			rzlog.setQymc(qymc);
			rzlog.setQysh(qysh);
			baseDao.save(rzlog);
		}catch(Exception e){
			return "FAILED";
		}
		return "SUCCESS";
	}
	
	public RzlogDAO getBaseDao() {
		return baseDao;
	}
	
	@Resource(name = "rzlogDao")
	public void setBaseDao(RzlogDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 解析xml
	 * 
	 * @param paramXml
	 * @param label
	 * @return
	 */
	public String getSv(String paramXml, String startLabel, String endLabel) {
		int beginI = paramXml.indexOf(startLabel);
		int endI = paramXml.indexOf(endLabel);
		if (beginI < 0 || endI < 0)
			return "";
		return paramXml.substring(beginI + startLabel.length(), endI);
	}
}
