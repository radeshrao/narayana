package org.jboss.narayana.txframework.impl.as;

import org.jboss.narayana.txframework.api.management.DataControl;
import org.jboss.narayana.txframework.impl.DataControlImpl;
import org.jboss.narayana.txframework.impl.ServiceRequestInterceptor;
import org.jboss.narayana.txframework.impl.handlers.wsat.ATTxControlImpl;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;


/**
 * @author paul.robinson@redhat.com, 2012-02-13
 */
public class TXFrameworkCDIExtension implements Extension {

    /**
     * Register all admin CDI beans.
     *
     * @param bbd the bbd event
     * @param bm  the bean manager
     */
    public void register(@Observes BeforeBeanDiscovery bbd, BeanManager bm) {
        final AnnotatedType<DataControlImpl> dataControlImpl = bm.createAnnotatedType(DataControlImpl.class);
        bbd.addAnnotatedType(dataControlImpl);

        final AnnotatedType<DataControl> dataControl = bm.createAnnotatedType(DataControl.class);
        bbd.addAnnotatedType(dataControl);

        final AnnotatedType<ATTxControlImpl> atTxControlImpl = bm.createAnnotatedType(ATTxControlImpl.class);
        bbd.addAnnotatedType(atTxControlImpl);

        final AnnotatedType<ServiceRequestInterceptor> serviceRequestInterceptor = bm.createAnnotatedType(ServiceRequestInterceptor.class);
        bbd.addAnnotatedType(serviceRequestInterceptor);

    }
}
