package at.ac.fhcampuswien.car_rental.validator;

import at.ac.fhcampuswien.car_rental.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorDTO> collect = ex.getBindingResult().getAllErrors().stream().
                map(objectError ->
                {
                    String field = ((FieldError) objectError).getField();
                    String message = Objects.requireNonNull(objectError.getDefaultMessage());
                    return new ErrorDTO(field, message, Instant.now(), ((ServletWebRequest) request).getRequest().getRequestURI());
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.BAD_REQUEST);

    }
}
