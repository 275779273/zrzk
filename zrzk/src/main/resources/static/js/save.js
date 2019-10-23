// function save(){
//     var f1 = document.getElementById("f1");
//     var $f1 = $("#f1");
//     alert("提交");
//     //ajax提交信息
//     f1.submit(function(){
//         //parent.layer.close(index); //再执行关闭
//         //$.get()
//         $.ajax({
//             async: false,
//             type: "get",
//             url:'http://127.0.0.1:8080/waterMeter/saveTotal',
//             contentType : "application/x-www-form-urlencoded; charset=utf-8",
//             data:f1.serialize(),
//             dataType: "text",
//             success: function (result) {
//                 if(result.success){
//                     alert("添加成功");
//                 }else {
//                     alert("添加失败");
//                 }
//             },
//             error: function () {
//                 alert("添加失败");
//             }
//         })
//     })
// }
function save() {
    var w1 = document.getElementById("w1").value;
    var w2 = document.getElementById("w2").value;
    var w3 = document.getElementById("w3").value;
    var w4 = document.getElementById("w4").value;
    var w5 = document.getElementById("w5").value;
    var g1 = document.getElementById("g1").value;
    var g2 = document.getElementById("g2").value;
    var g3 = document.getElementById("g3").value;
    var g4 = document.getElementById("g4").value;
    var g5 = document.getElementById("g5").value;
    var g6 = document.getElementById("g6").value;
    var xhr1 = new XMLHttpRequest;
    xhr1.open("POST", 'http://192.168.0.220:9090/waterMeter/saveTotal', true);
    //xhr1.open("POST", 'http://127.0.0.1:8080/waterMeter/saveTotal', true);
    xhr1.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr1.onreadystatechange = function () {

        if (xhr1.readyState==4){
            var obj = JSON.parse(xhr1.responseText);
            if(obj.success){
                alert(obj.message);
                window.location.reload();
            }
        }
    };
    if(w1===""){
        w1="0";
    }
    if(w2===""){
        w2="0";
    }
    if(w3===""){
        w3="0";
    }
    if(w4===""){
        w4="0";
    }
    if(w5===""){
        w5="0";
    }
    xhr1.send(g1+"="+ w1 + "&"+g2+"=" + w2 + "&"+g3+"=" + w3 + "&"+g4+"=" + w4 + "&"+g5+"=" + w5 + "&"+g6+"=0");

}

