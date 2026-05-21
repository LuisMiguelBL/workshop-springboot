package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.exceptions.UserNotFoundException;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // registra como componente para poder usar a injeção de dependencia
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User não encontrado pelo o id: "+ id));
    }

    public User insert(User user){
        return repository.save(user);
    }

    public void delete(long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj){
        User entity = repository.getReferenceById(id); // utilizado para buscar primeiro e depois vc poder modificar no banco de dados
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User userOld, User userNew) {
        userOld.setName(userNew.getName());
        userOld.setEmail(userNew.getEmail());
        userOld.setPhone(userNew.getPhone());
    }
}
