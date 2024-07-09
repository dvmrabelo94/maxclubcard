package br.com.diogenes.maxclubcard.dataprovider.client.repository;

import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = "spring.config.name=application-test")
@ExtendWith(SpringExtension.class)
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testSaveAndFindByDocument() {
        ClientEntity clientEntity = new ClientEntity(1l, "Joe", "12345678901", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");

        clientRepository.save(clientEntity);

        ClientEntity foundClient = clientRepository.findByDocument("12345678901");

        assertNotNull(foundClient);
    }
}