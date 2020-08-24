package lordfoom.sideprojects.pretentiousfilmclub.critic;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CriticRegistrationDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

}
