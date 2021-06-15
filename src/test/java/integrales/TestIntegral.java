package integrales;

import domain.comandos.Comando;
import domain.comandos.ComandoRobot;
import domain.comandos.Demorar;
import domain.comandos.notemporizados.ComandoFraseBuilder;
import domain.comandos.notemporizados.MostrarFrase;
import domain.comandos.notemporizados.conluz.ApagarLuz;
import domain.comandos.notemporizados.conluz.Color;
import domain.comandos.notemporizados.conluz.ComandoConLuzBuilder;
import domain.comandos.notemporizados.conluz.EncenderLuz;
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
                .conIPDestino("broker.hivemq.com")
                .conPuertoDestino(1883)
                .construir();

        ClienteMQTT clienteMQTT2 = new ClienteMQTTBuilder()
                .conIPDestino("broker.hivemq.com")
                .conPuertoDestino(1883)
                .construir();

        PublisherMQTT publisher = new PublisherMQTT(clienteMQTT);
        SubscriberMQTT subscriber = new SubscriberMQTT(clienteMQTT2);

        AdapterComunicadorRobot adapter = new AdapterMQTTComunicadorRobot(publisher);
        Sensor sensorProximidad = new Sensor();
        Robot robotin = new Robot("Robotin", sensorProximidad);
        subscriber.suscribir(AdapterMQTTComunicadorRobot.getBaseTopic() + "distancia", sensorProximidad);

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
                .conSegundos(1)
                .construir();

        ComandoRobot comandoIzquierda = new ComandoTemporizadoBuilder<>(Izquierda.class)
                .conReceptor(adapter)
                .conSegundos(1)
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
                .conSegundos(1)
                .construir();

        ComandoRobot comandoMostrarFrase = new ComandoFraseBuilder(MostrarFrase.class)
                .conReceptor(adapter)
                .conFrase(" EJ. COMANDOS")
                .construir();

        Comando demorar = new Demorar(4.0);

        ComandoRobot comandoAtrasCorto = new ComandoTemporizadoBuilder<>(Atras.class)
                .conReceptor(adapter)
                .conSegundos(1)
                .construir();

        ComandoRobot mostrarLeyendaObstaculo = new ComandoFraseBuilder(MostrarFrase.class)
                .conReceptor(adapter)
                .conFrase("PELIGRO COLISION")
                .construir();

        Regla alejarSiSeAcercaAlgo = new Regla(new CondicionMenor(), 10.0);
        alejarSiSeAcercaAlgo.agregarAccion(comandoAtrasCorto, mostrarLeyendaObstaculo, comandoEncenderLuzRoja);
        robotin.agregarObservers(alejarSiSeAcercaAlgo);

        robotin.encolaComando(
                comandoTocarBocina,
                comandoMostrarFrase,
                comandoEncenderLuzRoja,
                demorar,
                comandoEncenderLuzAmarilla,
                demorar,
                comandoEncenderLuzVerde,
                comandoApagarLuz,
                comandoAtras,
                demorar,
                comandoIzquierda,
                demorar,
                comandoDerecha,
                demorar,
                comandoAdelante,
                demorar
        );
        robotin.ejecutaComandos();
       // publisher.getCliente().desconectarYCerrar();
    }
}