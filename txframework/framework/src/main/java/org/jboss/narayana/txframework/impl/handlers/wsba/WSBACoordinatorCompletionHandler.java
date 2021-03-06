package org.jboss.narayana.txframework.impl.handlers.wsba;

import com.arjuna.mw.wst11.BusinessActivityManager;
import com.arjuna.mw.wst11.BusinessActivityManagerFactory;
import com.arjuna.wst.SystemException;
import com.arjuna.wst.UnknownTransactionException;
import com.arjuna.wst.WrongStateException;
import com.arjuna.wst11.BAParticipantManager;
import org.jboss.narayana.txframework.api.exception.TXFrameworkException;
import org.jboss.narayana.txframework.impl.handlers.ParticipantRegistrationException;
import java.lang.reflect.Method;
import java.util.UUID;

public class WSBACoordinatorCompletionHandler extends WSBAHandler
{
    public WSBACoordinatorCompletionHandler(Object serviceImpl, Method serviceMethod) throws TXFrameworkException
    {
        super(serviceImpl, serviceMethod);
    }

    @Override
    protected BAParticipantManager registerParticipants(Object participant, Method serviceMethod) throws ParticipantRegistrationException
    {
        try
        {
            register(new WSBAInternalParticipant()).completed();
        }
        catch (Exception e)
        {
            throw new ParticipantRegistrationException("", e);
        }

        return register(participant);
    }

    private BAParticipantManager register(Object participantObject) throws ParticipantRegistrationException
    {
        try
        {
            BAParticipantManager baParticipantManager = null;

            synchronized (participantRegistry)
            {
                BusinessActivityManager businessActivityManager = BusinessActivityManagerFactory.businessActivityManager();
                String txid = businessActivityManager.currentTransaction().toString();

                //Only create participant if there is not already a participant for this ServiceImpl and this transaction
                Class participantClass = participantObject.getClass();
                if (!participantRegistry.isRegistered(txid, participantClass))
                {

                    WSBACoordinatorCompletionParticipant coordinatorCompletionParticipant = new WSBACoordinatorCompletionParticipant(participantObject);

                    baParticipantManager = businessActivityManager.enlistForBusinessAgreementWithCoordinatorCompletion(coordinatorCompletionParticipant, participantClass.getName() + UUID.randomUUID());

                    participantRegistry.register(txid, participantClass, baParticipantManager);
                }
                else
                {
                    baParticipantManager = participantRegistry.lookupBAParticipantManager(txid, participantClass);
                }
            }

            return baParticipantManager;
        }
        catch (WrongStateException e)
        {
            throw new ParticipantRegistrationException("Transaction was not in a state in which participants can be registered", e);
        }
        catch (UnknownTransactionException e)
        {
            throw new ParticipantRegistrationException("Can't register a participant as the transaction in unknown", e);
        }
        catch (SystemException e)
        {
            throw new ParticipantRegistrationException("A SystemException occurred when attempting to register a participant", e);
        }
    }
}
