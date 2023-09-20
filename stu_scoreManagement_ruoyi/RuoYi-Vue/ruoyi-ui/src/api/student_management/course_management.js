import request from '@/utils/request'

// 查询课程管理列表   完成
export function listCourse_management(query) {
  return request({
    url: '/student/course/list',
    method: 'get',
    params: query
  })
}

// 根据课程id查询到课程的详细信息  完成
export function getCourse_management(courseId) {
  return request({
    url:`student/course/oneCourse?courseId=${courseId}`,
    method: 'get'
  })
}

// 新增课程管理   完成
export function addCourse_management(query) {
  return request({
    // const: {courseName,courseNo,createBy,deptId,pingshiPercent,qimoPercent} = data ,
    url: '/student/course/addCourse',
    method: 'post',
    params:query
  })
}

// 修改课程管理   完成
export function updateCourse_management(query) {
  return request({
    url: '/student/course/updateCourse',
    method: 'put',
    params:query
  })
}

// 删除课程管理      完成
export function delCourse_management(courseId) {
  return request({
    url:`student/course/deleteCourse?courseId=${courseId}`,
    method: 'delete'
  })
}

//根据学院id获取本学院的所有课程 完成
export function collegeCourseById(deptId) {
  return request({
    url:`student/course/collegeCourse?deptId=${deptId}`,
    // url:`student/course/list`,
    method: 'get'
  })
}

// 通过调用该接口实现动态的院系选择
export function getAcademy() {
  return request({
    url:"system/dept/list",
    method: 'get'
  })
}
