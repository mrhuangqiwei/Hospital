var classId = $.cookie('class_id');

new Vue({
    el: '#app',
    data: {swpb: [], xwpb: []},
    beforeCreate: function () {
        if (!classId) {
            return;
        }
        var url = "yspb?ksbm=" + classId;
        var _self = this;
        $.get(url, function (data, status) {
            if (status === 'success') {
                var infos = eval('(' + data + ')')
                _self.swpb = infos.swpb;
                _self.xwpb = infos.xwpb;
            } else {
                alert("请求失败！");
            }
        })
    }
});