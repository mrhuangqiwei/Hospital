var classId = $.cookie('class_id');

var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
    $scope.swpb = [];
    $scope.xwpb = [];

    $scope.getData = function () {
        if (!classId) {
            return;
        }
        var url = "yspb?ksbm=" + classId;
        $http.get(url).success(function (data) {
            $scope.swpb = data.swpb;
            $scope.xwpb = data.xwpb;
        });
    };
    $scope.getData();

    $scope.onDoctorClick = function (doc) {
        $.cookie('operate_doc', JSON.stringify(doc));
        window.location.href = 'fill_order.html';
    };
});