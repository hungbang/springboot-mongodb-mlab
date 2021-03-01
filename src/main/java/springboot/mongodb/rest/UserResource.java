package springboot.mongodb.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.mongodb.entity.User;
import springboot.mongodb.exception.ResourceAlreadyExistsException;
import springboot.mongodb.exception.ResourceNotFoundException;
import springboot.mongodb.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> findUsers(@RequestParam(defaultValue = "") String firstname,
                                                @RequestParam(defaultValue = "") String lastname,
                                                @RequestParam(defaultValue = "") String email,
                                                @RequestParam(defaultValue = "") String phone,
                                                @RequestParam(defaultValue = "") String address,
                                                Pageable page) {
        Page<User> users = userService.findUsers(firstname, lastname, email, phone, address, page);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (ResourceAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
