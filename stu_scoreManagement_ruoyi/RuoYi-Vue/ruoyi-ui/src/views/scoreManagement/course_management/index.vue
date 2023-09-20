<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="学院id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入学院id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="平时成绩占比" prop="pingshiPercent">
        <el-input
          v-model="queryParams.pingshiPercent"
          placeholder="请输入平时成绩占比"
          clearable
          disabled
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="course_managementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程id" align="center" prop="courseId" />
      <el-table-column label="学院id" align="center" prop="deptId" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="课程号" align="center" prop="courseNo" />
      <el-table-column label="课程名" align="center" prop="courseName" />
      <el-table-column label="课程类型" align="center" prop="courseType" />
      <el-table-column label="平时成绩占比" align="center" prop="pingshiPercent" />
      <el-table-column label="期末成绩占比" align="center" prop="qimoPercent" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改课程管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="课程名" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名" />
        </el-form-item>
        <el-form-item label="课程号" prop="courseNo">
          <el-input v-model="form.courseNo" placeholder="请输入课程号" />
        </el-form-item>
        <el-form-item label="创始人的名字" prop="createBy">
          <el-input v-model="form.createBy" placeholder="admin" />
        </el-form-item>
        <el-form-item label="归属学院" prop="deptId">
          <!-- 如何在里面添加一个下拉选择框 -->
          <el-select v-model="form.deptId" placeholder="请选择学院">
          <el-option label="哈哈哈哈" value="101"></el-option>
          <el-option label="嘻嘻嘻嘻" value="102"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="平时成绩占比" prop="pingshiPercent">
          <el-input v-model="form.pingshiPercent" placeholder="请输入平时成绩占比" />
        </el-form-item>
        <el-form-item label="期末成绩占比" prop="qimoPercent">
          <el-input v-model="form.qimoPercent" placeholder="请输入期末成绩占比" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCourse_management, getCourse_management, delCourse_management, addCourse_management, updateCourse_management,collegeCourseById , getAcademy} from "@/api/student_management/course_management";
import { getInfo } from "@/api/login";
export default {
  name: "Course_management",
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
      // 总条数
      total: 0,
      // 课程管理表格数据
      course_managementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 学院信息数组
      AcademyList:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        status: null,
        courseNo: null,
        courseName: null,
        courseType: null,
        pingshiPercent: null,
        qimoPercent: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getAcademyList()
    this.ifUserId()
  },
  methods: {
    /** 查询课程管理列表 */
    ifUserId(){
       getInfo().then(response=>{
          let id = response.user.deptId
          if(id===101|| id===102){
            // 学院管理员调用该接口
            this.getCourseById(id)
          }else {
            this.getList()
          }
       })
    },


    // 获取所有的id
    getList() {
      this.loading = true;
      listCourse_management(this.queryParams).then(response => {
        this.course_managementList = response.list;
        this.total = response.pageInfo.totalRecord;
        this.loading = false;
      });
    },
    // 通过deptId查询学院所有的课程
    getCourseById(deptId){
      this.loading = true;
       collegeCourseById(deptId).then(response => {
        this.course_managementList = response.list;
        this.loading = false;
    })
   },
  //  通过该接口实现动态获取到学院的id和对应的名字
  getAcademyList(){
    getAcademy().then(response=>{
      this.AcademyList = response.data
    })
  },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        courseId: null,
        deptId: null,
        status: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        courseNo: null,
        courseName: null,
        courseType: null,
        pingshiPercent: null,
        qimoPercent: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getCourseById(this.queryParams.deptId);
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.courseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const courseId = row.courseId || this.ids
      getCourse_management(courseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.courseId != null) {
            updateCourse_management(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse_management(this.form).then(response => {
              this.getList();
              this.$modal.msgSuccess("新增成功");
              this.open = false;
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const courseIds = row.courseId || this.ids;
      this.$modal.confirm('是否确认删除课程管理编号为"' + courseIds + '"的数据项？').then(function() {
        return delCourse_management(courseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
  }
};

</script>
