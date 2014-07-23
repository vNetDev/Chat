angular.module('chatMod').factory('ChatService',function() {

    var service = {};

    service.connect = function() {
        if(service.websocket) { return; }

        var websocket = new WebSocket("ws://localhost:8080/com-byteslounge-websockets-1.0-SNAPSHOT/websocket");

        var ONLINE;

        websocket.onopen = function(event) {
            console.log("Connection succesful function from Controller");
            ONLINE = true;
            service.callback(ONLINE);
         };

        websocket.onerror = function(event) {
            service.callback('Error');
        }

        websocket.onmessage = function(event) {

            console.log("OnMessage function from Service" + event);

            service.callback(JSON.parse(event.data));
        };

        service.websocket = websocket;
    }

    service.send = function(message) {
        console.log("Send() function from Service");
        console.log("Nickname is:"+ message.nickname + "Content is:"+message.text);
        service.websocket.send(message);
    }

    service.subscribe = function(callback) {
        console.log("Hi from Subscribe - Service" + callback.nickname + callback.text
                                                  + callback.time );
        console.log("Send() function from Service");
        service.callback = callback;
    }

    return service;
})
