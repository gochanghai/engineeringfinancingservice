<template>
  <div id="configure">
    <div class="for-item current-data" v-for="item in 9">
      <label class="name">氧气 <i class="el-icon-edit-outline" @click="updateAlarm"></i></label>
      <div class="info">
        <label>警告类型</label>
        氧气
      </div>
      <div class="info">
        <label>最大值</label>
        20
      </div>
      <div class="info">
        <label>最小值</label>
        10
      </div>
    </div>
    <div class="for-item current-data add" @click="addAlarm">
      <img src="../../../common/images/icon/add-small.png">
      <label>添加</label>
    </div>
    <update-alarm :updatealarmVisible="updatealarmVisible" :updatealarmTitle="updatealarmTitle" @updateClose="updateClose"></update-alarm>
  </div>
</template>
<script>
import updateAlarm from '../module/updatealarm.vue'

const alarmConfigService = require('../../../service/alarmConfigService.js');
export default {
  data() {
    return {
      list: [],
      updatealarmVisible: false,
      updatealarmTitle: ""
    }
  },

  components: {
    updateAlarm
  },

  methods: {
    updateAlarm() {
      this.updatealarmVisible = true;
      this.updatealarmTitle = '修改警告配置'
    },

    updateClose(data) {
      this.updatealarmVisible = data;
    },

    addAlarm() {
      this.updatealarmVisible = true;
      this.updatealarmTitle = '添加警告配置'
    },

    /**
     * 获取数据
     */
    getInfo(){
      let _than = this;
      alarmConfigService.list(res => {
        _than.list = res;
      });
    }
  }
}

</script>
<style scoped>
#configure {
  padding: 0 20px;
  height: 100%;
  background-color: #fff;
}

.current-data {
  float: left;
  margin-right: 20px;
  display: inline-block;
  width: 220px;
  height: 110px;
  margin-top: 20px;
  border: 1px solid #CCC;
  padding: 10px 16px;
  border-radius: 3px;
}

.current-data label.name {
  color: #202020;
  line-height: 30px;
  font-size: 15px;
}

.current-data label.name i:hover {
  cursor: pointer;
}

.current-data div.info {
  color: #202020;
  font-size: 14px;
  line-height: 26px;
}

.current-data div.info label {
  display: inline-block;
  width: 70px;
  color: #555555;
}

.add {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.add:hover {
  cursor: pointer;
}

.add img {
  margin-top: 17px;
  width: 55px;
  height: 55px;
}

.add label {
  margin-top: 5px;
  display: block;
  color: #555555;
  font-size: 14px;
}

</style>
