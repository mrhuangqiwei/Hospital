new Vue({
    el: '#app',
    data: {todos: []},
    beforeCreate: function () {
        var url = "Ksbmservlet";
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