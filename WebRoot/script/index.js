/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var ajax;

function test(obj) {
    obj.style.color = 'red';
}

//function test(obj){
//    window.location.assign("http://localhost/forfree1.2/index.php?c=index&a=index");
//}
function Ajax(){
    ajax=GetXmlHttpObject();    
    if (ajax === null){alert ("Browser does not support HTTP Request");return;} 
    var url="./index?p=auto&a=test"; //给出POST位置
    ajax.onreadystatechange=ajaxReturn; 
    ajax.open("POST",url,true); //POST方法，启用异步通讯
    ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    ajax.send(); //发送数据   

    return;
}

function loginPage() {
    window.location.assign("http://localhost/forfree1.2.1/index.php?g=login&c=login&a=login");
}

function ajaxReturn(){
    if (ajax.readyState===4 || ajax.readyState==="complete"){ //判断服务端处理状态
        document.getElementById('box').innerHTML = ajax.responseText;
        return;
    }
}

function GetXmlHttpObject(){
    var ajax=null;
    // Firefox, Opera 8.0+, Safari
    try{ajax=new XMLHttpRequest();}
    // Internet Explorer
    catch (e){
        try{ajax=new ActiveXObject("Msxml2.XMLHTTP");}
        catch (e){ajax=new ActiveXObject("Microsoft.XMLHTTP");}
    }
    return ajax;
}

