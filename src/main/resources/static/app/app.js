(function(angular) {
    'use strict';

    var showtime = angular.module("showtime", ['ui.ace','ngSanitize'])

    .controller('TaskCtrl',function($scope,$location, $http) {

    $scope.testCases = [{id: null}];

    $scope.addNewTC = function() {
        var newItemNo = $scope.testCases.length+1;
        $scope.testCases.push({'id':null});
    };

    $scope.removeTC = function() {
        var lastItem = $scope.testCases.length-1;
        $scope.testCases.splice(lastItem);
    };

    $scope.getAll = function (newPage,pageSize) {
        $http.get('/rest/tasks/?page='+newPage+'&size='+pageSize)
            .then(function success(data) {
                    $scope.tasks = data.data.content;

                },
                function error (response) {
                    $scope.message = '';
                    if (response.status === 404){
                        $scope.errorMessage = 'User not found!';
                    }
                    else {
                        $scope.errorMessage = "Error getting user!";
                    }
                });
    };

    $scope.getAllCategories = function (){
        $http.get("/rest/categories").then(
            function success(response){
                $scope.catList = response.data;
            }
        )
    };

    $scope.getTask = function (id) {

        $http.get('/rest/tasks/' +  id)
            .then(function success(response) {
                    $scope.task = response.data;
                },
                function error (response) {
                    $scope.message = '';
                    if (response.status === 404){
                        $scope.errorMessage = 'User not found!';
                    }
                    else {
                        $scope.errorMessage = "Error getting user!";
                    }
                });
    };

    $scope.deleteTask = function(id) {
        $http.delete('/rest/tasks/' + id);
    };

    $scope.saveTask = function() {
        var task_ = $scope.task;
        task_.testCases = [];
        task_.testCases = $scope.testCases;
        $http.post('/rest/tasks/', task_).then(
            function success(response) {
                $scope.message = "Task successfully created";
                $location.path('/tasks');
            },
            function error (response) {
                $scope.message = '';
                console.log('error');
                $scope.errorMessage = 'Error!';
            }
        );
    };

})

    .controller('AceCtrl', ['$scope','$http','$sanitize', function ($scope,$http,$sanitize) {
        // The modes
        $scope.modes = ['Java'];
        $scope.mode = $scope.modes[0];
        $scope.submission={};
        $scope.response={};
        var _session;

        $scope.aceLoaded = function(_editor) {
            _session = _editor.getSession();
        };
        $scope.modeChanged = function () {
            console.log($scope.mode.toLowerCase());
            _session.setMode('ace/mode/' + $scope.mode.toLowerCase());
        };

        $scope.submitSource = function (id) {
            $scope.loading = true;
            $scope.submission.taskId = id;
            $http.post('/checker/submission', $scope.submission).then(
                function success(response) {
                    $scope.errorMessage = '';
                    $scope.response = response.data;
                    $scope.loading = false;
                    $scope.$broadcast('dataloaded');
                    console.log($scope.response)
                },
                function error (response) {
                    $scope.errorMessage = 'Error!';
                    $scope.loading = false;
                }
            );
        };
        $scope.$on('dataloaded', function () {
            $('#tabs').tab();
        })

    }])
})(window.angular);