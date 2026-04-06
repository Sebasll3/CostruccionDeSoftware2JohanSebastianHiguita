package app.ports;

import java.util.List;

import app.domain.models.SystemRol;
import app.domain.models.User;

/**
 * Puerto (Interfaz) para servicios de negocio de User
 * Define operaciones del dominio de usuarios
 */
public interface UserService {
    
    /**
     * Registra un nuevo usuario de tipo persona natural
     */
    User registerUser(String userName, String password, String email, String fullName) throws Exception;
    
    /**
     * Crea un usuario con rol específico
     */
    User createUser(String userName, String password, String email, String fullName, SystemRol rol) throws Exception;
    
    /**
     * Edita los datos de un usuario existente
     */
    void editUser(int userId, String fullName, String email, String phone, String address) throws Exception;
    
    /**
     * Autentica un usuario
     */
    User authenticate(String userName, String password) throws Exception;
    
    /**
     * Obtiene un usuario por ID
     */
    User getUserById(int userId) throws Exception;
    
    /**
     * Obtiene un usuario por número de identificación
     */
    User getUserByIdentification(String identification) throws Exception;
    
    /**
     * Obtiene un usuario por nombre de usuario
     */
    User getUserByUserName(String userName) throws Exception;
    
    /**
     * Obtiene todos los usuarios con un rol específico
     */
    List<User> getUsersByRole(SystemRol rol);
    
    /**
     * Cambia la contraseña de un usuario
     */
    void changePassword(int userId, String oldPassword, String newPassword) throws Exception;
    
    /**
     * Asigna un rol a un usuario
     */
    void assignRole(int userId, SystemRol rol) throws Exception;
}
