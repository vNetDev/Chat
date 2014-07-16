var app = angular.module('app', []);

app.factory('ChatService', function() {

    var service = {};

    service.connect = function() {
        if(service.websocket) { return; }

        var websocket = new WebSocket("ws://localhost:8080/com-byteslounge-websockets-1.0-SNAPSHOT/websocket");

        websocket.onopen = function(event) {
            service.callback('Connection established');
        };

        websocket.onerror = function(event) {
            service.callback('Error');
        }

        websocket.onmessage = function(text) {

         var date = typeof(message.time) === 'string' ? parseInt(message.time) : message.time;
         service.callback(text.data);
        };

        service.websocket = websocket;
    }

    service.send = function(name, text) {
        alert(name + ":" + text);
        service.websocket.send(name +" : "+ text);
    }

    service.subscribe = function(callback) {
        service.callback = callback;
    }

    return service;
});


function AppCtrl($scope, ChatService) {
    $scope.messages = [];

    ChatService.subscribe(function(message) {
        $scope.messages.push(message);
        $scope.$apply();
    });

    $scope.connect = function() {
        ChatService.connect();
    }

    $scope.send = function() {
        ChatService.send($scope.name,$scope.text);
        $scope.text = "";
     }
}