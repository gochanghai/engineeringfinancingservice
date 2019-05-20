/**
 * 导航菜单工具类
 * auther: liuchanghai
 * date: 2019-03-23 00:16:00
 */

// 获取用户角色ID
const userRoleId = localStorage.getItem("role");
// 获取用户角色ID
const authenStatus = localStorage.getItem("authenStatus");
 /**
  * userRoleId: 1: 工程公司，2：资金方 3：融资人 0：平台方用户
  */
 export let getUserMenus = function () {
     console.log('userRoleId' + userRoleId);
     console.log('authen');
     console.log(authenStatus);
    if('0' === userRoleId){
        let menus = [
            {
                icon: 'el-icon-lx-home',
                index: 'authen-manager',
                title: '认证管理'
            },
            {
                icon: 'el-icon-lx-home',
                index: 'engineeringcompany-list',
                title: '工程公司管理'
            },
            {
                icon: 'el-icon-lx-home',
                index: 'financecompany-list',
                title: '资金方管理'
            },
            {
                icon: 'el-icon-lx-newsfill',
                index: 'eng-loan',
                title: '工程贷',
                subs: [
                    {
                        index: 'engineeringloan-credit-list',
                        title: '授信申请管理',
                    },
                    {
                        index: 'engineeringloan-loan-apply-list2',
                        title: '放款申请管理',
                    }
                ]
            },
            {
                icon: 'el-icon-lx-file',
                index: 'file',
                title: '文件管理',
                subs: [
                    {
                        index: 'file-list',
                        title: '文件列表',
                    },
                    {
                        index: 'file-preview',
                        title: '文件预览',
                    }
                ]
            },
            {
                icon: 'el-icon-lx-home',
                index: 'product-list',
                title: '金融产品管理'
            },
        ]
        return menus;
    }

    if('1' === userRoleId){
        let userType = localStorage.getItem("userType");
        let menus = null;
        if('1' === userType){
            menus = [
                {
                    icon: 'el-icon-lx-home',
                    index: '/',
                    title: '首页'
                },
                {
                    icon: 'el-icon-lx-profile',
                    index: 'my-business-manager',
                    title: '我的商务经理'
                },
                {
                    icon: 'el-icon-lx-newsfill',
                    index: 'eng-loan',
                    title: '工程贷',
                    subs: [
                        {
                            icon: 'el-icon-lx-home',
                            index: 'engineeringloan-credit-ec-list1',
                            title: '授信申请管理'
                        },
                    ]
                },
                {
                    icon: 'el-icon-lx-text',
                    index: 'project',
                    title: '工程管理',
                    subs: [
                        {
                            index: 'project-list',
                            title: '可授信项目',
                        },
                        {
                            index: 'fire-project-list',
                            title: '消防工程',
                        },
                        {
                            index: 'mainten-priject-list',
                            title: '维保工程',
                        },
                    ]
                },
                {
                    icon: 'el-icon-lx-commentfill',
                    index: 'message-list',
                    title: '消息'
                },
                {
                    icon: 'el-icon-lx-home',
                    index: 'childcompany-list',
                    title: '我的分公司'
                },
                {
                    icon: 'el-icon-lx-settings',
                    index: 'setting',
                    title: '设置',
                    subs: [
                        {
                            index: 'setting-changepassword',
                            title: '修改密码',
                        },
                        {
                            index: 'setting-changeheadimg',
                            title: '修改头像',
                        },
                        {
                            index: 'setting-usermanager',
                            title: '用户管理',
                        },
                        {
                            index: 'my-info-ec',
                            title: '我的信息',
                        }
                    ]
                },
            ]
        }else{
            menus = [
                {
                    icon: 'el-icon-lx-home',
                    index: '/',
                    title: '首页'
                },
                {
                    icon: 'el-icon-lx-profile',
                    index: 'my-business-manager',
                    title: '我的商务经理'
                },
                {
                    icon: 'el-icon-lx-newsfill',
                    index: 'eng-loan',
                    title: '工程贷',
                    subs: [
                        // {
                        //     icon: 'el-icon-lx-home',
                        //     index: 'engineeringloan-credit-ec-list1',
                        //     title: '授信申请管理'
                        // },
                        {
                            icon: 'el-icon-lx-home',
                            index: 'engineeringloan-credit-ec-list2',
                            title: '授信审批管理'
                        },
                        {
                            icon: 'el-icon-lx-home',
                            index: 'engineeringloan-loan-apply-list1',
                            title: '放款申请管理'
                        },
                    ]
                },
                {
                    icon: 'el-icon-lx-text',
                    index: 'project',
                    title: '工程管理',
                    subs: [
                        {
                            index: 'project-list',
                            title: '可授信项目',
                        },
                        {
                            index: 'fire-project-list',
                            title: '消防工程',
                        },
                        {
                            index: 'mainten-priject-list',
                            title: '维保工程',
                        },
                    ]
                },
                {
                    icon: 'el-icon-lx-commentfill',
                    index: 'message-list',
                    title: '消息'
                },
                {
                    icon: 'el-icon-lx-home',
                    index: 'childcompany-list',
                    title: '我的分公司'
                },
                {
                    icon: 'el-icon-lx-settings',
                    index: 'setting',
                    title: '设置',
                    subs: [
                        {
                            index: 'setting-changepassword',
                            title: '修改密码',
                        },
                        {
                            index: 'setting-changeheadimg',
                            title: '修改头像',
                        },
                        {
                            index: 'setting-usermanager',
                            title: '用户管理',
                        },
                        {
                            index: 'enterprise-info',
                            title: '企业信息',
                        }
                    ]
                },
            ]
        }
        return menus;
    }

    if('2' === userRoleId){
        let menus = [
            {
                icon: 'el-icon-lx-friend',
                index: 'mycustomer-list',
                title: '我的客户'
            },
            {
                icon: 'el-icon-lx-home',
                index: 'engineeringloan-credit-list4',
                title: '授信批复列表'
            },
            {
                icon: 'el-icon-lx-home',
                index: 'engineeringloan-loan-list3',
                title: '放款批复列表'
            },
            {
                icon: 'el-icon-lx-commentfill',
                index: 'message-list',
                title: '消息'
            },
        ]
        return menus;
    }

    if('3' === userRoleId && 3 != authenStatus){
        let menus = [
            {
                icon: 'el-icon-lx-home',
                index: 'home2',
                title: '首页'
            },
            {
                icon: 'el-icon-lx-sort',
                index: 'my-project',
                title: '我的项目'
            },
            {
                icon: 'el-icon-lx-vipcard',
                index: 'my-credit',
                title: '我的授信',
                subs: [
                    {
                        index: 'my-credit-el',
                        title: '工程贷',
                    },
                    {
                        index: 'my-credit-hl',
                        title: '房易贷',
                    }
                ]
            },
            {
                icon: 'el-icon-lx-rechargefill',
                index: 'my-loan',
                title: '我的放款',
                subs: [
                    {
                        index: 'my-loan-el',
                        title: '工程贷',
                    },
                    {
                        index: 'my-loan-hl',
                        title: '房易贷',
                    }
                ]
            },
            {
                icon: 'el-icon-lx-commentfill',
                index: 'message-list',
                title: '消息'
            },
            {
                icon: 'el-icon-lx-settings',
                index: 'setting',
                title: '设置',
                subs: [
                    {
                        index: 'setting-changepassword',
                        title: '修改密码',
                    },
                    {
                        index: 'setting-changeheadimg',
                        title: '修改头像',
                    },
                    {
                        index: 'businessmanager-authen',
                        title: '认证信息',
                    }
                ]
            },
        ]
        return menus;
    }else{
        return null
    }
    
}