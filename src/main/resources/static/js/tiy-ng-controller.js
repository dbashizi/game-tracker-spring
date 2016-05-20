angular.module('TIYAngularApp', [])
   .controller('SampleController', function($scope, $http) {

   		$scope.getGames = function() {
   			console.log("About to go get me some data!");
   			$scope.name = "James";

			$http.get("/games.json")
				.then(
				    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.games = response.data;
				    },
				    function errorCallback(response) {
				        console.log("Unable to get data");
				    });
   		};

   		$scope.addGame = function() {
   			console.log("About to add the following game " + JSON.stringify($scope.newGame));

			$http.post("/addGame.json", $scope.newGame)
				.then(
				    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.games = response.data;
				    },
				    function errorCallback(response) {
				        console.log("Unable to get data");
				    });
   		};

   		$scope.getUsers = function() {
   			console.log("About to go get me some data!");

			$http.get("/users.json")
				.then(
				    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.users = response.data;
				    },
				    function errorCallback(response) {
				        console.log("Unable to get data");
				    });
   		};

   		$scope.deleteGame = function(gameID) {
   		    console.log("About to delete game with ID " + gameID);

			$http.get("/deleteGame.json?gameID=" + gameID)
				.then(
				    function success(response) {
                        console.log(response.data);
                        console.log("Game deleted");
                        $scope.games = response.data;
				    },
				    function error(response) {
				        console.log("Unable to delete game");
				    });
       	};

   		$scope.toggleGame = function(gameID) {
   		    console.log("About to toggle game with ID " + gameID);

			$http.get("/toggleGame.json?gameID=" + gameID)
				.then(
				    function success(response) {
                        console.log(response.data);
                        console.log("Game toggled");
                        $scope.games = response.data;
				    },
				    function error(response) {
				        console.log("Unable to toggle game");
				    });
       	};

   		console.log("SampleController ...");
    	$scope.name = "James";

    	console.log("Initializing data ...");
//    	alert("About to initialize the controller");
//    	$scope.getGames();
//    	$scope.getUsers();

    	$scope.newGame = {};
	});