package jjuni.pjt.recommand.common.http;

import com.mongodb.lang.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response extends ResponseEntity {

    public Response(HttpStatus status) {
        super(status);
    }

}
