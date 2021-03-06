package org.fdiez.websocket_demo;

import org.fdiez.websocket_demo.db.db;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.websocket.OnClose;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;


public class WebSocketHandler extends AbstractWebSocketHandler {
    Dictionary<String, WebSocketSession> get_seession= new Hashtable<>();
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        var command=message.toString().split(",");
        String game_id;

        switch (command[0]){
            case "get_id":
                if(session.isOpen()){
                    session.sendMessage(
                            //TODO: send the new row id ely hwa id el game ely hyt3ml fyh save le data el game
                            new TextMessage("")
                    );
                }
                session.close(CloseStatus.GOING_AWAY);
                break;
            case "wait":
                WebSocketSession player1=session;
                game_id=command[1];
                get_seession.put(session.getId(),session);
                //TODO: set player 1 of gem_id in db
                break;
            case "get_into":
                game_id = command[1];
                //TODO shof el id sa7 walla la
                boolean mawgod;//true law ah false law la2
                if(mawgod) {
                    //TODO get player1 id and put it in the next var
                    String player1_id;
                    get_seession.put(session.getId(), session);
                    WebSocketSession player1_session = get_seession.get(player1_id);
                    session.sendMessage(new TextMessage("gogogo"));
                    player1_session.sendMessage(new TextMessage("gogogo"));
                }else{
                    session.sendMessage(new TextMessage("false"));
                }
                break;
            case "set_player1":
                game_id=command[1];
                //TODO update player1 session id
                get_seession.put(session.getId(),session);
                break;
            case "set_player2":
                game_id=command[1];
                //TODO update player2 session id
                get_seession.put(session.getId(),session);
                break;
            case "m":
                String player=session.getId();
                //todo get peer id ya3ny 7tshof player dah player2 or 1 w tgeb ely byl3b m3ah w t7oto f next var
                String peer_id;//dah el next var
                String mov=command[1]+","+command[2];
                //todo ba3d ma 3rft hwa player kam def var mov  fel movment array bta3o
                get_seession.get(peer_id).sendMessage(message);
                break;

        }
        if(message.toString().startsWith("s")){
            //todo tro7 el db tgeb el peer player ely m3ah zy ma kont shar7 mn shwaya w t7oto f next var
            String peer_player;
            get_seession.get(peer_player).sendMessage(message);
        }

        db.test(message.toString());
    }
    @OnClose
    public void onClose(WebSocketSession session) {
        get_seession.remove(session.getId());
    }
}
