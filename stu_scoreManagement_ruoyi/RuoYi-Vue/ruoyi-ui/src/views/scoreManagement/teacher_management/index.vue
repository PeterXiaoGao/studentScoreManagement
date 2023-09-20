<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="学院id" prop="deptId">
          <el-input
            v-model="queryParams.deptId"
            placeholder="请输入学院id"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="评教状态" prop="evaluateState">
          <el-input
            v-model="queryParams.evaluateState"
            placeholder="请输入评教状态"
            clearable
            disabled
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <!-- <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button> -->
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
  
      <el-table v-loading="loading" :data="teacherList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="教师id" align="center" prop="teacherId" />
        <el-table-column label="学院id" align="center" prop="deptId" />
        <el-table-column label="状态" align="center" prop="status" />
        <el-table-column label="教师工号" align="center" prop="teacherNo" />
        <el-table-column label="真实姓名" align="center" prop="realName" />
        <el-table-column label="性别" align="center" prop="gender" />
        <el-table-column label="评教成绩" align="center" prop="evaluateScore" />
        <el-table-column label="评教状态" align="center" prop="evaluateState" />
        <el-table-column label="职称" align="center" prop="academicTitle" />
        <el-table-column label="是否外聘" align="center" prop="internal" />
        <el-table-column label="用户id" align="center" prop="userId" />
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
  
      <!-- 添加或修改教师管理对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="学院id" prop="deptId">
            <el-input v-model="form.deptId" placeholder="请输入学院id" />
          </el-form-item>
          <el-form-item label="教师工号" prop="teacherNo">
            <el-input v-model="form.teacherNo" placeholder="请输入教师工号" />
          </el-form-item>
          <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="form.realName" placeholder="请输入真实姓名" />
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-input v-model="form.gender" placeholder="请输入性别" />
          </el-form-item>
          <el-form-item label="评教成绩" prop="evaluateScore">
            <el-input v-model="form.evaluateScore" placeholder="请输入评教成绩" />
          </el-form-item>
          <el-form-item label="职称" prop="academicTitle">
            <el-input v-model="form.academicTitle" placeholder="请输入职称" />
          </el-form-item>
          <el-form-item label="是否外聘" prop="internal">
            <el-input v-model="form.internal" placeholder="1表示内部，0表示外聘教师" />
          </el-form-item>
          <el-form-item label="用户id" prop="userId">
            <el-input v-model="form.userId" placeholder="请输入用户id" />
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
  import { listTeacher, getTeacher, delTeacher, addTeacher, updateTeacher  ,teacherById} from "@/api/teacher_management_small/teacher";
  
  export default {
    name: "Teacher",
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
        // 教师管理表格数据
        teacherList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          deptId: null,
          status: null,
          teacherNo: null,
          realName: null,
          gender: null,
          evaluateScore: null,
          evaluateState: null,
          academicTitle: null,
          internal: null,
          userId: null,
          teacherId:null
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
      /** 查询教师管理列表 */
      getList() {
        this.loading = true;
        listTeacher(this.queryParams).then(response => {
          this.teacherList = response.list;
          this.total = response?.list.length;
          this.loading = false;
        });
      },


      // 根据学院id来获取到该学院的所有老师
      getTeacherById(){
        teacherById(this.queryParams.deptId).then(response => {
          this.teacherList = response.list;
          this.total = response.pageInfo.totalRecord;
          this.loading = false;
      });
     },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          teacherId: null,
          deptId: null,
          status: null,
          delFlag: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          teacherNo: null,
          realName: null,
          gender: null,
          evaluateScore: null,
          evaluateState: null,
          academicTitle: null,
          internal: null,
          userId: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getTeacherById();
      },
      // /** 重置按钮操作 */
      // resetQuery() {
      //   this.resetForm("queryForm");
      //   this.handleQuery();
      // },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.teacherId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加教师管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.open = true;
        console.log("1111");
        const teacherId = row.teacherId || this.ids
        console.log("222");
        getTeacher(teacherId).then(response => {
          console.log("333");
          this.form = response.list;
          console.log(this.form);
          console.log("444");

          this.title = "修改教师管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.teacherId != null) {
              updateTeacher(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addTeacher(this.form).then(response => {
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
        const teacherIds = row.teacherId || this.ids;
        this.$modal.confirm('是否确认删除教师管理编号为"' + teacherIds + '"的数据项？').then(function() {
          return delTeacher(teacherIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
    }
  };
  </script>
  