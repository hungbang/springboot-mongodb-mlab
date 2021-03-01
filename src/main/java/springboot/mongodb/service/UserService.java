package springboot.mongodb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springboot.mongodb.entity.User;
import springboot.mongodb.exception.ResourceAlreadyExistsException;
import springboot.mongodb.exception.ResourceNotFoundException;
import springboot.mongodb.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findUsers(String firstname, String lastname, String email, String phone,
                                String address, Pageable page) {
        return userRepository.findUsers(firstname, lastname, email, phone, address, page);
    }

    public User createUser(User user) throws ResourceAlreadyExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    public User updateUser(String id, User newData) throws ResourceNotFoundException {
        Optional<User> oldDataOptional = userRepository.findById(id);
        if (oldDataOptional.isPresent()) {
            User oldData = oldDataOptional.get();
            oldData.setFirstname(newData.getFirstname());
            oldData.setLastname(newData.getLastname());
            oldData.setPhone(newData.getPhone());
            oldData.setAddress(newData.getAddress());
            return userRepository.save(oldData);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
