var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
    $scope.doc = {};
    $scope.tabs = [];
    $scope.friends = [];

    $scope.init = function () {
        $scope.doc = JSON.parse($.cookie('operate_doc'));
        if ($scope.doc) {
            $scope.tabs = [
                ['预约科室', $scope.doc.ksmc],
                ['挂号医生', $scope.doc.czyxm],
                ['科室位置', $scope.doc.mzsbdd],
                ['预约时间', $scope.doc.xq],
                ['上班信息', $scope.doc.zcmc]
            ];
            var openId = $.cookie('wx_openid');
            if (!openId) {
                window.location.href = 'homepage';
                return;
            }
            $http.get("GetFriendinfo?openid=" + openId)
                .success(function (data) {
                    $scope.friends = data;
                });
        }
    };
    $scope.init();
});