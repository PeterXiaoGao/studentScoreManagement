<template>
   <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item>
        <el-button type="primary" icon="el-icon-refresh" size="mini" @click="luru">录入</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="studentScoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生id" align="center" prop="studentId" />
      <el-table-column label="上课班级id" align="center" prop="courseclassId" />
      <el-table-column label="平时成绩" align="center" prop="pingshiScore" />
      <el-table-column label="期末成绩" align="center" prop="qimoScore" />
      <el-table-column label="总评成绩" align="center" prop="zongpingScore" />
      <el-table-column label="课程归属学院" align="center" prop="deptName" />
      <el-table-column label="课程" align="center" prop="courseName" />
      <el-table-column label="平时成绩占比" align="center" prop="pingshiPercent" />
      <el-table-column label="期末成绩占比" align="center" prop="qimoPercent" />
      <el-table-column label="真实姓名" align="center" prop="realName" />
      </el-table>  

    <!-- 录入成绩的弹窗 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="学生id" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生的id" />
        </el-form-item>
        <el-form-item label="上课班级id" prop="courseclassId">
          <el-input v-model="form.courseclassId" placeholder="请输入上课班级的id" />
        </el-form-item>
        <el-form-item label="平时成绩" prop="pingshiScore">
          <el-input v-model="form.pingshiScore" placeholder="请输入平时成绩" />
        </el-form-item>
        <el-form-item label="期末成绩" prop="qimoScore">
          <el-input v-model="form.deptId" placeholder="请输入期末成绩" />
        </el-form-item>
        <el-form-item label="综评成绩" prop="zongpingScore">
          <el-input v-model="form.zongpingScore" placeholder="请输入总评成绩" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"  @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

       <!-- 分页器功能 -->
      <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
      
    </div>
</template>

<script>
import { listStudentScore,changestudentScore,luruScore } from "@/api/student_management/studentScore";

export default {
  name: "StudentScore",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 学生成绩表格数据
      studentScoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      total:0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId:131080,
        courseclassId:null,
        pingshiScore:null,
        qimoScore:null,
        zongpingScore:null
      },
      // 表单参数
      form: {},
        // 表单校验
        rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询学生成绩列表 */
    getList() {
      this.loading = true;
      listStudentScore(this.queryParams.studentId).then(response => {
        this.studentScoreList = response.list;
        this.total = response.pageInfo.totalRecord;
        this.loading = false;
      });
    },
    
    //修改学生的成绩
    changeScore(){
      this.loading = true;
      changestudentScore(this.queryParams).then(response => {
        this.studentScoreList = response.list;
        this.loading = false;
      });
    },
    // 录入学生成绩
    lurustudentScore(){
      this.loading = true;
      luruScore(this.queryParams).then(response => {
        this.studentScoreList = response.list;
        this.loading = false;
      });
    },
      // 表单重置
      reset() {
      this.form = {
        meataclassId: null,
        metaclassNo: null,
        metaclassName: null,
        grade: null,
        deptId: null,
        status: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    // 录入的弹窗控制
    luru(){
      this.reset();
      this.open = true;
      this.title = "录入成绩"
    },
     // 取消按钮
     cancel() {
      this.open = false;
      this.reset();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.meataclassId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
     /** 提交按钮 */
     submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
           {
            luruScore(this.form).then(response => {
              this.getList();
              this.$modal.msgSuccess("新增成功");
              this.open = false;
            });
          }
        }
      });
    },
  }
};
</script>
