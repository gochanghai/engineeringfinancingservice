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
                },
                {
                    path: '/business-manager-list',
                    component: resolve => require(['../components/page/engineeringcompany/BusinessManagerList.vue'], resolve),
                    meta: { title: '商务经理管理' }
                },
                {
                    path: '/business-manager-add',
                    component: resolve => require(['../components/page/engineeringcompany/BusinessManagerAdd.vue'], resolve),
                    meta: { title: '新增商务经理' }
                },
                {
                    path: '/financier-unauthen',
                    component: resolve => require(['../components/page/financier/FinancierUnauthen.vue'], resolve),
                    meta: { title: '个人认证' }
                },
                {
                    path: '/may-credit-project-list',
                    component: resolve => require(['../components/page/engineeringcompany/MayCreditProjectList.vue'], resolve),
                    meta: { title: '可授信项目管理' }
                },
                {
                    path: '/may-credit-project-add',
                    component: resolve => require(['../components/page/engineeringcompany/AddMayCreditProject.vue'], resolve),
                    meta: { title: '新增可授信项目' }
                },
                {
                    path: '/ec-credit-apply-list',
                    component: resolve => require(['../components/page/engineeringcompany/CreditApplyList.vue'], resolve),
                    meta: { title: '公司授信申请管理' }
                },
                {
                    path: '/ec-credit-apply-list2',
                    component: resolve => require(['../components/page/engineeringcompany/CreditApplyList2.vue'], resolve),
                    meta: { title: '公司授信申请管理' }
                },
                {
                    path: '/ec-loan-apply-list',
                    component: resolve => require(['../components/page/engineeringcompany/LoanApplyList.vue'], resolve),
                    meta: { title: '公司放款申请管理' }
                },
                {
                    path: '/sm-credit-apply-list',
                    component: resolve => require(['../components/page/systemmanagement/CreditApplyList.vue'], resolve),
                    meta: { title: '平台授信申请管理' }
                },
                {
                    path: '/sm-loan-apply-list',
                    component: resolve => require(['../components/page/systemmanagement/LoanApplyList.vue'], resolve),
                    meta: { title: '平台放款申请管理' }
                },
                {
                    path: '/my-credit-project-list',
                    component: resolve => require(['../components/page/financier/FinancierCreditProjectList.vue'], resolve),
                    meta: { title: '我的授信项目列表' }
                },
                {
                    path: '/my-loan-apply-list',
                    component: resolve => require(['../components/page/financier/FinancierLoanApplyList.vue'], resolve),
                    meta: { title: '我的贷款申请列表' }
                },
                {
                    path: '/credit-Reply-list',
                    component: resolve => require(['../components/page/moneyside/MoneySideCreditReplyList.vue'], resolve),
                    meta: { title: '授信批复列表' }
                },
                {
                    path: '/loan-apply-Reply-list',
                    component: resolve => require(['../components/page/moneyside/MoneySideLoanApplyReplyList.vue'], resolve),
                    meta: { title: '放款申请批复列表' }
                },
                {
                    path: '/add-project-credit-apply',
                    component: resolve => require(['../components/page/financier/AddProjectCreditApply.vue'], resolve),
                    meta: { title: '新增项目授信申请' }
                },
                {
                    path: '/project-credit-apply-detail',
                    component: resolve => require(['../components/page/financier/ProjectCreditApplyDetail.vue'], resolve),
                    meta: { title: '项目授信申请详情' }
                },
                {
                    path: '/add-project-info',
                    component: resolve => require(['../components/page/engineeringcompany/AddProjectInfo.vue'], resolve),
                    meta: { title: '添加项目信息' }
                },
                {
                    path: '/add-loan-apply',
                    component: resolve => require(['../components/page/financier/AddLoanApply.vue'], resolve),
                    meta: { title: '新增贷款申请' }
                },
                {
                    path: '/financier-home',
                    component: resolve => require(['../components/page/financier/Home.vue'], resolve),
                    meta: { title: '融资人首页' }
                },
                {
                    path: '/encom-home',
                    component: resolve => require(['../components/page/engineeringcompany/Home.vue'], resolve),
                    meta: { title: '工程公司首页' }
                },
                {
                    path: '/moneyside-home',
                    component: resolve => require(['../components/page/moneyside/Home.vue'], resolve),
                    meta: { title: '工程公司首页' }
                },
                {
                    path: '/system-home',
                    component: resolve => require(['../components/page/systemmanagement/Home.vue'], resolve),
                    meta: { title: '工程公司首页' }
                },
                {
                    path: '/login2',
                    component: resolve => require(['../components/page/Login2.vue'], resolve),
                    meta: { title: '登录界面' }
                },
                {
                    path: '/engineeringcompany-list',
                    component: resolve => require(['../components/page/systemmanagement/EngineeringCompanyList.vue'], resolve),
                    meta: { title: '工程公司管理' }
                },
                {
                    path: '/find-eng-com-info',
                    component: resolve => require(['../components/page/systemmanagement/FindEngComInfo.vue'], resolve),
                    meta: { title: '用户管理' }
                },
                {
                    path: '/financial-company-list',
                    component: resolve => require(['../components/page/systemmanagement/FinancialCompanyList.vue'], resolve),
                    meta: { title: '资金方管理' }
                },
                {
                    path: '/financial-product-list',
                    component: resolve => require(['../components/page/systemmanagement/FinancialProductList.vue'], resolve),
                    meta: { title: '金融产品管理' }
                },
                {
                    path: '/addengineeringcompany',
                    component: resolve => require(['../components/page/systemmanagement/AddEngineeringCompany.vue'], resolve),
                    meta: { title: '金融产品管理' }
                },
                {
                    path: '/mystep',
                    component: resolve => require(['../components/page/systemmanagement/Mystep.vue'], resolve),
                    meta: { title: '我的Step' }
                },
                {
                    path: '/progress-detail',
                    component: resolve => require(['../components/page/engineeringcompany/ProjectProgressDetail.vue'], resolve),
                    meta: { title: '项目进度明细' }
                },
                {
                    path: '/addFundCompany',
                    component: resolve => require(['../components/page/systemmanagement/addFundCompany.vue'], resolve),
                    meta: { title: '新增资金方' }
                },
                {
                    path: '/add-financial-product',
                    component: resolve => require(['../components/page/systemmanagement/FinancialProduct.vue'], resolve),
                    meta: { title: '新增金融产品' }
                },
                {
                    path: '/customer-management',
                    component: resolve => require(['../components/page/systemmanagement/CustomerManagement.vue'], resolve),
                    meta: { title: '客户管理' }
                },
                {
                    path: '/find-project-credit-info',
                    component: resolve => require(['../components/page/engineeringcompany/FindProjectCreditInfo.vue'], resolve),
                    meta: { title: '授信项目详情' }
                },
                {
                    path: '/fund-com-info',
                    component: resolve => require(['../components/page/moneyside/FundComInfo.vue'], resolve),
                    meta: { title: '消息' }
                },
                {
                    path: '/eng-com-info',
                    component: resolve => require(['../components/page/engineeringcompany/EngComInfo.vue'], resolve),
                    meta: { title: '消息' }
                },
                {
                    path: '/financier-info',
                    component: resolve => require(['../components/page/financier/FinancierInfo.vue'], resolve),
                    meta: { title: '消息' }
                },
                {
                    path: '/child-com-list',
                    component: resolve => require(['../components/page/engineeringcompany/ChildComList.vue'], resolve),
                    meta: { title: '分工司管理' }
                },
                {
                    path: '/add-child-com',
                    component: resolve => require(['../components/page/engineeringcompany/ChildCompany.vue'], resolve),
                    meta: { title: '添加分工司' }
                },
                {
                    path: '/my-customer-list',
                    component: resolve => require(['../components/page/moneyside/MyCustomerList.vue'], resolve),
                    meta: { title: '我的客户' }
                },
                {
                    path: '/encom-info',
                    component: resolve => require(['../components/page/engineeringcompany/EngineeringCompanyInfo.vue'], resolve),
                    meta: { title: '企业信息' }
                },
                {
                    path: '/usermanagement-list',
                    component: resolve => require(['../components/page/systemmanagement/UserManagement.vue'], resolve),
                    meta: { title: '用户管理' }
                },
                {
                    path: '/encom-usermanagement-list',
                    component: resolve => require(['../components/page/engineeringcompany/EnComUserManagement.vue'], resolve),
                    meta: { title: '用户管理' }
                },
                {
                    path: '/financiermanagement-list',
                    component: resolve => require(['../components/page/systemmanagement/FinancierManagement.vue'], resolve),
                    meta: { title: '用户管理' }
                },
                {
                    path: '/change-password',
                    component: resolve => require(['../components/page/systemmanagement/ChangePassword.vue'], resolve),
                    meta: { title: '首次登录改密' }
                },
                {
                    path: '/file-management-list',
                    component: resolve => require(['../components/page/systemmanagement/FileManagementList.vue'], resolve),
                    meta: { title: '文件管理' }
                },
                {
                    path: '/fire-project-list',
                    component: resolve => require(['../components/page/systemmanagement/FireProjectList.vue'], resolve),
                    meta: { title: '工程管理' }
                },
                {
                    path: '/fire-maintenance-list',
                    component: resolve => require(['../components/page/systemmanagement/FireMaintenanceProjectList.vue'], resolve),
                    meta: { title: '维保与检测管理' }
                },
                {
                    path: '/credit-dateils2',
                    component: resolve => require(['../components/page/systemmanagement/CreditInfoDateils.vue'], resolve),
                    meta: { title: '授信详情' }
                },


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
