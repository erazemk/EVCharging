package si.fri.prpo.skupina00.evcharging.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;

//@DeclareRoles({"user", "owner", "admin"})
@OpenAPIDefinition(
        info = @Info(
                title = "EVCharging API",
                version = "v1",
                description = "API for EVCharging services.",
                contact = @Contact(email = "ne@pisi.mi"),
                license = @License(name = "MIT")
        ),
        servers = @Server(url = "http://localhost:8080")
)
@ApplicationPath("/v1")
public class EVChargingApplication extends javax.ws.rs.core.Application {
}
