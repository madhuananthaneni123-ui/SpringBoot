package in.madhu.crud.Exception;

import in.madhu.crud.Dto.ExceptionResponseDto;
import in.madhu.crud.Dto.ValidationExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GobalException {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> HandleRunException(RuntimeException ex, HttpServletRequest request) {
        ExceptionResponseDto re=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> HandleGenericRunException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went Wrong");
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> HandleResourceRunException(ResourceNotFoundException ex,
                                                                           HttpServletRequest request) {
        ExceptionResponseDto req=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(req);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDto> HandlemethodArgumentException(MethodArgumentNotValidException ex,
                                                                                HttpServletRequest request) {
        Map<String,String> fe=new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error ->fe.put(error.getField(), error.getDefaultMessage()) );
        ValidationExceptionDto req=new ValidationExceptionDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                fe
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(req);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDto> HandleDuplicateResourceRunException(DuplicateResourceException ex,
                                                                                    HttpServletRequest request) {
        ExceptionResponseDto re=new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(re);
    }

}
