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
 * Copyright (C) 2002,
 *
 * Arjuna Technologies Limited,
 * Newcastle upon Tyne,
 * Tyne and Wear,
 * UK.
 *
 * $Id: SingleParticipant.java,v 1.7.8.1 2005/11/22 10:36:12 kconner Exp $
 */

package com.arjuna.wst11.tests.arq.basic;

import javax.inject.Named;

import com.arjuna.mw.wst11.TransactionManager;
import com.arjuna.mw.wst11.UserTransaction;
import com.arjuna.wst.tests.common.DemoDurableParticipant;

/**
 * @author Mark Little (mark.little@arjuna.com)
 * @version $Id: SingleParticipant.java,v 1.7.8.1 2005/11/22 10:36:12 kconner Exp $
 * @since 1.0.
 */

@Named
public class SingleParticipant
{
    public void testSingleParticipant()
            throws Exception
    {
	    UserTransaction ut = UserTransaction.getUserTransaction();
	    TransactionManager tm = TransactionManager.getTransactionManager();
	    DemoDurableParticipant p = new DemoDurableParticipant();

	    ut.begin();
	    try {
	    tm.enlistForDurableTwoPhase(p, p.identifier());
        }  catch (Exception eouter) {
            try {
                ut.rollback();
            } catch(Exception einner) {
            }
            throw eouter;
        }

	    ut.commit();
    }
}