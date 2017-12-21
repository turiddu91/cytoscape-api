package org.cytoscape.service.util.internal;

/*
 * #%L
 * Cytoscape Service API (service-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.cytoscape.service.util.internal.utils.ServiceUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CyServiceRegistrarImplTest {

	private interface DummyInterface {
	}
	private class DummyClass implements DummyInterface{
		
	}
	
	private interface DummyInterface2 extends DummyInterface{
		
	}

	private class DummyClass2 implements DummyInterface2{
		
	}
	CyServiceRegistrarImpl sri;
	BundleContext bc;
	@Before
	public void testCyServiceRegistrarImpl(){
		bc = mock(BundleContext.class);
		sri = new  CyServiceRegistrarImpl(bc);
	}
	
	
	@Test
	public void testRegisterAllServices (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
			
		when(bc.registerService(eq(DummyInterface2.class.getName()), same(service), any(Dictionary.class))).thenReturn(s2); 
		when(bc.registerService(eq(DummyInterface.class.getName()), same(service), any(Dictionary.class))).thenReturn(s); 
	
		sri.registerAllServices(service, props);
		assertEquals(2, sri.getServiceRegistrations().size());
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));

	}
	
	@Test
	public void testRegisterService (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		final String DUMMY_KEY = "dummyKey";
		final String DUMMY_VALUE = "dummyValue";
		props.put(DUMMY_KEY, DUMMY_VALUE);
		
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		//when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
		doAnswer(new Answer<ServiceRegistration>() {
			public ServiceRegistration answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				assertTrue(args[2] instanceof Dictionary);
				Dictionary dictionary = (Dictionary) args[2];
				assertEquals(1, dictionary.size());
				assertEquals(DUMMY_VALUE, dictionary.get(DUMMY_KEY));
				return s2;
			}
		}).when(bc).registerService(eq(DummyInterface2.class.getName()), same(service), any(Dictionary.class));
			
		
		sri.registerService(service, DummyInterface2.class, props);
		assertEquals(1, sri.getServiceRegistrations().size());
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));

	}
	
	@Test
	public void testUnRegisterService (){
		
		DummyClass service = new DummyClass();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		when(bc.registerService(eq(DummyInterface.class.getName()), same(service), any(Dictionary.class))).thenReturn(s); 
		
		sri.registerService(service, DummyInterface.class, props);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
		
		sri.unregisterService(service, DummyInterface.class);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service)== null);

	}
	
	@Test
	public void testUnRegisterAllServices (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(eq(DummyInterface2.class.getName()), same(service), any(Dictionary.class))).thenReturn(s2); 
		when(bc.registerService(eq(DummyInterface.class.getName()), same(service), any(Dictionary.class))).thenReturn(s); 
	
		
		sri.registerAllServices(service, props);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
	
		sri.unregisterAllServices(service);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).isEmpty());
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).isEmpty());
	}
}
