new Vue({
    el: '#app',
    data: {todos: []},
    beforeCreate: function () {
        // var url = "Ksbmservlet";
        var url = "http://219.141.78.173/Hospital/Ksbmservlet";
        var _self = this;
        $.get(url, function (data, status) {
            if (status === 'success') {
                _self.todos = eval(data);
            } else {
                alert("请求失败！");
            }
        })
    }
});