new Vue({
    el: '#app',
    data: {todos: []},
    beforeCreate: function () {
        var url = "https://api.github.com/events";
        var _self = this;
        $.get(url, function (data, status) {
            if (status === 'success') {
                _self.todos = data;
                console.log(data)
            } else {
                alert("请求失败！");
            }
        })
    }
});