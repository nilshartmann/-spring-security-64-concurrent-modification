package org.example.ccm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    private static final Logger log = LoggerFactory.getLogger(HelloControllerTest.class);

    @LocalServerPort
    int port;

    private WebClient webClient;

    @BeforeEach
    void setupWebClient() {
        this.webClient = WebClient.builder()
            .baseUrl("http://localhost:%d/".formatted(port))
            .defaultHeaders(headers -> headers.setBasicAuth("user", "secret"))
            .build();
    }

    @Test
    @DirtiesContext
    void test_two_requests_different_endpoints() {

        // THIS DOES NOT:

        assertDoesNotThrow(() -> Mono.zip(
                request("hello"),
                request("goodbye"))
            .block());
    }

    @Test
    @DirtiesContext
    void test_two_requests_same_endpoint() {

        // THIS WORKS:

        assertDoesNotThrow(() -> Mono.zip(
                request("hello"),
                request("hello"))
            .block());
    }


    Mono<String> request(String path) {
        return webClient.get()
            .uri(path)
            .retrieve()
            .bodyToMono(String.class);
    }


}