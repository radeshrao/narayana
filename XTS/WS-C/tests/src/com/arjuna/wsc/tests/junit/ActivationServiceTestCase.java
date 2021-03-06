/*
 * JBoss, Home of Professional Open Source
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. 
 * See the copyright.txt in the distribution for a full listing 
 * of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 * 
 * (C) 2005-2006,
 * @author JBoss Inc.
 */
/*
 * Copyright (c) 2002, 2003, Arjuna Technologies Limited.
 *
 * ActivationServiceTestCase.java
 */

package com.arjuna.wsc.tests.junit;

import com.arjuna.webservices.SoapRegistry;
import com.arjuna.webservices.wscoor.CoordinationConstants;
import com.arjuna.webservices.wscoor.CoordinationContextType;
import com.arjuna.wsc.ActivationCoordinator;
import com.arjuna.wsc.InvalidCreateParametersException;
import com.arjuna.wsc.tests.TestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActivationServiceTestCase
{
    private String activationCoordinatorURI ;

    @Before
    public void setUp()
        throws Exception
    {
        final SoapRegistry soapRegistry = SoapRegistry.getRegistry() ;
        activationCoordinatorURI = soapRegistry.getServiceURI(CoordinationConstants.SERVICE_ACTIVATION_COORDINATOR) ;
    }

    @Test
    public void testKnownCoordinationType()
        throws Exception
    {
        final String messageID = "testKnownCoordinationType" ;
        final String coordinationTypeURI = TestUtil.COORDINATION_TYPE ;
        try
        {
            final CoordinationContextType coordinationContext = ActivationCoordinator.createCoordinationContext(activationCoordinatorURI, messageID, coordinationTypeURI, null, null) ;
            
            assertNotNull(coordinationContext);
            assertNotNull(coordinationContext.getCoordinationType()) ;
            assertEquals(TestUtil.COORDINATION_TYPE, coordinationContext.getCoordinationType().getValue());
            assertNotNull(coordinationContext.getRegistrationService()) ;
            assertNotNull(coordinationContext.getRegistrationService().getAddress()) ;
            assertNotNull(coordinationContext.getRegistrationService().getAddress().getValue()) ;
        }
        catch (final Throwable th)
        {
            fail("Unexpected exception: " + th);
        }
    }

    @Test
    public void testUnknownCoordinationType()
        throws Exception
    {
        final String messageID = "testUnknownCoordinationType" ;
        final String coordinationTypeURI = TestUtil.UNKNOWN_COORDINATION_TYPE ;
        try
        {
            ActivationCoordinator.createCoordinationContext(activationCoordinatorURI, messageID, coordinationTypeURI, null, null) ;
            fail("Expected exception: InvalidCreateParametersException");
        }
        catch (final InvalidCreateParametersException icpe) {} // Ignore, expected
        catch (final Throwable th)
        {
            fail("Expected exception: InvalidCreateParametersException");
        }
    }

    @After
    public void tearDown()
        throws Exception
    {
    }
}
