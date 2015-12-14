package com.lkulig.ratpack.e2e;

import static com.lkulig.ratpack.api.ApiConstants.USERS_API_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import ratpack.http.client.ReceivedResponse;

public class UsersTest extends AbstractFunctionalTest {

    @Test
    public void shouldGetAllUsers() {
        // when
        ReceivedResponse receivedResponse = httpClient.get(USERS_API_PATH);

        // then
        assertThat(receivedResponse.getStatusCode()).isEqualTo(200);
        assertThat(receivedResponse.getBody()).isNotNull();
    }
}
