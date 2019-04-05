const httpServer = require('./server/http.js');
const username = sessionStorage.getItem("username");
/**
 * 应急管理业务逻辑接口
 * date: 2019-3-18 pm
 * auther: liuchanghai
 * verion: 0.10
 */

// 数据库表
const tbl_emergencymanage = "emergencymanage";
// 表字段
const tbl_emergencymanage_fields = ["formid","division","command","name","position","phone"];

/**
 * 获取应急管理列表数据
 */
export let list = function (callback){
    console.log("www: 获取应急管理列表数据");
    let data = {
        user: username,
        form: tbl_emergencymanage,
        action: "get",
        fields: tbl_emergencymanage_fields,
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
 * 获取一个应急管理
 */
export let getById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_emergencymanage,
        action: "get",
        fields: tbl_emergencymanage_fields,
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
 * emergencymanage = { .... }
 * 添加一个应急管理的信息
 */
export let add = function (emergencymanage, callback){
    console.log("www: " + emergencymanage);
    let data = {
        user: username,
        form: tbl_emergencymanage,
        action: "add",
        fields: [{
            name: emergencymanage.name,
            division: emergencymanage.address,
            position: emergencymanage.remarks,
            command: emergencymanage.command,
            phone: emergencymanage.phone
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
 * emergencymanage = { .... }
 * 更新一个应急管理的信息
 */
export let updateById = function (emergencymanage, callback){
    console.log("www: " + emergencymanage);
    let data = {
        user: username,
        form: tbl_emergencymanage,
        action: "updateList",
        fields: [{
            name: emergencymanage.name,
            division: emergencymanage.address,
            position: emergencymanage.remarks,
            command: emergencymanage.command,
            phone: emergencymanage.phone
        }],
        condition: [{
            field: "formid",            
            symbol: "=",
            value: emergencymanage.id,
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
 * 删除一个应急管理的信息
 */
export let deleteById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_emergencymanage,
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
 * 批量删除应急管理的信息
 * id_list = [1,2,3]
 */
export let batchDeleteById_list = function(id_list, callback){
    console.log(id_list);
    let data = {
        user: username,
        form: tbl_emergencymanage,
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