const httpServer = require('./server/http.js');
const storage = require('./server/storage.js');
const md5Util = require('./server/md5.js');
const sendingSystem = require('./server/sendingSystem.js');
const dateUtil = require('./server/date.js');

/**
 * 用户登录接口
 * date: 2019-3-18 pm
 * auther: liuchanghai
 * verion: 0.1.0
 */ 
export let login = function (username, password, callback){
    // console.log("www: " + username + password + dateUtil.formatTime());
    let data = {
        user: null,
        datetime: dateUtil.formatTime(),
        action: 'query',
        platform: sendingSystem._Agent().platform, //操作系统..
        browser: sendingSystem._Agent().brand + " " + sendingSystem._Agent().model + " " + sendingSystem._Agent().version, //浏览器...
        fields: {
            userAccount: username,
            userPassword: password,
            usermd5pswd: md5Util.hexMD5(password),
        },
        condition: null
    };
    console.log(data);
    httpServer.request('/user', data , res =>{
        console.log("www: " + username + password + dateUtil.formatTime());
        console.log(res.data);
        if(1 === res.data.code){
            // 把用户信息存入session缓存
            console.log("把用户信息存入session缓存")
            sessionStorage.setItem("username", username);
            sessionStorage.setItem("nickname", username);
        }
        return typeof callback == 'function' && callback(res.data);
    });        
}

/**
 * 用户登出接口
 */
export let logout = function (username, password){
    console.log("www: " + username + password);

    // 清空缓存
    
    return "ok"
}

/**
 * 修改密码接口
 */
export let changepassword = function (oldPassword, newPassword, callback){
    console.log("www: " + oldPassword + newPassword);    
    let data = {
        user: sessionStorage.getItem("username"),
        datetime: dateUtil.formatTime(),
        platform: sendingSystem._Agent().platform, //操作系统..
        browser: sendingSystem._Agent().brand + " " + sendingSystem._Agent().model + " " + sendingSystem._Agent().version, //浏览器...
        action: "modify",
        fields: {
            userName: sessionStorage.getItem("username"), //用户名
            userAccount: sessionStorage.getItem("username"),
            userPassword: md5Util.hexMD5(oldPassword), //用户旧密码
            newPassword: md5Util.hexMD5(newPassword) //用户新密码
        },
        condition: null
    };
    httpServer.request('/user', data, res => {
        return typeof callback == 'function' && callback({ code: res.data.code })
    });
}


/**
 * 用户注册
 */
export let registration = function (username, password, nickname, callback){
    console.log("www: " + username + oldPassword + newPassword);    
    let data = {
        datetime: dateUtil.formatTime(),
        platform: sendingSystem._Agent().platform, //操作系统..
        browser: sendingSystem._Agent().brand + " " + sendingSystem._Agent().model + " " + sendingSystem._Agent().version, //浏览器...
        action: "add",
        fields: {
            userName: nickname, //用户名
            userAccount: username,
            userPassword: md5Util.hexMD5(password),
        },
        condition: null
    };
    httpServer.request('/user', data, res =>{
        return typeof callback == 'function' && callback(res.data)
    });
}


