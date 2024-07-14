package men.okuuim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;
    /**
     * сгенерированный токен
     */
    private String token;

    private long expiresIn;

    /**
     * Имя пользователя
     */
    private String name;


    public String getToken() {
        return token;
    }

}
