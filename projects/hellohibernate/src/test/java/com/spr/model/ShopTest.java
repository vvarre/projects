package com.spr.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spr.config.Application;
import com.spr.repo.ShopRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:test.properties")
@SpringApplicationConfiguration(classes = Application.class)
public class ShopTest {

	@Autowired
	ShopRepository shopRepo;
	
	@Test
	public void test() {
		assertTrue(Boolean.TRUE);
	}

}
