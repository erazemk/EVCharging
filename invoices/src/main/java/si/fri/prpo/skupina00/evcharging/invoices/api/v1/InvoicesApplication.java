package si.fri.prpo.skupina00.evcharging.invoices.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;

@DeclareRoles({"user", "owner", "admin"})
@OpenAPIDefinition(
        info = @Info(
                title = "Invoices API",
                version = "v1",
                description = "API for getting an invoice for your charge.",
                contact = @Contact(email = "ne@pisi.mi"),
                license = @License(name = "MIT")
        ),
        servers = @Server(url = "http://localhost:8082")
)
@ApplicationPath("/v1")
public class InvoicesApplication extends javax.ws.rs.core.Application{
}
