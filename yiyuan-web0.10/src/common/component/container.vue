<template>
  <div id="container">
    <el-container>
      <el-header>
        <div class="logo">
          <img src="">&nbsp;<span>易元安全监控平台</span>
        </div>
        <div class="title navigator router">
          <router-link to="/container/sharecenter">分控中心</router-link>
          <router-link to="/container/enterpriseinfo">企业信息</router-link>
          <router-link to="/container/alarmmanagement">告警管理</router-link>
        </div>
        <span class="mes-icon" :is-dot="true">
          <i class="el-icon-bell"></i>
        </span>
        <el-dropdown trigger="click" style="display: flex;align-items: center;" @command="handleCommand">
          <span class="el-dropdown-link user-manager">{{userName}}<i class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="user">用户信息</el-dropdown-item>
            <el-dropdown-item command="modifypwd">密码修改</el-dropdown-item>
            <el-dropdown-item command="out">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
    <modify-pwd :modifyVisible="modifyVisible" @modifyClose="modifyVisible = !modifyVisible"></modify-pwd>
    <!-- 退出提示 -->
    <el-dialog title="退出提示" :visible.sync="logOutVisible" width="20%" :before-close="logOutClose">
      <span>确定退出吗？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="logOutVisible = false" size="small">取 消</el-button>
    <el-button type="primary" @click="logOutVisible = false" size="small">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
import modifyPwd from '../component/modifypwd.vue'

export default {
  data() {
    return {
      modifyVisible: false,
      logOutVisible: false,
      userName: sessionStorage.getItem("username"),
    }
  },

  components: {
    modifyPwd
  },

  methods: {
    handleCommand(command) {
      if (command == 'modifypwd') {
        this.modifyVisible = true;
      } else if (command == 'out') {
        this.logOutVisible = true;
        this.logout();
      }
    },

    logOutClose() {
      this.logOutVisible = false;
    },

    /**
     * 用户信息处理函数
     */
    userInfo() {

    },

    /**
     * 密码处理函数
     */
    modifyClose() {

    },

    /**
     * 退出登录处理函数
     */
    logout() {
      console.log("logout...");
      this.$router.push('/login');
    }
  }
}

</script>
<style scoped>
.el-header {
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: space-between;
  line-height: 60px;
  background-color: #007ED7;
  color: #fff;
  overflow: hidden;
}

.user-manager {
  color: #fff;
}

.user-manager:hover {
  cursor: pointer;
}

.navigator {
  flex: 1;
  text-align: center;
}

.mes-icon {
  display: block;
  margin-right: 10px;
  width: 35px;
  height: 35px;
  line-height: 35px;
  text-align: center;
  border-radius: 50%;
  font-size: 24px;
  color: #fff;
}

.mes-icon:hover {
  cursor: pointer;
  background-color: rgba(0, 0, 0, 0.15);
  transition: all 0.43s ease;
}

.navigator a {
  margin-right: 35px;
  display: inline-block;
  height: 26px;
  line-height: 26px;
  text-decoration: none;
  color: #fff;
  font-size: 15px;
}

.navigator a.router-link-active {
  border-bottom: 3px solid #fff;
}

.el-main {
  padding: 0;
}

.dialog-footer {
  display: block;
  text-align: center;
}

</style>
