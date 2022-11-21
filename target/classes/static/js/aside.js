//  侧边模块

import {contentApi,skip} from "/js/fetch.js";

//  搜索发现栏加载
const div = document.querySelectorAll(".searchAndFind ul div");
const li2 = document.querySelectorAll(".searchAndFind li");

init();
function init() {
    var promise = contentApi("");
    promise.then(myjson=>{
        const data = myjson.data;
        for (let i = 0; i < 11; i++) {
            const title = data[i+1].title;
            const refId = data[i+1].id;
            if (i >= 0 && i <= 3) {
                div[i].innerText = title;
            } else {
                li2[i].innerText = title;
            }
            li2[i].addEventListener("click", function () {
                skip(refId);
            });
        }
    });
}