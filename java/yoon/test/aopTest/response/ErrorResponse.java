package yoon.test.aopTest.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private HttpStatus status;

    private String message;

    private String code;
}
