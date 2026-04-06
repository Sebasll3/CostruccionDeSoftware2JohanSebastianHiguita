package app.ports;

import java.util.List;
import java.util.Optional;

import app.domain.models.SystemRol;
import app.domain.models.User;

/**
 * Puerto (Interfaz) para la persistencia de User
 * Parte del Agregado de User
 */
public interface UserPort {
    
    /**
     * Guarda un nuevo usuario
     */
    void save(User user);
    
    /**
     * Busca un usuario por ID
     */
    Optional<User> findById(int userId);
    
    /**
     * Busca un usuario por nombre de usuario
     */
    Optional<User> findByUserName(String userName);
    
    /**
     * Busca un usuario por email
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Busca un usuario por número de identificación
     */
    Optional<User> findByIdentification(String identification);
    
    /**
     * Busca usuarios por rol
     */
    List<User> findByRole(SystemRol role);
    
    /**
     * Actualiza un usuario existente
     */
    void update(User user);
    
    /**
     * Elimina un usuario
     */
    void delete(int userId);
    
    /**
     * Busca todos los usuarios
     */
    List<User> findAll();
}
