<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#0f1632"
            text-color="#bfcbd9" active-text-color="#ff8208" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.subs">
                            <el-submenu v-if="subItem.subs" :index="subItem.index" :key="subItem.index">
                                <template slot="title">{{ subItem.title }}</template>
                                <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">
                                    {{ threeItem.title }}
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item v-else :index="subItem.index" :key="subItem.index">
                                {{ subItem.title }}
                            </el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index" >
                        <i :class="item.icon"></i><span slot="title" >{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                items: [
                    // ----- 融资人 ------ //
                    {
                        icon: 'el-icon-lx-home',
                        index: 'financier-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '30',
                        title: '工程贷',
                        subs: [
                            {
                                index: 'my-credit-project-list',
                                title: '我的授信申请'
                            },
                            {
                                index: 'my-loan-apply-list',
                                title: '我的贷款申请'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: 'dashboard',
                        title: '设置'
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'dashboard',
                        title: '消息'
                    },

                    // ------ 工程公司 ------ //

                    {
                        icon: 'el-icon-lx-home',
                        index: 'encom-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-peoplefill',
                        index: 'business-manager-list',
                        title: '商务经理管理'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '10',
                        title: '工程贷',
                        subs: [
                            {
                                index: 'may-credit-project-list',
                                title: '授信项目管理'
                            },
                            {
                                index: 'ec-credit-apply-list',
                                title: '授信申请管理'
                            },
                            {
                                index: 'ec-credit-apply-list',
                                title: '授信审批管理'
                            },
                            {
                                index: 'ec-loan-apply-list',
                                title: '放款申请管理'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: '11',
                        title: '设置',
                        subs: [
                            {
                                index: '1',
                                title: '分公司管理'
                            },
                            {
                                index: '2',
                                title: '企业信息'
                            },
                            {
                                index: '1',
                                title: '用户管理'
                            }
                        ]
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'dashboard',
                        title: '消息'
                    },

                    // -------- 资金方 ------- //
                    {
                        icon: 'el-icon-lx-home',
                        index: 'moneyside-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-friend',
                        index: 'dashboard',
                        title: '我的客户'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '20',
                        title: '工程贷',
                        subs: [
                            {
                                index: '1',
                                title: '我的授信申请'
                            },
                            {
                                index: '2',
                                title: '我的贷款申请'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: 'dashboard',
                        title: '设置'
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'dashboard',
                        title: '消息'
                    },

                    // ----- 平台 ------ //
                    {
                        icon: 'el-icon-lx-home',
                        index: 'system-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-friend',
                        index: '00',
                        title: '客户管理',
                        subs: [
                            {
                                index: '1',
                                title: '融资人管理'
                            },
                            {
                                index: 'financial-company-list',
                                title: '资金方管理'
                            },
                            {
                                index: 'engineeringcompany-list',
                                title: '工程公司管理'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-friend',
                        index: 'financial-product-list',
                        title: '金融产品管理'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '01',
                        title: '工程贷',
                        subs: [
                            {
                                index: '1',
                                title: '授信申请管理'
                            },
                            {
                                index: '2',
                                title: '放款申请管理'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: 'dashboard',
                        title: '设置'
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'dashboard',
                        title: '消息'
                    },




                    {
                        icon: 'el-icon-lx-home',
                        index: 'dashboard',
                        title: '系统首页'
                    },
                    {
                        icon: 'el-icon-lx-cascades',
                        index: 'table',
                        title: '基础表格'
                    },
                    {
                        icon: 'el-icon-lx-copy',
                        index: 'tabs',
                        title: 'tab选项卡'
                    },
                    {
                        icon: 'el-icon-lx-calendar',
                        index: '3',
                        title: '表单相关',
                        subs: [
                            {
                                index: 'form',
                                title: '基本表单'
                            },
                            {
                                index: '3-2',
                                title: '三级菜单',
                                subs: [
                                    {
                                        index: 'editor',
                                        title: '富文本编辑器'
                                    },
                                    {
                                        index: 'markdown',
                                        title: 'markdown编辑器'
                                    },
                                ]
                            },
                            {
                                index: 'upload',
                                title: '文件上传'
                            }
                        ]
                    },
                    {
                        icon: 'el-icon-lx-emoji',
                        index: 'icon',
                        title: '自定义图标'
                    },
                    {
                        icon: 'el-icon-lx-favor',
                        index: 'charts',
                        title: 'schart图表'
                    },
                    {
                        icon: 'el-icon-rank',
                        index: 'drag',
                        title: '拖拽列表'
                    },
                    {
                        icon: 'el-icon-lx-warn',
                        index: '6',
                        title: '错误处理',
                        subs: [
                            {
                                index: 'permission',
                                title: '权限测试'
                            },
                            {
                                index: '404',
                                title: '404页面'
                            }
                        ]
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'business-manager-list',
                        title: '商务经理管理'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'financier-unauthen',
                        title: '个人认证'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'may-credit-project-list',
                        title: '授信项目管理'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'ec-credit-apply-list',
                        title: '公司授信申请管理'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'ec-loan-apply-list',
                        title: '公司放款申请管理'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'sm-credit-apply-list',
                        title: '授信申请管理'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'sm-loan-apply-list',
                        title: '放款申请管理'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'my-credit-project-list',
                        title: '我的授信项目列表'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'my-loan-apply-list',
                        title: '我的贷款申请列表'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'credit-Reply-list',
                        title: '授信批复管理'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'loan-apply-Reply-list',
                        title: '放款批复管理'
                    },
                    {
                        icon: 'el-icon-circle-plus',
                        index: 'add-project-credit-apply',
                        title: '新增项目授信申请'
                    },
                    {
                        icon: 'el-icon-circle-plus',
                        index: 'add-project-info',
                        title: '添加项目资料'
                    },
                    {
                        icon: 'el-icon-circle-plus',
                        index: 'add-loan-apply',
                        title: '新增贷款申请'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'financier-home',
                        title: '融资人首页'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'encom-home',
                        title: '工程公司首页'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'moneyside-home',
                        title: '资金方首页'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'system-home',
                        title: '平台方首页'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'login2',
                        title: '登录界面'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'engineeringcompany-list',
                        title: '工程公司管理'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'financial-company-list',
                        title: '资金方管理'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'financial-product-list',
                        title: '金融产品管理'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'mystep',
                        title: '我的Step'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'progress-detail',
                        title: '项目进度明细'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'addFundCompany',
                        title: '新增资金方'
                    },
                    {
                        icon: 'el-icon-lx-group',
                        index: 'customer-management',
                        title: '客户管理'
                    },
                    {
                        icon: 'el-icon-lx-group',
                        index: 'find-project-credit-info',
                        title: '授信项目详情'
                    },

                ]
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
            console.log( "Sidebar: " + localStorage.getItem('role') );
            console.log( "Sidebar: " + localStorage.getItem('role')=== 1);

            // 平台
            if ( localStorage.getItem('role') === '0'){
                this.items = [
                    {
                        icon: 'el-icon-lx-home',
                        index: 'system-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-friend',
                        index: '00',
                        title: '客户管理',
                        subs: [
                            {
                                index: 'financiermanagement-list',
                                title: '融资人管理'
                            },
                            {
                                index: 'financial-company-list',
                                title: '资金方管理'
                            },
                            {
                                index: 'engineeringcompany-list',
                                title: '工程公司管理'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: 'financial-product-list',
                        title: '金融产品管理'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '01',
                        title: '工程贷',
                        subs: [
                            {
                                index: 'sm-credit-apply-list',
                                title: '授信申请管理'
                            },
                            {
                                index: 'sm-loan-apply-list',
                                title: '放款申请管理'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: '02',
                        title: '设置',
                        subs: [
                            {
                                index: 'usermanagement-list',
                                title: '用户管理'
                            },
                            {
                                index: '2',
                                title: '放款申请管理'
                            },
                            {
                                index: 'change-password',
                                title: '首次登录改密'
                            },
                            {
                                index: 'credit-dateils2',
                                title: '授信详情'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'dashboard',
                        title: '消息'
                    },
                    {
                        icon: 'el-icon-lx-file',
                        index: 'file-management-list',
                        title: '文件管理'
                    },
                    {
                        icon: 'el-icon-lx-hotfill',
                        index: '03',
                        title: '消防工程',
                        subs: [
                            {
                                index: 'fire-project-list',
                                title: '工程管理'
                            },
                            {
                                index: 'fire-maintenance-list',
                                title: '维保与检测管理'
                            },
                        ]
                    },
                ]
            }

            // 工程公司
            if ( localStorage.getItem('role')  === '1'){
                this.items = [
                    {
                        icon: 'el-icon-lx-home',
                        index: 'encom-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-peoplefill',
                        index: 'business-manager-list',
                        title: '商务经理管理'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '10',
                        title: '工程贷',
                        subs: [
                            {
                                index: 'may-credit-project-list',
                                title: '授信项目管理'
                            },
                            {
                                index: 'ec-credit-apply-list',
                                title: '授信申请管理'
                            },
                            {
                                index: 'ec-credit-apply-list2',
                                title: '授信审批管理'
                            },
                            {
                                index: 'ec-loan-apply-list',
                                title: '放款申请管理'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: '11',
                        title: '设置',
                        subs: [
                            {
                                index: 'child-com-list',
                                title: '分公司管理'
                            },
                            {
                                index: 'encom-info',
                                title: '企业信息'
                            },
                            {
                                index: 'encom-usermanagement-list',
                                title: '用户管理'
                            }
                        ]
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'eng-com-info',
                        title: '消息'
                    },
                ]
            }

            // 资金公司
            if ( localStorage.getItem('role')  === '2'){
                this.items = [
                    {
                        icon: 'el-icon-lx-home',
                        index: 'moneyside-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-friend',
                        index: 'my-customer-list',
                        title: '我的客户'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '20',
                        title: '工程贷',
                        subs: [
                            {
                                index: 'credit-Reply-list',
                                title: '授信批复管理'
                            },
                            {
                                index: 'loan-apply-Reply-list',
                                title: '放款批复管理'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: 'dashboard',
                        title: '设置'
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'fund-com-info',
                        title: '消息'
                    },
                ]
            }

            // 融资人
            if ( localStorage.getItem('role')  === '3'){
                this.items = [
                    {
                        icon: 'el-icon-lx-home',
                        index: 'financier-home',
                        title: '首页'
                    },
                    {
                        icon: 'el-icon-lx-newsfill',
                        index: '30',
                        title: '工程贷',
                        subs: [
                            {
                                index: 'my-credit-project-list',
                                title: '我的授信申请'
                            },
                            {
                                index: 'my-loan-apply-list',
                                title: '我的贷款申请'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-settings',
                        index: '31',
                        title: '设置',
                        subs: [
                            {
                                index: 'financier-unauthen',
                                title: '个人认证信息'
                            },
                            {
                                index: '32',
                                title: '其它'
                            },
                        ]
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'financier-info',
                        title: '消息'
                    },
                ]
            }

            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 80px;
        bottom:0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar{
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 250px;
    }
    .sidebar > ul {
        height:100%;
    }


    .el-submenu el-submenu__title:hover  {
        background-color: #18224d !important;
        border-left: 2px solid #ff8208;
    }

    .el-menu-item:hover {
        background-color: #18224d !important;
        border-left: 2px solid #ff8208;
        color: #ff8208;
    }
    .el-menu-item:hover i{
        color: #ff8208 !important;
    }
    .el-menu-item:hover span {
        color: #ff8208 !important;
    }
    .el-menu-item.is-active {
        background-color: #18224d !important;
        border-left: 2px solid #ff8208;
    }



</style>
