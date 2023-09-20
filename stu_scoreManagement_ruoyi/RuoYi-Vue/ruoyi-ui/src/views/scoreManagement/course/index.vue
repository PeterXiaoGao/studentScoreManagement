<template>
    <div class="app-container">  
      <el-table v-loading="loading" :data="course_managementList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="课程id" align="center" prop="courseId" />
        <el-table-column label="状态" align="center" prop="status" />
        <el-table-column label="课程号" align="center" prop="courseNo" />
        <el-table-column label="课程名" align="center" prop="courseName" />
        <el-table-column label="平时成绩占比" align="center" prop="pingshiPercent" />
        <el-table-column label="期末成绩占比" align="center" prop="qimoPercent" />
      </el-table>

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
import { listCourse} from "@/api/student_management/course";
// 用来获取当前学生的studentId
import { getoneStudent } from "../../../utils/getUserId";
  
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
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getStudentId();
    },
    methods: {
      // 获取当前用户的studentId
     getStudentId(){
        getoneStudent().then(studentId=>{
            this.getList(studentId) 
        })
     },

      /** 查询当前用户的课程安排 */
      getList(studentId) {
        // 这个是动画
        this.loading = true;
        listCourse(studentId).then(response => {
          this.course_managementList = response.list;
          this.total = response.pageInfo.totalRecord;
          this.loading = false;
        });
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.courseId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
    }
  };
  </script>
  