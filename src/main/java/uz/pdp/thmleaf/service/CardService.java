package uz.pdp.thmleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.thmleaf.dto.CardRequestDto;
import uz.pdp.thmleaf.dto.CardResponseDto;

import java.lang.reflect.Type;
import java.net.CacheResponse;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final RestTemplate restTemplate;
    @Value("${backend.base-url}")
    String BASE_URL;
    public List<CardResponseDto> getUserCards(UUID userId){
        ResponseEntity<List<CardResponseDto>> exchange = restTemplate.exchange(
                BASE_URL + "/card/user-cards/" + userId,
                HttpMethod.GET,
                new HttpEntity<>(userId),
                new ParameterizedTypeReference<List<CardResponseDto>>() {
                }


        );
        return exchange.getBody();
    }
    public CardResponseDto create(CardRequestDto cardRequestDto){
        ResponseEntity<CardResponseDto> createCard = restTemplate.exchange(
                BASE_URL + "/card/create",
                HttpMethod.POST,
                new HttpEntity<>(cardRequestDto),
                CardResponseDto.class
        );

        return createCard.getBody();
    }
}
