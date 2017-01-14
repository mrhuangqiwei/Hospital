$(function () {
    //$.cookie('userinfo', 'I am a god!');
    var userInfo = $.cookie('userinfo');
    var app1 = new Vue({
        el: '#app',
        data: {
            message: userInfo
        }
    });
    alert('userInfo:' + userInfo);
});