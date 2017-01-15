$(function () {
    //$.cookie('userinfo', 'I am a god!');
    var ua = navigator.userAgent.toLowerCase();
    var isWeixin = ua.indexOf('micromessenger') != -1;
    var userInfo = $.cookie('wx_openid');
    var app1 = new Vue({
        el: '#app',
        data: {
            message: userInfo
        }
    });
});