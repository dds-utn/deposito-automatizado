package domain.robots;

import domain.comandos.notemporizados.conluz.Color;
import domain.mqtt.IClienteMQTT;
import domain.mqtt.MensajeMQTT;

public class AdapterMQTTComunicadorRobot implements AdapterComunicadorRobot {
    private IClienteMQTT clienteMQTT;
    private static final String baseTopic = "ar.utn.frba.dds/robot/";

    public AdapterMQTTComunicadorRobot(IClienteMQTT clienteMQTT){
        this.clienteMQTT = clienteMQTT;
    }

    public static String getBaseTopic() {
        return baseTopic;
    }

    private MensajeMQTT armarMensaje(String topic, String body){
        return new MensajeMQTT(){
            @Override
            public String topic() {
                return topic;
            }

            @Override
            public String body() {
                return body;
            }
        };
    }

    private void armarMensajeYEnviar(String topic, String body){
        this.clienteMQTT.enviarMensaje(this.armarMensaje(baseTopic + topic, body));
    }

    public void apagarLuz(Color color){
        if (color == null) {
            armarMensajeYEnviar("apagar", "todo");
        } else {
            armarMensajeYEnviar("apagar", color.equivalente(color));
        }
    }

    public void encenderLuz(Color color){
        armarMensajeYEnviar("encender", color.equivalente(color));
    }

    public void adelante(Integer segundos){
        armarMensajeYEnviar("adelante", segundos.toString());
    }

    public void atras(Integer segundos){
        armarMensajeYEnviar("atras", segundos.toString());
    }

    public void izquierda(Integer segundos){
        armarMensajeYEnviar("izquierda", segundos.toString());
    }

    public void derecha(Integer segundos){
        armarMensajeYEnviar("derecha", segundos.toString());
    }

    public void tocarBocina(Integer segundos){
        armarMensajeYEnviar("sonar", segundos.toString());
    }

    public void mostrarFrase(String frase) {
        String mensaje  = "[{\"clear\": true,\"text\": \""+frase+"\" }, {\"clear\": true,\"text\": \" DDS UTNBA 2021\" }]";
        armarMensajeYEnviar("mensaje", mensaje);
    }
}