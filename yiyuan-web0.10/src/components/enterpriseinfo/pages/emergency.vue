<template>
  <!-- 应急管理 -->
  <div id="emergency">
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
        <el-button type="text" icon="el-icon-circle-plus-outline" @click="emergencyEdit('add')">添加</el-button>
        <el-button type="text" icon="el-icon-edit" @click="emergencyEdit('update')">修改</el-button>
      </div>
    </div>
    <div class="edit-txt">
      <div style="color: #555555" v-if="!isEdit">{{editTxt}} <i class="el-icon-edit" @click="isEdit=true"></i></div>
      <div style="display: flex;width: 54%" v-else>
        <el-input v-model="editTxt" style="margin-right: 5px;" clearable size="small"></el-input>
        <el-button type="primary" @click="isEdit=false" size="small">提交</el-button>
      </div>
    </div>
    <div class="tabelData">
      <el-table :data="userList" height="100%">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column prop="enterprisename" label="企业名称">
        </el-table-column>
        <el-table-column prop="username" label="用户名">
        </el-table-column>
        <el-table-column prop="phone" label="电话">
        </el-table-column>
        <el-table-column prop="IDcard" label="身份证">
        </el-table-column>
        <el-table-column prop="loginAt" label="登录时间">
        </el-table-column>
        <el-table-column prop="position" label="职务">
        </el-table-column>
        <el-table-column prop="Duties" label="职责">
        </el-table-column>
      </el-table>
    </div>
    <div class="paging">
      <el-pagination background :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400" class="paging-detail">
      </el-pagination>
    </div>
    <emergency-edit :emergencyeditTitle="emergencyeditTitle" :emergencyeditVisible="emergencyeditVisible" @emergencyeditClose="emergencyeditVisible=false"></emergency-edit>
  </div>
</template>
<script>
import emergencyEdit from '../module/emergencyedit.vue'

const emergencyManagementService = require('../../../service/emergencyManagementService.js');
export default {
  data() {
    return {
      emergencyeditTitle: '',
      emergencyeditVisible: false,
      isEdit: false,
      searchForm: '',
      editTxt: '深圳易元安全技术有限公司安全生产事故应急预案已经龙岗区安监局备案通过，取得安全生产事故应急预案备案登记证。',
      userList: [{
        enterprisename: "深圳易元安全技术有限公司",
        username: "谢小宝",
        phone: "130909487686",
        IDcard: "429006109987875646",
        loginAt: "2019/03/10 18:30:00",
        position: "企业主任",
        Duties: "安全管理人员"
      }, {
        enterprisename: "深圳易元安全技术有限公司",
        username: "谢小宝",
        phone: "130909487686",
        IDcard: "429006109987875646",
        loginAt: "2019/03/10 18:30:00",
        position: "企业主任",
        Duties: "安全管理人员"
      }, {
        enterprisename: "深圳易元安全技术有限公司",
        username: "谢小宝",
        phone: "130909487686",
        IDcard: "429006109987875646",
        loginAt: "2019/03/10 18:30:00",
        position: "企业主任",
        Duties: "安全管理人员"
      }, {
        enterprisename: "深圳易元安全技术有限公司",
        username: "谢小宝",
        phone: "130909487686",
        IDcard: "429006109987875646",
        loginAt: "2019/03/10 18:30:00",
        position: "企业主任",
        Duties: "安全管理人员"
      }]
    }
  },
  components: {
    emergencyEdit
  },
  created() {
    this.getListData();
  },
  methods: {
    edit() {
      this.isEdit = true;
    },
    /**
     * 获取应急管理列表数据
     */
    getListData() {
      emergencyManagementService.list(res => {
          console.log(list);
      });
      
    },

    /**
     * 获取应急管理添加or编辑
     */
    emergencyEdit(type) {
      if (type == 'add') {
        this.emergencyeditTitle = '添加应急救援人员'
      } else {
        this.emergencyeditTitle = '修改应急救援人员'
      }

      this.emergencyeditVisible = true;
    },

    /**
     * 上传附件
     */
    uploadFile(file) {
      console.log(file);
    },

    /**
     * 删除前弹窗提示
     */
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    }
  },
}

</script>
<style scoped>
#emergency {
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

.edit-txt {
  padding: 30px 0;
  padding-left: 64px;
  font-size: 15px;
}

.edit-txt div i:hover {
  cursor: pointer;
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
