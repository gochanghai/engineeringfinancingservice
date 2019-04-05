<template>
  <!-- 器材配备 -->
  <div id="material">
    <div class="search-top operation-top">
      <div class="search">
        <el-input placeholder="查询" v-model="searchForm" clearable prefix-icon="el-icon-search" class="search-input" clearable></el-input>
      </div>
      <div class="operation">
        <el-button type="text" icon="el-icon-refresh">刷新</el-button>
        <el-button type="text" icon="el-icon-delete">删除</el-button>
        <el-button type="text" icon="el-icon-circle-plus-outline" @click="materialEdit('add')">添加</el-button>
        <el-button type="text" icon="el-icon-edit" @click="materialEdit('update')">修改</el-button>
      </div>
    </div>
    <div class="tabelData">
      <el-table :data="materialList" height="100%">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column prop="name" label="器材名称">
        </el-table-column>
        <el-table-column prop="model" label="型号/规格">
        </el-table-column>
        <el-table-column prop="safetyRisk" label="安全风险">
        </el-table-column>
        <el-table-column prop="deposit" label="存放位置">
        </el-table-column>
        <el-table-column prop="count" label="数量">
        </el-table-column>
        <el-table-column prop="status" label="状态">
        </el-table-column>
        <el-table-column prop="personLiable" label="责任人">
        </el-table-column>
        <el-table-column prop="contact" label="联系方式">
        </el-table-column>
      </el-table>
    </div>
    <div class="paging">
      <el-pagination background :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400" class="paging-detail">
      </el-pagination>
    </div>
    <material-edit :materialeditTitle="materialeditTitle" :materialeditVisible="materialeditVisible" @materialeditClose="materialeditVisible=false">
    </material-edit>
  </div>
</template>
<script>
import materialEdit from '../module/materialedit.vue'

const equipmentService = require('../../../service/equipmentService.js');
export default {
  data() {
    return {
      materialeditTitle: '',
      materialeditVisible: false,
      searchForm: '',
      materialList: [{
        name: '灭火器',
        model: 'ST3506',
        deposit: '楼道',
        count: '299',
        status: '良好',
        personLiable: '张三',
        contact: '13409897657'
      }, {
        name: '灭火器',
        model: 'ST3506',
        deposit: '楼道',
        count: '299',
        status: '良好',
        personLiable: '张三',
        contact: '13409897657'
      }, {
        name: '灭火器',
        model: 'ST3506',
        deposit: '楼道',
        count: '299',
        status: '良好',
        personLiable: '张三',
        contact: '13409897657'
      }, {
        name: '灭火器',
        model: 'ST3506',
        deposit: '楼道',
        count: '299',
        status: '良好',
        personLiable: '张三',
        contact: '13409897657'
      }]
    }
  },

  components: {
    materialEdit
  },

  created() {
    this.getListData();
  },

  methods: {
    /**
     * 获取器材列表数据
     */
    getListData() {
      equipmentService.list(res => {
          console.log(res);
      });      
    },


    /**
     * 器材数据添加or编辑
     */
    materialEdit(type) {
      if (type == 'add') {
        this.materialeditTitle = '添加设备器材'
      } else {
        this.materialeditTitle = '修改设备器材'
      }

      this.materialeditVisible = true;
      console.log(this.materialeditVisible);
    }
  }
}

</script>
<style scoped>
#material {
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
  width: 280px;
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
