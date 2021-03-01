package springboot.mongodb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class User {
    @Indexed
    private String id;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    @Indexed
    private String email;
    private String phone;
    private String address;
}
