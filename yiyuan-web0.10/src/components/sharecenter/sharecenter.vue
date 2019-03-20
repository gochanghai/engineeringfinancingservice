<template>
  <div id="share-center">
    <div class="left-list work-space">
      <div class="work-list">
        <span class="work-name">作业空间列表</span>
        <div class="work-item for-item" v-for="(item, index) in testList" @click="workInfoView(index)">
          <label>{{item.name}}</label>
          <span :style="{color: item.status == '正在作业' ? '#E90404' : '#02B13C'}">{{item.status}}</span>
        </div>
      </div>
    </div>
    <div class="map center"></div>
    <!-- 作业监控 -->
    <work-monitoring :workVisible="workVisible" @workClose="workClose" />
  </div>
</template>
<script>
import workMonitoring from '../sharecenter/module/workmonitoring.vue'

export default {
  data() {
    return {
      workVisible: false,
      testList: [{
        name: "电梯井盖",
        status: "正在作业"
      }, {
        name: "电梯井盖",
        status: "正在作业"
      }, {
        name: "电梯井盖",
        status: "正在作业"
      }, {
        name: "电梯井盖",
        status: "作业结束"
      }]
    }
  },

  components: {
    workMonitoring
  },

  methods: {
    workClose() {
      this.workVisible = false;
    },

    workInfoView(index) {
      this.workVisible = true;
    }
  }
}

</script>
<style scoped>
#share-center {
  position: absolute;
  display: flex;
  width: 100%;
  height: 93.8%;
}

.left-list {
  width: 200px;
  height: 100%;
  margin-right: 7px;
  box-shadow: 5px 2px 10px #e5e5e5;
}

.map {
  flex: 1;
}

.map img {
  width: 100%;
  height: 100%;
}

.work-list span.work-name {
  display: block;
  line-height: 40px;
  font-size: 14px;
  color: #202020;
  text-align: center;
}

.work-item {
  font-size: 14px;
  padding: 0 10px;
  line-height: 40px;
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid #CCC;
}

.work-item label {
  color: #202020;
}

.work-item:hover {
  cursor: pointer;
  background-color: #e4e4e4;
}

</style>
