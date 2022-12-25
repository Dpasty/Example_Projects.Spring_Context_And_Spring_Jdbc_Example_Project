package school21.spring.service.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String email;
    private String tempPassword;
}
