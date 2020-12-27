package JMTemplate.core;

import arc.Events;

import arc.Input;
import arc.util.Log;
import arc.util.serialization.Jval;
import mindustry.game.EventType.*;
import mindustry.mod.Mod;

import JMTemplate.io.*;

import java.io.InputStream;

import static JMTemplate.Vars.*;

@SuppressWarnings("unused")
public class Main extends Mod {
    public Main(){
        Events.on(ClientLoadEvent.class, e -> {
            onlineMode = new SocketManager().connectionCheck("www.google.com",80);
            if(onlineMode) {
                this.updateCheck();
            }
        });

        Events.on(WorldLoadEvent.class, e -> {

        });
    }

    @Override
    public void init(){
    }

    @Override
    public void loadContent(){
    }

    private void updateCheck(){
        InputStream IS = getClass().getResourceAsStream("/mod.json");
        Jval jval = Jval.read(String.valueOf(IS));
        int currentVersion = jval.getInt("version",1);
        if(onlineMode) {
            int repoVersion = new Updater().versionCheck();
            if(currentVersion < repoVersion) {
                Log.info("@updater.newversion");
            }
        } else {
            Log.info("@updater.notonline");
        }
    }
}
