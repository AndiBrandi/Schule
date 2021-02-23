package sample;

import Subs.Publisher;
import Subs.Subscriber;
import com.sun.source.tree.InstanceOfTree;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements Publisher {
    public TextArea Inhalt;
    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {

        subscribers.remove(subscriber);
    }


    @Override
    public void notifyMessages(String message) {
        for (Subscriber element : subscribers) {
            // benachrichtige jeden Subscriber (Fleischesser, Veganer, Vegetarier),
            // dass es etwas zum Essen geht.
            element.notifyMessage(message);
        }
    }

    public void SendButtonClicked(ActionEvent actionEvent) {
       if(Inhalt.getText().length()>0){
           notifyMessages(Inhalt.getText());

           Inhalt.clear();
       }
    }

    public void ClosedButtonClicked(ActionEvent actionEvent) {



    }

/**
 * Schreiben Sie ein Programm, welches eine GUI wie folgt implementiert: Es soll eine Nachricht in einer Textarea eingegeben werden und ein Senden - Button darunter sein. Sobald der Senden - Button gedrückt wurde, soll die Nachricht an alle Subscriber verteilt werden.
 *
 *
 * Subscriber können nun auf ausgehende Nachrichten hören, die über die GUI gesendet werden.
 *
 * Erstellen Sie drei Subscriber, die wie folgende Logik implementieren:
 * - der erste Subscriber soll alle eingehenden Nachrichten in der Konsole ausgeben.
 * - der zweite Subscriber soll nur Nachrichten in der Konsole ausgeben, in der das Wort "Corona" vorkommt.
 * - der dritte Subscriber soll nur Nachrichten in einer Datei "subscriber2.txt" abspeichern, die genau 10 Wörter enthalten.
 */
}
