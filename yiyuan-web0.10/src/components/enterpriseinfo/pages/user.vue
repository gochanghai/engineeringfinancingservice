<template>
  <!-- 组织架构 -->
  <div id="user">
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
        <el-button type="text" icon="el-icon-circle-plus-outline" @click="useredit('add')">添加</el-button>
        <el-button type="text" icon="el-icon-edit" @click="useredit('update')">修改</el-button>
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
    <user-edit @usereditClose="usereditClose" :usereditVisible="usereditVisible" :usereditTitle="usereditTitle"></user-edit>
  </div>
</template>
<script>
import userEdit from '../module/useredit.vue'
const enterpriseinfoService = require('../../../service/enterpriseinfoService.js');
export default {
  data() {
    return {
      searchForm: '',
      usereditVisible: false,
      usereditTitle: '',
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
    userEdit
  },

  created() {
    this.getListData();
  },

  methods: {
    /**
     * 获取组织架构列表数据
     */
    getListData() {
      enterpriseinfoService.getUserList(res => {
          console.log(res);
      });
    },

    /**
     * 编辑或添加按钮处理函数
     */
    useredit(type) {
      if (type == 'add') {
        this.usereditTitle = '添加企业人员名单'
      } else {
        this.usereditTitle = '修改企业人员名单'
      }
      this.usereditVisible = true;
    },

    /**
     * 关闭弹窗
     */
    usereditClose(data) {
      this.usereditVisible = data;
    },

    uploadFile(file) {
      console.log(file);
    },

    /**
     * 删除前提示
     */
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    }

  }
}

</script>
<style scoped>
#user {
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

.file {
  position: absolute;
  width: 100px;
  opacity: 0;
}

.file:hover {
  cursor: pointer;
}

.file-name {
  position: absolute;
  top: 28px;
  left: 30px;
  width: 130px;
  font-size: 14px;
  color: #555555;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

</style>
