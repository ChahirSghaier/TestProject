package tn.esprit.idev.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@AllArgsConstructor
@Getter
public class ApiResponse extends Throwable {
    private String message;


}
