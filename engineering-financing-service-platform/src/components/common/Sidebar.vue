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
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
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
                    {
                        icon: 'el-icon-lx-home',
                        index: 'dashboard',
                        title: '系统首页'
                    },

                    // online start
                    {
                        icon: 'el-icon-lx-sort',
                        index: 'my-project',
                        title: '我的项目'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'home1',
                        title: 'home1'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'home2',
                        title: 'home2'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'product-add',
                        title: '添加产品'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'financecompany-add',
                        title: '添加资金方'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'engineeringcompany-add',
                        title: '添加工程公司'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'childcompany-add',
                        title: '添加分公司'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'businessmanager-add',
                        title: '添加商务经理'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'businessmanager-authen',
                        title: '商务经理认证'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'businessmanager-info-details',
                        title: '商务经理信息明细'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'project-add',
                        title: '添加项目'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'project-details',
                        title: '项目信息详情'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'credit-info-details',
                        title: '授信信息明细'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'engineeringloan-credit-add',
                        title: '添加授信'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'engineeringloan-loan-add',
                        title: '添加放款'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'engineeringloan-credit-apply-info',
                        title: '授信申请信息'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'engineeringloan-credit-apply-list',
                        title: '授信申请列表'
                    },
                    {
                        icon: 'el-icon-lx-home',
                        index: 'engineeringloan-loan-apply-list',
                        title: '放款申请列表'
                    },
                    {
                        icon: 'el-icon-rank',
                        index: 'project',
                        title: '工程管理',
                        subs: [
                            {
                                index: 'fire-project-list',
                                title: '消防工程',
                            },
                            {
                                index: 'mainten-priject-list',
                                title: '维保工程',
                            },
                            {
                                index: 'priject-list',
                                title: '可授信项目',
                            }
                        ]
                    },
                    {
                        icon: 'el-icon-lx-commentfill',
                        index: 'message-list',
                        title: '消息'
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
                        icon: 'el-icon-lx-newsfill',
                        index: 'product',
                        title: '金融产品管理',
                        subs: [
                            {
                                index: 'product-list',
                                title: '产品列表',
                            },
                            {
                                index: 'product-preview',
                                title: '文件预览',
                            }
                        ]
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
                        icon: 'el-icon-lx-friend',
                        index: 'mycustomer-list',
                        title: '我的客户'
                    },
                    // online end
                    {
                        icon: 'el-icon-lx-home',
                        index: 'credit-dateils',
                        title: '授信信息详情'
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
                        index: '6',
                        title: '拖拽组件',
                        subs: [
                            {
                                index: 'drag',
                                title: '拖拽列表',
                            },
                            {
                                index: 'dialog',
                                title: '拖拽弹框',
                            }
                        ]
                    },
                    {
                        icon: 'el-icon-lx-warn',
                        index: '7',
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
                    }
                ]
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
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
        top: 70px;
        bottom:0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar{
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 200px;
    }
    .sidebar > ul {
        height:100%;
    }

    /* add start */
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
    /* add end */
</style>
