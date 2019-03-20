const httpServer = require('./server/http.js');
const username = sessionStorage.getItem("username");
/**
 * 器材设备业务逻辑接口
 * date: 2019-3-18 pm
 * auther: liuchanghai
 * verion: 0.10
 */

// 数据库表
const tbl_equipment = "equipment";
// 表字段
const tbl_equipment_fields = ["formid","status","type","name","number","address","manager","remarks","callphone","risks"];

/**
 * 获取器材设备列表数据
 */
export let list = function (callback){
    console.log("www: 获取历史作业数据");
    let data = {
        user: username,
        form: tbl_equipment,
        action: "get",
        fields: tbl_equipment_fields,
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
 * 获取一个器材设备的信息
 */
export let getById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_equipment,
        action: "get",
        fields: tbl_equipment_fields,
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
 * equipment = { .... }
 * 添加一个器材设备的信息
 */
export let add = function (equipment, callback){
    console.log("www: " + equipment);
    let data = {
        user: username,
        form: tbl_equipment,
        action: "add",
        fields: [{
            type: equipment.group,
            name: equipment.name,
            address: equipment.address,
            remarks: equipment.remarks,
            status: equipment.status,
            manager: equipment.manager,
            risks: equipment.risks,
            callphone: equipment.callphone
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
 * equipment = { .... }
 * 更新一个器材设备的信息
 */
export let updateById = function (equipment, callback){
    console.log("www: " + equipment);
    let data = {
        user: username,
        form: tbl_equipment,
        action: "updateList",
        fields: [{
            type: equipment.group,
            name: equipment.name,
            address: equipment.address,
            remarks: equipment.remarks,
            status: equipment.status,
            manager: equipment.manager,
            risks: equipment.risks,
            callphone: equipment.callphone
        }],
        condition: [{
            field: "formid",            
            symbol: "=",
            value: equipment.id,
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
 * 删除一个器材设备的信息
 */
export let deleteById = function (id, callback){
    console.log("www: " + id);
    let data = {
        user: username,
        form: tbl_equipment,
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
        form: tbl_equipment,
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
