<template>
  <div class="useredit">
    <el-dialog :title="usereditTitle" :visible.sync="usereditVisible" width="20%" :before-close="usereditClose">
      <el-form label-width="80px" :model="usereditData" size="small">
        <el-form-item label="姓名">
          <el-input v-model="usereditData.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="职责">
          <el-select v-model="usereditData.duty" style="display: block;" clearable>
            <el-option v-for="item in dutyType" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职务">
          <el-input v-model="usereditData.post" clearable></el-input>
        </el-form-item>
        <el-form-item label="身份证">
          <el-input v-model="usereditData.IDCard" clearable></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="usereditData.contact" clearable></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="usereditClose">取 消</el-button>
        <el-button size="small" type="primary" @click="save">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
const enterpriseinfoService = require('../../../service/enterpriseinfoService.js');
export default {
  data() {
    return {
      usereditData: {
        name: '',
        duty: '',
        post: '',
        IDCard: '',
        contact: ''
      },

      dutyType: [{
        id: '0',
        name: '主要负责人'
      }, {
        id: '1',
        name: '安全管理员'
      }, {
        id: '2',
        name: '安全员'
      }]
    }
  },

  props: {
    usereditTitle: {
      type: String,
      default: ''
    },
    usereditVisible: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    /**
     * 关闭弹窗
     */
    usereditClose() {
      this.$emit('usereditClose', false)
    },

    /**
     * 获取数据
     */
    getRiskIdentifyInfo(){
      if("修改企业人员名单" === this.usereditTitle){
        let id = this.riskeditData.id;
        enterpriseinfoService.getUserById(id, res => {
            console.log(res);
        });
      }   
    },

    /**
     * 保存数据
     */
    save(){
      let riskIdentify = {
        id: this.usereditData.id,
        name: this.usereditData.name,
        duty: this.usereditData.duty,
        post: this.usereditData.post,
        IDCard: this.usereditData.IDCard,
        contact: this.usereditData.contact,
      }
      // 判断添加还是更新
      if("添加企业人员名单" === this.usereditTitle){
        enterpriseinfoService.addUser(riskIdentify, res => {
          console.log(res);
        });
      }

      if("修改企业人员名单" === this.usereditTitle){
        enterpriseinfoService.updateUserById(riskIdentify, res => {
          console.log(res);
        });
      }
      
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
