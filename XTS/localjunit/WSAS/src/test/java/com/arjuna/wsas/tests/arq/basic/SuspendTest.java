package com.arjuna.wsas.tests.arq.basic;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arjuna.wsas.tests.WSASTestUtils;
import com.arjuna.wsas.tests.arq.WarDeployment;

@RunWith(Arquillian.class)
public class SuspendTest {
	@Inject
	Suspend test;
	
	@Deployment
	public static WebArchive createDeployment() {
		return WarDeployment.getDeployment(
				Suspend.class,
				WSASTestUtils.class);
	}
	
	@Test
	public void test() throws Exception {
		test.testSuspend();
	}
}
