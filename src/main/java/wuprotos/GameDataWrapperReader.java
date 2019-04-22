package wuprotos;

import com.google.protobuf.util.JsonFormat;
import WUProtos.Data.Client.ClientGameDataWrapperOuterClass.ClientGameDataWrapper;
import WUProtos.Networking.Platform.Responses.DownloadGameMasterTemplatesResponseOuterClass.DownloadGameMasterTemplatesResponse;

import java.io.*;


public class GameDataWrapperReader {

    public ClientGameDataWrapper read() throws Exception {
        try (InputStream is = getClass().getResourceAsStream("/GameDataWrapper.bytes")) {
            ClientGameDataWrapper response = ClientGameDataWrapper.parseFrom(is);
            System.out.println("Templates Read Success! Count : " + response.getMessagesCount());
            return response;
        }
    }
    public void writeJSON(ClientGameDataWrapper response, OutputStream os) throws IOException {
        JsonFormat.Printer printer = JsonFormat.printer();
        try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
            printer.appendTo(response, writer);
        }
    }

    public static void main(String... args) throws Exception {
        GameDataWrapperReader reader = new GameDataWrapperReader();
        ClientGameDataWrapper response = reader.read();
        try (FileOutputStream fos = new FileOutputStream("GameDataWrapper.json")) {
            reader.writeJSON(response, fos);
        }
    }
}
