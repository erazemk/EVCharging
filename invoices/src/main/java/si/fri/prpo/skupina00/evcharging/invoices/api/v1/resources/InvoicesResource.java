package si.fri.prpo.skupina00.evcharging.invoices.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.invoices.api.v1.beans.InvoiceBean;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.ChargeDto;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.InvoiceDto;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.UserDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.util.logging.Logger;

@Path("/invoices")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class InvoicesResource {

    @Inject
    private InvoiceBean invoiceBean;

    @Context
    protected UriInfo uriInfo;

    private Client httpClient;
    private String baseUrl;
    private static final Logger log = Logger.getLogger(InvoicesResource.class.getName());

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://localhost:8080/v1";
    }

    @GET
    public Response getInvoice(@QueryParam("user") Integer userId, @QueryParam("charge") Integer chargeId)
            throws JAXBException, FileNotFoundException {
        InvoiceDto invoiceDto = new InvoiceDto();
        UserDto user = invoiceBean.getUser(userId);
        ChargeDto charge = invoiceBean.getCharge(chargeId);
        invoiceDto.setName(user.getName());
        invoiceDto.setSurname(user.getSurname());
        invoiceDto.setEmail(user.getEmail());

        invoiceDto.setPrice(charge.getPrice());
        invoiceDto.setStationId(charge.getStationId());
        invoiceDto.setDuration(Duration.between(charge.getBeginTime().toInstant(),
                charge.getEndTime().toInstant()).toMinutes());

        OutputStream xml = new FileOutputStream("invoice.xml");
        JAXBContext ctx = JAXBContext.newInstance(InvoiceDto.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.marshal(invoiceDto, xml);

        if (xml != null){
            return Response
                    .status(Response.Status.OK)
                    .entity(xml)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }


}
