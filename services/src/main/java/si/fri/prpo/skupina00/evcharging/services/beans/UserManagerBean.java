package si.fri.prpo.skupina00.evcharging.services.beans;

import si.fri.prpo.skupina00.evcharging.entities.*;
import si.fri.prpo.skupina00.evcharging.services.dtos.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@RequestScoped
public class UserManagerBean {
    private static final Logger log = Logger.getLogger(UserManagerBean.class.getName());

    @Inject
    private UserBean userBean;

    @Inject
    private OwnerBean ownerBean;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + UserManagerBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + UserManagerBean.class.getSimpleName());
    }

    @Transactional
    public boolean addUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getSurname(), userDto.getEmail());
        return userBean.addUser(user);
    }

    @Transactional
    public boolean addOwner(OwnerDto ownerDto) {
        Owner owner = new Owner(ownerDto.getName(), ownerDto.getSurname(), ownerDto.getEmail());
        return ownerBean.addOwner(owner);
    }
}
