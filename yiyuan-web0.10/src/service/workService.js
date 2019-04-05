const httpServer = require('./server/http.js');
const username = sessionStorage.getItem("username");
/**
 * 作业业务逻辑接口
 * date: 2019-3-18 pm
 * auther: liuchanghai
 * verion: 0.10
 */
// 数据库表
const tbl_work = "work";
// 表字段
const tbl_work_fields = ["formid","status","type","name","work_time","address","work_person","work_manager","remarks"];

/**
 * 获取历史作业数据
 */
export let list = function (callback){
    console.log("www: 获取历史作业数据");
    let data = {
        user: username,
        form: tbl_work,
        action: "get",
        fields: tbl_work_fields,
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
 * 获取一个历史作业数据
 */
export let getById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_work,
        action: "get",
        fields: tbl_work_fields,
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
 * worker = { .... }
 * 添加作业数据
 */
export let add = function (worker, callback){
    console.log("www: " + worker);
    let data = {
        user: username,
        form: tbl_work,
        action: "add",
        fields: [{
            type: worker.group,
            name: worker.name,
            address: worker.address,
            work_person: worker.work_person,
            remarks: worker.remarks,
            status: worker.status,
            work_time: worker.work_time,
            work_manager: worker.work_manager
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
 * worker = { .... }
 * 更新一个作业数据
 */
export let updateById = function (worker, callback){
    console.log("www: " + worker);
    let data = {
        user: username,
        form: tbl_work,
        action: "updateList",
        fields: [{
            type: worker.group,
            name: worker.name,
            address: worker.address,
            work_person: worker.work_person,
            remarks: worker.remarks,
            status: worker.status,
            work_time: worker.work_time,
            work_manager: worker.work_manager
        }],
        condition: [{
            field: "formid",            
            symbol: "=",
            value: worker.id,
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
 * 删除一个作业数据
 */
export let deleteById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_work,
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

