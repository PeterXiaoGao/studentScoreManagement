<template>
  <div class="app-container">
     <el-table v-loading="loading" :data="teacherCourseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="老师的id" align="center" prop="teacherId" />
      <el-table-column label="课程id" align="center" prop="courseId" />
      <el-table-column label="所属学院" align="center" prop="deptName" />
      <el-table-column label="课程名字" align="center" prop="courseName" />
      <el-table-column label="教师工号" align="center" prop="teacherNo" />
      <el-table-column label="学期" align="center" prop="term" />
      <el-table-column label="上课班级id" align="center" prop="courseclassId" />
      <el-table-column label="教师的名字" align="center" prop="realName" />
    </el-table>
  </div>
</template>

<script>
import { listTeacherCourse } from "@/api/student_management/teacherCourse";
import { getoneTeacher } from "../../../utils/getTeacherId";

export default {
  name: "TeacherCourse",
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
      // 查看授课表格数据
      teacherCourseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: 3756,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teacherId: [
          { required: true, message: "老师表的id不能为空", trigger: "blur" }
        ],
        courseId: [
          { required: true, message: "课程id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getTeacherId()
  },
  methods: {
    // 获取当前用户的teacherId
    getTeacherId(){
      getoneTeacher().then(teacherId=>{
        this.getList(teacherId)
        console.log(teacherId);
      })
    },
    /** 查询查看授课列表 */
    getList(teacherId) {
      this.loading = true;
      listTeacherCourse(teacherId).then(response => {
        this.teacherCourseList = response.list;
        this.loading = false;
      });
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.meataclassId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
  }
};
</script>
