package jjuni.pjt.recommend.common.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response extends ResponseEntity {

    public Response(HttpStatus status) {
        super(status);
    }

}
