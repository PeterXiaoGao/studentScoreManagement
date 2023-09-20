import request from '@/utils/request'

// 查询教师管理列表    完成
export function listTeacher(query) {
  return request({
    url: '/student/teacher/list',
    method: 'get',
    params: query
  })
}

// 获取取教师所教授的所有课程   完成
export function getTeacher(teacherId) {
  return request({
    url:`student/teacher/getTeachCourses?teacherId=${teacherId}`,
    method: 'get'
  })
}

// 新增教师管理    完成
export function addTeacher(query) {
  return request({
    url: '/student/teacher/addTeacher',
    method: 'post',
    params: query
  })
}

// 修改教师信息   完成
export function updateTeacher(query) {
  return request({
    url: '/student/teacher/modifyTeacher',
    method: 'put',
    params: query
  })
}

// 删除教师管理  完成
export function delTeacher(teacherId) {
  return request({
    url:`student/teacher/deleteTeacher?teacherId=${teacherId}`,
    method: 'delete'
  })
}

//根据学院id查询该学院下面的所有老师    有点bug  就是当学院id为100的时候报未知错误
export function teacherById(deptId) {
  return request({
    url:`student/teacher/collegeTeachers?deptId=${deptId}`,
    method: 'get'
  })
}
