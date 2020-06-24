package domain.robots;

import domain.comandos.notemporizados.Color;
import domain.mqtt.IClienteMQTT;
import domain.mqtt.MensajeMQTT;

public class AdapterMQTTComunicadorRobot implements AdapterComunicadorRobot{
    private IClienteMQTT clienteMQTT;

    public AdapterMQTTComunicadorRobot(IClienteMQTT clienteMQTT){
        this.clienteMQTT = clienteMQTT;
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
        this.clienteMQTT.enviarMensaje(this.armarMensaje(topic, body));
    }

    public void apagarLuz(Color color){
        if (color == null) {
            armarMensajeYEnviar("robot/apagar", "todo");
        } else {
            armarMensajeYEnviar("robot/apagar", "todo");
        }

    }

    public void encenderLuz(Color color){
        armarMensajeYEnviar("robot/encender", color.equivalente(color));
    }

    public void adelante(Integer segundos){
        armarMensajeYEnviar("robot/adelante", segundos.toString());
    }

    public void atras(Integer segundos){
        armarMensajeYEnviar("robot/atras", segundos.toString());
    }

    public void izquierda(Integer segundos){
        armarMensajeYEnviar("robot/izquierda", segundos.toString());
    }

    public void derecha(Integer segundos){
        armarMensajeYEnviar("robot/derecha", segundos.toString());
    }

    public void tocarBocina(Integer segundos){
        armarMensajeYEnviar("robot/sonar", segundos.toString());
    }
}