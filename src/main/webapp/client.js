var app = angular.module('app', []);

app.factory('ChatService', function() {

    var service = {};

    service.connect = function() {
        if(service.websocket) { return; }

        var websocket = new WebSocket("ws://localhost:8080/com-byteslounge-websockets-1.0-SNAPSHOT/websocket");

        websocket.onopen = function(event) {
            console.log("Connection succesful function from Controller");
            service.callback('Connection established');
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
        console.log("Hi from Subscribe - Service" + callback.nickname + callback.text);
        console.log("Send() function from Service");
        service.callback = callback;
    }

    return service;
});


 app.controller('AppCtrl',['$scope', 'ChatService', function($scope, ChatService) {

    $scope.messages = [{'nickname':'',
                        'text':''}
    ];
      ChatService.subscribe(function(message) {

        console.log("Hi from Subscribe - controller" + message.text);
        $scope.messages.push(message);
        $scope.$apply();
    });

    $scope.connect = function() {
        console.log("Connect() function from Controller");
        ChatService.connect();
    }

    $scope.send = function() {
        $scope.nickname = '';
        $scope.text = '';

        console.log("Send() function from Controller" );
//        console.log($scope.message);
        ChatService.send(JSON.stringify($scope.message));

        console.log($scope.message);
      }
}]);