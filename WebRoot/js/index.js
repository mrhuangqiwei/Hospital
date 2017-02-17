$(function () {
    //$.cookie('userinfo', 'I am a god!');
    var ua = navigator.userAgent.toLowerCase();
    var isWeixin = ua.indexOf('micromessenger') != -1;
    //$.cookie('wx_openid', 'owEWzwQKO7G_uy4C0X_Wn2boPVI4');
    var userInfo = $.cookie('wx_openid');
    var app1 = new Vue({
        el: '#app',
        data: {
            message: userInfo
        }
    });
});