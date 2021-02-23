package Subs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class sub3 implements Subscriber {
    @Override
    public void notifyMessage(String message) {
        int i = 1;

        for (int n = 0; n <= message.length(); ++n) {

            if (message.charAt(n) == ' ') {
                ++i;
            }

            if (i == 10) {
                i = 0;

                FileWriter fw;

                try {

                    fw = new FileWriter("subscriber2.txt");

                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(message);
                    System.out.println("gespeicherter Text in subscriber2.txt :" + message);

                    bw.flush();
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
