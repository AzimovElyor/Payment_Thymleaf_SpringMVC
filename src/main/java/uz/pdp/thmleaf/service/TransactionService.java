package uz.pdp.thmleaf.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uz.pdp.thmleaf.dto.*;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    @Value("${backend.base-url}")
    String BASE_URL;

    public Object create(TransactionRequestDto requestDto) {
      try {
          ResponseEntity<String> exchange = restTemplate.exchange(
                  BASE_URL + "/transaction/create",
                  HttpMethod.POST,
                  new HttpEntity<>(requestDto),
                  String.class
          );
          return exchange.getBody();

      }catch (HttpClientErrorException e){
         return e.getResponseBodyAs(new ParameterizedTypeReference<List<ExceptionDto>>() {
          });
      }
    }

    public List<TransactionResponseDto> getUserTransactions(UUID userId, UserTransactions transactions) {
        try {
            System.out.println();
            ResponseEntity<List<TransactionResponseDto>> exchange = restTemplate.exchange(
                    BASE_URL + "/transaction/user-transactions/" + userId,
                    HttpMethod.POST,
                    new HttpEntity<>(transactions),
                    new ParameterizedTypeReference<List<TransactionResponseDto>>() {
                    }

            );
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(new TransactionResponseDto());
        }
    }

    public List<TransactionResponseDto> getAllTransaction(UserTransactions userTransactions) {
        try {
           ResponseEntity<List<TransactionResponseDto>> getAll=  restTemplate.exchange(
                    BASE_URL + "/transaction/get-all",
                    HttpMethod.POST,
                    new HttpEntity<>(userTransactions),
                    new ParameterizedTypeReference<List<TransactionResponseDto>>(){

                    }
            );
           return getAll.getBody();
        }catch (Exception e){
            e.printStackTrace();
            return List.of(new TransactionResponseDto());
        }


    }
}
