package com.y.gui;

import com.alibaba.fastjson.JSON;
import com.y.gui.dao.GuiAreaMapper;
import com.y.gui.po.GuiArea;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class GuiApplicationTests {

	@Autowired
	private GuiAreaMapper guiAreaMapper;
	@Test
	@Rollback
	public void testArea() {
		GuiArea guiArea = new GuiArea();
		guiArea.setCode(1101001L);
		guiArea.setName("北京市");
		guiArea.setShortName("北京");
		guiArea.setLevel(1);
		guiArea.setCreateTime(LocalDateTime.now());
		int i = guiAreaMapper.insertSelective(guiArea);
		log.info("GuiApplicationTests.testArea, i:{}", i);
		GuiArea area = guiAreaMapper.selectByPrimaryKey(1L);
		log.info("GuiApplicationTests.testArea, area:{}", JSON.toJSONString(area));
		Assert.assertEquals("北京", area.getName());
	}

	@Test
    public void contextLoads() {
	}

}
