package uz.pdp.thmleaf.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;


public class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (
                response.getStatusCode().is4xxClientError()
                        || response.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode()
                .is5xxServerError()) {
            System.out.println("Server error keldi");
        } else if (response.getStatusCode()
                .is4xxClientError()) {
            System.out.println("Client error ");
            // handle CLIENT_ERROR
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new RuntimeException("Bunday url topilmadi");
            }
        }
    }
}
