//  内容模块
export {update}

import {contentApi, commentApi, postApi} from "/js/fetch.js";
import {updateComment} from "/js/comment.js";
import {skip} from "/js/fetch.js";
import {avatar} from "/js/login.js";

//  登录标记
const loginUser = avatar();
//  登录框
const mask = document.querySelector(".mask");

init();

function init() {
    update("");
}

//  创建内容节点
function createContentDiv(data, commentNum) {
    //  获得标题
    const title = data.title;
    //  获得内容
    const content = data.detail;
    //  获得作者信息
    const user = data.user;
    const avatar = user.avatar;
    const name = user.userName;
    //  获得媒体
    const media = data.media;
    //  获得创建时间
    let time = data.gmtCreated;
    time = time.substring(5, 10);
    //  获得点赞数
    const favor = data.favor;

    //  创建li节点
    var li = document.createElement("li");
    //  创建title节点
    const titleDiv = document.createElement("div");
    titleDiv.className = "title";
    titleDiv.innerHTML = title;
    //  需要给title加上监视器能够进行跳转
    titleDiv.addEventListener("click", function () {
        skip(refId);
    });
    //  创建user节点
    const userDiv = document.createElement("div");
    userDiv.className = "user";
    userDiv.style = `
                display:none;
                margin-top:10px;
            `;
    const userImg = document.createElement("img");
    const userNameDiv = document.createElement("div");
    userImg.src = avatar;
    userImg.style = `
                display:block;
                width:20px;
                height:20px;
                margin-right:5px;
            `;
    userNameDiv.innerText = name;
    userDiv.appendChild(userImg);
    userDiv.appendChild(userNameDiv);
    //  创建detail节点
    const detailDiv = document.createElement("div");
    detailDiv.className = "detail";
    const right = document.createElement("div");
    right.className = "right";
    const content1 = document.createElement("div");
    content1.className = "content";
    content1.innerHTML = content;
    const open = document.createElement("open");
    open.className = "open";
    const div = document.createElement("div");
    div.innerText = "阅读全文";
    const img = document.createElement("img");
    img.src = "/img/arrow.png";
    open.appendChild(div);
    open.appendChild(img);
    right.appendChild(content1);
    right.appendChild(open);
    const picDiv = document.createElement("div");
    picDiv.className = "picture";
    if (media != null) {
        picDiv.innerHTML = media;
        picDiv.style.width = "30%";
        right.style.width = "70%";
    } else {
        picDiv.innerHTML = "";
        picDiv.style.width = "0px";
        right.style.width = "100%";
    }
    detailDiv.appendChild(picDiv);
    //  创建other节点
    var other = document.createElement("div");
    other.className = "other";
    other.innerHTML = ` <div class="agree">
                    <div>
                        <img src="/img/agree.png">
                    </div>
                    <div><span class="text">喜欢</span>&nbsp;&nbsp;<span class="num">${favor}</span></div>
                </div>
                <div class="disagree">
                    <img src="/img/disagree.png">
                </div>
                <div class="comment">
                    <div>
                        <img src="/img/comment.png">
                    </div>
                    <div class="num">${commentNum}条评论</div>
                </div>
                <div class="time">
                    ${time}
                </div>`
    detailDiv.appendChild(right);
    li.appendChild(titleDiv);
    li.appendChild(userDiv);
    li.appendChild(detailDiv);
    li.appendChild(other);
    return li;
}

async function initData(data) {
    let n = 0;
    const ul = document.querySelector(".ul");
    for (let i = 0; i < data.length; i++) {
        //  获得作品ID
        const refId = data[i].id;
        //  利用fetch,获得作品的评论
        var promise = commentApi(refId);
        promise.then(myjson2 => {
            let commentNum = 0;
            let comments;
            comments = myjson2.data;
            if (comments != undefined) {
                commentNum = comments.length;
                for (let j = 0; j < comments.length; j++) {
                    if (comments[j].children != null) {
                        commentNum += comments[j].children.length;
                    }
                }
            }
            const li = createContentDiv(data[i], commentNum);
            ul.appendChild(li);
            //  评论点击
            const tempTitle = document.querySelectorAll(".other .comment .num");
            updateComment(comments, commentNum, refId, tempTitle[tempTitle.length - 1]);
            n++;
            if (n == data.length) {
                eclipse();
                agree();
                disagree();
            }
        })
    }
}


//  根据搜索框来更新内容
function update(topic) {
    const ul = document.querySelector(".ul");
    //  先将默认内容清空
    ul.innerHTML = "";
    var promise = contentApi(topic);
    promise.then(myjson => {
        const data = myjson.data;
        if (data.length == 0) {
            ul.innerHTML = ` <div class="empty">
                <img src="/img/none.png">
                <div>未搜索到相关内容</div>
                </div>`;
        } else {
            initData(data);
        }
    });
}


//  展开全文按钮
function eclipse() {
    const open = document.querySelectorAll(".open");
    const content = document.querySelectorAll(".ul .content");
    const titlePic = document.querySelectorAll(".detail .picture");
    const right = document.querySelectorAll("main .detail .right");
    const user = document.querySelectorAll(".user");
    var isopen = new Array(open.length);
    for (let i = 0; i < open.length; i++) {
        isopen[i] = false;
        open[i].addEventListener("click", function () {
            isopen = !isopen;
            if (!isopen) {
                open[i].innerHTML = "<div style='left: 685px;position: absolute;'>收起</div><img src=\"/img/collect.png\" style='left: 755px;position: absolute;'>";
                open[i].style = `
                    padding-left:5px;
                    text-align :right;  
                    padding-right:40px;
                `;
                right[i].style.width = "100%";
                content[i].style = `display:block`;
                user[i].style.display = "flex";
                titlePic[i].style.display = "none";
            } else {
                content[i].style = ` /* 隐藏超出部分 */
          overflow: hidden;
          /* 文本超出就用省略号 */
          text-overflow: ellipsis;
          /* 把对象作为弹性伸缩盒子模型显示 */
          display: -webkit-box;
          /* WebKit内核的浏览器的私有属性，设置文本超出2行就用省略号 */
          -webkit-line-clamp: 3;
          /* WebKit内核的浏览器的私有属性，设置或检索伸缩盒对象的子元素的排列方式 */
          -webkit-box-orient: vertical;`;
                open[i].innerHTML = "<div>阅读全文</div> <img src=\"/img/arrow.png\">";
                open[i].style = `
                    text-align:left;
                `
                if (titlePic[i].innerHTML != "") {
                    right[i].style.width = "70%";
                }
                user[i].style.display = "none";
                titlePic[i].style.display = "block";
            }
        });
    }

}


//  赞同模块
function agree() {
    const agree = document.querySelectorAll(".agree");
    const nums = document.querySelectorAll(".num");
    const text = document.querySelectorAll(".text");
    const like = new Array(agree.length);
    for (let i = 0; i < agree.length; i++) {
        like[i] = false;
        agree[i].addEventListener("click", function () {
            loginUser.then(value => {
                if (value == undefined) {
                    alert("请先登录");
                    mask.style.display = "block";
                } else {
                    if (like[i]) {
                        //  切换到不喜欢
                        var num = nums[i].innerHTML;
                        nums[i].innerHTML = parseInt(num) - 1;
                        text[i].innerHTML = "喜欢";
                        agree[i].style.backgroundColor = "#eaeefd"
                        like[i] = false;
                    } else {
                        //  切换到喜欢
                        var num = nums[i].innerHTML;
                        nums[i].innerHTML = parseInt(num) + 1;
                        text[i].innerHTML = "已喜欢";
                        agree[i].style.backgroundColor = "white"
                        like[i] = true;
                    }
                }
            });
        });
    }
}


//  不赞同模块
function disagree() {
    const disagree = document.querySelectorAll(".disagree");
    const dislike = new Array(disagree.length);
    for (let i = 0; i < disagree.length; i++) {
        dislike[i] = false;
        disagree[i].addEventListener("click", function () {
            loginUser.then(value => {
                if (value == undefined) {
                    alert("请先登录");
                    mask.style.display = "block";
                } else {
                    if (dislike[i]) {
                        //  切换为不踩
                        disagree[i].style.backgroundColor = "#e6f0fc";
                        dislike[i] = false;
                    } else {
                        //  切换为踩
                        disagree[i].style.backgroundColor = "blue";
                        dislike[i] = true;
                    }
                }
            });
        });
    }
}
