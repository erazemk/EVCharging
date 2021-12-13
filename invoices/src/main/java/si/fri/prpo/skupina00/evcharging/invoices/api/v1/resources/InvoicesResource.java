package si.fri.prpo.skupina00.evcharging.invoices.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.invoices.api.v1.beans.InvoiceBean;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.ChargeDto;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.InvoiceDto;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.UserDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

@Path("/invoices")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class InvoicesResource {

    @Inject
    private InvoiceBean invoiceBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getInvoice(@QueryParam("charge") Integer chargeId) throws JAXBException {

        InvoiceDto invoiceDto = new InvoiceDto();
        ChargeDto charge = invoiceBean.getCharge(chargeId);
        UserDto user = invoiceBean.getUser(charge.getUserId());
        invoiceDto.setName(user.getName());
        invoiceDto.setSurname(user.getSurname());
        invoiceDto.setEmail(user.getEmail());

        invoiceDto.setPrice(charge.getPrice());
        invoiceDto.setStationId(charge.getStationId());
        invoiceDto.setDuration(TimeUnit.MILLISECONDS.toMinutes(charge.getEndTime().getTime() -
                charge.getBeginTime().getTime()));

        JAXBContext ctx = JAXBContext.newInstance(InvoiceDto.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(invoiceDto, sw);
        String xmlContent = sw.toString();

        if (xmlContent != null){
            return Response
                    .status(Response.Status.OK)
                    .type(MediaType.TEXT_XML)
                    .entity(xmlContent)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
