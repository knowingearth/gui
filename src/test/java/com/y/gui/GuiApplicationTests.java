package com.y.gui;

import com.alibaba.fastjson.JSON;
import com.y.gui.dao.GuiAreaMapper;
import com.y.gui.po.GuiArea;
import com.y.gui.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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

	@Autowired
	private AsyncService asyncService;
	@Qualifier("asyncExecutor")
	@Autowired
	private Executor executor1;
	@Test
    public void testAsync() throws Exception {
		/*CompletableFuture<Long> one = asyncService.taskOne();
		CompletableFuture<String> two = asyncService.taskTwo();
		CompletableFuture<String> three = asyncService.taskThree();
		CompletableFuture.allOf(one, two, three).join();
		log.info("GuiApplicationTests.testAsync, one:{}", one.get());
		log.info("GuiApplicationTests.testAsync, one:{}", two.get());
		log.info("GuiApplicationTests.testAsync, one:{}", three.get());*/
		log.info("主线程执行开始");
		CompletableFuture<Long> taskOne = asyncService.taskOne();
		CompletableFuture<String> two = asyncService.taskTwo();
		CompletableFuture<String> three = asyncService.taskThree();
		CompletableFuture<String> three1 = asyncService.taskThree();
		CompletableFuture<String> three2 = asyncService.taskThree();
		CompletableFuture<String> three3 = asyncService.taskThree();
		CompletableFuture.allOf(taskOne, two, three, three1, three2, three3).join();


		CompletableFuture[] cfs = {
				CompletableFuture.supplyAsync(() -> asyncService.ta("参数"), executor1),
				CompletableFuture.supplyAsync(() -> asyncService.tb())
		};
		CompletableFuture.allOf(cfs).join();
		System.out.println(cfs[0].get());
		System.out.println(cfs[1].get());

		log.info("主线程执行结束");
	}

}
