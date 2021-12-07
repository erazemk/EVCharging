package si.fri.prpo.skupina00.evcharging.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina00.evcharging.entities.Owner;
import si.fri.prpo.skupina00.evcharging.entities.User;
import si.fri.prpo.skupina00.evcharging.services.annotations.LogCalls;
import si.fri.prpo.skupina00.evcharging.services.dtos.OwnerDto;
import si.fri.prpo.skupina00.evcharging.services.dtos.UserDto;
import si.fri.prpo.skupina00.evcharging.services.exceptions.InvalidRequestException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@LogCalls
public class UserManagerBean {
    private static final Logger log = Logger.getLogger(UserManagerBean.class.getName());

    @Inject
    private Mapper mapper;

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

    public List<UserDto> getUsers(QueryParameters queryParameters) {
        List<User> users = userBean.getUsers(queryParameters);
        List<UserDto> userDtos = new ArrayList<>(users.size());

        for (User user : users) {
            userDtos.add(mapper.mapUser(user));
        }

        return userDtos;
    }

    public UserDto getUser(Integer id) {
        User user = userBean.getUser(id);

        if (user != null) {
            return mapper.mapUser(user);
        }

        throw new InvalidRequestException("Could not find user");
    }

    @Transactional
    public UserDto addUser(UserDto userDto) {
        User user = null;

        if (userDto != null) {
            user = userBean.addUser(mapper.mapUser(userDto));
        }

        if (user != null) {
            return mapper.mapUser(user);
        }

        throw new InvalidRequestException("Could not add user");
    }

    @Transactional
    public UserDto updateUser(Integer id, UserDto userDto) {
        User user = null;

        if (userDto != null) {
            user = userBean.updateUser(id, mapper.mapUser(userDto));
        }

        if (user != null) {
            return mapper.mapUser(user);
        }

        throw new InvalidRequestException("Could not update user");
    }

    @Transactional
    public boolean deleteUser(Integer id) {
        if (userBean.deleteUser(id)) {
            return true;
        }

        throw new InvalidRequestException("Could not delete user");
    }

    public List<OwnerDto> getOwners(QueryParameters queryParameters) {
        List<Owner> owners = ownerBean.getOwners(queryParameters);
        List<OwnerDto> ownerDtos = new ArrayList<>(owners.size());

        for (Owner owner : owners) {
            ownerDtos.add(mapper.mapOwner(owner));
        }

        return ownerDtos;
    }

    public OwnerDto getOwner(Integer id) {
        Owner owner = ownerBean.getOwner(id);

        if (owner != null) {
            return mapper.mapOwner(owner);
        }

        throw new InvalidRequestException("Could not find owner");
    }

    @Transactional
    public OwnerDto addOwner(OwnerDto ownerDto) {
        Owner owner = null;

        if (ownerDto != null) {
            owner = ownerBean.addOwner(mapper.mapOwner(ownerDto));
        }

        if (owner != null) {
            return mapper.mapOwner(owner);
        }

        throw new InvalidRequestException("Could not add owner");
    }

    @Transactional
    public OwnerDto updateOwner(Integer id, OwnerDto ownerDto) {
        Owner owner = null;

        if (ownerDto != null) {
            owner = ownerBean.updateOwner(id, mapper.mapOwner(ownerDto));
        }

        if (owner != null) {
            return mapper.mapOwner(owner);
        }

        throw new InvalidRequestException("Could not update owner");
    }

    @Transactional
    public boolean deleteOwner(Integer id) {
        if (ownerBean.deleteOwner(id)) {
            return true;
        }

        throw new InvalidRequestException("Could not delete owner");
    }
}
