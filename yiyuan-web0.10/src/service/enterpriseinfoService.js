const httpServer = require('./server/http.js');
const username = sessionStorage.getItem("username");
/**
 * 企业信息业务逻辑接口
 * date: 2019-3-18 pm
 * auther: liuchanghai
 * verion: 0.10
 */

//  企业信息表
const tbl_group = "group"
//  企业信息表字段
const tbl_group_fileds = ["id","group_no","type","name","credit_code","address","phone","representative","worker_num",
"manage_num","safety_manager","covered_area","established_time","principal","registered_capital","fixed_assets","last_year_turnover"];
// 组织架构表
const tbl_group_worker = "worker"
// 组织架构字段
const tbl_group_worker_fileds = ["formid","group","name","position","duty","account","phone","cellphone","grade","memo"];
/**
 * 获取企业信息
 */
export let getEnterpriseInfo = function (callback){
    console.log("www: " );
    let groupNo = 1;
    let data = {
        user: username,
        form: tbl_group,
        action: "get",
        fields: tbl_group_fileds,
        condition: {
            field: "formid",
            symbol: "=",
            value: groupNo
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
 * 获取组织架构
 */
export let getUserList = function(callback){
    console.log("www: ");
    console.log("www: 获取组织架构");
    let group = 1;
    let data = {
        user: username,
        form: tbl_group_worker,
        action: "get",
        fields: tbl_group_worker_fileds,
        condition: {
            field: "group",
            symbol: "=",
            value: group
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
 * user = {username:'', phone: ', companyName:'' .... }
 * 添加组织成员
 */
export let addUser = function(user, callback){
    console.log("AddUser: " + user);
    console.log(user);
    let data = {
        user: username,
        form: tbl_group_worker,
        action: "add",
        fields: [{
            group: user.group,
            name: user.name,
            position: user.position,
            duty: user.duty,
            account: user.account,
            phone: user.phone,
            cellphone: user.cellphone,
            grade: user.grade,
            memo: user.memo
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
 * user = {username:'', phone: ', companyName:'' .... }
 * 添加组织成员
 */
export let getUserById = function(formid, callback){
    console.log("www: " + user);
    let data = {
        user: username,
        form: tbl_group_worker,
        action: "get",
        fields: tbl_group_worker_fileds,
        condition: {
            field: "formid",
            symbol: "=",
            value: formid
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
 * user = { id:'', name: '', group: '' .... }
 * 更新组织成员
 */
export let updateUserById = function(user, callback){
    console.log("UpdateUserById: " + user);
    console.log(user);
    let data = {
        user: username,
        form: tbl_group_worker,
        action: "updateList",
        fields: [{
            group: user.group,
            name: user.name,
            position: user.position,
            duty: user.duty,
            account: user.account,
            phone: user.phone,
            cellphone: user.cellphone,
            grade: user.grade,
            memo: user.memo
        }],
        condition: [{
            field: "formid",
            value: user.formid,
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


/**
 * userId 
 * 删除组织成员
 */
export let deleteUser = function(userId, callback){
    console.log("www: " + userId);
    let data = {
        user: username,
        form: tbl_group_worker,
        action: "deleteList",
        fields: null,
        condition: [{
            field: "formid",
            value: userId,
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
