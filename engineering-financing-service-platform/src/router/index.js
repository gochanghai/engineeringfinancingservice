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
                {
                    path: '/home1',
                    component: resolve => require(['../components/page/home1.vue'], resolve),
                    meta: { title: 'Home1' }
                },

                // online start

                {
                    path: '/credit-info-details',
                    component: resolve => require(['../components/page/engineeringloan/CreditInfoDetails.vue'], resolve),
                    meta: { title: 'ciedit-info-dateils' }
                },
                {
                    path: '/project-details',
                    component: resolve => require(['../components/page/project/Details.vue'], resolve),
                    meta: { title: 'project-dateils' }
                },
                {
                    path: '/businessmanager-add',
                    component: resolve => require(['../components/page/businessmanager/Add.vue'], resolve),
                    meta: { title: 'businessmanager-authen' }
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
                    path: '/engineeringcompany-add',
                    component: resolve => require(['../components/page/engineeringcompany/Add.vue'], resolve),
                    meta: { title: 'engineeringcompany-add' }
                },
                {
                    path: '/childcompany-add',
                    component: resolve => require(['../components/page/engineeringcompany/AddChildCompany.vue'], resolve),
                    meta: { title: 'childcompany-add' }
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
                {
                    path: '/engineeringloan-credit-apply-info',
                    component: resolve => require(['../components/page/engineeringloan/ApplyInfo.vue'], resolve),
                    meta: { title: 'engineeringloan-credit-apply-info' }
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
