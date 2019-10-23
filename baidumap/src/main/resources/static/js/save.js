// function save() {
//     var url = "http://api.map.baidu.com/place/v2/search";
//     //var f1 = $("#f1");
//     var param = {"query": "天安门", "region": "北京", "output": "json", "ak": "rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz"}
//     console.log(param);
//     $.get(url, param, function (data) {
//         console.log(data);
//     });
// }

function save() {
    //获取表单的所有参数(json对象)
    var arr = $("#f1").serializeArray();//序列化表单
    var obj = {};//分配内存空间
    for (var i = 0; i < arr.length; i++) {//数据类型为"自定义类的字段名=数据"后台会自动对数据进行匹配
        obj[arr[i].name] = arr[i].value;
    }
    console.log(obj);
    $.ajax({
        async: false,
        type: "get",
        url: 'http://api.map.baidu.com/place/v2/search',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: obj,
        dataType: "jsonp",
        success: function (result) {
            $("#p1").text(JSON.stringify(result));
            console.log(result);
        },
        error: function () {
            alert("请求失败");
        }
    })

    $.ajax({
        async: false,
        type: "get",
        url: 'http://api.map.baidu.com/place/v2/search',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"query": "天安门", "region": "北京", "output": "json", "ak": "rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz","location":"39.90960456049752,116.3972282409668"},
        dataType: "jsonp",
        success: function (result) {
            $("#p2").text(JSON.stringify(result));
            console.log(result);
        },
        error: function () {
            alert("请求失败");
        }
    })

    $.ajax({
        async: false,
        type: "get",
        url: 'http://api.map.baidu.com/place/v2/search',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"uid": "b66cebc049d3ef0a2202629c", "scope": "2", "output": "json", "ak": "rZvIGI1RUk4d2DjflRtRFuiLTRGkKgvz"},
        dataType: "jsonp",
        success: function (result) {
            $("#p3").text(JSON.stringify(result));
            console.log(result);
        },
        error: function () {
            alert("请求失败");
        }
    })
}