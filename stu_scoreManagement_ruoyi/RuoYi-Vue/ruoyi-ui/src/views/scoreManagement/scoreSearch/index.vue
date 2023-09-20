<template>
    <div class="app-container">
      <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="学生id" align="center" prop="studentId" />
        <el-table-column label="课程id" align="center" prop="courseclassId" />
        <el-table-column label="平时成绩" align="center" prop="pingshiScore" />
        <el-table-column label="期末成绩" align="center" prop="qimoScore" />
        <el-table-column label="姓名" align="center" prop="realName" />
        <el-table-column label="性别" align="center" prop="gender" />
        <el-table-column label="年级" align="center" prop="grade" />
        <el-table-column label="用户id" align="center" prop="userId" />
      </el-table> 
        <!--分页器的实现-->
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
  import {searchScore,studentListAll} from "@/api/student_management/scoreSearch";
// 用来获取当前学生的studentId
import { getoneStudent } from "../../../utils/getUserId";

  export default {
    name: "Student",
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
        
        total :0, //总条数
        studentList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          meataclassId: null,
          pingshiScore:null,
          qimoScore:null,
          grade:null,
          studentNo: null,
          realName: null,
          gender: null,
          grade: null,
          userId: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        },
      };

    },

    created() {
      this.getStudentId();
    },

    methods: {
      // 遍历所有学生获取到当前和咱们一致的studentId
     // 声明 newList 为全局
     getStudentId(){
        getoneStudent().then(studentId=>{
          // 要是学生登录的话就显示学生的成绩但是要是领导或者其他人登录的话默认显示的是131080学生的信息
            this.getList(studentId)
        })
     },
      /** 查询当前学生成绩 */
      getList(studentId) {
          this.loading = true;
          searchScore(studentId).then(response => {
          this.studentList = response.list;
          this.total = response.pageInfo.totalRecord;
          this.loading = false;
        });
      },

      // 表单重置
      reset() {
        this.form = {
          studentId: null,
          meataclassId: null,
          status: null,
          delFlag: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          studentNo: null,
          realName: null,
          gender: null,
          grade: null,
          userId: null
        };
        this.resetForm("form");
      },

      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.studentId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
    }
  };
  </script>
  