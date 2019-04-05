const httpServer = require('./server/http.js');
const username = sessionStorage.getItem("username");
/**
 * 风险识别业务逻辑接口
 * date: 2019-3-18 pm
 * auther: liuchanghai
 * verion: 0.1.0
 */
// 数据库表
const table = "riskIdentify";
// 表字段
const tableFileds = ["formid","level","source","risk","plan","damage","l","e","d","c","remarks"]
/**
 * 获取风险识别列表数据
 */
export let list = function (callback){
    console.log("www: ");
    let data = {
        user: username,
        form: table,
        action: "get",
        fields: tableFileds,
        condition: {
            field: "groupid",
            symbol: "=",
            value: "1"
        }
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
 * 获取一个风险的信息
 */
export let getById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: table,
        action: "get",
        fields: tableFileds,
        condition: {
            field: "formid",
            symbol: "=",
            value: id
        }
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
 * 添加一个风险的信息
 */
export let add = function (riskIdentify, callback){
    console.log("AddRiskIdentify: " + riskIdentify);
    console.log( riskIdentify);
    let data = {
        user: username,
        form: table,
        action: "add",
        fields: [{
            level: riskIdentify.level,
            source: riskIdentify.source,
            risk: riskIdentify.risk,
            plan: riskIdentify.plan,
            damage: riskIdentify.damage,
            l: riskIdentify.l,
            e: riskIdentify.e,
            c: riskIdentify.c,
            d: riskIdentify.d,
            remarks: riskIdentify.remarks
        }],
        condition: null
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
 * 更新一个风险的信息
 */
export let updateById = function (riskIdentify, callback){
    console.log("UpdateById: " + riskIdentify);
    console.log( riskIdentify);
    let data = {
        user: username,
        form: table,
        action: "updateList",
        fields: [{
            level: riskIdentify.level,
            source: riskIdentify.source,
            risk: riskIdentify.risk,
            plan: riskIdentify.plan,
            damage: riskIdentify.damage,
            l: riskIdentify.l,
            e: riskIdentify.e,
            c: riskIdentify.c,
            d: riskIdentify.d,
            remarks: riskIdentify.remarks
        }],
        condition: {
            field: "formid",
            symbol: "=",
            value: riskIdentify.formid
        }
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
 * 删除一个风险的信息
 */
export let deleteById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: table,
        action: "deleteList",
        fields: null,
        condition: [{
            field: "formid",            
            symbol: "=",
            value: id,
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

/**
 * 批量删除风险的信息
 * id_list = [1,2,3]
 */
export let batchDeleteById_list = function(id_list, callback){
    console.log(id_list);
    let data = {
        user: username,
        form: table,
        action: "deleteList",
        fields: null,
        condition: {
            field: "formid",
            symbol: "=",
            value: id_list
        }
    };
    httpServer.request('/form', data, res => {
        console.log(res.data);
        if(1 === res.data.code){
            return typeof callback == 'function' && callback(res.data)
        }
        return typeof callback == 'function' && callback(res.data)
    })
}
