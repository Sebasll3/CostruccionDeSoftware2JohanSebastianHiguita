package app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.SystemRol;
import app.domain.models.User;
import app.ports.UserService;
import app.services.user.UserAuthenticateService;
import app.services.user.UserCreateService;
import app.services.user.UserEditService;
import app.services.user.UserPasswordService;
import app.services.user.UserQueryService;
import app.services.user.UserRoleService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de dominio para User
 * Delegación de cada funcionalidad a un servicio independiente
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCreateService userCreateService;
    private final UserAuthenticateService userAuthenticateService;
    private final UserQueryService userQueryService;
    private final UserEditService userEditService;
    private final UserPasswordService userPasswordService;
    private final UserRoleService userRoleService;

    @Override
    public User registerUser(String userName, String password, String email, String fullName) throws Exception {
        return userCreateService.createUser(userName, password, email, fullName, SystemRol.INDIVIDUAL_CUSTOMER);
    }

    @Override
    public User createUser(String userName, String password, String email, String fullName, SystemRol rol) throws Exception {
        return userCreateService.createUser(userName, password, email, fullName, rol);
    }

    @Override
    public User authenticate(String userName, String password) throws Exception {
        return userAuthenticateService.authenticate(userName, password);
    }

    @Override
    public User getUserById(int userId) throws Exception {
        return userQueryService.getUserById(userId);
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        return userQueryService.getUserByUserName(userName);
    }

    @Override
    public List<User> getUsersByRole(SystemRol rol) {
        return userQueryService.getUsersByRole(rol);
    }

    @Override
    public void editUser(int userId, String fullName, String email, String phone, String address) throws Exception {
        userEditService.editUser(userId, fullName, email, phone, address);
    }

    @Override
    public void changePassword(int userId, String oldPassword, String newPassword) throws Exception {
        userPasswordService.changePassword(userId, oldPassword, newPassword);
    }

    @Override
    public void assignRole(int userId, SystemRol rol) throws Exception {
        userRoleService.assignRole(userId, rol);
    }

    @Override
    public User getUserByIdentification(String identification) throws Exception {
        return userQueryService.getUserByIdentification(identification);
    }
}
