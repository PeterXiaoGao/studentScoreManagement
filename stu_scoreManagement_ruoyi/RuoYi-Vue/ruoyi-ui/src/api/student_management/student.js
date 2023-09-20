import request from '@/utils/request'

// 查询学生管理列表
export function listStudent(query) {
  return request({
    url: '/student/stuInfo/list',
    method: 'get',
    params: query
  })
}

// 查询学生管理详细
export function getStudent(studentId) {
  return request({
    url: '/scoreManagement/student/' + studentId,
    method: 'get'
  })
}

// 新增学生管理
export function addStudent(data) {
  return request({
    url: '/scoreManagement/student',
    method: 'post',
    data: data
  })
}

// 修改学生管理
export function updateStudent(data) {
  return request({
    url: '/scoreManagement/student',
    method: 'put',
    data: data
  })
}

// 删除学生管理
export function delStudent(studentId) {
  return request({
    url: '/scoreManagement/student/' + studentId,
    method: 'delete'
  })
}
