package socket;

import java.io.IOException;

public interface IServer {

    void handleConnections();

    void start() throws IOException;

    void stop();

}
