
import request from '@/utils/request'

// 查询查看授课列表
export function listTeacherCourse(teacherId) {
  return request({
    url: `/student/teacher/getTeachCourses?teacherId=${teacherId}`,
    method: 'get',
  })
}

