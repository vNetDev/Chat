    var myapp = angular.module('chatMod', []);

    myapp.controller('ChatController', ['$scope','ChatService', function ($scope,ChatService) {

        $scope.messages = [];
        ChatService.subscribe(function(message) {

            console.log("Hi from Subscribe - controller" + message.toJson);
            if (message.data = true) $scope.ONLINE = true;

            $scope.messages.push(message);
            $scope.$apply();
        });

        $scope.connect = function() {
            console.log("Connect() function from Controller");
            ChatService.connect();
        }

        $scope.send = function() {

            console.log("Send() function from Controller" );
//        console.log($scope.message);
            ChatService.send(JSON.stringify($scope.message));

            console.log($scope.message);
        }
    }]);

