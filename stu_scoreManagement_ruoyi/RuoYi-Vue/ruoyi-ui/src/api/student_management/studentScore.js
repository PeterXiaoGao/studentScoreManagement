import request from '@/utils/request'

// 查询学生成绩列表
export function listStudentScore(studentId) {
  return request({
    url: `/student/stuInfo/score?studentId=131080`,
    method: 'get',
  })
}

// 录入学生成绩
export function luruScore(query) {
  return request({
    url: 'student/teacher/writeScore',
    method: 'post',
    params:query
  })
}