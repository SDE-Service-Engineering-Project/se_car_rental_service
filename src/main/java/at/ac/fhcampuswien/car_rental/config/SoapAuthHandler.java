package at.ac.fhcampuswien.car_rental.config;

import at.ac.fhcampuswien.car_rental.soap.client.Credentials;
import at.ac.fhcampuswien.car_rental.soap.client.ObjectFactory;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;
import java.util.TreeSet;

@Log4j2
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SoapAuthHandler implements SOAPHandler<SOAPMessageContext> {

    ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outboundProperty =
                (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty) {
            try {
                Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Credentials credentials = new Credentials();
                credentials.setToken(principal.getTokenValue());
                JAXBElement<Credentials> requesterCredentials = objectFactory.createCredentials(credentials);

                // obtaining marshaller which should marshal instance to xml
                Marshaller marshaller = JAXBContext.newInstance(Credentials.class).createMarshaller();
                // adding header because otherwise it's null
                SOAPHeader soapHeader = context.getMessage().getSOAPPart().getEnvelope().getHeader();
                // marshalling instance (appending) to SOAP header's xml node
                marshaller.marshal(requesterCredentials, soapHeader);
            } catch (JAXBException | SOAPException e) {
                log.error("Problem caught with SOAP: {}", e.getMessage());
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not make call to Currency Converter Service");
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {}

    @Override
    public Set<QName> getHeaders() {
        return new TreeSet();
    }
}
