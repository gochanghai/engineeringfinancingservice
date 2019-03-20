const httpServer = require('./server/http.js');
const username = sessionStorage.getItem("username");
/**
 * 警告配置业务逻辑接口
 * date: 2019-3-20 pm
 * auther: liuchanghai
 * verion: 0.10
 */

// 数据库表
const tbl_alarmconfig = "alarmconfig";
// 表字段
const tbl_alarmconfig_fields = ["formid","name","type","max_value","min_value"];

/**
 * 获取警告配置列表数据
 */
export let list = function (callback){
    console.log("www: 获取警告配置列表数据");
    let data = {
        user: username,
        form: tbl_alarmconfig,
        action: "get",
        fields: tbl_alarmconfig_fields,
        condition: [{
            field: "groupid",
            symbol: "=",
            value: 1           
        }]
    };
    httpServer.request('/form', data, res => {
        console.log(res.data);
        if(1 === res.data.code){
            return typeof callback == 'function' && callback(res.data);
        }
        return typeof callback == 'function' && callback(res.data);
    })
}

/**
 * 获取一个警告配置
 */
export let getById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_alarmconfig,
        action: "get",
        fields: tbl_alarmconfig_fields,
        condition: [{
            field: "formid",
            symbol: "=",
            value: id            
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
 * alarmconfig = { .... }
 * 添加一个警告配置的信息
 */
export let add = function (alarmconfig, callback){
    console.log("www: " + alarmconfig);
    let data = {
        user: username,
        form: tbl_alarmconfig,
        action: "add",
        fields: [{
            name: alarmconfig.name,
            type: alarmconfig.address,
            max_value: alarmconfig.max_value,
            min_value: alarmconfig.min_value,
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
 * alarmconfig = { .... }
 * 更新一个警告配置的信息
 */
export let updateById = function (alarmconfig, callback){
    console.log("www: " + alarmconfig);
    let data = {
        user: username,
        form: tbl_alarmconfig,
        action: "updateList",
        fields: [{
            name: alarmconfig.name,
            type: alarmconfig.address,
            max_value: alarmconfig.max_value,
            min_value: alarmconfig.min_value,
        }],
        condition: [{
            field: "formid",            
            symbol: "=",
            value: alarmconfig.id,
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
 * id 
 * 删除一个警告配置的信息
 */
export let deleteById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_alarmconfig,
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
 * 批量删除警告配置的信息
 * id_list = [1,2,3]
 */
export let batchDeleteById_list = function(id_list, callback){
    console.log(id_list);
    let data = {
        user: username,
        form: tbl_alarmconfig,
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