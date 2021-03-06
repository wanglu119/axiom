package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Subject;

/**
 * Concrete class that builds a SAML 2.0 assertion with the UserId
 * (Salesforce username or Federation Id) in an the NameIdentifier
 * in the Subject element.
 *
 * @author rbrainard
 */
public class Saml2AssertionUserIdInSubjectFactory extends Saml2AbstractAssertionFactory {

    private static Logger logger = Logger.getLogger(Saml2AssertionUserIdInSubjectFactory.class);

    /**
     * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
     */
    protected Saml2AssertionUserIdInSubjectFactory() {
    }

    /**
     * Used for building a Subject with the UserId in the NameIdentifer element.
     * This implements the abstract non-arg method in the super class and then
     * calls back to the arg method in the super class to provide a name id value.
     */
    @Override
    protected Subject buildSubject() {
        if (getUserId() != null) {
            logger.debug("Building subject with " + getUserId() + " as name identifier value.");
            return super.buildSubject(getUserId());
        } else {
            throw new IllegalStateException("User Id must not be null.");
        }
    }

    /**
     * Used for building a null AttributeStatement, because it is not used
     * for this type of Assertion. Cannot be overriden.
     */
    @Override
    final protected AttributeStatement buildAttributeStatement() {
        logger.debug("Calling back to superclass to add standard attributes without adding additional attributes");
        return super.buildAttributeStatement(null);
    }


}
