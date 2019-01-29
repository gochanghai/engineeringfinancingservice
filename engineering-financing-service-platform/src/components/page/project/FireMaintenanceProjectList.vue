<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="24">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>维保与检测工程</span>
                    </div>
                    <div class="top-btn-box">
                        <el-button type="warning" icon="le-icon-text" @click="addListVisible = true">批量导入</el-button>
                        <el-button type="warning" icon="el-icon-plus" @click="addVisible = true">添加工程</el-button>
                    </div>
                    <div class="project-box">
                        <el-table :data="tableData" border class="table" ref="multipleTable"
                                  :header-cell-style="{background:'#e84d10',color: '#fff'}" :row-class-name="tableRowClassName">
                            <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
                            <el-table-column prop="projectName" label="项目名称" align="center">
                            </el-table-column>
                            <el-table-column prop="floorArea" label="建筑面积（㎡）" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.floorArea}}
                                </template>
                            </el-table-column>
                            <el-table-column prop="projectType" label="建筑类型" align="center" width="200px">
                                <!--<template slot-scope="scope">-->
                                <!--<el-tag type="success" v-show=" scope.row.userType === 1 " >一类</el-tag>-->
                                <!--<el-tag type="success" v-show=" scope.row.userType === 2 " >二类</el-tag>-->
                                <!--</template>-->
                            </el-table-column>
                            <el-table-column prop="projectLocation" label="项目地点" align="center" width="200px">
                                <!--<template slot-scope="scope">-->
                                <!--<el-tag type="success" v-show=" scope.row.status === 1 " >正常</el-tag>-->
                                <!--<el-tag type="danger" v-show=" scope.row.status === 0 " >停止</el-tag>-->
                                <!--</template>-->
                            </el-table-column>
                            <el-table-column prop="remark" label="备注" width="150" align="center">
                                <!--<template slot-scope="scope">-->
                                <!--<el-tag type="warning">修改密码</el-tag>-->
                                <!--<el-tag type="success" v-show=" scope.row.status === 0 ">启用</el-tag>-->
                                <!--<el-tag type="danger" v-show=" scope.row.status === 1 " >停止</el-tag>-->
                                <!--</template>-->
                            </el-table-column>
                        </el-table>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <!-- 添加项目弹出框 -->
        <el-dialog title="新增项目" :visible.sync="addVisible" width="30%" center="">
            <el-form ref="form" :model="form" :rules="rules" label-width="100px">
                <el-form-item label="建筑类型" prop="projectType">
                    <el-select v-model="form.projectType" placeholder="请建筑类型">
                        <el-option key="办公" label="办公" value="办公"></el-option>
                        <el-option key="商业" label="商业" value="商业"></el-option>
                        <el-option key="住宅" label="住宅" value="住宅"></el-option>
                        <el-option key="厂房" label="厂房" value="厂房"></el-option>
                        <el-option key="公共" label="公共" value="公共"></el-option>
                        <el-option key="展馆" label="展馆" value="展馆"></el-option>
                        <el-option key="办公+厂房" label="办公+厂房" value="办公+厂房"></el-option>
                        <el-option key="办公+商业" label="办公+商业" value="办公+商业"></el-option>
                        <el-option key="办公+酒店" label="办公+酒店" value="办公+酒店"></el-option>
                        <el-option key="办公+仓库" label="办公+仓库" value="办公+仓库"></el-option>
                        <el-option key="超市" label="超市" value="超市"></el-option>
                        <el-option key="市政" label="市政" value="市政"></el-option>
                        <el-option key="酒店" label="酒店" value="酒店"></el-option>
                        <el-option key="医院" label="医院" value="医院"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="项目名称" prop="projectName">
                    <el-input v-model="form.projectName"></el-input>
                </el-form-item>
                <el-form-item label="建筑面积" prop="floorArea">
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
        <el-dialog title="批量导入项目数据" :visible.sync="addListVisible" width="410px" center>
            <el-upload
                    class="upload-demo"
                    ref="upload"
                    name="file"
                    action="http://192.168.1.98:8088/fire-maintenance-project/importExecl"
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
        name: 'UserManagement',
        data() {
            return {
                companyId: localStorage.getItem('userInfoId'),
                url: './static/vuetable.json',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                addVisible: false,
                fileList:[],
                addListVisible: false,
                form: {
                    projectType: '',
                    projectName: '',
                    floorArea: '',
                    projectLocation: '',
                    remark: ''
                },
                rules: {
                    projectType: [
                        {required: true, message: '建筑类型不能为空', trigger: 'blur'}
                    ],
                    projectName: [
                        {required: true, message: '项目名不能为空', trigger: 'change'}
                    ],
                    floorArea: [
                        {required: true, message: '建筑面积不能为空', trigger: 'change'}
                    ],
                    projectLocation: [
                        {required: true, message: '项目地点不能为空', trigger: 'change'}
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
                this.$axios.get('fire-maintenance-project/list',{params:{
                        id: 0
                    }}).then(function (response) {
                    console.log(response);
                    _than.tableData = response.data.extend.list;
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
            delAll() {
                const length = this.multipleSelection.length;
                let str = '';
                this.del_list = this.del_list.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].name + ' ';
                }
                this.$message.error('删除了' + str);
                this.multipleSelection = [];
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 保存
            save(formName){
                let _than = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        _than.$axios.post('fire-maintenance-project/save',
                            _than.qs.stringify(
                                {
                                    projectType: this.form.projectType,
                                    projectName: this.form.projectName,
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

<style>
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

