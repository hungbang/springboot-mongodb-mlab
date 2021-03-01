package springboot.mongodb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.mongodb.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);

    @Query(value = "{'firstname': {$regex : ?0, $options: 'i'}, " +
            "'lastname': {$regex : ?1, $options: 'i'}, " +
            "'email': {$regex : ?2, $options: 'i'}, " +
            "'phone': {$regex : ?3, $options: 'i'}, " +
            "'address': {$regex : ?4, $options: 'i'}, " +
            "}")
    Page<User> findUsers(String firstname, String lastname, String email, String phone, String address, Pageable page);
}
