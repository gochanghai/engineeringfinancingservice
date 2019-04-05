<template>
    <div class="">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-copy"></i> tab选项卡</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-tabs v-model="message">
                <el-tab-pane :label="`未读消息(${unread.length})`" name="first">
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <div class="message-item">
                        <div class="message-item-title">
                            <div class="message-badge">NEW</div>
                            <div class="message-type">• 消息通知</div>
                            <div class="message-date">2019-01-22 15:30:36</div>
                        </div>
                        <div class="message-item-content">
                            <div class="message-content-title">放款完成通知：</div>
                            <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww！</div>
                        </div>
                    </div>
                    <!--<el-table :data="unread" :show-header="false" style="width: 100%">-->

                        <!--<el-table-column>-->
                            <!--<template slot-scope="scope">-->
                                <!--<span class="message-title"><div class="info-badge">NEW</div>{{scope.row.title}}</span>-->
                            <!--</template>-->
                        <!--</el-table-column>-->
                        <!--<el-table-column prop="date" width="180"></el-table-column>-->
                        <!--<el-table-column width="120">-->
                            <!--<template slot-scope="scope">-->
                                <!--<el-button size="small" @click="handleRead(scope.$index)">标为已读</el-button>-->
                            <!--</template>-->
                        <!--</el-table-column>-->
                    <!--</el-table>-->
                    <div class="handle-row">
                        <el-button type="primary">全部标为已读</el-button>
                    </div>
                </el-tab-pane>
                <el-tab-pane :label="`已读消息(${read.length})`" name="second">
                    <template v-if="message === 'second'">
                        <el-table :data="read" :show-header="false" style="width: 100%">
                            <el-table-column>
                                <template slot-scope="scope">
                                    <span class="message-title">{{scope.row.title}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="date" width="150"></el-table-column>
                            <el-table-column width="120">
                                <template slot-scope="scope">
                                    <el-button type="danger" @click="handleDel(scope.$index)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="handle-row">
                            <el-button type="danger">删除全部</el-button>
                        </div>
                    </template>
                </el-tab-pane>
                <el-tab-pane :label="`回收站(${recycle.length})`" name="third">
                    <template v-if="message === 'third'">
                        <el-table :data="recycle" :show-header="false" style="width: 100%">
                            <el-table-column>
                                <template slot-scope="scope">
                                    <span class="message-title">{{scope.row.title}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="date" width="150"></el-table-column>
                            <el-table-column width="120">
                                <template slot-scope="scope">
                                    <el-button @click="handleRestore(scope.$index)">还原</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="handle-row">
                            <el-button type="danger">清空回收站</el-button>
                        </div>
                    </template>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'tabs',
        data() {
            return {
                message: 'first',
                showHeader: false,
                unread: [{
                    date: '2018-04-19 20:00:00',
                    title: '【系统通知】该系统将于今晚凌晨2点到5点进行升级维护',
                },{
                    date: '2018-04-19 21:00:00',
                    title: '今晚12点整发大红包，先到先得',
                }],
                read: [{
                    date: '2018-04-19 20:00:00',
                    title: '【系统通知】该系统将于今晚凌晨2点到5点进行升级维护'
                }],
                recycle: [{
                    date: '2018-04-19 20:00:00',
                    title: '【系统通知】该系统将于今晚凌晨2点到5点进行升级维护'
                }]
            }
        },
        methods: {
            handleRead(index) {
                const item = this.unread.splice(index, 1);
                console.log(item);
                this.read = item.concat(this.read);
            },
            handleDel(index) {
                const item = this.read.splice(index, 1);
                this.recycle = item.concat(this.recycle);
            },
            handleRestore(index) {
                const item = this.recycle.splice(index, 1);
                this.read = item.concat(this.read);
            }
        },
        computed: {
            unreadNum(){
                return this.unread.length;
            }
        }
    }

</script>

<style>
    .message-title{
        cursor: pointer;
        float: left;
    }
    .handle-row{
        margin-top: 30px;
    }

    .message-item{
        width: auto;
        padding: 20px 0px;
        margin: 20px 60px;
        border-bottom: 1px dashed #ccc;
    }
    .message-item-title{
        width:100%;
        height:16px;
        font-size:12px;
        font-family:MicrosoftYaHei;
        color:rgba(34,34,34,1);
        line-height:16px;
        text-align: left;
    }
    .message-type{
        display: block;
        height:16px;
        color:rgba(34,34,34,1);
        line-height:16px;
        float: left;
        margin-left: 20px;
    }
    .message-date{
        display: block;
        height:16px;
        color:rgba(34,34,34,1);
        line-height:16px;
        float: left;
        margin: 0 50px;
    }
    .message-badge{
        display: block;
        float: left;
        width:40px;
        height:16px;
        background:rgba(255,130,8,1);
        border-radius:8px;
        color:rgba(255,255,255,1);
        line-height:16px;
        text-align: center;
    }
    .message-item-content {
        padding: 16px 20px 16px 60px;
        height: auto;
        margin-bottom: 20px;
    }
    .message-content-title{
        display: block;
        float: left;
        width:98px;
        height:19px;
        font-size:14px;
        font-family:MicrosoftYaHei-Bold;
        font-weight:bold;
        color:rgba(34,34,34,1);
        line-height:19px;
    }
    .message-content{
        display: block;
        float: left;
        width: 90%;
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(34,34,34,1);
        line-height:19px;
    }
</style>

