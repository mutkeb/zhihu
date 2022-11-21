//  搜索框部分
import {update} from "/js/main.js";
import {contentApi} from "/js/fetch.js";
//  搜索输入框
const input = document.querySelector(".search input");
//  搜索提示框
const result = document.querySelector(".result");
//  special节点
const special = document.querySelector(".result .special");
//  body节点
const body = document.querySelector("body");
//  回到顶部按钮
const back = document.querySelector(".back");
//  第二栏选项栏
const title = document.querySelectorAll(".second-line>div");
//  历史搜索栏
const li = document.querySelectorAll(".result li");
//  底下的蓝条
const bar = document.querySelectorAll(".second-line .bar");
//  显示的内容
const ul = document.querySelector("main .ul");
//  搜索图标
const searchImg = document.querySelector(".search-icon");
init();

//  初始化函数
function init() {
    setListener();
    setSubject();
    addListener(li);
    searchByHistory(li);
}


//  监听器设置
function setListener() {
    //  获取焦点事件
    input.addEventListener("focusin", function (e) {
        result.style.display = "block";
    });
    //  给一个全局的监控， 点击到别的地方就消失
    body.addEventListener("click", function (e) {
        if (e.target.nodeName != "INPUT" && e.target.nodeName != "IMG") {
            result.style.display = "none";
        }
    });
    //  回到顶部按钮点击事件
    back.addEventListener("click", function () {
        document.documentElement.scrollTop = 0;
    });
    //  搜索图标点击事件
    searchImg.addEventListener("click", function () {
        update(input.value)
    });
    //  键盘搜索事件
    document.onkeydown = function (e) {
        if (e.keyCode == 13) {
            update(input.value)
        }
    };
    //  历史栏点击事件

    //  输入框输入事件
    input.addEventListener("input", function () {
        special.style.display = "block";
        //  调用查看事件接口
        const promise = contentApi(input.value);
        promise.then(myjson => {
            const data = myjson.data;
            //  讲原先内容全部删掉
            if (data != null) {
                result.innerHTML = "";
                //  加入对应的内容
                //  首先获取标题
                for (let i = 0; i < Math.min(data.length, 5); i++) {
                    //  创建一个li节点
                    var li = document.createElement("li");
                    li.innerHTML = data[i].title;
                    result.append(li);
                }
                //  加入最后的两个节点
                var li3 = document.createElement("li");
                li3.className = "special";
                var content = input.value;
                li3.innerHTML = "查看「" + content + "」的搜索结果"
                result.append(li3);
                var li2 = document.createElement("li");
                li2.innerHTML = "举报";
                result.append(li2);
                const lis = document.querySelectorAll(".result li");
                addListener(lis);
                searchByHistory(lis);
            }
        })
    });

}

//  设置标题底下的蓝条效果
function setSubject() {
    //  首先获得所有的标题
    var lastChoose = document.createElement("div");
    bar[0].style.display = "block";
    for (let i = 0; i < title.length - 1; i++) {
        title[i].addEventListener("click", function () {
            bar[0].style.display = "none";
            bar[i].style.display = "block";
            if (lastChoose != bar[i]) {
                lastChoose.style.display = "none";
            }
            if (i != 0) {
                ul.innerHTML = ` <div class="empty">
                <img src="/img/none.png">
                <div>未搜索到相关内容</div>
                </div>`;
            } else {
                update("");
            }
            lastChoose = bar[i];
        });
    }
}

function addListener(li) {
    let flag = false;
    for (let i = 0; i < li.length - 2; i++) {
        li[i].addEventListener("mouseenter", function () {
            li[i].style.backgroundColor = "#f6f3f7";
            li[i].style.color = "#335478"
            li[i].style.fontWeight = "bold";
            var img = document.createElement("img");
            img.src = "/img/cancel2.png";
            li[i].appendChild(img);
            //  设置删除图标的功能
            const result2 = document.querySelector(".result");
            const del = document.querySelectorAll(".result li img");
            var children = result2.children;
            for (let i = 0; i < del.length; i++) {
                del[i].addEventListener("click", function (e) {
                    result2.removeChild(children[i]);
                    flag = true;
                });
            }
        });
        li[i].addEventListener("mouseleave", function () {
            li[i].style.backgroundColor = "white";
            li[i].style.color = "black";
            li[i].style.fontWeight = "normal";
            //  让img标签消逝
            var childNodes = li[i].childNodes;
            li[i].removeChild(childNodes[1]);
        });
    }
}

function searchByHistory(li) {
    for (let i = 0; i < li.length - 2; i++) {
        li[i].addEventListener("click", function () {
            update(li[i].innerText);
        });
    }
}
