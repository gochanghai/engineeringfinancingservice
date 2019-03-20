<template>
  <div class="useredit">
    <el-dialog :title="emergencyeditTitle" :visible.sync="emergencyeditVisible" width="20%" :before-close="emergencyeditClose">
      <el-form label-width="80px" :model="emergencyeditData" size="small">
        <el-form-item label="专业分工">
          <el-input v-model="emergencyeditData.division" clearable></el-input>
        </el-form-item>
        <el-form-item label="应急指挥职务">
          <el-input v-model="emergencyeditData.command" clearable></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="emergencyeditData.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="行政职务">
          <el-input v-model="emergencyeditData.position" clearable></el-input>
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="emergencyeditData.phone" clearable></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
		    <el-button size="small" @click="emergencyeditClose">取 消</el-button>
		    <el-button size="small" type="primary" @click="save">提 交</el-button>
		  </span>
    </el-dialog>
  </div>
</template>
<script>

const emergencyManagementService = require('../../../service/emergencyManagementService.js');
export default {
  data() {
    return {
      emergencyeditData: {
        division: '',
        command: '',
        name: '',
        position: '',
        phone: '',
        id: ''
      }
    }
  },

  props: {
    emergencyeditTitle: {
      type: String,
      default: ''
    },
    emergencyeditVisible: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    /**
     * 关闭窗口
     */
    emergencyeditClose() {
      this.$emit('emergencyeditClose', false)
    },

    /**
     * 获取数据
     */
    getData(){
      if("修改应急救援人员" === this.emergencyeditTitle){
        let id = this.emergencyeditData.id;
        emergencyManagementService.getById(id, res => {
            console.log(res);
        });
      }      
      
    },

    /**
     * 保存数据
     */
    save(){
      let emergencyManagement = {
        id: this.emergencyeditData.id,
        phone: this.emergencyeditData.hazardSource,
        position: this.emergencyeditData.position,
        division: this.emergencyeditData.division,
        name: this.emergencyeditData.name,
        command: this.emergencyeditData.command,
      }
      // 判断添加还是更新
      if("添加应急救援人员" === this.emergencyeditTitle){
        emergencyManagementService.add(emergencyManagement, res => {
          console.log(res);
        });
      }

      if("修改应急救援人员" === this.emergencyeditTitle){
        emergencyManagementService.updateById(emergencyManagement, res =>{
          console.log(res)
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
