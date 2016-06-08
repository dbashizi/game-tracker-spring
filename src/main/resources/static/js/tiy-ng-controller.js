angular.module('TIYAngularApp', [])
   .controller('ChocolateController', function($scope, $http, $timeout) {

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

   		$scope.makeArray = function(arraySize) {
   		    var returnArray = [];
   		    for (var i = 0; i < arraySize; i++) {
   		        returnArray.push(i);
   		    }
   		    return returnArray;
   		}

   		$scope.hardcodedSolution = function() {
   		    $scope.processing = !$scope.processing;
   		    console.log("before hardcoding::" + JSON.stringify($scope.chocolateSolution));
   		    $scope.tempSolution = {};
   		    $scope.tempSolution.hasSolution = true;
   		    $scope.tempSolution.bigs = 3;
   		    $scope.tempSolution.smalls = 3;
            $scope.numBigs = $scope.tempSolution.bigs;
            $scope.numSmalls = $scope.tempSolution.smalls;

   		    $scope.chocolateSolution.smalls = 0;
   		    $scope.chocolateSolution.bigs = 0;
   		    $scope.chocolateSolution.hasSolution = true;
   		    console.log("after hardcoding::" + JSON.stringify($scope.tempSolution));

//   		    $scope.chocolateRequest.smalls = $scope.chocolateRequest.smalls - $scope.chocolateSolution.smalls;
//   		    $scope.chocolateRequest.bigs = $scope.chocolateRequest.bigs - $scope.chocolateSolution.bigs;

            $scope.processing = false;
            $scope.processSolutionSmalls();
            $scope.processSolutionBigs();
   		}

   		$scope.processSolutionSmalls = function() {
   		    console.log("processSolutionSmalls ...");
   		    if ($scope.tempSolution.smalls <= 0) {
   		        console.log("Done processing solution!");
   		        return;
   		    }
   		    $scope.tempSolution.smalls--;
   		    $scope.chocolateRequest.smalls--;
   		    $scope.chocolateSolution.smalls++;
   		    $timeout($scope.processSolutionSmalls, 1000);
   		}

   		$scope.processSolutionBigs = function() {
   		    console.log("processSolutionBigs ...");
   		    if ($scope.tempSolution.bigs <= 0) {
   		        console.log("Done processing solution!");
   		        return;
   		    }
   		    $scope.tempSolution.bigs--;
   		    $scope.chocolateRequest.bigs--;
   		    $scope.chocolateSolution.bigs++;
   		    $timeout($scope.processSolutionBigs, 1000);
   		}

   		$scope.getChocolateSolution = function() {
   		    $scope.chocolateSolution.hasSolution = false;
   		    $scope.processing = true;

   			console.log("About to get the solution to the chocolate problem " + JSON.stringify($scope.chocolateRequest));

   			console.log("User on the client side is " + JSON.stringify($scope.user));
   			console.log("Chocolate Request on the client side is " + JSON.stringify($scope.chocolateRequest));

			$http.post("/getChocolateSolution.json", $scope.chocolateRequest)
				.then(
				    function successCallback(response) {
                        console.log(response.data);
                        if (!response.data.hasSolution) {
                            console.log("no solution");
                            $scope.processing = false;
                            alert("No Solution");
                            return;
                        }
                        console.log("Adding data to scope");
                        $scope.chocolateSolution.smalls = 0;
                        $scope.chocolateSolution.bigs = 0;
                        $scope.chocolateSolution.hasSolution = true;
                        $scope.processing = false;
                        $scope.numBigs = response.data.bigs;
                        $scope.numSmalls = response.data.smalls;
                        $scope.tempSolution = response.data;
                        $scope.processSolutionSmalls();
                        $scope.processSolutionBigs();
				    },
				    function errorCallback(response) {
				        $scope.processing = false;
				        console.log("Unable to get data");
				    });
   		};

   		console.log("Chocolate Controller ...");
    	$scope.name = "Cookie Monster";

    	console.log("Initializing data ...");

    	$scope.chocolateRequest = {};
    	$scope.chocolateRequest.bigs = 7;
    	$scope.chocolateRequest.smalls = 12;
    	$scope.chocolateRequest.kilos = 41;

    	$scope.chocolateSolution = {};
    	$scope.chocolateSolution.hasSolution = false;
    	$scope.chocolateSolution.smalls = -1;
    	$scope.chocolateSolution.bigs = -1;

    	$scope.processing = false;
	})

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

   			console.log("User on the client side is " + JSON.stringify($scope.user));
   			console.log("newGame on the client side is " + JSON.stringify($scope.newGame));
   			$scope.newGame.user = $scope.user; 

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