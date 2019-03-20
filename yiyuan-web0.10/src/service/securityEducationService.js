const httpServer = require('./server/http.js');
const username = sessionStorage.getItem("username");
/**
 * 安全教育业务逻辑接口
 * date: 2019-3-18 pm
 * auther: liuchanghai
 * verion: 0.10
 */
//  安全培训数据库
const tbl_securityeducation = "securityeducation";
// 安全培训表字段
const fileds = ["id","url","name","status","time"];

/**
 * 获取安全教育列表数据
 */
export let list = function (callback){
    console.log("www: ");
    let data = {
        user: username,
        form: tbl_securityeducation,
        action: "get",
        fields: fileds,
    };
    httpServer.request('/form', data, res => {
        console.log(res.data);
        if(1 === res.data.code){
            return typeof callback == 'function' && callback(res.data)
        }
        return typeof callback == 'function' && callback(res.data)
    })
}

/**
 * 获取一个安全教育信息
 */
export let getById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_securityeducation,
        action: "get",
        fields: fileds,
        condition: [{
            field: "formid",
            value: id,
            symbol: "="
        }]
    };
    httpServer.request('/form', data, res => {
        console.log(res.data);
        if(1 === res.data.code){
            return typeof callback == 'function' && callback(res.data)
        }
        return typeof callback == 'function' && callback(res.data)
    })
}
