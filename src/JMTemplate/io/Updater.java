package JMTemplate.io;

import arc.Core;
import arc.Net;
import arc.util.Log;
import arc.util.serialization.Jval;

import static JMTemplate.Vars.*;

public class Updater {
    private static Integer temp = null;

    public int versionCheck(){
        Core.net.httpGet("https://raw.githubusercontent.com/" + repositoryURL + "/mod.json", res -> {
            if (res.getStatus() == Net.HttpStatus.OK) {
                Jval jval = Jval.read(res.getResultAsString());
                temp = jval.getInt("version", 1);
            }} , error -> {});

        while (temp == null){}
        int version = temp;
        temp = null;

        return version;
    }
}
