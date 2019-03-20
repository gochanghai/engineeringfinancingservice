<template>
  <div class="modifypwd">
    <el-dialog title="修改密码" :visible.sync="modifyVisible" width="22%" :before-close="modifyClose">
      <el-form label-width="80px" :model="userInfo" size="small">
        <el-form-item label="原密码">
          <el-input v-model="userInfo.usedPassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="userInfo.newPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="userInfo.confirmPassword"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
		    <el-button size="small" @click="modifyClose">取 消</el-button>
		    <el-button size="small" type="primary" @click="save">确 定</el-button>
		  </span>
    </el-dialog>
  </div>
</template>
<script>
const userService = require('../../service/userService.js');
export default {
  data() {
    return {
      userInfo: {
        usedPassword: '',
        newPassword: '',
        confirmPassword: ''
      }
    }
  },

  props: {
    modifyVisible: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    /**
     * 取消按钮处理函数
     */
    modifyClose() {
      this.$emit('modifyClose', false);
    },

    /**
     * 确定按钮处理函数
     */
    save(){
      let userInfo = this.userInfo;
      let res = userService.changepassword(userInfo.usedPassword, userInfo.newPassword);
      if(1 === res.code){
          console.log("修改成功");
          return;
      }
      console.log("失败");
    }
  }
}

</script>
<style scoped>
.dialog-footer {
  display: block;
  text-align: center;
}

</style>
