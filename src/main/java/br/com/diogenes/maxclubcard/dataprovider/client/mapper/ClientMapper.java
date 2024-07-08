package br.com.diogenes.maxclubcard.dataprovider.client.mapper;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.core.domain.client.ClientOut;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;

public class ClientMapper {

    public static ClientEntity toEntity(Client client) {
        return ClientEntity.builder()
                .name(client.name())
                .email(client.email())
                .birth(client.birth())
                .gender(client.gender())
                .document(client.document())
                .phoneNumber(client.phoneNumber())
                .build();
    }

    public static ClientOut toDomain(ClientEntity clientEntity) {
        return new ClientOut(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getEmail(),
                clientEntity.getBirth(),
                clientEntity.getGender(),
                clientEntity.getDocument(),
                clientEntity.getPhoneNumber()
        );
    }
}
