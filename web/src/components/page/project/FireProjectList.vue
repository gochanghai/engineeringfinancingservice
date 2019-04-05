<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>工程业绩</span>
                    </div>
                    <div class="top-btn-box">
                        <el-button class="btn-color" type="warning" icon="le-icon-text" @click="addListVisible = true">批量导入</el-button>
                        <el-button class="btn-color" type="warning" icon="el-icon-plus" @click="addVisible = true">添加工程</el-button>
                    </div>
                    <div class="project-box">
                        <el-table :data="tableData" border class="table" v-loading="loading" ref="multipleTable"
                                  :header-cell-style="{background:'#f1561d',color: '#fff'}" :row-class-name="tableRowClassName">
                            <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
                            <el-table-column prop="projectName" label="工程名称" align="center">
                            </el-table-column>
                            <el-table-column label="工程造价（万元）" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.projectCost}}万
                                </template>
                            </el-table-column>
                            <el-table-column prop="floorArea" label="建筑面积（㎡）" align="center" width="200px">
                            </el-table-column>
                            <el-table-column prop="projectType" label="建筑类型" align="center">
                            </el-table-column>
                            <el-table-column prop="projectLocation" label="工程地点" align="center" width="200px">
                            </el-table-column>
                            <el-table-column prop="remark" label="备注" width="150" align="center">
                            </el-table-column>
                        </el-table>
                    </div>
                </el-card>
            </el-col>
        </el-row>


        <!-- 添加工程弹出框 -->
        <el-dialog title="新增工程" :visible.sync="addVisible" width="30%" center="">
        <el-form ref="form" :model="form" :rules="rules" label-width="140px">
        <el-form-item label="建筑类型" prop="projectType">
        <el-select v-model="form.projectType" placeholder="请建筑类型">
        <el-option key="写字楼" label="写字楼" value="写字楼"></el-option>
        <el-option key="商业" label="商业" value="商业"></el-option>
        <el-option key="商住综合体" label="商住综合体" value="商住综合体"></el-option>
        <el-option key="公共建筑" label="公共建筑" value="公共建筑"></el-option>
        <el-option key="住宅" label="住宅" value="住宅"></el-option>
        <el-option key="仓储厂房" label="仓储厂房" value="仓储厂房"></el-option>
        <el-option key="机电工程" label="机电工程" value="机电工程"></el-option>
        <el-option key="装修装饰工程" label="装修装饰工程" value="装修装饰工程"></el-option>
        <el-option key="智能化工程" label="智能化工程" value="智能化工程"></el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
        <el-input v-model="form.projectName"></el-input>
        </el-form-item>
        <el-form-item label="工程造价（万元）" prop="projectCost">
        <el-input v-model="form.projectCost"></el-input>
        </el-form-item>
        <el-form-item label="建筑面积（㎡）" prop="floorArea">
        <el-input v-model="form.floorArea"></el-input>
        </el-form-item>
        <el-form-item label="项目地点" prop="projectLocation">
        <el-input v-model="form.projectLocation"></el-input>
        </el-form-item>
        <el-form-item label="备注">
        <el-input v-model="form.remark"></el-input>
        </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="addVisible = false">取 消</el-button>
        <el-button type="primary" @click="save('form')">确 定</el-button>
        </span>
        </el-dialog>

        <!-- 批量导入工程数据弹框 -->
        <el-dialog title="批量导入工程数据" :visible.sync="addListVisible" width="410px" center>
        <el-upload
        class="upload-demo"
        ref="upload"
        name="file"
        action="http://192.168.1.98:8088/fire-project/importExecl"
        :on-success="handleSuccess"
        :on-remove="handleRemove"
        :file-list="fileList"
        :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取excel文件</el-button>
        <div slot="tip" class="el-upload__tip">只能上传excel文件</div>
        </el-upload>
        <span slot="footer" class="dialog-footer">
        <el-button @click="addListVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpload">确 定</el-button>
        </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'fire-project-list',
        data() {
            return {
                companyId: localStorage.getItem('userInfoId'),
                loading: true,
                url: './static/vuetable.json',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                addVisible: false,
                addListVisible: false,
                fullscreenLoading: false,
                fileList:[],
                form: {
                    projectType: '',
                    projectName: '',
                    projectCost: '',
                    floorArea: '',
                    projectLocation: '',
                    remark: ''
                },
                rules: {
                    projectType: [
                        {required: true, message: '建筑类型不能为空', trigger: 'blur'}
                    ],
                    projectName: [
                        {required: true, message: '工程名不能为空', trigger: 'change'}
                    ],
                    projectCost: [
                        {required: true, message: '工程造价不能为空', trigger: 'change'}
                    ],
                    floorArea: [
                        {required: true, message: '建筑面积不能为空', trigger: 'change'}
                    ],
                    projectLocation: [
                        {required: true, message: '工程地点不能为空', trigger: 'change'}
                    ],
                },
                idx: -1
            }
        },
        created() {
            this.getDataList();
        },

        computed: {
            data() {
                return this.tableData.filter((d) => {
                    let is_del = false;
                    for (let i = 0; i < this.del_list.length; i++) {
                        if (d.name === this.del_list[i].name) {
                            is_del = true;
                            break;
                        }
                    }
                    if (!is_del) {
                        if (d.address.indexOf(this.select_cate) > -1 &&
                            (d.name.indexOf(this.select_word) > -1 ||
                                d.address.indexOf(this.select_word) > -1)
                        ) {
                            return d;
                        }
                    }
                })
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 获取数据
            getDataList() {
                let _than = this;
                console.log("companyId: " +  localStorage.getItem('userInfoId'));
                this.$axios.get('fire-project/list',{params:{
                        id: 0
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
                    _than.loading = false;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            // table 行变色
            tableRowClassName({row, rowIndex}) {
                if (rowIndex%2 === 1) {
                    return 'warning-row';
                }
                return '';
            },
            search() {
                this.is_search = true;
            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.tableData[index];
                this.form = {
                    name: item.name,
                    date: item.date,
                    address: item.address
                }
                this.editVisible = true;
            },
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 保存
            save(formName){
                let _than = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        _than.$axios.post('fire-project/save',
                            _than.qs.stringify(
                                {
                                    projectType: this.form.projectType,
                                    projectName: this.form.projectName,
                                    projectCost: this.form.projectCost,
                                    floorArea: this.form.floorArea,
                                    projectLocation: this.form.projectLocation,
                                    remark: this.form.remark,
                                }
                            )).then(function (response) {
                            console.log(response);
                            _than.addVisible = false;
                            _than.getDataList();
                        }).catch(function (error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
                this.$message.success('保存成功！');

            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handleSuccess(response,file,files){
                this.addListVisible = false;
                console.log("response::: " + response);
                this.$message.success('保存成功！');
                this.getDataList()
            },
        }
    }

</script>

<!--scoped-->
<style>
    .btn-color{
        background-color: #f1561d;
        border-color: #f1561d;
    }
    .table {
        background-color: #ffffff;
    }

    .el-table .warning-row {
        background: #ccc;
    }

    .top-btn-box {
        margin-bottom: 10px;
        /*padding-right: 10px;*/
        text-align: right;
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .el-upload--text {
    width: 110px;
    height: 35px;
    }

</style>

