<template>
  <!-- 历史作业 -->
  <div id="history-task">
    <div class="search-top operation-top">
      <div class="search">
        <el-input placeholder="查询" v-model="searchForm" clearable prefix-icon="el-icon-search" class="search-input" clearable></el-input>
      </div>
      <div class="operation">
        <div></div>
        <el-button type="text" icon="el-icon-refresh">刷新</el-button>
      </div>
    </div>
    <div class="tabelData">
      <el-table :data="workList" height="100%">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column prop="status" label="状态">
        </el-table-column>
        <el-table-column prop="workName" label="有限空间">
        </el-table-column>
        <el-table-column prop="wokrAt" label="作业时间">
        </el-table-column>
        <el-table-column prop="workPlace" label="作业地点">
        </el-table-column>
        <el-table-column prop="workPerson" label="现场作业人员">
        </el-table-column>
        <el-table-column prop="guardian" label="现场监护人">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="currentDetail">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="paging">
      <el-pagination background :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400" class="paging-detail">
      </el-pagination>
    </div>
    <!-- <history-task-tnfo/> -->
    <history-task-info :workVisible="workVisible" @workClose="workClose"></history-task-info>
  </div>
</template>
<script>
const workService = require('../../../service/workService.js');
import historyTaskInfo from '../module/historytaskinfo.vue'
export default {
  data() {
    return {
      workVisible: false,
      searchForm: '',
      workList: [{
        status: '待审批',
        workName: '有限空间',
        wokrAt: '2019-03-14 20:00:00',
        workPlace: '易元',
        workPerson: '张三，李四',
        guardian: '谢小宝'
      }, {
        status: '待审批',
        workName: '有限空间',
        wokrAt: '2019-03-14 20:00:00',
        workPlace: '易元',
        workPerson: '张三，李四',
        guardian: '谢小宝'
      }, {
        status: '待审批',
        workName: '有限空间',
        wokrAt: '2019-03-14 20:00:00',
        workPlace: '易元',
        workPerson: '张三，李四',
        guardian: '谢小宝'
      }, {
        status: '待审批',
        workName: '有限空间',
        wokrAt: '2019-03-14 20:00:00',
        workPlace: '易元',
        workPerson: '张三，李四',
        guardian: '谢小宝'
      }]
    }
  },

  created() {
    this.getListData();
  },

  methods: {
    /**
     * 获取历史作业列表数据
     */
    getListData() {
      workService.listHistoryWork(res => {
        console.log(res);
      });
      
    },

    /**
     * 详情按钮处理函数
     */
    currentDetail() {
      this.workVisible = true;
    },

    /**
     * 关闭弹窗
     */
    workClose(data) {
      this.workVisible = data;
    }
  },

  components: {
    historyTaskInfo
  },

}

</script>
<style scoped>
#history-task {
  position: relative;
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
  position: relative;
  width: 350px;
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
  height: 70px;
  padding-bottom: 4px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #CCC;
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
