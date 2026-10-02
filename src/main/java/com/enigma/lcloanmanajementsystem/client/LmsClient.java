package com.enigma.lcloanmanajementsystem.client;

import com.enigma.lcloanmanajementsystem.dto.request.CreditScoringRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CreditScoringResponse;
import com.enigma.lcloanmanajementsystem.utils.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@AllArgsConstructor
@Slf4j
public class LmsClient {
    private final RestClient creditScoringRestClient;

    public CreditScoringResponse getCreditScore(CreditScoringRequest request) {
        try {
            return creditScoringRestClient.post()
                    .uri("/api/v1/credit-scores")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(CreditScoringResponse.class);
        } catch (RestClientException e) {
            throw new BusinessException(
                    "Credit Scoring Service is currently unavailable. Please try again later.");
        }
    }
}
