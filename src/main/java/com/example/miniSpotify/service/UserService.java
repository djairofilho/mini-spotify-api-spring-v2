package com.example.miniSpotify.service;

import com.example.miniSpotify.exception.BusinessException;
import com.example.miniSpotify.exception.NotFoundException;
import com.example.miniSpotify.model.User;
import com.example.miniSpotify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        validateEmail(user.getEmail(), null);
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAllByAtivoTrue();
    }

    public User findById(Long id) {
        return getActiveEntity(id);
    }

    public User update(Long id, User request) {
        validateEmail(request.getEmail(), id);
        User user = getActiveEntity(id);
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setTipoPlano(request.getTipoPlano());
        user.setAtivo(request.isAtivo());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = getActiveEntity(id);
        user.setAtivo(false);
        userRepository.save(user);
    }

    public User getActiveEntity(Long id) {
        return userRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }

    public User getUsableEntity(Long id) {
        User user = getActiveEntity(id);
        if (!user.isAtivo()) {
            throw new BusinessException("Usuario inativo nao pode ser utilizado nesta operacao");
        }
        return user;
    }

    private void validateEmail(String email, Long userId) {
        boolean exists = userId == null
                ? userRepository.existsByEmail(email)
                : userRepository.existsByEmailAndIdNot(email, userId);
        if (exists) {
            throw new BusinessException("Ja existe usuario com este email");
        }
    }
}
