//  登录模块
export {avatar};

import {loginApi} from "/js/fetch.js";

//  登录框提交按钮
const logBut = document.querySelector(".enter-btn");
//  登录提交表单
const form = document.querySelector(".login-form");
//  登录界面
const mask = document.querySelector(".mask");
//  登录界面取消按钮
const cancel = document.querySelector(".cancel");
//  导航栏右半部分
const right = document.querySelector(".first-line .right");
//  获取login按钮
const loginBut = document.querySelector(".login");

init();

function init() {
    setListener();
}

//  设置监听器
function setListener() {
    //  提交按钮
    logBut.addEventListener("click", function () {
        form.submit();
    });
    //  登录按钮
    loginBut.addEventListener("click", function () {
        mask.style.display = "block";
    });

    //  设置取消按钮
    cancel.addEventListener("click", function () {
        mask.style.display = "none";
    });
}


//  更新头像信息
async function avatar() {
    var promise = loginApi();
    return promise.then(myjson => {
        const data = myjson.data;
        //  更新头像
        if (data != null) {
            //  获得姓名和头像
            const avatarMes = data.avatar;
            const userNameMes = data.userName;
            const login = document.querySelector(".login");
            if (login != undefined) {
                right.removeChild(login);
                //  增加头像
                const avatarIcon = document.createElement("img");
                avatarIcon.src = avatarMes;
                avatarIcon.style = `
                width:30px;
                height:30px;
                display:block;
                margin-top:5px;
            `;
                const nameDiv = document.createElement("div");
                nameDiv.style = `
                margin-left:20px;
                margin-right:10px;
            `;
                nameDiv.innerText = userNameMes;
                right.appendChild(nameDiv);
                right.appendChild(avatarIcon);
                alert("登录成功");
            }
        } else {
            if (myjson.symbol != null && myjson.symbol == 1) {
                alert("登录失败");
            }
        }
        return data;
    });
}
