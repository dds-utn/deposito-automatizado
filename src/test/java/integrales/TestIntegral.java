package integrales;

import domain.comandos.Comando;
import domain.comandos.ComandoRobot;
import domain.comandos.Demorar;
import domain.comandos.notemporizados.*;
import domain.comandos.temporizados.*;
import domain.mqtt.ClienteMQTT;
import domain.mqtt.ClienteMQTTBuilder;
import domain.mqtt.PublisherMQTT;
import domain.mqtt.SubscriberMQTT;
import domain.observers.Regla;
import domain.observers.condiciones.CondicionMenor;
import domain.robots.AdapterComunicadorRobot;
import domain.robots.AdapterMQTTComunicadorRobot;
import domain.robots.Robot;
import domain.robots.Sensor;
import org.eclipse.paho.client.mqttv3.MqttException;

public class TestIntegral {

    public static void main(String[] args) throws MqttException {
        ClienteMQTT clienteMQTT = new ClienteMQTTBuilder()
                .conIPDestino("test.mosquitto.org")
                .conPuertoDestino(1883)
                .construir();

        ClienteMQTT clienteMQTT2 = new ClienteMQTTBuilder()
                .conIPDestino("test.mosquitto.org")
                .conPuertoDestino(1883)
                .construir();

        PublisherMQTT publisher = new PublisherMQTT(clienteMQTT);
        SubscriberMQTT subscriber = new SubscriberMQTT(clienteMQTT2);

        AdapterComunicadorRobot adapter = new AdapterMQTTComunicadorRobot(publisher);
        Sensor sensorProximidad = new Sensor();
        Robot robotin = new Robot("Robotin", sensorProximidad);
        subscriber.suscribir("robot/distancia", sensorProximidad);

        ComandoTemporizadoBuilder<Adelante> builder = new ComandoTemporizadoBuilder<>(Adelante.class);
        ComandoRobot comandoAdelante = builder
                .conReceptor(adapter)
                .conSegundos(3)
                .construir();

        ComandoRobot comandoAtras = new ComandoTemporizadoBuilder<>(Atras.class)
                .conReceptor(adapter)
                .conSegundos(3)
                .construir();

        ComandoRobot comandoDerecha = new ComandoTemporizadoBuilder<>(Derecha.class)
                .conReceptor(adapter)
                .conSegundos(3)
                .construir();

        ComandoRobot comandoIzquierda = new ComandoTemporizadoBuilder<>(Izquierda.class)
                .conReceptor(adapter)
                .conSegundos(3)
                .construir();

        ComandoRobot comandoEncenderLuzRoja = new ComandoConLuzBuilder<>(EncenderLuz.class)
                .conReceptor(adapter)
                .conLuzDeColor(Color.ROJO)
                .construir();

        ComandoRobot comandoEncenderLuzAmarilla = new ComandoConLuzBuilder<>(EncenderLuz.class)
                .conReceptor(adapter)
                .conLuzDeColor(Color.AMARILLO)
                .construir();

        ComandoRobot comandoEncenderLuzVerde = new ComandoConLuzBuilder<>(EncenderLuz.class)
                .conReceptor(adapter)
                .conLuzDeColor(Color.VERDE)
                .construir();

        ComandoRobot comandoApagarLuz = new ComandoConLuzBuilder<>(ApagarLuz.class)
                .conReceptor(adapter)
                .construir();

        ComandoRobot comandoTocarBocina = new ComandoTemporizadoBuilder<>(TocarBocina.class)
                .conReceptor(adapter)
                .conSegundos(3)
                .construir();

        Comando demorar = new Demorar(3.0);

        Regla alejarSiSeAcercaAlgo = new Regla(new CondicionMenor(), 10.0);
        alejarSiSeAcercaAlgo.agregarAccion(comandoAtras);
        robotin.agregarObservers(alejarSiSeAcercaAlgo);

        robotin.encolaComando(
                comandoApagarLuz,
                comandoEncenderLuzRoja,
                demorar,
                comandoEncenderLuzAmarilla,
                demorar,
                comandoApagarLuz,
                comandoEncenderLuzVerde,
                demorar,
                comandoTocarBocina,
                comandoAdelante,
                demorar,
                comandoIzquierda,
                demorar,
                comandoAtras,
                demorar,
                comandoDerecha,
                comandoApagarLuz
        );
        robotin.ejecutaComandos();
        publisher.getCliente().desconectarYCerrar();
    }
}