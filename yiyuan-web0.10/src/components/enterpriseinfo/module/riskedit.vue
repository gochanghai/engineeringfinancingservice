<template>
  <div class="riskIdentifyedit">
    <el-dialog :title="riskeditTitle" :visible.sync="riskeditVisible" width="40%" :before-close="riskeditClose">
      <el-form label-width="100px" :model="riskeditData" size="small" style="display: flex;justify-content: space-between;">
        <div style="width: 45%">
          <el-form-item label="危险源">
            <el-input v-model="riskeditData.hazardSource" clearable></el-input>
          </el-form-item>
          <el-form-item label="安全风险">
            <el-input v-model="riskeditData.safetyRisk" clearable></el-input>
          </el-form-item>
          <el-form-item label="可能造成伤害">
            <el-input v-model="riskeditData.causeHarm" clearable></el-input>
          </el-form-item>
          <el-form-item label="风险值(L)">
            <el-input v-model="riskeditData.riskValueL" clearable></el-input>
          </el-form-item>
          <el-form-item label="风险值(E)">
            <el-input v-model="riskeditData.riskValueE" clearable></el-input>
          </el-form-item>
        </div>
        <div style="width: 45%;margin-right: 5%">
          <el-form-item label="风险值(C)">
            <el-input v-model="riskeditData.riskValueC" clearable></el-input>
          </el-form-item>
          <el-form-item label="风险值(D)">
            <el-input v-model="riskeditData.riskValueD" clearable></el-input>
          </el-form-item>
          <el-form-item label="风险等级">
            <el-input v-model="riskeditData.grade" clearable></el-input>
          </el-form-item>
          <el-form-item label="控制措施">
            <el-input v-model="riskeditData.measures" clearable></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="riskeditData.remarks" clearable></el-input>
          </el-form-item>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="riskeditClose">取 消</el-button>
        <el-button size="small" type="primary" @click="save">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>

const riskIdentifyService = require('../../../service/riskIdentifyService.js')
export default {
  data() {
    return {
      riskeditData: {
        id: '',
        hazardSource: '',
        safetyRisk: '',
        causeHarm: '',
        riskValueL: '',
        riskValueE: '',
        riskValueC: '',
        riskValueD: '',
        grade: '',
        measures: '',
        remarks: ''
      }
    }
  },

  props: {
    riskeditTitle: {
      type: String,
      default: ''
    },
    riskeditVisible: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    /**
     * 关闭弹窗
     */
    riskeditClose() {
      this.$emit('riskeditClose', false);
    },

    /**
     * 获取数据
     */
    getRiskIdentifyInfo(){
      if("修改作业风险标识" === this.riskeditTitle){
        let id = this.riskeditData.id;
        riskIdentifyService.getById(id, res => {
          console.log(res);
        });
      }      
      
    },

    /**
     * 保存数据
     */
    save(){
      let riskIdentify = {
        id: this.riskeditData.id,
        hazardSource: this.riskeditData.hazardSource,
        safetyRisk: this.riskeditData.safetyRisk,
        causeHarm: this.riskeditData.causeHarm,
        riskValueL: this.riskeditData.riskValueL,
        riskValueE: this.riskeditData.riskValueE,
        riskValueC: this.riskeditData.riskValueC,
        riskValueD: this.riskeditData.riskValueD,
        grade: this.riskeditData.grade,
        measures: this.riskeditData.measures,
        remarks: this.riskeditData.remarks,
      }
      // 判断添加还是更新
      if("添加作业风险标识" === this.riskeditTitle){
        riskIdentifyService.add(riskIdentify, res => {
          console.log(res);
        });
      }

      if("修改作业风险标识" === this.riskeditTitle){
        riskIdentifyService.updateById(riskIdentify, res => {
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
