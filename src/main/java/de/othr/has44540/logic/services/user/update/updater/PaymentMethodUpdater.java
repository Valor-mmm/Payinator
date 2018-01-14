package de.othr.has44540.logic.services.user.update.updater;

import de.othr.external.services.oauth.korbinianSchmidt.data.CreditCardPaymentMethod;
import de.othr.external.services.oauth.korbinianSchmidt.data.PaymentMethod;
import de.othr.external.services.oauth.korbinianSchmidt.data.WireTransferPaymentMethod;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterCase;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterQalifier;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.paymentInformation.CreditCard;
import de.othr.has44540.persistance.entities.user.paymentInformation.WireTransfer;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@EntityUpdaterQalifier(EntityUpdaterCase.PAYMENT_INFORMATION)
public class PaymentMethodUpdater implements EntityUpdaterIF<List<PaymentMethod>, Set<AbstractPaymentMethod>> {

    private static final Logger logger = Logger.getLogger(PaymentMethodUpdater.class.getName());

    @PersistenceContext
    private EntityManager em;

    public Set<AbstractPaymentMethod> update(@NotNull List<PaymentMethod> newPaymentMethods,
                                             Set<AbstractPaymentMethod> oldPaymentMethods) throws
                                                                                           InternalErrorException {
        Set<AbstractPaymentMethod> result = new TreeSet<>();
        for (PaymentMethod newPm : newPaymentMethods) {
            // TODO: change to real getID method
            AbstractPaymentMethod matchingPm = null; // findByOauthId(newPm.getID, oldPaymentMethods);
            AbstractPaymentMethod mergedPm = null;
            if (matchingPm == null) {
                mergedPm = mergeOne(newPm, createNewPm(newPm));
            } else {
                mergedPm = mergeOne(newPm, matchingPm);
                mergedPm = em.merge(mergedPm);
            }
            em.persist(mergedPm);
            result.add(mergedPm);
        }
        return result;
    }

    public Set<AbstractPaymentMethod> merge(List<PaymentMethod> newPaymentMethods,
                                            @NotNull Set<AbstractPaymentMethod> oldPaymentMethods) throws
                                                                                                   InternalErrorException {
        if (newPaymentMethods == null) {
            return oldPaymentMethods;
        }
        Set<AbstractPaymentMethod> result = new TreeSet<>();
        for (PaymentMethod newPm : newPaymentMethods) {
            // TODO: change to real getID method
            AbstractPaymentMethod matchingPm = null; //findByOauthId(newPm.getID, oldPaymentMethods);
            AbstractPaymentMethod mergedPM = mergeOne(newPm, matchingPm == null ? createNewPm(newPm) : matchingPm);
            result.add(mergedPM);
        }
        return result;
    }

    private AbstractPaymentMethod createNewPm(@NotNull PaymentMethod newPaymentMethod) throws InternalErrorException {
        if (newPaymentMethod instanceof WireTransferPaymentMethod) {
            return new WireTransfer();
        }
        if (newPaymentMethod instanceof CreditCardPaymentMethod) {
            return new CreditCard();
        }
        logger.severe("Unknown instance of PaymentMethod from OauthService.");
        throw new InternalErrorException("Unknown instance of an Payment method.",
                                         "The Payment method from the OAuth" + " service is unknown to this method.");
    }

    private AbstractPaymentMethod mergeOne(@NotNull PaymentMethod newPaymentMethod,
                                           @NotNull AbstractPaymentMethod oldPaymentMethod) throws
                                                                                            InternalErrorException {
        oldPaymentMethod.setDefault(newPaymentMethod.isIsPrimary());
        oldPaymentMethod.setName(newPaymentMethod.getName());

        if (newPaymentMethod instanceof WireTransferPaymentMethod) {
            if (!(oldPaymentMethod instanceof WireTransfer)) {
                logger.severe("Payment methods are not of the same type. At WireTransfer");
                throw new InternalErrorException("Can not merge payment methods",
                                                 "Payment methods to merge are not of the same type (WireTransfer).");
            }
            WireTransferPaymentMethod newWirePm = (WireTransferPaymentMethod) newPaymentMethod;
            WireTransfer oldWirePm = (WireTransfer) oldPaymentMethod;

            oldWirePm.setBic(newWirePm.getBic());
            oldWirePm.setIban(newWirePm.getIban());
            oldWirePm.setAccountName(newWirePm.getAccountName());
            return oldWirePm;
        }

        if (newPaymentMethod instanceof CreditCardPaymentMethod) {
            if (!(oldPaymentMethod instanceof CreditCard)) {
                logger.severe("Payment methods are not of the same type. At CreditCard");
                throw new InternalErrorException("Can not merge payment methods",
                                                 "Payment methods to merge are not of the same type (CreditCard).");
            }

            CreditCardPaymentMethod newCreditPm = (CreditCardPaymentMethod) newPaymentMethod;
            CreditCard oldCreditPm = (CreditCard) oldPaymentMethod;

            oldCreditPm.setCardProvider(newCreditPm.getCardProvider());
            oldCreditPm.setSecurityCode(newCreditPm.getSecurityCode());
            try {
                oldCreditPm.setSecurityNumber(Long.parseLong(newCreditPm.getSecurityNumber()));
            } catch (NumberFormatException ex) {
                logger.log(Level.WARNING, "Could not parse security number", ex);
            }
            return oldCreditPm;
        }

        logger.severe("Type of Oauth Payment method is not known to this method");
        throw new InternalErrorException("Can not merge payment methods",
                                         "OAuth PaymentMethod implementation type is unknown.");
    }

    private static AbstractPaymentMethod findByOauthId(Long id, Set<AbstractPaymentMethod> pmSet) {
        if (pmSet == null || id == null) {
            return null;
        }
        for (AbstractPaymentMethod pm : pmSet) {
            if (pm.getOauthID().equals(id)) {
                return pm;
            }
        }
        return null;
    }


}
