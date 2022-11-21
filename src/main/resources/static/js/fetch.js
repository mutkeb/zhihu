//  封装所有api操作
export {contentApi, loginApi, commentApi, postApi, skip};

//  查找内容接口

async function contentApi(topic) {
    const url = "http://localhost:8080/content/searchContent?keyword=" + topic;
    return await fetch(url, {
        method: "post"
    }).then(function (response) {
        return response.json();
    });
}

//  登录更新信息接口

async function loginApi() {
    const url = "http://localhost:8080/user/avatar";
    return await fetch(
        url, {
            method: "post"
        }).then(function (response) {
        return response.json();
    });
}

//  查找文章评论接口

async function commentApi(refId) {
    const url = "http://localhost:8080/comment/query?refId=" + refId;
    return await fetch(url, {
        method: "post"
    }).then(function (response) {
        return response.json();
    });
}

//  发布评论接口

async function postApi(refId, userId, parentId, content) {
    const url = "http://localhost:8080/comment/post?refId=" + refId + "&userId=" + userId + "&parentId=" + parentId + "&content=" + content;
    return await fetch(url, {
        method: "post"
    }).then(function (response) {
        return response;
    });
}

//  跳转内容界面接口

function skip(refId) {
    const skipUrl = "http://localhost:8080/content/skip?refId=" + refId;
    window.location.href = skipUrl;
}
