package domain.robots;

import domain.tracer.Tracer;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Sensor implements IMqttMessageListener {
    private Robot robot;

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Double proximidadAObjetoEnCM = new Double(message.toString());
        Tracer.getInstance().showTrace(proximidadAObjetoEnCM.toString());
        this.robot.setProximidadAObjetoEnCM(proximidadAObjetoEnCM);
    }
}
