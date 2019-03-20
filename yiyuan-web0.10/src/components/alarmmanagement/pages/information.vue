<template>
  <div id="information">
    <div class="search-top operation-top">
      <div class="search">
        <el-input placeholder="查询" v-model="searchForm" clearable prefix-icon="el-icon-search" class="search-input" clearable></el-input>
      </div>
      <div class="operation">
        <div class="refresh select">
          <img src="../../../common/images/icon/btn_refresh.png">
          <span>刷新</span>
        </div>
        <div class="delete select">
          <img src="../../../common/images/icon/btn_delete.png">
          <span>删除</span>
        </div>
      </div>
    </div>
    <el-tabs v-model="activeName" style="margin-top: 16px;">
      <el-tab-pane label="实时警告" name="realTime">
        <div class="tabelData">
          <el-table :data="realTimeList" height="100%">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="status" label="状态">
            </el-table-column>
            <el-table-column prop="name" label="告警名称">
            </el-table-column>
            <el-table-column prop="hazardsource" label="危险源">
            </el-table-column>
            <el-table-column prop="date" label="告警时间">
            </el-table-column>
            <el-table-column prop="rangeMin" label="告警范围（最小）">
            </el-table-column>
            <el-table-column prop="rangeMax" label="告警范围（最大）">
            </el-table-column>
            <el-table-column prop="number" label="告警值">
              <template slot-scope="scope">
                <span style="color:#1B88F5">{{scope.row.number}}</span>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane label="历史警告" name="history">
        <div class="tabelData">
          <el-table :data="historyList" height="100%">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="status" label="状态">
            </el-table-column>
            <el-table-column prop="name" label="告警名称">
            </el-table-column>
            <el-table-column prop="hazardsource" label="危险源">
            </el-table-column>
            <el-table-column prop="date" label="告警时间">
            </el-table-column>
            <el-table-column prop="rangeMin" label="告警范围（最小）">
            </el-table-column>
            <el-table-column prop="rangeMax" label="告警范围（最大）">
            </el-table-column>
            <el-table-column prop="number" label="告警值">
              <template slot-scope="scope">
                <span style="color:#1B88F5">{{scope.row.number}}</span>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
const alarmInfoService = require('../../../service/alarmInfoService.js');
export default {
  data() {
    return {
      searchForm: '',
      activeName: 'realTime',
      realTimeList: [{
        status: '告警',
        name: '硫化氢',
        hazardsource: '电梯井道',
        date: '2019-09-10 13:00:00',
        rangeMin: '40',
        rangeMax: '100',
        number: '0'
      }, {
        status: '告警',
        name: '硫化氢',
        hazardsource: '电梯井道',
        date: '2019-09-10 13:00:00',
        rangeMin: '40',
        rangeMax: '100',
        number: '0'
      }, {
        status: '告警',
        name: '硫化氢',
        hazardsource: '电梯井道',
        date: '2019-09-10 13:00:00',
        rangeMin: '40',
        rangeMax: '100',
        number: '0'
      }],
      historyList: [{
        status: '告警',
        name: '二氧化碳',
        hazardsource: '电梯井道',
        date: '2019-09-10 13:00:00',
        rangeMin: '40',
        rangeMax: '100',
        number: '0.12'
      }, {
        status: '告警',
        name: '二氧化碳',
        hazardsource: '电梯井道',
        date: '2019-09-10 13:00:00',
        rangeMin: '40',
        rangeMax: '100',
        number: '0.1'
      }, {
        status: '告警',
        name: '二氧化碳',
        hazardsource: '电梯井道',
        date: '2019-09-10 13:00:00',
        rangeMin: '40',
        rangeMax: '100',
        number: '0'
      }]
    }
  },
  
  methods: {
    /**
     * 获取历史警告数据
     */
    getHistoryInfo(){
      let _than = this;
      alarmInfoService.listHistoryInfo(res => {
        _than.historyList = res;
      });
    },

    /**
     * 获取实时警告数据
     */
    getRealTimeInfo(){
      alarmInfoService.listRealTimeInfo(res => {
        _than.realTimeList = res;
      })
    }
  }
}

</script>
<style scoped>
#information {
  padding: 0 20px;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #fff;
}

.select {
  padding: 0 15px;
  display: flex;
  align-items: center;
}

.select:last-child {
  padding-right: 0;
}

.select:hover {
  cursor: pointer;
}

.operation {
  display: flex;
  justify-content: space-between;
}

.operation span {
  color: #1B88F5;
  font-size: 14px;
}

.operation img {
  width: 17px;
  height: 17px;
  margin-right: 5px;
}

.search-top {
  position: absolute;
  right: 36px;
  width: 458px;
  height: 70px;
  padding-bottom: 4px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  z-index: 99;
}

.tabelData {
  width: 100%;
  display: flex;
  flex: 1;
}

.paging {
  padding-bottom: 66px;
  text-align: center;
  display: flex;
  align-items: center;
  height: 80px;
}

.paging-detail {
  width: 100%;
}

</style>
<style>
#information /deep/.el-tabs__nav-wrap::after {
  background-color: #fff !important
}

</style>
