<template>
  <div class="historytaskinfo">
    <el-dialog title="." :visible.sync="workVisible" width="50%" :before-close="workClose">
      <div class="tabs-top">
        <el-tabs v-model="activeName">
          <el-tab-pane label="作业详情" name="detail">
            <div class="for-item">
              <label>作业名称</label>
              <div>有限空间</div>
            </div>
            <div class="for-item">
              <label>作业时间</label>
              <div>2019-10-09 19:30:00</div>
            </div>
            <div class="for-item">
              <label>作业地址</label>
              <div>广东省深圳市南山区马家龙文化体育中心1号</div>
            </div>
            <div class="for-item">
              <label>主要负责人</label>
              <div>张三</div>
            </div>
            <div class="for-item">
              <label>安全管理人员</label>
              <div>李四</div>
            </div>
            <div class="for-item">
              <label>作业人数(人)</label>
              <div>李伟</div>
            </div>
            <div class="for-item">
              <label>现场作业人员</label>
              <div>张三，李四，王五</div>
            </div>
            <div class="for-item">
              <label>开始时间</label>
              <div>2019-10-09 23:00:00</div>
            </div>
            <div class="for-item">
              <label>结束时间</label>
              <div>2019-10-10 04:00:00</div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="作业流程" name="step">
            <div class="step">
              <div class="left type-list">
                <label v-for="(item, index) in typeList" :key="index" @click="selType(index)" :style="{color: typeIndex == index ? '#1B88F5' : ''}">
                  {{item}}
                </label>
              </div>
              <div class="right type-view" v-if="typeIndex == 0">
                <span class="span-title">基本信息</span>
                <div class="content">
                  <div class="for-item data-item">
                    <label>作业类型</label>
                    电梯井道
                  </div>
                  <div class="for-item data-item">
                    <label>作业名称</label>
                    有限空间
                  </div>
                  <div class="for-item data-item">
                    <label>作业时间</label>
                    2019/9/09 09:00:00
                  </div>
                  <div class="for-item data-item">
                    <label>作业地址</label>
                    广东省深圳市南山区马家龙文化体育中心1号
                  </div>
                  <div class="for-item data-item">
                    <label>主要负责人</label>
                    王兵
                  </div>
                  <div class="for-item data-item">
                    <label>安全管理人员</label>
                    李伟
                  </div>
                  <div class="for-item data-item">
                    <label>现场监护人</label>
                    张三
                  </div>
                  <div class="for-item data-item">
                    <label>作业人数(人)</label>
                    2
                  </div>
                </div>
                <span class="span-title" style="margin-top: 6px;">作业方案</span>
                <a href="#" class="file">易元公司有限空间作业方案.pdf</a>
                <span class="span-title" style="margin-top: 16px;">应急方案</span>
                <a href="#" class="file" >易元公司有限空间作业方案.pdf</a>
              </div>
              <div class="right type-view2" v-else-if="typeIndex == 1">
                <span class="span-title">审批信息</span>
                <div class="for-item data-item">
                  <label>审批人</label>
                  叶萧宝
                </div>
                <div class="for-item data-item">
                  <label>审批时间</label>
                  2019-10-09 10:00:00
                </div>
                <div class="for-item data-item">
                  <label>审批意见</label>
                  同意
                </div>
              </div>
              <div class="right type-view3" v-else-if="typeIndex == 2">
                <div v-for="(item,index) in 4" :key="index" class="type-view3-item">
                  <label>应急救援相关照片{{index}}</label>
                  <img src="../../../common/images/wallhaven.png">
                </div>
                  <div class="type-view3-context">
                    <span class="span-title" style="margin-top: 6px;">专项培训文件</span>
                    <a href="#" class="file">易元公司有限空间作业方案.pdf</a>
                    <span class="span-title" style="margin-top: 16px;">培训签到表</span>
                    <a href="#" class="file" >易元公司有限空间作业方案.pdf</a>
                  </div>
                </div>
                <div class="right type-view4" v-else-if="typeIndex == 3">
                  <div v-for="(item,index) in 4" :key="index" class="type-view3-item">
                    <label>现场合影{{index}}</label>
                    <img src="../../../common/images/wallhaven.png">
                </div>
                  </div>
                  <div class="right type-view5" v-else-if="typeIndex == 4">
                    <div style="width: 300px;height:60px;margin-top: 10px;">
                      <div class="type-view5-context">
                        <label>一氧化碳</label>
                        <span>0</span>
                      </div>
                      <div class="type-view5-context">
                        <label>氧气</label>
                        <span>20</span>
                      </div>
                    </div>
                    <div style="width: 300px;height:60px;margin-top: 10px;">
                      <div class="type-view5-context">
                        <label>硫化氢</label>
                        <span>0</span>
                      </div>
                      <div class="type-view5-context">
                        <label>人体感应</label>
                        <span>1</span>
                      </div>
                    </div>
                  </div>
                </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName: 'detail',
      typeList: ['作业申请信息', '作业审批信息', '上传准备材料', '落实工作', '作业环境状态'],
      typeIndex: 0
    }
  },

  props: {
    workVisible: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    workClose() {
      this.$emit('workClose', false);
    },
    selType(index) {
      this.typeIndex = index;
    }
  }
}

</script>
<style scoped>
.tabs-top {
  width: 93%;
  height: 600px;
  margin-top: -74px;
  top: 6px;
  left: 2%;
}

.for-item {
  display: flex;
}

.for-item label {
  display: inline-block;
  width: 110px;
  color: #555555;
  line-height: 36px;
}

.for-item div {
  color: #202020;
  flex: 1;
  line-height: 36px;

}

.step {
  display: flex;
}

.file {
  display: block;
  color: #555555
}

.span-title {
  display: block;
  line-height: 30px;
  color: #202020;
}

.type-list {
  width: 140px;
  line-height: 36px;
}

.type-list>label {
  width: 100%;
  display: inline-block;
}

.type-list>label:hover {
  cursor: pointer;
}

.data-item {
  line-height: 34px;
  color: #202020;
}

.data-item>label {
  width: 100px;
  line-height: 34px;
  color: #555555;
}

.type-view2,
.type-view3,
.type-view4,
.type-view5 {
  padding: 6px 12px;
  margin-top: 10px;
  width: 540px;
  height: 420px;
  background-color: #EEEEEE;
  overflow: hidden;
}

.type-view3-item {
  float: left;
  margin-right: 52px;
  margin-left: 2px;
  width: 170px;
}

.type-view3-item label {
  color: #202020;
  line-height: 31px;
}

.type-view3-item img {
  width: 100%;
}

.type-view3-context {
  margin-top: 280px;
}

.type-view5-context {
  float: left;
  text-align: center;
  width: 90px;
}

.type-view5-context label {
  display: block;
  line-height: 30px;
}

.type-view5-context span {
  display: block;
  line-height: 27px;
}

</style>
<style>
.tabs-top /deep/.el-tabs__nav-wrap::after {
  background-color: #007ED7 !important
}

.tabs-top /deep/.el-tabs__item {
  color: #fff;
}

.tabs-top /deep/.el-tabs__item:hover {
  color: #fff;
}

.tabs-top /deep/.el-tabs__item.is-active {
  color: #fff;
}

.tabs-top /deep/.el-tabs__active-bar {
  background-color: #fff;
  bottom: 3px;
}

</style>
