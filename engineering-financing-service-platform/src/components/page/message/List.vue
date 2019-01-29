<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>消息</span>
                    </div>
                    <div class="message-box">
                        <div class="message-nav-scroll">
                            <div style="width: 300px; margin: 0 auto;">
                                <div class="message-nav-item" :class="{'is-nav-item': isMessageType === 1}" @click="isMessageType = 1">消息通知</div>
                                <div class="message-nav-item" :class="{'is-nav-item': isMessageType === 0}" @click="isMessageType = 0 ">系统公告</div>
                            </div>
                        </div>
                        <div v-show="isMessageType === 1">
                            <div class="message-item" v-for=" item in MessageList">
                                <div class="message-item-title">
                                    <div class="message-badge" v-if="item.status === 0">NEW</div>
                                    <div class="message-type">• 消息通知</div>
                                    <div class="message-date">{{item.createDate}}</div>
                                </div>
                                <div class="message-item-content">
                                    <div class="message-content-title">{{item.title}}：</div>
                                    <div class="message-content">{{item.content}}</div>
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
                                    <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                                </div>
                            </div>
                            <div class="message-item">
                                <div class="message-item-title">
                                    <!--<div class="message-badge">NEW</div>-->
                                    <div class="message-type2">• 消息通知</div>
                                    <div class="message-date">2019-01-22 15:30:36</div>
                                </div>
                                <div class="message-item-content">
                                    <div class="message-content-title">放款完成通知：</div>
                                    <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                                </div>
                            </div>
                            <div class="message-item">
                                <div class="message-item-title">
                                    <!--<div class="message-badge">NEW</div>-->
                                    <div class="message-type2">• 消息通知</div>
                                    <div class="message-date">2019-01-22 15:30:36</div>
                                </div>
                                <div class="message-item-content">
                                    <div class="message-content-title">放款完成通知：</div>
                                    <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                                </div>
                            </div>
                            <div class="handle-row">
                                <el-button type="warning">全部标为已读</el-button>
                            </div>
                        </div>
                        <div v-show="isMessageType === 0">
                            <div class="message-item">
                                <div class="message-item-title">
                                    <div class="message-badge">NEW</div>
                                    <div class="message-type">• 消息通知</div>
                                    <div class="message-date">2020-01-22 15:30:36</div>
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
                                    <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                                </div>
                            </div>
                            <div class="message-item">
                                <div class="message-item-title">
                                    <!--<div class="message-badge">NEW</div>-->
                                    <div class="message-type2">• 消息通知</div>
                                    <div class="message-date">2019-01-22 15:30:36</div>
                                </div>
                                <div class="message-item-content">
                                    <div class="message-content-title">放款完成通知：</div>
                                    <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                                </div>
                            </div>
                            <div class="message-item">
                                <div class="message-item-title">
                                    <!--<div class="message-badge">NEW</div>-->
                                    <div class="message-type2">• 消息通知</div>
                                    <div class="message-date">2019-01-22 15:30:36</div>
                                </div>
                                <div class="message-item-content">
                                    <div class="message-content-title">放款完成通知：</div>
                                    <div class="message-content">亲爱的用户，您的放款申请已经批复完毕，近期款项会打入您的账户，请您注意账户变动信息！</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                isMessageType: 1,
                MessageList: [],
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
        created() {
            this.getDataList();
        },
        methods: {

            // 获取数据
            getDataList() {
                let _than = this;
                console.log("companyId: " +  localStorage.getItem('userInfoId'));
                this.$axios.get('api/message/all',{params:{
                        id: 0
                    }}).then(function (response) {
                    console.log(response);
                    _than.MessageList = response.data.extend.list;
                }).catch(function (error) {
                    console.log(error);
                });
            },

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
    }
    .handle-row{
        margin-left: 60px;
        margin-top: 20px;
    }

    .message-nav-scroll{
        width: auto;
        height: 55px;
        font-size:14px;
        font-family:MicrosoftYaHei;
        border-bottom: 2px solid #e4e7ed;
        text-align: center;
    }

    .message-nav-item{
        float: left;
        display: block;
        width:150px;
        height:40px;
        color:rgba(34,34,34,1);
        line-height:40px;
        text-align: center;
        padding-bottom: 13px;
        /*font-size:16px;*/
        /*font-weight: bold;*/
    }
    .message-nav-item:hover{
        border-bottom: 2px solid rgba(255,130,8,1);;
    }
    .is-nav-item{
        border-bottom: 2px solid rgba(255,130,8,1);
    }

    .message-item{
        width: auto;
        padding: 20px 0;
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
    .message-type2{
        display: block;
        height:16px;
        color:rgba(34,34,34,1);
        line-height:16px;
        float: left;
        margin-left: 60px;
    }
    .message-date{
        display: block;
        height:16px;
        color: #606266;
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
    }
    .message-content-title{
        display: block;
        float: left;
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
        /*width:90%;*/
        font-size:14px;
        font-family:MicrosoftYaHei;
        color:rgba(34,34,34,1);
        line-height:19px;

    }

</style>

