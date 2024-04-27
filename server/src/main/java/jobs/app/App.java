package jobs.app;

import jobs.server.Server;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Server.listen(10008);
    }
}
