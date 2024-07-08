package br.com.diogenes.maxclubcard.entrypoint.procuder;

public interface ProducerService {

    void send(String topic, Object message);
}
