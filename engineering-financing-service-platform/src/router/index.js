import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' }
                },


                // online start
                {
                    path: '/my-project',
                    component: resolve => require(['../components/page/myproject/List.vue'], resolve),
                    meta: { title: 'my-project' }
                },
                {
                    path: '/home1',
                    component: resolve => require(['../components/page/home/Home1.vue'], resolve),
                    meta: { title: 'Home1' }
                },
                {
                    path: '/home2',
                    component: resolve => require(['../components/page/home/Home2.vue'], resolve),
                    meta: { title: 'Home2' }
                },

                {
                    path: '/credit-info-details',
                    component: resolve => require(['../components/page/engineeringloan/CreditInfoDetails.vue'], resolve),
                    meta: { title: 'credit-info-dateils' }
                },
                {
                    path: '/project-details',
                    component: resolve => require(['../components/page/project/Details.vue'], resolve),
                    meta: { title: 'project-dateils' }
                },
                {
                    path: '/businessmanager-add',
                    component: resolve => require(['../components/page/businessmanager/Add.vue'], resolve),
                    meta: { title: 'businessmanager-add' }
                },
                {
                    path: '/businessmanager-authen',
                    component: resolve => require(['../components/page/businessmanager/Authen.vue'], resolve),
                    meta: { title: 'businessmanager-authen' }
                },
                {
                    path: '/businessmanager-info-details',
                    component: resolve => require(['../components/page/businessmanager/InfoDateils.vue'], resolve),
                    meta: { title: 'businessmanager-info-dateils' }
                },
                {
                    path: '/engineeringcompany-list',
                    component: resolve => require(['../components/page/engineeringcompany/List.vue'], resolve),
                    meta: { title: 'engineeringcompany-list' }
                },
                {
                    path: '/engineeringcompany-add',
                    component: resolve => require(['../components/page/engineeringcompany/Add.vue'], resolve),
                    meta: { title: 'engineeringcompany-add' }
                },
                {
                    path: '/childcompany-list',
                    component: resolve => require(['../components/page/engineeringcompany/ChildList.vue'], resolve),
                    meta: { title: 'childcompany-list' }
                },
                {
                    path: '/childcompany-add',
                    component: resolve => require(['../components/page/engineeringcompany/AddChildCompany.vue'], resolve),
                    meta: { title: 'childcompany-add' }
                },
                {
                    path: '/financecompany-list',
                    component: resolve => require(['../components/page/financecompany/List.vue'], resolve),
                    meta: { title: 'engineeringcompany-list' }
                },
                {
                    path: '/financecompany-add',
                    component: resolve => require(['../components/page/financecompany/Add.vue'], resolve),
                    meta: { title: 'engineeringcompany-add' }
                },
                {
                    path: '/product-add',
                    component: resolve => require(['../components/page/product/Add.vue'], resolve),
                    meta: { title: 'product-add' }
                },
                {
                    path: '/product-add',
                    component: resolve => require(['../components/page/product/Add.vue'], resolve),
                    meta: { title: 'product-add' }
                },
                {
                    path: '/engineeringloan-credit-add',
                    component: resolve => require(['../components/page/engineeringloan/AddCredit.vue'], resolve),
                    meta: { title: 'engineeringloan-credit-add' }
                },
                {
                    path: '/engineeringloan-loan-add',
                    component: resolve => require(['../components/page/engineeringloan/AddLoan.vue'], resolve),
                    meta: { title: 'engineeringloan-loan-add' }
                },
                {   // 工程贷授信申请信息
                    path: '/credit-engloan-info',
                    component: resolve => require(['../components/page/engineeringloan/ApplyInfo.vue'], resolve),
                    meta: { title: 'credit-engloan-info' }
                },
                {
                    path: '/engineeringloan-credit-ec-list1',
                    component: resolve => require(['../components/page/engineeringloan/ECCreditList1.vue'], resolve),
                    meta: { title: 'engineeringloan-credit-ec-list1' }
                },
                {
                    path: '/engineeringloan-credit-ec-list2',
                    component: resolve => require(['../components/page/engineeringloan/ECCreditList2.vue'], resolve),
                    meta: { title: 'engineeringloan-credit-ec-list2' }
                },
                {
                    path: '/engineeringloan-credit-list',
                    component: resolve => require(['../components/page/engineeringloan/CreditList.vue'], resolve),
                    meta: { title: 'engineeringloan-credit-list' }
                },
                {
                    path: '/engineeringloan-loan-apply-list',
                    component: resolve => require(['../components/page/engineeringloan/LoanApplyList.vue'], resolve),
                    meta: { title: 'engineeringloan-loan-apply-list' }
                },
                {
                    path: '/fire-project-list',
                    component: resolve => require(['../components/page/project/FireProjectList.vue'], resolve),
                    meta: { title: 'fire-project-list'}
                },
                {
                    path: '/mainten-priject-list',
                    component: resolve => require(['../components/page/project/FireMaintenanceProjectList.vue'], resolve),
                    meta: { title: 'mainten-priject-list'}
                },
                {
                    path: '/project-list',
                    component: resolve => require(['../components/page/project/ProjectList.vue'], resolve),
                    meta: { title: 'project-list'}
                },
                {
                    path: '/project-add',
                    component: resolve => require(['../components/page/project/AddProject.vue'], resolve),
                    meta: { title: 'project-add'}
                },
                {
                    path: '/project-progress-details',
                    component: resolve => require(['../components/page/project/ProgressDetails.vue'], resolve),
                    meta: { title: 'project-progress-details'}
                },
                {
                    path: '/edit-project-info',
                    component: resolve => require(['../components/page/project/EditProjectInfo.vue'], resolve),
                    meta: { title: 'edit-project-info'}
                },
                {
                    path: '/message-list',
                    component: resolve => require(['../components/page/message/List.vue'], resolve),
                    meta: { title: 'message-list' }
                },
                {
                    path: '/file-list',
                    component: resolve => require(['../components/page/filesystem/List.vue'], resolve),
                    meta: { title: 'message-list' }
                },
                {
                    path: '/file-preview',
                    component: resolve => require(['../components/page/filesystem/Preview.vue'], resolve),
                    meta: { title: 'message-list' }
                },
                {
                    path: '/product-list',
                    component: resolve => require(['../components/page/product/List.vue'], resolve),
                    meta: { title: 'product-list' }
                },
                {
                    path: '/product-info',
                    component: resolve => require(['../components/page/product/Info.vue'], resolve),
                    meta: { title: 'product-info' }
                },
                {
                    path: '/engineeringloan-credit-list4',
                    component: resolve => require(['../components/page/engineeringloan/CreditReplyList.vue'], resolve),
                    meta: { title: 'engineeringloan-credit-list4' }
                },
                {
                    path: '/engineeringloan-loan-list3',
                    component: resolve => require(['../components/page/engineeringloan/LoanReplyList.vue'], resolve),
                    meta: { title: 'engineeringloan-loan-list3' }
                },
                {
                    path: '/mycustomer-list',
                    component: resolve => require(['../components/page/mycustomer/List.vue'], resolve),
                    meta: { title: 'mycustomer-list' }
                },
                {
                    path: '/my-business-manager',
                    component: resolve => require(['../components/page/mybusinessmanager/List.vue'], resolve),
                    meta: { title: 'my-business-manager' }
                },
                {
                    path: '/my-credit-el',
                    component: resolve => require(['../components/page/mycredit/EngCreditList.vue'], resolve),
                    meta: { title: 'my-credit-el' }
                },
                {
                    path: '/my-loan-el',
                    component: resolve => require(['../components/page/myloan/EngLoanList.vue'], resolve),
                    meta: { title: 'my-loan-el' }
                },
                {
                    path: '/loan-apply-info',
                    component: resolve => require(['../components/page/myloan/LoanApplyInfo.vue'], resolve),
                    meta: { title: 'loan-apply-info' }
                },
                {
                    path: '/setting-changepassword',
                    component: resolve => require(['../components/page/setting/ChangePassword.vue'], resolve),
                    meta: { title: 'setting-changepassword' }
                },
                {
                    path: '/setting-changeheadimg',
                    component: resolve => require(['../components/page/setting/ChangeHeadImg.vue'], resolve),
                    meta: { title: 'setting-changeheadimg' }
                },
                {
                    path: '/setting-usermanager',
                    component: resolve => require(['../components/page/setting/UserList.vue'], resolve),
                    meta: { title: 'setting-usermanager' }
                },
                {
                    path: '/authen-manager',
                    component: resolve => require(['../components/page/authenmanager/List.vue'], resolve),
                    meta: { title: 'authen-manager' }
                },
                // onlian end


                {
                    path: '/icon',
                    component: resolve => require(['../components/page/Icon.vue'], resolve),
                    meta: { title: '自定义图标' }
                },
                {
                    path: '/table',
                    component: resolve => require(['../components/page/BaseTable.vue'], resolve),
                    meta: { title: '基础表格' }
                },
                {
                    path: '/tabs',
                    component: resolve => require(['../components/page/Tabs.vue'], resolve),
                    meta: { title: 'tab选项卡' }
                },
                {
                    path: '/form',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve),
                    meta: { title: '基本表单' }
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                    meta: { title: '富文本编辑器' }
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve),
                    meta: { title: 'markdown编辑器' }
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve),
                    meta: { title: '文件上传' }
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 拖拽Dialog组件
                    path: '/dialog',
                    component: resolve => require(['../components/page/DragDialog.vue'], resolve),
                    meta: { title: '拖拽弹框' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: resolve => require(['../components/page/Permission.vue'], resolve),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    component: resolve => require(['../components/page/404.vue'], resolve),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: { title: '403' }
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
