package si.fri.prpo.skupina00.evcharging.api.v1.exceptionmappers;

import si.fri.prpo.skupina00.evcharging.services.exceptions.InvalidRequestException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class InvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestException> {

    @Override
    public Response toResponse(InvalidRequestException invalidRequestException) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"Error\":\"" + invalidRequestException.getMessage() + "\"}")
                .build();
    }
}
