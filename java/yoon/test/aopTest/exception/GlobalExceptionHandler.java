package yoon.test.aopTest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yoon.test.aopTest.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> ValidatedExceptions(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage("입력한 값을 다시 확인해주세요.");
        errorResponse.setCode(bindingResult.getAllErrors().get(0).getDefaultMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    protected ResponseEntity<ErrorResponse> LoginPrincipalExceptions(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage("존재하지 않는 이메일 주소");
        errorResponse.setCode(e.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({BadCredentialsException.class})
    protected ResponseEntity<ErrorResponse> LoginCredentialExceptions(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage("이메일 또는 비밀번호가 불일치");
        errorResponse.setCode(e.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
