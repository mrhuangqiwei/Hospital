$(function () {
    var ua = navigator.userAgent.toLowerCase();
    var userInfo = $.cookie('wx_openid');
    var app1 = new Vue({
        el: '#app',
        data: {
            message: userInfo
        }
    });
});