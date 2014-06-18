package com.spr.model;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spr.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:application.properties")
@SpringApplicationConfiguration(classes = Application.class)
public class ShopTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
