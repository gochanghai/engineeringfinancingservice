<template>
  <!-- 风险识别 -->
  <div id="risk">
    <div class="search-top operation-top">
      <div class="search">
        <el-input placeholder="查询" v-model="searchForm" clearable prefix-icon="el-icon-search" class="search-input" clearable></el-input>
      </div>
      <div class="operation">
        <el-button type="text" icon="el-icon-refresh">刷新</el-button>
        <el-upload action="" :on-change="uploadFile" :before-remove="beforeRemove">
          <el-button type="text" icon="el-icon-upload2">上传</el-button>
          <!-- <div class="file-name">name</div> -->
        </el-upload>
        <el-button type="text" icon="el-icon-delete">删除</el-button>
        <el-button type="text" icon="el-icon-circle-plus-outline" @click="riskEdit('add')">添加</el-button>
        <el-button type="text" icon="el-icon-edit" @click="riskEdit('update')">修改</el-button>
      </div>
    </div>
    <div class="position-right">
      <el-radio-group v-model="position" size="mini" @tab-click="tabToogle">
        <el-radio-button label="open">展开</el-radio-button>
        <el-radio-button label="retract">收起</el-radio-button>
      </el-radio-group>
    </div>
    <div class="tabelData">
      <el-table :data="riskList" height="100%">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column prop="level" label="风险等级">
        </el-table-column>
        <el-table-column prop="hazardSource" label="危险源">
        </el-table-column>
        <el-table-column prop="safetyRisk" label="安全风险">
        </el-table-column>
        <el-table-column prop="hurt" label="可能造成伤害">
        </el-table-column>
        <el-table-column prop="typeL" label="L" width="80">
        </el-table-column>
        <el-table-column prop="typeE" label="E" width="80">
        </el-table-column>
        <el-table-column prop="typeC" label="C" width="80">
        </el-table-column>
        <el-table-column prop="typeD" label="D" width="80">
        </el-table-column>
        <el-table-column prop="prevention" label="预防措施" width="470">
        </el-table-column>
        <el-table-column prop="remarks" label="备注">
        </el-table-column>
      </el-table>
    </div>
    <div class="paging">
      <el-pagination background :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400" class="paging-detail">
      </el-pagination>
    </div>
    <transition name="slide">
      <div v-show="position=='open'" class="detail-dialog">
        <div class="row">
          <div class="row-item1 current-item">
            <label class="top-title">发生事故的可能性<span style="color: #E90404;">(L)</span></label>
            <div class="content table">
              <div>
                <span class="title number">分数值</span>
                <label class="title values">事故发生的可能性</label></div>
              <div>
                <span>1</span>
                <label>完全可以预料</label></div>
              <div>
                <span>12</span>
                <label>相当可能</label></div>
              <div>
                <span>9</span>
                <label>可能，但不经常</label></div>
              <div>
                <span>23</span>
                <label>可能性小</label></div>
              <div>
                <span>5</span>
                <label>很不可能</label></div>
              <div>
                <span>18</span>
                <label>很不可能</label></div>
            </div>
          </div>
          <div class="row-item2 current-item">
            <label class="top-title">人员暴露的频繁程度<span style="color: #E90404;">(E)</span></label>
            <div class="content table">
              <div>
                <span class="title number">分数值</span>
                <label class="title values">事故发生的可能性</label></div>
              <div>
                <span>10</span>
                <label>连续暴露</label></div>
              <div>
                <span>1</span>
                <label>每天工作事件暴露</label></div>
              <div>
                <span>2</span>
                <label>每月一次暴露</label></div>
              <div>
                <span>13</span>
                <label>每年几次暴露</label></div>
              <div>
                <span>5</span>
                <label>非常罕见暴露</label></div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="row-item3 current-item" style="padding-top: 15px;">
            <label class="top-title">发生事故后果的严重性<span style="color: #E90404;">(C)</span></label>
            <div class="content table">
              <div>
                <span class="title number">分数值</span>
                <label class="title values">事故发生的可能性</label></div>
              <div>
                <span>1</span>
                <label>10-13人死亡</label></div>
              <div>
                <span>12</span>
                <label>大部分人受伤</label></div>
              <div>
                <span>9</span>
                <label>可能，但不经常</label></div>
              <div>
                <span>23</span>
                <label>严重</label></div>
              <div>
                <span>5</span>
                <label>伤残</label></div>
              <div>
                <span>18</span>
                <label>送往医院</label></div>
            </div>
          </div>
          <div class="row-item4 current-item" style="padding-top: 15px;">
            <label class="top-title">风险等级判断表<span style="color: #E90404;">(D)</span></label>
            <div class="content table">
              <div>
                <span class="title number" style="width: 140px;">分数值</span>
                <span class="title number" style="width: 140px;">风险程度</span>
                <label class="title values" style="width: 140px;">标志色</label></div>
              <div>
                <span style="width: 170px;">>720</span>
                <label>极高</label>
                <label>红色</label></div>
              <div>
                <span style="width: 170px;">720>D>=240</span>
                <label>极高</label>
                <label>红色</label></div>
              <div>
                <span style="width: 170px;">240>D>=150</span>
                <label>极高</label>
                <label>红色</label></div>
              <div>
                <span style="width: 170px;">70>D>=150</span>
                <label>极高</label>
                <label>红色</label></div>
            </div>
          </div>
        </div>
      </div>
    </transition>
    <risk-edit @riskeditClose="riskeditVisible=false" :riskeditTitle="riskeditTitle" :riskeditVisible="riskeditVisible"></risk-edit>
  </div>
</template>
<script>
import riskEdit from '../module/riskedit.vue'

const riskIdentifyService = require('../../../service/riskIdentifyService.js')
export default {
  data() {
    return {
      riskeditVisible: false,
      riskeditTitle: '',
      searchForm: '',
      position: 'retract',
      riskList: [{
        level: '1',
        hazardSource: '电梯井道',
        safetyRisk: '操作不慎触电或漏电',
        hurt: '触电',
        typeL: '1',
        typeE: '3',
        typeC: '15',
        typeD: '45',
        prevention: '严格执行安全操作',
        remarks: '无'
      }, {
        level: '1',
        hazardSource: '电梯井道',
        safetyRisk: '操作不慎触电或漏电',
        hurt: '触电',
        typeL: '1',
        typeE: '3',
        typeC: '15',
        typeD: '45',
        prevention: '严格执行安全操作',
        remarks: '无'
      }, {
        level: '1',
        hazardSource: '电梯井道',
        safetyRisk: '操作不慎触电或漏电',
        hurt: '触电',
        typeL: '1',
        typeE: '3',
        typeC: '15',
        typeD: '45',
        prevention: '严格执行安全操作',
        remarks: '无'
      }]
    }
  },

  components: {
    riskEdit
  },

  created() {
    this.getListData();
  },

  methods: {
    tabToogle(data) {
      console.log(data);
    },
    /**
     * 获取风险识别列表数据
     */
    getListData() {
      riskIdentifyService.list(res =>{
        console.log(res);
      });
    },

    riskEdit(type) {
      if (type == 'add') {
        this.riskeditTitle = '添加作业风险标识'
      } else {
        this.riskeditTitle = '修改作业风险标识'
      }

      this.riskeditVisible = true;
    },

    uploadFile(file) {
      console.log(file);
    },

    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    }
  }
}

</script>
<style scoped>
#risk {
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

.position-right {
  text-align: right;
  padding: 16px 0;
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

.detail-dialog {
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 20px 10px;
  top: 128px;
  right: 0;
  font-size: 14px;
  background-color: #fff;
  box-shadow: #c7c7c7 0px 0px 18px;
  z-index: 999;
}

.detail-dialog div.row:last-child {
  display: flex;
  justify-content: space-between;
  border-bottom: none;
}

.detail-dialog div.row {
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid #ccc;
}

.current-item {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
  padding-bottom: 10px;
}

.current-item:last-child {
  margin-right: 0;
}

.row-item1,
.row-item3 {
  width: 290px;
  border-right: 1px solid #CCC;
}

.row-item2,
.row-item4 {
  width: 300px;
}

.current-item label.top-title {
  display: block;
  text-align: center;
  color: #555555;
  padding-bottom: 12px;
}

.current-item div.content div {
  width: 100%;
  line-height: 26px;
  display: flex;
  justify-content: space-around;
}

.current-item div.content div span {
  display: inline-block;
  text-align: center;
  width: 100px;
  color: #202020;
}

.current-item div.content div span.title,
.current-item div.content div label.title {
  color: #555555;
}

.current-item div.content div label {
  display: inline-block;
  text-align: center;
  width: 170px;
  color: #202020;
}

.slide-enter-active,
.slide-leave-active {
  transition: all .3s ease;
}

.slide-enter,
.slide-leave-to {
  transform: translateX(15px);
  opacity: 0;
}

</style>
