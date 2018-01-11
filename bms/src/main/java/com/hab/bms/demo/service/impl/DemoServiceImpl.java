package com.hab.bms.demo.service.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hab.bms.demo.model.Demo;
import com.hab.bms.demo.mysqlmapper.DemoMapper;
import com.hab.bms.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	private static Logger logger = LogManager.getLogger(DemoServiceImpl.class);
	@Autowired
	private DemoMapper demoMapper;

	@Override
	public Demo getDemoById(int demoId) {
		logger.debug("出入参数: " + JSON.toJSONString(demoId));
		return this.demoMapper.selectByPrimaryKey(demoId);
	}
}