var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
    $scope.doc = {};
    $scope.tabs = [];
    $scope.friends = [];
    $scope.myValue = null;

    $scope.init = function () {
        $scope.doc = JSON.parse($.cookie('operate_doc'));
        if ($scope.doc) {
            $scope.tabs = [
                ['预约科室', $scope.doc.ksmc],
                ['挂号医生', $scope.doc.czyxm],
                ['科室位置', $scope.doc.mzsbdd],
                ['预约时间', $scope.doc.xq],
                ['上班信息', $scope.doc.zcmc],
                ['挂号费用', $scope.doc.fydj + '元']
            ];
            var openId = $.cookie('wx_openid');
            if (!openId) {
                window.location.href = 'homepage';
                return;
            }
            //var url = "GetFriendinfo?openid=" + openId;
            var url = "http://219.141.78.173/Hospital/GetFriendinfo?openid=" + openId;
            $http.get(url)
                .success(function (data) {
                    $scope.friends = data;
                    if ($scope.friends.length > 0) {
                        $scope.myValue = $scope.friends[0];
                    }
                });
        }
    };
    $scope.init();

    $scope.submitOrder = function () {
        $http({
            method: 'post',
            url: 'fillorder',
            params: {
                type: 'weixin_pay',
                openid: $.cookie('wx_openid'),
                user_name: $scope.myValue.brxm,
                user_id: $scope.myValue.sfzh,
                doc_name: $scope.doc.czyxm
            }
        }).success(function (req) {
            console.log(req);
        })
    }
});