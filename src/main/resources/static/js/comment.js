export {updateComment}
//  评论模块

//  发布评论按钮
import {avatar} from "/js/login.js";
import {commentApi, postApi} from "/js/fetch.js";

const publishBtn = document.querySelector(".publish .button");
//  评论输入框
const input = document.querySelector(".publish input");
//  评论框的取消
const cancel2 = document.querySelector(".cancel2");
//  评论框
const mask2 = document.querySelector(".mask2");
//  评论框列表
const ul = document.querySelector(".mask2 ul")
//  评论框标题
const title = document.querySelector(".mask2 .title");
//  登录框
const mask = document.querySelector(".mask");
//  时间排序按钮
const sort = document.querySelector(".sort");
//  登录标记
const loginUser = avatar();
//  记录发表评论的父评论Id，默认为0
let parentId = 0;
//  记录各个父评论的Id数组
let id;
//  记录各个父评论标签的数组
let answerObject;
//  记录操作的文章的评论标签
let tempt;
//  记录操作的文章Id
let tempRefId;

init();

function init() {
    setListener();
}

function setListener() {
    //  评论发布按钮
    publishBtn.addEventListener("click", function () {
        const content = input.value;
        loginUser.then(value => {
            if (value == undefined) {
                alert("请先登录");
                mask.style.display = "block";
            } else {
                if (content == null || content == "") {
                    alert("输入内容不能为空");
                } else {
                    post(tempRefId, value.id, value.userName, value.avatar, parentId, content);
                    //  清空input框
                    input.value = "";
                }
            }
        })
    });
    //  评论框取消按钮
    cancel2.addEventListener("click", function () {
        mask2.style.display = "none";
    });
}

//  发布评论
function post(refId, userId, userName, avatar, parentId, content) {
    //  首先在数据库中增加对应的数据
    const result = postApi(refId, userId, parentId, content);
    result.then(value => {
        if (value.status == "200") {
            //   说明是一级评论，直接加就行
            var promise = commentApi(refId);
            promise.then(myjson2=>{
                const data = myjson2.data;
                let num = myjson2.data.length;
                for (let i = 0; i < data.length; i++) {
                    if (data[i].children != null) {
                        num += data[i].children.length;
                    }
                }
                updateComment2(data, num);
                tempt.innerText = `${num}条评论`;
            });
        }
    });
}
//  主界面评论按钮监控
function commentListener(comments, commentNum, refId, title) {
    //  评论回复按钮
    const comment = document.querySelectorAll(".other .comment");
    comment[comment.length - 1].addEventListener("click", function () {
        var promise = commentApi(refId);
        promise.then(myjson2=>{
            const data = myjson2.data;
            let num = myjson2.data.length;
            for (let i = 0; i < data.length; i++) {
                if (data[i].children != null) {
                    num += data[i].children.length;
                }
            }
            updateComment2(data, num);
            answerListener();
        });
        parentId = 0;
        tempRefId = refId;
        tempt = title;
        mask2.style.display = "block";
    })
}

//  更新评论
function updateComment(comments, commentNum, refId, title) {
    commentListener(comments, commentNum, refId, title);
    updateComment2(comments, commentNum);
}

//  返回渲染好的子节点
function createComment(data) {
    //  获得作者信息
    let authorInf = data.author;
    let nameInf = authorInf.userName;
    let avatarInf = authorInf.avatar;
    //  获得时间信息
    let date = data.gmtCreated;
    date = date.substring(5,10);
    //  获得评论内容
    let content = data.content;
    const div = document.createElement("div");
    div.innerHTML = `
                                            <div class="user">
                                                <img class="avatar" src=${avatarInf}>
                                                <div class="name">${nameInf}</div>
                                                <div class="create">${date}</div>
                                            </div>
                                            <div class="content">${content}</div>
                                            <div class="functions">
                                                <div class="good">
                                                    <img src="/img/good.png">
                                                    <div>赞</div>
                                                </div>
                                                <div class="answer">
                                                    <img src="/img/answer.png">
                                                    <div>回复</div>
                                                </div>
                                                <div class="bad">
                                                    <img src="/img/bad.png">
                                                    <div>踩</div>
                                                </div>
                                                <div class="report">
                                                    <img src="/img/report.png">
                                                    <div>举报</div>
                                                </div>
                                            </div> 
                                    `;
    return div;
}
function updateComment2(comments, commentNum) {
    const title = document.querySelector(".mask2 .title");
    title.innerText = `${commentNum}条评论`;
    ul.innerHTML = "";
    let sonNum = 0;
    let n = 0;
    if (comments != undefined) {
        for (let i = 0; i < comments.length; i++) {
            if (comments[i].children != null) {
                n += comments[i].children.length;
            }
        }
        n += comments.length;
        id = new Array(n);
        answerObject = new Array(n);
        for (let k = 0; k < comments.length; k++) {
            id[sonNum] = comments[k].id;
            answerObject[sonNum] = comments[k].author.userName;
            sonNum++;
            //  增加评论标签
            const div = createComment(comments[k]);
            //  获得子节点信息
            const children = comments[k].children;
            if (children != null && children.length > 0) {
                for (let i = 0; i < children.length; i++) {
                    id[sonNum] = children[i].id;
                    answerObject[sonNum] = children[i].author.userName;
                    sonNum++;
                    //  构件子节点
                    const sonDiv = createComment(children[i]);
                    sonDiv.style = `
                        padding-left:40px;
                        padding-top:10px;
                        margin-top:20px;
                        border-top:2px solid #efefef;
                    `;
                    div.appendChild(sonDiv);
                }
            }
            ul.appendChild(div);
        }
    }
}

//  回复按钮设置点击监听器
function answerListener() {
    const answer = document.querySelectorAll(".mask2 .answer");
    const good = document.querySelectorAll(".mask2 .good");
    const bad = document.querySelectorAll(".mask2 .bad");
    const report = document.querySelectorAll(".mask2 .report");
    const like = new Array(answer.length);
    const dislike = new Array(answer.length);
    const rep = new Array(answer.length);
    for (let i = 0; i < answer.length; i++) {
        answer[i].addEventListener("click", function () {
            loginUser.then(value => {
                if (value == undefined) {
                    alert("请先登录");
                    mask.style.display = "block";
                } else {
                    //  点击该回复按钮，就会将父节点的Id设为该评论
                    parentId = id[i];
                    //  给一点提示，显示此时正在回复某人
                    const input = document.querySelector(".publish input");
                    input.setAttribute("placeholder", "回复" + answerObject[i] + ":");
                }
            });
        });
        like[i] = false;
        good[i].addEventListener("click",function () {
            loginUser.then(value => {
                if (value == undefined) {
                    alert("请先登录");
                    mask.style.display = "block";
                } else {
                    if (!like[i]){
                        good[i].innerHTML = `
                        <img src="/img/good.png">
                            <div>已赞</div>`;
                    }else{
                        good[i].innerHTML = `
                        <img src="/img/good.png">
                            <div>赞</div>`;
                    }
                    like[i] = !like[i];
                }
            });
        });
        dislike[i] = false;
        bad[i].addEventListener("click",function () {
            loginUser.then(value => {
                if (value == undefined) {
                    alert("请先登录");
                    mask.style.display = "block";
                } else {
                    if (!dislike[i]){
                        bad[i].innerHTML = `
                        <img src="/img/bad.png">
                            <div>已踩</div>`;
                    }else{
                        bad[i].innerHTML = `
                        <img src="/img/bad.png">
                            <div>踩</div>`;
                    }
                    dislike[i] = !dislike[i];
                }
            });
        });
        rep[i] = false;
        report[i].addEventListener("click",function () {
            loginUser.then(value => {
                if (value == undefined) {
                    alert("请先登录");
                    mask.style.display = "block";
                } else {
                    if (!rep[i]){
                        report[i].innerHTML = `
                        <img src="/img/report.png">
                            <div>已举报</div>`;
                    }else{
                        report[i].innerHTML = `
                        <img src="/img/report.png">
                            <div>举报</div>`;
                    }
                    rep[i] = !rep[i];
                }
            });
        });
    }
}