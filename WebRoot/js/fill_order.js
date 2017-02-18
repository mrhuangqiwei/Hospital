var openId = $.cookie('wx_openid');
if (!openId) {
    window.location.href = 'homepage';
}

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
                ['挂号费用', $scope.doc.ghfy + '元']
            ];
            var url = "http://pd.nnxzyyy.com/Hospital/GetFriendinfo?openid=" + openId;
            $http.get(url)
                .success(function (data) {
                    $scope.friends = data;
                    if ($scope.friends.length > 0) {
                        $scope.myValue = $scope.friends[0];
                    }
                });
        }
    };

    $scope.getParams = function () {
        var url = "fillorder";
        $http({
            method: 'post',
            url: url,
            params: {type: 'get_jsapi_params'}
        }).success(function (req) {
            $scope.jsapiTicket = req.jsapiTicket;
            wx.config({
                debug: true,
                appId: req.appid,
                timestamp: req.timeStamp,
                nonceStr: req.nonceStr,
                signature: req.signature,
                jsApiList: ['chooseWXPay']
            });
        })
    };

    $scope.submitOrder = function () {
        var url = "fillorder";
        $http({
            method: 'post',
            url: url,
            params: {
                type: 'weixin_pay',
                openid: $.cookie('wx_openid'),
                user_name: $scope.myValue.brxm,
                user_id: $scope.myValue.sfzh,
                doc_name: $scope.doc.czyxm,
                money: $scope.doc.ghfy
            }
        }).success(function (req) {
            wx.chooseWXPay({
                timestamp: 0,
                nonceStr: req.nonce_str,
                package: req.prepay_id,
                signType: 'MD5',
                paySign: req.sign,
                success: function (res) {
                    window.location.href = 'homepage';
                }
            });
        })

    };

    $scope.getParams();
    $scope.init();
});